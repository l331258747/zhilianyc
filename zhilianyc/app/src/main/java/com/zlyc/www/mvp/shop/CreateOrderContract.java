package com.zlyc.www.mvp.shop;

import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.address.AddressBean;

import java.util.List;

public interface CreateOrderContract {

    interface Presenter {
        void createOrder(String uid, String goodsId,String addressId,int num);
        void addressList(String uid);
    }

    interface View {
        void createOrderSuccess(EmptyModel data);
        void createOrderFailed(String msg);

        void addressListSuccess(List<AddressBean> datas);
        void addressListFailed(String msg);

    }
}
