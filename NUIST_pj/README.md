# NUIST一键评教脚本

## 简介

某龙王山皇家气象学院教师评价系统一键脚本

## 依赖

* Python 3.5+
* requests

## 用法

```
python nuist_pj.py
```
1. 输入用户用密码
1. 脚本会自动获取您需要评课的所有课程老师，并自动为其打分


## 评教内容

* 默认评教全部按照9分处理
* 你可以自行定义评分，模板在data.py文件中
```
#-*- coding:utf-8 -*-
payload = {
    "__VIEWSTATE":"",
    "__VIEWSTATEGENERATOR": "",
    "__EVENTVALIDATION":"",
    # 修改RadioButton? 数字 1-9 对应 10-1 分数
    r"GridView1$ctl02$radiobtn": "RadioButton2",
    r"GridView1$ctl03$radiobtn": "RadioButton2",
    r"GridView1$ctl04$radiobtn": "RadioButton2",
    r"GridView1$ctl05$radiobtn": "RadioButton2",
    r"GridView1$ctl06$radiobtn": "RadioButton2",
    r"GridView1$ctl07$radiobtn": "RadioButton2",
    r"GridView1$ctl08$radiobtn": "RadioButton2",
    r"GridView1$ctl09$radiobtn": "RadioButton2",
    r"GridView1$ctl10$radiobtn": "RadioButton2",
    r"GridView1$ctl11$radiobtn": "RadioButton2",
    # TextBox4 意见和建议内容
    "TextBox4": "",
    "Button2": "提  交"
}
```