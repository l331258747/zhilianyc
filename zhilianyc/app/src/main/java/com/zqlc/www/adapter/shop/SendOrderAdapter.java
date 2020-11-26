package com.zqlc.www.adapter.shop;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.bean.shop.GoodsDetailsBean;
import com.zqlc.www.util.DecimalUtil;
import com.zqlc.www.util.StringUtils;
import com.zqlc.www.util.glide.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SendOrderAdapter extends RecyclerView.Adapter<SendOrderAdapter.ViewHolder> {

    Context mContext;
    List<GoodsDetailsBean> datas;
    int num;

    public SendOrderAdapter(Context context, GoodsDetailsBean data,int num) {
        mContext = context;
        this.datas = new ArrayList<>();
        this.datas.add(data);
        this.num = num;
    }

    @NonNull
    @Override
    public SendOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_send_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SendOrderAdapter.ViewHolder holder, int position) {
        if (holder == null) return;
        final GoodsDetailsBean data = datas.get(position);
        if (data == null) return;

        holder.tv_name.setText(data.getName());
        holder.tv_num.setText("数量："+num+"件");
        holder.tv_price.setText("单价："+ StringUtils.getStringNum(data.getPrice()) +"京豆");
        holder.tv_price_goods.setText(StringUtils.getStringNum(DecimalUtil.multiply(num,data.getPrice())) + "京豆");
        holder.tv_freight.setText(StringUtils.getStringNum(data.getPostage())  + "京豆");
        float totalSum = DecimalUtil.add(data.getPostage(),DecimalUtil.multiply(data.getPrice(),num));
        holder.tv_price_all.setText(StringUtils.getStringNum(totalSum) + "京豆");

        if(TextUtils.isEmpty(data.getImgUrl())){
            holder.iv_img.setImageResource(R.color.color_cc);
        }else{
            GlideUtil.loadRoundImage(mContext,data.getImgUrl(),holder.iv_img,5);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_img;
        TextView tv_name,tv_num,tv_price,tv_price_goods,tv_freight,tv_price_all;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_img = itemView.findViewById(R.id.iv_img);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_num = itemView.findViewById(R.id.tv_num);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_price_goods = itemView.findViewById(R.id.tv_price_goods);
            tv_freight = itemView.findViewById(R.id.tv_freight);
            tv_price_all = itemView.findViewById(R.id.tv_price_all);
        }
    }
}
