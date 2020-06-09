# -*- coding:utf-8 -*-
import tkinter as tk
from tkinter import ttk
import tkinter.messagebox

from Sql import Sql

class DefaultView(object):

    def __init__(self, master=None, vtype=0):
        super().__init__()

        self.root = master
        self.root.geometry("700x300")
        self.create(vtype)

    def create(self, vtype):
        if vtype == 1:
            self.frame = AdminView(self.root)
            # self.frame = UserView(self.root)
        else:
            self.frame = UserView(self.root)


class UserView(tk.Frame):

    def __init__(self, master=None):
        super().__init__(master=master)

        self.root = master
        self.wid = tk.StringVar()
        self.position = tk.StringVar()
        self.name = tk.StringVar()
        self.create()

    def create(self):
        self.pack()

        self.frameInput = tk.Frame(self)
        self.frameInput.pack(side=tk.TOP, padx=10, pady=10)

        tk.Label(self.frameInput, text="工号", width=10).grid(row=0, column=0)
        tk.Entry(self.frameInput, width=20,
                 textvariable=self.wid).grid(row=0, column=1)
        tk.Label(self.frameInput, text="姓名", width=10).grid(row=1, column=0)
        tk.Entry(self.frameInput, width=20,
                 textvariable=self.name).grid(row=1, column=1)
        tk.Label(self.frameInput, text="职位", width=10).grid(row=2, column=0)
        tk.Entry(self.frameInput, width=20,
                 textvariable=self.position).grid(row=2, column=1)
        tk.Button(self.frameInput, text="查询",
                  command=self.submit, width=15).grid(row=1, column=2, padx=10)
        tk.Button(self.frameInput, text="查询所有",
                  command=self.getAll, width=15).grid(row=2, column=2, padx=10)

        self.tvResult = ttk.Treeview(self, show="headings", columns=(
            "id", "username", "name", "position", "wage"), selectmode=tk.BROWSE)
        self.tvResult.column("id", anchor="center", width=140)
        self.tvResult.column("username", anchor="center", width=140)
        self.tvResult.column("name", anchor="center", width=140)
        self.tvResult.column("position", anchor="center", width=140)
        self.tvResult.column("wage", anchor="center", width=140)
        self.tvResult.heading("id", text="工号")
        self.tvResult.heading("username", text="用户名")
        self.tvResult.heading("name", text="姓名")
        self.tvResult.heading("position", text="职位")
        self.tvResult.heading("wage", text="薪资")
        self.tvResult.pack(side=tk.BOTTOM, padx=5, pady=5, fill=tk.BOTH)
        self.getAll()

    def submit(self):
        wid = self.wid.get()
        name = self.name.get()
        position = self.position.get()

        self.clearTvResult()
        print(wid, name, position)
        res = Sql.getInfo(wid, name, position)
        # print(res)
        for i, row in enumerate(res):
            print(row)
            self.tvResult.insert('', i, values=row)

    def clearTvResult(self):
        children = self.tvResult.get_children()
        if not children:
            return
        for i in self.tvResult.get_children():
            self.tvResult.delete(i)

    def getAll(self):
        self.clearTvResult()
        res = Sql.getAllUser()
        for i, row in enumerate(res):
            print(row)
            self.tvResult.insert('', i, values=row)

class AdminView(tk.Frame):

    def __init__(self, master=None):
        super().__init__(master=master)

        self.root = master
        self.root.geometry("700x600")

        self.wid = tk.StringVar()
        self.position = tk.StringVar()
        self.name = tk.StringVar()
        self.create()

    def create(self):
        self.pack()

        self.frameInput = tk.Frame(self)
        self.frameInput.grid(row=0, column=0, pady=10)

        tk.Label(self.frameInput, text="工号", width=10).grid(row=0, column=0)
        tk.Entry(self.frameInput, width=20,
                 textvariable=self.wid).grid(row=0, column=1)
        tk.Label(self.frameInput, text="姓名", width=10).grid(row=1, column=0)
        tk.Entry(self.frameInput, width=20,
                 textvariable=self.name).grid(row=1, column=1)
        tk.Label(self.frameInput, text="职位", width=10).grid(row=2, column=0)
        tk.Entry(self.frameInput, width=20,
                 textvariable=self.position).grid(row=2, column=1)
        tk.Button(self.frameInput, text="查询",
                  command=self.submit, width=15).grid(row=1, column=2, padx=10)
        tk.Button(self.frameInput, text="查询所有", width=15,
                  command=self.getAll).grid(row=2, column=2)

        self.tvResult = ttk.Treeview(self, show="headings", columns=(
            "id", "username", "password", "name", "position", "wage", "isAdmin"), selectmode=tk.BROWSE)
        self.tvResult.column("id", anchor="center", width=100)
        self.tvResult.column("username", anchor="center", width=100)
        self.tvResult.column("password", anchor="center", width=100)
        self.tvResult.column("name", anchor="center", width=100)
        self.tvResult.column("position", anchor="center", width=100)
        self.tvResult.column("wage", anchor="center", width=100)
        self.tvResult.column("isAdmin", anchor="center", width=100)
        self.tvResult.heading("id", text="工号")
        self.tvResult.heading("username", text="用户名")
        self.tvResult.heading("password", text="密码")
        self.tvResult.heading("name", text="姓名")
        self.tvResult.heading("position", text="职位")
        self.tvResult.heading("wage", text="薪资")
        self.tvResult.heading("isAdmin", text="是否为管理员")
        self.tvResult.grid(row=1, column=0)
        self.tvResult.bind("<ButtonRelease-1>", self.tvOnClick)
        self.getAll()

        self.frameAdd = tk.LabelFrame(self)
        self.frameAdd.grid(row=2, column=0, pady=10)

        tk.Label(self.frameAdd, text="工号", width=10).grid(row=0, column=0)
        tk.Label(self.frameAdd, text="用户名", width=10).grid(row=1, column=0)
        tk.Label(self.frameAdd, text="密码", width=10).grid(row=2, column=0)
        tk.Label(self.frameAdd, text="姓名", width=10).grid(row=3, column=0)
        tk.Label(self.frameAdd, text="职位", width=10).grid(row=4, column=0)
        tk.Label(self.frameAdd, text="薪资", width=10).grid(row=5, column=0)
        tk.Label(self.frameAdd, text="是否为管理员", width=10).grid(row=6, column=0)

        self.eId = tk.Entry(self.frameAdd, width=60)
        self.eId.grid(row=0, column=1)
        self.eUsername = tk.Entry(self.frameAdd, width=60)
        self.eUsername.grid(row=1, column=1)
        self.ePassword = tk.Entry(self.frameAdd, width=60)
        self.ePassword.grid(row=2, column=1)
        self.eName = tk.Entry(self.frameAdd, width=60)
        self.eName.grid(row=3, column=1)
        self.ePosition = tk.Entry(self.frameAdd, width=60)
        self.ePosition.grid(row=4, column=1)
        self.eWage = tk.Entry(self.frameAdd, width=60)
        self.eWage.grid(row=5, column=1)
        self.cIsAdmin = ttk.Combobox(self.frameAdd)
        self.cIsAdmin["value"] = ("否", "是")
        self.cIsAdmin.current(0)
        self.cIsAdmin.grid(row=6, column=1)

        tk.Button(self, text="提交修改/新增", width=15,
                  command=self.commitChange).grid(row=3, column=0)
        tk.Button(self, text="删除选中", width=15,
                  command=self.deleteSelect).grid(row=4, column=0, pady=10)

    def submit(self):
        wid = self.wid.get()
        name = self.name.get()
        position = self.position.get()

        self.clearTvResult()
        print(wid, name, position)
        res = Sql.getInfoAdmin(wid, name, position)
        # print(res)
        for i, row in enumerate(res):
            print(row)
            self.tvResult.insert('', i, values=row)

    def clearTvResult(self):
        children = self.tvResult.get_children()
        if not children:
            return
        for i in self.tvResult.get_children():
            self.tvResult.delete(i)

    def getAll(self):
        self.clearTvResult()
        res = Sql.getAllAdmin()
        for i, row in enumerate(res):
            print(row)
            self.tvResult.insert('', i, values=row)

    def deleteSelect(self):
        item = self.tvResult.item(self.tvResult.focus(), "values")
        if item:
            if tk.messagebox.askokcancel("提示", "确认删除选中项"):
                wid = item[0]
                if Sql.deleteUser(wid):
                    self.getAll()
                else:
                    tk.messagebox.showerror("警告", "系统中必须至少存在一个管理员账户")

    def tvOnClick(self, event):
        item = self.tvResult.item(self.tvResult.focus(), "values")
        if item:
            self.eId.delete(0, tk.END)
            self.eId.insert(0, item[0])
            self.eUsername.delete(0, tk.END)
            self.eUsername.insert(0, item[1])
            self.ePassword.delete(0, tk.END)
            self.ePassword.insert(0, item[2])
            self.eName.delete(0, tk.END)
            self.eName.insert(0, item[3])
            self.ePosition.delete(0, tk.END)
            self.ePosition.insert(0, item[4])
            self.eWage.delete(0, tk.END)
            self.eWage.insert(0, item[5])
            self.cIsAdmin.current(int(item[6]))

    def commitChange(self):
        wid = self.eId.get()
        username = self.eUsername.get()
        password = self.ePassword.get()
        name = self.eName.get()
        position = self.ePosition.get()
        wage = self.eWage.get()
        isAdmin = (1 if self.cIsAdmin.get() == "是" else 0)
        Sql.update(wid, username, password, name, position, wage, isAdmin)
        self.getAll()

        
        