package com.zqlc.www.mvp.my;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.user.AuthRealInfoBean;
import com.zqlc.www.bean.user.AuthRealNameBean;
import com.zqlc.www.bean.user.AuthRealPayBean;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class AuthRealNamePresenter implements AuthRealNameContract.Presenter{

    AuthRealNameContract.View iView;
    Context context;

    public AuthRealNamePresenter(Context context, AuthRealNameContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void authRealName(String uid, String name, String idCard, String cityCode) {
        ResponseCallback listener = new ResponseCallback<AuthRealNameBean>() {
            @Override
            public void onSuccess(AuthRealNameBean data) {
                iView.authRealNameSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.authRealNameFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("name",name);
        params.put("idCard",idCard);
        params.put("cityCode",cityCode);

        MethodApi.authRealName(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void authRealPay(String uid, String name, String idCard, String cityCode) {
        ResponseCallback listener = new ResponseCallback<AuthRealPayBean>() {
            @Override
            public void onSuccess(AuthRealPayBean data) {
                iView.authRealPaySuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.authRealPayFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("name",name);
        params.put("idCard",idCard);
        params.put("cityCode",cityCode);

        MethodApi.authRealPay(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void realNameInfo(String uid) {
        ResponseCallback listener = new ResponseCallback<AuthRealInfoBean>() {
            @Override
            public void onSuccess(AuthRealInfoBean data) {
                iView.realNameInfoSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.realNameInfoFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);

        MethodApi.realNameInfo(params, new OnSuccessAndFaultSub(listener, context));
    }
}
