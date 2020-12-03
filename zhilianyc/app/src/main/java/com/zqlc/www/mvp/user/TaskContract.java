package com.zqlc.www.mvp.user;

import com.zqlc.www.bean.user.TaskBean;

public interface TaskContract {

    interface Presenter {
        void getTask(String uid);
        void signin(String uid);
    }

    interface View {
        void getTaskSuccess(TaskBean data);
        void getTaskFailed(String msg);

        void signinSuccess(String data);
        void signinFailed(String msg);

    }
}
