package com.zqlc.www.mvp.otc;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.otc.OtcDetailBean;

import java.io.File;

public interface OtcDetailContract {

    interface Presenter {
        void getOtcDetail(String uid,String beansSendId);
        void getOtcVoucher(String uid, String beansSendId, File file);
        void getOtcCheck(String uid,String beansSendId);
        void getOtcHandle(String uid,int sendStatus,String beansSendId);
        void getOtcHandleSubmit(String uid,int sendStatus,String beansSendId,String vcode,String payPassword);
    }

    interface View {
        void getOtcDetailSuccess(OtcDetailBean data);
        void getOtcDetailFailed(String msg);
        void getOtcVoucherSuccess(EmptyModel data);
        void getOtcVoucherFailed(String msg);

        void getOtcCheckSuccess(EmptyModel data);
        void getOtcCheckFailed(String msg);
        void getOtcHandleSuccess(EmptyModel data);
        void getOtcHandleFailed(String msg);

    }
}
