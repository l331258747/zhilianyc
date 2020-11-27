package com.zqlc.www.mvp.my;

import android.content.Context;

import com.zqlc.www.bean.login.MineBean;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class FeePresenter implements FeeContract.Presenter{

    FeeContract.View iView;
    Context context;

    public FeePresenter(Context context, FeeContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void feeRatio(String uid) {
        ResponseCallback listener = new ResponseCallback<Float>() {
            @Override
            public void onSuccess(Float data) {
                iView.feeRatioSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.feeRatioFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);

        MethodApi.feeRatio(params, new OnSuccessAndFaultSub(listener, context));
    }
}
