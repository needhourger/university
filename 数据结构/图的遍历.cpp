#include<stdio.h>
#define MAX_VERTEX_NUM 20

typedef struct ArcNode{
	int		adjvex;
	struct ArcNode *nextarc;
	InfoType	*info;
}ArcNode;

typedef struct VNode{
	VertexType	data;
	ArcNode	*firstarc;
}VNode,AdjList[MAX_VERTEX_NUM];

typedef struct{
	AdjList	vertices;
	int vexnum,arcnum;
	int kind;
}ALGraph;

int main(){
	
	return 0;
}
