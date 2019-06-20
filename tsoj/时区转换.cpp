#include<stdio.h>
#include<stdlib.h>
#include<string.h>
unsigned int n;
unsigned int XIAOSHI;
unsigned int FENZHONG;
char SHEMESHIHOU[15];
char a[5];
char DIYICI[10];
char ZHONGZI[10];
int i,j;
int ZONGGONG;

char TIMEZONE[32][10]= {"UTC","GMT","BST","IST","WET","WEST","CET","CEST","EET","EEST","MSK","MSD","AST","ADT","NST","NDT","EST","EDT","CST","CDT","MST","MDT","PST","PDT","HST","AKST","AKDT","AEST","AEDT","ACST","ACDT","AWST"};
float TIMEOFFSET[32]= { 0, 0, 1, 1, 0, 1, 1, 2, 2, 3,
                   3, 4, -4, -3, -3.5, -2.5, -5, -4, -6, -5,
                   -7, -6, -8, -7, -10, -9, -8, 10, 11, 9.5,
                   10.5, 8
                 };

int DIFFERENT(char*s1,char*s2);

int main() {

	scanf("%d",&n);
	//getchar();
	for(i=0; i<n; i++) {
		scanf("%s",SHEMESHIHOU);
		// switch(SHEMESHIHOU[0]) {
		// 	case 'n': {
		// 		XIAOSHI=12;
		// 		FENZHONG=0;
		// 		break;
		// 	}
		// 	case 'm': {
		// 		XIAOSHI=0;
		// 		FENZHONG=0;
		// 		break;
		// 	}
		// 	default:
		// 		sscanf(SHEMESHIHOU,"%d:%d",&XIAOSHI,&FENZHONG);
		// 		XIAOSHI%=12;
		// 		scanf("%s",a);
		// 		if(a[0]=='p')XIAOSHI+=12;
		// }
		if (SHEMESHIHOU[0]=='n'){
			XIAOSHI=12;
			FENZHONG=0;
		}else if(SHEMESHIHOU[0]=='m'){
			XIAOSHI=0;
			FENZHONG=0;
		}else{
			sscanf(SHEMESHIHOU,"%d:%d",&XIAOSHI,&FENZHONG);
			XIAOSHI%=12;
			scanf("%s",a);
			if(a[0]=='p')XIAOSHI+=12;
		}
		scanf("%s %s",&DIYICI,&ZHONGZI);
		ZONGGONG=XIAOSHI*60+FENZHONG+DIFFERENT(DIYICI,ZHONGZI);
		if(ZONGGONG<0)   ZONGGONG+=24*60;
		if(ZONGGONG>=24*60)ZONGGONG-=24*60;
		XIAOSHI=ZONGGONG/60;
		FENZHONG=ZONGGONG%60;
		// switch(XIAOSHI) {
		// 	case 0: {
		// 		if(FENZHONG==0) {
		// 			printf("midnight\n");
		// 		} else {
		// 			printf("12:%d a.m.\n",FENZHONG);
		// 		}
		// 		break;
		// 	}
		// 	case 12: {
		// 		if(FENZHONG==0) {
		// 			printf("noon\n");
		// 		} else {
		// 			printf("12:%d p.m.\n",FENZHONG);
		// 		}
		// 		break;
		// 	}
		// 	default:
		// 		if(XIAOSHI>12) {
		// 			printf("%d:%d p.m.\n",XIAOSHI-12,FENZHONG);
		// 		} else {
		// 			printf("%d:%d a.m.\n",XIAOSHI,FENZHONG);
		// 		}
		// }
		if (XIAOSHI==0){
			if(FENZHONG==0) {
					printf("midnight\n");
				} else {
					printf("12:%d a.m.\n",FENZHONG);
				}
		}
		else if (XIAOSHI==12){
			if(FENZHONG==0) {
					printf("noon\n");
				} else {
					printf("12:%d p.m.\n",FENZHONG);
				}
		}
		else
		{
			if(XIAOSHI>12) {
					printf("%d:%d p.m.\n",XIAOSHI-12,FENZHONG);
				} else {
					printf("%d:%d a.m.\n",XIAOSHI,FENZHONG);
				}
		}
		
	}

	return 0;
}


int DIFFERENT(char*s1,char*s2) {
	int i,j;
	for(j=0; j<32; j++) {
		if(strcmp(s1,TIMEZONE[j])==0) break;
	}
	for(i=0; i<32; i++) {
		if(strcmp(s2,TIMEZONE[i])==0) break;
	}
	return (int)((TIMEOFFSET[i]-TIMEOFFSET[j])*60);
}