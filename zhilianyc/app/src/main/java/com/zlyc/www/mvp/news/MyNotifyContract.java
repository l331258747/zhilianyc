package com.zlyc.www.mvp.news;

import com.zlyc.www.bean.news.AnnouncementBean;

import java.util.List;

public interface MyNotifyContract {

    interface Presenter {
        void getAnnouncement(int page);
    }

    interface View {
        void getAnnouncementSuccess(List<AnnouncementBean> data);
        void getAnnouncementFailed(String msg);
    }
}
