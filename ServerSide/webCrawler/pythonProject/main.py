import requests
from bs4 import BeautifulSoup
import mysql.connector
from mysql.connector import Error

# 数据库连接配置
db_config = {
    'host': '127.0.0.1',  # 数据库服务器地址
    'user': 'root',       # 数据库用户名
    'password': '',       # 数据库密码
    'database': 'samplenews'  # 数据库名称
}

# 创建数据库连接
def create_db_connection(db_config):
    try:
        connection = mysql.connector.connect(**db_config)  # 使用配置创建数据库连接
        if connection.is_connected():  # 检查是否成功连接
            return connection
    except Error as e:
        print("Error while connecting to MySQL", e)
        return None

# 将数据存入数据库
def store_data(title, content, image_url, connection):
    try:
        cursor = connection.cursor()  # 创建数据库游标
        query = "INSERT INTO sn_news (title, content, imageUrl) VALUES (%s, %s, %s)"  # 插入数据的SQL查询
        cursor.execute(query, (title, content, image_url))  # 执行SQL查询，插入数据
        connection.commit()  # 提交事务
    except Error as e:
        print("Error while storing data in MySQL", e)

# 解析网页内容
def parse_webpage(url):
    # 发送HTTP GET请求获取网页内容
    response = requests.get(url)
    # 使用BeautifulSoup解析网页
    soup = BeautifulSoup(response.content, 'html.parser')

    # 这里需要根据实际网页结构进行调整
    # 定位到 <div class="sec-latest"> 并获取所有的 <li> 元素
    sec_latest = soup.find('div', class_='sec-latest')
    news_items = sec_latest.find_all('li') if sec_latest else []

    for item in news_items[:10]:  # 限制为前10个新闻项
        # 获取新闻标题
        title = item.find('h3').text.strip() if item.find('h3') else 'No Title'
        # 获取新闻内容
        content = item.find('p', class_='latesttxt').text.strip() if item.find('p',
                                                                               class_='latesttxt') else 'No Content'
        # 查找图片标签
        image_tag = item.find('img')
        image_url = image_tag['data-src'] if image_tag and 'data-src' in image_tag.attrs else 'No Image URL'  # 获取图片URL

        # 使用生成器返回新闻数据
        yield title, content, image_url

# 主函数
def main():
    url = 'https://china.kyodonews.net/'  # 网址
    connection = create_db_connection(db_config)  # 创建数据库连接

    if connection:
        for title, content, image_url in parse_webpage(url):  # 遍历解析的新闻数据
            store_data(title, content, image_url, connection)  # 存储新闻数据到数据库
        connection.close()  # 关闭数据库连接

if __name__ == "__main__":
    main()  # 调用主函数开始执行程序