package com.zlyc.www.view.coupon;

import android.os.Bundle;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.adapter.coupon.MyCouponAdapter;
import com.zlyc.www.base.BaseFragment;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.bean.coupon.MyCouponBean;
import com.zlyc.www.bean.coupon.MyCouponList;
import com.zlyc.www.mvp.coupon.MyCouponContract;
import com.zlyc.www.mvp.coupon.MyCouponPresenter;
import com.zlyc.www.util.rxbus.RxBus2;
import com.zlyc.www.util.rxbus.busEvent.MyCouponEvent;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.reactivex.disposables.Disposable;

public class MyCouponFragment extends BaseFragment implements MyCouponContract.View {

    SwipeRefreshLayout swipe;
    TextView tv_output, tv_labor;
    RecyclerView recyclerView;

    private MyCouponAdapter mAdapter;

    MyCouponPresenter mPresenter;

    Disposable mDisposable;

    private boolean isViewCreated;
    boolean isLoad = false;

    public static Fragment newInstance() {
        MyCouponFragment fragment = new MyCouponFragment();
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
        return R.layout.fragment_my_coupon;
    }

    @Override
    public void initView() {
        tv_output = $(R.id.tv_output);
        tv_labor = $(R.id.tv_labor);

        initSwipe();
        initRecycler();
    }

    @Override
    public void initData() {
        mPresenter = new MyCouponPresenter(context,this);

        mDisposable = RxBus2.getInstance().toObservable(MyCouponEvent.class, myCouponEvent -> getRefreshData());

        if (getUserVisibleHint()) {
            getRefreshData();
        }

    }

    private void getRefreshData() {
        swipe.setRefreshing(true);
        isLoad = true;
        mPresenter.getMyCoupon(MySelfInfo.getInstance().getUserId());
    }

    private void initSwipe() {
        swipe = $(R.id.swipe);
        swipe.setColorSchemeResources(R.color.color_1C81E9);
        swipe.setOnRefreshListener(() -> {
            if (isLoad) return;
            getRefreshData();
        });
    }

    //初始化recyclerview
    private void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new MyCouponAdapter(activity, new ArrayList<>());
        recyclerView.setAdapter(mAdapter);
        ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);//itemChanged 闪烁问题

    }


    @Override
    public void getMyCouponSuccess(MyCouponBean data) {
        tv_labor.setText(data.getLaborStr());
        tv_output.setText(data.getOutputStr());

        List<MyCouponList> datas = data.getList();

        mAdapter.setData(datas);

        isLoad = false;
        swipe.setRefreshing(false);
    }

    @Override
    public void getMyCouponFailed(String msg) {
        showLongToast(msg);
        isLoad = false;
        swipe.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed())
            mDisposable.dispose();
    }
}
