package com.zlyc.www.view.home.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.zlyc.www.R;
import com.zlyc.www.adapter.controller.HotGoodsAdapter;
import com.zlyc.www.base.BaseFragment;
import com.zlyc.www.bean.controller.HotGoodsBean;
import com.zlyc.www.mvp.controller.HotGoodsContract;
import com.zlyc.www.mvp.controller.HotGoodsPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShopFragment extends BaseFragment implements HotGoodsContract.View {


    View tab_electrical,tab_hot,tab_studio,tab_foot,tab_life;
    ScrollView scrollView;
    RecyclerView recyclerView;
    ImageView iv_floating;

    HotGoodsAdapter mAdapter;
    List<HotGoodsBean> datas;

    HotGoodsPresenter mPresenter;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    public void initView() {
        tab_electrical = $(R.id.tab_electrical);
        tab_hot = $(R.id.tab_hot);
        tab_studio = $(R.id.tab_studio);
        tab_foot = $(R.id.tab_foot);
        tab_life = $(R.id.tab_life);
        iv_floating = $(R.id.iv_floating);

        initRecycler();
    }

    @Override
    public void initData() {
        mPresenter = new HotGoodsPresenter(context,this);
        mPresenter.getHotGoods();
    }

    //初始化recyclerview
    public void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new HotGoodsAdapter(context, new ArrayList<HotGoodsBean>());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);

//        mAdapter.setOnItemClickListener(new HotGoodsAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(int position) {
//                HotGoodsBean item = datas.get(position);
//
//            }
//        });
    }

    @Override
    public void getHotGoodsSuccess(List<HotGoodsBean> data) {
        mAdapter.setData(data);
    }

    @Override
    public void getHotGoodsFailed(String msg) {
        showShortToast(msg);
    }
}
