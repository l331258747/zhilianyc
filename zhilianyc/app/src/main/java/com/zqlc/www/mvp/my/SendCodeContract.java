package com.zqlc.www.mvp.my;

import com.zqlc.www.bean.EmptyModel;

public interface SendCodeContract {

    interface Presenter {
        void sendCode(String mobile);
    }

    interface View {
        void sendCodeSuccess(EmptyModel data);
        void sendCodeFailed(String msg);
    }
}
