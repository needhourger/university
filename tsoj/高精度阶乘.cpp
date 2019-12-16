#include<iostream>
#include<cstring>

int ans[100000];
int n, i, y;
int asdwasd[100000];

int main()
{
	while (std::cin >> n) {
		ans[0] = 1;
		ans[1] = 1;
		for (y = 1; y <= n; y++)
		{
			memset(asdwasd, 0, sizeof(asdwasd));
			asdwasd[0] = ans[0];
			for (i = 1; i <= ans[0]; i++)
			{
				asdwasd[i] += ans[i] * y;
				asdwasd[i + 1] = asdwasd[i] / 10;
				asdwasd[i] %= 10;
			}
			while (asdwasd[asdwasd[0] + 1] > 0)
			{
				asdwasd[asdwasd[0] + 2] = asdwasd[asdwasd[0] + 1] / 10;
				asdwasd[asdwasd[0] + 1] %= 10;
				asdwasd[0]++;
			}
			for (i = 1; i <= asdwasd[0]; i++) ans[i] = asdwasd[i];
			ans[0] = asdwasd[0];
		}
		for (i = ans[0]; i >= 1; i--) std::cout << ans[i];
		std::cout << std::endl;
	}
	return 0;
}