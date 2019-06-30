/*
给定由若干个单词组乘的字符串... 
*/
#include<iostream>
#include<vector>
using namespace std;

vector<string> split(string str){
	vector<string> res;
	if (str=="") return res;
	str=str+" ";
	int pos=str.find(' ');
	int len=str.size();
	
	while (pos!=string::npos){
		string t=str.substr(0,pos);
		res.push_back(t);
		str=str.substr(pos+1,len);
		pos=str.find(' ');
	}
}

int main(){
	string str;
	string max;
	string min;
	while (getline(cin,str)){
		vector<string> a=split(str);
		max=a[1];
		min=a[1];
		for (int i=0;i<a.size();i++){
			if (a[i].length()>max.length()) max=a[i];
			if (a[i].length()<min.length()) min=a[i];
		}
		cout<<max<<endl<<min<<endl;
	}
	return 0;
} 
