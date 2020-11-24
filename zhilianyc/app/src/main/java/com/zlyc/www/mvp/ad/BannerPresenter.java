package com.zlyc.www.mvp.ad;

import android.content.Context;

import com.zlyc.www.bean.ad.BannerBean;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BannerPresenter implements BannerContract.Presenter{

    BannerContract.View iView;
    Context context;

    public BannerPresenter(Context context, BannerContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void getBanner() {
        ResponseCallback listener = new ResponseCallback<List<BannerBean>>() {
            @Override
            public void onSuccess(List<BannerBean> data) {
                iView.getBannerSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getBannerFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();

        MethodApi.getBanner(params, new OnSuccessAndFaultSub(listener, context));
    }
}
