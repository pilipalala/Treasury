package com.umeng.soexample.object;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.umeng.soexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/14.
 */
public class MyDialog extends Dialog {
    private final MyDialogListener listener;
    @BindView(R.id.dialog_gridView)
    MyGridView dialogGridView;
    private Context context;
    private String[] mealTime = {"5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60"};

    public interface MyDialogListener {
        public void getData(String data);

    }
    public MyDialog(Context context,MyDialogListener listener) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_grideview);
        ButterKnife.bind(this);
        dialogGridView.setAdapter(new ArrayAdapter<String>(context, R.layout.item_text, mealTime));
        dialogGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listener.getData(mealTime[i]);
                dismiss();
            }
        });
    }
}
