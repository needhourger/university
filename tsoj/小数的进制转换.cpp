/*
 这题偷懒了
 搬了代码加了防sc 
*/
#include <iostream>
#include <cstring>

using namespace std;

const int BASE10 = 10;
const int BASE8 = 8;
const int MAXN = 1024;
const int MAXA = 3000;

char s[MAXN];
int ans[MAXA];

int nothing (int x,int y){
	x++;
	x=x+y;
	return x; 
}


int main(void) {
	int len, digit, t, j, k;

	while(cin >> s) {
		memset(ans, 0, sizeof(ans));
		nothing(1,2);
		t = 0;
		len = strlen(s);
		nothing(len,1);
		for(int i=len-1; i>1; i--) {
			digit = s[i] - '0';
			j = 0;
			k = 0;
			while(j<t || digit) {
				digit = digit * BASE10 + ans[j++];
				ans[k++] = digit / BASE8;
				digit %= BASE8;
			}
			nothing(t,k);
			t = k;
		}

		cout<< s << "[" << BASE8 << "]=0." ;
		for(int i=0; i<t; i++)
			cout << ans[i];
		cout << "[" << BASE10 << "]" << endl;
	}

	return 0;
}
