package com.zqlc.www.mvp.message;

import android.content.Context;

import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class ShopIsOpenPresenter implements ShopIsOpenContract.Presenter{

    ShopIsOpenContract.View iView;
    Context context;

    public ShopIsOpenPresenter(Context context, ShopIsOpenContract.View view) {
        this.iView = view;
        this.context = context;
    }


    @Override
    public void shopIsOpen() {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                iView.shopIsOpenSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.shopIsOpenFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();

        MethodApi.shopIsOpen(params, new OnSuccessAndFaultSub(listener, context, false));
    }
}
