package com.zlyc.www.mvp.account;

import com.zlyc.www.bean.EmptyModel;

public interface PayPwdContract {
    interface Presenter {
        void payPwd(String payPassword,String vcode);
    }

    interface View {
        void payPwdSuccess(EmptyModel data);
        void payPwdFailed(String msg);
    }

}
