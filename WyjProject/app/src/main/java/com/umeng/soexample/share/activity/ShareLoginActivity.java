package com.umeng.soexample.share.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.R;
import com.umeng.soexample.backLayout.BaseActivity;
import com.umeng.soexample.share.adapter.ShareLoginAdapter;
import com.umeng.soexample.utils.AppData;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareLoginActivity extends BaseActivity {

    @BindView(R.id.list)
    ListView list;
    ShareLoginAdapter adapter;
    String[] titles = AppData.login_titles;
    int[] icons = AppData.login_icon;
    private SHARE_MEDIA[] listMedia = {SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.SINA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_login);
        ButterKnife.bind(this);

        adapter = new ShareLoginAdapter(this, titles, icons, listMedia);
        list.setAdapter(adapter);
        UMShareAPI.get(this).fetchAuthResultWithBundle(this, savedInstanceState, new UMAuthListener() {
            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                Toast.makeText(getApplicationContext(), "onRestoreInstanceState Authorize succeed", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                Toast.makeText(getApplicationContext(), "onRestoreInstanceState Authorize onError", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                Toast.makeText(getApplicationContext(), "onRestoreInstanceState Authorize onCancel", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
