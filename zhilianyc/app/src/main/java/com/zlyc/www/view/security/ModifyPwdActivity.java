package com.zlyc.www.view.security;

import android.widget.EditText;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.mvp.login.ResetPwdContract;
import com.zlyc.www.mvp.login.ResetPwdPresenter;
import com.zlyc.www.util.LoginUtil;

public class ModifyPwdActivity extends BaseActivity implements ResetPwdContract.View {

    EditText et_old_pwd, et_new_pwd, et_new_pwd2;
    TextView btn_submit;

    ResetPwdPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_pwd;
    }

    @Override
    public void initView() {
        showLeftAndTitle("修改登录密码");

        et_old_pwd = $(R.id.et_old_pwd);
        et_new_pwd = $(R.id.et_new_pwd);
        et_new_pwd2 = $(R.id.et_new_pwd2);
        btn_submit = $(R.id.btn_submit);

        btn_submit.setOnClickListener(v -> {
            if (!LoginUtil.verifyPassword(et_old_pwd.getText().toString()))
                return;
            if (!LoginUtil.verifyPassword(et_new_pwd.getText().toString()))
                return;
            if (!LoginUtil.verifyPassword(et_new_pwd2.getText().toString()))
                return;
            if (!LoginUtil.verifyPasswordDouble(et_new_pwd.getText().toString(), et_new_pwd2.getText().toString()))
                return;

            mPresenter.resetPwd(et_old_pwd.getText().toString(),et_new_pwd.getText().toString());


        });
    }

    @Override
    public void initData() {
        mPresenter = new ResetPwdPresenter(context,this);
    }

    @Override
    public void resetPwdSuccess(EmptyModel data) {
        showShortToast("修改成功");
        finish();
    }

    @Override
    public void resetPwdFailed(String msg) {
        showShortToast(msg);
    }
}
