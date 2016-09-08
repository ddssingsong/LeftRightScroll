package com.jhs.leftrightscroll.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by dds on 2016/9/8.
 *
 * @TODO
 */
public class LeftListView extends ListView {
    private float lastXIntercept = 0;
    private float lastYIntercept = 0;

    public LeftListView(Context context) {
        super(context);
    }

    public LeftListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LeftListView(Context context, AttributeSet attrs,
                        int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean flag = super.onInterceptTouchEvent(ev);
        return flag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean flag = super.dispatchTouchEvent(ev);
        return flag;
    }
}