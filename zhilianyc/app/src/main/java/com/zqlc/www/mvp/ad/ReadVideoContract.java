package com.zqlc.www.mvp.ad;

import com.zqlc.www.bean.EmptyModel;

public interface ReadVideoContract {
    interface Presenter {
        void viewVideoCallback();
    }

    interface View {
        void viewVideoCallbackSuccess(EmptyModel datas);
        void viewVideoCallbackFailed(String msg);

    }
}
