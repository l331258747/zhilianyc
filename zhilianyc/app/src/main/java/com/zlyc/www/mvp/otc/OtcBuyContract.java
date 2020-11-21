package com.zlyc.www.mvp.otc;

import com.zlyc.www.bean.EmptyModel;

public interface OtcBuyContract {

    interface Presenter {
        void sendOtcBuy(String uid, float unitPrice, int count, String payPassword);
    }

    interface View {
        void sendOtcBuySuccess(EmptyModel data);
        void sendOtcBuyFailed(String msg);

    }
}
