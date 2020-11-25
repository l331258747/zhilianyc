package com.zqlc.www.mvp.user;

import com.zqlc.www.bean.user.ListReginBean;

import java.util.List;

public interface ListRegionContract {

    interface Presenter {
        void listRegion(String pCode);
    }

    interface View {
        void listRegionSuccess(List<ListReginBean> data);
        void listRegionFailed(String msg);


    }
}
