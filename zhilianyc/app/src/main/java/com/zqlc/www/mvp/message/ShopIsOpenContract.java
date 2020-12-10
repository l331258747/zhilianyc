package com.zqlc.www.mvp.message;

public interface ShopIsOpenContract {

    interface Presenter {
        void shopIsOpen();
    }

    interface View {
        void shopIsOpenSuccess(String data);
        void shopIsOpenFailed(String msg);
    }
}
