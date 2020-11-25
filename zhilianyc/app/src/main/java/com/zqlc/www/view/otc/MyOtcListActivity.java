package com.zqlc.www.view.otc;

import android.content.Intent;

import com.zqlc.www.R;
import com.zqlc.www.adapter.base.EndlessRecyclerOnScrollListener;
import com.zqlc.www.adapter.base.LoadMoreWrapper;
import com.zqlc.www.adapter.otc.MyOtcListAdapter;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.bean.otc.MyOtcListBean;
import com.zqlc.www.constant.Constant;
import com.zqlc.www.mvp.otc.MyOtcListContract;
import com.zqlc.www.mvp.otc.MyOtcListPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MyOtcListActivity extends BaseActivity implements MyOtcListContract.View {

    private RecyclerView recyclerView;
    public SwipeRefreshLayout swipeRefreshLayout;
    private MyOtcListAdapter mAdapter;
    private MyOtcListPresenter mPresenter;
    List<MyOtcListBean> list;

    int page = Constant.DEFAULT_PAGE;
    LoadMoreWrapper loadMoreWrapper;
    int isLoadType = 1;//1下拉刷新，2上拉加载
    boolean isLoad = false;//是否在加载，重复加载问题

    @Override
    public int getLayoutId() {
        return R.layout.activity_transaction_list;
    }

    @Override
    public void initView() {
        showLeftAndTitle("交易明细");
        initSwipeLayout();
        initRecycler();
    }

    @Override
    public void initData() {
        page = Constant.DEFAULT_PAGE;
        mPresenter = new MyOtcListPresenter(context,this);
        getRefreshData();
    }

    public void getRefreshData() {
        swipeRefreshLayout.setRefreshing(true);
        page = Constant.DEFAULT_PAGE;
        isLoad = true;
        isLoadType = 1;
        mPresenter.getMyOtcList(MySelfInfo.getInstance().getUserId(),page);
    }

    public void getMoreData() {
        isLoad = true;
        page = page + 1;
        isLoadType = 2;
        mPresenter.getMyOtcList(MySelfInfo.getInstance().getUserId(),page);
    }

    //初始化recyclerview
    private void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new MyOtcListAdapter(activity, new ArrayList<>());
        loadMoreWrapper = new LoadMoreWrapper(mAdapter);
        recyclerView.setAdapter(loadMoreWrapper);

        mAdapter.setOnItemClickListener(position -> {
            MyOtcListBean item = list.get(position);

            intent = new Intent(context,OtcDetailActivity.class);
            intent.putExtra("orderType",item.getOrderType());
            intent.putExtra("beansSendId",item.getId());
            startActivity(intent);
        });

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (isLoad || loadMoreWrapper.getLoadState() == LoadMoreWrapper.LOADING_END) return;
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
                getMoreData();
            }
        });
    }

    //初始化SwipeLayout
    public void initSwipeLayout() {
        swipeRefreshLayout = $(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(getResColor(R.color.color_368feb));
        swipeRefreshLayout.setOnRefreshListener(() -> {
            if (isLoad) return;
            getRefreshData();
        });
    }

    @Override
    public void getMyOtcListSuccess(List<MyOtcListBean> data) {
        if(data == null) data = new ArrayList<>();

        swipeRefreshLayout.setRefreshing(false);

        if (isLoadType == 1) {
            mAdapter.setData(data);
        } else {
            mAdapter.addData(data);
        }

        this.list = mAdapter.getData();

        isLoad = false;

        if (data.size() >= Constant.DEFAULT_SIZE) {
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
        } else {
            // 显示加载到底的提示
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
        }
    }

    @Override
    public void getMyOtcListFailed(String msg) {
        showShortToast(msg);
        swipeRefreshLayout.setRefreshing(false);
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
        isLoad = false;
    }
}
