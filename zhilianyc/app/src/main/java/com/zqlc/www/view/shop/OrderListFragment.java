package com.zqlc.www.view.shop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.zqlc.www.R;
import com.zqlc.www.adapter.shop.OrderListAdapter;
import com.zqlc.www.base.BaseFragment;
import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.bean.login.MineBean;
import com.zqlc.www.bean.shop.OrderListBean;
import com.zqlc.www.dialog.OrderPayDialog;
import com.zqlc.www.dialog.TextDialog;
import com.zqlc.www.mvp.my.MyInfoContract;
import com.zqlc.www.mvp.my.MyInfoPresenter;
import com.zqlc.www.mvp.shop.OrderListContract;
import com.zqlc.www.mvp.shop.OrderListPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class OrderListFragment  extends BaseFragment implements OrderListContract.View, MyInfoContract.View {

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

    MyInfoPresenter mInfoPresenter;

    private boolean isViewCreated;
    boolean isLoad = false;

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
            if (isLoad) return;
            getRefreshData();
        });
    }

    public void getRefreshData() {
        swipe.setRefreshing(true);
        isLoad = true;
        mPresenter.getOrderList(MySelfInfo.getInstance().getUserId(),orderType);
    }

    @Override
    public void initData() {
        mInfoPresenter = new MyInfoPresenter(context,this);
        mPresenter = new OrderListPresenter(context,this);

        if (getUserVisibleHint()) {
            getRefreshData();
        }
    }


    int payPosition;
    //初始化recyclerview
    private void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new OrderListAdapter(activity, new ArrayList<>());
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OrderListAdapter.OnItemClickListener() {
            @Override
            public void onCancelClick(int position) {
                new TextDialog(context).setContent("是否确认取消订单？").setSubmitListener(v1 -> {
                    mPresenter.cancelOrder(MySelfInfo.getInstance().getUserId(),datas.get(position).getId());
                }).show();
            }

            @Override
            public void onPayClick(int position) {
                payPosition = position;
                mInfoPresenter.mine(MySelfInfo.getInstance().getUserId(),true);
            }

            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(context,OrderDetailActivity.class);
                intent.putExtra("orderId",datas.get(position).getId());
                startActivity(intent);
            }

            @Override
            public void onReceiveClick(int position) {
                new TextDialog(context).setContent("是否确认收货？").setSubmitListener(v1 -> {
                    mPresenter.receiveOrder(MySelfInfo.getInstance().getUserId(),datas.get(position).getId());
                }).show();
            }
        });
    }


    @Override
    public void getOrderListSuccess(List<OrderListBean> data) {
        if(data == null) data = new ArrayList<>();
        this.datas = data;
        mAdapter.setData(data);

        isLoad = false;
        swipe.setRefreshing(false);
    }

    @Override
    public void getOrderListFailed(String msg) {
        showLongToast(msg);
        isLoad = false;
        swipe.setRefreshing(false);
    }

    @Override
    public void receiveOrderSuccess(EmptyModel data) {
        showShortToast("确认收货成功");
        getRefreshData();
    }

    @Override
    public void receiveOrderFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void cancelOrderSuccess(EmptyModel data) {
        showShortToast("取消成功");
        getRefreshData();
    }

    @Override
    public void cancelOrderFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void payOrderSuccess(EmptyModel data) {
        showShortToast("支付成功");
        getRefreshData();
        mOrderPayDialog.dismiss();
    }

    @Override
    public void payOrderFailed(String msg) {
        showShortToast(msg);
    }

    OrderPayDialog mOrderPayDialog;
    @Override
    public void mineSuccess(MineBean data) {
        mOrderPayDialog = new OrderPayDialog(context,this.datas.get(payPosition).getTotalSum(),data.getBeans()).setSubmitListener((dialog, content) -> {
            if(TextUtils.isEmpty(content)){
                showShortToast("请输入密码");
                return;
            }
            if(data.getBeans() < this.datas.get(payPosition).getTotalSum()){
                showShortToast("支付金豆不足");
                return;
            }
            mPresenter.payOrder(MySelfInfo.getInstance().getUserId(),this.datas.get(payPosition).getId(),content);
        });
        mOrderPayDialog.show();
    }

    @Override
    public void mineFailed(String msg) {
        showShortToast(msg);
    }
}
