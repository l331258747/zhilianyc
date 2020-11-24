package com.zqlc.www.mvp.shop;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.shop.OrderDetailBean;

public interface OrderDetailContract {

    interface Presenter {
        void getOrderDetail(String uid, String orderId);
        void receiveOrder(String uid,String orderId);
        void cancelOrder(String uid,String orderId);
        void payOrder(String uid,String orderId,String fundPassword);
    }

    interface View {
        void getOrderDetailSuccess(OrderDetailBean data);
        void getOrderDetailFailed(String msg);

        void receiveOrderSuccess(EmptyModel data);
        void receiveOrderFailed(String msg);

        void cancelOrderSuccess(EmptyModel data);
        void cancelOrderFailed(String msg);

        void payOrderSuccess(EmptyModel data);
        void payOrderFailed(String msg);

    }
}
