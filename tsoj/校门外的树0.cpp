#include<iostream>
#include<cstring> 
using namespace std;
int a[100000]={1};

int main(){
	int l,m,begin,end;
	int ans;
	while (cin>>l>>m){
		for (int i=0;i<=l;i++) a[i]=1;
		for (int i=0;i<m;i++){
			cin>>begin>>end;
			for (int j=begin;j<=end;j++)
				a[j]=0;
		}
		ans=0;
		for (int i=0;i<=l;i++)
		if (a[i]) ans=ans+1;
		cout<<ans<<endl;
	}
	return 0;
}
