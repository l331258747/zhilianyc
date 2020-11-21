package com.zlyc.www.view.home.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.zlyc.www.R;
import com.zlyc.www.adapter.shop.HotGoodsAdapter;
import com.zlyc.www.base.BaseFragment;
import com.zlyc.www.bean.shop.HotGoodsBean;
import com.zlyc.www.mvp.shop.HotGoodsContract;
import com.zlyc.www.mvp.shop.HotGoodsPresenter;
import com.zlyc.www.view.shop.GoodsDetailsActivity;
import com.zlyc.www.view.shop.OrderListActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShopFragment extends BaseFragment implements HotGoodsContract.View, View.OnClickListener {


    View tab_electrical,tab_hot,tab_studio,tab_foot,tab_life;
    RecyclerView recyclerView;
    ImageView iv_floating;

    HotGoodsAdapter mAdapter;
    List<HotGoodsBean> datas;

    HotGoodsPresenter mPresenter;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    public void initView() {
        tab_electrical = $(R.id.tab_electrical);
        tab_hot = $(R.id.tab_hot);
        tab_studio = $(R.id.tab_studio);
        tab_foot = $(R.id.tab_foot);
        tab_life = $(R.id.tab_life);
        iv_floating = $(R.id.iv_floating);

        iv_floating.setOnClickListener(this);

        initRecycler();
    }

    @Override
    public void initData() {
        mPresenter = new HotGoodsPresenter(context,this);
        mPresenter.getHotGoods();
    }

    //初始化recyclerview
    public void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new HotGoodsAdapter(context, new ArrayList<>());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        mAdapter.setOnItemClickListener(position -> {
            HotGoodsBean item = datas.get(position);

            Intent intent = new Intent(context, GoodsDetailsActivity.class);
            intent.putExtra("id",item.getGoodsId());
            startActivity(intent);
        });
    }

    @Override
    public void getHotGoodsSuccess(List<HotGoodsBean> data) {
        if(data == null) data = new ArrayList<>();
        this.datas = data;
        mAdapter.setData(data);
    }

    @Override
    public void getHotGoodsFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_floating:
                startActivity(new Intent(context, OrderListActivity.class));
                break;

        }
    }
}
