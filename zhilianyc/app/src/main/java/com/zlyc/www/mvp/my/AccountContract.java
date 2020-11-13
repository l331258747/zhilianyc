package com.zlyc.www.mvp.my;

import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.login.InfoBean;

public interface AccountContract {
    interface Presenter {
        void info(String uid);
        void resetNickname(String uid,String nickname);
    }

    interface View {
        void infoSuccess(InfoBean data);
        void infoFailed(String msg);

        void resetNicknameSuccess(EmptyModel data);
        void resetNicknameFailed(String msg);
    }
}
