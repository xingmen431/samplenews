package com.example.samplenews;
// 定义一个表示新闻条目的Java类
public class News {
    // 定义新闻条目的属性
    private String title;      // 新闻标题
    private String content;    // 新闻内容
    private String imageUrl;   // 如果新闻有图片链接，存储图片URL

    // 一个空的构造方法，如果需要在代码中直接创建新闻对象
    public News() {
    }

    // 构造方法，用于初始化新闻对象
    public News(String title, String content, String imageUrl) {
        this.title = title;         // 设置新闻标题
        this.content = content;     // 设置新闻内容
        this.imageUrl = imageUrl;   // 设置新闻图片URL
    }

    // Getter和Setter方法，用于获取和设置新闻属性值

    public String getTitle() {
        return title; // 获取新闻标题
    }

    public void setTitle(String title) {
        this.title = title; // 设置新闻标题
    }

    public String getContent() {
        return content; // 获取新闻内容
    }

    public void setContent(String content) {
        this.content = content; // 设置新闻内容
    }

    public String getImageUrl() {
        return imageUrl; // 获取新闻图片URL
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl; // 设置新闻图片URL
    }

    // toString方法，用于在调试时查看新闻对象的信息
    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}