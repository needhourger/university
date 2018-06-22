/*第5题	电话簿管理--源代码注解如下：*/
#include<fstream>       
#include<conio.h>
#include<iostream>     
#include<iomanip>
#include<string.h>
#include<stdlib.h>
#include<time.h>

using namespace std;
 
class Base
{
	public:
	char date[20];
	char time[20];
    char szLName[20];
	char szFName[20];
    char szNumber[20];
};
 
class CTelRecord:public Base
{
public:
    CTelRecord();                                //缺省的构造函数
	~CTelRecord(){};                            //析构函数
    CTelRecord(char *name,char *number,char *,char*,char*);        //构造函数   
    void SetRecord(char *name,char *number,char *,char *D,char *T);   //设置姓名和电话号码
	void SetDate_Time(char *dd,char*tt){strcpy(date,dd);strcpy(time,tt);}
	char *GetLName(){return szLName;}                //获得姓氏
	char *GetFName(){return szFName;}               //获得名字
	char *GetNumber(){return szNumber;}             //获得电话号码
	char *GetDate(){return date;}
	char *GetTime(){return time;}
    int Compare(CTelRecord &);                      //按照姓名比较若返回值为零，则两者相等
    void Show();                                    //显示函数 
    void Modify(char*number);                       //修改电话号码
    int LookChar(char*szLName);                     //按照姓氏查找，若返回值为1，则表示查到，否则没有查到
};

class CNode
{
private:
	CTelRecord*pData;
    CNode*pNext;
public:
    CNode(){pData=new CTelRecord;pNext=0;}    //缺省的构造函数
	CNode(CTelRecord ctelrecord,CNode node){pData=&ctelrecord,pNext=&node;}//构造函数
    CNode(CNode&node);//重载拷贝构造函数
	~CNode(){};
    void InputData(CTelRecord*pdata){pData=pdata;}//数据录入函数
    void ShowNode(){pData->Show();}              //节点显示函数
    CTelRecord *GetData(){return pData;}         //获得节点信息函数
	CNode *GetpNext(){CNode *p=pNext;return p;}  //获得下一个节点
    friend class CList;        //友元类
};

CNode *current_ptr=NULL;  //全程变量，用于指明当前在链表中的位置
char pause;
class CList
{
	CNode *pHead;
public:
    CList(CNode *node=NULL){pHead=node;}       //构造函数
    ~CList(){DeleteList();}                   //析构函数
	void SetpHead(CNode*node){pHead=node;}    //重置链表头函数
    void AddNode(CNode *pnode);               //把接点添加到头部的函数
    CNode *DeleteNode(CNode *);               //删除节点函数
    CNode *LookUp(CTelRecord &);              //按照姓氏智能查询函数
    void ShowList();                         //显示链表函数
    void DeleteList();                       //删除链表函数
    CNode *GetListHead(){return pHead;}      //获得链表头部函数
	CNode *GetListEnd();                     //获得链表尾部函数
	CNode *GetListNextNode(CNode *);         //获得链表中指定节点的下个节点函数
    void Insert(CNode *pnode);               //排序插入函数
	int load_list_from_file();               //从文件载入数据函数
	void delete_record();                    //删除记录函数
	int verify_delete();                     //确认删除的函数
	void write_list_to_file();               //把信息写入文件的函数
	void handle_choice(int choice);          //选择处理函数
	void help_me();                         //帮助函数
};


CTelRecord::CTelRecord()
{
	strcpy(date,"date");
	strcpy(time,"time");
	strcpy(szLName,"姓氏");
	strcpy(szFName,"名字");
	strcpy(szNumber,"电话号码");
}

CTelRecord::CTelRecord(char *lastname,char *firstname,char *number,char *DD,char *TT)
{  
	strcpy(date,DD);
	strcpy(time,TT);
	strcpy(szLName,lastname);
	strcpy(szNumber,number);
    strcpy(szFName,firstname);
}
		 
void CTelRecord::SetRecord(char *lastname,char *firstname,char *number,char *DD,char *TT)
{
	strcpy(date,DD);
	strcpy(time,TT);
	strcpy(szLName,lastname);
	strcpy(szNumber,number);
	strcpy(szFName,firstname);
}

int CTelRecord::Compare(CTelRecord &ctelrecord)
{  
	if(strcmp(ctelrecord.szLName,szLName)==0||strcmp(ctelrecord.szFName,szFName)==0)
		return 0;
	else return 1;
}

void CTelRecord::Show()
{
	char name[40]="";
	strcpy(name,szLName);
	strcat(name,szFName);
	cout<<setw(40)<<name<<setw(20)<<szNumber<<setw(20)<<date<<setw(10)<<time<<'\n';
}

void CTelRecord::Modify(char *number)
{
	strcpy(szNumber,number);
}

int CTelRecord::LookChar(char szlastName[])
{
	if(strcmp(szLName,szlastName)==0)
		return 1;
	else
		return 0;
}

CNode::CNode(CNode &node)
{
	if(node.pData!=NULL)
	pData=node.pData,pNext=node.pNext;
}

void CList::AddNode(CNode *pnode)
{
	pHead=pnode;
	pHead->pNext=pnode->pNext=NULL;
}

CNode *CList::GetListNextNode(CNode *pnode)
{
	return pnode->pNext;
}

void CList::Insert(CNode *pnode)
{        // 根据姓氏，返回其在链表中的正确位置。新节点即将插入此点。
  char temp_name[20];
  CNode *temp_node;
  int tempint(1);
  if(pHead!=NULL)           // 确保链表头不为零！！！
  {                           
	  temp_node=current_ptr = pHead;   
      if(GetListNextNode(current_ptr)==NULL)                                //当链表为一个节点时
          if(strcmp(pnode->GetData()->GetLName(),pHead->GetData()->GetLName())<=0)   //当链表头的姓氏的值比
			                                                                           //要插入的姓氏的值大时
		  {
                pnode->pNext=pHead;
                pHead=pnode;
		  }
          else                                                      //这时候链表头的姓氏的值比要插入的姓氏的值小
	   	 pHead->pNext=pnode,pnode->pNext=0;
         else                                                      //当链表不为一个节点的时候
		 {
            tempint = strcmp(pnode->pData->GetLName(),temp_name);
            while(tempint >= 0 && temp_node->pNext!=NULL)          //循环到要插入的节点
			{ 
			    current_ptr=temp_node;
                temp_node =GetListNextNode(current_ptr);
                strcpy(temp_name, temp_node->GetData()->GetLName());
                tempint = strcmp(pnode->pData->GetLName(),temp_name);
			}
			if(temp_node==pHead)                                  //如果头节点的姓氏值比要插入的姓氏值大时
			{
                  pnode->pNext=pHead;                             //把新的节点置为链表头
                  pHead=pnode;
			}
			else
			{
	            if(tempint<0&&temp_node->pNext==0)         //刚好循环到链表尾，且他的值比前一个节点大

					temp_node->pNext=pnode,pnode->pNext=0;
				else
				{
					if(tempint<0&&temp_node->pNext!=0)         //在链表中部添加节点  
						pnode->pNext=temp_node,current_ptr->pNext=pnode;

				      else 
						  if(tempint >=0&&temp_node->pNext==0)        //在链表尾部添加节点
							  temp_node->pNext=pnode,pnode->pNext=0;
				}
			}	
		 }					 
  }
}  // 插入函数结束 

int CList::load_list_from_file()     // 	从数据文件FRIENDS.TXT中读取数据重建链表处理函数
{
	current_ptr=pHead;
	//fstream infile("FRIENDS.TXT",ios::in|ios::nocreate);
	fstream infile("FRIENDS.TXT",ios::in);
    CNode *new_rec_ptr;
    int end_loop = 0;
    if (!!infile)                // 如果成功打开文件
	{                           // 从文件读入数据
		cout<<"已成功打开\"FRIENDS.txt\"文件!\n";
		char dd[20];
		char tt[20];
		char LName[20];
	    char FName[20];
        char Number[20];
        for(;end_loop==0 ;)
		{
			new_rec_ptr=new CNode;  
		    if(new_rec_ptr->GetData()==NULL)
			{
				end_loop=1;
			    continue;
			}
		    if(new_rec_ptr != NULL) // 确保new_rec_ptr指针不为NULL
			{
				if(!infile.eof())   //如果文件中有数据可读入
				{
					infile.getline(LName,20);             
		            infile.getline(FName,20);
				    infile.getline(Number,20);
					infile.getline(dd,20);
					infile.getline(tt,20);
					if(strlen(LName) != 0 &&strcmp(LName, "END OF FILE") != 0)//如果姓氏的长度不为零
					{
						if(new_rec_ptr->GetData()!=NULL)
                        new_rec_ptr->GetData()->SetRecord(LName,FName,Number,dd,tt);
					    if(pHead==NULL)               //若头指针为零
						    AddNode(new_rec_ptr);
					    else
                            Insert(new_rec_ptr);         
					    strcpy(LName,"");
					    strcpy(FName,"");
					    strcpy(Number,"");
						strcpy(dd,"");
						strcpy(tt,"");
					}
			        else                                          
						end_loop = 1;					
				}
		        else  
				{ 
			       cout << "完成数据录入!\n";
                   end_loop = 1;
				}
			}
		}
	    return 1;
	    infile.close(); // 关闭文件
	}
    else  // 打开文件失败
	{
	     cout << "打开文件失败!请确保已建立\"FRIENDS.txt\"文件!\n";
         return 0;
	}
} // 文件录入函数结束 

void CList::delete_record()
{
	system("cls");
	char search_string[20];
	CNode *cnode;
	cnode = NULL;     
	current_ptr = pHead;  // 从链表头部开始                         
	cin.ignore(20,'\n');
	cout << "\n输入你想删除纪录的姓氏(一次只能删除一条纪录):\n ";
	cin.getline(search_string,20);
	cin.ignore(20,'\n');
    while((current_ptr != NULL) && current_ptr->GetData()!=NULL&&
	   strncmp(search_string,current_ptr->GetData()->GetLName(),strlen(search_string)) !=0&&
	   strncmp(search_string,current_ptr->GetData()->GetLName(),strlen(current_ptr->GetData()->GetLName())) !=0)
	{
		cnode = current_ptr;      
		current_ptr = current_ptr->pNext; 
	}                                 
   if(current_ptr != NULL&&current_ptr->GetData()!=NULL) 
	{                      
		cout << "\n符合的纪录:\n";
		cout << current_ptr->GetData()->GetLName() 
			<< current_ptr->GetData()->GetFName()<< endl;
         
		cout << current_ptr->GetData()->GetNumber() << endl;
		if(verify_delete()) 
		{                               
			DeleteNode(cnode);     
			cout << "\n已删除记录!\n";  
		}
		else                            
		{
			cout << "\n您没有删除任何记录!\n";
		}
   }
   else 
   {
		cout << "\n找不到节点，您没有删除任何记录!\n";
   }
    system("cls");
    system("date /t");
    system("time /t");
} //删除记录函数结束
 
CNode *CList::DeleteNode(CNode *cnode)
{
	system("date /t");
	system("time /t");
	if(current_ptr == pHead)  
	{                           
		current_ptr = pHead;  
		if(pHead->pNext != NULL)                            
			pHead = current_ptr->pNext;   
	} 
   else                        
	{                           
		if(current_ptr->pNext == NULL)       
		{                                  
			delete current_ptr; 
			cnode->pNext = NULL; 
			current_ptr = pHead; 
		}
		else                       
		{                                          
			cnode->pNext = current_ptr->pNext;			
		}                        
   } 
   return current_ptr;
}                       //删除接点函数结束
                               

void CList::DeleteList()
{
	CNode*temp_ptr;  
    current_ptr=pHead;  
    do    
	{
		temp_ptr=current_ptr->GetpNext();
		if(current_ptr->GetpNext()==NULL)
			 current_ptr=NULL;	 
      else
		  current_ptr=temp_ptr;                                      
	}while(temp_ptr!= NULL);       
}                              // 删除链表函数结束
 
void CList::ShowList()
{
	char fullname[30];
	char date_time[30];
	current_ptr = pHead;   
    if(current_ptr != NULL&&current_ptr->GetData()!=NULL)
	{
		cout << endl;
        cout << "姓名:                     电话号码:                       录入时间和日期:      \n";
        cout << "-------------------------------------------------------------------------------\n";
        do
		{
			strcpy(fullname,""); // 清除 fullname的内容.
            strcat(fullname, current_ptr->GetData()->GetLName());                                 
            strcat(fullname, current_ptr->GetData()->GetFName());
			strcpy(date_time,current_ptr->GetData()->GetDate());
			strcat(date_time,current_ptr->GetData()->GetTime());
            cout.setf(ios::left);
            cout << setw(30) << fullname;	    
            cout << setw(20) << current_ptr->GetData()->GetNumber()<<setw(30)<<date_time<< endl;
            current_ptr = current_ptr->pNext;                  
	        cout <<  endl;
		} 
		while(current_ptr != NULL); 
		cout << "Press Enter to continue \n";
	    cin.get(pause);
	    cin.ignore(1,pause);  
	}
  else 
  {
    cout << "\n尚未输入数据!\n";
  }
} // 显示信息函数结束

int CList::verify_delete()
{
	char YesNo;
  cout << "\nAre you sure (Y/N) ";
  cin >> YesNo;
  if((YesNo == 'Y') || (YesNo == 'y'))
   {
    return(1); 
   }
  else
   {
    return(0); 
   }
 } // 删除确认函数
 
void  CList::write_list_to_file()
{
	fstream outfile;
	//outfile.open("FRIENDS.TXT",ios::out|ios::nocreate);
	outfile.open("FRIENDS.TXT",ios::out);
    if (!!outfile)  
	{            
		current_ptr = pHead;  
        if(pHead != NULL&&current_ptr->GetData()!=0)  
		{                    
           do    
		   {

              outfile<<setiosflags(ios::left)<<current_ptr->GetData()->GetLName()<<'\n';
              outfile<<setiosflags(ios::left)<<current_ptr->GetData()->GetFName()<<'\n';
              outfile<<setiosflags(ios::left)<<current_ptr->GetData()->GetNumber()<<'\n';
			  outfile<<setiosflags(ios::left)<<current_ptr->GetData()->GetDate()<<'\n';
			  outfile<<setiosflags(ios::left)<<current_ptr->GetData()->GetTime()<<'\n';
              current_ptr = current_ptr->pNext;  
		   } while(current_ptr != NULL); 
		}
        outfile.close(); 
	}
    else 
	   {
          cout << "没有打开文件!\n";
	   }
} //文件录入函数结束

CNode * CList::LookUp(CTelRecord &ctelrecord)
{
	int i(0);
	system("cls");
	char search_string[20];  
	CNode *cc=current_ptr;
    current_ptr = pHead;  
    strcpy(search_string,ctelrecord.GetLName());   
	while(current_ptr!=NULL)
	{
		if(current_ptr->GetData()->LookChar(search_string)!=0)
		
		{
			if (i==0)
				cout<<"姓名:-----------------------------------"<<"电话号码:--------------\n"; 
			current_ptr->ShowNode();
			i++;
		}
		
		current_ptr=current_ptr->pNext;
	}
	if(i)
		cout<<"完成!\n";
	else 
		cout<<"找不到您要找的记录!\n";
	current_ptr=cc;
	return current_ptr;
}//智能查找函数结束

void CList::help_me()
{
	help:
	int choice2;
	system("cls");
	cout << "欢迎到帮助信息来，请选择你需要的帮助!\n";
	cout << "1: 我的记录怎么了?\n";
	cout << "2: 如何清除记录?\n";
	cout << "3: 什么时候显示最新记录?\n";
	cout << "4: 新显示的是什么?\n";
	cout << "5: 退出!\n";
	cin >>  choice2;
	switch(choice2) //帮助
	{
	case 1:
		cout << "确保有一个FRIENDS的文件!\n";
		cout << "如果它没有消失，把它命名为FRIENDS!\n";
		cout << "按回车键继续!\n";
		pause=getch();
		system("cls");
		goto help;
		break;
	case 2:
		cout << "删除文件数据!\n";
		cout << "按回车键继续!\n";
		pause=getch();
		system("cls");
		goto help;
		break;
	case 3:
		cout << "等到我有时间吧，最近比较忙~~~\n";
		cout << "按回车键继续!\n";
		pause=getch();
		system("cls");
		goto help;
		break;
	case 4:
		cout << "我想添加以下功能:\n";
		cout << "1.复制所有资料\n";
		cout << "2.一次删除多个纪录\n";
		cout << "3.电话本加密功能\n";
		cout << "按回车键继续!\n";
		pause=getch();
		system("cls");
		goto help;
		break;
	default:
		cout << "按回车键退出!\n";
		cout << "按回车键继续!\n";
		break;
	}
} //帮助函数结束

CNode *CList::GetListEnd()
{
	current_ptr = pHead;  
    while(current_ptr!= NULL)
	{                             
    current_ptr = current_ptr->pNext;
	}
	CNode * current_ptr_end=current_ptr;
	return current_ptr_end;
} 

void CList::handle_choice(int choice)
{
	switch(choice) 
	{
	case 1: 
		{
	    char ch;
	    system("cls");
		do{  
			CNode *pnode;
		    pnode=new CNode;
		    char first_name[20];
	    	char last_name[20];
	    	char phone_num[20];
			char dd[20];
			char tt[20];
    		
	    	if(pnode!=NULL)
			
			{
				CTelRecord *ctelrecord=new CTelRecord;
		        cin.ignore(20,'\n');             
				cout << "请输入要添加的名字:\n ";
                cin.get(first_name,20);
                cin.ignore(20,'\n');
      	        cout << "请输入要添加的姓氏:\n ";
                cin.get(last_name,20);
                cin.ignore(20,'\n');
                cout << "请输入要添加的电话号码:\n ";
                cin.get(phone_num,20);
                cin.ignore(20,'\n');
				system("date /t>d:\\translate.txt");
				//fstream trf("d:\\translate.txt",ios::in|ios::nocreate);
				fstream trf("d:\\translate.txt",ios::in);
				trf.getline(dd,20);
				trf.close();
				system("time /t>d:\\translate.txt");
			    //trf.open("d:\\translate.txt",ios::in|ios::nocreate);
			    trf.open("d:\\translate.txt",ios::in);
				trf.getline(tt,20);
				trf.close();
		  	    ctelrecord->SetRecord(last_name,first_name,phone_num,dd,tt);
	   		    pnode->InputData(ctelrecord);
				if(pHead!=NULL)
					Insert(pnode);
				else 
					AddNode(pnode);
				system("date /t");
                system("time /t");
				cout<<"您要继续吗?(y/n)\n";
				cin.get(ch);
				system("cls");
			} 
			else cout<<"you can not add a node!\n";
		}while(ch=='y'||ch=='Y');
		}
        break;
    case 2: 
		system("cls");
		system("date /t");
        system("time /t");
        ShowList();                      
        break; 
	case 3:
		char ch;
		do{
			char lastname[20],firstname[20];
            CTelRecord ctelrecord;
		    cout<<"请输入您要查找的姓氏:\n";
	        cin.getline(lastname,20);
		    cin.ignore(20,'\n');
			cout<<"请输入您要查找的名字:\n";
	    	cin.getline(firstname,20);
	    	cin.ignore(20,'\n');
            ctelrecord.SetRecord(lastname,firstname," "," " ," ");
	    	current_ptr=pHead;
	    	while(current_ptr!=NULL&&current_ptr->GetData()->Compare(ctelrecord)!=0)
			current_ptr=current_ptr->pNext;
	    	if(current_ptr==NULL)
				cout<<"对不起,没有找到您要找的记录.\n";
	    	else
			{
				cout<<"您要找的记录：\n";
			    current_ptr->ShowNode();
			}
			cout<<"您查找的时间是：\n";
			system("date /t");
            system("time /t");
			cout<<"您要继续吗？(y/n)\n";
			cin.get(ch);
		}while(ch=='y'||ch=='Y');
		system("cls");
		break;

	case 4:                            
		{                            
			system("cls");
		    char LName[20];
            cout<<"请输入您要查找的姓:\n";
			cin.get();
            cin.getline(LName,20);
	    	cin.ignore(20,'\n');
			cout<<"您当前查找的时间是：\n";
			system("date /t");
            system("time /t");
	    	CTelRecord ctelrecord(LName,"","","","");
	    	LookUp(ctelrecord);
		}
        break; 
	
    case 5:  
    delete_record();
    break;	 
	case 6:
		{
		   fstream trf;
		   do 
		   {
			  char ch,lastname[20],firstname[20],dd[20],tt[20];
			  int ch1;
		   	CTelRecord ctelrecord;
		    cout<<"请输入您要修改的姓氏:\n";
	        cin.getline(lastname,20);
		    cin.ignore(20,'\n');
			cout<<"请输入您要修改的名字:\n";
	    	cin.getline(firstname,20);
	    	cin.ignore(20,'\n');
            ctelrecord.SetRecord(lastname,firstname," ","","");
	    	current_ptr=pHead;
	    	while(current_ptr!=NULL&&current_ptr->GetData()->Compare(ctelrecord)!=0)
			current_ptr=current_ptr->pNext;
	    	if(current_ptr==NULL)
				cout<<"对不起没有找到您要找的记录!\n";
	    	else
			{
				cout<<"您要找的记录:\n";
			    current_ptr->ShowNode();
			    cout<<"请选择您要修改的项目:\n";
			    cout<<"---1---修改电话号码!\n";
			    cout<<"---2---修改姓名和电话号码!(您需要先把这个记录删除再进行操作)\n";
		    	cin>>ch1;
			    switch(ch1)
				{
			    case 1:
				char number[20];
				cout<<"请您输入新的电话号码:\n";
				cin.get();
				cin.getline(number,20);
				current_ptr->GetData()->Modify(number);
				system("date /t>translate.txt");
				//trf.open("d:\\translate.txt",ios::in|ios::nocreate);     
				trf.open("d:\\translate.txt",ios::in); 
				trf.getline(dd,20);
				trf.close();
				system("time /t>d:\\translate.txt");
				//trf.open("d:\\translate.txt",ios::in|ios::nocreate);
				trf.open("d:\\translate.txt",ios::in);
				trf.getline(tt,20);
				trf.close();
                current_ptr->GetData()->SetDate_Time(dd,tt);
				break;
			case 2:
				if(verify_delete())
				{
					DeleteNode(current_ptr);
				    CNode *pnode;
		            pnode=new CNode;
		            char first_name[20];
	    	        char last_name[20];
	    	        char phone_num[20];
    		
	    	       if(pnode!=NULL)
			
				   {
			    	    CTelRecord *ctelrecord=new CTelRecord;
			            system("cls");
		                cin.ignore(20,'\n');             
			        	cout << "请输入要添加的名字:\n ";
                        cin.get(first_name,20);
                        cin.ignore(20,'\n');
      	                cout << "请输入要添加的姓氏:\n ";
                        cin.get(last_name,20);
                        cin.ignore(20,'\n');
                        cout << "请输入要添加的电话号码:\n ";
                        cin.get(phone_num,20);
                        cin.ignore(20,'\n');
						system("date /t>d:\\translate.txt");
						//trf.open("d:\\translate.txt",ios::in|ios::nocreate);
						trf.open("d:\\translate.txt",ios::in);
				        trf.getline(dd,20);
						trf.close();
				        system("time /t>d:\\translate.txt");
						//trf.open("d:\\translate.txt",ios::in|ios::nocreate);
						trf.open("d:\\translate.txt",ios::in);
				        trf.getline(tt,20);
						trf.close();
		  	            ctelrecord->SetRecord(last_name,first_name,phone_num,dd,tt);
	   		            pnode->InputData(ctelrecord);
			        	if(pHead!=NULL)
						    Insert(pnode);
				        else 
				    	    AddNode(pnode);
				   }
				   cout<<"您修改了一个记录!\n";
				}
				else
					cout<<"您没有修改任何记录!\n";
				break;
			default:
				cout<<"您输入了错误的选择!\n";
				break;
				}
			cout<<"您修改记录的时间是:";cout<<endl;
		    system("date /t");
            system("time /t");
			cout<<"您要继续吗?(y/n)\n";
			cin.get(ch);
			}
		   }while(ch=='y'||ch=='Y');
		   trf.close();
		}
		break;

	case 7:  
    help_me();
	break;
	case 0:  
	cout<<"谢谢您光临电话簿管理系统!再见!!!\n";
	system("date /t");
    system("time /t");
    if(pHead != NULL)   // 如果链表头不为空
	{ 
		write_list_to_file();
		DeleteList(); 
	}
    break;
	default : // 如果您的输入出错
		cout <<"选择错误!\n"; 
    break;
	}
} // 选择处理函数结束

int main()
{
	int choice;
	CNode node;
	CList ClassNumberList;
	cout<<"---------------------------欢迎使用电话簿管理系统-----------------------------!\n";
	cout<<"--------------------------------按回车键继续-----------------------------------\n";
	cin.get(pause);
	cout<<"作者：储兆龙、张驰、王储、王诗然、江京台\n";
    cout<<"学号：20178314037\n";
    cout<<"班级：信息安全1班\n";
    cout<<"--------------------------------按回车键继续-----------------------------------\n";
	cin.get(pause);
	system("cls"); //执行系统命令：cls-清屏
    ClassNumberList.load_list_from_file(); // 从文件读入数据
    current_ptr=ClassNumberList.GetListEnd();
	do
	{  
		system("cls");//   主菜单显示
		system("date /t");
		system("time /t");
		cout<< "--------------------------------1 - 添加记录!-----------------------------------\n";
		cout<< "--------------------------------2 - 显示记录!-----------------------------------\n";
		cout<< "--------------------------------3 - 根据全名查找!-------------------------------\n";
		cout<< "--------------------------------4 - 智能查找!-----------------------------------\n";
		cout<< "--------------------------------5 - 删除记录!-----------------------------------\n";
		cout<< "--------------------------------6 - 修改记录!-----------------------------------\n";
		cout<< "--------------------------------7 - 帮助!---------------------------------------\n";
		cout<< "--------------------------------0 - 退出程序!-----------------------------------\n";
		cout<<"请输入您的选择:\n";
		cin>>choice;
        ClassNumberList.handle_choice(choice);
	}
	while(choice!=0);
	return 0;
}
