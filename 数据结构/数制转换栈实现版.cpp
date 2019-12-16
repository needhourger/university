#include<iostream>
#include<string.h>
#include<cstdio>
#include<cstdlib>
#include<conio.h>
#include<cmath>
#define STACK_INIT_SIZE 100
#define STACKINCREMENT 10
#define MAXSIZE 256
using namespace std;

//栈的存储结构定义 
struct STACK {
	int* top;
	int* base;
	int size;
};

//栈初始化函数 
int init(STACK &s) {
	s.base=(int*) malloc(STACK_INIT_SIZE* sizeof(int));
	if (!s.base) {
		printf("[INFO] Malloc error!\n");
		return -1;
	}
	s.top=s.base;
	s.size=STACK_INIT_SIZE;
}

//获取栈顶元素函数 
int GetTop(STACK s) {
	if (s.top==s.base) {
		printf("[INFO] Stack empty!\n");
		return -1;
	}
	return *(s.top-1);
}

//向栈中压入数据函数 
int push(STACK &s,int x) {
	if (s.top-s.base>=s.size) {
		s.base=(int*)realloc(s.base,(s.size+STACKINCREMENT)*sizeof(int));
		if (!s.base) {
			printf("[INFO] Realloc error!\n");
			return -1;
		}
		s.top=s.base+s.size;
		s.size+=STACKINCREMENT;
	}
	*s.top++=x;
	return 0;
}

//从栈顶弹出数据函数 
int pop(STACK &s) {
	if (s.top==s.base) {
		printf("[INFO] Stack empty!\n");
		return -1;
	}
	return *(--s.top);
}

//判断栈是否为空 
bool IsEmpty(STACK s) {
	if (s.base==s.top) return true;
	else return false;
}

//打印栈内容 
int print(STACK s) {
	for (int* i=s.base; i!=s.top; i++) printf("%d ",*i);
	printf("\n");
}

//菜单打印 
void logo() {
	system("cls");
	printf("+===============================+\n");
	printf("+       数制转换-栈实现版       +\n");
	printf("+===============================+\n");
	printf("               - code by 尼玛才让\n\n");
	return;
}

int main() {
	while (true) {
		logo();				//菜单打印 
		string temp;		
		char q[MAXSIZE];
		int M,len,MD,target;
		STACK s;			//用以存储结果的栈的声明 

		cout<<"请输入数字的进制(2-16):";
		cin>>temp;
		M=atoi(temp.c_str());
		if (M<=1) {
			cout<<"输入的数据不符合规范"<<endl;
			getch();
			return 0;
		}

		cout<<"请输入该数字:";
		cin>>q;
		len=strlen(q);
		strupr(q);

		MD=0;
		for (int i=len-1; i>=0; i--) {
			if (q[i]<='9' && q[i]>='0') MD=MD+(q[i]-48)*pow(M,len-i-1);
			else if (q[i]>='A' && q[i]<='F') MD=MD+(q[i]-55)*pow(M,len-i-1);
		}
		cout<<"转换为10进制结果:"<<MD<<endl;

		cout<<"输入的目标进制(2-16):";
		cin>>temp;
		target=atoi(temp.c_str());
		if (target<2 && target >16) {
			cout<<"输入的数据不合法"<<endl;
			getch();
			return 0;
		}
		
		init(s);	//栈初始化 
		int t;		//暂存从栈顶弹出的元素 
		printf("转换结果:");
		while(MD){
			push(s,MD%target);
			MD=MD/target;
		}
		while (!IsEmpty(s)){
			if ((t=pop(s))>9) printf("%c",t+55);
			else printf("%d",t);
		}
		printf("\n");
		getch();
	}
	return 0;
}
