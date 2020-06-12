#-*- coding:utf-8 -*-
import os
import getpass
from Sql import sql
from AdminView import AdminView

class LoginView(object):
    
    def __init__(self):
        super().__init__()
        
        while True:
            self.printView()
            username=input("username:")
            password=getpass.getpass()
            if sql.login(username,password):
                AdminView(username)
            else:
                print("账户名或者密码错误")
                input("按回车键继续")

    def printView(self):
        os.system("cls")
        print("""
        ===========================
        》      会议管理系统      《
        ===========================
        """)