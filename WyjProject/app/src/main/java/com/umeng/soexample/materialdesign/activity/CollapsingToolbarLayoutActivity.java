package com.umeng.soexample.materialdesign.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.umeng.soexample.R;
import com.umeng.soexample.materialdesign.adapter.TabViewPagerAdapter;
import com.umeng.soexample.materialdesign.fragment.MyFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/*注意：Toolbar 和CollapsingToolbarLayout 同时设置了title时，不会显示Toolbartitle而是显示CollapsingToolbarLayout 的title，如果要显示Toolbar 的title，你可一在代码中添加如下代码：

collapsingToolbarLayout.setTitle("");
*/



public class CollapsingToolbarLayoutActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private String[] titles = {"菜单", "商家", "评价", "待付款", "待收货", "待评价", "售后"};
//    private int[] images = {R.mipmap.caidan, R.mipmap.shangjia, R.mipmap.pingjia, R.mipmap.daifukuan, R.mipmap.daishouhuo, R.mipmap.daipingjia, R.mipmap.shouhou};
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callapsing_toolbar_layout);
        ButterKnife.bind(this);

        initToolBar();

        initToolbarLayout();

        initTabLayout();

    }

    private void initToolBar() {
        toolbar.setNavigationIcon(R.mipmap.back);//设置导航栏图标左上角
//        toolbar.setLogo(R.mipmap.ic_logo);//设置app logo
//        toolbar.setTitle("主标题");//设置主标题
//        toolbar.setSubtitle("子标题");//设置子标题
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.inflateMenu(R.menu.base_toolbar_menu);//设置右上角的填充菜单
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_search) {
                    Toast.makeText(CollapsingToolbarLayoutActivity.this, "无线", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_notification) {
                    Toast.makeText(CollapsingToolbarLayoutActivity.this, "星星", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item1) {
                    Toast.makeText(CollapsingToolbarLayoutActivity.this, "item_01", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item2) {
                    Toast.makeText(CollapsingToolbarLayoutActivity.this, "item_02", Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(CollapsingToolbarLayoutActivity.this, "返回", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initToolbarLayout() {
        collapsingToolbarLayout.setTitle("CollapsingToolbarLayout");
        //通过CollapsingToolbarLayout修改字体颜色
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色
    }

    private void initTabLayout() {
        fragments = new ArrayList<>();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        for (int i = 0; i < titles.length; i++) {
            tablayout.addTab(tablayout.newTab().setText(titles[i])/*.setIcon(images[i])*/);
            fragments.add(new MyFragment(titles[i]));
        }
        tablayout.setupWithViewPager(viewpager);
        viewpager.setAdapter(new TabViewPagerAdapter(getSupportFragmentManager(), fragments, titles));
    }
}
