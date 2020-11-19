package com.zlyc.www.view.shop;

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

public class OrderListFragment  extends BaseFragment implements OrderListContract.View {

    public static final int ORDER_ALL = 0;
    public static final int ORDER_PAYMENT = 1;
    public static final int ORDER_DELIVER = 2;
    public static final int ORDER_RECEIVING = 3;

    private int orderType;

    private RecyclerView recyclerView;
    private OrderListAdapter mAdapter;
    private OrderListPresenter mPresenter;

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
        initRecycler();
    }

    @Override
    public void initData() {
        mPresenter = new OrderListPresenter(context,this);
        mPresenter.getOrderList(MySelfInfo.getInstance().getUserId(),orderType);
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
                showShortToast("订单详情");
            }
        });
    }


    @Override
    public void getOrderListSuccess(List<OrderListBean> data) {
        mAdapter.setData(data);
    }

    @Override
    public void getOrderListFailed(String msg) {
        showLongToast(msg);
    }
}
