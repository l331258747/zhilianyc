package com.zqlc.www.view.security;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.mvp.my.AuthRealNameContract;
import com.zqlc.www.mvp.my.AuthRealNamePresenter;
import com.zqlc.www.util.LoginUtil;
import com.zqlc.www.util.pickerView.PickerCityHelp;

public class AuthenticationActivity extends BaseActivity implements AuthRealNameContract.View {

    EditText et_real_name,et_code;
    View view_address;
    TextView tv_address,btn_submit;

    PickerCityHelp mPickerCityHelp;

    AuthRealNamePresenter mPresenter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_authentication;
    }

    @Override
    public void initView() {
        showLeftAndTitle("实名认证");

        et_real_name = $(R.id.et_real_name);
        et_code = $(R.id.et_code);
        view_address = $(R.id.view_address);
        tv_address = $(R.id.tv_address);
        btn_submit = $(R.id.btn_submit);

        btn_submit.setOnClickListener(v -> {
            if (!LoginUtil.verifyName(et_real_name.getText().toString()))
                return;
            if (!LoginUtil.verifyID(et_code.getText().toString()))
                return;
            if (!LoginUtil.verifyEmpty(tv_address.getText().toString(),"请选择地区"))
                return;

            mPresenter.authRealName(MySelfInfo.getInstance().getUserId(),et_real_name.getText().toString(),et_code.getText().toString(),tv_address.getText().toString());
        });

        view_address.setOnClickListener(v -> mPickerCityHelp.showPickerView((str1, str2, str3) -> tv_address.setText(str1 + " " + str2 + " " + str3)));
    }

    @Override
    public void initData() {
        mPresenter = new AuthRealNamePresenter(context,this);
        mPickerCityHelp = new PickerCityHelp(context);
        mPickerCityHelp.initData();
    }


    @Override
    public void authRealNameSuccess(EmptyModel data) {
        showShortToast("调用腾讯的几口，弹出一个框");
    }

    @Override
    public void authRealNameFailed(String msg) {

    }
}
