//
//  NewsViewModel.swift
//  sampleNews
//
//  这个视图模型用于管理新闻数据，通过调用fetchNews()方法从NewsService获取数据，
//  并在成功或失败时更新newsItems属性。
//
//  Created by vincent on 2023/11/26.
//
import Foundation

// 视图模型用于管理新闻数据
class NewsViewModel: ObservableObject {
    @Published var newsItems = [News]() // 使用@Published属性包装的数组，用于在视图中自动更新数据

    init() {
        fetchNews() // 在初始化时调用fetchNews方法，以获取新闻数据
    }

    // 从NewsService中获取新闻数据
    func fetchNews() {
        NewsService().fetchNews { [weak self] result in
            DispatchQueue.main.async { // 在主线程中更新视图数据
                switch result {
                case .success(let newsArray):
                    self?.newsItems = newsArray // 如果成功，将新闻数据设置为获取的数据
                case .failure(let error):
                    print(error.localizedDescription) // 如果失败，打印错误信息或执行其他错误处理
                }
            }
        }
    }
}
