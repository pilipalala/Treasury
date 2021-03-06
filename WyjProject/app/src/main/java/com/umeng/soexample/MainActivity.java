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
import com.umeng.soexample.mdesign.MaterialDesignActivity;
import com.umeng.soexample.object.ObjectActivity;
import com.umeng.soexample.share.activity.ShareMainActivity;

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
    @BindView(R.id.btn_materialdesign)
    Button btnMaterialDesign;
    @BindView(R.id.btn_object)
    Button btnObject;

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


    @OnClick({R.id.btn_jPush, R.id.btn_aMap, R.id.btn_aShare, R.id.btn_aChat, R.id.btn_materialdesign, R.id.btn_object})
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
            case R.id.btn_materialdesign:
                startActivity(new Intent(this, MaterialDesignActivity.class));
                break;
            case R.id.btn_object:
                startActivity(new Intent(this, ObjectActivity.class));
                break;
        }
    }
}
