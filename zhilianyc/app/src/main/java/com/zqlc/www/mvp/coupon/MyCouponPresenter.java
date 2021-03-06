package com.zqlc.www.mvp.coupon;

import android.content.Context;

import com.zqlc.www.bean.coupon.MyCouponBean;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class MyCouponPresenter implements MyCouponContract.Presenter{

    MyCouponContract.View iView;
    Context context;

    public MyCouponPresenter(Context context, MyCouponContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void getMyCoupon(String uid) {
        ResponseCallback listener = new ResponseCallback<MyCouponBean>() {
            @Override
            public void onSuccess(MyCouponBean data) {
                iView.getMyCouponSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getMyCouponFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);

        MethodApi.getMyCoupon(params, new OnSuccessAndFaultSub(listener, context,false));
    }
}
