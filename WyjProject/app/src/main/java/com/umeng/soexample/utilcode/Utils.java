package com.umeng.soexample.utilcode;

import android.content.Context;

/**
 * Created by Administrator on 2017/1/24.
 */
public class Utils {
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("么有初始化Utils");
    }

    /**
     * 初始化工具类
     *
     * @param context
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("u should init first");
    }
}
