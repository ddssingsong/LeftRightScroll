package com.jhs.leftrightscroll.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 * @Description:这个类也是从网上找的参考
 */
public class SyncHorizontalScrollView extends HorizontalScrollView {

    private View mView;


    public SyncHorizontalScrollView(Context context) {
        super(context);
    }

    public SyncHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //设置控件滚动监听，得到滚动的距离，然后让传进来的view也设置相同的滚动具体
        if (mView != null) {
            mView.scrollTo(l, t);
        }
    }

    /**
     * 设置跟它联动的view
     *
     * @param view
     */
    public void setScrollView(View view) {
        mView = view;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        float x = ev.getX();
//        float y = ev.getY();
//
//        switch (ev.getAction()) {
//            /*如果拦截了Down事件,则子类不会拿到这个事件序列*/
//            case MotionEvent.ACTION_DOWN:
//                lastXIntercept = x;
//                lastYIntercept = y;
//                intercepted = false;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                final float deltaX = x - lastXIntercept;
//                final float deltaY = y - lastYIntercept;
//                /*根据条件判断是否拦截该事件*/
//
//                Log.i("msg", "左右滑动距离" + deltaX);
//                Log.i("msg", "上下滑动距离" + deltaY);
//
//                if (Math.abs(deltaX) > Math.abs(deltaY)) {
//                    intercepted = true;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//
//        }
//        lastXIntercept = x;
//        lastYIntercept = y;
//
//        return intercepted;


        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean flag = super.dispatchTouchEvent(ev);
        return flag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean flag = super.onTouchEvent(event);
        return flag;
    }
}