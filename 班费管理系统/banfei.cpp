#include<iostream>
#include <string>
#include<fstream>
#include "banfei.h"
using namespace std;

void banfei::initdata()
{
	ifstream infile("income.txt",ios::in);//打开收入文件
	if(!infile)
	{
		cerr<<"读取错误,无income.txt!"<<endl;
		return;
	}
	int n=0,i=0;
	icount =0;
	while(!infile.eof())
	{	
		infile>>im[i].id;
		infile>>im[i].money;
		infile>>im[i].name;
		infile>>im[i].date;
		infile>>im[i].remark;
		//cout<<im[i].id<<" "<<im[i].money<<" "<<im[i].name<<" "<<im[i].date<<" "<<im[i].remark<<endl;
		icount++;
		i++;
	}
	
	infile.close();
	cout<<"读取income.txt正常!"<<endl;

	ifstream exfile("expend.txt",ios::in);//打开支出文件
	if(!exfile)
	{
		cerr<<"读取错误,无expend.txt!"<<endl;
		return;
	}
	ecount =0;
	i=0;
	while(!exfile.eof())
	{	
		exfile>>ed[i].id;
		exfile>>ed[i].money;
		exfile>>ed[i].name;
		exfile>>ed[i].date;
		exfile>>ed[i].remark;
		//cout<<ed[i].id<<" "<<ed[i].money<<" "<<ed[i].name<<" "<<ed[i].date<<" "<<ed[i].remark<<endl;
		ecount++;
		i++;
	}
	
	exfile.close();
	cout<<"读取expend.txt正常!"<<endl;


};

void banfei::savedata()
{
	ofstream imfile("income.txt",ios::out);
	ofstream edfile("expend.txt",ios::out);
	int n=0;
	for(n=0;n<icount;n++)
	{	
		cout<<"保存成功"<<endl;
		imfile<<im[n].id<<"  ";
		imfile<<im[n].money<<"  ";
		imfile<<im[n].name<<"  ";
		imfile<<im[n].date<<"  ";
		imfile<<im[n].remark;
		imfile<<endl;
	}
	imfile.close();

	for(n=0;n<ecount;n++)
	{
		edfile<<ed[n].id<<"  ";
		edfile<<ed[n].money<<"  ";
		edfile<<ed[n].name<<"  ";
		edfile<<ed[n].date<<"  ";
		edfile<<ed[n].remark;
		edfile<<endl;
	} 
    edfile.close();
	cout<<"保存成功"<<endl;
	

};

void banfei::fixed_expend()
{
	int id,i;
	cout<<"请输入需要修改的编号"<<endl;
	cin>>id;
	for(i=0;i<icount;i++)
	{
		if(ed[i].id==id)
		{	
			cout<<"该编号的具体信息如下"<<endl;
			ed[i].myprintf();
			cout<<"请输入修改金额:"<<endl;
			cin>>ed[i].money;
			cout<<"请输入修改经办人姓名:"<<endl;
			cin>>ed[i].name;
			cout<<"请输入修改日期:"<<endl;
			cin>>ed[i].date;
			cout<<"请输入修改备注:"<<endl;
			cin>>ed[i].remark;
			cout<<"修改成功"<<endl;
			return;

		}
	}
	cout<<"输入有误，没有相应的编号"<<endl;
};

void banfei::fixed_income()
{
	int id,i;
	cout<<"请输入需要修改的编号"<<endl;
	cin>>id;
	for(i=0;i<icount;i++)
	{
		if(im[i].id==id)
		{	
			cout<<"该编号的具体信息如下"<<endl;
			im[i].myprintf();
			cout<<"请输入修改金额:"<<endl;
			cin>>im[i].money;
			cout<<"请输入修改经办人姓名:"<<endl;
			cin>>im[i].name;
			cout<<"请输入修改日期:"<<endl;
			cin>>im[i].date;
			cout<<"请输入修改备注:"<<endl;
			cin>>im[i].remark;
			cout<<"修改成功"<<endl;
			return;

		}
	}
	cout<<"输入有误，没有相应的编号"<<endl;


};

void banfei::search_expend()
{
	int i;
	string name;
	cout<<"请输入经办人姓名"<<endl;
	cin>>name;
	for(i=0;i<ecount;i++)
	{	
		if(name==ed[i].name)
			ed[i].myprintf();
	}
};

void banfei::search_income()
{
	int i;
	string name;
	cout<<"请输入经办人姓名"<<endl;
	cin>>name;
	for(i=0;i<icount;i++)
	{	if(name==im[i].name)
			im[i].myprintf();
	}
};

void banfei::display_expend()
{
	int i;
	for(i=0;i<ecount;i++)
	{
		ed[i].myprintf();
	}
};

void banfei::display_income()
{
	int i;
	for(i=0;i<icount;i++)
	{
		im[i].myprintf();
	}
};

void banfei::add_expend()
{	
	
	ed[ecount].id = ecount+1;
	cout<<"请输入金额:"<<endl;
	cin>>ed[ecount].money;
	cout<<"请输入经办人姓名:"<<endl;
	cin>>ed[ecount].name;
	cout<<"请输入日期:"<<endl;
	cin>>ed[ecount].date;
	cout<<"请输入备注:"<<endl;
	cin>>ed[ecount].remark;
	cout<<"添加成功"<<endl;
	ecount++;
};

void banfei::add_income()
{
	;
	im[icount].id = icount+1;
	cout<<"请输入金额:"<<endl;
	cin>>im[icount].money;
	cout<<"请输入经办人姓名:"<<endl;
	cin>>im[icount].name;
	cout<<"请输入日期:"<<endl;
	cin>>im[icount].date;
	cout<<"请输入备注:"<<endl;
	cin>>im[icount].remark;
	cout<<"添加成功"<<endl;
	icount++;
};

void banfei::Menu()
{
	int choice;
	system("cls");
	cout<<endl<<"请输入相应的操作序号进行操作:"<<endl;
	cout<<"㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣"<<endl;
	cout<<"㊣                                                        ㊣"<<endl;
	cout<<"㊣       1.收入录入                 2.支出录入            ㊣"<<endl;
	cout<<"㊣                                                        ㊣"<<endl;
	cout<<"㊣       3.收入显示                 4.支出显示            ㊣"<<endl;
	cout<<"㊣                                                        ㊣"<<endl;
    cout<<"㊣       5.收入查找                 6.支出查找            ㊣"<<endl;
	cout<<"㊣                                                        ㊣"<<endl;
	cout<<"㊣       7.收入修改                 8.支出修改            ㊣"<<endl;
	cout<<"㊣                                                        ㊣"<<endl;
	cout<<"㊣       9.保存数据                 10.退出系统           ㊣"<<endl;
	cout<<"㊣                                                        ㊣"<<endl;
	cout<<"㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣㊣"<<endl;
	cout<<"→";
	cin>>choice;
	while(choice<1||choice>10)
   { 
       cout<<"操作错误，请输入正确的操作序号!"<<endl;
       cout<<"→";
       cin>>choice;
   }
	switch(choice)
    {
    case 1: add_income();
        break;
    case 2:add_expend();
		break;
    case 3: display_income();
        break;
	case 4:display_expend();
		break;
    case 5: search_income();//收入查找
        break;
	case 6: search_expend();//支出查找
        break;
    case 7:fixed_income();//收入修改
		break;
    case 8: fixed_expend();//支出修改
        break;
	case 9:savedata();//保存数据到文件
		break;
    case 10: exit(0);
        break;
	default:
		break;
	
   }
	cout<<"...任意键回到主菜单..."<<endl;
	cin.get();
	cin.get();
	Menu();

};



void income::myprintf()
{
	cout<<"##############收入##################"<<endl;
	cout<<"#                                   #"<<endl;
	cout<<" 　　 编号："<<id<<"   金额: "<<money<<endl;
	cout<<"#                                   #"<<endl;
	cout<<" 　　 经办人："<<name<<"   时间: "<<date<<endl;
	cout<<"#                                   #"<<endl;
	cout<<" 　　 备注:　"<<remark<<endl;
	cout<<"#                                   #"<<endl;
	cout<<"#####################################"<<endl;
};
void expend::myprintf()
{
	cout<<"##############支出##################"<<endl;
	cout<<"#                                   #"<<endl;
	cout<<" 　　 编号："<<id<<"   金额: "<<money<<endl;
	cout<<"#                                   #"<<endl;
	cout<<" 　　 经办人："<<name<<"   时间: "<<date<<endl;
	cout<<"#                                   #"<<endl;
	cout<<" 　　 备注:　"<<remark<<endl;
	cout<<"#                                   #"<<endl;
	cout<<"#####################################"<<endl;
};