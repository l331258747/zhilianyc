package com.zlyc.www.mvp.shop;

import android.content.Context;

import com.zlyc.www.bean.BasePageModel;
import com.zlyc.www.bean.shop.GoodsListBean;
import com.zlyc.www.constant.Constant;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class GoodsListPresenter implements GoodsListContract.Presenter {

    Context context;
    GoodsListContract.View iView;

    public GoodsListPresenter(Context context, GoodsListContract.View iView) {
        this.context = context;
        this.iView = iView;
    }


    @Override
    public void getGoodsList(String categoryId, int page) {
        ResponseCallback listener = new ResponseCallback<BasePageModel<GoodsListBean>>() {
            @Override
            public void onSuccess(BasePageModel<GoodsListBean> data) {
                iView.getGoodsListSuccess(data.getList());
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getGoodsListFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("categoryId",categoryId);
        params.put("page",page+"");
        params.put("size", Constant.DEFAULT_SIZE +"");

        MethodApi.getGoodsList(params, new OnSuccessAndFaultSub(listener, context,false));
    }
}
