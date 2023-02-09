package com.example.geektime.view.activity;

import android.os.Bundle;
import android.view.View;

import com.example.geektime.R;
import com.example.geektime.tools.navigation.NavigationBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.geektime.databinding.ActivityMainBottomNavigationBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainBottomNavigationActivity extends AppCompatActivity {

    private ActivityMainBottomNavigationBinding binding;

    @BindView(R.id.titleBar)
    public NavigationBar titleBar; // 注解式绑定控件：声明属性titleBar 并绑定到id为titleBar控件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBottomNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /* 这段代码一定要加上不然会导致程序启动失败内存溢出，程序崩溃
        * 并且写在跟视图绑定方法setContentView 之后
        * */
        ButterKnife.bind(this);

        this.getSupportActionBar().hide(); //去掉标题栏

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null); /* 配置点击tab后 无背景颜色 */

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_mine)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main_bottom_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        initData();
        loadView();
    }

    // 初始化数据
    private void initData() {

    }

    // 初始化界面
    private void loadView() {
        if (titleBar != null) {
            titleBar.getLeftView().setVisibility(View.INVISIBLE); // 隐藏导航左按钮
            titleBar.setTitle("主题"); // 设置导航主题文字内容
            titleBar.setTitleColor(R.color.black); // 设置导航栏主题文字颜色
        }


    }

}