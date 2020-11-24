package com.zqlc.www.mvp.shop;

import com.zqlc.www.bean.shop.GoodsListBean;

import java.util.List;

public interface GoodsListContract {

    interface Presenter {
        void getGoodsList(String categoryId, int page);
    }

    interface View {
        void getGoodsListSuccess(List<GoodsListBean> data);
        void getGoodsListFailed(String msg);

    }
}
