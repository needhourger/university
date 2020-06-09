#-*- config:utf-8 -*-
import tkinter

from LoginView import LoginView
from Sql import Sql

Sql.initDB()

root=tkinter.Tk()
LoginView(root)
root.mainloop()
