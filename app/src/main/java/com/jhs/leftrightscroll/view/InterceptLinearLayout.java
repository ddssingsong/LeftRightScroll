package com.jhs.leftrightscroll.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by dds on 2016/9/7.
 *
 * @TODO
 */
public class InterceptLinearLayout extends LinearLayout {

    public InterceptLinearLayout(Context context) {
        super(context);
    }

    public InterceptLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        ViewGroup view1 = (ViewGroup) getChildAt(1);
        View view = getChildAt(0);
        if (!view1.onInterceptTouchEvent(ev)) {
            view.dispatchTouchEvent(ev);
        }
        if (view.onTouchEvent(ev)) {
            view1.dispatchTouchEvent(ev);
        }
        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }


}
