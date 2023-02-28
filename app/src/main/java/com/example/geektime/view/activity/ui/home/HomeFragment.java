package com.example.geektime.view.activity.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.geektime.R;
import com.example.geektime.adapter.HomeListViewAdapter;
import com.example.geektime.bean.CourseBean;
import com.example.geektime.databinding.FragmentHomeBinding;
import com.example.geektime.tools.glide.GlideApp;
import com.example.geektime.view.fragment.CourseDetailFragment;
import com.example.geektime.view.fragment.CoursePurchasedFragment;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private View root;
    private Banner banner; // 轮播图界面
//    private List<Integer> bannerData; // 轮播图数据1：加载本地图片资源 - R.drawable.数组
    private List<String> bannerData; // 轮播图数据2：加载网络图片资源 - 链接数组
    private List<CourseBean> courseData; // 课程数据

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        // 加载初始界面textview控件数据（利用了原本的数据模型HomeViewModel）
        loadTextView();

        // 加载轮播图
        try {
            loadBannerView();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 加载表格信息
        loadListView();

        return root;
    }

    private void loadTextView() {
        // 获取数据模型
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // 获取界面文本控件
        final TextView textView = binding.textHome;
        // 将数据模型与界面控件绑定（将homeViewModel的getText获得的string 赋值到textView上）
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

    }

    private void loadBannerView() throws IOException {
        bannerData = new ArrayList<>();

//        轮播图数据1：加载本地图片资源
//        bannerData.add(R.drawable.banner1);
//        bannerData.add(R.drawable.banner2);
//        bannerData.add(R.drawable.banner3);

//        轮播图数据2：加载网络图片资源
        String image1 = "https://static001.geekbang.org/resource/image/f7/b4/f79b1fcb2e9eeb1002fd7db4b4fd10b4.jpg";
        String image2 = "https://static001.geekbang.org/resource/image/30/86/307332b7ba9556ba1e38c358ad6aab86.jpg";
        String image3 = "https://static001.geekbang.org/resource/image/4e/c5/4ea96e35e97f37914c1703d1cf4b69c5.jpg";
        bannerData.add(image1);
        bannerData.add(image2);
        bannerData.add(image3);

//        banner = root.findViewById(R.id.main_banner); // fragment中 使用root.findViewById获取控件
        banner = binding.mainBanner; // 由于 FragmentHomeBinding 绑定，也可以这样获取控件
        // 适配器传入数据：必须明确参数类型，及参数数组的元素类型 才不会报错
//        banner.setAdapter(new BannerImageAdapter<Integer>(bannerData) {
        banner.setAdapter(new BannerImageAdapter<String>(bannerData) {
            @Override
//            public void onBindView(BannerImageHolder holder, Integer data, int position, int size) {
            public void onBindView(BannerImageHolder holder, String data, int position, int size) {
//                轮播图数据1：加载本地图片资源
//                holder.imageView.setImageResource(data);

//                轮播图数据2：加载网络图片资源
                GlideApp.with(root)
                        .load(data)
                        .into(holder.imageView);
            }
        });
        banner.isAutoLoop(true); // 自动循环
        banner.setIndicator(new CircleIndicator(this.getActivity())); // 轮播图添加指示器 于fragment所属的activity
//        banner.setScrollBarFadeDuration(1000);
        banner.setIndicatorSelectedColor(Color.WHITE); // 设置指示器 点颜色
        banner.start(); // 轮播图运行

    }

    private void loadListView() {
        courseData = fakeListData(); // 获取课程列表模拟数据

        // 通过id拿到UI界面上的ListView对象
        ListView listView = root.findViewById(R.id.homeListView);
        // adapter辅助适配类：用于控件的数据填充（数据和 fragment所属的activity）
        listView.setAdapter(new HomeListViewAdapter(courseData, this.getActivity()));
        // listView的item点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CourseBean selectCourse = courseData.get(i);
                Log.d("点击了", "onItemClick: 第" + i + "行" + "数据为：" + selectCourse);

                // 创建已购课程fragment
                CoursePurchasedFragment coursePurchasedFragment = new CoursePurchasedFragment();
                // 切换fragment
                replaceFragment(coursePurchasedFragment);
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        // activity(fragment所属的)的CourseFragment，切换fragment
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.CourseFragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    private List<CourseBean> fakeListData() {
        String imageUrl = "https://static001.geekbang.org/resource/image/87/ee/8778de4ccd67425a762cea15361639ee.jpg?x-oss-process=image/resize,m_fill,h_336,w_254";
        String name = "MongoDB高手课";
        String desc = "Tapdata CTO、MongoDB中文社区主席、前MongoDB大中华区首席架构师";
        String teacher = "唐建法";
        String detail = "MongoDB 是当前广受欢迎的 NoSQL 数据库，目前社...";
        String courseList = "第一章：MongoDB再入门 (9讲)";
        int price = 129;
        int total = 47;
        int update = 9;
        int studentCount = 2205;
        CourseBean courseBean1 = new CourseBean(imageUrl, name, desc, teacher, detail, courseList, price, total, update, studentCount);

        imageUrl = "https://static001.geekbang.org/resource/image/a6/48/a6c8ecd93024e4ba483bd3800dac3148.jpg?x-oss-process=image/resize,m_fill,h_336,w_254";
        name = "JavaScript核心原理解析";
        desc = "重构你对JavaScript语言的认知";
        teacher = "周爱民";
        detail = "作为前端工程师必备技能，JavaScript 的重要性不言而喻。但是，很多人对 JavaScript 的印象都只是“简单易学”，对其掌握也仅仅停留在“会用就好”，以至于不求甚解、迷失于 JavaScript 。究其原因，他们从来都只是“写代码”，而没有去真正去了解、去探索“什么是语言”。.";
        courseList = "从零开始 (3讲)";
        price = 199;
        total = 21;
        update = 4;
        studentCount = 2532;
        CourseBean courseBean2 = new CourseBean(imageUrl, name, desc, teacher, detail, courseList, price, total, update, studentCount);

        imageUrl = "https://static001.geekbang.org/resource/image/cd/6c/cd7ba03722f906db730366de0d6f2d6c.jpg?x-oss-process=image/resize,m_fill,h_336,w_254";
        name = "设计模式之美";
        desc = "前Google工程师手把手教你写高质量代码";
        teacher = "王争";
        detail = "设计模式对你来说，应该不陌生。在面试中，经常会被问到；在工作中，有时候也会用到。一些设计模式书籍，比如大名鼎鼎的 GoF 的《设计模式》、通俗易懂的《Head First 设计模式》，估计你也都研读过。那你是否觉得自己已经掌握了设计模式呢？是否思考过怎么才算真正掌握了设计模式呢？是熟练掌握每种设计模式的原理和代码实现吗？";
        courseList = "开篇词 (1讲)";
        price = 151;
        total = 33;
        update = 78;
        studentCount = 6655;
        CourseBean courseBean3 = new CourseBean(imageUrl, name, desc, teacher, detail, courseList, price, total, update, studentCount);

        imageUrl = "https://static001.geekbang.org/resource/image/bb/e4/bb10501b8ec19b04755ce600ded53ee4.jpg?x-oss-process=image/resize,m_fill,h_336,w_254";
        name = "项目管理实战20讲";
        desc = "网易内部项目管理心法";
        teacher = "雷蓓蓓";
        detail = "当下，项目管理能力已经逐渐成为我们每一个人的必备技能。随着项目复杂度的增加、竞争压力的增大，只是做一名优秀的项目参与者是远远不够的。实际上，我们都应该成为拥有全局视角和主人翁意识的项目管理者。如果能够跨出自己的日常职责范围，主动推进项目目标的落地，促进各角色的高效协同运转，自己的职场精进之路将更加顺畅。而这一切，都离不开项目管理能力的支撑。";
        courseList = "开篇词(1讲)";
        price = 144;
        total = 26;
        update = 83;
        studentCount = 7821;
        CourseBean courseBean4 = new CourseBean(imageUrl, name, desc, teacher, detail, courseList, price, total, update, studentCount);

        imageUrl = "https://static001.geekbang.org/resource/image/a6/48/a6c8ecd93024e4ba483bd3800dac3148.jpg?x-oss-process=image/resize,m_fill,h_336,w_254";
        name = "iOS核心技术开发";
        desc = "要学就学iOS核心技术开发";
        teacher = "彼岸向阳而生";
        detail = "预计3个工作日内完成审核，审核常见问题请查看帮助文档";
        courseList = "重新开始 (78讲)";
        price = 199;
        total = 21;
        update = 4;
        studentCount = 2532;
        CourseBean courseBean5 = new CourseBean(imageUrl, name, desc, teacher, detail, courseList, price, total, update, studentCount);

        ArrayList fakeCourseData = new ArrayList<>();
        fakeCourseData.add(courseBean1);
        fakeCourseData.add(courseBean2);
        fakeCourseData.add(courseBean3);
        fakeCourseData.add(courseBean4);
        fakeCourseData.add(courseBean5);
        fakeCourseData.add(courseBean1);
        fakeCourseData.add(courseBean2);
        fakeCourseData.add(courseBean3);
        fakeCourseData.add(courseBean4);
        fakeCourseData.add(courseBean5);

        return fakeCourseData;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}