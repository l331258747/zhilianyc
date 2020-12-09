package com.zqlc.www.mvp.user;

import com.zqlc.www.bean.user.TaskBean;

public interface TaskContract {

    interface Presenter {
        void getTask(String uid);
    }

    interface View {
        void getTaskSuccess(TaskBean data);
        void getTaskFailed(String msg);
    }
}
