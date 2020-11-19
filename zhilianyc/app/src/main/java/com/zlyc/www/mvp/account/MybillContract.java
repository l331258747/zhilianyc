package com.zlyc.www.mvp.account;

import com.zlyc.www.bean.account.MyBillBean;

public interface MybillContract {
    interface Presenter {
        void getBill(String uid, int type);
    }

    interface View {
        void getBillSuccess(MyBillBean data);
        void getBillFailed(String msg);
    }

}
