package com.zqlc.www.mvp.login;

import com.zqlc.www.bean.EmptyModel;

public interface ForgetPwdContract {

    interface Presenter {
        void forgetPwd(String mobile, String vcode,String password);
    }

    interface View {
        void forgetPwdSuccess(EmptyModel data);
        void forgetPwdFailed(String msg);
    }
}
