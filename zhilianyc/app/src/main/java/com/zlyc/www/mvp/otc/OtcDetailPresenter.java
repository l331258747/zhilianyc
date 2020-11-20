package com.zlyc.www.mvp.otc;

import android.content.Context;

import com.zlyc.www.bean.otc.OtcDetailBean;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class OtcDetailPresenter implements OtcDetailContract.Presenter {

    Context context;
    OtcDetailContract.View iView;

    public OtcDetailPresenter(Context context, OtcDetailContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void getOtcDetail(String uid,String beansSendId) {
        ResponseCallback listener = new ResponseCallback<OtcDetailBean>() {
            @Override
            public void onSuccess(OtcDetailBean data) {
                iView.getOtcDetailSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getOtcDetailFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("beansSendId", beansSendId);

        MethodApi.getOtcDetail(params, new OnSuccessAndFaultSub(listener, context));
    }
}
