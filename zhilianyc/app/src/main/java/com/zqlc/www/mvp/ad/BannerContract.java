package com.zqlc.www.mvp.ad;

import com.zqlc.www.bean.ad.BannerBean;

import java.util.List;

public interface BannerContract {
    interface Presenter {
        void getBanner();
    }

    interface View {
        void getBannerSuccess(List<BannerBean> datas);
        void getBannerFailed(String msg);

    }
}
