package com.zqlc.www.mvp.my;

import android.content.Context;

import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class RealNameStatusPresenter implements RealNameStatusContract.Presenter{

    RealNameStatusContract.View iView;
    Context context;

    public RealNameStatusPresenter(Context context, RealNameStatusContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void realNameStatus(String uid) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                iView.realNameStatusSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.realNameStatusFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);

        MethodApi.realNameStatus(params, new OnSuccessAndFaultSub(listener, context,false));
    }
}
