package com.zlyc.www.mvp.otc;

import android.content.Context;

import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;

public class OtcFeedbackPresenter implements OtcFeedbackContract.Presenter {

    Context context;
    OtcFeedbackContract.View iView;

    public OtcFeedbackPresenter(Context context, OtcFeedbackContract.View iView) {
        this.context = context;
        this.iView = iView;
    }


    @Override
    public void otcFeedback(String uid,String file) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.otcFeedbackSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.otcFeedbackFailed(errorMsg);
            }
        };

        MethodApi.otcFeedback(uid,file, new OnSuccessAndFaultSub(listener, context));
    }
}
