#include<iostream>
#include<vector>
#include<string>

using namespace std;

#define MaxVertexNum 100

int main() {
	char pause = '\n';
	cout<<"----------------计算最短路径-----------------\n";

	cout<<"--------------------------------按回车键继续-----------------------------------\n";

	cin.get(pause);
	cout<< "--------------------------------1 - 直接用'南京', '苏州', '无锡', '上海', '连云港', '重庆'(默认为从南京到重庆为一条通路，每条路径长度都为1) ，直接初始化-----------------\n";

	cout<< "--------------------------------2 - 自定义初始化-----------------\n";
	vector<string> citymin;
	int choice;
	vector<string> citytest = { "南京", "苏州", "无锡", "上海", "连云港", "重庆" };
	cout << endl;
	cin >> choice;

	typedef string VertexType;//顶点的数据类型
	typedef int EdgeType;//带权图中边上权值的数据类型
	typedef struct {
		vector<VertexType> Vex;//顶点表
		EdgeType Edge[MaxVertexNum][MaxVertexNum];//邻接矩阵，边表
		int vexnum, arcnum;//图的当前顶点数和弧数

	} MGragh;
	MGragh A;
	if (choice == 1) {
		for (int i = 0; i < MaxVertexNum; i++)
			for (int j = 0; j < MaxVertexNum; j++)
				A.Edge[i][j] = 65536;
		for (int i = 0; i < citytest.size(); i++)
			for (int j = 0; j <citytest.size(); j++) {
				if (i == j - 1) {
					A.Edge[i][j] = 1;
				}
			}
		for (int i = 0; i < citytest.size(); i++) {
			A.Vex.push_back(citytest[i]);
		}
		int p = citytest.size();
		A.vexnum = 6;
		A.arcnum = 5;

	}
	if (choice == 2) {
		for (int i = 0; i < MaxVertexNum; i++)
			for (int j = 0; j < MaxVertexNum; j++)
				A.Edge[i][j] = 65536;
		int  e;
		string city, city1, city2;
		int num = 0;
		cout << "请输入结点城市的名称（每一次回车代表输入完了一次城市，第一次输入的城市默认为源点，所有城市输入结束后请输入\"停止\"结束输入）：";
		cin >> city;
		A.Vex.push_back(city);
		while (city != "停止") {
			cin >> city;
			num++;
			A.Vex.push_back(city);
			A.vexnum = num + 1;
		}
		cout << "请输入一个int来决定城市图之间的边数：";
		cin >> e;
		A.arcnum = e;

		cout << "请输入两个城市名称和一个int类型数据来决定这些边在哪两个城市之间（边的方向为单向，从前一个数指向后一个），且给这个边加上权值：";
		for (int i = 0; i < A.arcnum; i++) {
			int o, p, q;
			cin >> city1;
			cin >> city2;
			cin >> q;
			for (int i = 0; i < A.vexnum; i++) {
				if (A.Vex[i] == city1)
					o = i;
			}
			for (int i = 0; i < A.vexnum; i++) {
				if (A.Vex[i] == city2)
					p = i;
			}
			A.Edge[o][p] = q;
		}
	}
	string choice2;
	cout << "是否想改变城市名称以及相应的路径，输入'YES'进入改变模式，输入'NO'则直接进入测试";
	cout << endl;
	cin >> choice2;
	cout << endl;
	if (choice2 == "YES") {
		int choice3;
		cout << "首先减少城市，输入0进入，再次输入0跳过减少，到增加阶段(减少城市后，城市道路也随之删去，可进入修改路径阶段修改),输入2进入修改路径阶段";
		cout << endl;
		cin >> choice3;
		if (choice3 == 0) {
			string citymin1;
			int temp;
			cout << "请先输入想要删除多少城市（输入0跳过）";
			cin >> temp;
			cout << endl;
			cout << "请输入想要删除的城市（每输入一个按一个回车表示输入完毕）：";
			for (int i = 0; i < temp; i++) {
				cin >> citymin1;
				citymin.push_back(citymin1);
				for (int i = 0; i < A.vexnum; i++) {
					if (A.Vex[i] == citymin1) {
						for (int k = 0; k < MaxVertexNum; k++)
							for (int j = 0; j < MaxVertexNum; j++)
								if (j == i || k == i) {
									A.Edge[j][k] = 65536;
								}
					}
				}
			}

			cout << "城市名减少结束，现进入城市增加阶段";
			cout << endl;
			int temp2;
			cout << "请先输入想增加多少城市（输入0跳过）";
			cin >> temp2;
			cout << endl;
			cout << "请输入想要增加的城市（每输入一个按一个回车表示输入完毕）：";
			cout << endl;
			for (int i = 0; i < temp2; i++) {
				string citymax;
				cin >> citymax;
				A.Vex.push_back(citymax);
				A.vexnum += 1;
			}
			cout << "城市名修改结束，现在进入路径的增加以及修改阶段,请先输入改变路径的条数（输入0跳过）：";
			cout << endl;
			int temp3;
			cin >> temp3;
			cout << endl;
			cout << "请输入两个城市名称和一个int类型数据来修改路径";
			cout << endl;
			for (int i = 0; i < temp3; i++) {
				string city7;
				string city8;
				int u, l, m;
				cin >> city7;
				cin >> city8;
				cin >> m;
				cout << endl;
				for (int i = 0; i < A.vexnum; i++) {
					if (A.Vex[i] == city7)
						u = i;
				}
				for (int i = 0; i < A.vexnum; i++) {
					if (A.Vex[i] == city8)
						l = i;
				}
				A.Edge[u][l] = m;


			}
		}
		if (choice3 == 2) {
			int temp3;

			cout << "请先输入想要改变多少条路径";
			cin >> temp3;
			cout << endl;
			cout << "请输入两个城市名称和一个int类型数据来修改路径";
			cout << endl;
			for (int i = 0; i < temp3; i++) {
				string city7;
				string city8;
				int u, l, m;
				cin >> city7;
				cin >> city8;
				cin >> m;
				for (int i = 0; i < A.vexnum; i++) {
					if (A.Vex[i] == city7)
						u = i;
				}
				for (int i = 0; i < A.vexnum; i++) {
					if (A.Vex[i] == city8)
						l = i;
				}
				A.Edge[u][l] = m;


			}


		}

	}

	int s[100];
	for (int i = 0; i < 100; i++) {
		s[i] = 65536;
	}

	int dist[100], min = 65536;
	for (int i = 0; i < 100; i++) {
		dist[i] = A.Edge[0][i];
	}

	int count = 0;
	vector<int> T;
	T.push_back(0);
	while (count < A.vexnum - 1) {
		int temper[100];
		for (int i = 0; i < 100; i++) {
			temper[i] = dist[i];
		}
		for (int i = 0; i < 100; i++) {
			if (s[i] == i) {
				temper[i] = 65536;
			}
		}
		int j;
		for (int i = 0; i < A.vexnum; i++) {
			if (temper[i + 1] < temper[i]) {
				min = temper[i + 1];
			}
		}

		for (int i = 0; i < A.vexnum; i++) {
			if (dist[i] == min) {
				s[i] = i;
				j = i;
			}

		}

		for (int k = 0; k < A.vexnum; k++) {
			if (dist[j] + A.Edge[j][k] < dist[k]) {
				dist[k] = dist[j] + A.Edge[j][k];
				T.push_back(j);
			}
		}
		count += 1;
	}

	string citytest1;
	cout << "源点默认为第一个输入的城市，现在再输入一个城市，计算出源点到其的最短路径。";
	cin >> citytest1;
	cout << endl;
	int tes;
	for (int i = 0; i <A.vexnum; i++) {
		if (A.Vex[i] == citytest1) {
			cout << "源点到该城市的最短路径长度为：";
			cout << dist[i];
			cout << endl;
			cout << "若输出超过65536，则说明没有到此城市的路.";
			cout << endl;
			tes = i;
		}
	}
	cout << "所经过路径为： ";
	for (int i = 0; i < tes; i++) {
		cout << A.Vex[T[i]];
		cout << "→";
	}
	cout << citytest1;
	cout << endl;


}



