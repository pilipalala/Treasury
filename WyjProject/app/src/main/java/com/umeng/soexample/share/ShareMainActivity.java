package com.umeng.soexample.share;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.R;
import com.umeng.soexample.backLayout.BaseActivity;
import com.umeng.soexample.utils.AppData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareMainActivity extends BaseActivity {

    @BindView(R.id.list)
    ListView list;
    String[] titles = AppData.share_titles;
    int[] icons = AppData.share_icon;

    ArrayList<SHARE_MEDIA> share_media = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_main);
        ButterKnife.bind(this);
        initMedia();
        list.setAdapter(new ShareAdapter(this, titles, icons));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(ShareMainActivity.this,ShareDetailActivity.class);
                intent.putExtra("type",share_media.get(position));
                startActivity(intent);
            }
        });
    }

    private void initMedia() {
        share_media.add(SHARE_MEDIA.WEIXIN);
        share_media.add(SHARE_MEDIA.WEIXIN_CIRCLE);
        share_media.add(SHARE_MEDIA.WEIXIN_FAVORITE);
        share_media.add(SHARE_MEDIA.QQ);
        share_media.add(SHARE_MEDIA.QZONE);
        share_media.add(SHARE_MEDIA.SINA);
    }
}
