package com.zlyc.www.adapter.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.bean.shop.HotGoodsBean;
import com.zlyc.www.util.glide.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HotGoodsAdapter extends RecyclerView.Adapter<HotGoodsAdapter.ViewHolder> {

    Context mContext;
    List<HotGoodsBean> datas;

    public HotGoodsAdapter(Context context, List<HotGoodsBean> datas) {
        mContext = context;
        this.datas = datas;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_hot_goods, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (holder == null) return;
        final HotGoodsBean data = datas.get(position);
        if (data == null) return;

        holder.tv_content.setText(data.getName());
        holder.tv_price.setText(data.getPriceStr());
        holder.tv_num.setText(data.getNumStr());

        GlideUtil.loadTopImage(mContext,data.getImgUrl(),holder.iv_img,20);

        if (mOnItemClickListener != null) {
            holder.view_card.setOnClickListener(v -> mOnItemClickListener.onClick(position));

        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<HotGoodsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View view_card;
        ImageView iv_img;
        TextView tv_content,tv_price,tv_num;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view_card = itemView.findViewById(R.id.view_card);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_num = itemView.findViewById(R.id.tv_num);
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
