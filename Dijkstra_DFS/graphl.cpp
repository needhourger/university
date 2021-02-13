#include "graphl.h"

GraphL::GraphL()
{
    for (int i = 0; i < MAXNODES; i++)
    {
        adjList[i].visited = false;
        adjList[i].data = NULL;
        adjList[i].edgeHead = NULL;
    }
}

GraphL::~GraphL()
{
    for (int i = 0; i < MAXNODES; i++)
    {
        if (adjList[i].data != NULL)
            delete adjList[i].data;
        EdgeNode *p = adjList[i].edgeHead;
        EdgeNode *pre = NULL;
        while (p != NULL)
        {
            pre = p;
            p = p->nextEdge;
            delete pre;
        }
    }
}

void GraphL::buildGraph(ifstream &f)
{
    int size;
    f >> size;
    f.ignore(2);
    for (int i = 1; i <= size; i++)
    {
        adjList[i].data = new NodeData();
        adjList[i].data->setData(f);
        adjList[i].edgeHead = NULL;
    }
    int a, b;
    while (!f.eof())
    {
        f >> a >> b;
        if (a == 0)
            return;
        EdgeNode *e = new EdgeNode();
        e->adjGraphNode = b;
        e->nextEdge = adjList[a].edgeHead;
        adjList[a].edgeHead = e;
    }
}

void GraphL::displayGraph()
{
    cout << "Graph:" << endl;
    for (int i = 1; i < MAXNODES; i++)
    {
        if (adjList[i].data == NULL)
            break;
        cout << "Node " << i << "\t" << *adjList[i].data << endl
             << endl;
        EdgeNode *p = adjList[i].edgeHead;
        while (p != NULL)
        {
            cout << "   edge " << i << " " << p->adjGraphNode << endl;
            p = p->nextEdge;
        }
    }
}

void GraphL::depthFirstSearch()
{
    cout <<endl<< "Depth-first ordering: ";
    for (int i = 1; i < MAXNODES; i++)
    {
        if (adjList[i].data == NULL)
            break;
        if (!adjList[i].visited)
            DFS(i);
    }
    cout << endl<<endl;
}

void GraphL::DFS(int x)
{
    adjList[x].visited = true;
    cout << x << " ";
    EdgeNode *p = adjList[x].edgeHead;
    while (p)
    {
        if (!adjList[p->adjGraphNode].visited)
            DFS(p->adjGraphNode);
        p = p->nextEdge;
    }
}