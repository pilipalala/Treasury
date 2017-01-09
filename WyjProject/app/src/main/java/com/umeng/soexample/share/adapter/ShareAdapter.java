package com.umeng.soexample.share.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.soexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/6.
 */
public class ShareAdapter extends BaseAdapter {
    private final Context context;
    private String[] titles;
    private int[] icons;

    public ShareAdapter(Context context, String[] titles, int[] icons) {
        this.context = context;
        this.titles = titles;
        this.icons = icons;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int i) {
        return titles[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_shareadapter, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(titles[i]);
        holder.adapterImage.setImageResource(icons[i]);
        return view;
    }


    static class ViewHolder {
        @BindView(R.id.adapter_image)
        ImageView adapterImage;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.divider)
        View divider;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
