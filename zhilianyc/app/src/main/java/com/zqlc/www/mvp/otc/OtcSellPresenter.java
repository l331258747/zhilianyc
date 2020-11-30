package com.zqlc.www.mvp.otc;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;
import com.zqlc.www.util.md5.MD5Utils;

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
        params.put("payPassword", MD5Utils.MD5(payPassword));
        params.put("vcode", vcode);

        MethodApi.sendOtcSell(params, new OnSuccessAndFaultSub(listener, context));
    }
}
