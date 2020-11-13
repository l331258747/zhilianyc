package com.zlyc.www.mvp.address;

import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.address.AddressBean;

import java.util.List;

public interface AddressSetContract {
    interface Presenter {
        void addressList(String uid);
        void addressDelete(String uid, String addressId);
    }

    interface View {
        void addressListSuccess(List<AddressBean> datas);
        void addressListFailed(String msg);

        void addressDeleteSuccess(EmptyModel data);
        void addressDeleteFailed(String msg);
    }
}
