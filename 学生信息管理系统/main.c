#include<stdio.h>
#include<windows.h>
#include<math.h>
#include<process.h>
#include<conio.h>

#define N 3
int num;
typedef struct {
	char no[10];      /*学号*/
	char name[20];    /*姓名*/
	int score[N];     /*N门功课成绩*/
	double total;     /*总分*/
	double aver;      /*平均分*/
	int order;        /*名次*/
} STU;

typedef struct node { 
	STU data;
	struct node *next;
} Node;

typedef struct {         
	Node *head,*tail;   
	int len;        
} LinkList;
LinkList *L;
void ShowMenu(LinkList *L);
void input(LinkList *L);
void output(LinkList *L);
void query(LinkList *L);
void querybyname(LinkList *L);
void querybyid(LinkList *L);
void Delete(LinkList *L);
void change(LinkList *L);
void addStu(LinkList *L);



int main() {
	LinkList *L = (LinkList *)malloc(sizeof(LinkList));
	do {
		system("cls");
		ShowMenu(L);
	} while(1);


	return 0;
}


void ShowMenu(LinkList *L) {
	int i;
	char s[10];

	char *p[7]= {"1、信息录入","2、信息输出","3、信息查询","4、插入学生信息","5、删除学生信息","6、修改学生信息","0、退出程序"};
	for(i=0; i<20; i++) {
		printf("*");
		if(i==10)
			printf("Menu");
		if(i==19)
			printf("\n");

	}
	for(i=0; i<7; i++) {
		printf("%s\n",p[i]);
	}
	for(i=0; i<20; i++) {
		printf("-");
		if(i==10)
			printf("Menu");
		if(i==19)
			printf("\n");

	}
	printf("请选择操作：");
	do {
		scanf("%s",s);
		i=atoi(s);
		if(i<0||i>7)
			printf("请重新输入有效操作：");
	} while(i<0||i>7);

	switch(i) {
		case 1:system("cls");input(L);break;
		case 2:system("cls");output(L);break;
		case 3:system("cls");query(L);break;
		case 4:system("cls");addStu(L);break;
		case 5:system("cls");Delete(L);break;
		case 6:system("cls");change(L);break;
		case 0:exit(0);
	}
}


void input(LinkList *L) {
	int i;
	Node *p,*q;
	L->len=0;
	printf("请输入学生人数：");
	scanf("%d",&num);
	printf("请输入学生的信息：");
	for(i=0; i<num; i++) {
		p=(Node*)malloc(sizeof(Node));
		p->next=NULL;
		if (i==0) {
			L->head=p;
			q=p;
		}
		else{
			q->next=p;
			q=p;/*   修改的部分    增加这一句   */
		}
		printf("第%d个学生：",i+1);
		printf("\n学号：");
		scanf("%s",p->data.no);
		printf("\n姓名：");
		scanf("%s",p->data.name);
		printf("\n语文成绩：");
		scanf("%d",&p->data.score[1]);
		printf("\n数学成绩：");
		scanf("%d",&p->data.score[2]);
		printf("\n英语成绩：");
		scanf("%d",&p->data.score[3]);
		p->data.total=p->data.score[1]+p->data.score[2]+p->data.score[3];
		p->data.aver=p->data.total/N;
		L->len++;
		L->tail=p;
	}
}


void sort(LinkList *L){
	Node* p;
	Node* q;
	STU t;
	int i,j;
	for (i=0;i<10;i++){
		p=L->head;
		while (p->next!=NULL){
			q=p;
			while (q->next!=NULL){
				if (q->data.total<q->next->data.total){
					t=q->data;
					q->data=q->next->data;
					q->next->data=t;
				}
				q=q->next;
			}
			p=p->next;	
		}
	}
}


void output(LinkList *L) {
	int i,j;
	Node *p;
	p=L->head;
/*	p=L->head->next;*/ 
	sort(L);
	printf("学生信息如下：\n");
	printf("学号\t姓名\t语文\t数学\t英语\t总分\t\t平均分\n");
	for(i=0; i<L->len; i++) {/*   修改的部分    把num修改为L->len   */
		/*p->data.total=p->data.score[1]+p->data.score[2]+p->data.score[3];
		p->data.aver=p->data.total/N;*/
		printf("%s\t%s\t%d\t%d\t%d\t%lf\t%lf\n",p->data.no,p->data.name,p->data.score[1],p->data.score[2],p->data.score[3],p->data.total,p->data.aver);
		p=p->next;
	}
	getch();

}


void query(LinkList *L) {
	int i;
	char x[10];
	Node *p;
	p=L->head->next;
	printf("若按姓名查找请输入“1”，若按学号查找请输入“2”：");
	do {
		scanf("%s",x);
		i=atoi(x);
		if(i<1||i>5)
			printf("请重新输入有效操作：");
	} while(i<1||i>2);
	switch(i) {
		case 1:querybyname(L);break;
		case 2:querybyid(L);break;
	}

}


void querybyname(LinkList *L) {
	char xingming[15];
	Node *p;
	p=L->head;/*   修改的部分    把L->head->next修改为L->head   */
	printf("请输入你要查找的姓名：");
	scanf("%s",xingming);
	while(p) {
		if(strcmp(p->data.name,xingming)==0) {
			printf("你要查找的学生信息如下：\n");
			printf("学号\t姓名\t语文\t数学\t英语\t总分\t\t平均分\n");
			printf("%s\t%s\t%d\t%d\t%d\t%f\t%f\n",p->data.no,p->data.name,p->data.score[1],p->data.score[2],p->data.score[3],p->data.total,p->data.aver);
			getch();
			return;
		} else
			p=p->next;
	}
	printf("查无此人！");
	getch();
}


void querybyid(LinkList *L) {
	char sid[10];
	Node *p;
	p=L->head;/*   修改的部分    把L->head->next修改为L->head   */
	p->data.total=p->data.score[1]+p->data.score[2]+p->data.score[3];
	p->data.aver=p->data.total/N;
	printf("请输入你要查找的学号：");
	scanf("%s",sid);
	while(p) {
		if(strcmp(p->data.no,sid)==0) {
			printf("你要查找的学生信息如下：\n");
			printf("学号\t姓名\t语文\t数学\t英语\t总分\t\t平均分\n");
			printf("%s\t%s\t%d\t%d\t%d\t%f\t%f\n",p->data.no,p->data.name,p->data.score[1],p->data.score[2],p->data.score[3],p->data.total,p->data.aver);
			getch();
			return;
		} else
			p=p->next;
	}
	printf("查无此人！\n");
	getch();
}


void addStu(LinkList *L) {/*     修改的部分    修改了函数参数*/

	Node *p,*q,*s;
	int count=1,n;
	p=(Node *)malloc(sizeof(Node));
	printf("请输入新学生的信息：\n");
	printf("\n学号：");
	scanf("%s",&p->data.no);
	printf("\n姓名：");
	scanf("%s",&p->data.name);
	printf("\n语文成绩：");
	scanf("%d",&p->data.score[1]);
	printf("\n数学成绩：");
	scanf("%d",&p->data.score[2]);
	printf("\n英语成绩：");
	scanf("%d",&p->data.score[3]);
	p->data.total=p->data.score[1]+p->data.score[2]+p->data.score[3];
	p->data.aver=p->data.total/N;
	p->next=NULL;
	printf("请输入你要插入的位置：");
	scanf("%d",&n);
	if (n<1) {
		free(p);
		return ;
	}
	q=L->head;
	s=L->head;
	while (count<n&&q->next!=NULL){
		count++;
		s=q;
		q=q->next;
	}
	if (count==1){
		p->next=L->head;
		L->head=p;
		L->len++;
	}
	else if (q->next==NULL){
		q->next=p;
		p->next=NULL;
		L->tail=p;
		L->len++;
	} 
	else{
		s->next=p;
		p->next=q;
		L->len++;
	}
}


void Delete(LinkList *L) {
	int x,y,count;
	Node *p,*q;
	printf("请输入你要删除的学号:");
	scanf("%d",&x); 
	p=L->head;
	q=p;
	count=1;
	while (count<=L->len){
		if (x==atoi(p->data.no)){
			if (L->head==p){
				L->head=p->next;
				L->len--;
				free(p);
				printf("删除成功！");
				getch();
				return;
			}
			else{
				q->next=p->next;
				L->len--;
				free(p);
				printf("删除成功！");
				getch();
				return;
			}
		}
		else{
			q=p;
			p=p->next;
			count++;
		}
	}
	printf("你输入的学号有误！");
	getch();
	return; 
}


void change(LinkList *L) {
	char n[10];
	printf("请输入需要修改的学号："); scanf("%s",n);
	Node* p=L->head;
	while (strcmp(p->data.no,n)!=0 && p->next!=NULL) p=p->next;
	if (strcmp(p->data.no,n)!=0){
		printf("查询不到该学生");
		getch();
		return;
	}
	printf("请输入新学生的信息：\n");
	printf("\n学号：");
	scanf("%s",&p->data.no);
	printf("\n姓名：");
	scanf("%s",&p->data.name);
	printf("\n语文成绩：");
	scanf("%d",&p->data.score[1]);
	printf("\n数学成绩：");
	scanf("%d",&p->data.score[2]);
	printf("\n英语成绩：");
	scanf("%d",&p->data.score[3]);
	p->data.total=p->data.score[1]+p->data.score[2]+p->data.score[3];
	p->data.aver=p->data.total/N;
	return;
}
