package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OrderedDishesTable extends JTable implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5338549362096334917L;
	
	public DefaultTableModel defaultTableModel=null;
	
	public OrderedDishesTable() {
		// TODO 自动生成的构造函数存根
		defaultTableModel=new uETableModel();
		defaultTableModel.addColumn("ID");
		defaultTableModel.addColumn("菜名");
		defaultTableModel.addColumn("单价");
		defaultTableModel.addColumn("口味");
		
		this.setModel(defaultTableModel);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		if (e.getClickCount()==2) {
			int row=((JTable)e.getSource()).rowAtPoint(e.getPoint());
			defaultTableModel.removeRow(row);
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
