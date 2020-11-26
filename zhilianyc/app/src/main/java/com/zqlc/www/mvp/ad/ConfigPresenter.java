package com.zqlc.www.mvp.ad;

import android.content.Context;

import com.zqlc.www.bean.ad.ConfigBean;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class ConfigPresenter implements ConfigContract.Presenter{

    ConfigContract.View iView;
    Context context;

    public ConfigPresenter(Context context, ConfigContract.View view) {
        this.iView = view;
        this.context = context;
    }


    @Override
    public void getAdConfig() {
        ResponseCallback listener = new ResponseCallback<ConfigBean>() {
            @Override
            public void onSuccess(ConfigBean data) {
                iView.getAdConfigSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getAdConfigFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();

        MethodApi.getAdConfig(params, new OnSuccessAndFaultSub(listener, context,false));
    }
}
