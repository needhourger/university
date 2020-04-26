#include<iostream>
#include<cstdio>
#include<cmath>
#include<algorithm>

using namespace std;

struct Node{
	int x;
	int y;
};

bool cmp(const Node a,const Node b){
	if (a.x>=b.x && a.y>b.y) return true;
	return false;
}

int main(){
	unsigned int t;
	unsigned int n;
	
	Node N[100000]
	int x,y;
	
	double res;
	double d;
	
	bool flag;
	
	cin>>t;
	while (t--){
		cin>>n;
		for (int i=0;i<n;i++) cin>>N[i].x>>N[i].y;
		sort(N,n,cmp)
		flag=true;
		for (int i=0;i<n;i++) {
			cin>>x>>y;
			for (int j=0;j<n;j++){
				d=pow((N[j].x-x),2)+pow((N[j].y-y),2);
				if (i==0 && j==0) {
					res=d;
					flag=true
				}
				if (flag  && d<res) res=d; 
				
			}
		}
		printf("%.3lf\n",sqrt(res));
	}
	return 0;
}
