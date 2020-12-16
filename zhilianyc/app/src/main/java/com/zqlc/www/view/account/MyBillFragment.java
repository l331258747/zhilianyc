package com.zqlc.www.view.account;

import android.os.Bundle;

import com.zqlc.www.R;
import com.zqlc.www.adapter.account.BillListAdapter;
import com.zqlc.www.adapter.base.EndlessRecyclerOnScrollListener;
import com.zqlc.www.adapter.base.LoadMoreWrapper;
import com.zqlc.www.base.BaseFragment;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.bean.account.MyBillListBean;
import com.zqlc.www.constant.Constant;
import com.zqlc.www.mvp.account.MyBillPresenter;
import com.zqlc.www.mvp.account.MybillContract;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MyBillFragment extends BaseFragment implements MybillContract.View {

    public static final int BILL_JINDOU = 0;
    public static final int BILL_CONTRIBUTION = 1;//contribution
    public static final int BILL_LABOUR = 2;//labour
    public static final int BILL_VENDIBILITY = 3;//vendibility

    private int billType;

    SwipeRefreshLayout swipe;
    private RecyclerView recyclerView;
    private BillListAdapter mAdapter;
    private MyBillPresenter mPresenter;

    private boolean isViewCreated;

    int page = Constant.DEFAULT_PAGE;
    LoadMoreWrapper loadMoreWrapper;
    int isLoadType = 1;//1下拉刷新，2上拉加载
    boolean isLoad = false;//是否在加载，重复加载问题

    public static Fragment newInstance(int billType) {
        MyBillFragment fragment = new MyBillFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("billType", billType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            billType = bundle.getInt("billType");
        }
        isViewCreated = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);//比oncreate先执行
        if (isVisibleToUser && isViewCreated && !isLoad) {
            getRefreshData();
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_bill;
    }

    @Override
    public void initView() {
        initSwipe();
        initRecycler();
    }

    private void initSwipe() {
        swipe = $(R.id.swipe);
        swipe.setColorSchemeResources(R.color.color_1C81E9);
        swipe.setOnRefreshListener(() -> {
            if (isLoad) return;
            getRefreshData();
        });
    }

    public void getRefreshData() {
        swipe.setRefreshing(true);
        page = Constant.DEFAULT_PAGE;
        isLoad = true;
        isLoadType = 1;
        mPresenter.getBill(MySelfInfo.getInstance().getUserId(),billType,page);
    }

    public void getMoreData() {
        isLoad = true;
        page = page + 1;
        isLoadType = 2;
        mPresenter.getBill(MySelfInfo.getInstance().getUserId(),billType,page);
    }

    @Override
    public void initData() {
        page = Constant.DEFAULT_PAGE;

        mPresenter = new MyBillPresenter(context,this);

        if (getUserVisibleHint()) {
            getRefreshData();
        }
    }

    //初始化recyclerview
    private void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new BillListAdapter(activity, new ArrayList<>());
        loadMoreWrapper = new LoadMoreWrapper(mAdapter);
        recyclerView.setAdapter(loadMoreWrapper);

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (isLoad || loadMoreWrapper.getLoadState() == LoadMoreWrapper.LOADING_END) return;
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
                getMoreData();
            }
        });
    }


    @Override
    public void getBillSuccess(List<MyBillListBean> data) {
        List<MyBillListBean> datas = data;

        if (isLoadType == 1) {
            mAdapter.setData(datas);
        } else {
            mAdapter.addData(datas);
        }
        isLoad = false;

        swipe.setRefreshing(false);

        if (datas.size() >= Constant.DEFAULT_SIZE) {
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
        } else {
            // 显示加载到底的提示
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
        }
    }

    @Override
    public void getBillFailed(String msg) {
        showLongToast(msg);
        isLoad = false;
        swipe.setRefreshing(false);
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
    }
}
