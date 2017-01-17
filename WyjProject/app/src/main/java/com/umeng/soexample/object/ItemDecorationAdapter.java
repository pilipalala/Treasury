package com.umeng.soexample.object;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/14.
 */
public class ItemDecorationAdapter extends RecyclerView.Adapter<ItemDecorationAdapter.MyViewHolser> {
    private final Context context;
    private LayoutInflater inflater;
    private List<WaitMVBean.DataBean.ComingBean> data;

    public ItemDecorationAdapter(Context context,List<WaitMVBean.DataBean.ComingBean> data) {
        this.context = context;
        this.data = data;
        Log.e("ItemDecorationAdapter", "ItemDecorationAdapter: "+data.size() );
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolser onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_item_decoration, null);
        return new MyViewHolser(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolser holder, int position) {


        //注：当你发下图片无法打开是，做个字符串替换即可
        String imagUrl = data.get(position).getImg();
        String newImagUrl = imagUrl.replaceAll("w.h", "400.640");
        Glide.with(context).load(newImagUrl).into(holder.image);
        holder.mvName.setText(data.get(position).getNm());
        holder.mvDec.setText(data.get(position).getScm());
        holder.mvDate.setText(data.get(position).getShowInfo());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    class MyViewHolser extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.mv_name)
        TextView mvName;
        @BindView(R.id.tv_people)
        TextView tvPeople;
        @BindView(R.id.tv_professional)
        TextView tvProfessional;
        @BindView(R.id.mv_dec)
        TextView mvDec;
        @BindView(R.id.mv_date)
        TextView mvDate;

        public MyViewHolser(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
