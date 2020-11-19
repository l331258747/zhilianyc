package com.zlyc.www.view.shop;

import com.zlyc.www.R;
import com.zlyc.www.adapter.base.BaseFragmentAdapter;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.widget.myTabLayout.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class OrderListActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> mFragments;
    private String[] titles = {"全部" ,"待付款","待发货","待收货"};

    OrderListFragment fOrderAll;
    OrderListFragment fOrderPayment;
    OrderListFragment fOrderDeliver;
    OrderListFragment fOrderReceiving;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_list;
    }

    @Override
    public void initView() {

        showLeftAndTitle("我的订单");

        mTabLayout = $(R.id.tabs);
        mViewPager = $(R.id.viewpager);
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        fOrderAll = (OrderListFragment) OrderListFragment.newInstance(OrderListFragment.ORDER_ALL);
        fOrderPayment = (OrderListFragment) OrderListFragment.newInstance(OrderListFragment.ORDER_PAYMENT);
        fOrderDeliver = (OrderListFragment) OrderListFragment.newInstance(OrderListFragment.ORDER_DELIVER);
        fOrderReceiving = (OrderListFragment) OrderListFragment.newInstance(OrderListFragment.ORDER_RECEIVING);
        mFragments.add(fOrderAll);
        mFragments.add(fOrderPayment);
        mFragments.add(fOrderDeliver);
        mFragments.add(fOrderReceiving);

        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, titles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
