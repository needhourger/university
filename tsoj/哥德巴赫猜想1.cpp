#include<iostream>

using namespace std;

bool check(unsigned int x){
	for (int i=2;i<x/2;i++)
	if (x%i==0) return false;
	return true;
}

int main(){

	unsigned int n;
	
	while (cin>>n){
		for (unsigned int i=2;i<=n/2;i++){
			if (check(i) && check(n-i)){
				cout<<i<<"+"<<n-i<<endl;
				break;
			}
		}
	} 
	
	return 0;
}
