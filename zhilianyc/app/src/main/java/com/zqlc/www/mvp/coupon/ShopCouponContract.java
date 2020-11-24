package com.zqlc.www.mvp.coupon;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.coupon.ShopCouponBean;

import java.util.List;

public interface ShopCouponContract {
    interface Presenter {
        void getShopCoupon(String uid);
        void buyShopCoupon(String uid,String type);
    }

    interface View {
        void getShopCouponSuccess(List<ShopCouponBean> data);
        void getShopCouponFailed(String msg);

        void buyShopCouponSuccess(EmptyModel data);
        void buyShopCouponFailed(String msg);

    }
}
