//
//  ContentView.swift
//  sampleNews
//
//  Created by vincent on 2023/11/16.
//

import SwiftUI

struct ContentView: View {
    
    @ObservedObject var viewModel = NewsViewModel() // 创建新闻视图模型

    var body: some View {
        NavigationView {
            List(viewModel.newsItems) { news in
                NavigationLink(destination: NewsDetailView(news: news)) {
                    NewsRow(news: news) // 使用NewsRow来呈现新闻列表项
                }
            }
            .navigationTitle("新闻") // 设置导航栏标题为"新闻"
        }
    }
}

// 新闻列表项的视图
struct NewsRow: View {
    let news: News
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(news.title) // 新闻标题
                .font(.headline)
            Text(news.content) // 新闻内容
                .font(.subheadline)
                .lineLimit(3) // 最多显示3行内容
            AsyncImage(url: URL(string: news.imageUrl)) { image in
                image.resizable()
                    .aspectRatio(contentMode: .fit)
            } placeholder: {
                Color.gray // 图像加载前的占位符
            }
            .frame(height: 200) // 设置图像的高度
        }
    }
}
