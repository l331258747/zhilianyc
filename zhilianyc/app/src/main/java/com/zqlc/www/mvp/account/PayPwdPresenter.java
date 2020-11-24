package com.zqlc.www.mvp.account;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class PayPwdPresenter implements PayPwdContract.Presenter{

    PayPwdContract.View iView;
    Context context;

    public PayPwdPresenter(Context context, PayPwdContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void payPwd(String payPassword,String vcode) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.payPwdSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.payPwdFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("payPassword",payPassword);
        params.put("vcode", vcode);
        params.put("uid", MySelfInfo.getInstance().getUserId());
        params.put("mobile", MySelfInfo.getInstance().getUserMobile());

        MethodApi.payPwd(params, new OnSuccessAndFaultSub(listener, context));
    }
}
