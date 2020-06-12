#-*- coding:utf-8 -*-
import os
import datetime

from Sql import sql

class AdminView(object):

    def __init__(self,username):
        super().__init__()
        self.username=username

        while True:
            self.printView("main")
            c=input("请输入您的选择:")

            if c=="1":
                self.queryMeeting()
            elif c=="2":
                self.addMeeting()
            elif c=="3":
                self.deleteMeeting()
            elif c=="4":
                self.listMeeting()
            elif c=="0":
                return
            else:
                continue

    def printView(self,view):
        os.system("cls")
        print("""
        ===============================
        》 会议管理系统   欢迎您：{}
        ===============================
        """.format(self.username))
        if view=="main":
            print("""
            1. 会议查询
            2. 会议新增
            3. 会议删除
            4. 会议概览
            0.   退出
            """)
        elif view=="list":
            print("排序方式  1.id 2.名称 3.开始时间 4.结束时间 0.退出\n")
            print("状态\t\tID\t名称\t开始\t\t结束\t\t会议描述")
        else:
            return

    def queryMeeting(self):
        self.printView("query")
        
        key=input("请输入会议ID/包含关键词\n")
        print("\n")
        print("状态\t\tID\t名称\t开始\t\t结束\t\t会议描述")
        print(sql.getMeeting(key))
        input("按回车键继续")

    def addMeeting(self):
        self.printView("add")

        name=input("请输入会议名称\n")
        starttime=input("请输入会议开始时间[%Y-%m-%d %H:%M]\n")
        endtime=input("请输入会议结束时间[%Y-%m-%d %H:%M]\n")
        describe=input("请输入会议描述\n")

        t1=datetime.datetime.strptime(starttime,"%Y-%m-%d %H:%M")
        t2=datetime.datetime.strptime(endtime,"%Y-%m-%d %H:%M")
        if t2<t1:
            print("会议开始时间不得晚于结束时间！")
            input("添加失败，按回车键结束！")
            return
        
        sql.addMeeting(name,starttime,endtime,describe)

    def deleteMeeting(self):
        self.printView("delete")

        print("状态\tID\t名称\t开始\t结束\t会议描述")
        print(sql.getAll())

        mid=input("请输入要删除的会议ID:\n")
        if not sql.deleteMeeting(mid):
            print("您要删除的会议不存在")
        else:
            print("会议删除成功")
        input("按回车键继续")

    def listMeeting(self):
        order="1"
        while True:
            self.printView("list")

            print(sql.getAll(order))
            order=input("请输入你的排序选择:")
            if order=="0":
                break



        