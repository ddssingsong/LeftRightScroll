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

    private float lastx;
    private float lasty;

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
        boolean flag = super.onInterceptTouchEvent(ev);
        return flag;
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