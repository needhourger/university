package main;

import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;


public class OrdersTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2091830708373802731L;
	public static final int ROWHEIGHT=20;
	public static final int IDWIDTH=80;
	public static final int TABLENOWIDTH=50;
	public static final int ORDERTIMEWIDTH=150;
	public static final int DISHESIDWIDTH=200;
	public static final int MONEYWIDTH=100;
	public static final int DISCOUNTWIDTH=50;
	public static final int ISCHECKWIDTH=100;
	public static final int CHECKTIMEWIDTH=150;
	
	
	public OrdersTable() {
		// TODO 自动生成的构造函数存根
		this.setAutoscrolls(true);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		try {
			this.setModel(SQL.GetOrdersTable());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("无法获取表格数据");
		}
		
		this.getColumnModel().getColumn(0).setMaxWidth(IDWIDTH);
		this.getColumnModel().getColumn(1).setMaxWidth(TABLENOWIDTH);
		this.getColumnModel().getColumn(2).setMaxWidth(ORDERTIMEWIDTH);
		this.getColumnModel().getColumn(3).setMaxWidth(DISHESIDWIDTH);
		this.getColumnModel().getColumn(4).setMaxWidth(MONEYWIDTH);
		this.getColumnModel().getColumn(5).setMaxWidth(DISCOUNTWIDTH);
		this.getColumnModel().getColumn(6).setMaxWidth(ISCHECKWIDTH);
		this.getColumnModel().getColumn(7).setMaxWidth(CHECKTIMEWIDTH);
		this.setRowHeight(ROWHEIGHT);
	}
	
	
	public void seach(String ID) {
		this.setAutoscrolls(true);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		try {
			this.setModel(SQL.SearchOrdersTable(ID));
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("无法获取表格数据");
		}
		
		this.getColumnModel().getColumn(0).setMaxWidth(IDWIDTH);
		this.getColumnModel().getColumn(1).setMaxWidth(TABLENOWIDTH);
		this.getColumnModel().getColumn(2).setMaxWidth(ORDERTIMEWIDTH);
		this.getColumnModel().getColumn(3).setMaxWidth(DISHESIDWIDTH);
		this.getColumnModel().getColumn(4).setMaxWidth(MONEYWIDTH);
		this.getColumnModel().getColumn(5).setMaxWidth(DISCOUNTWIDTH);
		this.getColumnModel().getColumn(6).setMaxWidth(ISCHECKWIDTH);
		this.getColumnModel().getColumn(7).setMaxWidth(CHECKTIMEWIDTH);
		this.setRowHeight(ROWHEIGHT);	
	}
	
	public void fire() {
		this.setAutoscrolls(true);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		try {
			this.setModel(SQL.GetOrdersTable());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("无法获取表格数据");
		}
		
		this.getColumnModel().getColumn(0).setMaxWidth(IDWIDTH);
		this.getColumnModel().getColumn(1).setMaxWidth(TABLENOWIDTH);
		this.getColumnModel().getColumn(2).setMaxWidth(ORDERTIMEWIDTH);
		this.getColumnModel().getColumn(3).setMaxWidth(DISHESIDWIDTH);
		this.getColumnModel().getColumn(4).setMaxWidth(MONEYWIDTH);
		this.getColumnModel().getColumn(5).setMaxWidth(DISCOUNTWIDTH);
		this.getColumnModel().getColumn(6).setMaxWidth(ISCHECKWIDTH);
		this.getColumnModel().getColumn(7).setMaxWidth(CHECKTIMEWIDTH);
		this.setRowHeight(ROWHEIGHT);
	}
}
