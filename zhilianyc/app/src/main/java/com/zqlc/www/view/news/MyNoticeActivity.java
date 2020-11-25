package com.zqlc.www.view.news;

import android.content.Intent;

import com.zqlc.www.R;
import com.zqlc.www.adapter.base.EndlessRecyclerOnScrollListener;
import com.zqlc.www.adapter.base.LoadMoreWrapper;
import com.zqlc.www.adapter.news.MyNoticeAdapter;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.bean.news.AnnouncementBean;
import com.zqlc.www.constant.Constant;
import com.zqlc.www.mvp.news.MyNotifyContract;
import com.zqlc.www.mvp.news.MyNotifyPresenter;
import com.zqlc.www.view.web.WebTextActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MyNoticeActivity extends BaseActivity implements MyNotifyContract.View {

    SwipeRefreshLayout swipe;
    RecyclerView recyclerView;

    private MyNoticeAdapter mAdapter;

    MyNotifyPresenter mPresenter;

    List<AnnouncementBean> list;

    int page = Constant.DEFAULT_PAGE;
    LoadMoreWrapper loadMoreWrapper;
    int isLoadType = 1;//1下拉刷新，2上拉加载
    boolean isLoad = false;//是否在加载，重复加载问题

    @Override
    public int getLayoutId() {
        return R.layout.acitivty_my_notify;
    }

    @Override
    public void initView() {
        showLeftAndTitle("公告中心");
        initSwipeLayout();
        initRecycler();
    }

    @Override
    public void initData() {
        page = Constant.DEFAULT_PAGE;
        mPresenter = new MyNotifyPresenter(context,this);
        getRefreshData();
    }

    public void getRefreshData() {
        swipe.setRefreshing(true);
        page = Constant.DEFAULT_PAGE;
        isLoad = true;
        isLoadType = 1;
        mPresenter.getAnnouncement(page);
    }

    public void getMoreData() {
        isLoad = true;
        page = page + 1;
        isLoadType = 2;
        mPresenter.getAnnouncement(page);
    }

    //初始化recyclerview
    private void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new MyNoticeAdapter(activity, new ArrayList<>());
        loadMoreWrapper = new LoadMoreWrapper(mAdapter);
        recyclerView.setAdapter(loadMoreWrapper);

        mAdapter.setOnItemClickListener(position -> {
            AnnouncementBean item = list.get(position);
            intent = new Intent(context, WebTextActivity.class);
            intent.putExtra("web_text", item.getContent());
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
        swipe = $(R.id.swipe);
        swipe.setColorSchemeColors(getResColor(R.color.color_368feb));
        swipe.setOnRefreshListener(() -> {
            if (isLoad) return;
            getRefreshData();
        });
    }

    @Override
    public void getAnnouncementSuccess(List<AnnouncementBean> data) {
        if(data == null) data = new ArrayList<>();

        swipe.setRefreshing(false);

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
    public void getAnnouncementFailed(String msg) {
        showShortToast(msg);
        swipe.setRefreshing(false);
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
        isLoad = false;
    }
}
