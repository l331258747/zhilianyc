package com.zlyc.www.mvp.shop;

import android.content.Context;

import com.zlyc.www.bean.shop.OrderListBean;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderListPresenter implements OrderListContract.Presenter {

    Context context;
    OrderListContract.View iView;

    public OrderListPresenter(Context context, OrderListContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void getOrderList(String uid,int type) {
        ResponseCallback listener = new ResponseCallback<List<OrderListBean>>() {
            @Override
            public void onSuccess(List<OrderListBean> data) {
                iView.getOrderListSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getOrderListFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("type",type+"");

        MethodApi.getOrderList(params, new OnSuccessAndFaultSub(listener, context));
    }
}
