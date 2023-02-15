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
    private List<Integer> bannerData; // 轮播图数据：图片链接 请求后的Bitmap 数组

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
        bannerData.add(R.drawable.banner1);
        bannerData.add(R.drawable.banner2);
        bannerData.add(R.drawable.banner3);

        banner = root.findViewById(R.id.main_banner); // fragment中 使用root.findViewById获取控件
        // 适配器传入数据：必须明确参数类型，及参数数组的元素类型 才不会报错
        banner.setAdapter(new BannerImageAdapter<Integer>(bannerData) {
            @Override
            public void onBindView(BannerImageHolder holder, Integer data, int position, int size) {
                holder.imageView.setImageResource(data);
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