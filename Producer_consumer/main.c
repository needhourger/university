#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<pthread.h>
#include<stdbool.h>
#define MAX_LINE 256

int bufferSize;
int x,y;
int numP,numC;

/* 定义循环队列 */ 
typedef struct{
	int *numArray;	//数据
	int font;		//队列首部 
	int rear;		//队列尾部 
	int capacity;	//队列总长度 
	int size;		//队列内元素个数 
}queueArray;

/* 定义缓冲区 */ 
typedef struct{
	queueArray* bufferArray;		//循环队列定义 
	pthread_mutex_t producerLock;	//生产线程互斥锁 
	pthread_mutex_t consumerLock;	//消费线程互斥锁 
	int producerSleep;				//生产线程睡眠时间 
	int consumerSleep;				//消费线程睡眠时间 
}buffer;


/* 创建循环队列，变量初始化 */ 
queueArray* createQueueArray(int len){
	queueArray* queue=(queueArray*)malloc(sizeof(queueArray));	//循环队列分配空间 
	queue->font		=0;		//初始化队首0 
	queue->rear		=0;		//初始化队尾0 
	queue->capacity	=len;	//初始化队列大小bufferSize 
	queue->size		=0;		//初始化队列元素格式0 
	queue->numArray=(int*)malloc(len*sizeof(int));	//为队列元素分配空间 
	return queue; 	//返回队列指针 
}

/* 清空队列（未使用） */  
void clearQueueArray(queueArray* queue){
	queue->font=0;
	queue->rear=0;
	queue->size=0;
}

/* 判断队列是否为空 */ 
bool isEmpty(queueArray* queue){
//	printf("Queue Size:[%d]\n",queue->size);
//	printf("Queue capacity:[%d]\n",queue->capacity);
	if (queue->size==0) return true;
	return false;
}

/* 判断队列是否已满 */ 
bool isFull(queueArray* queue){
//	printf("Queue Size:[%d]\n",queue->size);
//	printf("Queue capacity:[%d]\n",queue->capacity);
	if (queue->size==queue->capacity) return true;
	return false;
}

/* 向循环队列中写入数据 */ 
int writeQueueArray(queueArray* queue,int num){
	if (isFull(queue)) return 0;
	if (queue->rear+1<queue->capacity) {
		queue->numArray[queue->rear++]=num;
	}else {
		queue->numArray[0]=num;
		queue->rear=0;
	}
	queue->size++;
	return 1;
}

/* 从循环队列中读取数据 */ 
int readQueueArray(queueArray* queue){
	int ret=0;
	if (isEmpty(queue)) return ret;
	if (queue->font+1<queue->capacity){
		ret=queue->numArray[queue->font];
		queue->font+1;
	}else{
		ret=queue->numArray[queue->font];
		queue->font=0;	
	}
	queue->size--;
	return ret;
}


/* 打印循环的队列中现有元素（debug用）*/ 
void fetchall(queueArray* queue){
	int i=0;
	int p;
	for (i=0,p=queue->font;i<queue->size;i++){
		if (p<queue->capacity) printf("%d\n",queue->numArray[p]);
		else {
			p=0;
			printf("%d\n",queue->numArray[p]);
		}
	}
}

/* 销毁循环队列，释放空间（未使用）*/ 
void destroyQueueArray(queueArray* queue){
	free(queue->numArray);
	return;
}

/* 创建缓冲区 */ 
buffer* createBuffer(){
	buffer* buf=NULL;
	queueArray* queue=createQueueArray(bufferSize);		//调用创建循环队列函数 
	buf=(buffer*)malloc(sizeof(buffer));				//为缓冲区分配空间 
	buf->bufferArray	=queue;							//初始化缓冲区中的循环队列指针 
	buf->producerSleep	=x;								//初始化生产线程睡眠时间 
	buf->consumerSleep	=y;								//初始化消费线程睡眠时间 
	if (pthread_mutex_init(&buf->producerLock,NULL)){	//初始化生产线程锁 
		printf("Producer Mutex initialization failed");
	}
	if (pthread_mutex_init(&buf->consumerLock,NULL)){	//初始化消费线程锁 
		printf("Consumer Mutex initialization failed");
	}
	return buf;
}

/* 从文件中获取程序运行变量参数 */ 
int getArguement(const char* filename){
	char buf[MAX_LINE];		//定义文件读取缓冲区 
	FILE *fp=NULL;			//生命文件指针 
	if ((fp=fopen(filename,"r"))==NULL){	//打开文件 
		printf("Cant not read file:[%s]\n",filename);
		return 0;
	}
	if (!feof(fp)){	//按行读取文件中的参数 
		fgets(buf,MAX_LINE,fp);
		bufferSize=atoi(buf);
		printf("The buffer size:[%d]\n",bufferSize);
		
		fgets(buf,MAX_LINE,fp);
		x=atoi(buf);
		printf("The x:[%d]\n",x);
		
		fgets(buf,MAX_LINE,fp);
		y=atoi(buf);
		printf("The y:[%d]\n",y);
		
		fgets(buf,MAX_LINE,fp);
		numP=atoi(buf);
		printf("The number of producer:[%d]\n",numP);
		
		fgets(buf,MAX_LINE,fp);
		numC=atoi(buf);
		printf("The number of consumer:[%d]\n",numC);
	}
	return 1;
	
}

/* 生产者线程函数 */ 
void* producer(void* args){
	int num;
	buffer* buf=(buffer*)args;	//参数格式转换 
	while(1){
		pthread_mutex_lock(&buf->producerLock);	//线程加锁 
		srand((unsigned)time(NULL));			//初始化随机数种子 
		num=rand()%100000000;					//生成随机数 
		if (writeQueueArray(buf->bufferArray,num)){			//尝试向缓冲区写入该随机数 
			printf("Generate number [%d] and writed\n",num);
		}else{
			printf("Buffer is full wait for consumer\n");
		}
		pthread_mutex_unlock(&buf->producerLock);	//线程解锁 
		sleep(buf->producerSleep);	//线程睡眠 
	}
}

/* 消费者线程函数 */ 
void* consumer(void* args){
	int num;
	buffer* buf=(buffer*)args;	//参数格式转换 
	while (1){
		pthread_mutex_lock(&buf->consumerLock);		//线程上锁 
		num=readQueueArray(buf->bufferArray);		//尝试从缓冲区读取数据 
		if (num==0){			
			printf("buffer is empty wait for producer\n");
		}else{
			printf("Read from buffer number:[%d]\n",num);
		}
		pthread_mutex_unlock(&buf->consumerLock);	//线程解锁 
		sleep(buf->consumerSleep);	//线程睡眠 
	}
}

/* 创建生产，消费线程 */ 
pthread_t* createThreads(buffer* buf){
	int i;
	//为生产消费线程池分配空间 
	pthread_t* threads=(pthread_t*)malloc(numP*sizeof(pthread_t)+numC*sizeof(pthread_t));
	
	for (i=0;i<numP;i++){	//创建生产线程 
		if (pthread_create(&threads[i],NULL,producer,buf)){
			printf("Producer thread [%d] field\n",i+1);
		}else printf("Producer thread [%d] created\n",i+1);
	}
	
	for (;i<numC+numP;i++){	//创建消费线程 
		if (pthread_create(threads+i,NULL,consumer,buf)){
			printf("Consumer thread [%d] field\n",i+1);
		}else printf("Consumer thread [%d] created\n",i+1);
	}
	return threads;	//返回线程池地址 
}

/* 线程等待 */ 
void threadWait(pthread_t* threads){
	int i;
	for (i=0;i<numP+numC;i++)
		pthread_join(threads[i],NULL);
}


/* 主函数 */ 
int main(int argc,char* argv[]){
	buffer* buf=NULL;		//生命缓冲区指针 
	pthread_t* threads=NULL;//生命线程池指针 
	if (argc<=1){			//判断是否命令行传入参数 
		printf("Please input a argument file path!");
		return 0;
	}
	getArguement(argv[1]);	//从命令行参数文件地址中获取程序参数u 
	buf=createBuffer();		//创建缓冲区 
	threads=createThreads(buf);	//创建线程池 
	threadWait(threads);		//线程等待 
	
	return 0;
}
