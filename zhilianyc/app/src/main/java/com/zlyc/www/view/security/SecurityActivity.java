package com.zlyc.www.view.security;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.mvp.my.RealNameStatusContract;
import com.zlyc.www.mvp.my.RealNameStatusPresenter;

import androidx.core.content.ContextCompat;

public class SecurityActivity extends BaseActivity implements View.OnClickListener, RealNameStatusContract.View {

    View view_modify,view_capital,view_authentication,view_address,view_collection;

    TextView tv_status;

    RealNameStatusPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_security;
    }

    @Override
    public void initView() {
        showLeftAndTitle("安全中心");

        view_modify = $(R.id.view_modify);
        view_capital = $(R.id.view_capital);
        view_authentication = $(R.id.view_authentication);
        view_address = $(R.id.view_address);
        view_collection = $(R.id.view_collection);
        tv_status = $(R.id.tv_status);

        view_modify.setOnClickListener(this);
        view_capital.setOnClickListener(this);
        view_authentication.setOnClickListener(this);
        view_address.setOnClickListener(this);
        view_collection.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new RealNameStatusPresenter(context,this);
        mPresenter.realNameStatus(MySelfInfo.getInstance().getUserId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_modify:
                startActivity(new Intent(context, ModifyPwdActivity.class));
                break;
            case R.id.view_capital:
                startActivity(new Intent(context, CapitalSetActivity.class));
                break;
            case R.id.view_authentication:
                startActivity(new Intent(context,AuthenticationActivity.class));
                break;
            case R.id.view_address:
                startActivity(new Intent(context,AddressSetActivity.class));
                break;
            case R.id.view_collection:
                startActivity(new Intent(context,CollectionActivity.class));
                break;
        }
    }

    @Override
    public void realNameStatusSuccess(String data) {
        if (TextUtils.equals(data, "1")) {
            tv_status.setText("已认证");
            tv_status.setTextColor(ContextCompat.getColor(context, R.color.color_61B53F));
        } else if (TextUtils.equals(data, "2")) {
            tv_status.setText("审核中");
            tv_status.setTextColor(ContextCompat.getColor(context, R.color.color_61B53F));
        } else if (TextUtils.equals(data, "0")) {
            tv_status.setText("审核不通过");
            tv_status.setTextColor(ContextCompat.getColor(context, R.color.color_red));
        } else {
            tv_status.setText("未认证");
            tv_status.setTextColor(ContextCompat.getColor(context, R.color.color_red));
        }
    }

    @Override
    public void realNameStatusFailed(String msg) {
        showShortToast(msg);
    }
}
