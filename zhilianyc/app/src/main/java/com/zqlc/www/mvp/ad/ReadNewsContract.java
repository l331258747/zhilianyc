package com.zqlc.www.mvp.ad;

import com.zqlc.www.bean.EmptyModel;

public interface ReadNewsContract {
    interface Presenter {
        void readNewsCallback();
    }

    interface View {
        void readNewsCallbackSuccess(EmptyModel datas);
        void readNewsCallbackFailed(String msg);

    }
}
