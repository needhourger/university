package main;


import java.awt.Component;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;


public class DishesTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4459731509792576561L;
	
	public static final int DISHESWIDTH=200;
	public static final int ROWHEIGHT=100;
	public static final int IDWIDTH=50;
	public static final int NAMEWIDTH=150;
	public static final int PRICEWIDTH=80;
	public static final int TASTWIDTH=200;
	
	public DishesTable() {
		// TODO 自动生成的构造函数存根
		this.setAutoscrolls(true);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		try {
			this.setModel(SQL.GetDishesTable());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("无法获取表格数据");
		}
		
		this.getColumnModel().getColumn(1).setCellRenderer(new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
					int arg5) {
				// TODO 自动生成的方法存根
				JLabel label=(JLabel) arg1;
				label.setSize(DISHESWIDTH, ROWHEIGHT);
				return label;
			}
		});
		this.getColumnModel().getColumn(0).setMaxWidth(IDWIDTH);
		this.getColumnModel().getColumn(1).setMaxWidth(DISHESWIDTH);
		this.getColumnModel().getColumn(2).setMaxWidth(NAMEWIDTH);
		this.getColumnModel().getColumn(3).setMaxWidth(PRICEWIDTH);
		this.getColumnModel().getColumn(4).setMaxWidth(TASTWIDTH);
		this.setRowHeight(ROWHEIGHT);
	}
	
	public void fire() {
		this.setAutoscrolls(true);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		try {
			this.setModel(SQL.GetDishesTable());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("无法获取表格数据");
		}
		
		this.getColumnModel().getColumn(1).setCellRenderer(new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
					int arg5) {
				// TODO 自动生成的方法存根
				JLabel label=(JLabel) arg1;
				label.setSize(DISHESWIDTH, ROWHEIGHT);
				return label;
			}
		});
		this.getColumnModel().getColumn(0).setMaxWidth(IDWIDTH);
		this.getColumnModel().getColumn(1).setMaxWidth(DISHESWIDTH);
		this.getColumnModel().getColumn(2).setMaxWidth(NAMEWIDTH);
		this.getColumnModel().getColumn(3).setMaxWidth(PRICEWIDTH);
		this.getColumnModel().getColumn(4).setMaxWidth(TASTWIDTH);
		this.setRowHeight(ROWHEIGHT);
	}
}
