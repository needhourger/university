#include<iostream>

using namespace std;

bool light[50000];

int main(){
	unsigned int n,k;
	unsigned int p;
	
	while (cin>>n>>k){
		
		p=1;
		for(int i=1;i<=k;i++){
				while (p*i<=n){
				light[p*i]=!light[p*i];
				p++;
			}
			p=1;
		}
		
		for (int i=1;i<=n;i++) if (light[i]) cout<<i<<endl;
			
	}

	return 0;
}
