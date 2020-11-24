package com.zqlc.www.mvp.my;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.login.InfoBean;

import java.io.File;

public interface AccountContract {
    interface Presenter {
        void info(String uid);
        void resetNickname(String uid,String nickname);
        void resetHead(String uid, File headImg);
    }

    interface View {
        void infoSuccess(InfoBean data);
        void infoFailed(String msg);

        void resetNicknameSuccess(EmptyModel data);
        void resetNicknameFailed(String msg);

        void resetHeadSuccess(String data);
        void resetHeadFailed(String msg);
    }
}
