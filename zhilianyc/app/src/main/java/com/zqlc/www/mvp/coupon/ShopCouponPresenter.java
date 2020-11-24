package com.zqlc.www.mvp.coupon;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.coupon.ShopCouponBean;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopCouponPresenter implements ShopCouponContract.Presenter{

    ShopCouponContract.View iView;
    Context context;

    public ShopCouponPresenter(Context context, ShopCouponContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void getShopCoupon(String uid) {
        ResponseCallback listener = new ResponseCallback<List<ShopCouponBean>>() {
            @Override
            public void onSuccess(List<ShopCouponBean> data) {
                iView.getShopCouponSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getShopCouponFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);

        MethodApi.getShopCoupon(params, new OnSuccessAndFaultSub(listener, context,false));
    }

    @Override
    public void buyShopCoupon(String uid, String type) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.buyShopCouponSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.buyShopCouponFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("type",type);

        MethodApi.buyShopCoupon(params, new OnSuccessAndFaultSub(listener, context));
    }
}
