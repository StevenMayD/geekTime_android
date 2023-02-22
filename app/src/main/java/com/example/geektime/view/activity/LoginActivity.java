package com.example.geektime.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.geektime.R;
import com.example.geektime.tools.navigation.NavigationBar;
import com.example.geektime.tools.navigation.titleBar.OnTitleBarListener;
import com.example.geektime.tools.navigation.titleBar.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    /** 注解绑定控件 声明的控件必须是 public类型
     *   并且一定要写 ButterKnife.bind(this); 否则崩溃
     * */
    @BindView(R.id.titleBar)
    public NavigationBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* 这段代码一定要加上不然会导致程序启动失败内存溢出，程序崩溃
         * 并且写在跟视图绑定方法setContentView 之后
         * */
        ButterKnife.bind(this);

        this.getSupportActionBar().hide(); // 隐藏默认导航栏
        if (titleBar != null) {
            // 获取前一个activity跳转的传参
            Intent intent = getIntent();
            String title = intent.getStringExtra("title");

            titleBar.setLeftTitle(title);
            // 监听导航栏有控件的部位 被点击
            titleBar.setOnTitleBarListener(new OnTitleBarListener() {
                // 导航栏左按钮被点击
                @Override
                public void onLeftClick(TitleBar titleBar) {
                    OnTitleBarListener.super.onLeftClick(titleBar);
                    Log.d("点击了", "导航栏左按钮");
                    // 传参
                    Intent intent = new Intent();
                    intent.putExtra("title", "登录");

                    // activity返回上一页
                    setResult(RESULT_OK, intent); // 无需传参： setResult(RESULT_OK);
                    finish();
                }

                // 导航栏title被点击
                @Override
                public void onTitleClick(TitleBar titleBar) {
                    OnTitleBarListener.super.onTitleClick(titleBar);
                    Log.d("点击了", "导航title被点击");
                }

                @Override
                public void onRightClick(TitleBar titleBar) {
                    OnTitleBarListener.super.onRightClick(titleBar);
                    Log.d("点击了", "导航栏右按钮");
                }
            });
            // 监听导航栏非按钮或title的部位 被点击
            titleBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("点击了", "导航栏");
                }
            });
        }

    }
}