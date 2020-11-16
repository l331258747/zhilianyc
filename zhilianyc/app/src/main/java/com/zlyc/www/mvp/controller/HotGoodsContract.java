package com.zlyc.www.mvp.controller;

import com.zlyc.www.bean.controller.HotGoodsBean;

import java.util.List;

public interface HotGoodsContract {

    interface Presenter {
        void getHotGoods();
    }

    interface View {
        void getHotGoodsSuccess(List<HotGoodsBean> data);
        void getHotGoodsFailed(String msg);

    }
}
