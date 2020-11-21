package com.zlyc.www.mvp.user;

import com.zlyc.www.bean.user.TaskBean;

public interface TaskContract {

    interface Presenter {
        void getTask(String uid);
    }

    interface View {
        void getTaskSuccess(TaskBean data);
        void getTaskFailed(String msg);

    }
}
