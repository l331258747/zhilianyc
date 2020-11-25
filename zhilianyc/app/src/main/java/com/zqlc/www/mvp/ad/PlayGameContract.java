package com.zqlc.www.mvp.ad;

import com.zqlc.www.bean.EmptyModel;

public interface PlayGameContract {
    interface Presenter {
        void playGameCallback();
    }

    interface View {
        void playGameCallbackSuccess(EmptyModel datas);
        void playGameCallbackFailed(String msg);

    }
}
