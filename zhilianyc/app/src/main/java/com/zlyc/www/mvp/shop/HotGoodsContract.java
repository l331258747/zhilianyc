package com.zlyc.www.mvp.shop;

import com.zlyc.www.bean.shop.GoodsClassBean;
import com.zlyc.www.bean.shop.HotGoodsBean;

import java.util.List;

public interface HotGoodsContract {

    interface Presenter {
        void getHotGoods(int page);
        void getGoodsClass();
    }

    interface View {
        void getHotGoodsSuccess(List<HotGoodsBean> data);
        void getHotGoodsFailed(String msg);

        void getGoodsClassSuccess(List<GoodsClassBean> data);
        void getGoodsClassFailed(String msg);

    }
}
