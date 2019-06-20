#include<iostream>
#include<cmath>
using namespace std;


bool filter(unsigned long n){
	return true;
}

bool check(unsigned long x,int n){
	unsigned long sum=0;
	unsigned long t=x;
	while (t>0){
		sum+=pow(t%10,n);
		t/=10;
	}
	if (x==sum) return true;
	return false;
}

int main() {
	int n;
	unsigned long min,max;
	
	while (cin>>n) {
		min=pow(10,n-1);
		max=pow(10,n);
		for (unsigned long i=min;i<max;i++){
			if (filter(i) && check(i,n)) cout<<i<<endl;
		}
	}
	return 0;
}
