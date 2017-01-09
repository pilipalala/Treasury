package com.umeng.soexample.share.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.umeng.soexample.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/6.
 */
public class ShareTypeAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<String> styles;

    public ShareTypeAdapter(Context context, ArrayList<String> styles) {
        this.context = context;
        this.styles = styles;
    }

    @Override
    public int getCount() {
        return styles.size();
    }

    @Override
    public Object getItem(int i) {
        return styles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_styleadapter, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(styles.get(i));
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.name)
        TextView name;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
