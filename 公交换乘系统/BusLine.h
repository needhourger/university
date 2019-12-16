#ifndef BUSLINE_H
#define BUSLINE_H
#include<iostream>
#include<cstdio>
#include<vector>
#include<algorithm>


using namespace std;

class BusLine
{
	public:
		BusLine();
		//构造函数初始化传入数据 
		BusLine(string n,string t,vector<string> s); 
		~BusLine();
		//站台信息打印函数 
		void print();
		//判断该线路内是否含有某个站台stop 
		bool hasStop(string stop);
		//获取线路名称 
		string getname();
		//获取线路的所有站台信息 
		vector<string> getstops();
		//打印从站台a 到b的路线 
		int zone_print(string a,string b);
	private:
		string name;		//存储线路信息 
		string timezone;	//存储线路运行时刻 
		vector<string> stops;	//存储站台信息 
		
};

#endif
