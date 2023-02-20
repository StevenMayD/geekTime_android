package com.example.geektime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geektime.R;

import java.util.List;
import java.util.Map;

public class MineListViewAdapter extends BaseAdapter {
    private List<Map> listData;
    private Context context;

    public MineListViewAdapter(List dataList, Context context) {
        this.listData = dataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.listData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MineViewHolder mineViewHolder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_mine_list_item, viewGroup, false);

            mineViewHolder = new MineViewHolder();
            mineViewHolder.imageView = view.findViewById(R.id.icon);
            mineViewHolder.textView = view.findViewById(R.id.title);

            view.setTag(mineViewHolder);
        } else {
            mineViewHolder = (MineViewHolder) view.getTag();
        }

        Map item = (Map) listData.get(i);
        mineViewHolder.imageView.setImageResource((Integer) item.get("image")); // image值为 Integer类型的R.drawable.icon_life
        mineViewHolder.textView.setText((String)item.get("title"));
        return view;
    }

    private final class MineViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
