#include<iostream>

using namespace std;

int main(){
	int m,n,a,b;
	
	while (cin>>m>>n){
		
		a=-1;
		b=-1;
		
		if ((4*m-n)%2==0 && 4*m-n>=0) {
			a=(4*m-n)/2;
			if (m-a<0) { a=-1; b=-1;}
			else b=m-a;
		}
		
		cout<<a<<" "<<b<<endl;
	}
	
	return 0;
} 
