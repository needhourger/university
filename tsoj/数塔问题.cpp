#include <iostream>
using namespace std;

unsigned short a[1000][1000];
unsigned int ans[1000][1000]; 

unsigned int min(unsigned int a,unsigned int b){
	return (a<b?a:b);
}
 
int main(){
	unsigned short n;

	
	while (cin>>n){
		
		for (int i=0;i<n;i++)
			for(int j=0;j<=i;j++)
			cin>>a[i][j];
			
		for (int i=n-1;i>=0;i--){
			for (int j=0;j<=i;j++){
				if (i!=n-1) ans[i][j]=a[i][j]+min(ans[i+1][j],ans[i+1][j+1]);
				else ans[i][j]=a[i][j];
			}
		}
		
		cout<<ans[0][0]<<endl;
	}
	
	return 0;
} 
