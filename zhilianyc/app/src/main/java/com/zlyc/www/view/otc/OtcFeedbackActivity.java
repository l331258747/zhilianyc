package com.zlyc.www.view.otc;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.adapter.ImageAdapter;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.mvp.otc.OtcFeedbackContract;
import com.zlyc.www.mvp.otc.OtcFeedbackPresenter;
import com.zlyc.www.util.LoginUtil;
import com.zlyc.www.util.MyTextWatcher.MyTexxtWatcher;
import com.zlyc.www.util.accessory.ImageUtils;
import com.zlyc.www.util.dialog.LoadingDialog;
import com.zlyc.www.util.photo.TackPicturesUtil;
import com.zlyc.www.util.rxbus.RxBus2;
import com.zlyc.www.util.rxbus.busEvent.UpLoadPhotos;
import com.zlyc.www.util.thread.MyThreadPool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.Disposable;

public class OtcFeedbackActivity extends BaseActivity implements OtcFeedbackContract.View, View.OnClickListener {


    OtcFeedbackPresenter mPresenter;

    TextView tv_words, btn_submit;
    EditText et_content, et_phone;
    RecyclerView recyclerView;
    ImageView iv_camera;

    List<String> filePaths = new ArrayList<>();

    String beansSendId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_otc_feedback;
    }

    @Override
    public void initView() {
        showLeftAndTitle("订单申诉");

        beansSendId = intent.getStringExtra("beansSendId");

        tv_words = $(R.id.tv_words);
        et_content = $(R.id.et_content);
        btn_submit = $(R.id.btn_submit);
        et_phone = $(R.id.et_phone);
        iv_camera = $(R.id.iv_camera);

        initRecycler();

        et_content.addTextChangedListener(new MyTexxtWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s))
                    tv_words.setText("0/500");
                else
                    tv_words.setText("(" + s.length() + "/500)");
            }
        });

        btn_submit.setOnClickListener(this);
        iv_camera.setOnClickListener(this);

    }

    ImageAdapter mAdapter;

    private void initRecycler() {
        recyclerView = $(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new ImageAdapter(activity, new ArrayList<>());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter = new OtcFeedbackPresenter(context, this);
        loadingDialog = new LoadingDialog(context);
        tackPicUtil = new TackPicturesUtil(this);

        getPicPermission(context);
    }

    @Override
    public void otcFeedbackSuccess(String data) {
        loadingDialog.dismiss();
        filePaths.add(data);

        showShortToast("头像上传成功");

        if (filePaths.size() >= 3) {
            iv_camera.setVisibility(View.GONE);
        }

        mAdapter.setData(filePaths);
    }

    @Override
    public void otcFeedbackFailed(String msg) {
        loadingDialog.dismiss();
        showShortToast(msg);
    }

    @Override
    public void getOtcHandleSuccess(EmptyModel data) {
        showShortToast("提交成功");
    }

    @Override
    public void getOtcHandleFailed(String msg) {
        showShortToast(msg);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_camera:
                tackPicUtil.showDialog(context);
                break;
            case R.id.btn_submit:
                if (!LoginUtil.verifyEmpty(et_content.getText().toString(), "请输入申诉内容"))
                    return;
                if (filePaths.size() == 0) {
                    showShortToast("请选择申诉图片");
                    return;
                }

                mPresenter.getOtcHandle(MySelfInfo.getInstance().getUserId(),
                        7, beansSendId, et_content.getText().toString(), getImgUrls(), TextUtils.isEmpty(et_phone.getText().toString())?MySelfInfo.getInstance().getUserMobile():et_phone.getText().toString());
                break;
        }
    }

    public String getImgUrls() {
        String str = "";
        for (int i = 0; i < filePaths.size(); i++) {
            str = str + filePaths;
            if(i != filePaths.size() - 1){
                str = str + ",";
            }
        }
        return str;
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
     *
     * @param requestCode
     * @param resultCode
     * @param data
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
        mPresenter.otcFeedback(MySelfInfo.getInstance().getUserId(), file);
    }

    private void compressImage() {
        MyThreadPool.getInstance().submit(() -> {
            File file = new File(headpath);
            String savePath = TackPicturesUtil.IMAGE_CACHE_PATH + "crop" + file.getName();
            ImageUtils.getImage(headpath, savePath);
            headCompressPath = savePath;
            RxBus2.getInstance().post(new UpLoadPhotos());
        });
    }

    //----------------end 拍照
}
