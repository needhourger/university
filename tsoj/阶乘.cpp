#include<iostream>
using namespace std;
long long ans;

long long functions(int n){
	if (n==1) return 1;
	else ans=ans*n*functions(n-1);
	return ans;
};


int main(){
	int n;
	
	while (cin>>n){
		ans=1;
		cout<<functions(n)<<endl;
	}
	return 0;
}
