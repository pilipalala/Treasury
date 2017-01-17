package com.umeng.soexample.share.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.umeng.soexample.backLayout.BaseActivity;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.R;
import com.umeng.soexample.share.adapter.ShareAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareInfoActivity extends BaseActivity {

    @BindView(R.id.list)
    ListView list;
    String[] titles = {"微信", "QQ", "微博"};
    int[] icons = {R.mipmap.icon_wechat, R.mipmap.icon_qq, R.mipmap.icon_sina};
    private ArrayList<SHARE_MEDIA> share_media = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_info);
        ButterKnife.bind(this);
        initMedia();
        list.setAdapter(new ShareAdapter(this, titles, icons));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(ShareInfoActivity.this, InfoDetailActivity.class);
                intent.putExtra("type", share_media.get(position));
                startActivity(intent);
            }
        });
    }

    private void initMedia() {
        share_media.add(SHARE_MEDIA.WEIXIN);
        share_media.add(SHARE_MEDIA.QQ);
        share_media.add(SHARE_MEDIA.SINA);
    }
}
