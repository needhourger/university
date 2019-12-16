package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class Table extends JButton implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4058929867337843597L;
	public int tableNO=0;
	public int tableSize=2;
	public int peopleNum=0;
	public int orderID=0;
	
	public Table(int no,int Size) {
		// TODO 自动生成的构造函数存根
		tableNO=no;
		tableSize=Size;
		this.setSize(500,500);
		this.setFont(new Font("Dialog",1,20));
		this.setText("<html>第"+tableNO+"桌<br>"+peopleNum+"/"+tableSize+"<html>");
		this.addActionListener(this);
	}
	
	public void setPeopleNum(int num) {
		peopleNum=num;
		this.setText("<html>第"+tableNO+"桌<br>"+peopleNum+"/"+tableSize+"<html>");
	}
	
	
	public void free() {
		peopleNum=0;
		orderID=0;
		this.setText("<html>第"+tableNO+"桌<br>"+peopleNum+"/"+tableSize+"<html>");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		orderID=Main.windows.orderDishes(tableNO);
		
	}
	

}
