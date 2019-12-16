#ifndef BANFEI_H

#define BANFEI_H

#include<iostream>
#include <string>
#include<fstream>
using namespace std;



class fee//费用类
{

public:
	fee(){};

public:
	int id;//编号
	int money;//金额
	string name;//经办人姓名
	string date;//时间
	string remark;//备注

	
};

class income:public fee//收入类，继承费用类
{
public:	
	void myprintf();//打印出需要的信息

};

class expend:public fee//支出类，继承费用类
{
public:	
	void myprintf();//打印出需要的信息

};


class banfei
{
public:
	void Menu();//菜单界面
	void add_income();//收入录入
	void add_expend();//支出录入
	void display_income();//收入显示
	void display_expend();//支出录入
	void search_income();//收入查找
	void search_expend();//支出查找
	void fixed_income();//收入修改
	void fixed_expend();//支出修改
	void savedata();//保存数据到文件
	void initdata();//初始化数据,从文件中读取


public:
	income im[50];//记录收入
	expend ed[50]; //记录支出
	int icount;//收入个数
	int ecount;//支出个数
};








#endif