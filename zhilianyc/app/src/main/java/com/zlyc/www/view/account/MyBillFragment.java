package com.zlyc.www.view.account;

import android.os.Bundle;

import com.zlyc.www.R;
import com.zlyc.www.adapter.account.BillListAdapter;
import com.zlyc.www.base.BaseFragment;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.bean.account.MyBillBean;
import com.zlyc.www.bean.account.MyBillListBean;
import com.zlyc.www.mvp.account.MyBillPresenter;
import com.zlyc.www.mvp.account.MybillContract;

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
    boolean isLoad = false;


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
        isLoad = true;
        mPresenter.getBill(MySelfInfo.getInstance().getUserId(),billType);
    }

    @Override
    public void initData() {
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
        recyclerView.setAdapter(mAdapter);

    }


    @Override
    public void getBillSuccess(MyBillBean data) {
        List<MyBillListBean> datas = data.getList();
        mAdapter.setData(datas);

        isLoad = false;
        swipe.setRefreshing(false);
    }

    @Override
    public void getBillFailed(String msg) {
        showLongToast(msg);
        isLoad = false;
        swipe.setRefreshing(false);
    }
}
