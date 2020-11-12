package com.zlyc.www.mvp.login;

import com.zlyc.www.bean.login.LoginBean;

public interface LoginContract {

    interface Presenter {
        void login(String username,String password);
    }

    interface View {
        void loginSuccess(LoginBean data);
        void loginFailed(String msg);
    }
}
