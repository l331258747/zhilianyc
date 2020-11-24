package com.zlyc.www.mvp.news;

import com.zlyc.www.bean.news.StudyCentreBean;

import java.util.List;

public interface MyStudyContract {

    interface Presenter {
        void getStudyCentre(int page);
    }

    interface View {
        void getStudyCentreSuccess(List<StudyCentreBean> data);
        void getStudyCentreFailed(String msg);
    }
}
