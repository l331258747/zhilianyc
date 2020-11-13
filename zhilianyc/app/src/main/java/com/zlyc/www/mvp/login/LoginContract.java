package com.zlyc.www.mvp.login;

import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.login.LoginBean;

public interface LoginContract {

    interface Presenter {
        void login(String username,String password);
        void register(String mobile,String code,String password,String vcode);
    }

    interface View {
        void loginSuccess(LoginBean data);
        void loginFailed(String msg);

        void registerSuccess(EmptyModel data);
        void registerFailed(String msg);
    }
}
