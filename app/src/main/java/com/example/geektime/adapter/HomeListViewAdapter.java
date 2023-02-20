package com.example.geektime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geektime.R;
import com.example.geektime.bean.CourseBean;
import com.example.geektime.tools.glide.GlideApp;

import java.util.List;

public class HomeListViewAdapter extends BaseAdapter {
    private List<CourseBean> dataList;
    private Context context;

    public HomeListViewAdapter(List<CourseBean> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.dataList.size();
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
        CourseViewHolder courseViewHolder;
        if (view == null) {
            // 拿到item条目的布局
            view = LayoutInflater.from(context).inflate(R.layout.layout_home_list_item, viewGroup, false);

            // 将viewHolder的textView和 item项R.id的textView绑定（这样不会为每一个view调用耗时的findViewById了）
            courseViewHolder = new CourseViewHolder();
            courseViewHolder.imageView = view.findViewById(R.id.imageView);
            courseViewHolder.name = view.findViewById(R.id.name);
            courseViewHolder.price = view.findViewById(R.id.price);
            courseViewHolder.describe = view.findViewById(R.id.describe);

            // 将viewHolder暂存到view的tag属性 （安卓的视图类自带一个tag属性，可以存放Object任意类型对象）
            view.setTag(courseViewHolder);
        } else {
            // 取出view的tag属性对象
            courseViewHolder = (CourseViewHolder) view.getTag();
        }
        /** 赋值viewHolder的textview（即view的textview）为listdata数组第i条数据（Bean的name属性）
         *  Adapter中的with参数，为外界的activity
         * */
        GlideApp.with(context)
                .load(dataList.get(i).getImageUrl())
                .into(courseViewHolder.imageView);
        courseViewHolder.name.setText(dataList.get(i).getName()); // name为string类型
        courseViewHolder.price.setText("￥" + String.valueOf(dataList.get(i).getPrice())); // 注意：price为int类型，需转string
        courseViewHolder.describe.setText(dataList.get(i).getDesc());
        return view;
    }

    // 定义一个ViewHolder类，控件持有类(ViewHolder类中填写子控件（View持有内容类：listView的item项有几个子控件 这里就填写一个子控件）)
    private final class CourseViewHolder {
        ImageView imageView;
        TextView name;
        TextView price;
        TextView describe;
    }
}
