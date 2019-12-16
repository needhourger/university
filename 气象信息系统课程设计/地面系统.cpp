#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int str2int(char *ch,int k,int n);
void dmdisp(char *ch);  

void logo(){
	system("cls");
	printf(
	"=============================================\n"
	"	       气象信息译码系统 \n"
	"=============================================\n" 
	);
}

int main() {
	int year,month,day,hour,n,p;
	char station[6],name[]="AAXX0000.T00",ch[400];
	char file[21]="./data/";
	FILE *fp;

	while (1){
		logo();
		printf("请输入年,月,日[YYYY MM DD]:\n");
		scanf("%d%d%d",&year,&month,&day);
		name[4]='0'+month/10;
		name[5]='0'+month%10;
		name[6]='0'+day/10;
		name[7]='0'+day%10;
	
		printf("请输入地面世界时[0,3,6,9,12,15,18,21]:");
		scanf("%d",&hour);
		name[10]='0'+hour/10;
		name[11]='0'+hour%10;
	 
		printf("请输入台站号[默认南京为58238]:");
		scanf("%s",station);
		
		strcat(file,name); 
		if (fp=fopen(file,"rt")) {
			fgets(ch,80,fp);
			fgets(ch,80,fp);
			if (p==1) fgets(ch,80,fp);
			while(!feof(fp)) {
				fgets(ch,80,fp);
				n=strlen(ch);
				while (n<300 && ch[n-1] != '=' && !feof(fp)) {
					ch[n]= ' ';
	
					fgets(&ch[n+1],80,fp);
					n=strlen(ch)-1;
				}
				if (!strncmp(&ch[p-1],station,5)) {
					dmdisp(ch);
					p=0;
					break;
				}
			}
			fclose(fp);
			if (p!=0) printf("%s--台站未找到，请检查输入数据！\n",station);
		} else  printf("%s--文件不存在，请检查输入数据！\n" ,name);
		system("pause");
	}
	
}

int str2int(char *ch,int k,int n) {
	int i,m=0;
	for(i=0; i<n; i++) {
		if (ch[k+i]<'0' || ch[k+i]>'9') {
			m=-1;
			break;
		}
		m=m*10+ch[k+i]-'0';
	}
	return m;
}

void dmdisp(char *ch) {
	int i;
	long int vv;
	int dd,ff,x,y,ww,m,n;
	printf("\n==============================");
	printf("[台站号]:");
	for(i=0; i<5; i++)
		printf("%c",ch[i]);
	printf("\n\t云层高度\t");
	switch(ch[8]) {
		case '0' :
			printf("<50米");
			break;
		case '1' :
			printf("50-<100米");
			break;
		case '2' :
			printf("100-<200米");
			break;
		case '3' :
			printf("200-<300米");
			break;
		case '4' :
			printf("300-<600米");
			break;
		case '5' :
			printf("600-<1000米");
			break;
		case '6' :
			printf("1000-<1500米");
			break;
		case '7' :
			printf("1500-<2000米");
			break;
		case '8' :
			printf("2000-<2500米");
			break;
		case '9' :
			printf(">=2500米");
			break;
		default:
			printf("缺测");
	}
	printf("\n\t有效能见度\t");
	vv=str2int(ch,9,2);
	if (vv==0)
		printf("<100米");
	else if (vv<=50) {
		vv=vv*100;
		printf("%d米",vv);
	} else if(vv<=55)
		printf("不用");
	else if (vv<=79) {
		vv=vv-50;
		printf("%d千米",vv);
	} else if (vv==80)
		printf(">=30千米");
	else if (vv<=88) {
		vv=(vv-80)*5+30;
		printf("%d千米",vv);
	} else if (vv==89)
		printf(">70千米");
	else if (vv==90)
		printf("<50米");
	else if (vv==91)
		printf("50米");
	else if (vv==92)
		printf("200米");
	else if (vv==93)
		printf("500米");
	else if (vv==94)
		printf("1千米");
	else if (vv==95)
		printf("2千米");
	else if (vv==96)
		printf("4千米");
	else if (vv==97)
		printf("10千米");
	else if (vv==98)
		printf("20千米");
	else if (vv==99)
		printf(">=50千米");
	printf("\n\t总云量\t\t");
	switch(ch[12]) {
		case '0' :
			printf("无云");
			break;
		case '1' :
			printf("1");
			break;
		case '2' :
			printf("2-3");
			break;
		case '3' :
			printf("4");
			break;
		case '4' :
			printf("5");
			break;
		case '5' :
			printf("6");
			break;
		case '6' :
			printf("7-8");
			break;
		case '7' :
			printf("9");
			break;
		case '8' :
			printf("10");
			break;
		case '9' :
			printf("有雾");
			break;
		default:
			printf("缺测");
	}
	printf("\n\t风向\t\t");
	dd=str2int(ch,13,2);
	m=dd%10;
	n=dd/10;
	if(m<5)
		dd=n*10;
	else
		dd=(n+1)*10;
	if (dd==0)
		printf("静止风");
	else {
		dd=dd*10;
		printf("风向%d度",dd);
	}
	printf("\n\t风速\t\t");
	ff=str2int(ch,15,2);
	m=ff%10;
	n=ff/10;
	if(m<5)
		ff=n*10;
	else
		ff=(n+1)*10;
	ff=ff/10*10;
	printf("%d米/秒",dd);
	n=18;
	if(ch[n]=='1') {
		printf("\n\t气温资料\t");
		switch(ch[n+1]) {
			case'0':
				break;
			case'1':
				printf("-");
				break;
			default:
				printf("数据出错");
		}
		x=str2int(ch,n+2,2);
		y=str2int(ch,n+4,1);
		printf("%d.%d度",x, y);
		n+=6;
	}
	
	if(ch[n]=='2') {
		printf("\n\t露点温度\t");
		switch(ch[n+1]) {
			case'0':
				break;
			case'1':
				printf("-");
				break;
			default:
				printf("数据出错");
		}
		x=str2int(ch,n+2,2);
		y=str2int(ch,n+4,1);
		printf("%d.%d度",x,y);
		n+=6;
	}
	
	if(ch[n]=='3') {
		printf("\n\t气压资料\t");
		x=str2int(ch,n+1,3);
		y=str2int(ch,n+4,1);
		printf("%d.%d百帕",x,y);
		n+=6;
	}
	
	if(ch[n]=='4') {
		printf("\n\t海平面气压\t");
		x=str2int(ch,n+1,3);
		y=str2int(ch,n+4,1);
		if (x<5)
			x=x+10;
		printf("%d.%d百帕",x,y);
		n+=6;
	}
	
	if(ch[n]=='5') {
		printf("\n\t过去三小时气压变化量\t");
		if (ch[n+1]>4)
			printf("上升");
		else  printf("下降");
		x=str2int(ch,n+2,2);
		y=str2int(ch,n+4,1);
		printf("%d.%d百帕",x,y);
		n+=6;
	}
	
	if(ch[n]=='6') {
		printf("\n\t过去六小时降水\t\t");
		x=str2int(ch,n+1,3);
		if (x==0)
			printf("降水量无");
		else if (x<=998)
			printf("降水量%d毫米",x);
		else if(x==990)
			printf("降水量微量");
		else if(x<=999)
			printf("降水量%d毫米",(x-990)/10);
		else printf("降水量无");
		n+=6;
	}
	
	if(ch[n]=='7') {
		printf("\n\n\t观测时和过去的天气现象资料:");
		ww=str2int(ch,n+1,2);
		if ((ww==00)||(ww==01)||(ww==02)||(ww==03))
			printf("无");
		else if (ww==4)
			printf("烟");
		else if (ww==5)
			printf("霾");
		else if (ww<=9)
			printf("沙尘");
		else if (ww<=12)
			printf("轻、浅雾");
		else if (ww==13)
			printf("闪电");
		else if (ww<=16)
			printf("降水");
		else if (ww==17)
			printf("雷暴");
		else if (ww==18)
			printf("飑");
		else if (ww==19)
			printf("龙卷");
		else if (ww==20)
			printf("毛毛雨");
		else if (ww==21)
			printf("雨");
		else if (ww==22)
			printf("雪、米雪");
		else if (ww==23)
			printf("雨夹雪");
		else if (ww==24)
			printf("雨、雨凇");
		else if (ww==25)
			printf("阵雪");
		else if (ww==26)
			printf("阵雨夹雪");
		else if (ww==27)
			printf("冰雹");
		else if (ww==28)
			printf("大雾");
		else if (ww==29)
			printf("雷暴");
		else if (ww<=35)
			printf("沙尘暴");
		else if (ww<=39)
			printf("吹雪");
		else if (ww<=49)
			printf("浓雾");
		else if (ww<=59)
			printf("浓毛毛雨");
		else if (ww<=61)
			printf("小雨");
		else if (ww<=63)
			printf("中雨");
		else if (ww<=65)
			printf("大雨");
		else if (ww<=67)
			printf("雨凇");
		else if (ww<=69)
			printf("中雨降水");
		else if (ww<=71)
			printf("小雪");
		else if (ww<=73)
			printf("中雪");
		else if (ww<=75)
			printf("大雪");
		else if (ww==76)
			printf("冰针");
		else if (ww==77)
			printf("米雪");
		else if (ww==78)
			printf("雪晶");
		else if (ww==79)
			printf("冰粒");
		else if (ww==80)
			printf("小阵雨");
		else if (ww==81)
			printf("中阵雨");
		else if (ww==82)
			printf("大阵雨");
		else if (ww==83)
			printf("小阵性雨夹雪");
		else if (ww==84)
			printf("大阵性雨夹雪");
		else if (ww==85)
			printf("小阵雪");
		else if (ww==86)
			printf("中或大阵雪");
		else if (ww==87)
			printf("小阵性霰");
		else if (ww==88)
			printf("中或大阵性霰");
		else if (ww==89)
			printf("轻冰雹");
		else if (ww==90)
			printf("中或强冰雹");
		else if (ww==91)
			printf("雷暴后小雨");
		else if (ww==92)
			printf("雷暴后中雨");
		else if (ww==93)
			printf("雷暴后小雪");
		else if (ww==94)
			printf("雷暴后大雪");
		else if (ww==95)
			printf("小雷暴伴雨雪");
		else if (ww==96)
			printf("小雷暴伴冰雹");
		else if (ww==97)
			printf("大雷暴伴雨雪");
		else if (ww==98)
			printf("雷暴、沙尘暴");
		else if (ww==99)
			printf("大雷暴伴冰雹");
		printf("\n\t主要过去六小时天气现象:");
		switch(ch[n+3]) {
			case'0':
				printf("无");
				break;
			case'3':
				printf("沙尘暴");
				break;
			case'4':
				printf("雾");
				break;
			case'5':
				printf("毛毛雨");
				break;
			case'6':
				printf("非阵性雨");
				break;
			case'7':
				printf("固体降水");
				break;
			case'8':
				printf("阵雨");
				break;
			case'9':
				printf("雷暴");
				break;
			default:
				printf("数据错误");
		}
		printf("\n\t次要过去六小时天气现象:");
		switch(ch[n+4]) {
			case'0':
				printf("无");
				break;
			case'3':
				printf("沙尘暴");
				break;
			case'4':
				printf("雾");
				break;
			case'5':
				printf("毛毛雨");
				break;
			case'6':
				printf("非阵性雨");
				break;
			case'7':
				printf("固体降水");
				break;
			case'8':
				printf("阵雨");
				break;
			case'9':
				printf("雷暴");
				break;
			default:
				printf("数据错误");
		}
		n+=6;
	}
	
	if(ch[n]=='8') {
		printf("\n\t云的资料:");
		switch(ch[n+1]) {
			case '0' :
				printf("无云");
				break;
			case '1' :
				printf("总云量为1");
				break;
			case '2' :
				printf("云底高度总云量为2-3");
				break;
			case '3' :
				printf("总云量为4");
				break;
			case '4' :
				printf("总云量为5");
				break;
			case '5' :
				printf("总云量为6");
				break;
			case '6' :
				printf("总云量为7-8");
				break;
			case '7' :
				printf("总云量为9");
				break;
			case '8' :
				printf("总云量为10");
				break;
			case '9' :
				printf("有雾");
				break;
			default:
				printf("缺测");
		}
		switch(ch[n+2]) {
			case '0' :
				printf("无低云");
				break;
			case '1' :
				printf("低云状为淡积云");
				break;
			case '2' :
				printf("低云状为浓积云");
				break;
			case '3' :
				printf("低云状为积层云");
				break;
			case '4' :
				printf("低云状为积层云");
				break;
			case '5' :
				printf("低云状为积层云");
				break;
			case '6' :
				printf("低云状为碎雨云");
				break;
			case '7' :
				printf("低云状为碎雨云");
				break;
			case '8' :
				printf("低云状为积云");
				break;
			case '9' :
				printf("低云状为鬃积云");
				break;
			default:
				printf("数据错误");
		}
		switch(ch[n+3]) {
			case '0' :
				printf("无中云");
				break;
			case '1' :
				printf("中云状为高层云");
				break;
			case '2' :
				printf("中云状为雨层云");
				break;
			case '3' :
				printf("中云状为高积云");
				break;
			case '4' :
				printf("中云状为荚状云");
				break;
			case '5' :
				printf("中云状为层积云");
				break;
			case '6' :
				printf("中云状为积雨云");
				break;
			case '7' :
				printf("中云状为积层云");
				break;
			case '8' :
				printf("中云状为积云");
				break;
			case '9' :
				printf("中云状为散积云");
				break;
			default:
				printf("数据错误");
		}
		switch(ch[n+4]) {
			case '0' :
				printf("无低云");
				break;
			case '1' :
				printf("高云状为毛卷云");
				break;
			case '2' :
				printf("高云状为密卷云");
				break;
			case '3' :
				printf("高云状为伪卷云");
				break;
			case '4' :
				printf("高云状为钩卷云");
				break;
			case '5' :
				printf("高云状为卷云");
				break;
			case '6' :
				printf("高云状为辐卷云");
				break;
			case '7' :
				printf("高云状为卷层云");
				break;
			case '8' :
				printf("高云状为层云");
				break;
			case '9' :
				printf("高云状为卷积云");
				break;
			default:
				printf("数据错误");
		}
		n+=6;
	}
	
	if(ch[n]=='9') {
		printf("\n\t实际观测时间:");
		x=str2int(ch,n+1,2);
		y=str2int(ch,n+3,2);
		printf("%d小时%d分钟",x,y);
		n+=6;
	}
	printf("\n\t");

}


