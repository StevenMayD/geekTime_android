package com.example.geektime.view.activity.ui.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.geektime.R;
import com.example.geektime.adapter.MineListViewAdapter;
import com.example.geektime.databinding.FragmentMineBinding;
import com.example.geektime.tools.glide.GlideApp;
import com.example.geektime.view.activity.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MineFragment extends Fragment {

    private FragmentMineBinding binding;
    private View root;
    private List<Map> listData; // listView数据为 键值对组成的数组 (键值对的值 有字符串和int 就不指定类型了)
    private Activity activity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMineBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        activity = this.getActivity();

        loadTextView(); // 加载文本框视图
        loadHeadView(); // 加载头部视图
        loadListView(); // 加载列表视图

        Button loginBtn = root.findViewById(R.id.button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // fragment跳转另一个activity（实际上是其所属activity跳转activity）
                startActivity(new Intent(activity, LoginActivity.class));
            }
        });

        return root;
    }

    private void loadTextView() {
        MineViewModel mineViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        final TextView textView = binding.textMine;
        mineViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
    }

    private void loadHeadView() {
        ImageView imageView = binding.headImage;
//        imageView.setImageResource(R.drawable.head);

        // fragment的with参数为 root或 其所属的activity
        GlideApp.with(this.getActivity())
                .load(R.drawable.head) // glide加载本地图片
                .transform(new CircleCrop())
                .into(imageView);
    }

    private void loadListView() {
        fakeData();

        ListView listView = root.findViewById(R.id.mineListView);
        // adapter辅助适配类：用于控件的数据填充（数据和 fragment所属的activity）
        listView.setAdapter(new MineListViewAdapter(listData, this.getActivity()));
        // listView的item点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("点击了", "onItemClick: 第" + i + "行");
            }
        });
    }

    private void fakeData() {
        listData = new ArrayList<>();
        Map item1 = new HashMap<>(); // 列表数组中放置键值对 字典类型
        item1.put("image", R.drawable.icon_life);
        item1.put("title", "账号");
        listData.add(item1);
        Map item2 = new HashMap<>();
        item2.put("image",R.drawable.icon_between);
        item2.put("title", "已购");
        listData.add(item2);
        Map item3 = new HashMap<>();
        item3.put("image",R.drawable.icon_live);
        item3.put("title", "商城订单");
        listData.add(item3);
        Map item4 = new HashMap<>();
        item4.put("image",R.drawable.icon_document);
        item4.put("title", "我的拼团");
        listData.add(item4);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}