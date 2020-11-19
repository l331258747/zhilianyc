package com.zlyc.www.adapter.shop;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.bean.shop.OrderListBean;
import com.zlyc.www.util.glide.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {

    Context mContext;
    List<OrderListBean> datas;

    public OrderListAdapter(Context context, List<OrderListBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_order_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder == null) return;
        final OrderListBean data = datas.get(position);
        if (data == null) return;

        holder.tv_order_no.setText(data.getId());
        holder.tv_status.setText(data.getTypeStr());
        holder.tv_name.setText(data.getName());
        holder.tv_num.setText(data.getNumStr());
        holder.tv_price.setText(data.getPriceStr());
        holder.tv_price_all.setText(data.getTotalSumStr());

        holder.ll_btn.setVisibility(View.GONE);
        holder.btn_text.setVisibility(View.GONE);
        if(data.getType() == 1){
            holder.ll_btn.setVisibility(View.VISIBLE);
        }else if(data.getType() == 2){
            holder.btn_text.setVisibility(View.VISIBLE);
            holder.btn_text.setText("等待商家发货");
        }else if(data.getType() == 3){
            holder.btn_text.setVisibility(View.VISIBLE);
            holder.btn_text.setText("等待客户收货");
        }

        if(TextUtils.isEmpty(data.getImgUrl())){
            holder.iv_img.setImageResource(R.mipmap.default_head);
        }else{
            GlideUtil.loadRoundImage(mContext,data.getImgUrl(),holder.iv_img,5);
        }

        if (mOnItemClickListener != null) {
            holder.btn_cancel.setOnClickListener(v -> mOnItemClickListener.onCancelClick(position));
            holder.btn_pay.setOnClickListener(v -> mOnItemClickListener.onPayClick(position));
            holder.cl_parent.setOnClickListener( v -> mOnItemClickListener.onItemClick(position));
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<OrderListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_order_no,tv_status,tv_name,tv_num,tv_price,tv_price_all,btn_text,btn_cancel,btn_pay;
        ImageView iv_img;
        ConstraintLayout cl_parent;
        LinearLayout ll_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_order_no = itemView.findViewById(R.id.tv_order_no);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_num = itemView.findViewById(R.id.tv_num);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_price_all = itemView.findViewById(R.id.tv_price_all);
            btn_text = itemView.findViewById(R.id.btn_text);
            btn_cancel = itemView.findViewById(R.id.btn_cancel);
            btn_pay = itemView.findViewById(R.id.btn_pay);
            iv_img = itemView.findViewById(R.id.iv_img);
            ll_btn = itemView.findViewById(R.id.ll_btn);
            cl_parent = itemView.findViewById(R.id.cl_parent);

        }
    }

    OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onCancelClick(int position);
        void onPayClick(int position);
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
