package com.zqlc.www.mvp.login;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.login.LoginBean;
import com.zqlc.www.util.AppUtils;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;
import com.zqlc.www.util.md5.MD5Utils;

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
        params.put("version",AppUtils.getVersionCode());

        MethodApi.login(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void register(String mobile, String vcode, String password, String code) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.registerSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.registerFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("mobile",mobile);
        params.put("vcode",vcode);
        params.put("password", MD5Utils.MD5(password));
        params.put("code",code);

        MethodApi.register(params, new OnSuccessAndFaultSub(listener, context));
    }
}
