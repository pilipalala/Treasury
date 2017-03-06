package com.umeng.soexample.object;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.umeng.soexample.R;
import com.umeng.soexample.backLayout.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends BaseActivity {

    @BindView(R.id.rv_recycleview)
    RecyclerView rvRecycleview;
    @BindView(R.id.spl_refresh)
    SwipeRefreshLayout splRefresh;
    ArrayList<CardInfo> list = new ArrayList<CardInfo>();
    PullMoreRecyclerAdapter adapter;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        adapter = new PullMoreRecyclerAdapter(this, getData());
        rvRecycleview.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvRecycleview.setLayoutManager(layoutManager);

        splRefresh.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary, R.color.umeng_socialize_color_group,
                R.color.colorPrimary, R.color.colorPrimaryDark);//设置下拉圆圈上的颜色
        splRefresh.setDistanceToTriggerSync(400);//设置手指在屏幕下拉多少距离会触发下拉刷新
        splRefresh.setProgressBackgroundColorSchemeColor(Color.GRAY); // 设定下拉圆圈的背景
        splRefresh.setSize(SwipeRefreshLayout.LARGE);// 设置圆圈的大小
        splRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        getData();
                        adapter.notifyDataSetChanged();
                        splRefresh.setEnabled(true);//禁止使用刷新通知
                        splRefresh.setRefreshing(false);// 停止刷新
                    }
                }, 3000);
            }
        });
        /**
         * 监听RecyclerView的滚动
         */
        rvRecycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;

            /**
             * 监听滑动状态的变化
             * 滚动状态变化时回调
             *
             * @param recyclerView  当前在滚动的RecyclerView
             * @param newState 目前的状态
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!splRefresh.isRefreshing()) {
                    /**
                     * //静止,没有滚动
                     public static final int SCROLL_STATE_IDLE = 0;

                     //正在被外部拖拽,一般为用户正在用手指滚动
                     public static final int SCROLL_STATE_DRAGGING = 1;

                     //自动滚动开始
                     public static final int SCROLL_STATE_SETTLING = 2;
                     * */
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                        adapter.setMoreStatus(PullMoreRecyclerAdapter.LOADING_MORE);
                        splRefresh.setEnabled(false);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getData();
                                splRefresh.setEnabled(true);
                                adapter.setMoreStatus(PullMoreRecyclerAdapter.PULLUP_LOAD_MORE);
                                adapter.notifyDataSetChanged();
                            }
                        }, 1000);
                    }
                }
            }

            /**
             * 滚动时回调
             *
             * @param recyclerView  当前在滚动的RecyclerView
             * @param dx 水平滚动距离
             * @param dy 垂直滚动距离
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });

        adapter.setOnItemClickListener(new PullMoreRecyclerAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClick(View itemView, int position) {
                Toast.makeText(RecyclerViewActivity.this, position + "\n" + list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<CardInfo> getData() {

        for (int i = 0; i < 10; i++) {
            CardInfo info = new CardInfo();
            info.setContent("ContentContentContentContentContentContent");
            info.setTitle("Title" + i);
            list.add(info);
        }
        return list;
    }
}
