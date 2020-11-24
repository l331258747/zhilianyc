package com.zqlc.www.view.account;

import com.zqlc.www.R;
import com.zqlc.www.adapter.base.BaseFragmentAdapter;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.widget.myTabLayout.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MyBillActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> mFragments;
    private String[] titles = {"京豆" ,"贡献","劳动力","可售额度"};

    MyBillFragment fBillJindou;
    MyBillFragment fBillContribution;
    MyBillFragment fBillLabour;
    MyBillFragment fBillVendibility;

    @Override
    public int getLayoutId() {
        return R.layout.acitivty_my_bill;
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
        fBillJindou = (MyBillFragment) MyBillFragment.newInstance(MyBillFragment.BILL_JINDOU);
        fBillContribution = (MyBillFragment) MyBillFragment.newInstance(MyBillFragment.BILL_CONTRIBUTION);
        fBillLabour = (MyBillFragment) MyBillFragment.newInstance(MyBillFragment.BILL_LABOUR);
        fBillVendibility = (MyBillFragment) MyBillFragment.newInstance(MyBillFragment.BILL_VENDIBILITY);
        mFragments.add(fBillJindou);
        mFragments.add(fBillContribution);
        mFragments.add(fBillLabour);
        mFragments.add(fBillVendibility);

        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, titles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
