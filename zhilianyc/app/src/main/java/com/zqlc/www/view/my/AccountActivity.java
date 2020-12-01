package com.zqlc.www.view.my;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.base.ActivityCollect;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.bean.login.InfoBean;
import com.zqlc.www.dialog.EditDialog;
import com.zqlc.www.dialog.TextDialog;
import com.zqlc.www.mvp.my.AccountContract;
import com.zqlc.www.mvp.my.AccountPresenter;
import com.zqlc.www.util.accessory.ImageUtils;
import com.zqlc.www.util.dialog.LoadingDialog;
import com.zqlc.www.util.glide.GlideUtil;
import com.zqlc.www.util.photo.TackPicturesUtil;
import com.zqlc.www.util.rxbus.RxBus2;
import com.zqlc.www.util.rxbus.busEvent.UpLoadPhotos;
import com.zqlc.www.util.thread.MyThreadPool;
import com.zqlc.www.view.login.LoginActivity;

import java.io.File;

import io.reactivex.disposables.Disposable;

public class AccountActivity extends BaseActivity implements View.OnClickListener, AccountContract.View {

    View view_head, view_nickname;
    ImageView iv_head;
    TextView tv_nickname, tv_phone, tv_recommend, btn_loginOff;

    AccountPresenter mPresenter;

    String newNickname;

    @Override
    public int getLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    public void initView() {

        showLeftAndTitle("账户信息");

        view_head = $(R.id.view_head);
        view_nickname = $(R.id.view_nickname);
        iv_head = $(R.id.iv_head);
        tv_nickname = $(R.id.tv_nickname);
        tv_phone = $(R.id.tv_phone);
        tv_recommend = $(R.id.tv_recommend);
        btn_loginOff = $(R.id.btn_loginOff);

        view_head.setOnClickListener(this);
        view_nickname.setOnClickListener(this);
        btn_loginOff.setOnClickListener(this);

        getPicPermission(context);
    }

    @Override
    public void initData() {
        tackPicUtil = new TackPicturesUtil(activity);
        loadingDialog = new LoadingDialog(context);

        mPresenter = new AccountPresenter(context, this);
        mPresenter.info(MySelfInfo.getInstance().getUserId());

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_nickname:
                final String oldName = tv_nickname.getText().toString();

                new EditDialog(context).setContent(tv_nickname.getText().toString())
                        .setTitle("修改昵称").setSubmitListener((dialog, content) -> {

                    if (TextUtils.isEmpty(content)) {
                        showShortToast("昵称不能为空");
                        return;
                    }

                    if (TextUtils.equals(oldName, content)) {
                        showShortToast("昵称相同");
                        return;
                    }

                    mPresenter.resetNickname(MySelfInfo.getInstance().getUserId(), content);

                    newNickname = content;

                    dialog.dismiss();
                }).show();

                break;
            case R.id.view_head:
                tackPicUtil.showDialog(context);
                break;
            case R.id.btn_loginOff:
                new TextDialog(context).setContent("是否确认退出APP？").setSubmitListener(v1 -> {
                    final String phone = MySelfInfo.getInstance().getUserMobile();

                    MySelfInfo.getInstance().loginOff();
                    ActivityCollect.getAppCollect().finishAllActivity();

                    Intent intent = new Intent(new Intent(context, LoginActivity.class));
                    intent.putExtra("LOGIN_PHONE", phone);
                    context.startActivity(intent);
                }).show();
                break;

        }
    }

    @Override
    public void infoSuccess(InfoBean data) {
        if (TextUtils.isEmpty(data.getHeadImg())) {
            iv_head.setImageResource(R.mipmap.default_head);
        } else {
            GlideUtil.loadCircleImage(context, data.getHeadImg(), iv_head);
        }

        tv_nickname.setText(data.getNickName());
        tv_phone.setText(data.getMobile());
        tv_recommend.setText(data.getPmobile());
    }

    @Override
    public void infoFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void resetNicknameSuccess(EmptyModel data) {
        showShortToast("修改成功");
        tv_nickname.setText(newNickname);
    }

    @Override
    public void resetNicknameFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void resetHeadSuccess(String data) {
        loadingDialog.dismiss();
        showShortToast("头像上传成功");
        GlideUtil.loadCircleImage(context, headpath, iv_head);
    }

    @Override
    public void resetHeadFailed(String msg) {
        loadingDialog.dismiss();
        showShortToast(msg);
    }

    //图片
    private TackPicturesUtil tackPicUtil;
    private String headpath;// 头像地址
    private String headCompressPath;
    private Disposable disposable;
    private LoadingDialog loadingDialog;
    //-----------start 拍照-----------

    //拍照，存储权限
    public void getPicPermission(Context context) {
        tackPicUtil.checkPermission(context);
    }


    /**
     * 获取图片回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TackPicturesUtil.CHOOSE_PIC:
            case TackPicturesUtil.TACK_PIC:
            case TackPicturesUtil.CROP_PIC:
                String path = tackPicUtil.getPicture(requestCode, resultCode, data, false);
                if (path == null)
                    return;
                headpath = path;
                upFile();
                break;
            default:
                break;
        }
    }

    public void upFile() {
        disposable = RxBus2.getInstance().toObservable(UpLoadPhotos.class, upLoadPhotos -> {
            sendHead();
            disposable.dispose();
        });

        loadingDialog.showDialog("上传头像...");
        loadingDialog.setCancelable(false);
        compressImage();
    }

    public void sendHead() {
        //构建要上传的文件
        File file = new File(headCompressPath);
        mPresenter.resetHead(MySelfInfo.getInstance().getUserId(), file);
    }

    private void compressImage() {
        MyThreadPool.getInstance().submit(() -> {
            File file = new File(headpath);
            String savePath = TackPicturesUtil.IMAGE_CACHE_PATH + File.separator + "crop" + file.getName();
            ImageUtils.getImage(headpath, savePath);
            headCompressPath = savePath;
            RxBus2.getInstance().post(new UpLoadPhotos());
        });
    }

    //----------------end 拍照
}
