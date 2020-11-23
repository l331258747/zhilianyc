package com.zlyc.www.mvp.otc;

import com.zlyc.www.bean.EmptyModel;

public interface OtcFeedbackContract {

    interface Presenter {
        void otcFeedback(String file, String uid);
    }

    interface View {
        void otcFeedbackSuccess(EmptyModel data);
        void otcFeedbackFailed(String msg);

    }
}
