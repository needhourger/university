#include<iostream>
using namespace std;
int main(){
	string words;
	int n;
	cin>>n;
	getchar(); 
	while(n>0){
		getline(cin,words);
		for (int i=words.length()-1;i>=0;i--) cout<<words[i];
		cout<<endl;
		n--;
	}
	
	return 0;
} 
