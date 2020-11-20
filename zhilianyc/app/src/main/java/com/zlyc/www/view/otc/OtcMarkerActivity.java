package com.zlyc.www.view.otc;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.zlyc.www.R;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.otc.OtcInfoBean;
import com.zlyc.www.mvp.otc.OtcMarkerContract;
import com.zlyc.www.mvp.otc.OtcMarkerPresenter;
import com.zlyc.www.widget.myTabLayout.TabLayout;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class OtcMarkerActivity extends BaseActivity implements OtcMarkerContract.View {

    SwipeRefreshLayout swipe;
    NestedScrollView scrollView;
    ImageView iv_img,iv_change,iv_order_price,iv_order_num;
    TextView tv_todayTradeNum,tv_name,tv_change,tv_height,tv_low,tv_amount,btn_submit;
    View view_change,view_order_price,view_order_num;
    LineChart lineChart;
    TabLayout tabs;
    RecyclerView recycler_view;

    OtcMarkerPresenter mPresenter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_otc_marker;
    }

    @Override
    public void initView() {
        showLeftAndTitle("置换中心");



    }

    @Override
    public void initData() {

    }

    @Override
    public void getOtcInfoSuccess(OtcInfoBean data) {

    }

    @Override
    public void getOtcInfoFailed(String msg) {

    }

    @Override
    public void getOtcOpenSuccess(String data) {

    }

    @Override
    public void getOtcOpenFailed(String msg) {

    }
}
