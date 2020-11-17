package com.zlyc.www.mvp.shop;

import com.zlyc.www.bean.shop.GoodsDetailsBean;

public interface GoodsDetailsContract {

    interface Presenter {
        void getGoodsDetails(String uid,String goodsId);
    }

    interface View {
        void getGoodsDetailsSuccess(GoodsDetailsBean data);
        void getGoodsDetailsFailed(String msg);

    }
}
