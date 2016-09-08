package com.jhs.leftrightscroll.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by dds on 2016/7/25.
 *
 * @TODO
 */
public class RightListView extends ListView {

    public RightListView(Context context) {
        super(context);
    }

    public RightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RightListView(Context context, AttributeSet attrs,
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
        boolean flag = super.onTouchEvent(ev);
        return flag;
    }
}
