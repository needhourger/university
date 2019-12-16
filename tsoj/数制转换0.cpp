#include<iostream>
#include<cstring>
using namespace std;
void done(int n,int r) {
	string ans,temp;
	ans.clear();
	while (n!=0) {
		switch (n%r) {
			case 10: ans.append("A"); break;
			case 11: ans.append("B"); break;
			case 12: ans.append("C"); break;
			case 13: ans.append("D"); break;
			case 14: ans.append("E"); break;
			case 15: ans.append("F"); break;
			default: ans.append(to_string(n%r)); 
		}
		n/=r;
	}
	int len=ans.length();
	for (int i=len-1;i>=0;i--)
	cout<<ans[i];
	cout<<endl;
}

using namespace std;
int main() {
	int p,r,temp;
	string n;
	while (cin>>p>>r>>n) {
		if (p==0 && r==0 && n=="0") break;
		temp=stoi(n,nullptr,p);
		if (r==10) cout<<temp<<endl;
		else done(temp,r);
	}
	return 0;
}
