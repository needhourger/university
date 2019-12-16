#include<iostream>
#include<fstream>
#include<vector>
#include<algorithm>
#include<sstream>
#include<conio.h>

#include "BusLine.h"
#include "menu.h"
using namespace std;

vector<BusLine> lines;

//分割字符串函数，用以站点数据的读入处理 
vector<string> &split(const string &str, char delim, vector<string> &elems, bool skip_empty = true) {
    istringstream iss(str);
    for (string item; getline(iss, item, delim); )
        if (skip_empty && item.empty()) continue;
        else elems.push_back(item);
    return elems;
}

//从文件中读取线路数据 
void loadData(){
	string name;		//线路名称 
	string timezone;	//线路的运行时间 
	string temp;		//暂存站台信息的字符串 
	char buf[1024];
	vector<string> stops;	//从temp中分割出的站台数据的存放点 
	
	//检查文件是否存在 
	ifstream f("./lineData.txt");
	if (!f.is_open()){
		cout<<"请检查数据文件完整性"<<endl;
		getch();
		exit(0); 
	}
	
	//读取文件内容 
	while(!f.eof()){
		stops.clear();
		f.getline(buf,1024);
		name=string(buf);
		
		f.getline(buf,1024);
		timezone=string(buf);
		
		f.getline(buf,1024);
		temp=string(buf);
		split(temp,' ',stops);
		 
		lines.push_back(BusLine(name,timezone,stops));
		f.getline(buf,1024);
	}
	
	f.close();
	return;
}

int main(){
	
	loadData();
	
	//主界面函数运行 
	while (true){
		menu::print(lines);
		menu::getchoice(lines);
	} 
	
	return 0;
}
