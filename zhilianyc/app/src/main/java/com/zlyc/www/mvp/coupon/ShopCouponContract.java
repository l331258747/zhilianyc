package com.zlyc.www.mvp.coupon;

import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.coupon.ShopCouponBean;

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
