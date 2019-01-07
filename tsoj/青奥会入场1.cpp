#include <iostream>
using namespace std;

class Country{
	public:
		Country(unsigned short N,string n,unsigned short p,string C); 
		unsigned short NO;
		string name;
		unsigned short people;
		string Continent;
		
		void print();
};

void Country::print(){
	printf("%d %s %d % s\n",NO,name.c_str(),people,Continent.c_str());
};

Country::Country(unsigned short N,string n,unsigned short p,string C){
	NO=N;
	name=n;
	people=p;
	Continent=C;
};

Country* a[300];

int main(){
	short n,x;

	unsigned short NO;
	string name;
	unsigned short people;
	string Continent;

	Country* t;
		
	while (cin>>n){
		for (int i=0;i<n;i++){
			cin>>NO>>name>>people>>Continent;
			a[i]=new Country(NO,name,people,Continent);
		}
		
		//for (int i=0;i<n;i++) a[i]->print();
		 
		for (int i=0;i<n-1;i++){
			for (int j=0;j<n-i-1;j++)
			if (a[j]->name>a[j+1]->name){
				t=a[j];
				a[j]=a[j+1];
				a[j+1]=t; 
			}
		}
		
		//for (int i=0;i<n;i++) a[i]->print();
		
		for (int i=0;i<n;i++)
		if (a[i]->name=="China") x=i;
		else {
			a[i]->print();
			delete a[i];
		}
		
		a[x]->print();
		delete a[x];
	}
	return 0;
}
