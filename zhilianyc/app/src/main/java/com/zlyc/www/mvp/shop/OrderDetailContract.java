package com.zlyc.www.mvp.shop;

import com.zlyc.www.bean.shop.OrderDetailBean;

public interface OrderDetailContract {

    interface Presenter {
        void getOrderDetail(String uid, String orderId);
    }

    interface View {
        void getOrderDetailSuccess(OrderDetailBean data);
        void getOrderDetailFailed(String msg);

    }
}
