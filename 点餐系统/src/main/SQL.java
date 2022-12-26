package main;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;



public class SQL {
	static final String DRIVER="com.mysql.cj.jdbc.Driver";
	static final String URL="jdbc:mysql://localhost:3306/ordering?&serverTimezone=UTC";
	static final String USER="orderAdmin";
	static final String PASS="123456";
	
	private static Connection conn=null;
	
	public SQL() {
		// TODO �Զ����ɵĹ��캯�����
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷���ȡ���ݿ�����");
		}
		
		try {
			conn=DriverManager.getConnection(URL,USER,PASS);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷��������ݿ�");
			return;
		}
		System.out.println("�������ݿ�ɹ�");
	}
	
	private static void init() {
		// TODO �Զ����ɵķ������
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷���ȡ���ݿ�����");
		}
		
		try {
			conn=DriverManager.getConnection(URL,USER,PASS);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷��������ݿ�");
		}
		System.out.println("�������ݿ�ɹ�");
	}

	private static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷��ر����ݿ�����");
		}
	}
	
	public static Boolean login(String username,String password) throws SQLException {
		init();
		String sql="select count(*) from user where username=? and password=?";
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
		} catch (SQLException e) {
			
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷�����pstmt");
			close();
			return false;
		}
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		
		ResultSet res=pstmt.executeQuery();
		while (res.next()) {
			if (res.getInt(1)==1) {
				close();
				return true;
			}
			else {
				close();
				return false;
			}
		}
		pstmt.close();
		close();
		return false;
	}
	
	
	public static boolean check(int ID) throws SQLException {
		init();
		String sql="update orderings set ischeck=1,checktime=localtime() where id=?";
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷�����pstmt");
			close();
			return false;
		}
		
		pstmt.setInt(1, ID);
		if (pstmt.executeUpdate()==1) {
			System.out.println("����ɹ�");
			return true;
		}
		System.out.print("����ʧ��");
		pstmt.close();
		close();
		return false;
	}
	
	public static float GetHisTurnover() throws SQLException {
		float ret=0;
		
		init();
		String sql="select sum(money*discount) from orderings where ischeck=1";
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷�����pstmt");
			close();
			return ret;
		}
		
		ResultSet res=pstmt.executeQuery();
		while (res.next()) {
			ret=res.getFloat("sum(money*discount)");
		}
		pstmt.close();
		close();
		return ret;
	}
	
	public static float Getturnover() throws SQLException {
		float ret=0;
		
		init();
		String sql="select sum(money*discount) from orderings where ischeck=1 and date_format(checktime,'%Y-%m-%d')=date_format(now(),'%Y-%m-%d')";
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷�����pstmt");
			close();
			return ret;
		}
		
		
		ResultSet res=pstmt.executeQuery();
		while (res.next()) {
			ret=res.getFloat("sum(money*discount)");
		}
		pstmt.close();
		close();
		return ret;
		
	}
	
	public static DefaultTableModel GetDishesTable() throws SQLException {
		init();
		DefaultTableModel dtm=new uETableModel();
		dtm.addColumn("ID");
		dtm.addColumn("ͼƬ");
		dtm.addColumn("����");
		dtm.addColumn("����");
		dtm.addColumn("��ζ");
		
		String sql="select ID,picture,name,price,taste from dishes";
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("�޷�����pstmt");
			close();
			return dtm;
		}
		ResultSet res=pstmt.executeQuery();
		while (res.next()) {
			Vector<Object> row=new Vector<Object>();
			row.add(res.getString("ID"));
			Blob blob=res.getBlob("picture");
			
			ImageIcon imageIcon=new ImageIcon(Blob2Bytes(blob));
			imageIcon.setImage(imageIcon.getImage().getScaledInstance(DishesTable.DISHESWIDTH, DishesTable.ROWHEIGHT, Image.SCALE_DEFAULT));
			JLabel label=new JLabel(imageIcon);
			row.add(label);
			row.add(res.getString("name"));
			row.add(res.getString("price"));
			row.add(res.getString("taste"));
			dtm.addRow(row);
		}
		pstmt.close();
		close();
		return dtm;
	}
	
	public static DefaultTableModel GetOrdersTable() throws SQLException {
		init();
		DefaultTableModel dtm=new uETableModel();
		dtm.addColumn("ID");
		dtm.addColumn("����");
		dtm.addColumn("�µ�ʱ��");
		dtm.addColumn("��Ʒ���");
		dtm.addColumn("�ܽ��");
		dtm.addColumn("�ۿ�");
		dtm.addColumn("�ѽ���");
		dtm.addColumn("����ʱ��");
		
		String sql="select ID,tableNO,ordertime,dishesID,money,discount,ischeck,checktime from orderings";
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("�޷�����pstmt");
			close();
			return dtm;
		}
		ResultSet res=pstmt.executeQuery();
		while (res.next()) {
			Vector<Object> row=new Vector<Object>();
			row.add(res.getString("ID"));
			row.add(res.getString("tableNO"));
			row.add(res.getString("ordertime"));
			row.add(res.getString("dishesID"));
			row.add(res.getString("money"));
			row.add(res.getString("discount"));
			String tempString=res.getString("ischeck");
			if (tempString.equals("1")) row.add("��");
			else row.add("��");
			row.add(res.getString("checktime"));
			dtm.addRow(row);
		}
		pstmt.close();
		close();
		return dtm;
	}
	
	
	
	public static DefaultTableModel SearchOrdersTable(String ID) throws SQLException {
		init();
		DefaultTableModel dtm=new uETableModel();
		dtm.addColumn("ID");
		dtm.addColumn("����");
		dtm.addColumn("�µ�ʱ��");
		dtm.addColumn("��Ʒ���");
		dtm.addColumn("�ܽ��");
		dtm.addColumn("�ۿ�");
		dtm.addColumn("�ѽ���");
		dtm.addColumn("����ʱ��");
		
		String sql="select ID,tableNO,ordertime,dishesID,money,discount,ischeck,checktime from orderings where id=?";
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷�����pstmt");
			close();
			return dtm;
		}
		pstmt.setString(1, ID);
		ResultSet res=pstmt.executeQuery();
		while (res.next()) {
			Vector<Object> row=new Vector<Object>();
			row.add(res.getString("ID"));
			row.add(res.getString("tableNO"));
			row.add(res.getString("ordertime"));
			row.add(res.getString("dishesID"));
			row.add(res.getString("money"));
			row.add(res.getString("discount"));
			String tempString=res.getString("ischeck");
			if (tempString.equals("1")) row.add("��");
			else row.add("��");
			row.add(res.getString("checktime"));
			dtm.addRow(row);
		}
		pstmt.close();
		close();
		return dtm;
	}
	
	public static DefaultTableModel GetVIPuserTable() throws SQLException {
		init();
		DefaultTableModel dtm=new uETableModel();
		dtm.addColumn("ID");
		dtm.addColumn("����");
		dtm.addColumn("�ۿ�");
		
		String sql="select ID,name,discount from vipcustomers";
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷�����pstmt");
			close();
			return dtm;
		}
		ResultSet res=pstmt.executeQuery();
		while (res.next()) {
			Vector<Object> row=new Vector<Object>();
			row.add(res.getString("ID"));
			row.add(res.getString("name"));
			row.add(res.getString("discount"));
			dtm.addRow(row);
		}
		pstmt.close();
		close();
		return dtm;
	}
	
	public static DefaultTableModel GetUncheckedOrders() throws SQLException {
		init();
		DefaultTableModel dtm=new uETableModel();
		
		dtm.addColumn("ID");
		dtm.addColumn("����");
		dtm.addColumn("�µ�ʱ��");
		dtm.addColumn("��Ʒ���");
		dtm.addColumn("�ܽ��");
		dtm.addColumn("�ۿ�");
		
		String sql="select ID,tableNO,ordertime,dishesID,money,discount from orderings where ischeck=0";
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷�����pstmt");
			close();
			return dtm;
		}
		ResultSet  res=pstmt.executeQuery();
		while (res.next()) {
			Vector<Object> row=new Vector<Object>();
			row.add(res.getString("ID"));
			row.add(res.getString("tableNO"));
			row.add(res.getString("ordertime"));
			row.add(res.getString("dishesID"));
			row.add(res.getString("money"));
			row.add(res.getString("discount"));
			dtm.addRow(row);
		}
		pstmt.close();
		close();
		return dtm;
		
	}
	
	public static void insert2Dishes(File picture,String name,String price,String taste) throws SQLException, FileNotFoundException {
		init();
		String sql="insert into dishes (picture,name,price,taste) values (?,?,?,?)";
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷�����pstmt");
			close();
			return;
		}
		InputStream picStream=new FileInputStream(picture);
		pstmt.setBinaryStream(1,picStream);
		pstmt.setString(2, name);
		pstmt.setFloat(3,Float.parseFloat(price));
		pstmt.setString(4,taste);
		
		try {
			pstmt.executeUpdate();
			System.out.println("�ɹ�������Ϣ");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("����ʧ��");
		}
		pstmt.close();
		close();
	}
	
	
	public static void insert2VIPcustomuers(String name,String dicount) throws NumberFormatException, SQLException {
		init();
		String sql="insert into vipcustomers (name,discount) values (?,?)";
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷�����pstmt");
			close();
			return;
		}
		
		pstmt.setString(1, name);
		pstmt.setFloat(2, Float.parseFloat(dicount));
		
		try {
			pstmt.executeUpdate();
			System.out.println("�ɹ�������Ϣ");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("����ʧ��");
		}
		pstmt.close();
		close();
	}
	
	
	public static void insert2orderings(int tableNO,String dishesID,float money,String VIPuser) throws SQLException {
		init();
		String sql="insert into orderings (tableNO,ordertime,dishesID,money,discount) "
				+ "values (?,localtime(),?,?,ifnull((select discount from vipcustomers where name=?),1))";
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("�޷�����pstmt");
			close();
			return;
		}
		
		pstmt.setInt(1, tableNO);
		pstmt.setString(2, dishesID);
		pstmt.setFloat(3, money);
		pstmt.setString(4, VIPuser);
		
		try {
			pstmt.executeUpdate();
			System.out.println("��������ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("�������ʧ��");
		}
		pstmt.close();
		close();
	}
	
	
	
	public static void DishesDelete(int[] a) throws SQLException {
		init();
		String sql="delete from dishes where id=?";
		PreparedStatement pstmt=null;
		pstmt=conn.prepareStatement(sql);

		for (int i=0;i<a.length;i++) {
			pstmt.setInt(1, a[i]);
			pstmt.executeUpdate();
			System.out.println("�ɹ�ɾ��id="+a[i]);
		}
		pstmt.close();
		close();
	}
	
	public static void VIPuserDelete(int[] a) throws SQLException {
		init();
		String sql="delete from vipcustomers where id=?";
		PreparedStatement pstmt=null;
		pstmt=conn.prepareStatement(sql);
		
		for (int i=0;i<a.length;i++) {
			pstmt.setInt(1, a[i]);
			pstmt.executeUpdate();
			System.out.println("�ɹ�ɾ��id="+a[i]);
		}
		pstmt.close();
		close();
	}
	
	private static byte[] Blob2Bytes(Blob blob) {
		BufferedInputStream is = null;
        byte[] bytes = null;
        try {
            is = new BufferedInputStream(blob.getBinaryStream());
            bytes = new byte[(int) blob.length()];
            int len = bytes.length;
            int offset = 0;
            int read = 0;
 
            while (offset < len
                    && (read = is.read(bytes, offset, len - offset)) >= 0) {
                offset += read;
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
	}
	

}
