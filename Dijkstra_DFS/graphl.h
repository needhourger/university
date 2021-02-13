#ifndef GRAPHL_H
#define GRAPHL_H
#define MAXNODES 150
#include <fstream>
#include "nodedata.h"

using namespace std;

struct EdgeNode
{
    int adjGraphNode; // subscript of the adjacent graph node
    EdgeNode *nextEdge;
};

struct GraphNode
{                       // structs used for simplicity, use classes if desired
    EdgeNode *edgeHead; // head of the list of edges
    NodeData *data;     // data information about each node
    bool visited;
};



class GraphL
{
public:
    GraphL();
    ~GraphL();
    void buildGraph(ifstream &f);
    void displayGraph();
    void depthFirstSearch();
private:
    void DFS(int x);

private:
    GraphNode adjList[MAXNODES];
};

#endif