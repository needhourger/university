#include<iostream>
#include<string.h>	//strlen，atoi，itoa等函数的头文件 
#include<cstdio>	//c的基本输入输出函数的头文件 
#include<cstdlib>	//system函数的头文件 
#include<conio.h>	//getch函数头文件 
#include<math.h>	//数学运算pow头文件 
#define MAXSIZE 256	//定义数组以及字符串的最大长度 
using namespace std;

//菜单打印函数 
void menu(){
	system("cls");	//控制台清空 
    printf("+==========================================+\n");
    printf("+           课程设计-数制转换              +\n");
	printf("+==========================================+\n");
	printf("+             1.数组实现版                 +\n");
	printf("+             2.c++自带函数                +\n");
	printf("+             0.退出                       +\n"); 
	printf("+==========================================+\n");
	printf("                        code by 尼玛才让    \n");
	return;                                      
}


//数制转换数组实现版本函数 
void arrayVersion(){
	string temp;			//用来暂存用户的输入，使用string型防止用户错误输入使程序退出 
	char q[MAXSIZE];		//存放用户输入的数字 
	int M,len,MD,target;	//M数字进制；len用户输入数字的长度；MD转换为十进制后的结果；	
							//target目标进制 
	int ans[MAXSIZE];		//转换为任意进制后的结果存储 
	
	 
	cout<<"请输入数字的进制(2-16):"; cin>>temp;
	M=atoi(temp.c_str());
	//检测输入的数据是否合法 
	if (M<=1){
		cout<<"输入的数据不符合规范"<<endl;
		getch();
		return;
	}
	
	cout<<"请输入该数字:"; cin>>q;
	//获取该数字长度 
	len=strlen(q);
	//将数字中的小写字符转为大写字符方便后续处理 
	strupr(q);
	
	//转换为十进制 
	MD=0;
	for (int i=len-1;i>=0;i--){
		if (q[i]<='9' && q[i]>='0') MD=MD+(q[i]-48)*pow(M,len-i-1);
		else if (q[i]>='A' && q[i]<='F') MD=MD+(q[i]-55)*pow(M,len-i-1);
	}
	cout<<"转换为10进制结果:"<<MD<<endl;
	
	//转换目标进制 
	cout<<"输入的目标进制(2-16):"; cin>>temp;
	target=atoi(temp.c_str());
	if (target<2 && target >16){
		cout<<"输入的数据不合法"<<endl;
		getch();
		return;
	} 
	int p=0;	//数组的下标 
	while (MD!=0){
		ans[p++]=MD%target;
		MD=MD/target;
	}
	cout<<"转换结果:";
	for (int i=p-1;i>=0;i--)
	if (ans[i]>9) printf("%c",ans[i]+55);
	else cout<<ans[i];
	cout<<endl;
	getch();	//等待用户从键盘读取一个字符，用以暂停控制台展示结果 
	return;
}

//c++自带函数实现版本 
void funcVersion(){
	string temp;
	char q[MAXSIZE];
	int M,len,MD,target;
	char ans[MAXSIZE];
	
	cout<<"请输入数字的进制(2-16):"; cin>>temp;
	M=atoi(temp.c_str());
	if (M<=1){
		cout<<"输入的数据不符合规范"<<endl;
		getch();
		return;
	}
	
	cout<<"请输入该数字:"; cin>>q;
	len=strlen(q);
	strupr(q);
	
	MD=0;
	for (int i=len-1;i>=0;i--){
		if (q[i]<='9' && q[i]>='0') MD=MD+(q[i]-48)*pow(M,len-i-1);
		else if (q[i]>='A' && q[i]<='F') MD=MD+(q[i]-55)*pow(M,len-i-1);
	}
	cout<<"转换为10进制结果:"<<MD<<endl;
	
	cout<<"输入的目标进制(2-16):"; cin>>temp;
	target=atoi(temp.c_str());
	if (target<2 && target >16){
		cout<<"输入的数据不合法"<<endl;
		getch();
		return;
	}
	//itoa函数，将一个十进制数字转换为target目标进制的数
	//并以字符数组的形式将结果存储。这里即春初在了ans里 
	itoa(MD,ans,target);
	cout<<"转换结果:"; cout<<ans<<endl;
	getch();
	return;
	
}

int main(){
	string choice;	//存储用户菜单选择，用string型防止用户输入非法数据打断程序 
	while (true){
		menu();		//打印菜单 
		cin>>choice;
		switch(choice[0]){
			case '1': arrayVersion(); break;
			case '2': funcVersion(); break;
			case '0': return 0;
			default: continue;
		}
	}
	return 0;
}
