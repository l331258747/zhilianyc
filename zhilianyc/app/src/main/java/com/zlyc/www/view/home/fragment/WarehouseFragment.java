package com.zlyc.www.view.home.fragment;

import com.zlyc.www.R;
import com.zlyc.www.adapter.base.BaseFragmentAdapter;
import com.zlyc.www.base.BaseFragment;
import com.zlyc.www.view.coupon.MyCouponFragment;
import com.zlyc.www.view.coupon.ShopCouponFragment;
import com.zlyc.www.widget.myTabLayout.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class WarehouseFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> mFragments;
    private String[] titles = {"我的卷轴" ,"卷轴商城"};

    MyCouponFragment fMyCoupon;
    ShopCouponFragment fShopCoupon;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_warehouse;
    }

    @Override
    public void initView() {
        mTabLayout = $(R.id.tabs);
        mViewPager = $(R.id.viewpager);

    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        fMyCoupon = (MyCouponFragment) MyCouponFragment.newInstance();
        fShopCoupon = (ShopCouponFragment) ShopCouponFragment.newInstance();
        mFragments.add(fMyCoupon);
        mFragments.add(fShopCoupon);

        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getChildFragmentManager(), mFragments, titles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(1);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
