package main;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class uEtableModel extends DefaultTableModel implements TableModelListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3251332202397464133L;
	public int ID;
	public int no;
	
	public uEtableModel(int x) {
		// TODO 自动生成的构造函数存根
		super();
		this.setColumnCount(2);
		no=x;
	}
	
	public int getno() {
		return no;
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO 自动生成的方法存根
		if (column==1) return true;
		return false;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO 自动生成的方法存根
		this.fireTableStructureChanged();
	}
}
