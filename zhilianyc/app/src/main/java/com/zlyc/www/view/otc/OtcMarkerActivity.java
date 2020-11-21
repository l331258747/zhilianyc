package com.zlyc.www.view.otc;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.zlyc.www.R;
import com.zlyc.www.adapter.base.EndLessScrollOnScrollListener;
import com.zlyc.www.adapter.base.LoadMoreWrapper;
import com.zlyc.www.adapter.otc.OtcListAdapter;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.bean.otc.OtcInfoBean;
import com.zlyc.www.bean.otc.OtcListBean;
import com.zlyc.www.constant.Constant;
import com.zlyc.www.mvp.otc.OtcMarkerContract;
import com.zlyc.www.mvp.otc.OtcMarkerPresenter;
import com.zlyc.www.util.StringUtils;
import com.zlyc.www.util.chart.ChartHelp;
import com.zlyc.www.util.chart.RankAgeListBean;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class OtcMarkerActivity extends BaseActivity implements OtcMarkerContract.View, View.OnClickListener {

    SwipeRefreshLayout swipe;
    NestedScrollView scrollView;
    ImageView iv_img, iv_change, iv_order_price, iv_order_num;
    TextView tv_todayTradeNum, tv_name, tv_change, tv_height, tv_low, tv_amount, btn_submit;
    View view_order_price, view_order_num;
    LineChart lineChart;
    RecyclerView recyclerView;

    LinearLayout tab_bulk, tab_pack, view_change;
    TextView tv_bulk, tv_pack;
    View line_bulk, line_pack;

    OtcMarkerPresenter mPresenter;
    OtcListAdapter mAdapter;

    private List<OtcListBean> datas;
    private LoadMoreWrapper loadMoreWrapper;
    private int page;
    private int isLoadType = 1;//1下拉刷新，2上拉加载
    private boolean isLoad = false;//是否在加载，重复加载问题

    OtcInfoBean infoData;

    ChartHelp mChartHelp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_otc_marker;
    }

    @Override
    public void initView() {
        showLeftAndTitle("置换中心");

        tab_bulk = $(R.id.tab_bulk);
        tab_pack = $(R.id.tab_pack);
        tv_bulk = $(R.id.tv_bulk);
        tv_pack = $(R.id.tv_pack);
        line_pack = $(R.id.line_pack);
        line_bulk = $(R.id.line_bulk);

        scrollView = $(R.id.scrollView);
        iv_img = $(R.id.iv_img);
        iv_change = $(R.id.iv_change);
        iv_order_price = $(R.id.iv_order_price);
        iv_order_num = $(R.id.iv_order_num);

        tv_todayTradeNum = $(R.id.tv_todayTradeNum);
        tv_name = $(R.id.tv_name);
        tv_change = $(R.id.tv_change);
        tv_height = $(R.id.tv_height);
        tv_low = $(R.id.tv_low);
        tv_amount = $(R.id.tv_amount);
        btn_submit = $(R.id.btn_submit);

        view_change = $(R.id.view_change);
        view_order_price = $(R.id.view_order_price);
        view_order_num = $(R.id.view_order_num);

        lineChart = $(R.id.lineChart);

        tab_bulk.setOnClickListener(this);
        tab_pack.setOnClickListener(this);
        view_change.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        view_order_price.setOnClickListener(this);
        view_order_num.setOnClickListener(this);

        initRecycler();
        initSwipeLayout();

    }


    @Override
    public void initData() {
        mPresenter = new OtcMarkerPresenter(context, this);
        mPresenter.getOtcOpen();

        mChartHelp = new ChartHelp(context,lineChart);

        iv_order_price.setImageResource(priceSort == 1 ? R.mipmap.ic_order_down : R.mipmap.ic_order_up);
        iv_order_num.setImageResource(numSort == 1 ? R.mipmap.ic_order_down : R.mipmap.ic_order_up);
        setTab(numType == 0 ? true : false);
    }

    private void initSwipeLayout() {
        swipe = $(R.id.swipe);
        swipe.setColorSchemeColors(getResColor(R.color.color_368feb));
        swipe.setOnRefreshListener(() -> {
            if (isLoad) return;
            isLoad = true;

            getRefreshData();

        });
    }

    private void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new OtcListAdapter(activity, new ArrayList<>());
        loadMoreWrapper = new LoadMoreWrapper(mAdapter);
        recyclerView.setAdapter(loadMoreWrapper);
        recyclerView.setNestedScrollingEnabled(false);

        mAdapter.setOnItemClickListener(position -> {
            OtcListBean item = datas.get(position);

            intent = new Intent(context, OtcDetailActivity.class);
            intent.putExtra("orderType", item.getOrderType());
            intent.putExtra("beansSendId", item.getId());
            startActivity(intent);
        });

        scrollView.setOnScrollChangeListener(new EndLessScrollOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (isLoad || loadMoreWrapper.getLoadState() == LoadMoreWrapper.LOADING_END) return;
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
                getMoreData();
            }

            @Override
            public void onScrollChange(int scrollY) {

            }
        });
    }

    private void getRefreshData() {
        swipe.setRefreshing(true);
        isLoad = true;
        page = Constant.DEFAULT_PAGE;
        isLoadType = 1;
        mPresenter.getOtcList(MySelfInfo.getInstance().getUserId(), orderType, priceSort, numSort, numType, page);
    }

    private void getMoreData() {
        isLoad = true;
        page = page + 1;
        isLoadType = 2;
        mPresenter.getOtcList(MySelfInfo.getInstance().getUserId(), orderType, priceSort, numSort, numType, page);
    }

    int orderType = 0;//转让还是求购订单0是求购蛋1是转让单
    int priceSort = 0;//价格排序方式0是升序1是倒序
    int numSort = 0;//数量排序方式0是升序1是倒序
    int numType = 0;//散货还是整货0是散货1是整货

    @Override
    public void getOtcInfoSuccess(OtcInfoBean data) {
        this.infoData = data;
        if (orderType == 1) {
            tv_todayTradeNum.setText(StringUtils.getStringNum(data.getTodayBuyNum()));
        } else {
            tv_todayTradeNum.setText(StringUtils.getStringNum(data.getTodaySellNum()));
        }
        tv_height.setText(StringUtils.getStringNum(data.getTodayMaxPrice()));
        tv_low.setText(StringUtils.getStringNum(data.getTodayMinPrice()));
        tv_amount.setText(StringUtils.getStringNum(data.getTodayTradeNum()));

        if(infoData.getLineData() != null && infoData.getLineData().getCategories() != null && infoData.getLineData().getTradeNums() != null){
            List<RankAgeListBean> lineDatas = new ArrayList<>();
            for (int i=0;i<infoData.getLineData().getCategories().size();i++){
                RankAgeListBean item = new RankAgeListBean();
                item.setName(infoData.getLineData().getCategories().get(i));
                item.setNum(infoData.getLineData().getTradeNums().get(i));
                lineDatas.add(item);
            }
            mChartHelp.initLineChart(lineDatas);
            mChartHelp.showLineChart("每日成交量",lineDatas);
        }
    }

    @Override
    public void getOtcInfoFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void getOtcOpenSuccess(String data) {
        // 0 只开求购通道
        // 1 只开转让通道
        // 2 两个通道都开

        if (TextUtils.equals("2", data)) {
            view_change.setVisibility(View.VISIBLE);
            tv_change.setText("我要转让");
            orderType = 0;
        } else if (TextUtils.equals("1", data)) {
            view_change.setVisibility(View.GONE);
            orderType = 1;
        } else {
            view_change.setVisibility(View.GONE);
            orderType = 0;
        }

        if (orderType == 1) {
            iv_img.setImageResource(R.mipmap.ic_otc_change);
            tv_name.setText("转让单");
        } else {
            iv_img.setImageResource(R.mipmap.ic_otc_buy);
            tv_name.setText("求购单");
        }

        mPresenter.getOtcInfo();
        getRefreshData();
    }

    @Override
    public void getOtcOpenFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void getOtcListSuccess(List<OtcListBean> datas) {
        this.datas = datas;

        swipe.setRefreshing(false);

        if (isLoadType == 1) {
            mAdapter.setData(datas);
        } else {
            mAdapter.addData(datas);
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
    public void getOtcListFailed(String msg) {
        showShortToast(msg);
        swipe.setRefreshing(false);
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
        isLoad = false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_bulk:
                if (numType == 0)
                    return;
                setTab(true);
                numType = 0;
                getRefreshData();
                break;
            case R.id.tab_pack:
                if (numType == 1)
                    return;
                setTab(false);
                numType = 1;
                getRefreshData();
                break;
            case R.id.view_change:
                orderType = orderType == 0 ? 1 : 0;

                if (orderType == 1) {
                    iv_img.setImageResource(R.mipmap.ic_otc_change);
                    tv_name.setText("转让单");
                    tv_change.setText("我要求购");
                    tv_todayTradeNum.setText(StringUtils.getStringNum(infoData.getTodayBuyNum()));
                } else {
                    iv_img.setImageResource(R.mipmap.ic_otc_buy);
                    tv_name.setText("求购单");
                    tv_change.setText("我要转让");
                    tv_todayTradeNum.setText(StringUtils.getStringNum(infoData.getTodaySellNum()));
                }

                getRefreshData();
                break;
            case R.id.btn_submit:
                //TODO popupwindow

                break;
            case R.id.view_order_price:
                priceSort = priceSort == 0 ? 1 : 0;

                iv_order_price.setImageResource(priceSort == 1 ? R.mipmap.ic_order_down : R.mipmap.ic_order_up);

                getRefreshData();
                break;
            case R.id.view_order_num:
                numSort = numSort == 0 ? 1 : 0;

                iv_order_num.setImageResource(numSort == 1 ? R.mipmap.ic_order_down : R.mipmap.ic_order_up);

                getRefreshData();
                break;

        }
    }


    //设置tab
    public void setTab(boolean isbulk) {
        tv_bulk.setTextColor(ContextCompat.getColor(context, R.color.color_99));
        line_bulk.setVisibility(View.INVISIBLE);
        tv_pack.setTextColor(ContextCompat.getColor(context, R.color.color_99));
        line_pack.setVisibility(View.INVISIBLE);
        if (isbulk) {
            tv_bulk.setTextColor(ContextCompat.getColor(context, R.color.color_368feb));
            line_bulk.setVisibility(View.VISIBLE);
        } else {
            tv_pack.setTextColor(ContextCompat.getColor(context, R.color.color_368feb));
            line_pack.setVisibility(View.VISIBLE);
        }
    }
}
