# -*- coding:utf-8 -*-
from data import payload
import re
import requests
import logging
logging.basicConfig(
    format="%(asctime)s-[%(levelname)s]:%(message)s", level=logging.INFO)


indexUrl = "http://ids.nuist.edu.cn/amserver/UI/Login?goto=http://bkxk.nuist.edu.cn/Default_JZ.aspx"
loginUrl = "http://ids.nuist.edu.cn/amserver/UI/Login"

headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36"
}

username=input("Username:")
password=input("Password:")

session = requests.Session()
session.headers = headers
session.get(indexUrl)

loginData = {
    "IDToken1": username,
    "IDToken2": password,
    "goto": "aHR0cDovL2JreGsubnVpc3QuZWR1LmNuL0RlZmF1bHRfSlouYXNweA==",
    "encoded": "true",
    "gx_charset": "UTF-8"
}

r = session.get(loginUrl, params=loginData)
if r.url.endswith("public/newslist.aspx"):
    logging.info("登陆成功")
else:
    logging.warning("登录失败")
    exit()

pjUrl = r.url.replace("public/newslist.aspx", "student/wspj.aspx")
r = session.get(pjUrl)
tUrls = re.findall(r"<a href='(.*?)'>网上评教</a></span>", r.text)
for tUrl in tUrls:
    url = pjUrl.replace("wspj.aspx", tUrl)
    logging.info(url)

    r = session.get(url)
    text = r.content.decode("utf-8-sig").encode("utf-8").decode("utf-8")
    payload["__VIEWSTATE"] = re.findall(
        r'<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="(.*?)" />', text, re.S)[0]
    payload["__VIEWSTATEGENERATOR"] = re.findall(
        r'<input type="hidden" name="__VIEWSTATEGENERATOR" id="__VIEWSTATEGENERATOR" value="(.*?)" />', text)[0]
    payload["__EVENTVALIDATION"] = re.findall(
        r'<input type="hidden" name="__EVENTVALIDATION" id="__EVENTVALIDATION" value="(.*?)" />', text)[0]

    url = url.replace(r"pjmc=期末", r"pjmc=%u671f%u672b")
    logging.info(url)
    # logging.info(r.cookies)
    r = session.post(url, data=payload, headers={
        "Content-Type": "application/x-www-form-urlencoded",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36"
    })

    print(r.status_code)
