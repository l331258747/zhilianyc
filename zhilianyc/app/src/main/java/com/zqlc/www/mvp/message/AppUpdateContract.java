package com.zqlc.www.mvp.message;

import com.zqlc.www.bean.message.AppUpdateBean;

public interface AppUpdateContract {

    interface Presenter {
        void getAppUpdate();
    }

    interface View {
        void getAppUpdateSuccess(AppUpdateBean data);
        void getAppUpdateFailed(String msg);
    }
}
