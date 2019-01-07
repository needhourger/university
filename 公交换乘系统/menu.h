#ifndef MENU_H
#define MENU_H

using namespace std;

class menu
{
	public:
		menu();
		~menu();
		//静态方法打印菜单界面 
		static void print(vector<BusLine> lines); 
		//静态方法接收用户选择 
		static void getchoice(vector<BusLine> lines);
	private:
		//存放线路信息 
		vector<BusLine> lines;
};

#endif
