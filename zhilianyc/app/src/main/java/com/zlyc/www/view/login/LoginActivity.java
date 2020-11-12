package com.zlyc.www.view.login;

import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.bean.login.LoginBean;
import com.zlyc.www.mvp.login.LoginContract;
import com.zlyc.www.mvp.login.LoginPresenter;
import com.zlyc.www.util.AppUtils;
import com.zlyc.www.util.LoginUtil;
import com.zlyc.www.util.StatusBarUtil;
import com.zlyc.www.util.ToastUtil;
import com.zlyc.www.view.home.HomeActivity;

import androidx.core.content.ContextCompat;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginContract.View {

    TextView tv_title,tv_register,tv_login,btn_login,btn_forget;
    LinearLayout tab_login,tab_register;
    View line_register,line_login;
    EditText et_phone,et_password;

    LoginPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

        StatusBarUtil.setStatusBar(this, ContextCompat.getColor(context,R.color.color_1C81E9));

        hideTitleLayout();

        tv_title = $(R.id.tv_title);

        line_login = $(R.id.line_login);
        line_register = $(R.id.line_register);
        tab_login = $(R.id.tab_login);
        tab_register = $(R.id.tab_register);
        tv_register = $(R.id.tv_register);
        tv_login = $(R.id.tv_login);

        et_password = $(R.id.et_password);
        et_phone = $(R.id.et_phone);

        btn_login = $(R.id.btn_login);
        btn_forget = $(R.id.btn_forget);

        tab_login.setOnClickListener(this);
        tab_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_forget.setOnClickListener(this);

        testLogin();

    }

    public void testLogin(){
        et_phone.setText("15616397868");
        et_password.setText("123456");
    }

    @Override
    public void initData() {
        tv_title.setText(String.format("智链云仓(%1$s)",AppUtils.getVersionName()));

        mPresenter = new LoginPresenter(context,this);

        if(MySelfInfo.getInstance().isLogin()){
            startActivity(new Intent(context, HomeActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tab_login:
                ToastUtil.showShortToast(context,"tab-登录");
                //情况状态  设置状态
                setTab(true);
                break;
            case R.id.tab_register:
                ToastUtil.showShortToast(context,"tab-注册");
                setTab(false);
                break;
            case R.id.btn_login:
                //判断账号密码    请求
                if (!LoginUtil.verifyPhone(et_phone.getText().toString()))
                    return;
                if (!LoginUtil.verifyPassword(et_password.getText().toString()))
                    return;
                //请求

                mPresenter.login(et_phone.getText().toString(),et_password.getText().toString());

                break;
            case R.id.btn_forget:
                ToastUtil.showShortToast(context,"忘记密码");
                //把手机号码带入

                break;

        }
    }

    public void setTab(boolean isLogin){
        tv_login.setTextColor(ContextCompat.getColor(context,R.color.color_99));
        tv_login.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        line_login.setVisibility(View.INVISIBLE);
        tv_register.setTextColor(ContextCompat.getColor(context,R.color.color_99));
        tv_register.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        line_register.setVisibility(View.INVISIBLE);

        if(isLogin){
            tv_login.setTextColor(ContextCompat.getColor(context,R.color.color_text));
            tv_login.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            line_login.setVisibility(View.VISIBLE);
        }else{
            tv_register.setTextColor(ContextCompat.getColor(context,R.color.color_text));
            tv_register.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            line_register.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void loginSuccess(LoginBean data) {
        MySelfInfo.getInstance().setLoginData(data,et_phone.getText().toString());
        startActivity(new Intent(context, HomeActivity.class));
        finish();
    }

    @Override
    public void loginFailed(String msg) {
        showShortToast(msg);
    }

}
