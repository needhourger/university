#include<iostream>
#include<cstring>
using namespace std;
int main(){
	string num;
	int len,i; 
	while(getline(cin,num)){
		len=num.length();
		cout<<6;
		for (i=len-1;len-i<5;i--)
		cout<<num[i];
		cout<<num[i]<<endl;
	}
	return 0;
}
