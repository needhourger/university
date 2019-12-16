#-*- coding:utf-8 -*-
import json
import requests
import logging

from json import JSONDecodeError

logging.basicConfig(format="%(asctime)s-[%(levelname)s]: %(message)s",level=logging.INFO)

HEADER={
    "User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36 Edg/79.0.309.51",
    "Accept":"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Accept-Language": "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6",
    "Connection": "keep-alive",
    "Keep-Alive": "timeout=30"
}
url="https://movie.douban.com/j/search_subjects"
params={
    "type":"tv",
    "tag":"热门",
    "sort":"recommend",
    "page_limit":0,
    "page_start":0
}

def generate(num=20):
    params["page_limit"]=num
    logging.info("Running...")
    try:
        r=requests.get(url,params=params,headers=HEADER)
        # print(r.text)
    except:
        logging.warning("Network error!")
    try:
        data=json.loads(r.text)
        # print(data)
    except JSONDecodeError:
        logging.warning("Json cannot decode data!")
    data=data.get("subjects",[])
    for i,x in enumerate(data):
        logging.info("No.{}\t剧名:《{}》\t评分:{}\t链接:{}".format(i+1,x.get("title"),x.get("rate"),x.get("url")))
        x["No"]=i+1
    
    logging.info("Saveing...")
    with open("./result.json","w+",encoding="utf-8") as f:
        json.dump(data,f,sort_keys=True,indent=4,separators=(',',':'))
        logging.info("Save result complete!")
        return
    logging.warning("Cannot open file!")

if __name__ == "__main__":
    try:
        num=int(input("请输入爬取数量："))
    except:
        logging.warning("错误的输入数据！")
        exit(0)
    generate(num)

