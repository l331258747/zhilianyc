package com.zlyc.www.view.security;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.mvp.my.AuthRealNameContract;
import com.zlyc.www.mvp.my.AuthRealNamePresenter;
import com.zlyc.www.util.LoginUtil;
import com.zlyc.www.util.pickerView.PickerCityHelp;

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

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!LoginUtil.verifyName(et_real_name.getText().toString()))
                    return;
                if (!LoginUtil.verifyID(et_code.getText().toString()))
                    return;
                if (!LoginUtil.verifyEmpty(tv_address.getText().toString(),"请选择地区"))
                    return;

                mPresenter.authRealName(MySelfInfo.getInstance().getUserId(),et_real_name.getText().toString(),et_code.getText().toString(),tv_address.getText().toString());
            }
        });

        view_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPickerCityHelp.showPickerView(tv_address);
            }
        });
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
