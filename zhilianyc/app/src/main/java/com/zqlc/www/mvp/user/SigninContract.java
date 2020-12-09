package com.zqlc.www.mvp.user;

public interface SigninContract {

    interface Presenter {
        void signin(String uid);
    }

    interface View {
        void signinSuccess(String data);
        void signinFailed(String msg);

    }
}
