# -*- coding:utf-8 -*-
import tkinter as tk
import tkinter.messagebox
import tkinter.font

from Sql import Sql
from DefaultView import DefaultView

class LoginView(object):

    def __init__(self, master=None):
        super().__init__()

        self.root = master
        self.root.geometry("400x200")
        self.root.title("人事资源管理系统")
        self.root.resizable(False,False)
        self.username = tk.StringVar()
        self.password = tk.StringVar()

        self.create()

    def create(self):
        self.frame = tk.Frame(self.root)
        self.frame.pack(padx=20, pady=20, side=tk.TOP)

        self.frameInput=tk.Frame(self.frame)
        self.frameInput.pack(side=tk.TOP,pady=10,padx=10)

        tk.Label(self.frameInput, text="用户名", width=10).grid(
            row=0, column=0, stick=tk.W, pady=10, padx=10)
        tk.Label(self.frameInput, text="密  码", width=10).grid(
            row=1, column=0, stick=tk.W, pady=10, padx=10)
        tk.Entry(self.frameInput, width=20, textvariable=self.username).grid(
            row=0, column=1, stick=tk.E, pady=10)
        tk.Entry(self.frameInput, show="*", width=20,
                 textvariable=self.password).grid(row=1, column=1, stick=tk.E, pady=10)

        tk.Button(self.frame, text="登录", command=self.login,
                  width=20).pack(side=tk.BOTTOM,padx=10,pady=10)

    def login(self):
        username = self.username.get()
        password = self.password.get()

        vtype=Sql.login(username, password)
        if vtype!=-1:
            print("success {}".format(vtype))
            self.frame.destroy()
            DefaultView(self.root,vtype)
        else:
            tk.messagebox.showwarning("警告", "账户或者密码错误")
