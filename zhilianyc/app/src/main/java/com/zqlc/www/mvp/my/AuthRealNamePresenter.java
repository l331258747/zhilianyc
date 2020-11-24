package com.zqlc.www.mvp.my;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
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
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.authRealNameSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.authRealNameFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);

        MethodApi.authRealName(params, new OnSuccessAndFaultSub(listener, context,false));
    }
}
