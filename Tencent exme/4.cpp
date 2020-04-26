#include<iostream>
#include<stack>

using namespace std;

int main(){
	unsigned int n;
	
	stack<int> s1;
	stack<int> s2;
	
	string s;
	int num;
	
	cin>>n;
	while (n--){
		cin>>s;
		if (s=="add"){
			cin>>num;
			s1.push(num);
		}
		else if (s=="poll"){
			while (!s1.empty()) {
				s2.push(s1.top());
				s1.pop(); 
			}
			s2.pop();
			while (!s2.empty()) {
				s1.push(s2.top());
				s2.pop();
			}
		}
		else if (s=="peek"){
			while (!s1.empty()) {
				s2.push(s1.top());
				s1.pop(); 
			}
			cout<<s2.top()<<endl;
			while (!s2.empty()) {
				s1.push(s2.top());
				s2.pop();
			}
		}
	}
	
	return 0;
}
