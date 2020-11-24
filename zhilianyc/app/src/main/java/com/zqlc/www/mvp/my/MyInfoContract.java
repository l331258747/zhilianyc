package com.zqlc.www.mvp.my;

import com.zqlc.www.bean.login.MineBean;

public interface MyInfoContract {

    interface Presenter {
        void mine(String uid,boolean isShow);
    }

    interface View {
        void mineSuccess(MineBean data);
        void mineFailed(String msg);
    }
}
