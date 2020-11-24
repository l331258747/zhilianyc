package com.zqlc.www.mvp.shop;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.shop.OrderListBean;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

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

        MethodApi.getOrderList(params, new OnSuccessAndFaultSub(listener, context,false));
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
