package com.zqlc.www.mvp.login;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.login.VerifyImageBean;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class VerifyImagePresenter implements VerifyImageContract.Presenter{

    VerifyImageContract.View iView;
    Context context;

    public VerifyImagePresenter(Context context, VerifyImageContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void verifyImageConfirm(String mobile, int x, int y) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.verifyImageConfirmSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.verifyImageConfirmFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("mobile",mobile);
        params.put("x",x+"");
        params.put("y",y+"");



        MethodApi.verifyImageConfirm(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void verifyImage(String mobile) {
        ResponseCallback listener = new ResponseCallback<VerifyImageBean>() {
            @Override
            public void onSuccess(VerifyImageBean data) {
                iView.verifyImageSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.verifyImageFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("mobile",mobile);

        MethodApi.verifyImage(params, new OnSuccessAndFaultSub(listener, context));
    }
}
