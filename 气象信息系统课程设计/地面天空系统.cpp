#include <stdio.h>
#include <stdio.h>
#include <string.h>
int str2int(char *ch,int k,int n);
void dmdisp(char *ch); //  对ch进行地面译码的功能函数由学生自行完成
void updisp(char *ch); //  对ch进行高空译码的功能函数由学生自行完成
void main(void) {
	int year,month,day,hour,n,p;
	char station[6],name[]="AAXX0000.T00",ch[400];
	FILE *fp;

	printf("请输入年、月、日\n");
	scanf("%d%d%d",&year,&month,&day);
	name[4]='0'+month/10;
	name[5]='0'+month%10;
	name[6]='0'+day/10;
	name[7]='0'+day%10;

	printf("请选择：1-地面；2-高空\n");
	scanf("%d",&p);
	if (p!=1) {
		name[0]='T';
		name[1]='T';
		name[2]='A';
		name[3]='A';
		p=13;
	}

	if (p!=1) printf("请输入高空世界时，供选择：0、6、12、18\n");
	else printf("请输入地面世界时，供选择：0、3、6、9、12、15、18、21\n");
	scanf("%d",&hour);
	name[10]='0'+hour/10;
	name[11]='0'+hour%10;

	printf("请输入台站号，南京为58238\n");
	scanf("%s",station);

	if (fp=fopen(name,"rt")) {
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
				if (p==1) dmdisp(ch);
				else updisp(ch);
				p=0;
				break;
			}
		}
		fclose(fp);
		if (p!=0) printf("%s--台站未找到，请检查！！！\n",station);
	} else  printf("%s--文件不存在，请检查！！！\n" ,name);
}
//长度为n的字符串转换为整形值。对于含有非数字字符，则返回负1。
//k：字符串的起始位置
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
//对字符串电码ch进行译码，翻译出地面各要素的值进行显示。
void dmdisp(char *ch) {
	int i;
	long int vv;
	int dd,ff,x,y,ww,m,n;
//  对ch进行译码，由学生自行完成
	//puts(ch);  // 显示读到的字符串电码内容
	printf("台站号：");
	for(i=0; i<5; i++)
		printf("%c",ch[i]);
	printf("\n");
	switch(ch[8]) {
		case '0' :
			printf("云底高度<50米");
			break;
		case '1' :
			printf("云底高度50-<100米");
			break;
		case '2' :
			printf("云底高度100-<200米");
			break;
		case '3' :
			printf("云底高度200-<300米");
			break;
		case '4' :
			printf("云底高度300-<600米");
			break;
		case '5' :
			printf("云底高度600-<1000米");
			break;
		case '6' :
			printf("云底高度1000-<1500米");
			break;
		case '7' :
			printf("云底高度1500-<2000米");
			break;
		case '8' :
			printf("云底高度2000-<2500米");
			break;
		case '9' :
			printf("云底高度>=2500米");
			break;
		default:
			printf("缺测");
	}
	printf("\n");
	vv=str2int(ch,9,2);
	if (vv==0)
		printf("有效能见度<100米");
	else if (vv<=50) {
		vv=vv*100;
		printf("有效能见度%d米",vv);
	} else if(vv<=55)
		printf("不用");
	else if (vv<=79) {
		vv=vv-50;
		printf("有效能见度%d千米",vv);
	} else if (vv==80)
		printf("有效能见度>=30千米");
	else if (vv<=88) {
		vv=(vv-80)*5+30;
		printf("有效能见度%dq千米",vv);
	} else if (vv==89)
		printf("有效能见度>70千米");
	else if (vv==90)
		printf("有效能见度<50米");
	else if (vv==91)
		printf("有效能见度为50米");
	else if (vv==92)
		printf("有效能见度为200米");
	else if (vv==93)
		printf("有效能见度为500米");
	else if (vv==94)
		printf("有效能见度为1千米");
	else if (vv==95)
		printf("有效能见度为2千米");
	else if (vv==96)
		printf("有效能见度为4千米");
	else if (vv==97)
		printf("有效能见度为10千米");
	else if (vv==98)
		printf("有效能见度为20千米");
	else if (vv==99)
		printf("有效能见度>=50千米");
	printf("\n");
	switch(ch[12]) {
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
	printf("\n");
	dd=str2int(ch,13,2);
	m=dd%10;
	n=dd/10;
	if(m<5)
		dd=n*10;
	else
		dd=(n+1)*10;
	if (dd==0)
		printf("为静止风");
	else {
		dd=dd*10;
		printf("风向为%d度",dd);
	}
	printf("\n");
	ff=str2int(ch,15,2);
	m=ff%10;
	n=ff/10;
	if(m<5)
		ff=n*10;
	else
		ff=(n+1)*10;
	ff=ff/10*10;
	printf("风速为%d米/秒",dd);
	printf("\n");
	n=18;
	if(ch[n]=='1') {
		printf("气温资料为：");
		switch(ch[n+1]) {
			case'0':
				printf(" ");
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
	printf("\n");
	if(ch[n]=='2') {
		printf("露点温度资料为：");
		switch(ch[n+1]) {
			case'0':
				printf(" ");
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
	printf("\n");
	if(ch[n]=='3') {
		printf("本站气压资料为:");
		x=str2int(ch,n+1,3);
		y=str2int(ch,n+4,1);
		printf("本站气压为%d.%d百帕",x,y);
		n+=6;
	}
	printf("\n");
	if(ch[n]=='4') {
		printf("海平面气压资料为:");
		x=str2int(ch,n+1,3);
		y=str2int(ch,n+4,1);
		if (x<5)
			x=x+10;
		printf("气压值为%d.%d百帕",x,y);
		n+=6;
	}
	printf("\n");
	if(ch[n]=='5') {
		printf("过去三小时本站气压变化量为:");
		if (ch[n+1]>4)
			printf("上升");
		else  printf("下降");
		x=str2int(ch,n+2,2);
		y=str2int(ch,n+4,1);
		printf("%d.%d百帕",x,y);
		n+=6;
	}
	printf("\n");
	if(ch[n]=='6') {
		printf("过去六小时降水量资料：");
		x=str2int(ch,n+1,3);
		if (x==0)
			printf("降水量无");
		else if (x<=998)
			printf("降水量为%d毫米",x);
		else if(x==990)
			printf("降水量微量");
		else if(x<=999)
			printf("降水量为%d毫米",(x-990)/10);
		n+=6;
	}
	printf("\n");
	if(ch[n]=='7') {
		printf("观测时和过去的天气现象资料为：");
		ww=str2int(ch,n+1,2);
		if ((ww==00)||(ww==01)||(ww==02)||(ww==03))
			printf("观测时天气现象无");
		else if (ww==4)
			printf("观测时天气现象为烟");
		else if (ww==5)
			printf("观测时天气现象为霾");
		else if (ww<=9)
			printf("观测时天气现象为沙尘");
		else if (ww<=12)
			printf("观测时天气现象为轻、浅雾");
		else if (ww==13)
			printf("观测时天气现象为闪电");
		else if (ww<=16)
			printf("观测时天气现象为降水");
		else if (ww==17)
			printf("观测时天气现象为雷暴");
		else if (ww==18)
			printf("观测时天气现象为飑");
		else if (ww==19)
			printf("观测时天气现象为龙卷");
		else if (ww==20)
			printf("观测时天气现象为毛毛雨");
		else if (ww==21)
			printf("观测时天气现象为雨");
		else if (ww==22)
			printf("观测时天气现象为雪、米雪");
		else if (ww==23)
			printf("观测时天气现象为雨夹雪");
		else if (ww==24)
			printf("观测时天气现象为雨、雨凇");
		else if (ww==25)
			printf("观测时天气现象为阵雪");
		else if (ww==26)
			printf("观测时天气现象为阵雨夹雪");
		else if (ww==27)
			printf("观测时天气现象为冰雹");
		else if (ww==28)
			printf("观测时天气现象为大雾");
		else if (ww==29)
			printf("观测时天气现象为雷暴");
		else if (ww<=35)
			printf("观测时天气现象为沙尘暴");
		else if (ww<=39)
			printf("观测时天气现象为吹雪");
		else if (ww<=49)
			printf("观测时天气现象为浓雾");
		else if (ww<=59)
			printf("观测时天气现象为浓毛毛雨");
		else if (ww<=61)
			printf("观测时天气现象为小雨");
		else if (ww<=63)
			printf("观测时天气现象为中雨");
		else if (ww<=65)
			printf("观测时天气现象为大雨");
		else if (ww<=67)
			printf("观测时天气现象为雨凇");
		else if (ww<=69)
			printf("观测时天气现象为中雨降水");
		else if (ww<=71)
			printf("观测时天气现象为小雪");
		else if (ww<=73)
			printf("观测时天气现象为中雪");
		else if (ww<=75)
			printf("观测时天气现象为大雪");
		else if (ww==76)
			printf("观测时天气现象为冰针");
		else if (ww==77)
			printf("观测时天气现象为米雪");
		else if (ww==78)
			printf("观测时天气现象为雪晶");
		else if (ww==79)
			printf("观测时天气现象为冰粒");
		else if (ww==80)
			printf("观测时天气现象为小阵雨");
		else if (ww==81)
			printf("观测时天气现象为中阵雨");
		else if (ww==82)
			printf("观测时天气现象为大阵雨");
		else if (ww==83)
			printf("观测时天气现象为小阵性雨夹雪");
		else if (ww==84)
			printf("观测时天气现象为大阵性雨夹雪");
		else if (ww==85)
			printf("观测时天气现象为小阵雪");
		else if (ww==86)
			printf("观测时天气现象为中或大阵雪");
		else if (ww==87)
			printf("观测时天气现象为小阵性霰");
		else if (ww==88)
			printf("观测时天气现象为中或大阵性霰");
		else if (ww==89)
			printf("观测时天气现象为轻冰雹");
		else if (ww==90)
			printf("观测时天气现象为中或强冰雹");
		else if (ww==91)
			printf("观测时天气现象为雷暴后小雨");
		else if (ww==92)
			printf("观测时天气现象为雷暴后中雨");
		else if (ww==93)
			printf("观测时天气现象为雷暴后小雪");
		else if (ww==94)
			printf("观测时天气现象为雷暴后大雪");
		else if (ww==95)
			printf("观测时天气现象为小雷暴伴雨雪");
		else if (ww==96)
			printf("观测时天气现象为小雷暴伴冰雹");
		else if (ww==97)
			printf("观测时天气现象为大雷暴伴雨雪");
		else if (ww==98)
			printf("观测时天气现象为雷暴、沙尘暴");
		else if (ww==99)
			printf("观测时天气现象为大雷暴伴冰雹");
		printf("\n");
		switch(ch[n+3]) {
			case'0':
				printf("主要过去六小时天气现象无");
				break;
			case'3':
				printf("主要过去六小时天气现象为沙尘暴");
				break;
			case'4':
				printf("主要过去六小时天气现象为雾");
				break;
			case'5':
				printf("主要过去六小时天气现象为毛毛雨");
				break;
			case'6':
				printf("主要过去六小时天气现象为非阵性雨");
				break;
			case'7':
				printf("主要过去六小时天气现象为固体降水");
				break;
			case'8':
				printf("主要过去六小时天气现象为阵雨");
				break;
			case'9':
				printf("主要过去六小时天气现象为雷暴");
				break;
			default:
				printf("数据错误");
		}

		switch(ch[n+4]) {
			case'0':
				printf("次要过去六小时天气现象无");
				break;
			case'3':
				printf("次要过去六小时天气现象为沙尘暴");
				break;
			case'4':
				printf("次要过去六小时天气现象为雾");
				break;
			case'5':
				printf("次要过去六小时天气现象为毛毛雨");
				break;
			case'6':
				printf("次要过去六小时天气现象为非阵性雨");
				break;
			case'7':
				printf("次要过去六小时天气现象为固体降水");
				break;
			case'8':
				printf("次要过去六小时天气现象为阵雨");
				break;
			case'9':
				printf("次要过去六小时天气现象为雷暴");
				break;
			default:
				printf("数据错误");
		}
		n+=6;
	}
	printf("\n");
	if(ch[n]=='8') {
		printf("云的资料为：");
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
	printf("\n");
	if(ch[n]=='9') {
		x=str2int(ch,n+1,2);
		y=str2int(ch,n+3,2);
		printf("实际观察时间为%d小时%d分钟",x,y);
		n+=6;
	}
	printf("\n");

}
//对字符串电码ch进行译码，翻译出高空各层各要素的值进行显示。
void updisp(char *ch) {
	//  对ch进行译码，由学生自行完成
	//puts(ch);  // 显示读到的字符串电码内容
	int n,i,p,y,t,d,f;
	n=(ch[10]==' ')?11:12;
	y=str2int(ch,n-6,2);
	printf("区站号 ：\n");
	for(i=0; i<5; i++)printf("%c",ch[n+i]);
	printf("\n");
	while(1) {
		n+=6;
		p=str2int(ch,n+2,3);
		if(ch[n]=='9'&&ch[n+1]=='9') {
			if(p<=100) printf("地面或本站气压的位势米：%d",(p+1000));
			else printf("地面或本站气压的位势米：%d",p);
		} else if(ch[n]=='0'&&ch[n+1]=='0') {
			if(p>500)p=500-p;
			printf("气压1000hPa位势米:%d",p);
		} else if(ch[n]=='9'&&ch[n+1]=='2') {
			printf("925hPa位势米：%d",p);
		} else if(ch[n]=='8'&&ch[n+1]=='5')
			printf("850hPa位势米：%d",(p+1000));
		else if(ch[n]=='7'&&ch[n+1]=='0') {
			if(p>300) p+=2000;
			else p+=3000;
			printf("700hPa位势米：%d",p);
		} else if(ch[n]=='5'||ch[n]=='4'&&ch[n+1]=='0') {
			printf("500hPa，400hPa 位势米：%d",p*10);
		} else if((ch[n]=='3'&&ch[n+1]=='0')||(ch[n]=='2'&&ch[n+1]=='5')) {
			if(p<500) p=(p+1000)*10;
			else p=p*10;
			printf("300hPa，250hPa 位势米：%d",p);
		} else if((ch[n]=='2'&&ch[n+1]=='0')||(ch[n]=='1'&&ch[n+1]=='5')||(ch[n]=='1'&&ch[n+1]=='0'))
			printf("200hPa，150hPa，100hPa 位势米：%d",(p+1000)*10);
		else break;
		printf("\n");
		n+=6;
		t=str2int(ch,n,3);
		if(t%2==0)printf("气温： ");
		else printf("气温：-");
		printf("%.1f℃\n",p/10.0);
		d=str2int(ch,n+3,2);
		if(d>50) d=d-50;
		else d=d/10;
		printf("露点温度差：%d℃\n",d);
		n+=6;
		d=str2int(ch,n,3);
		f=str2int(ch,n+3,2);
		if(d%5==0) printf("风向：%d",d);
		printf("\n");
		if(y>50) printf("风速：%d米/秒",f*0.5);
		else printf("风速：%d米/秒",f);
		printf("\n");
	}
}
}

