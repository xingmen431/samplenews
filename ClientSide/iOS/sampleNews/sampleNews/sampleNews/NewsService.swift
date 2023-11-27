//
//  NewsService.swift
//
//  这段代码描述了一个用于从远程服务器获取新闻数据的网络请求服务。
//  它使用了异步操作和错误处理，以确保在请求期间处理错误，并在成功时传递新闻数据。
//
//  Created by vincent on 2023/11/16.
//

import Foundation

class NewsService {
    // 定义一个方法，用于从远程服务器获取新闻数据
    func fetchNews(completion: @escaping (Result<[News], Error>) -> Void) {
        // 检查URL是否有效
        guard let url = URL(string: "http://192.168.11.13:3000/api/items") else {
            return
        }
        
        // 创建一个URL会话来执行网络请求
        URLSession.shared.dataTask(with: url) { data, response, error in
            // 检查是否有错误发生
            if let error = error {
                completion(.failure(error)) // 如果有错误，将错误传递给完成处理程序并退出
                return
            }
            
            // 检查是否有数据返回
            guard let data = data else {
                return // 如果没有数据，退出
            }
            
            do {
                // 尝试使用JSON解码器解码数据为News数组
                let news = try JSONDecoder().decode([News].self, from: data)

                // 使用主线程将成功结果传递给完成处理程序
                DispatchQueue.main.async {
                    completion(.success(news))
                }
            } catch {
                // 如果解码失败，将错误传递给完成处理程序
                completion(.failure(error))
            }
        }.resume() // 开始执行URL会话任务
    }
}
