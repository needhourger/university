#include<iostream>
#include<stack>

using namespace std;
int main(){
	int T,len,flag;
	string input;
	stack<char> s;
	cin>>T;
	getchar();
	while(T>0){
		getline(cin,input);
		len=input.length();
		flag=1;
		while (!s.empty()) s.pop();
		for (int i=0;i<len && flag;i++){
			switch (input[i]){
				case '(': s.push(input[i]); break;
				case '{': s.push(input[i]); break;
				case ')': 
					if (s.empty() || s.top()!='(') flag=0;
					else s.pop(); 
					break;
				case '}':
					if (s.empty() || s.top()!='{') flag=0;
					else s.pop();
					break;
				default: continue;
			}
		}
		if (!s.empty()) flag=0;
		if (flag) cout<<"Yes"<<endl;
		else cout<<"No"<<endl;
		T--;
	}
	return 0;
}
