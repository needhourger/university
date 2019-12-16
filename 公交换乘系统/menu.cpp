#include<iostream>
#include<cstdio>
#include<cstdlib>
#include<vector>
#include<conio.h>
#include<set>
#include "BusLine.h"
#include "menu.h"

menu::menu()
{
}

menu::~menu()
{
}


//logo打印函数 
void logo(){
	cout<<"888888b.                      .d8888b.                    888                          "<<endl;
	cout<<"888  '88b                    d88P  Y88b                   888                          "<<endl;
	cout<<"888  .88P                    Y88b.                        888                          "<<endl;
	cout<<"8888888K.  888  888 .d8888b   'Y888b.   888  888 .d8888b  888888 .d88b.  88888b.d88b.  "<<endl;
	cout<<"888  'Y88b 888  888 88K          'Y88b. 888  888 88K      888   d8P  Y8b 888 '888 '88b "<<endl;
	cout<<"888    888 888  888 'Y8888b.       '888 888  888 ;Y8888b. 888   88888888 888  888  888 "<<endl;
	cout<<"888   d88P Y88b 888      X88 Y88b  d88P Y88b 888      X88 Y88b. Y8b.     888  888  888 "<<endl;
	cout<<"8888888P\"   \"Y88888  88888P'  '8888P'   'Y88888  88888P'  'Y888 ''Y8888  888  888  888 "<<endl;
	cout<<"                                             888                                       "<<endl;
	cout<<"                                        Y8b d88P                                       "<<endl;
	cout<<"                                         'Y88P'                                        "<<endl;
	return;
}


//线路站台打印函数，n控制打印多少条线路，默认0打印全部线路 
void stops_print(vector<BusLine> lines,int n=0){
	if (n==0){
		for (int i=0;i<lines.size();i++) lines[i].print();
		return;
	}
	for (int i=0;i<n && i<lines.size();i++)
	lines[i].print();
	return;
} 

//静态方法打印菜单选择界面 
void menu::print(vector<BusLine> lines){
	system("cls");
	logo();
	cout<<"======================================================================================="<<endl;
	stops_print(lines,2);
	cout<<"======================================================================================="<<endl;
	cout<<">                                                         --code by 陈明祥            <"<<endl;
	cout<<"======================================================================================="<<endl;
	cout<<"=                                1.查询线路                                           ="<<endl;
	cout<<"=                                2.查询站点                                           ="<<endl;
	cout<<"=                                3.出行规划                                           ="<<endl;
	cout<<"=                                4.全部线路                                           ="<<endl;
	cout<<"=                                0.退出                                               ="<<endl;
	cout<<"======================================================================================="<<endl;
	cout<<"=>Choice:";
	return;
}

//全部路线打印函数 
void allLinePrint(vector<BusLine> lines){
	system("cls");
	stops_print(lines,0);
	cout<<"按任意键继续"<<endl;
	getch();
}

//查询线路函数 
void inquireLine(vector<BusLine> lines){
	string name;
	cout<<"\n请输入您要查询的线路名称："; cin>>name;
	if (name.find("路")==string::npos) name=name+"路";
	for (int i=0;i<lines.size();i++){
		if (lines[i].getname()==name) {
			lines[i].print();
			cout<<"为您找到如上信息，按任意键继续"<<endl;
			getch();
			return;
		}
	}
	cout<<"\n抱歉未能找到该线路，按任意键继续"<<endl;
	getch();
	return;
}

//查询站台函数 
void inquireStop(vector<BusLine> lines){
	string name;
	bool flag=true;
	cout<<"\n请输入您要查询的站点名称："; cin>>name;
	cout<<"\n查询结果如下:"<<endl;
	
	for (int i=0;i<lines.size();i++)
		if (lines[i].hasStop(name)) {
			lines[i].print();
			flag=false;
		}
	
	if (flag) cout<<"\n抱歉路线经过该站点，按任意键继续"<<endl;
	else cout<<"以上线路经过此站，按任意键继续"<<endl;
	getch();
	return;
}

//线路规划函数（换乘函数） 
void routePlan(vector<BusLine> lines){
	string outset;
	string destination;
	BusLine a;
	BusLine b;
	bool flag=true;
	
	//读入起点终点信息 
	cout<<"\n请输入您的起点："; cin>>outset;
	cout<<"请输入您的终点："; cin>>destination;
	cout<<endl;
	
	//判断起点终点是否存在 
	for (int i=0;i<lines.size();i++)
	if (lines[i].hasStop(outset)){
		flag=false;
		a=lines[i];
		break;
	}
	
	if (flag){
		cout<<"\n抱歉，未能找到您输入的起始站点"<<endl;
		getch();
		return;
	}
	flag=true;
	
	for (int i=0;i<lines.size();i++)
	if (lines[i].hasStop(destination)){
		flag=false;
		b=lines[i];
		break; 
	}
	
	if (flag){
		cout<<"\n抱歉，未能找到您输入的终点站点"<<endl;
		getch();
		return; 
	}
	
	
//	路径规划原理
//	首先判断起点终点是否在同一条线上，如果在直接输出线路
//	如果不在
//	选取起点所有能够到达的站点作为集合A 
//	选取终点所有能够到达的站点作为集合B
//	集合AB的交集存在，那么交集就是换乘站点
	 
	//尝试路径规划 
	if (a.getname()==b.getname()){
		cout<<"\n为您规划路径如下"<<endl;
		cout<<"搭乘 "<<a.getname()<<endl;
		a.zone_print(outset,destination);
		getch();
		return;
	} 
	else{
		vector<string> node;				//存放交集元素 
		vector<string> tempA=a.getstops();	//存放出发点能够到达的所有站点 
		vector<string> tempB=b.getstops();	//存放终点能够到达的所有站点 
		
		//将站点信息排序为下面求交集做准备 
		sort(tempA.begin(),tempA.end()); 
		sort(tempB.begin(),tempB.end());
		//求交集 
		set_intersection(
			tempA.begin(),
			tempA.end(),
			tempB.begin(),
			tempB.end(),
			back_inserter(node)
		);
		
		//如果交集为空，则认为不存在换乘方法，交集不为空，打印换成方法。 
		if (node.empty()){
			cout<<"\n抱歉，未能找到合适的换乘路线，按任意键继续"<<endl;
			getch();
			return;
		}
		cout<<"\n为您找到如下路径可选择,按任意键继续"<<endl;
		for (int i=0;i<node.size();i++){
			//cout<<node[i]<<endl;
			int x,y;

			printf("第%d种路线\n",i+1);
			cout<<"========================================================="<<endl;
			cout<<"在 "<<outset<<" 搭乘 "<<a.getname()<<" 至 "<<node[i]<<endl<<endl;
			x=a.zone_print(outset,node[i]);
			cout<<endl;
			cout<<"在 "<<node[i]<<" 换乘 "<<b.getname()<<" 至 "<<destination<<endl<<endl; 
			y=b.zone_print(node[i],destination);
			printf("\n共计%d站路程\n\n\n",x+y); 
		}
		getch();
		return;
	}

	
}


//获取用户选择，以及相关处理函数 
void menu::getchoice(vector<BusLine> lines){
	string choice;
	cin>>choice;
	switch (choice[0]){
		case '1': inquireLine(lines); break;	//线路查询 
		case '2': inquireStop(lines); break;	//站台查询 
		case '3': routePlan(lines); break;		//路径规划 
		case '4': allLinePrint(lines); break;	//打印所有线路信息 
		case '0':  exit(0);						//退出 
	}
	
	
}

