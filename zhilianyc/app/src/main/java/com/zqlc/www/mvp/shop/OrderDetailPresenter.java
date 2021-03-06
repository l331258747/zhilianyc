package com.zqlc.www.mvp.shop;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.shop.OrderDetailBean;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

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

    @Override
    public void receiveOrder(String uid, String orderId) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.receiveOrderSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.receiveOrderFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("orderId",orderId);

        MethodApi.receiveOrder(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void cancelOrder(String uid, String orderId) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.cancelOrderSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.cancelOrderFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("orderId",orderId);

        MethodApi.cancelOrder(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void payOrder(String uid, String orderId, String fundPassword) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.payOrderSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.payOrderFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("orderId",orderId);
        params.put("fundPassword",fundPassword);

        MethodApi.payOrder(params, new OnSuccessAndFaultSub(listener, context));
    }
}
