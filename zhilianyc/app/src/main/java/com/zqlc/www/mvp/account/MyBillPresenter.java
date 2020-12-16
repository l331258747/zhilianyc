package com.zqlc.www.mvp.account;

import android.content.Context;

import com.zqlc.www.bean.BasePageModel;
import com.zqlc.www.bean.account.MyBillListBean;
import com.zqlc.www.constant.Constant;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

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
    public void getBill(String uid, int type, int page) {
        ResponseCallback listener = new ResponseCallback<BasePageModel<MyBillListBean>>() {
            @Override
            public void onSuccess(BasePageModel<MyBillListBean> data) {
                iView.getBillSuccess(data.getList());
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getBillFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("page", page + "");
        params.put("size", Constant.DEFAULT_SIZE + "");

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
