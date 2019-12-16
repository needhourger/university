#include <iostream>
#include <cstdlib>
using namespace std;

string change(int n,int r) {
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
	return ans;
}

int main(){
	short p,r;
	string n,ans;
	long t;
	
	while (cin>>p>>r>>n){
		if (p==0 && r==0 && n=="0") break;
		t=strtol(n.c_str(),NULL,p);
		
		ans=change(t,r);
		for (int i=ans.length()-1;i>=0;i--) cout<<ans[i];
		cout<<endl;
		
	}

	return 0;; 
} 
