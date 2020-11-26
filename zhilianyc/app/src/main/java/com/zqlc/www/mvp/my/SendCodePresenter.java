package com.zqlc.www.mvp.my;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class SendCodePresenter implements SendCodeContract.Presenter{

    SendCodeContract.View iView;
    Context context;

    public SendCodePresenter(Context context, SendCodeContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void sendCode(String mobile) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.sendCodeSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.sendCodeFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("mobile",mobile);

        MethodApi.sendCode(params, new OnSuccessAndFaultSub(listener, context));
    }
}
