#include<iostream>
using namespace std;

int main(){
	int n,t;
	int ling,yi;
	
	while (cin>>n){
		yi=0;
		ling=0;
		while (n>0){
			t=n%2;
			n/=2;
			switch (t){
				case 0: ling+=1; break;
				case 1: yi+=1; break;
			}
		}
		cout<<yi<<endl;
		cout<<ling<<endl;
	} 
	
	return 0;
}
