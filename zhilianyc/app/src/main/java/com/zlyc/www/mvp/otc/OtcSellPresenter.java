package com.zlyc.www.mvp.otc;

import android.content.Context;

import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class OtcSellPresenter implements OtcSellContract.Presenter {

    Context context;
    OtcSellContract.View iView;

    public OtcSellPresenter(Context context, OtcSellContract.View iView) {
        this.context = context;
        this.iView = iView;
    }


    @Override
    public void sendOtcSell(String uid, float unitPrice, int count, String payPassword, String vcode) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.sendOtcSellSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.sendOtcSellFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("unitPrice", unitPrice + "");
        params.put("count", count + "");
        params.put("payPassword", payPassword);
        params.put("vcode", vcode);

        MethodApi.sendOtcSell(params, new OnSuccessAndFaultSub(listener, context));
    }
}
