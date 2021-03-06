package com.zqlc.www.mvp.login;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.login.VerifyImageBean;

public interface VerifyImageContract {

    interface Presenter {
        void verifyImageConfirm(String mobile, int x, int y);

        void verifyImage(String mobile);
    }

    interface View {
        void verifyImageConfirmSuccess(EmptyModel data);

        void verifyImageConfirmFailed(String msg);

        void verifyImageSuccess(VerifyImageBean data);

        void verifyImageFailed(String msg);
    }
}
