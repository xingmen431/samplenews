//
//  News.swift
//  sampleNews
//
//  Created by vincent on 2023/11/16.
//

import Foundation

// 定义一个名为 "News" 的结构体，遵循 Codable 和 Identifiable 协议
struct News: Codable, Identifiable {
    let id: Int           // 新闻的唯一标识符
    let title: String     // 新闻标题
    let content: String   // 新闻内容
    let imageUrl: String // 新闻图片的URL

    // 枚举，用于指定编码和解码时的键
    enum CodingKeys: String, CodingKey {
        case id             // 对应 JSON 中的 "id"
        case title          // 对应 JSON 中的 "title"
        case content        // 对应 JSON 中的 "content"
        case imageUrl = "imageUrl" // 对应 JSON 中的 "imageUrl"
    }
}
