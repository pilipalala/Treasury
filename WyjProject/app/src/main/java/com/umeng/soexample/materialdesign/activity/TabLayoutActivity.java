package com.umeng.soexample.materialdesign.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.umeng.soexample.R;
import com.umeng.soexample.backLayout.BaseActivity;
import com.umeng.soexample.materialdesign.adapter.TabViewPagerAdapter;
import com.umeng.soexample.materialdesign.fragment.MyFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabLayoutActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private String[] titles = {"菜单", "商家", "评价", "待付款", "待收货", "待评价", "售后"};
    private int[] images = {R.mipmap.caidan, R.mipmap.shangjia, R.mipmap.pingjia, R.mipmap.daifukuan, R.mipmap.daishouhuo, R.mipmap.daipingjia, R.mipmap.shouhou};
    private ArrayList<Fragment> fragments;
    private int TotalCount = 0;
    private int gcCount = 5;

    /**
     * 1、为了ToolBar可以滚动，CoordinatorLayout里面,放一个带有可滚动的View.本例,放的是ViewPager,而ViewPager里面是放了RecylerView的,即是可以滚动的View。
     * <p>
     * 2、CoordinatorLayout中的android:fitsSystemWindows="true"是必写的,不然你的Toolbar会与状态栏重叠在一起；
     * <p>
     * 3、CoordinatorLayout包含的子视图中带有滚动属性的View需要设置app:layout_behavior属性，如ViewPager控件；
     * <p>
     * 4、app:layout_behavior="@string/appbar_scrolling_view_behavior"
     * <p>
     * android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
     * <p>
     * app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
     * <p>
     * 5、为了使得Toolbar有滑动效果，必须做到如下三点:
     * 1、CoordinatorLayout作为布局的父布局容器。
     * 2、给需要滑动的组件(本身没有滑动功能)设置 app:layout_scrollFlags=”scroll|enterAlways” 属性。
     * 3、给滑动的组件(本身有滑动功能)设置app:layout_behavior属性
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        initToolBar();


        for (int i = 0; i < titles.length; i++) {
            tablayout.addTab(tablayout.newTab().setText(titles[i]).setIcon(images[i]));
            fragments.add(new MyFragment(titles[i]));
        }
        tablayout.setupWithViewPager(viewpager);
        viewpager.setAdapter(new TabViewPagerAdapter(getSupportFragmentManager(), fragments, titles));
    }

    private void initToolBar() {
        //如果你的主题没有设置NoActionbar且没有调用supportRequestWindowFeature(Window.FEATURE_NO_TITLE);下面这句话报错
//        setSupportActionBar(toolbar);
        //如果没有调用getSupportActionBar().setDisplayShowTitleEnabled(false);主标题会显示默认的app名称
//        if (getSupportActionBar() != null)
//            getSupportActionBar().setDisplayShowTitleEnabled(false);

//        toolbar.setNavigationIcon(R.mipmap.back);//设置导航栏图标左上角
//        toolbar.setLogo(R.mipmap.ic_logo);//设置app logo
        toolbar.setTitle("主标题");//设置主标题
        toolbar.setSubtitle("子标题");//设置子标题
        toolbar.inflateMenu(R.menu.base_toolbar_menu);//设置右上角的填充菜单
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_search) {
                    Toast.makeText(TabLayoutActivity.this, "无线", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_notification) {
                    Toast.makeText(TabLayoutActivity.this, "星星", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item1) {
                    Toast.makeText(TabLayoutActivity.this, "item_01", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item2) {
                    Toast.makeText(TabLayoutActivity.this, "item_02", Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count();//提醒回收垃圾
                Snackbar.make(view, "退出？", Snackbar.LENGTH_SHORT).setAction("退出", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                }).show();
            }
        });
    }



    private void count() {
        TotalCount++;
        if (TotalCount >= gcCount) {
            System.gc();
            TotalCount = 0;
        }
    }

    @OnClick(R.id.fab)
    public void onClick() {
        Toast.makeText(TabLayoutActivity.this, "悬浮按钮", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //在这里绑定你的menu
        getMenuInflater().inflate(R.menu.base_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 这里是处理menu中选中item的地方
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_item1:
                Toast.makeText(TabLayoutActivity.this, "item11", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_item2:
                Toast.makeText(TabLayoutActivity.this, "item22", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
