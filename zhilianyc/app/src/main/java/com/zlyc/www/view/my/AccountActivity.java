package com.zlyc.www.view.my;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.base.ActivityCollect;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.bean.login.InfoBean;
import com.zlyc.www.dialog.EditDialog;
import com.zlyc.www.mvp.my.AccountContract;
import com.zlyc.www.mvp.my.AccountPresenter;
import com.zlyc.www.util.glide.GlideUtil;
import com.zlyc.www.view.login.LoginActivity;

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
    }

    @Override
    public void initData() {
        mPresenter = new AccountPresenter(context, this);
        mPresenter.info(MySelfInfo.getInstance().getUserId());


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_nickname:
                final String oldName = tv_nickname.getText().toString();

                new EditDialog(context).setContent(tv_nickname.getText().toString())
                        .setTitle("修改昵称").setSubmitListener(new EditDialog.OnItemClickListener() {
                    @Override
                    public void onClick(Dialog dialog,String content) {

                        if(TextUtils.isEmpty(content)){
                            showShortToast("昵称不能为空");
                            return;
                        }

                        if(TextUtils.equals(oldName,content)){
                            showShortToast("昵称相同");
                            return;
                        }

                        mPresenter.resetNickname(MySelfInfo.getInstance().getUserId(),content);

                        newNickname = content;

                        dialog.dismiss();
                    }
                }).show();

                break;
            case R.id.view_head:
                showShortToast("头像");
                break;
            case R.id.btn_loginOff:
                MySelfInfo.getInstance().loginOff();
                ActivityCollect.getAppCollect().finishAllActivity();
                startActivity(new Intent(context, LoginActivity.class));
                break;

        }
    }

    @Override
    public void infoSuccess(InfoBean data) {
        GlideUtil.LoadCircleImage(context, data.getHeadImg(), iv_head);
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
}
