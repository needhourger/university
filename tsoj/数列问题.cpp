#include<iostream>
#include<list>
using namespace std;


void sort(long* a,int n){
	long t;
	for (int i=0;i<n;i++)
		for (int j=0;j<n-1;j++)
		if (a[j]<a[j+1]){
			t=a[j];
			a[j]=a[j+1];
			a[j+1]=t;
		}
		

//	for (int i=0;i<n;i++)
//		cout<<a[i]<<" ";
//	cout<<endl;
}

int main(){
	long n;
	long a[20];
	while (cin>>n){
		if (n<1) {
			cout<<0<<endl;
			continue;
		}
		for (int i=0;i<n;i++) cin>>a[i];
		while (n>1){
			sort(a,n);
			a[n-2]=a[n-1]*a[n-2]+1;
			n--;
		}
		cout<<a[0]<<endl;
	}
	
	return 0;
} 
