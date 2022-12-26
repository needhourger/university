package main;

import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;


public class OrdersCheckTable extends JTable implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3481105316408010164L;

	public static final int ROWHEIGHT=20;
	public static final int IDWIDTH=80;
	public static final int TABLENOWIDTH=50;
	public static final int ORDERTIMEWIDTH=150;
	public static final int DISHESIDWIDTH=200;
	public static final int MONEYWIDTH=100;
	public static final int DISCOUNTWIDTH=50;
	
	
	public OrdersCheckTable() {
		// TODO �Զ����ɵĹ��캯�����
		this.setAutoscrolls(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		try {
			this.setModel(SQL.GetUncheckedOrders());
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷���ȡ�������");
		}
		
		this.getColumnModel().getColumn(0).setMaxWidth(IDWIDTH);
		this.getColumnModel().getColumn(1).setMaxWidth(TABLENOWIDTH);
		this.getColumnModel().getColumn(2).setMaxWidth(ORDERTIMEWIDTH);
		this.getColumnModel().getColumn(3).setMaxWidth(DISHESIDWIDTH);
		this.getColumnModel().getColumn(4).setMaxWidth(MONEYWIDTH);
		this.getColumnModel().getColumn(5).setMaxWidth(DISCOUNTWIDTH);
		this.setRowHeight(ROWHEIGHT);
		
		this.addMouseListener(this);
	}
	
	public void fire() {
		this.setAutoscrolls(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		try {
			this.setModel(SQL.GetUncheckedOrders());
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷���ȡ�������");
		}
		
		this.getColumnModel().getColumn(0).setMaxWidth(IDWIDTH);
		this.getColumnModel().getColumn(1).setMaxWidth(TABLENOWIDTH);
		this.getColumnModel().getColumn(2).setMaxWidth(ORDERTIMEWIDTH);
		this.getColumnModel().getColumn(3).setMaxWidth(DISHESIDWIDTH);
		this.getColumnModel().getColumn(4).setMaxWidth(MONEYWIDTH);
		this.getColumnModel().getColumn(5).setMaxWidth(DISCOUNTWIDTH);
		this.setRowHeight(ROWHEIGHT);
		
		this.addMouseListener(this);
	}

	


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO �Զ����ɵķ������
		if (e.getClickCount()==2) {
			int row=((JTable)e.getSource()).rowAtPoint(e.getPoint());
			int ID=Integer.parseInt(this.getValueAt(row, 0).toString());
			int tableNO=Integer.parseInt(this.getValueAt(row, 1).toString());
			String ordertime=this.getValueAt(row, 2).toString();
			String dishesID=this.getValueAt(row, 3).toString();
			float money=Float.parseFloat(this.getValueAt(row, 4).toString());
			float discount=1;
			if (this.getValueAt(row, 5)!=null) discount=Float.parseFloat(this.getValueAt(row, 5).toString());
			
			String confimString="���ţ�"+tableNO+"\n"
					+ "�µ�ʱ�䣺"+ordertime+"\n"
					+ "��Ʒ��ţ�"+dishesID+"\n"
					+ "�ܽ��"+money+"\n"
					+ "�������ۿۣ�"+discount+"\n"
					+ "�����"+money*discount+"\n"
					+ "ȷ�Ͻ��㣿";
			
			int ret=JOptionPane.showConfirmDialog(this,confimString);
			if (ret==JOptionPane.YES_OPTION) {
				try {
					if  (SQL.check(ID)) {
						Windows.tables.get(tableNO-1).free();
						this.fire();
					}
					else JOptionPane.showMessageDialog(this, "�д������������³��Խ���");
				} catch (HeadlessException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
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









