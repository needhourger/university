#include<iostream>
#include<string.h>
using namespace std;

struct NATION{
	int NO;
	string name;
	int people;
	string continent;
};


int main(){
	int n,china;
	NATION list[300];
	NATION t;
	while(cin>>n){
		china=-1;
		for (int i=0;i<n;i++)
			cin>>list[i].NO>>list[i].name>>list[i].people>>list[i].continent;
		for (int i=0;i<n-1;i++)
		for (int j=0;j<n-i-1;j++){
			if (strcmp(list[j].name.c_str(),list[j+1].name.c_str())>0){
				t=list[j];
				list[j]=list[j+1];
				list[j+1]=t;
			}
		}
		for (int i=0;i<n;i++)
		{
			if (list[i].name!="China") cout<<list[i].NO<<" "<<list[i].name<<" "<<list[i].people<<" "<<list[i].continent<<endl;
			else china=i;
		}
		cout<<list[china].NO<<" "<<list[china].name<<" "<<list[china].people<<" "<<list[china].continent<<endl;
	} 
	return 0;
}
