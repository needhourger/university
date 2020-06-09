#-*- coding:utf-8 -*-
import sqlite3

class Sql():

    DATABASE="./data.db"

    conn=sqlite3.connect(DATABASE)
    cursor=conn.cursor()

    @staticmethod
    def initDB():
        Sql.cursor.execute("""
        create table if not exists users(
            id integer primary key,
            username text,
            password text,
            name text,
            position text,
            wage real,
            isAdmin boolean
        )""")
        Sql.cursor.execute("""
        insert or replace into users (id,username,password,name,position,wage,isAdmin) values (1,"admin","123456","admin","admin",10000.0,true)
        """)
        # for i in range(2,22):
        #     Sql.cursor.execute("""
        #     insert or replace into users (id,username,password,name,position,wage,isAdmin) values (?,"user{}","111111","小明","员工",1000.0,false)
        #     """.format(i-1),(i,))
        Sql.conn.commit()


    @staticmethod
    def setDataBase(database):
        Sql.conn.close()
        Sql.DATABASE=database
        Sql.conn=sqlite3.connect(Sql.DATABASE)
        Sql.cursor=Sql.conn.cursor()

    @staticmethod
    def login(username,password):
        res=Sql.cursor.execute(
            """
            select password,isAdmin from users where username=?
            """,
            (username,)
        )
        for row in res.fetchall():
            if row and row[0]==password:
                return row[1]
        return -1

    @staticmethod
    def getInfo(wid,name,position):
        res=Sql.cursor.execute("""
            select id,username,name,position,wage from users where id=? or name=? or position=?
        """,(wid,name,position))
        ret=res.fetchall()
        return ret

    @staticmethod
    def getInfoAdmin(wid,name,position):
        res=Sql.cursor.execute("""
            select id,username,password,name,position,wage from users where id=? or name=? or position=?
        """,(wid,name,position))
        ret=res.fetchall()
        return ret

    @staticmethod
    def getAllAdmin():
        res=Sql.cursor.execute("""
            select * from users
        """)
        ret=res.fetchall()
        return ret

    @staticmethod
    def getAllUser():
        res=Sql.cursor.execute("""
            select id,username,name,position,wage from users;
        """)
        ret=res.fetchall()
        return ret

    @staticmethod
    def checkAdmin(wid):
        res=Sql.cursor.execute("""
        select isAdmin from users where id=?
        """,(wid,))
        if res.fetchone()[0]==0:
            return True
        res=Sql.cursor.execute("""
        select count(*) from users where isAdmin=true
        """)
        if res.fetchone()[0]>1:
            return True
        return False


    @staticmethod
    def deleteUser(wid):
        if Sql.checkAdmin(wid):
            Sql.cursor.execute("""
                delete from users where id=?
            """,(wid,))
            Sql.conn.commit()
            return True
        else:
            return False

    @staticmethod
    def update(wid,username,password,name,position,wage,isAdmin):
        Sql.cursor.execute("""
            insert or replace into users (id,username,password,name,position,wage,isAdmin) values (?,?,?,?,?,?,?)
        """,(wid,username,password,name,position,wage,isAdmin))
        Sql.conn.commit()