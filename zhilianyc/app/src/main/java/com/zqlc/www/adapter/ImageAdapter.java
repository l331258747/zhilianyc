package com.zqlc.www.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zqlc.www.R;
import com.zqlc.www.util.glide.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    Context mContext;
    List<String> datas;

    public ImageAdapter(Context context, List<String> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder == null) return;
        final String data = datas.get(position);
        if (data == null) return;

        if(!TextUtils.isEmpty(data))
            GlideUtil.loadImage(mContext,data,holder.iv_img);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<String> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_img = itemView.findViewById(R.id.iv_img);
        }
    }
}
