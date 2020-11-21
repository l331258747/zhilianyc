package com.zlyc.www.mvp.shop;

import com.zlyc.www.bean.shop.HotGoodsBean;

import java.util.List;

public interface HotGoodsContract {

    interface Presenter {
        void getHotGoods(int page);
    }

    interface View {
        void getHotGoodsSuccess(List<HotGoodsBean> data);
        void getHotGoodsFailed(String msg);

    }
}
