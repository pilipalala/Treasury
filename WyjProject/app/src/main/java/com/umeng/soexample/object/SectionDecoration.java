package com.umeng.soexample.object;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.umeng.soexample.R;
import com.umeng.soexample.utils.MyUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/1/16.
 */
public class SectionDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "SectionDecoration";
    private Context context;
    private DecorationCall callback;
    private List<NameBean> dataList;
    private Resources res;
    private Paint paint;
    private TextPaint textPaint;
    private Paint.FontMetrics fontMetrics;
    private int topGap;
    private int alignBottom;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);//获取view在Adapter中的position
        Log.i(TAG, "getItemOffsets: " + position);
        String groupId = callback.getGroupId(position);
        if (groupId.equals("-1")) {
            return;
        }
        if (position == 0 || isFirstInGroup(position)) {
            outRect.top = topGap;
            if (TextUtils.isEmpty(dataList.get(position).getName())) {
                outRect.top = 0;
            }
        } else {
            outRect.top = 0;
        }
    }

    /**
     * 判断是不是组中的第一个位置
     *
     * @param position
     * @return
     */
    private boolean isFirstInGroup(int position) {
        if (position == 0) {
            return true;
        } else {
            // 因为是根据 字符串内容的相同与否 来判断是不是同一组的，所以此处的标记id 要是String类型
            String prevGroupId = callback.getGroupId(position - 1);

            String groupId = callback.getGroupId(position);
            //判断前一个字符串 与 当前字符串 是否相同
            if (prevGroupId.equals(groupId)) {
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        /*这个数字是不固定的，随着recycleview的滑动会改变,比如有的页面显示出了6个view，
         *那这个数字就是6。此时滑一下，第一个view出去了一半，后边又加进来半个view，
         *此时getChildCount()就是7。所以这里可见item view的个数，露出一半也算一个。*/
        int childCount = parent.getChildCount();//获取当前可见的item view的个数
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            String groupId = callback.getGroupId(position);
            if (groupId.equals("-1")) {
                return;
            }
            String textLine = callback.getGroupFirstLine(position).toUpperCase();//字符串转为大写

            if (TextUtils.isEmpty(textLine)) {
                float top = view.getTop();
                float bottom = view.getTop();
                c.drawRect(left, top, right, bottom, paint);
                return;
            } else {
                if (position == 0 || isFirstInGroup(position)) {
                    float top = view.getTop() - topGap;
                    float bottom = view.getTop();

                    /*绘制悬浮栏*/
                    c.drawRect(left, top - topGap, right, bottom, paint);
                    /*绘制文本*/
                    c.drawText(textLine, left, bottom, textPaint);
                }
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int itemCount = state.getItemCount();
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();

        int right = parent.getWidth() - parent.getPaddingRight();
        float lineHeight = textPaint.getTextSize() + fontMetrics.descent;
        String preGroupId = "";
        String groupId = "-1";
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);

            preGroupId = groupId;
            groupId = callback.getGroupId(position);
            if (groupId.equals("-1") || groupId.equals(preGroupId)) continue;

            String textLine = callback.getGroupFirstLine(position).toUpperCase();
            if (TextUtils.isEmpty(textLine)) {
                continue;
            }
            int viewBottom = view.getBottom();
            float textY = Math.max(topGap, view.getTop());
            //下一个和当前不一样移动当前
            if (position + 1 < itemCount) {
                String nextGroupId = callback.getGroupId(position + 1);
                //组内最后一个view进入了header
                if (nextGroupId != groupId && viewBottom < textY) {
                    textY = viewBottom;
                }
            }
            //textY - topGap决定了悬浮栏绘制的高度和位置
            c.drawRect(left, textY - topGap, right, textY, paint);
            Log.e(TAG, "left: " + left);//0
            Log.e(TAG, "textY - topGap: " + (textY - topGap));//0.0
            Log.e(TAG, "topGap: " + topGap);//45
            Log.e(TAG, "right: " + right);//1080
            Log.e(TAG, "textY: " + textY);//45.0
            //left+2*alignBottom 决定了文本往左偏移的多少（加-->向左移）
            //textY-alignBottom  决定了文本往右偏移的多少  (减-->向上移)
            c.drawText(textLine, left + 2 * alignBottom, textY - alignBottom, textPaint);
        }

    }

    public SectionDecoration(List<NameBean> dataList, Context context, DecorationCall decorationCallback) {
        this.dataList = dataList;
        this.context = context;
        this.callback = decorationCallback;
        res = context.getResources();
        /*设置悬浮栏的画笔     paint*/
        paint = new Paint();
        paint.setColor(res.getColor(R.color.divider));
        /*设置悬浮栏中文文本的画笔*/
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);//抗锯齿
        textPaint.setTextSize(MyUtils.dip2px(context, 14));
        textPaint.setColor(Color.DKGRAY);
        textPaint.setTextAlign(Paint.Align.LEFT);// 设置对齐方式
        fontMetrics = new Paint.FontMetrics();//字体度量
        /*决定悬浮栏的高度等*/
        topGap = res.getDimensionPixelSize(R.dimen.sectioned_top);
        /*决定文本的显示位置等*/
        alignBottom = res.getDimensionPixelSize(R.dimen.sectioned_alignBottom);
    }

    /**
     * 定义一个借口方便外界的调用
     */
    public interface DecorationCall {
        String getGroupId(int position);

        String getGroupFirstLine(int position);
    }
}
