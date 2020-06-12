#-*- coding:utf-8 -*-
import sqlite3
import datetime

DATABASE="./data.db"

class sql(object):

    conn=sqlite3.connect(DATABASE)
    cursor=conn.cursor()

    @staticmethod
    def initDB():
        sql.cursor.execute("""
        create table if not exists user(
            id integer primary key,
            username text,
            password text)""")

        sql.cursor.execute("""
        create table if not exists meeting(
            id integer primary key,
            name text,
            starttime text,
            endtime text,
            describe text
        )
        """)
        sql.cursor.execute("""
        insert or replace into user (id,username,password) values (1,"admin","111111")
        """)
        sql.conn.commit()

    @staticmethod
    def login(username,password):
        res=sql.cursor.execute("""
        select password from user where username=?
        """,(username,))
        if res.fetchone()[0]==password:
            return True
        return False

    @staticmethod
    def getMeeting(key):
        ret=""
        
        res=sql.cursor.execute("""
        select * from meeting
        """)
        rows=res.fetchall()
        for row in rows:
            if key == row[0] or key in row[1] or key in row[4]:
                starttime=datetime.datetime.strptime(row[2],"%Y-%m-%d %H:%M")
                endtime=datetime.datetime.strptime(row[3],"%Y-%m-%d %H:%M")
                now=datetime.datetime.now()
                if now<starttime:
                    ret+="尚未开始\t"
                elif starttime<=now and now<=endtime:
                    ret+="正在举行\t"
                elif endtime<now:
                    ret+="已经结束\t"
                ret+=str(row[0])+'\t'+'\t'.join(row[1:])+"\n"
        if ret=="":
            ret="未查询到符合条件的会议记录"
        return ret

    @staticmethod
    def addMeeting(name,starttime,endtime,describe):
        sql.cursor.execute("""
        insert into meeting (name,starttime,endtime,describe) values (?,?,?,?)
        """,(name,starttime,endtime,describe))
        sql.conn.commit()

    @staticmethod
    def getAll(order=None):
        ret=""

        if not order:
            res=sql.cursor.execute("select * from meeting")
        else:
            if order=="1":
                res=sql.cursor.execute("select * from meeting order by id")
            elif order=="2":
                res=sql.cursor.execute("select * from meeting order by name")
            elif order=="3":
                res=sql.cursor.execute("select * from meeting order by starttime")
            elif order=="4":
                res=sql.cursor.execute("select * from meeting order by endtime")
            else:
                res=sql.cursor.execute("select * from meeting")
        
        rows=res.fetchall()
        for row in rows:
            starttime=datetime.datetime.strptime(row[2],"%Y-%m-%d %H:%M")
            endtime=datetime.datetime.strptime(row[3],"%Y-%m-%d %H:%M")
            now=datetime.datetime.now()
            if now<starttime:
                ret+="尚未开始\t"
            elif starttime<=now and now<=endtime:
                ret+="正在举行\t"
            elif endtime<now:
                ret+="已经结束\t"
            ret+=str(row[0])+'\t'+'\t'.join(row[1:])+"\n"
        return ret

    @staticmethod
    def deleteMeeting(mid):
        res=sql.cursor.execute("select count(*) from meeting where id=?",(mid,))
        row=res.fetchone()
        if row[0]!=1:
            return False

        res=sql.cursor.execute("""
        delete from meeting where id=?
        """,(mid,))
        sql.conn.commit()
        return True
    