/*¾ø¶ÔËØÊı*/
#include<iostream>
#include<cstring>
#include<cmath>
using namespace std;

bool check(int x){
	for (int i=2;i<sqrt(x);i++)
	if (x % i==0) return false;
	return true;
}

int main(){
	int n;
	int a,b;
	char x[10]={0};
	
	cin>>n;
	while (n--){
		cin>>x;
		a=atoi(x);
		b=atoi(strrev(x));
		if (check(a) && check(b)) cout<<1<<endl;
		else cout<<0<<endl; 
	}
	return 0;
} 
