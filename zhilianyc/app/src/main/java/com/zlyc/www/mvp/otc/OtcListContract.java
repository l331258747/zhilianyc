package com.zlyc.www.mvp.otc;

import com.zlyc.www.bean.otc.OtcListBean;

import java.util.List;

public interface OtcListContract {

    interface Presenter {
        void getOtcList(String uid, int orderType, int priceSort, int numSort, int numType, int page);
    }

    interface View {

        void getOtcListSuccess(List<OtcListBean> datas);

        void getOtcListFailed(String msg);
    }
}
