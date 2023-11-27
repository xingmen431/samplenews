//
//  NewsDetailView.swift
//  sampleNews
//
//  这个视图用于显示单个新闻的详细信息，包括标题、图片和内容。
//
//  Created by vincent on 2023/11/26.
//

import SwiftUI

// 新闻详情视图，显示新闻的详细信息
struct NewsDetailView: View {
    let news: News // 接收新闻对象作为参数
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading) {
                Text(news.title) // 新闻标题
                    .font(.title)
                
                // 异步加载新闻图片
                AsyncImage(url: URL(string: news.imageUrl)) { image in
                    image.resizable()
                        .aspectRatio(contentMode: .fit)
                } placeholder: {
                    Color.gray // 图片加载前的占位符
                }
                .frame(height: 300) // 设置图片高度
                
                Text(news.content) // 新闻内容
                    .font(.body)
            }
            .padding() // 设置内边距
        }
        .navigationTitle(news.title) // 设置导航栏标题为新闻标题
        .navigationBarTitleDisplayMode(.inline) // 导航栏标题显示模式为内联
    }
}
