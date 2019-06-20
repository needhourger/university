#include<iostream>
using namespace std;


unsigned int gcd(unsigned int m,unsigned int n) {
	if (m < n) {
		int tmp = m;
		m = n;
		n = tmp;
	}
	if (n == 0)
		return m;
	else
		return gcd(n, m % n);
}

int main() {
	unsigned int a,b,c;
	unsigned int ans;
	while(cin>>a>>b>>c) {
		ans=gcd(a,b);
		ans=gcd(ans,c);
		cout<<ans<<endl;
	}
	return 0;
}
