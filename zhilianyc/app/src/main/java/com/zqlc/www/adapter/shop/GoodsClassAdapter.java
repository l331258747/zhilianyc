package com.zqlc.www.adapter.shop;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.bean.shop.GoodsClassBean;
import com.zqlc.www.util.glide.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GoodsClassAdapter extends RecyclerView.Adapter<GoodsClassAdapter.ViewHolder> {

    Context mContext;
    List<GoodsClassBean> datas;

    public GoodsClassAdapter(Context context, List<GoodsClassBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_goods_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder == null) return;
        final GoodsClassBean data = datas.get(position);
        if (data == null) return;

        holder.tv_name.setText(data.getName());

        if(TextUtils.isEmpty(data.getImgUrl())){
            holder.iv_img.setImageResource(R.mipmap.default_head);
        }else{
            GlideUtil.loadImage(mContext,data.getImgUrl(),holder.iv_img);
        }

        if (mOnItemClickListener != null) {
            holder.ll_parent.setOnClickListener(v -> mOnItemClickListener.onClick(position));
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<GoodsClassBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_img;
        TextView tv_name;
        LinearLayout ll_parent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_img = itemView.findViewById(R.id.iv_img);
            tv_name = itemView.findViewById(R.id.tv_name);
            ll_parent = itemView.findViewById(R.id.ll_parent);
        }
    }

    OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
