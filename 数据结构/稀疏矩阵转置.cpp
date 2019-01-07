#include<stdio.h>

#define MAXSIZE 12500

struct Triple{
	int i,j;
	int x;
};

struct TSMatrix{
	Triple data[MAXSIZE+1];
	int mu,nu,tu;
};

void CreateSMatrix(TSMatrix &M){
	printf("请输入矩阵的行数:");		scanf("%d",&M.mu);
 	printf("请输入矩阵的列数:");		scanf("%d",&M.nu);
	printf("请输入矩阵的非零元个数:");	scanf("%d",&M.tu);
	printf("请输入非零元:[x] [y] [data]\n"); 
	for (int i=1;i<=M.tu;i++){
		printf("第%d个:\t",i);	
		scanf("%d %d %d",&M.data[i].i,&M.data[i].j,&M.data[i].x); 
	}
	return;
}

void PrintSMatrix(TSMatrix M){
	printf("=====================\n");
	printf("i\tj\tv\t\n");
	printf("+++++++++++++++++++++\n");
	for (int i=1;i<=M.tu;i++){
		printf("%d\t%d\t%d\n",M.data[i].i,M.data[i].j,M.data[i].x);
	}
	printf("---------------------\n\n");
	return;
}


int TransposeSMatrix(TSMatrix M,TSMatrix &T){
	T.mu=M.mu;
	T.nu=M.nu;
	T.tu=M.tu;
	
	if (T.tu){
		int q=1;
		for (int col=1;col <=M.nu;++col)
			for (int p=1;p<=M.tu;++p){
				if (M.data[p].j==col){
					T.data[p].i=M.data[p].j;
					T.data[q].j=M.data[p].i;
					T.data[q].x=M.data[p].x;
					++q;
				}
			}
	}
	return 0;
}

int TransposeSMatrixX(TSMatrix M,TSMatrix &T){
	T.mu=M.mu;
	T.nu=M.nu;
	T.tu=M.tu;
	int num[MAXSIZE];
	int cpot[MAXSIZE];
	int col,q;
	if (T.tu){
		for (col=1;col<=M.nu;++col) num[col]=0;
		for (int t=1;t<=M.tu;++t) ++num[M.data[t].j];
		cpot[1]=1;
		
		for (col=2;col<=M.nu;++col) cpot[col]=cpot[col-1]+num[col-1];
		for (int p=1;p<=M.tu;++p){
			col=M.data[p].j;
			q=cpot[col];
			T.data[q].i=M.data[p].j;
			T.data[q].j=M.data[p].i;
			T.data[q].x=M.data[p].x;
			++cpot[col];
		} 
	}
	return 0;
}

int main(){
	TSMatrix M={0};
	CreateSMatrix(M);
	printf("转置前:\n");
	PrintSMatrix(M);
	
	TSMatrix T;
	TransposeSMatrixX(M,T);
	printf("转置后:\n");
	PrintSMatrix(T);
	
} 
