package com.zqlc.www.mvp.account;

import com.zqlc.www.bean.account.MyBillListBean;

import java.util.List;

public interface MybillContract {
    interface Presenter {
        void getBill(String uid, int type,int page);
    }

    interface View {
        void getBillSuccess(List<MyBillListBean> data);
        void getBillFailed(String msg);
    }

}
