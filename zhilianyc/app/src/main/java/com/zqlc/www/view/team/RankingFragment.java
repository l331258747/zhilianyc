package com.zqlc.www.view.team;

import android.os.Bundle;

import com.zqlc.www.R;
import com.zqlc.www.adapter.team.RankingAdapter;
import com.zqlc.www.base.BaseFragment;
import com.zqlc.www.bean.team.RankingBean;
import com.zqlc.www.mvp.team.RankingContract;
import com.zqlc.www.mvp.team.RankingPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RankingFragment extends BaseFragment implements RankingContract.View {

    public static final int RANKING_DAY = 0;
    public static final int RANKING_WEEK = 1;

    private int rankingType;

    private RecyclerView recyclerView;
    private RankingAdapter mAdapter;
    private RankingPresenter mPresenter;

    public static Fragment newInstance(int rankingType) {
        RankingFragment fragment = new RankingFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("rankingType", rankingType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            rankingType = bundle.getInt("rankingType");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ranking;
    }

    @Override
    public void initView() {
        initRecycler();
    }

    @Override
    public void initData() {
        mPresenter = new RankingPresenter(context,this);
        mPresenter.inviteRanking(rankingType);
    }

    //初始化recyclerview
    private void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new RankingAdapter(activity, new ArrayList<>());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void inviteRankingSuccess(List<RankingBean> data) {
        if(data == null) data = new ArrayList<>();
        mAdapter.setData(data);
    }

    @Override
    public void inviteRankingFailed(String msg) {
        showLongToast(msg);
    }
}
