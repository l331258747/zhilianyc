package com.zqlc.www.mvp.account;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.account.UserAccountBean;

public interface UserAccountContract {
    interface Presenter {
        void getUserAccount(String uid);
        void updateAccountNo(String uid,String account);
    }

    interface View {
        void getUserAccountSuccess(UserAccountBean data);
        void getUserAccountFailed(String msg);

        void updateAccountNoSuccess(EmptyModel data);
        void updateAccountNoFailed(String msg);
    }

}
