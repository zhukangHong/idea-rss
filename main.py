#!/usr/bin/python3
# -*- coding: utf-8 -*-
import datetime
import requests
from bs4 import BeautifulSoup, Comment
import xml.dom.minidom
import json
import PyRSS2Gen


def get_list():
    url = 'https://www.taoguba.com.cn/newIndex/getZh?pageNo=1'
    data = requests.get(url).text
    art_list = []

    data = json.loads(data)
    item_list = data.get("dto").get("list")

    for i in item_list:
        topic_id = i.get("topicID")
        feed = format_feed(topic_id)
        art_list.append(feed)

        # print(f"title:{feed.get('title')}\ncontent:{feed.get('content')}")
    return art_list


def create_item(doc, i):
    item = doc.createElement('item')
    item_title = doc.createElement('title')
    item_title.appendChild(doc.createTextNode(i.get('title')))
    item.appendChild(item_title)
    item_description = doc.createElement('description')
    item_description.appendChild(doc.createTextNode(i.get('content')))
    item.appendChild(item_description)
    item_date = doc.createElement('pubDate')
    item_date.appendChild(doc.createTextNode(str(i.get('date'))))
    item.appendChild(item_date)
    return item


def format_feed(topic_id):
    detail_url = "https://www.taoguba.com.cn/Article/" + topic_id + "/1"
    detail_html = requests.get(detail_url)
    soup = BeautifulSoup(detail_html.text, "lxml")
    find = soup.find("div", {"id": "first"})

    # Press the green button in the gutter to run the script.
    # [s.extract() for s in find("img")]
    [s.extract() for s in find("div")]
    [s.extract() for s in find("div")]
    comments = find.findAll(text=lambda text: isinstance(text, Comment))
    [comment.extract() for comment in comments]
    title = soup.find("b", {"id": "b_subject"}).text.replace(" ", "")
    content = find.text.replace("\n", "").replace(" ", "")
    date = soup.find("span", {"class": "pcyclspan"}).text

    res_obj = {"title": title, "content": content, "date": date}
    return res_obj


# def create_xml():
#     art_list = get_list()
#     # XML生成
#     doc = xml.dom.minidom.Document()
#     root = doc.createElement('rss')
#     root.setAttribute('version', '2.0')
#     doc.appendChild(root)
#     channel = doc.createElement('channel')
#     title = doc.createElement('title')
#     title.appendChild(doc.createTextNode('tgb'))
#     channel.appendChild(title)
#     for i in art_list:
#         item = create_item(doc, i)
#         channel.appendChild(item)
#
#     root.appendChild(channel)
#
#     # 写入xml
#     fp = open('e:\\rss.xml', 'w', encoding='gbk')
#     doc.writexml(fp, indent='\t', addindent='\t', newl='\n', encoding="gbk")


def create_rss(list):
    items_source = []

    for index in range(len(list)):
        items_source.append(PyRSS2Gen.RSSItem(title=list[index].get('title'),
                                              description=list[index].get('content').replace("\xa0", ""),
                                              pubDate=list[index].get('date')
                                              ))
    date = datetime.datetime.now().strftime("%Y-%m-%d")
    rss = PyRSS2Gen.RSS2(
        title="陶县",
        link="https://www.taoguba.com.cn/",
        description="kb personal rss source for taoguba",
        lastBuildDate=date,
        pubDate=date,
        items=items_source
    )
    fs = open("/home/rss/rss.xml", "w", encoding="utf-8")
    rss.write_xml(fs, encoding="utf-8")


if __name__ == '__main__':
    create_rss(get_list())
