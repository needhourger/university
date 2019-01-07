/*
这一题令我印象深刻！！！！
在测试数据爆表的情况下，模拟只有timeout这个结果！！！
当然，这里没这回事。 
*/


#include<iostream>
using namespace std;
int main(){
	int n,k;
	bool light[5000];
	while (cin>>n>>k){
		for (int i=1;i<=n;i++) light[i]=true;
		for (int i=2;i<=k;i++)
			for (int j=1;j<=n;j++)
			if (j%i==0) light[j]=!light[j];
		for (int i=1;i<=n;i++)
		if (light[i]) cout<<i<<endl;
	}
	return 0;
}
