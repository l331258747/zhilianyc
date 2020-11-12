package com.zlyc.www.mvp.my;

import com.zlyc.www.bean.login.InfoBean;

public interface AccountContract {
    interface Presenter {
        void info(String uid);
    }

    interface View {
        void infoSuccess(InfoBean data);
        void infoFailed(String msg);
    }
}
