package com.zlyc.www.mvp.login;

import com.zlyc.www.bean.EmptyModel;

public interface ForgetPwdContract {

    interface Presenter {
        void forgetPwd(String mobile, String vcode,String password);
    }

    interface View {
        void forgetPwdSuccess(EmptyModel data);
        void forgetPwdFailed(String msg);
    }
}
