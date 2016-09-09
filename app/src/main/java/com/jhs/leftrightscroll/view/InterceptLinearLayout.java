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
    private float lastXIntercept = 0;
    private float lastYIntercept = 0;
    private int count;//一次滑动截获的move事件数
    private boolean flag;//是否拦截了右边上下滑动事件

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
        view1.dispatchTouchEvent(ev);
        View view = getChildAt(0);


        float x = ev.getX();
        float y = ev.getY();
        switch (ev.getAction()) {
            /*如果拦截了Down事件,则子类不会拿到这个事件序列*/
            case MotionEvent.ACTION_DOWN:
                view.dispatchTouchEvent(ev);
                lastXIntercept = x;
                lastYIntercept = y;
                count = 0;
                flag = false;
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaX = x - lastXIntercept;
                final float deltaY = y - lastYIntercept;
                /*根据条件判断是否拦截该事件*/
                if (count == 0) {
                    //左右滑动
                    if (Math.abs(deltaX) > Math.abs(deltaY)) {
                        view.setEnabled(false);
                        count++;
                    }
                    //上下滑动
                    if (Math.abs(deltaX) < Math.abs(deltaY)) {
                        if (view1.onInterceptTouchEvent(ev)) {
                            flag = true;
                            count++;
                        } else {
                            view.dispatchTouchEvent(ev);
                            view.setEnabled(true);
                            flag = false;
                            count++;
                        }
                    }
                } else {
                    if (!flag) {
                        view.dispatchTouchEvent(ev);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                view.dispatchTouchEvent(ev);
                count = 0;
                flag = false;
                view.setEnabled(true);
                break;
            case MotionEvent.ACTION_CANCEL:
                view.dispatchTouchEvent(ev);
                break;


        }
        lastXIntercept = x;
        lastYIntercept = y;

        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }


}
