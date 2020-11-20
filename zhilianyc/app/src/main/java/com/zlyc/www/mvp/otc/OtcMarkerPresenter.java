package com.zlyc.www.mvp.otc;

import android.content.Context;

import com.zlyc.www.bean.otc.OtcInfoBean;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class OtcMarkerPresenter implements OtcMarkerContract.Presenter {

    Context context;
    OtcMarkerContract.View iView;

    public OtcMarkerPresenter(Context context, OtcMarkerContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void getOtcInfo() {
        ResponseCallback listener = new ResponseCallback<OtcInfoBean>() {
            @Override
            public void onSuccess(OtcInfoBean data) {
                iView.getOtcInfoSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getOtcInfoFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();

        MethodApi.getOtcInfo(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void getOtcOpen() {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                iView.getOtcOpenSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getOtcOpenFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();

        MethodApi.getOtcOpen(params, new OnSuccessAndFaultSub(listener, context));
    }
}
