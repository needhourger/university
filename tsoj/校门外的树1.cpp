#include<iostream>

using namespace std;

	bool tree[100001];

int main(){

	unsigned int m,l;
	unsigned int a,b;
	unsigned int ans;
	
	while(cin>>l>>m){
		for (int i=0;i<=l;i++) tree[i]=true;
		for (int i=0;i<m;i++){
			cin>>a>>b;
			for (int j=a;j<=b;j++) if (tree[j]) tree[j]=false;
		}
		ans=0;
		for (int i=0;i<=l;i++) if (tree[i]) ans++;
		cout<<ans<<endl;
	}
	
	return 0;
}
