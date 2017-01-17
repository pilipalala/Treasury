package com.umeng.soexample.object;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.soexample.backLayout.BaseActivity;
import com.umeng.soexample.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JiugonggeAcivity extends BaseActivity {

    @BindView(R.id.mgv_multiple_Choice)
    MyGridView mgvMultipleChoice;
    @BindView(R.id.radio)
    Button radio;
    private ArrayList<Entity> timeList;
    private String[] times;
    private MultipleChoiceAdapter choiceAdapter;
    private MyDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiugongge_acivity);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        /*多选*/
        initMultiChoice();

        /*弹出框式GridView*/
        initDialog();
    }

    /**
     * 弹出框式GridView
     */
    private void initDialog() {
        dialog = new MyDialog(this, new MyDialog.MyDialogListener() {
            @Override
            public void getData(String data) {
                Toast.makeText(JiugonggeAcivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setCancelable(false);//dialog设置不能取消
    }


    /**
     * 多选模式
     */
    private void initMultiChoice() {
        timeList = new ArrayList<>();
        times = getResources().getStringArray(R.array.time);
        for (int i = 0; i < times.length; i++) {
            //把每个item的状态和时间都添加到集合中
            timeList.add(new Entity(times[i], false));
        }
        //设置适配器
        choiceAdapter = new MultipleChoiceAdapter(this, timeList);
        mgvMultipleChoice.setAdapter(choiceAdapter);
        /*多选点击事件*/
        mgvMultipleChoice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (timeList.get(i).isSelect()) {
                    /*判断第i个item是不是被选中，若是被选中则把这个item的状态改为false*/
                    timeList.get(i).setIsSelect(false);
                } else {
                     /*反之 则把这个item的状态改为true*/
                    timeList.get(i).setIsSelect(true);
                }
                Toast.makeText(JiugonggeAcivity.this, times[i], Toast.LENGTH_SHORT).show();
                /*刷新适配器*/
                choiceAdapter.notifyDataSetChanged();
            }
        });
        mgvMultipleChoice.setFocusable(false);
    }

    @OnClick(R.id.radio)
    public void onClick() {

        dialog.show();
    }
}
