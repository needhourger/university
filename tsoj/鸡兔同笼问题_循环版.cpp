#include<iostream>
using namespace std;
int main(){
	int head,foot,flag;
	while (cin>>head>>foot){
		flag=1; 
		for(int i=0;i<=head;i++){
			if (2*i+(head-i)*4==foot){
				cout<<i<<" "<<head-i<<endl;
				flag=0;
				break;
			} 
		}
		if (flag) cout<<-1<<" "<<-1<<endl;
	}
	return 0;
}
