package com.zlyc.www.mvp.address;

import com.zlyc.www.bean.EmptyModel;

public interface AddressEditContract {
    interface Presenter {
        void addressEdit(String id, String uid, String name,String province,String city,String region,String address,String mobile,String type);
        void addressAdd(String uid, String name,String province,String city,String region,String address,String mobile,String type);
    }

    interface View {
        void addressEditSuccess(EmptyModel data);
        void addressEditFailed(String msg);

        void addressAddSuccess(EmptyModel data);
        void addressAddFailed(String msg);
    }
}
