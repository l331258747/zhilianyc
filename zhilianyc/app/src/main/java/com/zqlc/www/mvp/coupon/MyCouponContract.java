package com.zqlc.www.mvp.coupon;

import com.zqlc.www.bean.coupon.MyCouponBean;

public interface MyCouponContract {
    interface Presenter {
        void getMyCoupon(String uid);
    }

    interface View {
        void getMyCouponSuccess(MyCouponBean data);
        void getMyCouponFailed(String msg);

    }
}
