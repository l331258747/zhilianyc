package com.zqlc.www.mvp.ad;

import com.zqlc.www.bean.EmptyModel;

public interface AwardContract {
    interface Presenter {
        void awardCallback();
    }

    interface View {
        void awardCallbackSuccess(EmptyModel datas);
        void awardCallbackFailed(String msg);

    }
}
