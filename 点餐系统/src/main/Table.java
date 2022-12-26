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
		// TODO �Զ����ɵĹ��캯�����
		tableNO=no;
		tableSize=Size;
		this.setSize(500,500);
		this.setFont(new Font("Dialog",1,20));
		this.setText("<html>��"+tableNO+"��<br>"+peopleNum+"/"+tableSize+"<html>");
		this.addActionListener(this);
	}
	
	public void setPeopleNum(int num) {
		peopleNum=num;
		this.setText("<html>��"+tableNO+"��<br>"+peopleNum+"/"+tableSize+"<html>");
	}
	
	
	public void free() {
		peopleNum=0;
		orderID=0;
		this.setText("<html>��"+tableNO+"��<br>"+peopleNum+"/"+tableSize+"<html>");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		orderID=Main.windows.orderDishes(tableNO);
		
	}
	

}
