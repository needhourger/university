#include<iostream>
using namespace std;

 struct node{
	int data;
	node* next;
}; 

class list{
	private:
		node* head;
	public:
		list(){ head=NULL;}
		~list();
		
		void print();
		void insert(int x);
		void insert(int x,int dest);
		int  get(int dest);
		
};

list::~list(){
	node* p=head;
	while (head){
		p=head;
		head=head->next;
		delete(p);
	}
}


void list::print(){
	node* p=head;
	while (p!=NULL){
		cout<<p->data<<" ";
		p=p->next;
	}
	cout<<endl;
}

void list::insert(int x){
	if (head==NULL){
		head=new node;
		head->data=x;
		head->next=NULL;
	}
	else{
		node* p=head;
		while (p->next!=NULL) p=p->next;
		
		node* pre=p;
		p=new node;
		p->data=x;
		p->next=NULL;
		pre->next=p;
		//cout<<"ok"<<endl;
	}
}

void list::insert(int x,int dest){
	if (head==NULL){
		head=new node;
		head->data=x;
		head->next=NULL;
		return;
	}
	int i=0;
	node* p=head;
	node* pre=head;
	if (p->next==NULL){
		pre=p;
		p=new node;
		p->data=x;
		p->next=NULL;
		pre->next=p;
		return;
	}
	while (i<=dest && p->next!=NULL){
		pre=p;
		p=p->next;
	}
	node* t=new node;
	t->data=x;
	pre->next=t;
	t->next=p;
	
}

int list::get(int dest){
	node* p=head;
	int i=0;
	while (i<dest && p->next!=NULL){
		p=p->next;
		i++;
	}
	return p->data;
}

int main(){
	list x;
	for (int i=0;i<5;i++){
		x.insert(i);
		cout<<"did"<<endl; 
	}
	x.print();
	return 0;
} 
