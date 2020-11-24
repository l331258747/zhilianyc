package com.zlyc.www.view.shop;

import android.content.Intent;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.adapter.base.EndlessRecyclerOnScrollListener;
import com.zlyc.www.adapter.base.LoadMoreWrapper;
import com.zlyc.www.adapter.shop.GoodsCategoryAdapter;
import com.zlyc.www.adapter.shop.GoodsListAdapter;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.shop.GoodsClassBean;
import com.zlyc.www.bean.shop.GoodsListBean;
import com.zlyc.www.constant.Constant;
import com.zlyc.www.mvp.shop.GoodsListContract;
import com.zlyc.www.mvp.shop.GoodsListPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class GoodsListActivity extends BaseActivity implements GoodsListContract.View {

    String categoryId;
    String categoryName;
    List<GoodsClassBean> dataH;
    List<GoodsListBean> datas;

    GoodsListPresenter mPresenter;

    SwipeRefreshLayout swipe;
    RecyclerView recycler_view_category,recycler_view_goods;
    TextView tv_title;

    GoodsCategoryAdapter mCategoryAdapter;
    GoodsListAdapter mGoodsAdapter;

    int page = Constant.DEFAULT_PAGE;
    LoadMoreWrapper loadMoreWrapper;
    int isLoadType = 1;//1下拉刷新，2上拉加载
    boolean isLoad = false;//是否在加载，重复加载问题



    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_list;
    }

    @Override
    public void initView() {
        showLeftAndTitle("全部商品");

        categoryId = intent.getStringExtra("categoryId");
        categoryName = intent.getStringExtra("categoryName");
        dataH = (List<GoodsClassBean>) intent.getSerializableExtra("goodsClass");

        tv_title = $(R.id.tv_title);

        initSwipe();
        initRecycler();
        initRecyclerCategory();

    }

    private void initSwipe() {
        swipe = $(R.id.swipe);
        swipe.setColorSchemeResources(R.color.color_1C81E9);
        swipe.setOnRefreshListener(() -> {
            getRefreshData();
        });
    }

    //初始化recyclerview
    public void initRecycler() {
        recycler_view_goods = $(R.id.recycler_view_goods);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler_view_goods.setLayoutManager(linearLayoutManager);
        mGoodsAdapter = new GoodsListAdapter(context, new ArrayList<>());
        loadMoreWrapper = new LoadMoreWrapper(mGoodsAdapter);
        recycler_view_goods.setAdapter(loadMoreWrapper);

        recycler_view_goods.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (isLoad || loadMoreWrapper.getLoadState() == LoadMoreWrapper.LOADING_END) return;
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
                getMoreData();
            }
        });

        mGoodsAdapter.setOnItemClickListener(position -> {
            intent = new Intent(context, GoodsDetailsActivity.class);
            intent.putExtra("id", datas.get(position).getGoodsId());
            startActivity(intent);
        });
    }

    //初始化recyclerview
    public void initRecyclerCategory() {
        recycler_view_category = $(R.id.recycler_view_category);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler_view_category.setLayoutManager(linearLayoutManager);
        mCategoryAdapter = new GoodsCategoryAdapter(context, dataH,categoryId);
        recycler_view_category.setAdapter(mCategoryAdapter);

        mCategoryAdapter.setOnItemClickListener(position -> {
            categoryId = dataH.get(position).getId();
            categoryName = dataH.get(position).getName();
            tv_title.setText(categoryName);
            getRefreshData();
        });

    }

    @Override
    public void initData() {
        tv_title.setText(categoryName);
        mPresenter = new GoodsListPresenter(context,this);
        getRefreshData();
    }

    public void getRefreshData() {
        swipe.setRefreshing(true);
        page = Constant.DEFAULT_PAGE;
        isLoad = true;
        isLoadType = 1;
        mPresenter.getGoodsList(categoryId,page);
    }

    public void getMoreData() {
        isLoad = true;
        page = page + 1;
        isLoadType = 2;
        mPresenter.getGoodsList(categoryId,page);
    }

    @Override
    public void getGoodsListSuccess(List<GoodsListBean> data) {
        if(data == null) data = new ArrayList<>();
        this.datas = data;

        swipe.setRefreshing(false);

        if (isLoadType == 1) {
            mGoodsAdapter.setData(datas);
        } else {
            mGoodsAdapter.addData(datas);
        }
        isLoad = false;

        if (datas.size() >= Constant.DEFAULT_SIZE) {
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
        } else {
            // 显示加载到底的提示
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
        }
    }

    @Override
    public void getGoodsListFailed(String msg) {
        showShortToast(msg);
        isLoad = false;
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
        swipe.setRefreshing(false);
    }
}
