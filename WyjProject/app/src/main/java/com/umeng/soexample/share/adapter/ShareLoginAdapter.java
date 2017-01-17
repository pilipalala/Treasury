package com.umeng.soexample.share.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.R;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/9.
 */
public class ShareLoginAdapter extends BaseAdapter {
    private Context mContext;
    private SHARE_MEDIA[] share_media;
    private Activity mActivity;
    private String[] titles;
    private int[] icons;

    public ShareLoginAdapter(Context mContext, String[] titles, int[] icons, SHARE_MEDIA[] share_media) {
        this.mContext = mContext.getApplicationContext();
        this.titles = titles;
        this.icons = icons;
        this.mActivity = (Activity) mContext;
        this.share_media = share_media;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_loginadapter, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final boolean isauth = UMShareAPI.get(mContext).isAuthorize(mActivity, share_media[i]);
        holder.name.setText(titles[i]);
        holder.adapterImage.setImageResource(icons[i]);
        if (isauth) {
            holder.authButton.setText("删除授权");
        } else {
            holder.authButton.setText("授权");
        }
        holder.authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isauth) {
                    UMShareAPI.get(mContext).deleteOauth(mActivity, share_media[i], authListener);
                } else {
                    UMShareAPI.get(mContext).doOauthVerify(mActivity, share_media[i], authListener);
//                    UMShareAPI.get(mContext).getPlatformInfo(mActivity, share_media[i], authListener);
                }
            }
        });
        if (i == titles.length - 1) {
            holder.divider.setVisibility(View.GONE);
        } else {
            holder.divider.setVisibility(View.VISIBLE);
        }
        return view;
    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(mContext, "成功了", Toast.LENGTH_LONG).show();
            notifyDataSetChanged();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(mContext, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    static class ViewHolder {
        @BindView(R.id.adapter_image)
        ImageView adapterImage;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.auth_button)
        TextView authButton;
        @BindView(R.id.divider)
        View divider;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
