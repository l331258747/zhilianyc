package com.zlyc.www.mvp.otc;

import android.content.Context;

import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class OtcFeedbackPresenter implements OtcFeedbackContract.Presenter {

    Context context;
    OtcFeedbackContract.View iView;

    public OtcFeedbackPresenter(Context context, OtcFeedbackContract.View iView) {
        this.context = context;
        this.iView = iView;
    }


    @Override
    public void otcFeedback(String uid, File file) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                iView.otcFeedbackSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.otcFeedbackFailed(errorMsg);
            }
        };

        MethodApi.otcFeedback(uid,file, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void getOtcHandle(String uid, int sendStatus, String beansSendId,String complainContent,String complainImg,String complainMobile) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.getOtcHandleSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getOtcHandleFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("sendStatus", sendStatus + "");
        params.put("beansSendId", beansSendId);
        params.put("complainContent", complainContent);
        params.put("complainImg", complainImg);
        params.put("complainMobile", complainMobile);

        MethodApi.getOtcHandle(params, new OnSuccessAndFaultSub(listener, context));
    }
}
