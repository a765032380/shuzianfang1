package com.bjxiyang.shuzianfang.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class MyTopListView extends ListView{
    public MyTopListView(Context context) {
        super(context);
    }

    public MyTopListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTopListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev!=null){
            //判断是否滑动到顶部了
            if (getFirstVisiblePosition() == 0 && getChildAt(0).getTop() == 0) {//到顶部了
                //返回触摸事件
                getParent().requestDisallowInterceptTouchEvent(false);
            } else {//没有到头部
                //拦截触摸事件
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
        return super.onInterceptTouchEvent(ev);
    }
}

