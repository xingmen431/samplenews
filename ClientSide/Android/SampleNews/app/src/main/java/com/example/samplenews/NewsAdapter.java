package com.example.samplenews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

// NewsAdapter类用于将新闻数据绑定到RecyclerView上
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<News> newsList; // 存储新闻数据的列表
    private LayoutInflater inflater; // 用于加载布局文件
    private Context context;

    // 构造函数，接收上下文和新闻列表作为参数
    public NewsAdapter(Context context, List<News> newsList) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.newsList = newsList;
    }

    // 设置新闻列表数据
    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged(); // 通知RecyclerView数据已更新
    }

    // 创建新的ViewHolder实例
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_news, parent, false); // 加载新闻列表项的布局
        return new NewsViewHolder(itemView);
    }

    // 将数据绑定到ViewHolder上
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News newsItem = newsList.get(position); // 获取当前位置的新闻项数据
        holder.textViewTitle.setText(newsItem.getTitle()); // 设置标题文本
        holder.textViewSummary.setText(newsItem.getContent()); // 设置摘要文本
        // 如果有图片URL，使用Glide或其他库加载图片到ImageView
        Glide.with(context).load(newsItem.getImageUrl()).into(holder.imageViewThumbnail);

        // 设置列表项的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理点击事件，启动新的Activity以显示新闻详细信息
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("newsTitle", newsItem.getTitle());
                intent.putExtra("newsContent", newsItem.getContent());
                intent.putExtra("newsImageUrl", newsItem.getImageUrl());
                context.startActivity(intent); // 启动新的Activity
            }
        });
    }

    // 获取新闻列表的项数
    @Override
    public int getItemCount() {
        return newsList.size();
    }

    // 定义ViewHolder类，用于表示RecyclerView中的每个新闻项
    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle; // 标题文本视图
        public TextView textViewSummary; // 摘要文本视图
        public ImageView imageViewThumbnail; // 缩略图图像视图

        // 构造函数，初始化ViewHolder中的视图元素
        public NewsViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle); // 根据视图ID获取视图
            textViewSummary = itemView.findViewById(R.id.textViewSummary);
            imageViewThumbnail = itemView.findViewById(R.id.imageViewThumbnail);
        }
    }
}