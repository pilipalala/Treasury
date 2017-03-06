package com.umeng.soexample.mdesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.umeng.soexample.R;
import com.umeng.soexample.backLayout.BaseActivity;
import com.umeng.soexample.materialdesign.activity.CollapsingToolbarLayoutActivity;
import com.umeng.soexample.materialdesign.activity.TabLayoutActivity;
import com.umeng.soexample.materialdesign.activity.ToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaterialDesignActivity extends BaseActivity {


    @BindView(R.id.btn_toolbar)
    Button btnToolbar;
    @BindView(R.id.btn_appbarLayout)
    Button btnAppbarLayout;
    @BindView(R.id.btn_tabLayout)
    Button btnTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_toolbar, R.id.btn_appbarLayout, R.id.btn_tabLayout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_toolbar:
                startActivity(new Intent(this, ToolBarActivity.class));
                break;
            case R.id.btn_appbarLayout:
                startActivity(new Intent(this, CollapsingToolbarLayoutActivity.class));
                break;
            case R.id.btn_tabLayout:
                startActivity(new Intent(this, TabLayoutActivity.class));
                break;
        }
    }
}
