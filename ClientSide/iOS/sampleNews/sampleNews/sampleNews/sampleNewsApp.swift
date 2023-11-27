//
//  sampleNewsApp.swift
//  sampleNews
//
//  Created by vincent on 2023/11/16.
//

import SwiftUI

@main
struct sampleNewsApp: App {
    
    var body: some Scene {
        WindowGroup {
            
            TabView {
                // 新闻页
                ContentView()
                    .tabItem {
                        Image(systemName: "newspaper")
                        Text("News")
                    }
                
                // 天气页
                WeatherView()
                    .tabItem {
                        Image(systemName:"cloud.sun")
                        Text("天气")
                    }
                
                // 优惠券页
                CouponsView()
                    .tabItem {
                        Image(systemName:"tag")
                        Text("优惠券")
                    }
                
                // 我的页面
                ProfileView()
                    .tabItem {
                        Image(systemName:"person")
                        Text("我的")
                    }
            }
        }
    }
}

// 天气页
struct WeatherView: View {
    var body: some View {
        Text("天气信息")
    }
}

// 优惠券页
struct CouponsView: View {
    var body: some View {
        Text("优惠券信息")
    }
}

// 我的页面
struct ProfileView: View {
    var body: some View {
        List {
            // 第一部分
            Section(header: Text("设定")) {
                NavigationLink(destination: Text("通知设定")) {
                    Text("通知设定和推送通知")
                }
                NavigationLink(destination: Text("地区")) {
                    Text("地区")
                }
            }
            
            // 第二部分
            Section(header: Text("用户管理")) {
                NavigationLink(destination: Text("用户设定")) {
                    Text("用户设定")
                }
                NavigationLink(destination: Text("退出")) {
                    Text("退出")
                }
            }
            
            // 第三部分
            Section(header: Text("其他")) {
                NavigationLink(destination: Text("帮助中心")) {
                    Text("帮助中心")
                }
                NavigationLink(destination: Text("写评论")) {
                    Text("写评论")
                }
                NavigationLink(destination: Text("隐私政策")) {
                    Text("隐私政策")
                }
            }
        }
        .listStyle(GroupedListStyle()) // 这个样式模仿iOS设置中的"分组"样式
        .navigationTitle("设定") // 导航栏的标题
    }
}
