package com.zqlc.www.mvp.ad;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class ReadNewsPresenter implements ReadNewsContract.Presenter{

    ReadNewsContract.View iView;
    Context context;

    public ReadNewsPresenter(Context context, ReadNewsContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void readNewsCallback() {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.readNewsCallbackSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.readNewsCallbackFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid", MySelfInfo.getInstance().getUserId());

        MethodApi.readNewsCallback(params, new OnSuccessAndFaultSub(listener, context,false));
    }
}
