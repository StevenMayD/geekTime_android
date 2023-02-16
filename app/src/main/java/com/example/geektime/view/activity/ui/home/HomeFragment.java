package com.example.geektime.view.activity.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.geektime.R;
import com.example.geektime.databinding.FragmentHomeBinding;
import com.example.geektime.tools.glide.GlideApp;
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // 获取数据模型
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        // 获取界面文本控件
        final TextView textView = binding.textHome;
        // 将数据模型与界面控件绑定（将homeViewModel的getText获得的string 赋值到textView上）
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

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

        banner = root.findViewById(R.id.main_banner); // fragment中 使用root.findViewById获取控件
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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}