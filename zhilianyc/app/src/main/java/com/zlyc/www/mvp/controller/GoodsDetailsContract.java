package com.zlyc.www.mvp.controller;

import com.zlyc.www.bean.controller.GoodsDetailsBean;

public interface GoodsDetailsContract {

    interface Presenter {
        void getGoodsDetails(String uid,String goodsId);
    }

    interface View {
        void getGoodsDetailsSuccess(GoodsDetailsBean data);
        void getGoodsDetailsFailed(String msg);

    }
}
