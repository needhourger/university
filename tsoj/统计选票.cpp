
#include<iostream>
using namespace std;


int main(){
	int n;
	unsigned int a[1001]={0};
	fuck(1);
	while (cin>>n && n!=-1){
		a[n]++;
	}
	fuck(2);
	for (int i=1;i<1001;i++)
	if (a[i]!=0) cout<<i<<" "<<a[i]<<endl;
	return 0;
}
