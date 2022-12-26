package main;

import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class VIPusertable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1857689063041189927L;
	
	public static final int ROWHEIGHT=20;
	public static final int IDWIDTH=50;
//	public static final int NAMEWIDTH=200;
//	public static final int DISCOUNTWIDTH=100;
	
	public VIPusertable() {
		// TODO �Զ����ɵĹ��캯�����
		this.setAutoscrolls(true);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		try {
			this.setModel(SQL.GetVIPuserTable());
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		this.getColumnModel().getColumn(0).setMaxWidth(IDWIDTH);
//		this.getColumnModel().getColumn(1).setMaxWidth(NAMEWIDTH);
//		this.getColumnModel().getColumn(2).setMaxWidth(DISCOUNTWIDTH);
		
	}
	
	public void fire() {
		// TODO �Զ����ɵĹ��캯�����
		this.setAutoscrolls(true);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		try {
			this.setModel(SQL.GetVIPuserTable());
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷���ȡ�������");
		}
		
		this.getColumnModel().getColumn(0).setMaxWidth(IDWIDTH);
//		this.getColumnModel().getColumn(1).setMaxWidth(NAMEWIDTH);
//		this.getColumnModel().getColumn(2).setMaxWidth(DISCOUNTWIDTH);
		this.setRowHeight(ROWHEIGHT);
	}

}
