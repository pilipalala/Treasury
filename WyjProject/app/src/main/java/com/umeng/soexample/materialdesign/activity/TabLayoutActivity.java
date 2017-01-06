package com.umeng.soexample.materialdesign.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.umeng.soexample.R;
import com.umeng.soexample.backLayout.BaseActivity;
import com.umeng.soexample.materialdesign.adapter.TabViewPagerAdapter;
import com.umeng.soexample.materialdesign.fragment.MyFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabLayoutActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private String[] titles = {"菜单", "商家", "评价", "待付款", "待收货", "待评价", "售后"};
    private int[] images = {R.mipmap.caidan, R.mipmap.shangjia, R.mipmap.pingjia, R.mipmap.daifukuan, R.mipmap.daishouhuo, R.mipmap.daipingjia, R.mipmap.shouhou};
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        for (int i = 0; i < titles.length; i++) {
            tablayout.addTab(tablayout.newTab().setText(titles[i]).setIcon(images[i]));
            fragments.add(new MyFragment(titles[i]));
        }
        tablayout.setupWithViewPager(viewpager);
        viewpager.setAdapter(new TabViewPagerAdapter(getSupportFragmentManager(), fragments, titles));
    }
}
