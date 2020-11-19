package com.zlyc.www.mvp.shop;

import com.zlyc.www.bean.shop.OrderListBean;

import java.util.List;

public interface OrderListContract {

    interface Presenter {
        void getOrderList(String uid,int type);
    }

    interface View {
        void getOrderListSuccess(List<OrderListBean> data);
        void getOrderListFailed(String msg);

    }
}
