package com.zqlc.www.mvp.my;

public interface FeeContract {

    interface Presenter {
        void feeRatio(String uid);
    }

    interface View {
        void feeRatioSuccess(Float data);
        void feeRatioFailed(String msg);
    }
}
