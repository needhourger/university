#include <iostream>
#include<vector>
using namespace std;
 
int main()
{
	int n,x,y;
	while (cin >>n>>x>>y)
	{
		vector<vector<int> > a(n + 1, vector<int>(n + 1));
		int i = 0;
		int  j = 0;
		int  k = 1;
		for (i = 1; i <= n / 2; ++i)
		{
			for (j = i; j <= n - i; ++j) /* 左侧 */
			{
				a[i][j] = k++;
			}
			for (j = i; j <= n - i; ++j) /* 下方 */
			{
				a[j][n + 1 - i] = k++;
			}
			for (j = n - i + 1; j >= i + 1; j = j - 1) /* 右侧 */
			{
				a[n + 1 - i][j] = k++;
			}
 
			for (j = n - i + 1; j >= i + 1; j = j - 1) /* 上方 */
			{
				a[j][i] = k++;
			}
		}
		if (n%2==1) a[n/2+1][n/2+1]=k;
		cout<<a[x][y]<<endl;
	}
	return 0;
}

