package com.umeng.soexample.share.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.umeng.soexample.backLayout.BaseActivity;
import com.umeng.soexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareMainActivity extends BaseActivity {

    @BindView(R.id.btn_share)
    Button btnShare;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_info)
    Button btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_share, R.id.btn_login,R.id.btn_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_share:
                startActivity(new Intent(this, ShareActivity.class));
                break;
            case R.id.btn_login:
                startActivity(new Intent(this, ShareLoginActivity.class));
                break;
            case R.id.btn_info:
                startActivity(new Intent(this, ShareInfoActivity.class));
                break;
        }
    }
}
