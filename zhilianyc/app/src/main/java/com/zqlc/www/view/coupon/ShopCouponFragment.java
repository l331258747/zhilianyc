package com.zqlc.www.view.coupon;

import android.os.Bundle;

import com.zqlc.www.R;
import com.zqlc.www.adapter.coupon.ShopCouponAdapter;
import com.zqlc.www.base.BaseFragment;
import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.bean.coupon.ShopCouponBean;
import com.zqlc.www.dialog.TextDialog;
import com.zqlc.www.mvp.coupon.ShopCouponContract;
import com.zqlc.www.mvp.coupon.ShopCouponPresenter;
import com.zqlc.www.util.rxbus.RxBus2;
import com.zqlc.www.util.rxbus.busEvent.MyCouponEvent;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class ShopCouponFragment extends BaseFragment implements ShopCouponContract.View {

    SwipeRefreshLayout swipe;
    RecyclerView recyclerView;

    private ShopCouponAdapter mAdapter;

    ShopCouponPresenter mPresenter;

    List<ShopCouponBean> datas;

    private boolean isViewCreated;
    boolean isLoad = false;


    public static Fragment newInstance() {
        ShopCouponFragment fragment = new ShopCouponFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        return R.layout.fragment_shop_coupon;
    }

    @Override
    public void initView() {
        initSwipe();
        initRecycler();
    }

    private void getRefreshData() {
        swipe.setRefreshing(true);
        isLoad = true;
        mPresenter.getShopCoupon(MySelfInfo.getInstance().getUserId());
    }

    private void initSwipe() {
        swipe = $(R.id.swipe);
        swipe.setColorSchemeResources(R.color.color_1C81E9);
        swipe.setOnRefreshListener(() -> {
            if (isLoad) return;
            getRefreshData();
        });
    }

    @Override
    public void initData() {
        mPresenter = new ShopCouponPresenter(context,this);
        if (getUserVisibleHint()) {
            getRefreshData();
        }
    }

    //初始化recyclerview
    private void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new ShopCouponAdapter(activity, new ArrayList<>());
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(position -> {
            new TextDialog(context).setTitle("兑换").setContent("确认兑换吗？").setSubmitListener(v -> {
                ShopCouponBean item = datas.get(position);
                mPresenter.buyShopCoupon(MySelfInfo.getInstance().getUserId(),item.getType());
            }).show();
        });
    }

    @Override
    public void getShopCouponSuccess(List<ShopCouponBean> data) {
        if(data == null) data = new ArrayList<>();
        datas = data;
        mAdapter.setData(datas);

        isLoad = false;
        swipe.setRefreshing(false);
    }

    @Override
    public void getShopCouponFailed(String msg) {
        showLongToast(msg);

        isLoad = false;
        swipe.setRefreshing(false);
    }

    @Override
    public void buyShopCouponSuccess(EmptyModel data) {
        showShortToast("购买成功");
        RxBus2.getInstance().post(new MyCouponEvent());
    }

    @Override
    public void buyShopCouponFailed(String msg) {
        showShortToast(msg);
    }
}
