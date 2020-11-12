package com.zlyc.www.mvp.login;

import com.zlyc.www.bean.EmptyModel;

public interface LoginContract {

    interface Presenter {
        void login(String username,String password);
    }

    interface View {
        void loginSuccess(EmptyModel data);
        void loginFailed(String msg);
    }
}
