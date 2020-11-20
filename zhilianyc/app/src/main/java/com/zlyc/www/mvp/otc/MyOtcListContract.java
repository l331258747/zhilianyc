package com.zlyc.www.mvp.otc;

import com.zlyc.www.bean.otc.MyOtcListBean;

import java.util.List;

public interface MyOtcListContract {

    interface Presenter {
        void getMyOtcList(String uid,int page);
    }

    interface View {
        void getMyOtcListSuccess(List<MyOtcListBean> data);
        void getMyOtcListFailed(String msg);

    }
}
