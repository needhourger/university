#include<stdio.h>
#include<stdlib.h>

struct NODE{
	int data;
	NODE* next;
}; 

//初始化 
NODE* init(){
	return NULL;
}
//初始化 
void init(NODE* &head){
	head=NULL;
}


//末尾插入 
void insert(NODE* &head,int x){
	
	if (head==NULL){
		head=(NODE*)malloc(sizeof(NODE));
		head->data=x;
		head->next=NULL;
	}
	else{
		NODE* p=head;
		while (p->next!=NULL) p=p->next;
		NODE* t=(NODE*)malloc(sizeof(NODE));
		t->data=x;
		t->next=NULL;
		p->next=t;
	}
}

//删除元素按位置 
void del(NODE* &head,int dest){
	if (head==NULL){
		printf("[INFO] No data\n");
		return;
	}
	else{
		if (dest==0){
			NODE* t=head->next;
			free(head);
			head=t;
			printf("[INFO] Delete success\n");
		}
		else{
			int i=0;
			NODE* p=head;
			NODE* pre=p;
			while (i<dest && p->next!=NULL){
				i++;
				pre=p;
				p=p->next;
			}
			if (i<dest){
				printf("[INFO] Out of range\n");
				return;
			}
			
			pre->next=p->next;
			free(p);
			printf("[INFO] Delete success\n");
		}
	}
} 

//删除元素按数据
void del(NODE* &head,NODE* p){
	if (head==NULL){
		printf("[INFO] No data\n");
		return;
	}
	else{
		if (p==NULL){
			printf("[INFO] Delete fail\n");
			return;
		}
		else{
			NODE* pre=head;
			if(pre==p){
				head=pre->next;
				free(pre);
				printf("[INFO] Delete success\n");
				return;
			}
			while (pre->next!=p && pre->next!=NULL) pre=pre->next;
			if (pre->next!=p){
				printf("[INFO] Delete fail, no such destination\n");
				return;
			}
			else{
				pre->next=p->next;
				free(p);
				printf("[INFO] Delete success\n");
				return;
			}
		}
	}
} 

//范围性删除
void del(NODE* &head,int max,int min){
	if (head==NULL){
		printf("[INFO] No data\n");
		return;
	}
	NODE* p=head;
	while (p->next!=NULL){
		
		if (p->data>min && p->data<max) {
			NODE* t=p;
			p=p->next;
			printf("[INFO] delete data:%p\t%d\n",t,t->data);
			del(head,t);
			continue;
		}
		p=p->next;
	}
	if (p->data>min && p->data<max) {
		printf("[INFO] delete data:%p\t%d\n",p,p->data);
		del(head,p);
	}
	
} 

//获取数值 
int get(NODE* head,int dest){

	if (head==NULL){
		printf("[INFO] No data\n");
		return -1;
	}
	else{
		NODE* p=head;
		int i=0;
		while (i<dest && p->next!=NULL){
			i++;
			p=p->next;
		}
		if (i==dest) return p->data;
		else {
			printf("[INFO] Out of range\n");
			return -1;
		}
	}
}

//查找元素
NODE* search(NODE* head,int x){
	if (head==NULL){
		printf("[INFO] No data\n");
		return NULL;
	}
	else{
		NODE* p=head;
		while (p->data!=x && p->next!=NULL) p=p->next;
		if (p->data==x){
			printf("[INFO] Find\n");
			return p; 
		}
		else{
			printf("[INFO] No find\n");
			return NULL;
		}
	}
} 

//释放空间 
void clear(NODE* &head){
	NODE* p=head;
	NODE* pre=p;
	while (p!=NULL){
		p=pre->next;
		free(pre); 
		pre=p;
	}
	head=NULL;
} 

//打印 
void print(NODE* head){
	if (head==NULL){
		printf("[INFO] No data\n"); 
		return;
	}
	else{
		NODE* p=head;
		while (p!=NULL){
			printf("%d ",p->data);
			p=p->next;
		}
		printf("\n");
	}
}

//有序链表合并 
NODE* connect(NODE* head1,NODE* head2){
	if(head1==NULL){
		return head2;
	}
	else if(head2==NULL){
		return head1;
	}
	
	NODE* head=NULL; 
	if(head1->data<head2->data){
		head=head1;
		head->next=connect(head1->next,head2);
	}
	else{
		head=head2;
		head->next=connect(head1,head2->next);
	}
	return head;
}

int main(){
	NODE* head1;
	NODE* head2;
	init(head1);
	init(head2);
	
	for (int i=0;i<7;i++) insert(head1,i);
	printf("链表1:\n");
	print(head1);
	for (int i=4;i<10l;i++) insert(head2,i);
	printf("链表2:\n");
	print(head2);
	
	printf("\n合并后:\n"); 
	NODE* ans=connect(head1,head2);
	print(ans);
	return 0;
} 
