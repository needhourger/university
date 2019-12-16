from flask import Flask,render_template,session,url_for,request,redirect
import pymysql

app=Flask(__name__)
HOST="localhost"
USER="testuser"
PASS="123456"
DATABASE="test"

@app.route("/")
def index():
    if 'username' in session:
        db=pymysql.connect(HOST,USER,PASS,DATABASE)
        cursor=db.cursor()
        cursor.execute("select count(*) from student")
        n=cursor.fetchone()[0]
        cursor.execute("select (@i:=@i+1) as i,no,name,birth,college from student,(select @i:=0) as it")
        students=cursor.fetchall()
        db.close()
        # print(students)
        return render_template('index.html',username=session['username'],totalStuNum=n,students=students)
    return redirect(url_for('login'))

@app.route("/search",methods=['GET','POST'])
def search():
    result=None
    if 'username' in session:
        if request.method=='GET':
            return render_template('search.html',username=session['username'],result=result)
        elif request.method=='POST':
            name=request.form["name"]
            no=request.form["no"]
            college=request.form["college"]
            db=pymysql.connect(HOST,USER,PASS,DATABASE)
            cursor=db.cursor()
            cursor.execute("select * from student where no='{}' or name='{}' or college='{}'".format(no,name,college))
            result=cursor.fetchall()
            db.close()
            return render_template('search.html',username=session['username'],result=result)
    return redirect(url_for('login'))

@app.route("/record",methods=['GET','POST'])
def record():
    if "username" in session:
        if request.method=='GET':
            return render_template("record.html",username=session['username'],result="")
        elif request.method=='POST':
            no=request.form["no"]
            name=request.form["name"]
            sex=request.form["sex"]
            birth=request.form["date"]
            college=request.form["college"]
            home=request.form["home"]
            db=pymysql.connect(HOST,USER,PASS,DATABASE)
            cursor=db.cursor()
            try:
                cursor.execute("insert into student (no,name,sex,birth,college,home) values ('{}','{}','{}','{}','{}','{}')".format(no,name,sex,birth,college,home))
                db.commit()
                return render_template("record.html",username=session['username'],result="插入成功")
            except:
                db.rollback()
                return render_template("record.html",username=session['username'],result="插入失败")
            db.close()
    return redirect(url_for('login'))

@app.route('/delete',methods=['GET','POST'])
def delete():
    if "username" in session:
        if request.method=='GET':
            db=pymysql.connect(HOST,USER,PASS,DATABASE)
            cursor=db.cursor()
            cursor.execute("select count(*) from student")
            n=cursor.fetchone()[0]
            db.close()
            # print(students)
            return render_template('delete.html',username=session['username'],totalStuNum=n)
        elif request.method=='POST':
            no=request.form["no"]
            db=pymysql.connect(HOST,USER,PASS,DATABASE)
            cursor=db.cursor()
            try:
                cursor.execute("delete from student where no={}".format(no))
                db.commit()
                return redirect(url_for('delete'))
            except:
                db.rollback()
                return redirect(url_for('delete'))
    return redirect(url_for('login'))


@app.route('/login',methods=['GET','POST'])
def login():
    if request.method=='POST':
        username=request.form['username']
        password=request.form['password']
        db=pymysql.connect(HOST,USER,PASS,DATABASE)
        cursor=db.cursor()
        cursor.execute("select count(*) from user where username='{}' and password='{}'".format(username,password))
        data=cursor.fetchone()
        db.close()
        if data[0]==1:
            session['username']=username
            return redirect(url_for('index'))
    return render_template('login.html')

@app.route('/logout')
def logout():
    session.pop('username',None)
    return redirect(url_for('login'))

@app.errorhandler(404)
def page_not_found(e):
    return render_template('404.html'),404

@app.errorhandler(405)
def page_not_found(e):
    return render_template('405.html'),405

@app.errorhandler(500)
def page_not_found(e):
    return render_template('500.html'),500

if __name__=="__main__":
    app.secret_key="sdhu21y2h3iosyd89132hdui918j2kwd"
    app.run(debug=True,port=80)