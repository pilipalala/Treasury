package com.umeng.soexample.share.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.soexample.backLayout.BaseActivity;
import com.bumptech.glide.Glide;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.R;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoDetailActivity extends BaseActivity {

    @BindView(R.id.getbtn)
    Button getbtn;
    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.result)
    TextView result;
    private SHARE_MEDIA share_media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail);
        ButterKnife.bind(this);
        share_media = (SHARE_MEDIA) getIntent().getSerializableExtra("type");
    }

    @OnClick(R.id.getbtn)
    public void onClick() {

        UMShareAPI.get(this).getPlatformInfo(this, share_media, new UMAuthListener() {
            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Glide.with(InfoDetailActivity.this).load(map.get("iconurl")).into(icon);
                name.setText(map.get("name"));

                String temp = "";
                for (String key : map.keySet()) {
                    temp = temp + key + " : " + map.get(key) + "\n";
                }
                result.setText(temp);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                result.setText("错误" + throwable.getMessage());
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }


}
