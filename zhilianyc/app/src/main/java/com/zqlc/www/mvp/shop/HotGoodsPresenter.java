package com.zqlc.www.mvp.shop;

import android.content.Context;

import com.zqlc.www.bean.BasePageModel;
import com.zqlc.www.bean.shop.GoodsClassBean;
import com.zqlc.www.bean.shop.HotGoodsBean;
import com.zqlc.www.constant.Constant;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.List;
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

    @Override
    public void getGoodsClass() {
        ResponseCallback listener = new ResponseCallback<List<GoodsClassBean>>() {
            @Override
            public void onSuccess(List<GoodsClassBean> data) {
                iView.getGoodsClassSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getGoodsClassFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();

        MethodApi.getGoodsClass(params, new OnSuccessAndFaultSub(listener, context,false));
    }
}
