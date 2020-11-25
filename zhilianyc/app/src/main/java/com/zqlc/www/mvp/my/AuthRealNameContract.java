package com.zqlc.www.mvp.my;

import com.zqlc.www.bean.user.AuthRealInfoBean;
import com.zqlc.www.bean.user.AuthRealNameBean;
import com.zqlc.www.bean.user.AuthRealPayBean;

public interface AuthRealNameContract {

    interface Presenter {
        void authRealName(String uid, String name, String idCard, String cityCode);
        void authRealPay(String uid, String name, String idCard, String cityCode);
        void realNameInfo(String uid);
    }

    interface View {
        void authRealNameSuccess(AuthRealNameBean data);
        void authRealNameFailed(String msg);

        void authRealPaySuccess(AuthRealPayBean data);
        void authRealPayFailed(String msg);

        void realNameInfoSuccess(AuthRealInfoBean data);
        void realNameInfoFailed(String msg);
    }
}
