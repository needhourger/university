package main;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class OrderDishesTable extends JTable implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 244363556175105998L;
	
	public static final int DISHESWIDTH=200;
	public static final int ROWHEIGHT=100;
	public static final int IDWIDTH=30;
	public static final int NAMEWIDTH=100;
	public static final int PRICEWIDTH=50;
	public static final int TASTWIDTH=50;
	
	public OrderedDishesTable orderedDishesTable=null;
	
	
	public OrderDishesTable(OrderedDishesTable t) {
		// TODO 自动生成的构造函数存根
		orderedDishesTable=t;
		
		this.setAutoscrolls(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		
		this.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		if (e.getClickCount()==2) {
			int row=((JTable)e.getSource()).rowAtPoint(e.getPoint());
			Vector<Object>rowData=new Vector<Object>();
			for (int i=0;i<5;i++) {
				if (i==1) continue;
				rowData.add(this.getValueAt(row, i));
			}
			DefaultTableModel dtm=(DefaultTableModel) orderedDishesTable.getModel();
			dtm.addRow(rowData);
			orderedDishesTable.setModel(dtm);
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		return;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		return;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		return;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		return;
	}
	
	
}
