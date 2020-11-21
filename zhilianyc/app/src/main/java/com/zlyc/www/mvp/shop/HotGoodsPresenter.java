package com.zlyc.www.mvp.shop;

import android.content.Context;

import com.zlyc.www.bean.BasePageModel;
import com.zlyc.www.bean.shop.HotGoodsBean;
import com.zlyc.www.constant.Constant;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class HotGoodsPresenter implements HotGoodsContract.Presenter {

    Context context;
    HotGoodsContract.View iView;

    public HotGoodsPresenter(Context context, HotGoodsContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void getHotGoods(int page) {
        ResponseCallback listener = new ResponseCallback<BasePageModel<HotGoodsBean>>() {
            @Override
            public void onSuccess(BasePageModel<HotGoodsBean> data) {
                iView.getHotGoodsSuccess(data.getList());
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getHotGoodsFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("page",page + "");
        params.put("size", Constant.DEFAULT_SIZE + "");

        MethodApi.getHotGoods(params, new OnSuccessAndFaultSub(listener, context,false));
    }
}
