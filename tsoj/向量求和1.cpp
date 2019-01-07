#include <iostream>
using namespace std;

 
int main(){
	unsigned short n;
	
	int a[2000];
	int x;
	
	while (cin>>n){
		for(int i=0;i<n;i++) cin>>a[i];
		
		for (int i=0;i<n;i++){
			cin>>x;
			cout<<x+a[i]<<" "; 
		}
		
	}
	return 0;
} 
