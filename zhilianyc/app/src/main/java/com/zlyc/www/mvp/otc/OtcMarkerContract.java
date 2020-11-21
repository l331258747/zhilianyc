package com.zlyc.www.mvp.otc;

import com.zlyc.www.bean.otc.OtcInfoBean;
import com.zlyc.www.bean.otc.OtcListBean;

import java.util.List;

public interface OtcMarkerContract {

    interface Presenter {
        void getOtcInfo();
        void getOtcList(String uid, int orderType, int priceSort, int numSort, int numType, int page);
        void getOtcOpen();
    }

    interface View {
        void getOtcInfoSuccess(OtcInfoBean data);
        void getOtcInfoFailed(String msg);

        void getOtcOpenSuccess(String data);
        void getOtcOpenFailed(String msg);

        void getOtcListSuccess(List<OtcListBean> datas);
        void getOtcListFailed(String msg);

    }
}
