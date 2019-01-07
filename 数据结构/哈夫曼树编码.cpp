#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct {
	 int weight;
	 int parent,lchild,rchild;
}HTNode,*HuffmanTree;

typedef char** HuffmanCode;

void Select(HuffmanTree &HT, int n, int &s1, int &s2){
	s1=0;
	s2=0;
	int t;
	for (int i=1;i<=n;i++)
	if (HT[i].parent==0 && HT[s1].weight>HT[i].weight) s1=i;
	for (int i=1;i<=n;i++)
	if (i!=s1 && HT[i].parent==0 && HT[s2].weight>HT[i].weight) s2=i;
	//if (s1>s2){
	//	t=s1;
	//	s1=s2;
	//	s2=t;	
	//}
	//printf("%d %d\n",s1,s2); 
}

void HuffmanCoding(HuffmanTree &HT,HuffmanCode &HC,int *w,int n){
	//printf ("%d\n",*w);
	int m,i,start,c,f,s1,s2;
	HuffmanTree p;
	char* cd;
	
	if(n<=1) return;
	m=2*n-1;
	HT=(HuffmanTree)malloc((m+1)*sizeof(HTNode));
	for (p=HT,i=0;i<=n;++i,++p,++w) {
		*p={*w,0,0,0};
		//printf("%d %d %d %d\n",p->weight,p->parent,p->rchild,p->lchild);
		//p->weight=*w;
		//p->parent=0;
		//p->rchild=0;
		//p->lchild=0;
		//printf("%d %d\n",p->weight,HT[0].weight);
		
	}
	for (;i<=m;++i,++p) *p={0,0,0,0};
	
	for (i=n+1;i<=m;++i){	
		Select(HT,i-1,s1,s2);
		
		HT[s1].parent=i; 	HT[s2].parent=i;
		HT[i].lchild=s1;	HT[i].rchild=s2;
		HT[i].weight=HT[s1].weight+HT[s2].weight;
	} 
	
	HC=(HuffmanCode)malloc((n+1)*sizeof(char*));
	cd=(char*)malloc(n*sizeof(char));
	cd[n-1]='\0';
	for (i=1;i<=n;++i){
		start=n-1;
		for (c=i,f=HT[i].parent;f!=0;c=f,f=HT[f].parent)
			if (HT[f].lchild==c) cd[--start]='0';
			else cd[--start]='1';
			
		HC[i]=(char*)malloc((n-start)*sizeof(char));
		strcpy(HC[i],&cd[start]);
	}
	free(cd);
}



int main(){
	HuffmanTree HT;
	HuffmanCode HC;
    int w[1000];
	int n;
	printf("输入权值个数：");scanf("%d",&n);
	for (int i=1;i<=n;i++){
		printf("输入第%d个权值:",i);
		scanf("%d",&w[i]);
	}
	//printf("\n\n");
	HuffmanCoding(HT,HC,w,n);
	for (int i=1;i<=2*n-1;i++)
	printf("%d\t%d\t%d\t%d\n",HT[i].weight,HT[i].parent,HT[i].rchild,HT[i].lchild);
	printf("\n");
	for (int i=0;i<=n;i++)
	printf("%s\n",HC[i+1]); 
	return 0;
}
