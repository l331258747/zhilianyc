package com.zlyc.www.view.shop;

import android.content.Intent;
import android.os.Bundle;

import com.zlyc.www.R;
import com.zlyc.www.adapter.shop.OrderListAdapter;
import com.zlyc.www.base.BaseFragment;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.bean.shop.OrderListBean;
import com.zlyc.www.mvp.shop.OrderListContract;
import com.zlyc.www.mvp.shop.OrderListPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class OrderListFragment  extends BaseFragment implements OrderListContract.View {

    public static final int ORDER_ALL = 0;
    public static final int ORDER_PAYMENT = 1;
    public static final int ORDER_DELIVER = 2;
    public static final int ORDER_RECEIVING = 3;

    private int orderType;

    SwipeRefreshLayout swipe;

    private RecyclerView recyclerView;
    private OrderListAdapter mAdapter;
    private OrderListPresenter mPresenter;
    List<OrderListBean> datas;

    public static Fragment newInstance(int orderType) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("orderType", orderType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            orderType = bundle.getInt("orderType");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_list;
    }

    @Override
    public void initView() {
        swipe = $(R.id.swipe);

        initSwipe();
        initRecycler();

    }

    private void initSwipe() {
        swipe = $(R.id.swipe);
        swipe.setColorSchemeResources(R.color.color_1C81E9);
        swipe.setOnRefreshListener(() -> {
            getRefreshData();
        });
    }

    public void getRefreshData() {
        swipe.setRefreshing(true);
        mPresenter.getOrderList(MySelfInfo.getInstance().getUserId(),orderType);
    }

    @Override
    public void initData() {
        mPresenter = new OrderListPresenter(context,this);
        getRefreshData();
    }


    //初始化recyclerview
    private void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new OrderListAdapter(activity, new ArrayList<>());
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OrderListAdapter.OnItemClickListener() {
            @Override
            public void onCancelClick(int position) {
                showShortToast("取消订单");
            }

            @Override
            public void onPayClick(int position) {
                showShortToast("付款");
            }

            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(context,OrderDetailActivity.class);
                intent.putExtra("orderId",datas.get(position).getId());
                startActivity(intent);
            }
        });
    }


    @Override
    public void getOrderListSuccess(List<OrderListBean> data) {
        if(data == null) data = new ArrayList<>();
        this.datas = data;
        mAdapter.setData(data);

        swipe.setRefreshing(false);
    }

    @Override
    public void getOrderListFailed(String msg) {
        showLongToast(msg);
        swipe.setRefreshing(false);
    }
}
