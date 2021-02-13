#include "graphm.h"

GraphM::GraphM()
{
    size = 0;
    for (int i = 0; i < MAXNODES; i++)
    {
        for (int j = 0; j < MAXNODES; j++)
        {
            C[i][j] = INT_MAX;
            T[i][j].dist = INT_MAX;
            T[i][j].path = 0;
            T[i][j].visited = false;
        }
    }
}

GraphM::~GraphM()
{
}

void GraphM::buildGraph(ifstream &f)
{
    f >> size;
    f.ignore(2);
    for (int i = 1; i <= size; i++)
    {
        data[i].setData(f);
    }
    while (!f.eof())
    {
        int i, j, dist;
        f >> i >> j >> dist;
        if (i == 0)
        {
            return;
        }
        C[i][j] = dist;
    }
}

void GraphM::insertEdge(int a, int b,int dist)
{
    C[a][b]=dist;
    T[a][b].dist=dist;
    T[a][b].visited=true;
    T[a][b].path=a;
}

void GraphM::removeEdge(int a, int b)
{
    C[a][b]=INT_MAX;
    T[a][b].dist=INT_MAX;
    T[a][b].visited=false;
    T[a][b].path=0;
}

void GraphM::findShortestPath()
{
    for (int source = 1; source <= size; source++)
    {

        for (int i = 1; i <= size; i++)
        {
            T[source][i].dist = C[source][i];
            T[source][i].visited = false;
            T[source][i].path = source;
        }

        for (int count = 1; count < size; count++)
        {
            int v = 0;
            int min = INT_MAX;
            for (int i = 1; i <= size; i++)
            {
                if (!T[source][i].visited && T[source][i].dist < min)
                {
                    min = T[source][i].dist;
                    v = i;
                }
            }

            T[source][v].visited = true;
            for (int w = 1; w <= size; w++)
            {
                if (!T[source][w].visited && C[v][w] != INT_MAX && T[source][w].dist > (T[source][v].dist + C[v][w]))
                {
                    T[source][w].dist = T[source][v].dist + C[v][w];
                    T[source][w].path = v;
                }
            }
        }
    }
}

void GraphM::displayAll()
{
    cout << "Description\t\tFrom node\tTo node\tDijkstra's\tPath" << endl;
    for (int i = 1; i <= size; i++)
    {
        cout << data[i] << endl;
        for (int j = 1; j <= size; j++)
        {
            if (i == j)
                continue;
            cout << "\t\t\t" << i << "\t\t" << j << "\t";
            if (T[i][j].dist == INT_MAX)
            {
                cout << "----\t\t";
            }
            else
            {
                cout << T[i][j].dist << "\t\t";
                vector<int> path;
                path.push_back(j);
                int t = j;
                while (t != i)
                {
                    t = T[i][t].path;
                    path.push_back(t);
                }
                for (t = path.size() - 1; t >= 0; t--)
                    cout << path[t] << " ";
            }
            cout << endl;
        }
    }
}

void GraphM::display(int a, int b)
{
    cout << "  " << a << "\t\t" << b << "\t\t";
    if (T[a][b].dist == INT_MAX)
    {
        cout << "----" << endl<<endl;
    }
    else
    {
        cout << T[a][b].dist << "\t\t";

        vector<int> path;
        path.push_back(b);
        int t = b;
        while (t != a)
        {
            t = T[a][t].path;
            path.push_back(t);
        }
        for (t = path.size() - 1; t >= 0; t--)
            cout << path[t] << " ";
        cout << endl;
        while (!path.empty())
        {
            t=path.back();
            path.pop_back();
            cout << data[t] << endl
                 << endl;
        }
        cout<<endl;
    }
}