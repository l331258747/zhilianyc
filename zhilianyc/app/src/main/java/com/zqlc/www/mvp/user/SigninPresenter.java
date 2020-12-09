package com.zqlc.www.mvp.user;

import android.content.Context;

import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class SigninPresenter implements SigninContract.Presenter {

    Context context;
    SigninContract.View iView;

    public SigninPresenter(Context context, SigninContract.View iView) {
        this.context = context;
        this.iView = iView;
    }


    @Override
    public void signin(String uid) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                iView.signinSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.signinFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);

        MethodApi.signin(params, new OnSuccessAndFaultSub(listener, context));
    }
}
