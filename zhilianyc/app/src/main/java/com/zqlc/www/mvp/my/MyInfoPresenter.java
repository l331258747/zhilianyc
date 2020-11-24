package com.zqlc.www.mvp.my;

import android.content.Context;

import com.zqlc.www.bean.login.MineBean;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class MyInfoPresenter implements MyInfoContract.Presenter{

    MyInfoContract.View iView;
    Context context;

    public MyInfoPresenter(Context context, MyInfoContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void mine(String uid,boolean isShow) {
        ResponseCallback listener = new ResponseCallback<MineBean>() {
            @Override
            public void onSuccess(MineBean data) {
                iView.mineSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.mineFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);

        MethodApi.mine(params, new OnSuccessAndFaultSub(listener, context,isShow));
    }

}
