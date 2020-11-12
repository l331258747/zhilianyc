package com.zlyc.www.mvp.login;

import com.zlyc.www.bean.EmptyModel;

public interface ResetPwdContract {

    interface Presenter {
        void resetPwd(String oldPassword, String newPassword);
    }

    interface View {
        void resetPwdSuccess(EmptyModel data);
        void resetPwdFailed(String msg);
    }
}
