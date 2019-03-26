package com.cinema.cintix.startingapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

public class MyDrawerLayout extends DrawerLayout {

    boolean isSwipeOpenEnabled  = false;

    public MyDrawerLayout(@NonNull Context context) {
        super(context);
    }

    public MyDrawerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDrawerLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev){
        if (!isSwipeOpenEnabled) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (!isSwipeOpenEnabled) {
            return false;
        }
        return super.onTouchEvent(ev);
    }
}
