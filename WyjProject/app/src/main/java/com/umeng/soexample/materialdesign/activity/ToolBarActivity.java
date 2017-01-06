package com.umeng.soexample.materialdesign.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.umeng.soexample.R;
import com.umeng.soexample.backLayout.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToolBarActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar_layout);
        ButterKnife.bind(this);

        toolbar.setNavigationIcon(R.mipmap.back);//设置导航栏图标左上角
//        toolbar.setLogo(R.mipmap.ic_logo);//设置app logo
        toolbar.setTitle("主标题");//设置主标题
        toolbar.setSubtitle("子标题");//设置子标题

        toolbar.inflateMenu(R.menu.base_toolbar_menu);//设置右上角的填充菜单
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_search) {
                    Toast.makeText(ToolBarActivity.this, "无线", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_notification) {
                    Toast.makeText(ToolBarActivity.this, "星星", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item1) {
                    Toast.makeText(ToolBarActivity.this, "item_01", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item2) {
                    Toast.makeText(ToolBarActivity.this, "item_02", Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ToolBarActivity.this, "导航栏右上角", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
