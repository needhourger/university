#include<iostream>
using namespace std;
int money_all;

class STUDENT{
public:
	int get();
	int yuanshi();
	int wusi();
	int chenji();
	int xibu();
	int banji();
	int show();
	int money;
private:
	string name;
	int qimo_point;
	int class_point;
	bool class_job;
	bool west_stud;
	int paper;
};




int STUDENT::get(){
	char x;
	cin>>name>>qimo_point>>class_point;
	cin>>x; 
	if (x=='Y') class_job=true;
	else if (x=='N') class_job=false;
	else return 0;
	cin>>x; 
	if (x=='Y') west_stud=true;
	else if (x=='N') west_stud=false;
	else return 0;
	cin>>paper;
	return 1;
}

int STUDENT::yuanshi(){
	if (qimo_point>80 && paper>=1) return 8000;
	else return 0;
}

int STUDENT::wusi(){
	if (qimo_point>85 && class_point>80) return 4000;
	else return 0;
}

int STUDENT::chenji(){
	if (qimo_point>90) return 2000;
	else return 0;
}

int STUDENT::xibu(){
	if (qimo_point>85 && west_stud) return 1000;
	else return 0;
}

int STUDENT::banji(){
	if (class_point>80 && class_job) return 850;
	else return 0;
}

int STUDENT::show(){
	cout<<name<<endl;
	cout<<money<<endl;
	cout<<money_all<<endl;
}


int main(){
	STUDENT student[100];
	int n,p,i;
	while (cin>>n){
		money_all=0;
		for (i=0;i<n;i++){
			student[i].get();
			student[i].money=student[i].banji()+student[i].chenji()+student[i].wusi()+student[i].xibu()+student[i].yuanshi();
			money_all+=student[i].money;
		}
		p=0;
		i=1;
		while (i<n){
			if (student[p].money<student[i].money) p=i;
			i++;
		}
		student[p].show();
	}
	return 0;
}
