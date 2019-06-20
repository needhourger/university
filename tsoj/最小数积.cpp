#include<stdio.h>
#include<stdlib.h>

int main() {
	int N;
	while(scanf("%d", &N)!=EOF) {
		int *a = (int *)malloc(sizeof(int) *N);
		int *b = (int *)malloc(sizeof(int) *N);
		int *book1 = (int *)malloc(sizeof(int) *N);
		int *book2 = (int *)malloc(sizeof(int) *N);

		for (int i = 0; i < N; i++) {
			scanf("%d", &a[i]);
			book1[i] = 0;
			book2[i] = 0;
		}

		for (int i = 0; i < N; i++) {
			scanf("%d", &b[i]);
		}

		int min_of_a;
		int max_of_b;
		int result = 0;
		int count = 0;
		int temp;
		int label_a = 0;
		int label_b = 0;

		while (count < N) {
			for (; label_a < N; label_a++) {
				if (book1[label_a] == 0) {
					min_of_a = a[label_a];
					break;
				}
			}

			for (; label_b < N; label_b++) {
				if (book2[label_b] == 0) {
					max_of_b = b[label_b];
					break;
				}
			}

			for (int i = 0; i < N; i++) {
				if (min_of_a >=a[i] && book1[i] == 0) {
					min_of_a = a[i];
					temp = i;
				}
			}
			book1[temp] = 1;

			for (int i = 0; i < N; i++) {
				if (max_of_b <= b[i] && book2[i] == 0) {
					max_of_b = b[i];
					temp = i;
				}
			}
			book2[temp] = 1;

			result += min_of_a * max_of_b;
			count++;
		}
		printf("%d\n", result);
		free(a);
		free(b);
		free(book1);
		free(book2);
	}
	return 0;
}

