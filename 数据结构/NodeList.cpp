#include<iostream>
#include<stdio.h>
#include<stdlib.h>
using namespace std;

/*
定义单个链表节点结构体 
value	存储链表节点数据
next	存储指向下一个链表节点的指针 
*/
struct Node{
	int value;
	Node* next;
};

/*
定义链表结构体
header	存储链表头节点
length 	存储链表元素个数 
*/
struct LinkList{
	Node* header;
	int length;
};

/*
创建单个链表节点
将链表节点值初始化为传入参数v的值
将链表节点指向下一个链表节点的指针置为NULL(0) 
*/
Node* createNode(int v){
	Node* node=new Node;
	node->value=v;
	node->next=NULL;
	return node;
}

/*
创建链表
根据传入参数length创建具有length个节点的链表 
*/
LinkList createLinkList(int length){ 
	LinkList list;
	list.header=createNode(0);
	list.length=length;
	Node* p=list.header;
	int i=0;
	while (i<length-1){
		p->next=createNode(++i);
		p=p->next;
	}
	return list;
}


/*
打印链表内所有节点
*/
void print(LinkList list){
	Node* p=list.header;
	cout<<"NodeList length:"<<list.length<<endl;
	int i=0;
	while(p){
		printf("Node %d\t\tValue:%d\n",i++,p->value);
		p=p->next;
	}
	cout<<"========================"<<endl<<endl;;
	return;
}

/*
插入链表节点
在list链表pos位置处插入链表节点node 
*/
LinkList insertNode(LinkList list,Node* node,int pos){
	if (pos<=0){
		node->next=list.header;
		list.header=node;
	}
	else if (pos>=list.length){
		Node* p=list.header;
		for (int i=0;i<list.length-1;i++) p=p->next;
		p->next=node;
	}
	else {
		Node* p=list.header;
		for (int i=0;i<list.length && i<pos-1;i++) p=p->next;
		Node* temp=p->next;
		p->next=node;
		node->next=temp;
	}
	list.length++;
	return list;
}


/*
插入链表节点
在list链表pos位置插入一个节点其值为value 
*/
LinkList insertNode(LinkList list,int value,int pos){
	Node* node=createNode(value);
	if (pos<=0){
		node->next=list.header;
		list.header=node;
	}
	else if (pos>=list.length){
		Node* p=list.header;
		for (int i=0;i<list.length-1;i++) p=p->next;
		p->next=node;
	}
	else{
		Node* p=list.header;
		for (int i=0;i<list.length && i<pos-1;i++) p=p->next;
		Node* temp=p->next;
		p->next=node;
		node->next=temp;
	}
	list.length++;
	return list;
}

/*
删除链表节点
删除链表list在pos位置处的节点 
*/
LinkList deleteNode(LinkList list,int pos){
	if (pos<=0){
		Node* temp=list.header;
		list.header=list.header->next;
		delete temp;
	}
	else if (pos>=list.length){
		Node* p=list.header;
		for (int i=0;i<list.length-1;i++) p=p->next;
		delete p->next;
		p->next=NULL;
	}
	else{
		Node* p=list.header;
		for (int i=0;i<list.length && i<pos-1;i++) p->next;
		Node* temp=p->next;
		p->next=temp->next;
		delete temp;
	}
	list.length--;
	return list;
}

/*
修改链表节点 
修改链表list在pos位置的值为value 
*/
void updateNode(LinkList list,int value,int pos){
	Node* p=list.header;
	for(int i=0;i<list.length-1 && i<pos;i++) p=p->next;
	p->value=value;
}

/*
查询链表list在pos位置的值并打印其值 
*/
void queryNode(LinkList list,int pos){
	if (pos<0 || pos>=list.length){
		cout<<"Position out of range!"<<endl;
		cout<<"========================"<<endl<<endl;;
		return;
	}
	Node* p=list.header;
	for (int i=0;i<list.length-1 && i<pos;i++) p=p->next;
	printf("Node %d\t\tvalue %d\n",pos,p->value);
	cout<<"========================"<<endl<<endl;;
	return; 
}
 
/*
查询链表list中值为value的节点，并打印节点 
*/
void queryValue(LinkList list,int value){
	Node* p=list.header;
	bool flag=true;
	for (int i=0;i<list.length;i++){
		if (p->value==value){
			printf("Node %d\t\tvalue %d\n",i,p->value);
			flag=false;
		}
		p=p->next;
	}
	if (flag) {
		cout<<"No Node's value="<<value<<endl;
		cout<<"========================"<<endl<<endl;;
	}
}


int main(){
	cout<<"Create linklist:"<<endl;
	cout<<"------------------------"<<endl;
	LinkList list=createLinkList(10);
	print(list);
	
	cout<<"Insert node into list:"<<endl;
	cout<<"------------------------"<<endl;
	list=insertNode(list,createNode(10),10);
	print(list);
	
	cout<<"Insert value into list:"<<endl;
	cout<<"------------------------"<<endl;
	list=insertNode(list,2,2);
	print(list);
	
	cout<<"Delete node from list"<<endl;
	cout<<"------------------------"<<endl;
	list=deleteNode(list,5);
	print(list);
	
	cout<<"Update Node in list"<<endl;
	cout<<"------------------------"<<endl;
	updateNode(list,5,0);
	print(list);
	
	cout<<"Query Node in list"<<endl;
	cout<<"------------------------"<<endl;
	queryNode(list,2);
	
	cout<<"Query value in list"<<endl;
	cout<<"------------------------"<<endl;
	queryValue(list,0);
	return 0;
} 
