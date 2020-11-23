package com.zlyc.www.view.home.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.zlyc.www.R;
import com.zlyc.www.adapter.base.EndLessScrollOnScrollListener;
import com.zlyc.www.adapter.base.LoadMoreWrapper;
import com.zlyc.www.adapter.shop.GoodsClassAdapter;
import com.zlyc.www.adapter.shop.HotGoodsAdapter;
import com.zlyc.www.base.BaseFragment;
import com.zlyc.www.bean.shop.GoodsClassBean;
import com.zlyc.www.bean.shop.HotGoodsBean;
import com.zlyc.www.constant.Constant;
import com.zlyc.www.mvp.shop.HotGoodsContract;
import com.zlyc.www.mvp.shop.HotGoodsPresenter;
import com.zlyc.www.view.shop.GoodsDetailsActivity;
import com.zlyc.www.view.shop.GoodsListActivity;
import com.zlyc.www.view.shop.OrderListActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class ShopFragment extends BaseFragment implements HotGoodsContract.View, View.OnClickListener {

    RecyclerView view_tab;
    GoodsClassAdapter mAdapterH;
    List<GoodsClassBean> datasH;

    RecyclerView recyclerView;
    ImageView iv_floating;
    NestedScrollView scrollView;

    HotGoodsAdapter mAdapter;
    List<HotGoodsBean> datas;

    HotGoodsPresenter mPresenter;

    SwipeRefreshLayout swipe;

    int page;
    LoadMoreWrapper loadMoreWrapper;
    int isLoadType = 1;//1下拉刷新，2上拉加载
    boolean isLoad = false;//是否在加载，重复加载问题

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    public void initView() {
        scrollView = $(R.id.scrollView);
        view_tab = $(R.id.view_tab);
        iv_floating = $(R.id.iv_floating);

        iv_floating.setOnClickListener(this);

        initSwipe();
        initRecycler();

        initRecyclerH();
    }

    @Override
    public void initData() {
        mPresenter = new HotGoodsPresenter(context, this);
        getRefreshData();

        mPresenter.getGoodsClass();
    }

    private void initSwipe() {
        swipe = $(R.id.swipe);
        swipe.setColorSchemeResources(R.color.color_1C81E9);
        swipe.setOnRefreshListener(() -> {
            getRefreshData();
        });
    }

    private void initRecyclerH() {
        view_tab = $(R.id.view_tab);

        view_tab = $(R.id.view_tab);
        view_tab.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        mAdapterH = new GoodsClassAdapter(activity, new ArrayList<>());
        view_tab.setAdapter(mAdapterH);
        view_tab.setNestedScrollingEnabled(false);
        mAdapterH.setOnItemClickListener(position -> {
            //TODO 进入商品列表，分类选择
            Intent intent = new Intent(context, GoodsListActivity.class);
            intent.putExtra("categoryId", datasH.get(position).getId());
            intent.putExtra("goodsClass", (Serializable) datasH);
            intent.putExtra("categoryName", datasH.get(position).getName());
            startActivity(intent);
        });

    }


    //初始化recyclerview
    public void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new HotGoodsAdapter(context, new ArrayList<>());
        loadMoreWrapper = new LoadMoreWrapper(mAdapter);
        recyclerView.setAdapter(loadMoreWrapper);
        recyclerView.setNestedScrollingEnabled(false);

        mAdapter.setOnItemClickListener(position -> {
            HotGoodsBean item = datas.get(position);

            Intent intent = new Intent(context, GoodsDetailsActivity.class);
            intent.putExtra("id", item.getGoodsId());
            startActivity(intent);
        });

        scrollView.setOnScrollChangeListener(new EndLessScrollOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (isLoad || loadMoreWrapper.getLoadState() == LoadMoreWrapper.LOADING_END) return;
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
                getMoreData();
            }

            @Override
            public void onScrollChange(int scrollY) {

            }
        });
    }

    public void getRefreshData() {
        swipe.setRefreshing(true);
        page = Constant.DEFAULT_PAGE;
        isLoad = true;
        isLoadType = 1;
        mPresenter.getHotGoods(page);
    }

    public void getMoreData() {
        isLoad = true;
        page = page + 1;
        isLoadType = 2;
        mPresenter.getHotGoods(page);
    }

    @Override
    public void getHotGoodsSuccess(List<HotGoodsBean> data) {
        if (data == null) data = new ArrayList<>();
        this.datas = data;
        swipe.setRefreshing(false);

        if (isLoadType == 1) {
            mAdapter.setData(datas);
        } else {
            mAdapter.addData(datas);
        }
        isLoad = false;

        if (datas.size() >= Constant.DEFAULT_SIZE) {
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
        } else {
            // 显示加载到底的提示
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
        }
    }

    @Override
    public void getHotGoodsFailed(String msg) {
        showShortToast(msg);
        isLoad = false;
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
        swipe.setRefreshing(false);
    }

    @Override
    public void getGoodsClassSuccess(List<GoodsClassBean> data) {
        if(data == null) data = new ArrayList<>();
        datasH = data;

        mAdapterH.setData(datasH);
    }

    @Override
    public void getGoodsClassFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_floating:
                startActivity(new Intent(context, OrderListActivity.class));
                break;

        }
    }
}
