#include<stdio.h>

int compare(int x,int y,bool type){
	if (type){
		if (x>y) return 1;
		return 0;
	}
	else{
		if (x<y) return 1;
		return 0;
	}
}

void sort(int* a,int i,int j,bool type){
	int key=a[j];
	int h=i;
	int t=j;
	while (i<j){
		while(i<j && compare(a[i],key,type)) i++;
		if (i<j) {
			a[j]=a[i];
			j--;
		}
		while (i<j && compare(a[j],key,!type)) j--;
		if (i<j){
			a[i]=a[j];
			i++;
		}
	}
	a[j]=key;
	if (h<i-1) sort(a,h,i-1,type);
	if (i+1<t) sort(a,i+1,t,type);
}

void Quicksort(int* a,int n,bool type){
	int i=0;
	int j=n-1;
	sort(a,i,j,type);
}

int main(){
	int a[10]={12,321,3,123,45,56,76,2,788,123};
	Quicksort(a,10,false);
	for (int i=0;i<10;i++) printf("%d ",a[i]);
	printf("\n");
	return 0;
} 
