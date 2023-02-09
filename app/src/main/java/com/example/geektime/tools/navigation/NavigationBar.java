package com.example.geektime.tools.navigation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.example.geektime.tools.navigation.titleBar.TitleBar;

// 自定义一个导航栏类 继承自TitleBar
public class NavigationBar extends TitleBar {
    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NavigationBar(Context context) {
        super(context, null);
        init();
    }

    public NavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        init();
    }

    private void init() {
    }


    Drawable leftIcon;

    // 重写返回NavigationBar类
    @Override
    public NavigationBar setLeftIcon(Drawable drawable) {
        this.leftIcon = drawable;
        super.setLeftIcon(drawable);
        return this;
    }

    public NavigationBar setLeftIconTint(int color) {
        if (leftIcon != null) {
            leftIcon.setTint(color);
            return setLeftIcon(leftIcon);
        }
        return null;
    }

    Drawable rightIcon;

    @Override
    public NavigationBar setRightIcon(Drawable drawable) {
        this.rightIcon = drawable;
        super.setRightIcon(drawable);
        return this;
    }

    public NavigationBar setRightIconTint(int color) {
        if (rightIcon != null) {
            rightIcon.setTint(color);
            return setLeftIcon(rightIcon);
        }
        return null;
    }
}
