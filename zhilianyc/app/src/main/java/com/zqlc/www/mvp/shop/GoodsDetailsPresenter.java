package com.zqlc.www.mvp.shop;

import android.content.Context;

import com.zqlc.www.bean.shop.GoodsDetailsBean;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class GoodsDetailsPresenter implements GoodsDetailsContract.Presenter {

    Context context;
    GoodsDetailsContract.View iView;

    public GoodsDetailsPresenter(Context context, GoodsDetailsContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void getGoodsDetails(String uid,String goodsId) {
        ResponseCallback listener = new ResponseCallback<GoodsDetailsBean>() {
            @Override
            public void onSuccess(GoodsDetailsBean data) {
                iView.getGoodsDetailsSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getGoodsDetailsFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("goodsId",goodsId);

        MethodApi.getGoodsDetails(params, new OnSuccessAndFaultSub(listener, context));
    }
}
