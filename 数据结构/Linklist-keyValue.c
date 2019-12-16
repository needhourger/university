#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h> 
#define null 0


struct Data{
	int key;
	int value;
};

struct Node{
	struct Data* data;
	struct Node* next;
};

struct LinkList{
	struct Node* head;
	struct Node* tail;
};

char UserInterface(){
	fflush(stdin);
//	system("cls");
//	system("clean");
	printf(
	"\n\n=======>User Interface<=======\n"
	"	1.add first\n"
	"	2.add last\n"
	"	3.add after\n"
	"	4.search\n"
	"	5.print\n"
	"	6.update\n"
	"	7.remove\n"
	"	0.exit\n"
	"==============================\n"
	">Your Choice:"
	);
	return getchar();
}

bool isListEmpty(struct LinkList* list){
	return list->head==null;
} 


struct LinkList createLinkList(){
	struct LinkList ret;
	ret.head=null;
	ret.tail=null;
	return ret;
}

struct Node* createNode(){
	int k;
	int v;
	
	printf("Please input key:"); scanf("%d",&k);
	printf("Please input a value:"); scanf("%d",&v);
	
	struct Node* node=(struct Node*)malloc(sizeof(struct Node));
	node->data=(struct Data*)malloc(sizeof(struct Data));
	node->data->key=k;
	node->data->value=v;
	node->next=null;
	
	return node;
}

void freeNode(struct Node* node){
	free(node->data);
	free(node);
	return;
}


void insertFirst(struct LinkList* list){
	struct Node* node=createNode();
	
	node->next=list->head;
	list->head=node;
	if (list->tail==null) list->tail=node;
	
	printf("Complete!\n");
	return;
}

void insertLast(struct LinkList* list){
	struct Node* node=createNode();
	
	if (list->tail==null){
		list->tail=node;
		list->head=node;
	}
	else{
		list->tail->next=node;
		list->tail=node;
	}
	
	
	printf("Complete!\n");
	return;
}

void insertAfter(struct LinkList* list){
	int k;
	
	printf("Please input a key in the list:"); scanf("%d",&k);
	struct Node* p=list->head;
	while (p!=null && p->data->key!=k) p=p->next;
	if (p==null) {
		printf("Can not find the key!\n");
		return;
	}
	
	struct Node* node=createNode();
	node->next=p->next;
	p->next=node;
	
	printf("Complete!\n");
	return;
}

void search(struct LinkList* list){
	int k;
	bool flag=false;
	
	printf("Please input a key to search:"); scanf("%d",&k);
	struct Node* p=list->head;
	while(p!=null) {
		if (p->data->key==k){
			printf("<key:%d---value:%d>\n",k,p->data->value);
			flag=true;
		}
		p=p->next;
	}
	if (!flag) printf("The key not in the list!\n");
	return;
}

void print(struct LinkList* list){
	struct Node* p=list->head;
	
	if (isListEmpty(list)){
		printf("List empty!\n");
		return;
	}
	
	while (p!=null){
		printf("<key:%d---value:%d>\n",p->data->key,p->data->value);
		p=p->next;
	}
	return;
}

void update(struct LinkList* list){
	int k;
	int v;
	
	printf("Please input a key to search:"); scanf("%d",&k);
	struct Node* p=list->head;
	while (p!=null && p->data->key!=k) p=p->next;
	if (p==null) {
		printf("The key not in the list!\n");
		return;
	}
	
	printf("Please input a new value:"); scanf("%d",&v);
	p->data->value=v;
	
	printf("Complete!\n");
	return;
}

void removeNode(struct LinkList* list){
	int k;
	
	printf("Please input a key to search:"); scanf("%d",&k);
	struct Node* p=list->head;
	struct Node* pre=list->head;
	while (p!=null && p->data->key!=k) {
		pre=p;
		p=p->next;
	}
	if (p==null) {
		printf("The key not in the list!\n");
		return;
	}
	
	if (p==list->head){
		list->head=p->next;
		freeNode(p);
	}
	else if (p==list->tail){
		pre->next=null;
		list->tail=pre;
		freeNode(p);
	}
	else{
		pre->next=p->next;
		freeNode(p);
	}
	printf("Complete!");
	return;
}

int main(){
	char c;
	struct LinkList list=createLinkList();
	while (1){
		c=UserInterface();
		switch(c){
			case '1': insertFirst(&list); break;
			case '2': insertLast(&list); break;
			case '3': insertAfter(&list); break;
			case '4': search(&list); break;
			case '5': print(&list); break;
			case '6': update(&list); break;
			case '7': removeNode(&list); break;
			case '0': return 0;
			default:
				continue;
		}
	}
	return 0;
} 
