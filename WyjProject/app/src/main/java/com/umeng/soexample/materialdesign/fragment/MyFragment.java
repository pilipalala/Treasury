package com.umeng.soexample.materialdesign.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umeng.soexample.R;


/**
 * Created by Administrator on 2017/1/5.
 */
@SuppressLint("ValidFragment")
public class MyFragment extends Fragment {
    private final String title;
    private Context mContext;

    private TextView tv;
    @SuppressLint("ValidFragment")
    public MyFragment(String title) {
        this.title = title;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv.setText(title);
        tv.setGravity(Gravity.CENTER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);
        tv = (TextView) view.findViewById(R.id.tvInfo);
        return view;
    }
}
