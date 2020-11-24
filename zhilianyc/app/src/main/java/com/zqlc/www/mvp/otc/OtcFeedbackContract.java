package com.zqlc.www.mvp.otc;

import com.zqlc.www.bean.EmptyModel;

import java.io.File;

public interface OtcFeedbackContract {

    interface Presenter {
        void otcFeedback(String uid,File file);
        void getOtcHandle(String uid, int sendStatus, String beansSendId,String complainContent,String complainImg,String complainMobile);
    }

    interface View {
        void otcFeedbackSuccess(String data);
        void otcFeedbackFailed(String msg);

        void getOtcHandleSuccess(EmptyModel data);
        void getOtcHandleFailed(String msg);

    }
}
