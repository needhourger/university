#include<iostream>
#include<vector>
#include<stdio.h>
#include<string>

using namespace std;

#define MaxVertexNum 100
typedef string VertexType;//顶点的数据类型
typedef int EdgeType;//带权图中边上权值的数据类型
typedef struct {
    vector<VertexType> Vex;//顶点表
    EdgeType Edge[MaxVertexNum][MaxVertexNum];//邻接矩阵，边表
    int vexnum, arcnum;//图的当前顶点数和弧数
    
 }MGragh;
MGragh A;



int num = 0;

int  e;


string city, city1, city2;

class CList
{
public:
    void handle_choice(int choice);
};
void CList::handle_choice(int choice)

{
    vector<VertexType>Vex(10);
    string a[7]={"南京","苏州","无锡","上海","连云港","重庆"};
    vector<VertexType>::size_type ix=0;
    
    for(int ix=0;ix<6;++ix)
        
    {
        A.Vex.push_back(a[ix]);
    }

    
    switch(choice)
    {
            
        case 1:
        {
            
            cout << "请输入结点城市的名称（每一次回车代表输入完了一次城市，默认南京为源点，所有城市输入结束后请输入\"停止\"结束输入）：";
            cin >> city;
            while (city != "停止")
            {
                A.Vex.push_back(city);
                A.vexnum = num + 1;
                cin >> city;
            }
            for (int i=0;i<A.Vex.size();i++)
                cout<<A.Vex.at(i)<<endl;
        }
        case 2:{
            string Del;
            cout<<"请输入你要删除的城市";
            cin>>Del;
            vector<VertexType>::iterator it;
            if(Del=="无")
                ;
            else for(it=A.Vex.begin();it!=A.Vex.end();)
                {
                            if(*it==Del)
                                it=A.Vex.erase(it);    //删除元素，返回值指向已删除元素的下一个位置
                            else
                            ++it;    //指向下一个位置
                    }
            for (int i=0;i<A.Vex.size();i++)
                cout<<A.Vex.at(i)<<endl;
        }
        case 3: {
            
            cout << "请输入一个int来决定城市图之间的边数：";
            cin >> e;
            A.arcnum = e;
            
            cout << "请输入两个城市名称和一个int类型数据来决定这些边在哪两个城市之间（边的方向为单向，从前一个数指向后一个），且给这个边加上权值：";
            for (int i = 0; i < MaxVertexNum; i++)
                for (int j = 0; j<MaxVertexNum; j++)
                    A.Edge[i][j] = 65536;
            for (int i = 0; i < A.arcnum; i++)
            {
                int o, p, q;
                cin >> city1;
                cin >> city2;
                cin >> q;
                for (int i = 0; i < A.vexnum; i++)
                if (A.Vex[i] == city1){
                    o = i;
                    break;
				}
                       
                
                for (int i = 0; i < A.vexnum; i++)
                if (A.Vex[i] == city2){
                	p = i;
                	break;
				}
                        
                A.Edge[o][p] = q;
            }
    
            int s[100];
            for (int i = 0; i < 100; i++) s[i] = 65536;
            
            string route[100];
            int dist[100], min=65536;
            for (int i = 0; i < 100; i++)
            {
                dist[i] = A.Edge[0][i];
               
            }
            int count = 0;
            int r=0;
            while (count < A.vexnum)
            {
                int j;
                for (int i = 0; i < A.vexnum; i++)
                {
                    if (dist[i + 1] < dist[i])
                    {
                        min = dist[i + 1];
                    }
                }
                //string
                for (int i = 0; i < A.vexnum; i++)
                {
                    if (dist[i] == min)
                    {
                        s[i] = i;
                        route[r]=A.Vex[i];
                        j = i;
                    }
                    
                }
                for (int k = 0; k < A.vexnum; k++)
                {
                    if (dist[j] + A.Edge[j][k] < dist[k])
                    {
                        dist[k] = dist[j] + A.Edge[j][k];
                    }
                }
                //string c[]
                count += 1;
            }
            string citytest;
            cout << "源点默认为第一个输入的城市，现在再输入一个城市，计算出源点到其的最短路径。";
            cin >> citytest;
            for (int i = 0; i < A.vexnum; i++)
            {
                if (A.Vex[i] == citytest)
                {
                    cout << "源点到该城市的最短路径长度为：";
                    cout << dist[i];
                    for(int t=0;t<r;t++)
                        cout<<route[r];
                }
                
            }
            
        }
        case 0:{
            cout<<"再见!!!\n";
            break;
        }
    }
}

int main(){


   
    char pause = '\n';
    
    CList ClassNumberList;
    A.vexnum=6;
    
    int choice;
    
    cout<<"----------------计算最短路径-----------------\n";
    
    cout<<"--------------------------------按回车键继续-----------------------------------\n";
    
    cin.get(pause);
    
    cout<<"作者：储兆龙\n";
    
    cout<<"学号：20178314037\n";
    
    cout<<"班级：信息安全1班\n";
    
    cout<<"--------------------------------按回车键继续-----------------------------------\n";
    
    cin.get(pause);
    
    
    do
        
    {
        
        
        cout<<"南京，苏州，无锡，上海，连云港，重庆\n";
        
        cout<< "--------------------------------1 - 添加结点!-----------------\n";
        
        cout<< "--------------------------------2 - 删除结点!-----------------\n";
        
        cout<< "--------------------------------3 - 输出最短路径-----------------\n";
        
        cout<< "--------------------------------0 - 退出程序!-----------------------------------\n";
        
        cout<<"请输入您的选择:\n";
        
        cin>>choice;
        
        ClassNumberList.handle_choice(choice);
        
    }
    
    while(choice!=0);
    
    return 0;
    
    
    
}

