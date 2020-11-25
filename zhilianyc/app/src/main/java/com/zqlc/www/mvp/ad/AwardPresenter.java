package com.zqlc.www.mvp.ad;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class AwardPresenter implements AwardContract.Presenter{

    AwardContract.View iView;
    Context context;

    public AwardPresenter(Context context, AwardContract.View view) {
        this.iView = view;
        this.context = context;
    }


    @Override
    public void awardCallback() {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.awardCallbackSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.awardCallbackFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();

        MethodApi.awardCallback(params, new OnSuccessAndFaultSub(listener, context,false));
    }
}
