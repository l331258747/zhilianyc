package com.zlyc.www.view.security;

import android.content.Intent;
import android.view.View;

import com.zlyc.www.R;
import com.zlyc.www.base.BaseActivity;

public class SecurityActivity extends BaseActivity implements View.OnClickListener {

    View view_modify,view_capital,view_authentication,view_address,view_collection;


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

        view_modify.setOnClickListener(this);
        view_capital.setOnClickListener(this);
        view_authentication.setOnClickListener(this);
        view_address.setOnClickListener(this);
        view_collection.setOnClickListener(this);
    }

    @Override
    public void initData() {

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
}
