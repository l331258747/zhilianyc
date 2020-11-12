package com.zlyc.www.mvp.my;

import com.zlyc.www.bean.login.MineBean;

public interface MyInfoContract {

    interface Presenter {
        void mine(String uid);
    }

    interface View {
        void mineSuccess(MineBean data);
        void mineFailed(String msg);
    }
}
