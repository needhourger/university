#include<iostream>
#include<cstring>
using namespace std;
int main(){
	int t,flag,top,len,p;
	char stack[80]={0};
	string str;
	while (cin>>t){
		for (int i=0;i<t;i++){
			flag=1;
			top=-1;
			p=0;
			//cin>>str;
			
			fflush(stdin);
			getline(cin,str);
			 
			len=str.length();
			while (p<len && flag){
				if (str[p]=='(' ||str[p]=='{') stack[++top]=str[p];
				else {
					switch(str[p]){
						case ')':if (top==-1||stack[top--]!='(') flag=0; break;
						case '}':if (top==-1||stack[top--]!='{') flag=0; break;
					}
				}
				p++;
			}
			if (top!=-1) flag=0;
			if (flag) cout<<"Yes"<<endl;
			else cout<<"No"<<endl;
		}
	}
	
	return 0;
}
