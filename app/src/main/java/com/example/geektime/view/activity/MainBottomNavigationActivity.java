package com.example.geektime.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.geektime.R;
import com.example.geektime.tools.navigation.NavigationBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.geektime.databinding.ActivityMainBottomNavigationBinding;
import com.example.geektime.view.activity.ui.home.HomeFragment;
import com.example.geektime.view.fragment.CourseDetailFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainBottomNavigationActivity extends AppCompatActivity {
    private ActivityMainBottomNavigationBinding binding;
    private NavController navController;

    @BindView(R.id.titleBar)
    public NavigationBar titleBar; // 注解式绑定控件：声明属性titleBar 并绑定到id为titleBar控件

    @SuppressLint("ResourceAsColor")
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
        navView.setBackgroundColor(getResources().getColor(R.color.teal_200)); // 设置底部tab菜单栏背景色

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_mine)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main_bottom_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        /** 监听navController的fragment切换变化
         * */
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                String title = destination.getLabel().toString(); // 获取当前fragment标题
                titleBar.setTitle(title); // 设置导航主题文字内容
            }
        });

        /** 监听navView的tab点击选择
         * 此方法能生效，但会覆盖navView源码中 原本的tab点击选择的监听方法（源码中会切换fragment），导致fragment不进行切换
         * */
//        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                String title = "";
//                switch (item.getItemId()) {
//                    case R.id.navigation_home:
//                    case R.id.navigation_mine:
//                        title = item.getTitle().toString(); // 获取当前的tab菜单标题
//                        break;
//                }
//                titleBar.setTitle(title); // 设置导航主题文字内容
//                return true; // 这里必须返回true才能响应点击事件
//            }
//        });

        initData();
        loadView();
    }

    // 初始化数据
    private void initData() {

    }

    @SuppressLint("ResourceAsColor")
    // 初始化界面
    private void loadView() {
        if (titleBar != null) {
            String title = (String) navController.getCurrentDestination().getLabel(); // 获取当前的tab菜单标题
            titleBar.setTitle(title); // 设置导航主题文字内容
            titleBar.getLeftView().setVisibility(View.INVISIBLE); // 隐藏导航左按钮
            titleBar.setTitleColor(Color.WHITE); // 设置顶部导航栏主题文字颜色
            /* 设置顶部导航栏背景色（根据参数类型不同，传递不同颜色类型）
            * 读取colors.xml里的颜色 需要使用 getResources().getColor(R.color.xxx)获取
            * 而是用Color系统库里的颜色 直接使用Color.WHITE 即可
            * */
            titleBar.setBackgroundColor(getResources().getColor(R.color.main_JIKE));
        }

        // 给FragmeLayoutd动态加载初始的fragment（课程详情）
        CourseDetailFragment courseDetailFragment = new CourseDetailFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.CourseFragment, courseDetailFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 这里没有使用价值：页面返回data有值 但requestCode不是MineFragment跳转时 设定的1000
        if (requestCode == 1000) {
            String title = data.getStringExtra("title");
            Log.d("来自", title + " 页面的返回");
        }
    }
}