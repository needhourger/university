#include<stdio.h>
#include<stdlib.h>

void dec2bin(int n){
	short a[32]={0}; 
	unsigned short i=0;
	if (n<0) {
		printf("-");
		n=-n;
	}
	while (n>0) {
		a[i++]=n%2;
		n/=2;
	}
	while(i>0) printf("%d",a[--i]);
	printf("\n");
}

void dec2oct(int n){
	short a[12]={0}; 
	unsigned short i=0;
	if (n<0) {
		printf("-");
		n=-n;
	}
	while (n>0) {
		a[i++]=n%8;
		n/=8;
	}
	while(i>0) printf("%d",a[--i]);
	printf("\n");
}

void dec2hex(int n){
	char a[8]={0};
	unsigned short i=0;
	if (n<0){
		printf("-");
		n=-n;
	}
	while (n>0){
		if (n%16<10) a[i++]='0'+n%16;
		else a[i++]='A'+n%16-10;
		n/=16;
	}
	while (i>0) printf("%c",a[--i]);
	printf("\n");
}


int main(){
	int n;
	while (1){
		printf("\n=========================\nPlease input a Decimal number:\nn = ");
		scanf("%d",&n);
		printf("[%d] convert to binary:",n); dec2bin(n);
		printf("[%d] convert to octonary:",n); dec2oct(n);
		printf("[%d] convert to Hexadecimal:",n); dec2hex(n);
	}

	return 0;
} 
