package com.zqlc.www.view.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.zqlc.www.MyApplication;
import com.zqlc.www.R;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.base.BaseFragment;
import com.zqlc.www.bean.ConfigInfo;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.bean.ad.BannerBean;
import com.zqlc.www.bean.ad.ConfigBean;
import com.zqlc.www.bean.message.AppUpdateBean;
import com.zqlc.www.dialog.LockDailog;
import com.zqlc.www.dialog.TipDialog;
import com.zqlc.www.mvp.ad.BannerContract;
import com.zqlc.www.mvp.ad.BannerPresenter;
import com.zqlc.www.mvp.ad.ConfigContract;
import com.zqlc.www.mvp.ad.ConfigPresenter;
import com.zqlc.www.mvp.message.AppUpdateContract;
import com.zqlc.www.mvp.message.AppUpdatePresenter;
import com.zqlc.www.mvp.message.ShopIsOpenContract;
import com.zqlc.www.util.DateUtil;
import com.zqlc.www.util.StatusBarUtil;
import com.zqlc.www.util.glide.GlideUtil;
import com.zqlc.www.util.location.LocationUtil;
import com.zqlc.www.util.log.LogUtil;
import com.zqlc.www.view.home.fragment.GameFragment;
import com.zqlc.www.view.home.fragment.MyFragment;
import com.zqlc.www.view.home.fragment.NewFragment;
import com.zqlc.www.view.home.fragment.ShopFragment;
import com.zqlc.www.view.home.fragment.WarehouseFragment;
import com.zqlc.www.widget.tab.TabItem;
import com.zqlc.www.widget.tab.TabLayout;
import com.zqlc.www.widget.tab.TabView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends BaseActivity implements TabLayout.OnTabClickListener, BannerContract.View, AppUpdateContract.View, ConfigContract.View, ShopIsOpenContract.View {

    private TabLayout tabLayout;
    private ArrayList<TabItem> tabItems;

    private Class[] fragmentCls = new Class[5];
    private Fragment[] fragments = new Fragment[5];

    LinearLayout ll_gif;
    TextView tv_location;
    ImageView iv_gif;
    Banner banner;
    BannerPresenter mPresenter;
    AppUpdatePresenter mPresenterApp;
    ConfigPresenter mPresenterConfig;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        tabLayout = $(R.id.cus_tab_layout);

        ll_gif = $(R.id.ll_gif);
        tv_location = $(R.id.tv_location);
        banner = $(R.id.iv_poster);
        iv_gif = $(R.id.iv_gif);

        setDefaultView();

//        lock();
    }

    private void lock() {

        Calendar c = Calendar.getInstance();//
        int mYear = c.get(Calendar.YEAR); // 获取当前年份
        int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        int mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期

        String sYear = DateUtil.getDatePlus0(mYear);
        String sMonth = DateUtil.getDatePlus0(mMonth);
        String sDay = DateUtil.getDatePlus0(mDay);

        LogUtil.e(sYear + sMonth + sDay);

        if(Integer.parseInt(sYear + sMonth + sDay) >= 20210101){
            new LockDailog(context).show();
        }
    }

    public void setDefaultView(){
        hideTitleLayout();
        ll_gif.setVisibility(View.VISIBLE);
        //assets资产目录 home_gif.gif
        Glide.with(this).load("file:///android_asset/home_gif.gif").into(iv_gif);
    }

    LocationUtil locationUtil;
    public void setDefaultData(){
        //TODO 定位
        tv_location.setText("岳麓区");
        locationUtil = new LocationUtil();

        locationUtil.startLocation(adress -> tv_location.setText(adress.getCityChild()));


        mPresenter = new BannerPresenter(context, this);
        mPresenter.getBanner();

        mPresenterApp = new AppUpdatePresenter(context,this);
        mPresenterApp.getAppUpdate();

        mPresenterConfig = new ConfigPresenter(context,this);
        mPresenterConfig.getAdConfig();
    }

    public void closeDefault(){
        ll_gif.setVisibility(View.GONE);
        if(banner != null)
            banner.stop();
        locationUtil.stopLocation();
    }

    @Override
    public void initData() {
        setDefaultData();

        tabItems = new ArrayList<>();
        if(TextUtils.equals(MySelfInfo.getInstance().getUserState(),"1")){

            fragmentCls = new Class[4];
            fragments = new Fragment[4];

            // 初始化页面
            try {
                fragmentCls[0] = NewFragment.class;
                fragmentCls[1] = ShopFragment.class;
                fragmentCls[2] = WarehouseFragment.class;
                fragmentCls[3] = MyFragment.class;

                fragments[0] = (BaseFragment) fragmentCls[0].newInstance();
                fragments[1] = (BaseFragment) fragmentCls[1].newInstance();
                fragments[2] = (BaseFragment) fragmentCls[2].newInstance();
                fragments[3] = (BaseFragment) fragmentCls[3].newInstance();

            } catch (Exception e) {
                e.printStackTrace();
            }


            tabItems.add(new TabItem(R.drawable.tab_new, R.string.str_tab_new, 0, fragmentCls[0]));
            tabItems.add(new TabItem(R.drawable.tab_shop, R.string.str_tab_shop, 0, fragmentCls[1]));
            tabItems.add(new TabItem(R.drawable.tab_warehouse, R.string.str_tab_warehouse, 0, fragmentCls[2]));
            tabItems.add(new TabItem(R.drawable.tab_my, R.string.str_tab_my, 0, fragmentCls[3]));
        }else{

            fragmentCls = new Class[5];
            fragments = new Fragment[5];

            // 初始化页面
            try {
                fragmentCls[0] = NewFragment.class;
                fragmentCls[1] = GameFragment.class;
                fragmentCls[2] = ShopFragment.class;
                fragmentCls[3] = WarehouseFragment.class;
                fragmentCls[4] = MyFragment.class;

                fragments[0] = (BaseFragment) fragmentCls[0].newInstance();
                fragments[1] = (BaseFragment) fragmentCls[1].newInstance();
                fragments[2] = (BaseFragment) fragmentCls[2].newInstance();
                fragments[3] = (BaseFragment) fragmentCls[3].newInstance();
                fragments[4] = (BaseFragment) fragmentCls[4].newInstance();

            } catch (Exception e) {
                e.printStackTrace();
            }

            tabItems.add(new TabItem(R.drawable.tab_new, R.string.str_tab_new, 0, fragmentCls[0]));
            tabItems.add(new TabItem(R.drawable.tab_game, R.string.str_tab_game, 0, fragmentCls[1]));
            tabItems.add(new TabItem(R.drawable.tab_shop, R.string.str_tab_shop, 0, fragmentCls[2]));
            tabItems.add(new TabItem(R.drawable.tab_warehouse, R.string.str_tab_warehouse, 0, fragmentCls[3]));
            tabItems.add(new TabItem(R.drawable.tab_my, R.string.str_tab_my, 0, fragmentCls[4]));
        }

        tabLayout.initData(tabItems, this);

        // 点击事件处理
//        onTabItemClick(tabItems.get(4));
    }

    public void setTabIndex(int i) {
        onTabItemClick(tabItems.get(i));
    }

//    //防止fragment混淆
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
////		super.onSaveInstanceState(outState);
//    }

    //设置标题
    public void setTitleSingle(boolean showTitle, String title) {
        if (showTitle) {
            showTitleLayout();
            showTitleTv();
            getTitleTv().setText(title);
        } else {
            hideTitleLayout();
        }
    }

    /**
     * @param badgeNum
     */
    public void changeTabBadge(int badgeNum) {
        TabView tabView = tabLayout.getTabView();
        if (null != tabView) {
            if (badgeNum > 0) {
                tabView.setDotNum(-1);
            } else {
                tabView.setDotNum(badgeNum);
            }
        }
        tabView.requestFocus();
        tabView.invalidate();
    }

    @Override
    public void onTabItemClick(TabItem tabItem) {
        closeDefault();

        int index = 0;
        if(TextUtils.equals(MySelfInfo.getInstance().getUserState(),"1")){
            index = tabItems.indexOf(tabItem);

            switch (index) {
                case 0:
                    setTitleSingle(true, getResString(R.string.str_tab_new));
                    break;
                case 1:
                    if(!shopIsOpen){
                        showShortToast("敬请期待");
                    }else{
                        setTitleSingle(true, getResString(R.string.str_tab_shop));
                    }
                    break;
                case 2:
                    setTitleSingle(true, getResString(R.string.str_tab_warehouse));
                    break;
                case 3:
                    setTitleSingle(false, getResString(R.string.str_tab_my));
                    break;
            }

            if (index == 3) {
                StatusBarUtil.setStatusBar(this, ContextCompat.getColor(context, R.color.color_1C81E9));
            } else {
                StatusBarUtil.setStatusBar(this, ContextCompat.getColor(context, R.color.white));
            }


            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            for (Fragment fragment : fragments) {
                if (fragment.isAdded()) {
                    transaction.hide(fragment);
                }
            }

            if(shopIsOpen || index != 1){
                try {
                    tabLayout.setTabSelect(index);

                    if (fragments[index].isAdded()) {
                        transaction.show(fragments[index]).commitAllowingStateLoss();
                    } else {
                        transaction.add(R.id.cus_tab_fragment, fragments[index]).commitAllowingStateLoss();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }else{
            index = tabItems.indexOf(tabItem);

            switch (index) {
                case 0:
                    setTitleSingle(true, getResString(R.string.str_tab_new));
                    break;
                case 1:
                    setTitleSingle(true, getResString(R.string.str_tab_game));
                    break;
                case 2:
                    if(!shopIsOpen){
                        showShortToast("敬请期待");
                    }else{
                        setTitleSingle(true, getResString(R.string.str_tab_shop));
                    }
                    break;
                case 3:
                    setTitleSingle(true, getResString(R.string.str_tab_warehouse));
//                RxBus2.getInstance().post(new NotifyEvent(false));
                    break;
                case 4:
                    setTitleSingle(false, getResString(R.string.str_tab_my));

                    break;
            }

            if (index == 4) {
                StatusBarUtil.setStatusBar(this, ContextCompat.getColor(context, R.color.color_1C81E9));
            } else {
                StatusBarUtil.setStatusBar(this, ContextCompat.getColor(context, R.color.white));
            }

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            for (Fragment fragment : fragments) {
                if (fragment.isAdded()) {
                    transaction.hide(fragment);
                }
            }

            if(shopIsOpen || index != 2){
                try {
                    tabLayout.setTabSelect(index);

                    if (fragments[index].isAdded()) {
                        transaction.show(fragments[index]).commitAllowingStateLoss();
                    } else {
                        transaction.add(R.id.cus_tab_fragment, fragments[index]).commitAllowingStateLoss();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public void getBannerSuccess(List<BannerBean> datas) {
        if (datas == null) datas = new ArrayList<>();
        setBanner(datas);
    }

    @Override
    public void getBannerFailed(String msg) {
        showShortToast(msg);
    }


    public void setBanner(List<BannerBean> datas) {
        banner.setAdapter(new BannerImageAdapter<BannerBean>(datas) {
            @Override
            public void onBindView(BannerImageHolder holder, BannerBean data, int position, int size) {
                GlideUtil.loadRoundImage(context, data.getImgUrl(), holder.imageView, 10);
            }
        }).addBannerLifecycleObserver(this)//添加生命周期观察者
          .setIndicator(new CircleIndicator(this));
    }

    @Override
    public void getAppUpdateSuccess(AppUpdateBean data) {
        if(data != null){
            new TipDialog(context).setTitle("更新提示").setContent(data.getFeatures()).show();
        }
    }

    @Override
    public void getAppUpdateFailed(String msg) {
        LogUtil.e(msg);
    }

    @Override
    public void getAdConfigSuccess(ConfigBean data) {
        ConfigInfo.getInstance().setConfigData(data);
        MyApplication.getInstance().setConfigKey();
    }

    @Override
    public void getAdConfigFailed(String msg) {
        LogUtil.e(msg);
    }

    //防止fragment混淆
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
//		super.onSaveInstanceState(outState);
    }

    boolean shopIsOpen;
    @Override
    public void shopIsOpenSuccess(String data) {
        if(TextUtils.equals(data,"1")){
            shopIsOpen = true;
        }
    }

    @Override
    public void shopIsOpenFailed(String msg) {
        LogUtil.e(msg);
    }
}
