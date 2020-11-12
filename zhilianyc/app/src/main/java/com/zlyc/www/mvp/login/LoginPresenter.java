package com.zlyc.www.mvp.login;

import android.content.Context;

import com.zlyc.www.bean.login.LoginBean;
import com.zlyc.www.util.AppUtils;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;
import com.zlyc.www.util.md5.MD5Utils;

import java.util.HashMap;
import java.util.Map;

public class LoginPresenter implements LoginContract.Presenter{

    LoginContract.View iView;
    Context context;

    public LoginPresenter(Context context, LoginContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void login(String username, String password) {
        ResponseCallback listener = new ResponseCallback<LoginBean>() {
            @Override
            public void onSuccess(LoginBean data) {
                iView.loginSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.loginFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("mobile",username);
        params.put("password", MD5Utils.MD5(password));
        params.put("version",AppUtils.getVersionName());

        MethodApi.login(params, new OnSuccessAndFaultSub(listener, context));
    }
}
