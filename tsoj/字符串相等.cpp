#include<iostream>
using namespace std;


string func(string s){
	string ret;
	ret.empty();
	for (int i=0;i<s.length();i++){
		if (s[i]==' ') continue;
		if (s[i]>='A' && s[i]<='Z') ret.append(1,s[i]+32);
		else ret.append(1,s[i]);
	}
//	cout<<ret<<endl;
	return ret;
}

int main() {
	long n;
	string s1,s2;
	cin>>n;
	fflush(stdin);
	while (n){
		getline(cin,s1);
		getline(cin,s2);
//		cout<<s1<<endl;
//		cout<<s2<<endl;
		s1=func(s1);
		s2=func(s2);
		if (s1==s2) cout<<"YES"<<endl;
		else cout<<"NO"<<endl;
		n--;
	}
	return 0;
}
