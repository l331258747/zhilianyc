package com.zlyc.www.mvp.otc;

import com.zlyc.www.bean.otc.OtcInfoBean;

public interface OtcMarkerContract {

    interface Presenter {
        void getOtcInfo();
        void getOtcOpen();
    }

    interface View {
        void getOtcInfoSuccess(OtcInfoBean data);
        void getOtcInfoFailed(String msg);

        void getOtcOpenSuccess(String data);
        void getOtcOpenFailed(String msg);

    }
}
