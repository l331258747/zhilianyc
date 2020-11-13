package com.zlyc.www.mvp.address;

import android.content.Context;

import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.address.AddressBean;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressSetPresenter implements AddressSetContract.Presenter{

    AddressSetContract.View iView;
    Context context;

    public AddressSetPresenter(Context context, AddressSetContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void addressList(String uid) {
        ResponseCallback listener = new ResponseCallback<List<AddressBean>>() {
            @Override
            public void onSuccess(List<AddressBean> data) {
                iView.addressListSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.addressListFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);

        MethodApi.addressList(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void addressDelete(String uid, String addressId) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.addressDeleteSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.addressDeleteFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("addressId",addressId);

        MethodApi.addressDelete(params, new OnSuccessAndFaultSub(listener, context));
    }
}
