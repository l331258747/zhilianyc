package com.zlyc.www.view.home;

import com.zlyc.www.R;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.base.BaseFragment;
import com.zlyc.www.util.StatusBarUtil;
import com.zlyc.www.view.home.fragment.GameFragment;
import com.zlyc.www.view.home.fragment.MyFragment;
import com.zlyc.www.view.home.fragment.NewFragment;
import com.zlyc.www.view.home.fragment.ShopFragment;
import com.zlyc.www.view.home.fragment.WarehouseFragment;
import com.zlyc.www.widget.tab.TabItem;
import com.zlyc.www.widget.tab.TabLayout;
import com.zlyc.www.widget.tab.TabView;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends BaseActivity implements TabLayout.OnTabClickListener {

    private TabLayout tabLayout;
    private ArrayList<TabItem> tabItems;

    private Class[] fragmentCls = new Class[5];
    private Fragment[] fragments = new Fragment[5];


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        tabLayout = $(R.id.cus_tab_layout);
    }

    @Override
    public void initData() {

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

        tabItems = new ArrayList<>();
        tabItems.add(new TabItem(R.drawable.tab_new, R.string.str_tab_new, 0, fragmentCls[0]));
        tabItems.add(new TabItem(R.drawable.tab_game, R.string.str_tab_game, 0, fragmentCls[1]));
        tabItems.add(new TabItem(R.drawable.tab_shop, R.string.str_tab_shop, 0, fragmentCls[2]));
        tabItems.add(new TabItem(R.drawable.tab_warehouse, R.string.str_tab_warehouse, 0, fragmentCls[3]));
        tabItems.add(new TabItem(R.drawable.tab_my, R.string.str_tab_my, 0, fragmentCls[4]));

        tabLayout.initData(tabItems, this);

        // 点击事件处理
        onTabItemClick(tabItems.get(0));
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
        int index = tabItems.indexOf(tabItem);

        switch (index) {
            case 0:
                setTitleSingle(true, getResString(R.string.str_tab_new));
                break;
            case 1:
                setTitleSingle(true, getResString(R.string.str_tab_game));
                break;
            case 2:
                setTitleSingle(true, getResString(R.string.str_tab_shop));
                break;
            case 3:
                setTitleSingle(true, getResString(R.string.str_tab_warehouse));
//                RxBus2.getInstance().post(new NotifyEvent(false));
                break;
            case 4:
                setTitleSingle(false, getResString(R.string.str_tab_my));

                break;
        }

        if(index == 4){
            StatusBarUtil.setStatusBar(this, ContextCompat.getColor(context,R.color.color_1C81E9));
        }else{
            StatusBarUtil.setStatusBar(this, ContextCompat.getColor(context,R.color.white));
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : fragments) {
            if (fragment.isAdded()) {
                transaction.hide(fragment);
            }
        }

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
