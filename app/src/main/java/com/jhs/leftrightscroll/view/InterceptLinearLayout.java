package com.jhs.leftrightscroll.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by dds on 2016/9/7.
 *
 * @TODO
 */
public class InterceptLinearLayout extends LinearLayout {
    private float lastXIntercept = 0;
    private float lastYIntercept = 0;
    private int count;

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

        View view1 = getChildAt(1);
        view1.dispatchTouchEvent(ev);
        View view = getChildAt(0);
        view.dispatchTouchEvent(ev);

        float x = ev.getX();
        float y = ev.getY();
        switch (ev.getAction()) {
            /*如果拦截了Down事件,则子类不会拿到这个事件序列*/
            case MotionEvent.ACTION_DOWN:
                lastXIntercept = x;
                lastYIntercept = y;
                count = 0;
                view.setEnabled(true);

                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaX = x - lastXIntercept;
                final float deltaY = y - lastYIntercept;
                /*根据条件判断是否拦截该事件*/

                Log.i("msg", "左右滑动距离" + deltaX);
                Log.i("msg", "上下滑动距离" + deltaY);

                if (count == 0) {
                    if (Math.abs(deltaX) > Math.abs(deltaY)) {
                        view.setEnabled(false);
                        count++;
                    }
                    if (Math.abs(deltaX) < Math.abs(deltaY)) {
                        view.setEnabled(true);
                        count++;
                    }
                }


                break;
            case MotionEvent.ACTION_UP:
                count = 0;
                view.setEnabled(true);
                break;

        }
        lastXIntercept = x;
        lastYIntercept = y;

        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        boolean intercepted = super.onTouchEvent(ev);
        switch (ev.getAction()) {
            /*如果拦截了Down事件,则子类不会拿到这个事件序列*/
            case MotionEvent.ACTION_DOWN:
                lastXIntercept = x;
                lastYIntercept = y;
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaX = x - lastXIntercept;
                final float deltaY = y - lastYIntercept;
                /*根据条件判断是否拦截该事件*/

                Log.i("msg", "左右滑动距离" + deltaX);
                Log.i("msg", "上下滑动距离" + deltaY);

                View view = getChildAt(0);
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    view.setEnabled(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        lastXIntercept = x;
        lastYIntercept = y;

        return intercepted;
    }


}
