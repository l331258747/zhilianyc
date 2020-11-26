package com.zqlc.www.mvp.ad;

import com.zqlc.www.bean.ad.ConfigBean;

public interface ConfigContract {
    interface Presenter {
        void getAdConfig();
    }

    interface View {
        void getAdConfigSuccess(ConfigBean data);
        void getAdConfigFailed(String msg);

    }
}
