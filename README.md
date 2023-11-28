# samplenews

## 演示
### 前端
#### iOS

### 后端

## 各个功能模块说明
### 前端
| 名称 | 功能 |
| ---- | ---- |
| Android | SampleNews的Android版。具有新闻预览、详细查看、突发新闻接收，优惠券（TODO）、消息交流（TODO）、会员信息管理（TODO）等功能 |
| iOS | SampleNews的iOS版。具有新闻预览，详细查看，突发新闻接收，优惠券（TODO）,消息（TODO），会员信息管理（TODO）等功能|
| BSAdmin | SampleNews的后台管理页面，基于Vue开发。可以对后台采集到的新闻进行增加、删除，查看等操作 |

### 后端
| 名称 | 功能 |
| ---- | ---- |
| Server | SampleNews的后台服务器容器，基于Nodejs开发 |
| Webcrawler | SampleNews的后台新闻数据采集程序，基于Python开发 |
| SQL | 数据存储，基于MySQL |

## 安装(CentOS)
### Python 安装执行
```
# yum -y install python
# which python3.6
/bin/python3.6
# pip3 install mysqlclient
# pip3 install beautifulsoup4
# python main.py
```
### Node.js环境和vue环境的安装
```
# curl -sL https://rpm.nodesource.com/setup_8.x | sudo bash -
# yum install nodejs
# node -v
# npm -v
# npm install express --save
# npm install -g @vue/cli
# vue --version
```
### MySQL环境的安装
```
# yum install -y mysql-community-server
# systemctl start mysqld
# systemctl stop mysqld
```

## 使用库说明
| 名称 | 概要 | 详情|
| ---- | ---- |---- |
| [Volley](https://github.com/mcxiaoke/android-volley) | 一个能让Android的网络请求更简单快捷的Http库 |Volley集成了AsyncHttpClient和Universal-Image-Loader的优点，既可以像AsyncHttpClient一样非常简单地进行HTTP通信，也可以像Universal-Image-Loader一样轻松加载网络上的图片。但是对大数据量的网络操作如文件的下载支持较差|
| [Glide](https://github.com/bumptech/glide) | Google推荐的图片加载和缓存的库 |专注于平滑滚动时的流畅加载, Google开源项目, 2014年Google I/O 上被推荐|
| [BeautifulSoup4](https://pypi.org/project/beautifulsoup4/) | 一个HTML/XML的解析器，主要的功能也是如何解析和提取HTML/XML 数据 |一个Python库，用于解析HTML和XML文档，并提供了一种简单而直观的方式来浏览、搜索和操作这些文档。 它将HTML/XML文档转换成一个Python对象树，可以使用Python的语法和方法来方便地提取所需的信息|
| [Express](https://expressjs.com/) | 专门对外提供API接口服务器 |一个简洁而灵活的 node.js Web应用框架, 提供了一系列强大特性帮助你创建各种 Web 应用，和丰富的 HTTP 工具|
