package com.zqlc.www.mvp.my;

import com.zqlc.www.bean.EmptyModel;

public interface AuthRealNameContract {

    interface Presenter {
        void authRealName(String uid, String name, String idCard, String cityCode);
    }

    interface View {
        void authRealNameSuccess(EmptyModel data);
        void authRealNameFailed(String msg);
    }
}
