#include<iostream>
#include<queue>
using namespace std;

int main(){
	int n;
	int t;
	string cmd;
	int num;
	
	queue<int> q;
	
	cin>>n;
	while (n--){
		cin>>t;
		for (int i=0;i<t;i++){
			cin>>cmd;
			if (cmd=="PUSH") {
				cin>>num;
				q.push(num);
			}
			else if (cmd=="TOP"){
				if (q.empty()) cout<<-1<<endl;
				else cout<<q.front()<<endl;
			}
			else if (cmd=="POP"){
				if (q.empty()) cout<<-1<<endl;
				else q.pop();
			}
			else if (cmd=="SIZE"){
				cout<<q.size()<<endl;
			}
			else if (cmd=="CLEAR"){
				while (!q.empty()) q.pop();
			}
		}
		while (!q.empty()) q.pop();
	}
	return 0;
} 
