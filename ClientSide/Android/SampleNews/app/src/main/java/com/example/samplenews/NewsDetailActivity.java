package com.example.samplenews;

import static android.content.Intent.getIntent;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class NewsDetailActivity extends AppCompatActivity {
    private TextView textViewDetailTitle;
    private TextView textViewDetailContent;
    private ImageView imageViewDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail); // 设置活动的布局文件为activity_news_detail.xml

        // 初始化视图元素
        textViewDetailTitle = findViewById(R.id.textViewDetailTitle);
        textViewDetailContent = findViewById(R.id.textViewDetailContent);
        imageViewDetail = findViewById(R.id.imageViewDetail);

        // 从Intent中获取传递过来的新闻数据
        String title = getIntent().getStringExtra("newsTitle"); // 获取新闻标题
        String content = getIntent().getStringExtra("newsContent"); // 获取新闻内容
        String imageUrl = getIntent().getStringExtra("newsImageUrl"); // 获取新闻图片URL

        // 在视图元素中显示新闻标题和内容
        textViewDetailTitle.setText(title); // 设置新闻标题文本
        textViewDetailContent.setText(content); // 设置新闻内容文本

        // 如果有图片URL，使用Glide或其他库加载图片到ImageView
        Glide.with(this).load(imageUrl).into(imageViewDetail);
    }
}