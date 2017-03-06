package com.umeng.soexample.object;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.umeng.soexample.MainActivity2;
import com.umeng.soexample.R;
import com.umeng.soexample.backLayout.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ObjectActivity extends BaseActivity {

    @BindView(R.id.btn_jiugongge)
    Button btnJiugongge;
    @BindView(R.id.btn_popwindow)
    Button btnPopwindow;
    @BindView(R.id.btn_itemdecoration)
    Button btnItemdecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_jiugongge,R.id.btn_popwindow, R.id.btn_itemdecoration,R.id.btn_textinputlayout,R.id.btn_recycleview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_jiugongge:
                startActivity(new Intent(this, JiugonggeAcivity.class));
                break;
            case R.id.btn_popwindow:
                startActivity(new Intent(this, MainActivity2.class));
                break;
            case R.id.btn_itemdecoration:
                startActivity(new Intent(this, ItemDecorationActivity.class));
                break;
            case R.id.btn_textinputlayout:
                startActivity(new Intent(this, TextInputLayoutActivity.class));
                break;
            case R.id.btn_recycleview:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
        }
    }
}
