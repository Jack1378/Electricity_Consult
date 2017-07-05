package com.example.myok;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * name ：李飞宇
 * Date: 2017/7/4
 * desc:
 */

public class LvAdapter extends BaseAdapter {

    private List<HomeBean.DataBean> Home_List = new ArrayList<>();
    private Context context;

    public LvAdapter(Context context, List<HomeBean.DataBean> Home_List) {
        this.Home_List = Home_List;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Home_List.size();
    }

    @Override
    public Object getItem(int position) {
        return Home_List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.image_text, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.image);
            holder.textView = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(Home_List.get(position).getNews_title());
        //Glide框架加载网络图片
        Glide.with(context)
                .load(Home_List.get(position).getPic_url())
                .into(holder.imageView);
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
