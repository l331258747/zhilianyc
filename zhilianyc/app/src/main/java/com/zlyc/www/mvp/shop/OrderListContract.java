package com.zlyc.www.mvp.shop;

import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.shop.OrderListBean;

import java.util.List;

public interface OrderListContract {

    interface Presenter {
        void getOrderList(String uid,int type);

        void receiveOrder(String uid,String orderId);
        void cancelOrder(String uid,String orderId);
        void payOrder(String uid,String orderId,String fundPassword);
    }

    interface View {
        void getOrderListSuccess(List<OrderListBean> data);
        void getOrderListFailed(String msg);

        void receiveOrderSuccess(EmptyModel data);
        void receiveOrderFailed(String msg);

        void cancelOrderSuccess(EmptyModel data);
        void cancelOrderFailed(String msg);

        void payOrderSuccess(EmptyModel data);
        void payOrderFailed(String msg);

    }
}
