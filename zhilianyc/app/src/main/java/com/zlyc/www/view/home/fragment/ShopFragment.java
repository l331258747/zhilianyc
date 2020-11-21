package com.zlyc.www.view.home.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.zlyc.www.R;
import com.zlyc.www.adapter.base.EndLessScrollOnScrollListener;
import com.zlyc.www.adapter.base.LoadMoreWrapper;
import com.zlyc.www.adapter.shop.HotGoodsAdapter;
import com.zlyc.www.base.BaseFragment;
import com.zlyc.www.bean.shop.HotGoodsBean;
import com.zlyc.www.constant.Constant;
import com.zlyc.www.mvp.shop.HotGoodsContract;
import com.zlyc.www.mvp.shop.HotGoodsPresenter;
import com.zlyc.www.view.shop.GoodsDetailsActivity;
import com.zlyc.www.view.shop.OrderListActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class ShopFragment extends BaseFragment implements HotGoodsContract.View, View.OnClickListener {


    View tab_electrical, tab_hot, tab_studio, tab_foot, tab_life;
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
        tab_electrical = $(R.id.tab_electrical);
        tab_hot = $(R.id.tab_hot);
        tab_studio = $(R.id.tab_studio);
        tab_foot = $(R.id.tab_foot);
        tab_life = $(R.id.tab_life);
        iv_floating = $(R.id.iv_floating);

        iv_floating.setOnClickListener(this);

        initSwipe();
        initRecycler();
    }

    @Override
    public void initData() {
        mPresenter = new HotGoodsPresenter(context, this);
        getRefreshData();
    }

    private void initSwipe() {
        swipe = $(R.id.swipe);
        swipe.setColorSchemeResources(R.color.color_1C81E9);
        swipe.setOnRefreshListener(() -> {
            getRefreshData();
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_floating:
                startActivity(new Intent(context, OrderListActivity.class));
                break;

        }
    }
}
