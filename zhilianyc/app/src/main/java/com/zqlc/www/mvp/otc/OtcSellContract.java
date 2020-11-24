package com.zqlc.www.mvp.otc;

import com.zqlc.www.bean.EmptyModel;

public interface OtcSellContract {

    interface Presenter {
        void sendOtcSell(String uid, float unitPrice, int count, String payPassword,String vcode);
    }

    interface View {
        void sendOtcSellSuccess(EmptyModel data);
        void sendOtcSellFailed(String msg);

    }
}
