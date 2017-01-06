package com.umeng.soexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.umeng.soexample.backLayout.BaseActivity;
import com.umeng.soexample.gaodemap.MapLocationActivity;
import com.umeng.soexample.jpush.JPushMainActivity;
import com.umeng.soexample.materialdesign.activity.AppBarLayoutActivity;
import com.umeng.soexample.materialdesign.activity.TabLayoutActivity;
import com.umeng.soexample.materialdesign.activity.ToolBarActivity;
import com.umeng.soexample.share.ShareMainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.btn_jPush)
    Button btnJPush;
    @BindView(R.id.btn_aMap)
    Button btnAMap;
    @BindView(R.id.btn_aShare)
    Button btnAShare;
    @BindView(R.id.btn_aChat)
    Button btnAChat;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.btn_toolbar)
    Button btnToolbar;
    @BindView(R.id.btn_appbarLayout)
    Button btnAppbarLayout;
    @BindView(R.id.btn_tabLayout)
    Button btnTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        text.setText("cehsi");
    }


    @OnClick({R.id.btn_jPush, R.id.btn_aMap, R.id.btn_aShare, R.id.btn_aChat, R.id.btn_toolbar, R.id.btn_appbarLayout,R.id.btn_tabLayout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_jPush:
                startActivity(new Intent(this, JPushMainActivity.class));
                break;
            case R.id.btn_aMap:
                startActivity(new Intent(this, MapLocationActivity.class));
                break;
            case R.id.btn_aShare:
                startActivity(new Intent(this, ShareMainActivity.class));
                break;
            case R.id.btn_aChat:
                break;
            case R.id.btn_toolbar:
                startActivity(new Intent(this, ToolBarActivity.class));
                break;
            case R.id.btn_appbarLayout:
                startActivity(new Intent(this, AppBarLayoutActivity.class));
                break;
            case R.id.btn_tabLayout:
                startActivity(new Intent(this, TabLayoutActivity.class));
                break;
        }
    }

}
