package com.zlyc.www.view.team;

import com.zlyc.www.R;
import com.zlyc.www.adapter.base.BaseFragmentAdapter;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.widget.myTabLayout.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class InvitationRankingActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> mFragments;
    private String[] titles = {"日排行榜" ,"周排行榜"};

    RankingFragment fRankingDay;
    RankingFragment fRankingWeek;


    @Override
    public int getLayoutId() {
        return R.layout.activity_invitation_rank;
    }

    @Override
    public void initView() {

        showLeftAndTitle("邀请排行榜");
        mTabLayout = $(R.id.tabs);
        mViewPager = $(R.id.viewpager);

    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        fRankingDay = (RankingFragment) RankingFragment.newInstance(RankingFragment.RANKING_DAY);
        fRankingWeek = (RankingFragment) RankingFragment.newInstance(RankingFragment.RANKING_WEEK);
        mFragments.add(fRankingDay);
        mFragments.add(fRankingWeek);

        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, titles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(1);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
