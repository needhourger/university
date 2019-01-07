#include<iostream>
using namespace std;
int main() {
	int T,n,k;
	int a[10][10]= {0};
	int b[10]= {0};
	long ans;
	cin>>T;
	while (T>0) {
		cin>>n>>k;
		for (int i=0; i<n; i++){
			for (int j=0; j<n; j++) 
				cin>>a[i][j];
				b[i]=a[i][i];
			}
		ans=0;
		for (int i=0; i<k; i++)
			for (int j=0; j<n; j++){
				a[j][j]*=b[j];
				if (i==k-1) ans+=a[j][j];
			}
		cout<<ans%9973<<endl;
		T--;
	}
	return 0;
}
