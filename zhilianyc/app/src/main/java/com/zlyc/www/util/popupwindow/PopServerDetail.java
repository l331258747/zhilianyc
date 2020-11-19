package com.zlyc.www.util.popupwindow;

import android.view.View;
import android.view.WindowManager;

/**
 * Created by LGQ
 * Time: 2018/12/14
 * Function:
 */

public class PopServerDetail extends BackgroundDarkPopupWindow {
    public PopServerDetail(View contentView, View parentView) {
        super(parentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }
//    private View contentView;
//    private Activity context;
//
//    ImageView tv_pop_close;
//    TextView tv_empty;
//    RecyclerView recyclerView;
//
//    PopServerDetailAdapter mAdapter;
//    List<ServerItem> serverItems;
//
//    public PopServerDetail(final Activity context, View parentView, List<ServerItem> serverItems) {
//        super(parentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        contentView = inflater.inflate(R.layout.popup_server_detail, null);
//        this.context = context;
//        this.serverItems = serverItems;
//
//        recyclerView = contentView.findViewById(R.id.recycler_view);
//        tv_pop_close = contentView.findViewById(R.id.tv_pop_close);
//        tv_empty = contentView.findViewById(R.id.tv_empty);
//
//
//        tv_pop_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismissPopupWindow();
//            }
//        });
//
//        this.setContentView(contentView);
//        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
//        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
//
//        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0000000000);
//        this.setBackgroundDrawable(dw);
//        contentView.setFocusable(true);
//        contentView.setFocusableInTouchMode(true);
//        this.setOutsideTouchable(true);
//        this.setFocusable(true);
//
//        initList();
//        initEmpty();
//    }
//
//    private void initEmpty(){
//        tv_empty.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogUtil.getInstance().getDefaultDialog(context, "是否确认清除明细？", new DialogUtil.DialogCallBack() {
//                    @Override
//                    public void exectEvent(DialogInterface alterDialog) {
//                        serverItems.clear();
//                        RxBus2.getInstance().post(new ServerDetailEvent());
//                        RxBus2.getInstance().post(new ServerPriceTotalEvent());
//                        dismissPopupWindow();
//                        alterDialog.dismiss();
//                    }
//                }).show();
//            }
//        });
//    }
//
//    private void initList() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        mAdapter = new PopServerDetailAdapter(context, serverItems);
//        recyclerView.setAdapter(mAdapter);
//
//        mAdapter.setOnItemClickListener(new PopServerDetailAdapter.OnItemClickListener() {
//            @Override
//            public void onCancelClick(int position) {
//                serverItems.remove(position);
//                mAdapter.notifyDataSetChanged();
//                RxBus2.getInstance().post(new ServerDetailEvent());
//                RxBus2.getInstance().post(new ServerPriceTotalEvent());
//                if(serverItems.size() == 0){
//                    dismissPopupWindow();
//                    return;
//                }
//            }
//
//            @Override
//            public void onNumClick(int position, int num) {
//                serverItems.get(position).setServeNum(num);
//                if(num == 0){
//                    serverItems.remove(position);
//                    mAdapter.notifyDataSetChanged();
//                    RxBus2.getInstance().post(new ServerDetailEvent());
//                }
//                RxBus2.getInstance().post(new ServerPriceTotalEvent());
//                if(serverItems.size() == 0){
//                    dismissPopupWindow();
//                    return;
//                }
//            }
//        });
//    }
//
//    public void showPopupWindow(View parent) {
//        if (serverItems == null || serverItems.size() == 0)
//            return;
//        if (!this.isShowing()) {
//            setDarkStyle(-1);
//            setDarkColor(Color.parseColor("#a0000000"));
//            resetDarkPosition();
//            darkAbove(parent);
//
//            showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, AppUtils.getNavigationBarHeight(context) + AppUtils.dip2px(50));
//        }
//    }
//
//    public void dismissPopupWindow() {
//        if (this.isShowing())
//            this.dismiss();
//    }
}
