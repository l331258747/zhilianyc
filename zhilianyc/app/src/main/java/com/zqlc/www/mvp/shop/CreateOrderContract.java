package com.zqlc.www.mvp.shop;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.address.AddressBean;

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
