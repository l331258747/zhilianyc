package com.zqlc.www.mvp.address;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class AddressEditPresenter implements AddressEditContract.Presenter{

    AddressEditContract.View iView;
    Context context;

    public AddressEditPresenter(Context context, AddressEditContract.View view) {
        this.iView = view;
        this.context = context;
    }


    @Override
    public void addressEdit(String id, String uid, String name, String province, String city, String region, String address, String mobile, String type) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.addressEditSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.addressEditFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("id",id);
        params.put("uid",uid);
        params.put("name",name);
        params.put("province",province);
        params.put("city",city);
        params.put("region",region);
        params.put("address",address);
        params.put("mobile",mobile);
        params.put("type",type);

        MethodApi.addressEdit(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void addressAdd(String uid, String name, String province, String city, String region, String address, String mobile, String type) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.addressAddSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.addressAddFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("name",name);
        params.put("province",province);
        params.put("city",city);
        params.put("region",region);
        params.put("address",address);
        params.put("mobile",mobile);
        params.put("type",type);

        MethodApi.addressAdd(params, new OnSuccessAndFaultSub(listener, context));
    }
}
