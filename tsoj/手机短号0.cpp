#include<iostream>
#include<string>
using namespace std;
int main(){
	string str;
	int t,len;
	while(getline(cin,str)){
		cout<<'6'+str.substr(str.length()-5)<<endl;
	}
	return 0;
}
