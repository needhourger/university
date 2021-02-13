#ifndef GRAPHM_H
#define GRAPHM_H
#define MAXNODES 150
#ifndef INT_MAX
#define INT_MAX 0x7fffffff
#endif
#include <iostream>
#include <vector>
#include "nodedata.h"

using namespace std;


struct TableType
{
    /* data */
    bool visited;
    int dist;
    int path;
};


class GraphM
{
private:
    /* data */
    NodeData data[MAXNODES];
    int C[MAXNODES][MAXNODES];
    int size;
    TableType T[MAXNODES][MAXNODES];

public:
    GraphM();
    ~GraphM();

    void buildGraph(ifstream &f);
    void insertEdge(int a,int b,int dist);
    void removeEdge(int a,int b);
    void findShortestPath();
    void displayAll();
    void display(int a,int b);
};
#endif
