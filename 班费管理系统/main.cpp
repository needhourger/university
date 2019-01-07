#include<iostream>
#include <string>
#include<fstream>
#include "banfei.h"
using namespace std;

//extern banfei bf;

void load()
{ifstream infile("zh.txt",ios::in);
if(!infile)
{cerr<<"读取错误,无资料中!"<<endl;
return;
}
int n=0;
int id,m,acnum;
string nam,passw;
infile>>acnum;
for(n=0;n<acnum;n++)
{
infile>>id;
infile>>m;
infile>>nam;
infile>>passw;
cout<<id<<" "<<m<<" "<<nam<<" "<<passw<<endl;

}
infile.close();
cout<<"读取资料正常!"<<endl;
}


int main()
{	//load();
	banfei bf;
	bf.initdata();
	bf.Menu();
	getchar();
	return 1;
}