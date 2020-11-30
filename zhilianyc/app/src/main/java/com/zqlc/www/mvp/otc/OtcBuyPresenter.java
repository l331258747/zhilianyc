package com.zqlc.www.mvp.otc;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;
import com.zqlc.www.util.md5.MD5Utils;

import java.util.HashMap;
import java.util.Map;

public class OtcBuyPresenter implements OtcBuyContract.Presenter {

    Context context;
    OtcBuyContract.View iView;

    public OtcBuyPresenter(Context context, OtcBuyContract.View iView) {
        this.context = context;
        this.iView = iView;
    }


    @Override
    public void sendOtcBuy(String uid, float unitPrice, int count, String payPassword) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.sendOtcBuySuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.sendOtcBuyFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("unitPrice", unitPrice + "");
        params.put("count", count + "");
        params.put("payPassword", MD5Utils.MD5(payPassword));

        MethodApi.sendOtcBuy(params, new OnSuccessAndFaultSub(listener, context));
    }
}
