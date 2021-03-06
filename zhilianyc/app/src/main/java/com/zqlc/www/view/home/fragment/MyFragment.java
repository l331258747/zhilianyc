package com.zqlc.www.view.home.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.adapter.my.MyTabAdapter;
import com.zqlc.www.base.BaseFragment;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.bean.login.MineBean;
import com.zqlc.www.bean.my.MyTabBean;
import com.zqlc.www.constant.Constant;
import com.zqlc.www.dialog.TextDialog;
import com.zqlc.www.mvp.my.MyInfoContract;
import com.zqlc.www.mvp.my.MyInfoPresenter;
import com.zqlc.www.mvp.my.RealNameStatusContract;
import com.zqlc.www.mvp.my.RealNameStatusPresenter;
import com.zqlc.www.util.glide.GlideUtil;
import com.zqlc.www.util.rxbus.RxBus2;
import com.zqlc.www.util.rxbus.busEvent.InformationEvent;
import com.zqlc.www.view.account.MyBillActivity;
import com.zqlc.www.view.my.AccountActivity;
import com.zqlc.www.view.my.MyTeamActivity;
import com.zqlc.www.view.news.MyNoticeActivity;
import com.zqlc.www.view.news.MyStudyActivity;
import com.zqlc.www.view.otc.MyOtcListActivity;
import com.zqlc.www.view.otc.OtcMarkerActivity;
import com.zqlc.www.view.security.SecurityActivity;
import com.zqlc.www.view.team.InvitationActivity;
import com.zqlc.www.view.user.ExcitationActivity;
import com.zqlc.www.view.user.MyTaskActivity;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.reactivex.disposables.Disposable;

public class MyFragment extends BaseFragment implements View.OnClickListener, MyInfoContract.View, RealNameStatusContract.View {

    RecyclerView recyclerView;
    MyTabAdapter mAdapter;
    List<MyTabBean> datas;

    SwipeRefreshLayout swipe;

    ImageView iv_head;
    TextView tv_name, tv_UID, tv_data_all_num, tv_data_today_num, tv_data_use_num, tv_data_contribution_num, tv_data_labour_num, tv_region, tv_authentication;
    TextView tv_data_contribution_level,tv_data_labour_num_level;

    View view_title;

    MyInfoPresenter mPresenter;
    RealNameStatusPresenter rnsPresenter;

    MineBean data;

    Disposable disposable;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {

        tv_data_contribution_level = $(R.id.tv_data_contribution_level);
        tv_data_labour_num_level = $(R.id.tv_data_labour_num_level);
        iv_head = $(R.id.iv_head);
        tv_name = $(R.id.tv_name);
        tv_UID = $(R.id.tv_UID);
        tv_data_all_num = $(R.id.tv_data_all_num);
        tv_data_today_num = $(R.id.tv_data_today_num);
        tv_data_use_num = $(R.id.tv_data_use_num);
        tv_data_contribution_num = $(R.id.tv_data_contribution_num);
        tv_data_labour_num = $(R.id.tv_data_labour_num);
        tv_region = $(R.id.tv_region);
        tv_authentication = $(R.id.tv_authentication);

        tv_region.setVisibility(View.GONE);
        tv_authentication.setVisibility(View.GONE);

        view_title = $(R.id.view_title);

        initSwipe();
        initRecycler();

        view_title.setOnClickListener(this);
    }

    private void initSwipe() {
        swipe = $(R.id.swipe);
        swipe.setColorSchemeResources(R.color.color_1C81E9);
        swipe.setOnRefreshListener(() -> {
            mPresenter.mine(MySelfInfo.getInstance().getUserId(),false);
            rnsPresenter.realNameStatus(MySelfInfo.getInstance().getUserId());
        });
    }

    @Override
    public void initData() {
        mPresenter = new MyInfoPresenter(context, this);
        mPresenter.mine(MySelfInfo.getInstance().getUserId(),false);
        rnsPresenter = new RealNameStatusPresenter(context, this);
        rnsPresenter.realNameStatus(MySelfInfo.getInstance().getUserId());

        disposable = RxBus2.getInstance().toObservable(InformationEvent.class, realNameStatusEvent -> {
            rnsPresenter.realNameStatus(MySelfInfo.getInstance().getUserId());
        });
    }

    //初始化recyclerview
    public void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 4);
        recyclerView.setLayoutManager(gridLayoutManager);
        MyTabBean myTabBean = new MyTabBean();
        datas = myTabBean.getDatas();
        mAdapter = new MyTabAdapter(context, datas);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(position -> {
            MyTabBean item = datas.get(position);
            switch (item.getId()) {
                case Constant.MY_TAB_TEAM:
                    startActivity(new Intent(context, MyTeamActivity.class));
                    break;
                case Constant.MY_TAB_SECURITY:
                    startActivity(new Intent(context, SecurityActivity.class));
                    break;
                case Constant.MY_TAB_ORDER:
                    startActivity(new Intent(context, MyBillActivity.class));
                    break;
                case Constant.MY_TAB_TRANSACTION_DTS:
                    startActivity(new Intent(context, MyOtcListActivity.class));
                    break;
                case Constant.MY_TAB_INVITAT:
                    startActivity(new Intent(context, InvitationActivity.class));
                    break;
                case Constant.MY_TAB_STUDIO:
                    startActivity(new Intent(context, MyStudyActivity.class));
                    break;
                case Constant.MY_TAB_NOTIFY:
                    startActivity(new Intent(context, MyNoticeActivity.class));
                    break;
                case Constant.MY_TAB_CHANGE:
                    startActivity(new Intent(context, OtcMarkerActivity.class));
                    break;
                case Constant.MY_TAB_CLOUD:
                case Constant.MY_TAB_SHOP:
                    new TextDialog(context).setContent("功能完善中，敬请期待！").show();
                    break;
                case Constant.MY_TAB_RED_PACKAGE:
                    startActivity(new Intent(context,ExcitationActivity.class));
                    break;
                case Constant.MY_TAB_TASK:
                    Intent intent = new Intent(context, MyTaskActivity.class);

                    if(data != null){
                        intent.putExtra("beans",data.getBeansStr());
                        intent.putExtra("todayBeans",data.getTodayBeans());
                        intent.putExtra("sellableBeans",data.getSellableBeans());
                        startActivity(intent);
                    }

                    break;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_title:
                startActivity(new Intent(context, AccountActivity.class));
                break;
        }
    }

    @Override
    public void mineSuccess(MineBean data) {
        swipe.setRefreshing(false);

        this.data = data;

        if (TextUtils.isEmpty(data.getHeadImg())) {
            iv_head.setImageResource(R.mipmap.default_head);
        } else {
            GlideUtil.loadCircleImage(context, data.getHeadImg(), iv_head);
        }

        tv_name.setText(data.getNickName());
        tv_UID.setText("UID:" + MySelfInfo.getInstance().getUserId());
        tv_data_all_num.setText(data.getBeansStr());
        tv_data_today_num.setText(data.getTodayBeans());
        tv_data_use_num.setText(data.getSellableBeans());
        tv_data_contribution_num.setText(data.getContribution());
        tv_data_labour_num.setText(data.getLabor());
        tv_data_contribution_level.setText(data.getContributionRankStr());
        tv_data_labour_num_level.setText(data.getExtraLabor());

        if (!TextUtils.isEmpty(data.getCityPartnerName())) {
            tv_region.setText(data.getCityPartnerName());
            tv_region.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void mineFailed(String msg) {
        swipe.setRefreshing(false);
        showShortToast(msg);
    }

    @Override
    public void realNameStatusSuccess(String data) {
        tv_authentication.setVisibility(View.VISIBLE);
        //0 审核不通过 1 已实名 2 审核中 3 未认证

        if (TextUtils.equals(data, "1")) {
            tv_authentication.setText("已认证");
            tv_authentication.setBackgroundResource(R.drawable.btn_61b53f_r1);
        } else if (TextUtils.equals(data, "2")) {
            tv_authentication.setText("审核中");
            tv_authentication.setBackgroundResource(R.drawable.btn_61b53f_r1);
        } else if (TextUtils.equals(data, "0")) {
            tv_authentication.setText("审核不通过");
            tv_authentication.setBackgroundResource(R.drawable.btn_red_r1);
        } else {
            tv_authentication.setText("未认证");
            tv_authentication.setBackgroundResource(R.drawable.btn_red_r1);
        }
    }

    @Override
    public void realNameStatusFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
