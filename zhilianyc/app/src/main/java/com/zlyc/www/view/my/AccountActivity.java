package com.zlyc.www.view.my;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.base.ActivityCollect;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.util.glide.GlideUtil;
import com.zlyc.www.view.login.LoginActivity;

public class AccountActivity extends BaseActivity implements View.OnClickListener {

    View view_head, view_nickname;
    ImageView iv_head;
    TextView tv_nickname, tv_phone, tv_recommend,btn_loginOff;

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
        GlideUtil.LoadCircleImage(context, MySelfInfo.getInstance().getUserAvatar(), iv_head);
        tv_nickname.setText(MySelfInfo.getInstance().getUserNickname());
        tv_phone.setText(MySelfInfo.getInstance().getUserMoble());
        tv_recommend.setText("13695621459");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_nickname:
                showShortToast("昵称");
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
}
