package com.zlyc.www.mvp.my;

public interface RealNameStatusContract {

    interface Presenter {
        void realNameStatus(String uid);
    }

    interface View {
        void realNameStatusSuccess(String data);
        void realNameStatusFailed(String msg);
    }
}
