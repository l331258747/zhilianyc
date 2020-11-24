package com.zlyc.www.mvp.account;

import android.content.Context;

import com.zlyc.www.bean.account.MyBillBean;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class MyBillPresenter implements MybillContract.Presenter{

    MybillContract.View iView;
    Context context;

    public MyBillPresenter(Context context, MybillContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void getBill(String uid, int type) {
        ResponseCallback listener = new ResponseCallback<MyBillBean>() {
            @Override
            public void onSuccess(MyBillBean data) {
                iView.getBillSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getBillFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);

        if(type == 1){
            MethodApi.contributionRecord(params, new OnSuccessAndFaultSub(listener, context,false));
        }else if(type == 2){
            MethodApi.laborRecord(params, new OnSuccessAndFaultSub(listener, context,false));
        }else if(type == 3){
            MethodApi.sellableBeansRecord(params, new OnSuccessAndFaultSub(listener, context,false));
        }else{
            MethodApi.beansRecord(params, new OnSuccessAndFaultSub(listener, context,false));
        }

    }
}
