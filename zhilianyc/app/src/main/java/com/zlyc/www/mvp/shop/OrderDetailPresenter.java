package com.zlyc.www.mvp.shop;

import android.content.Context;

import com.zlyc.www.bean.shop.OrderDetailBean;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class OrderDetailPresenter implements OrderDetailContract.Presenter {

    Context context;
    OrderDetailContract.View iView;

    public OrderDetailPresenter(Context context, OrderDetailContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void getOrderDetail(String uid, String orderId) {
        ResponseCallback listener = new ResponseCallback<OrderDetailBean>() {
            @Override
            public void onSuccess(OrderDetailBean data) {
                iView.getOrderDetailSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getOrderDetailFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("orderId",orderId);

        MethodApi.getOrderDetail(params, new OnSuccessAndFaultSub(listener, context));
    }
}
