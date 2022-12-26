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
		// TODO �Զ����ɵĹ��캯�����
		defaultTableModel=new uETableModel();
		defaultTableModel.addColumn("ID");
		defaultTableModel.addColumn("����");
		defaultTableModel.addColumn("����");
		defaultTableModel.addColumn("��ζ");
		
		this.setModel(defaultTableModel);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO �Զ����ɵķ������
		if (e.getClickCount()==2) {
			int row=((JTable)e.getSource()).rowAtPoint(e.getPoint());
			defaultTableModel.removeRow(row);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO �Զ����ɵķ������
		return;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������
		return;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������
		return;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO �Զ����ɵķ������
		return;
	}

	
}
