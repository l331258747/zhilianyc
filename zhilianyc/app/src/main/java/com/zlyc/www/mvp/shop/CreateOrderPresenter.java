package com.zlyc.www.mvp.shop;

import android.content.Context;

import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.address.AddressBean;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateOrderPresenter implements CreateOrderContract.Presenter {

    Context context;
    CreateOrderContract.View iView;

    public CreateOrderPresenter(Context context, CreateOrderContract.View iView) {
        this.context = context;
        this.iView = iView;
    }


    @Override
    public void createOrder(String uid, String goodsId,String addressId,int num) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.createOrderSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.createOrderFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("goodsId",goodsId);
        params.put("addressId", addressId);
        params.put("num", num + "");

        MethodApi.createOrder(params, new OnSuccessAndFaultSub(listener, context,false));
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
}
