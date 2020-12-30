package main;

import javax.swing.table.DefaultTableModel;

public class uETableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public boolean isCellEditable(int row,int column) {
		return false;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class<?> ret=Object.class;
		if ((columnIndex>=0)&&(columnIndex<getColumnCount())&&(getRowCount()>0)){
			for (int i=0;i<getRowCount();i++){
				if (getValueAt(i,columnIndex)!=null){
					ret=getValueAt(i,columnIndex).getClass();
				}
			}
		}
		return ret;
	}
}
