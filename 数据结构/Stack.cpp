#include<stdio.h>
#include<stdlib.h>
#define STACK_INIT_SIZE 100
#define STACKINCREMENT 10

struct STACK{
	int* top;
	int* base;
	int size;
};

int init(STACK &s){
	s.base=(int*) malloc(STACK_INIT_SIZE* sizeof(int));
	if (!s.base) {
		printf("[INFO] Malloc error!\n");
		return -1;
	}
	s.top=s.base;
	s.size=STACK_INIT_SIZE;
}
int GetTop(STACK s){
	if (s.top==s.base) {
		printf("[INFO] Stack empty!\n");
		return -1;
	}
	return *(s.top-1);
}
int push(STACK &s,int x){
	if (s.top-s.base>=s.size){
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
int pop(STACK &s){
	if (s.top==s.base) {
		printf("[INFO] Stack empty!\n");
		return -1;
	}
	return *(--s.top);
}
bool IsEmpty(STACK s){
	if (s.base==s.top) return true;
	else return false;
}
int print(STACK s){
	for (int* i=s.base;i!=s.top;i++) printf("%d ",*i);
	printf("\n");
}
int main(){
	STACK s;
	int n;
	init(s);
	scanf("%d",&n);
	while (n){
		push(s,n%8);
		n/=8;
	}		
	while (!IsEmpty(s)){
		printf("%d",GetTop(s));
		pop(s);
	} 
	printf("\n");
	return 0;
}
