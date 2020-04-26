#include<iostream>
using namespace std;

struct Node{
	unsigned long data;
	struct Node* left;
	struct NOde* right;
};


Node* create_tree(unsigned long x,unsigned long i){
	Node *root=new Node;
	root->data=i;
	root->left=create_tree(++i);
	root->righ=create_tree(++i);
	if (i==x) return root;
}

void delete_tree(Node* root){
	
}

int main(){
	unsigned int q;
	unsigned long x;
	unsigned short k;
	
	Node * tree=NULL;
	
	cin>>q;
	while (q--){
		cin>>x>>k;
		
	}
	
	return 0; 
} 
