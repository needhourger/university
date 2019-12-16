#include "BusLine.h"

BusLine::BusLine()
{
}

//构造函数初始化函数信息 
BusLine::BusLine(string n,string t,vector<string> s){
	name=n;
	timezone=t;
	stops=s;
}

BusLine::~BusLine()
{
}


//判断是否含有某个站点 
bool BusLine::hasStop(string stop){
	
	for (int i=0;i<stops.size();i++)
		if (stop==stops[i]) return true;
	return false;
}



//站点信息打印 
void BusLine::print(){
	int t=0;
	cout<<name<<"\t"<<timezone<<"\t"<<stops.size()<<endl;
	for (int i=0;i<stops.size();i++)
		if (i!=stops.size()-1) {
			cout<<stops[i]<<"--";
			t++;
			if (t % 8==0) cout<<endl;
		}
		else cout<<stops[i]<<endl;
	cout<<endl; 
	return;
}

//获取线路名称 
string BusLine::getname(){
	return name;
}

//获取线路所有站台信息 
vector<string> BusLine::getstops(){
	return stops;
}


//打印从站台a到站台b的路径 
int BusLine::zone_print(string a,string b){
	int i=0;
	int j=0;
	while (stops[i]!=a && i<stops.size()) i++;
	while (stops[j]!=b && j<stops.size()) j++;
	if (i>j){
		for (int k=i;k>=j;k--)
		if (k!=j) cout<<stops[k]<<"-->";
		else cout<<stops[k]<<endl;
	}
	else{
		for (int k=i;k<=j;k++)
		if (k!=j) cout<<stops[k]<<"-->";
		else cout<<stops[k]<<endl;
	}
	return abs(i-j);
}
