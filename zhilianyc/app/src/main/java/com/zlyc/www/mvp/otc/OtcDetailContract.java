package com.zlyc.www.mvp.otc;

import com.zlyc.www.bean.otc.OtcDetailBean;

public interface OtcDetailContract {

    interface Presenter {
        void getOtcDetail(String uid,String beansSendId);
    }

    interface View {
        void getOtcDetailSuccess(OtcDetailBean data);
        void getOtcDetailFailed(String msg);

    }
}
