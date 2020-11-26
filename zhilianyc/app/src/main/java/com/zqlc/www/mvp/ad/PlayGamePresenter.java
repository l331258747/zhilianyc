package com.zqlc.www.mvp.ad;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class PlayGamePresenter implements PlayGameContract.Presenter{

    PlayGameContract.View iView;
    Context context;

    public PlayGamePresenter(Context context, PlayGameContract.View view) {
        this.iView = view;
        this.context = context;
    }


    @Override
    public void playGameCallback() {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.playGameCallbackSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.playGameCallbackFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid", MySelfInfo.getInstance().getUserId());

        MethodApi.playGameCallback(params, new OnSuccessAndFaultSub(listener, context,false));
    }
}
