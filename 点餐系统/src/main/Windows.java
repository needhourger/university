package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;



public class Windows{
	/**
	 * v1.0
	 */
	public static boolean islogin=false;
	
	private static ImageIcon welcomeIcon=new ImageIcon("libs/welcome.png");
	private static File pictureFile=null;
	
	public static int TABLENUM=20;
	public static Vector<Table>tables=null;
	

	public Windows(int table_num) {
		// TODO �Զ����ɵĹ��캯�����
		islogin=false;
		TABLENUM=table_num;
		tables=new Vector<Table>();
		for (int i=1;i<=TABLENUM;i++) {
			Table table=new Table(i, 4);
			tables.add(table);
		}
		
	}
	
	public void login() {
		JFrame frame=new JFrame("���ϵͳ��½");
		frame.setSize(350,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel panel=new JPanel();
		frame.add(panel);
		panel.setLayout(null);
		
		JLabel userlabel=new JLabel("�û�����");
		userlabel.setBounds(10, 20, 80, 25);
		panel.add(userlabel);
		
		JTextField userText=new JTextField(20);
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);
		
		JLabel passwordLabel=new JLabel("����:");
		passwordLabel.setBounds(10, 50, 80, 25);
		panel.add(passwordLabel);
		
		JPasswordField passwordTextField=new JPasswordField(20);
		passwordTextField.setBounds(100, 50, 165, 25);
		panel.add(passwordTextField);
		
		JButton loginButton=new JButton("��½");
		loginButton.setBounds(10, 80, 80, 25);
		loginButton.setLocation(100,100);
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String userString=userText.getText();
				String passworString=new String(passwordTextField.getPassword());
				try {
					if (SQL.login(userString, passworString)) {
						System.out.println("��½�ɹ�");
						islogin=true;
						frame.setVisible(false);
						menu();
					}else {
						System.out.println("��½ʧ��");
						JOptionPane.showMessageDialog(frame, "�û��������������");
						islogin=false;
					}
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
		});;
		panel.add(loginButton);
		
		frame.setVisible(true);
		
	}
	
	
	public void picFileChoose() {
		JFrame frame=new JFrame("��ѡ���ļ�");
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JFileChooser picFileChooser=new JFileChooser(pictureFile);
		picFileChooser.addChoosableFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				// TODO �Զ����ɵķ������
				return "*.jpg,*.png";
			}
			
			@Override
			public boolean accept(File f) {
				// TODO �Զ����ɵķ������
				String filename=f.getName().toLowerCase();
				if (filename.endsWith(".jpg") || filename.endsWith(".png")) return true;
				return false;
			}
		});
		picFileChooser.setBounds(10, 10, 400, 300);
		picFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		picFileChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				pictureFile=picFileChooser.getSelectedFile();
				
			}
		});
		frame.add(picFileChooser);
		
		frame.setVisible(true);
	}
	
	public void dishesManager() {
		JFrame frame=new JFrame("��Ʒ����ϵͳ");
		frame.setSize(700,500);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel panel=new JPanel();
		frame.add(panel);
		panel.setLayout(null);

		DishesTable dishesTable=new DishesTable();
		dishesTable.setBounds(10, 10, 665, 300);
		JScrollPane scrollPane=new JScrollPane(dishesTable);
		scrollPane.setSize(665,300);
		scrollPane.setLocation(10, 10);
		panel.add(scrollPane);
		
		JLabel piclaJLabel=new JLabel("ͼƬ��");
		piclaJLabel.setBounds(10, 310, 50, 20);
		panel.add(piclaJLabel);
		
		JFileChooser picFileChooser=new JFileChooser();
		picFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		picFileChooser.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				// TODO �Զ����ɵķ������
				return "*.jpg;*.png";
			}
			
			@Override
			public boolean accept(File f) {
				// TODO �Զ����ɵķ������
				String filename=f.getName().toLowerCase();
				if (filename.endsWith(".jpg") || filename.endsWith(".png")) return true;
				return false;
			}
		});
		
		JTextField picTextField=new JTextField();
		picTextField.setBounds(10,330,150,20);
		picTextField.setColumns(20);
		picTextField.setEditable(false);
		panel.add(picTextField);
		
		JButton fileChoseButton=new JButton("ѡ��ͼ��");
		fileChoseButton.setBounds(10, 350, 150, 20);
		fileChoseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				int i=picFileChooser.showOpenDialog(frame.getContentPane());
				if (i==JFileChooser.APPROVE_OPTION) {
					pictureFile=picFileChooser.getSelectedFile();
					picTextField.setText(pictureFile.getName());
				}
			}
		});
		panel.add(fileChoseButton);
		
		JLabel dishesNameLabel=new JLabel("������");
		dishesNameLabel.setBounds(170, 310, 50, 20);
		panel.add(dishesNameLabel);
		
		JTextField dishesNameField=new JTextField();
		dishesNameField.setBounds(170,330,150,20);
		dishesNameField.setColumns(20);
		panel.add(dishesNameField);
		
		JLabel priceLabel=new JLabel("���ۣ�");
		priceLabel.setBounds(330,310,50,20);
		panel.add(priceLabel);
		
		JTextField priceTextField=new JTextField();
		priceTextField.setBounds(330,330,100,20);
		priceTextField.setColumns(10);
		panel.add(priceTextField);
		
		
		JLabel tasteLabel=new JLabel("��ζ��");
		tasteLabel.setBounds(440,310,50,20);
		panel.add(tasteLabel);
		
		JTextField tasteTextField=new JTextField();
		tasteTextField.setBounds(440,330,150,20);
		tasteTextField.setColumns(20);
		panel.add(tasteTextField);
		
		
		JButton addButton=new JButton("������¼");
		addButton.setBounds(300, 380, 150, 50);
		addButton.setFont(new Font("Dialog", 1, 20));
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String dishNameString=dishesNameField.getText();
				String priceString=priceTextField.getText();
				String tasteString=tasteTextField.getText();
				if (
						dishNameString==null || priceString==null || tasteString==null || pictureFile==null ||
						dishNameString.isEmpty() || priceString.isEmpty() || tasteString.isEmpty()
					) {
					JOptionPane.showMessageDialog(frame, "����д������Ϣ");
					return;
				}
				try {
					SQL.insert2Dishes(pictureFile,dishNameString,priceString,tasteString);
					dishesTable.fire();
				} catch (SQLException | FileNotFoundException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
					System.out.println("�����Ʒʧ��");
				}
			}
		});
		panel.add(addButton);
		
		JButton deleteButton=new JButton("ɾ��ѡ��");
		deleteButton.setBounds(460,380,150,50);
		deleteButton.setFont(new Font("Dialog", 1, 20));
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				int[] selectRow=dishesTable.getSelectedRows();
				for (int i=0;i<selectRow.length;i++) {
					String t=dishesTable.getValueAt(selectRow[i],0).toString();
					selectRow[i]=Integer.parseInt(t);	
				}
				
				try {
					SQL.DishesDelete(selectRow);
					dishesTable.fire();
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
					System.out.println("ɾ�����ִ���");
				}
			}
		});
		panel.add(deleteButton);
		
		frame.setVisible(true);
	}

	
	public void VIPmanager() {
		JFrame frame=new JFrame("VIP�û�����");
		frame.setSize(500,400);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel panel=new JPanel();
		frame.add(panel);
		panel.setLayout(null);
		
		VIPusertable vipusertable=new VIPusertable();
		vipusertable.setBounds(10, 10, 465, 200);
		JScrollPane scrollPane=new JScrollPane(vipusertable);
		scrollPane.setSize(465,200);
		scrollPane.setLocation(10, 10);
		panel.add(scrollPane);
		
		
		JLabel nameJLabel=new JLabel("����:");
		nameJLabel.setBounds(10,210,80,20);
		panel.add(nameJLabel);
		
		JTextField nameField=new JTextField();
		nameField.setColumns(20);
		nameField.setBounds(10,230,150,20);
		panel.add(nameField);
		
		JLabel discountLabel=new JLabel("�ۿ�:");
		discountLabel.setBounds(200,210,80,20);
		panel.add(discountLabel);
		
		JTextField discountField=new JTextField();
		discountField.setColumns(20);
		discountField.setText("0.75");
		discountField.setBounds(200,230,150,20);
		panel.add(discountField);
		
		
		JButton deleteButton=new JButton("ɾ��ѡ��");
		deleteButton.setFont(new Font("Dialog", 1, 20));
		deleteButton.setBounds(150,280,120,50);
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				int[] selectRow=vipusertable.getSelectedRows();
				for (int i=0;i<selectRow.length;i++) {
					String t=vipusertable.getValueAt(selectRow[i], 0).toString();
					selectRow[i]=Integer.parseInt(t);
				}
				
				try {
					SQL.VIPuserDelete(selectRow);
					vipusertable.fire();
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
					System.out.println("ɾ�����ִ���");
				}
				
			}
		});
		panel.add(deleteButton);
		
		JButton addButton=new JButton("��Ӽ�¼");
		addButton.setFont(new Font("Dialog",1,20));
		addButton.setBounds(300, 280, 120, 50);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String nameSting=nameField.getText();
				String discString=discountField.getText();
				if (nameSting==null || nameSting.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "����д����");
					return;
				}
				
				try {
					SQL.insert2VIPcustomuers(nameSting,discString);
					vipusertable.fire();
				} catch (NumberFormatException | SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
					System.out.println("��ӳ��ִ���");
				}
				
			}
		});
		panel.add(addButton);
		
		frame.setVisible(true);
	}
	
	
	public void orderManager() {
		JFrame frame=new JFrame("�����������");
		frame.setSize(800,430);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel panel=new JPanel();
		panel.setLayout(null);
		frame.add(panel);
		
		OrdersTable ordersTable=new OrdersTable();
		ordersTable.setBounds(10, 10, 765, 300);
		JScrollPane scrollPane=new JScrollPane(ordersTable);
		scrollPane.setSize(765,300);
		scrollPane.setLocation(10, 10);
		panel.add(scrollPane);
		
		JLabel searchLabel=new JLabel("������ţ�");
		searchLabel.setBounds(10,320,100,20);
		panel.add(searchLabel);
		
		JTextField searchField=new JTextField();
		searchField.setColumns(50);
		searchField.setBounds(80,320,200,20);
		panel.add(searchField);
		
		JButton searchButton=new JButton("����");
		searchButton.setBounds(300,320,80,20);
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String IDString=searchField.getText();
				if (IDString.isEmpty()) ordersTable.fire();
				else ordersTable.seach(IDString);
			}
		});
		panel.add(searchButton);
		
		JLabel turnoverLabel=new JLabel();
		try {
			turnoverLabel.setText("����Ӫҵ�"+SQL.Getturnover());
		} catch (SQLException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
			System.out.println("�޷���ȡӪҵ��");
			turnoverLabel.setText("����Ӫҵ���ȡʧ��");
		}
		turnoverLabel.setBounds(10,350,300,20);
		panel.add(turnoverLabel);
		
		JLabel hisTurnoverLabel=new JLabel();
		try {
			hisTurnoverLabel.setText("��ʷӪҵ�ܶ"+SQL.GetHisTurnover());
		} catch (SQLException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
			hisTurnoverLabel.setText("��ʷӪҵ�ܶ��ȡʧ��");
		}
		hisTurnoverLabel.setBounds(320,350,300,20);
		panel.add(hisTurnoverLabel);
		
		frame.setVisible(true);
		
		
	}
	
	public void check() {
		JFrame frame=new JFrame("����ϵͳ");
		frame.setSize(640,400);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel panel=new JPanel();
		panel.setLayout(null);
		frame.add(panel);
		
		OrdersCheckTable table=new OrdersCheckTable();
		table.setSize(600,300);
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setSize(600,300);
		scrollPane.setLocation(10, 10);
		panel.add(scrollPane);
		
		frame.setVisible(true);
	}
	
	
	public void order() {
		JFrame frame=new JFrame("���ϵͳ");
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel panel=new JPanel();
		panel.setBackground(new Color(100,155,100));
		frame.add(panel);
		frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.PAGE_AXIS));
//		panel.setLayout(new BoxLayout(parent, BoxLayout.X_AXIS));
		
		for (int i=0;i<TABLENUM;i++) {
			panel.add(tables.get(i));
		}	
		frame.setVisible(true);
	}
	
	public int orderDishes(int tableNO) {
		JFrame frame=new JFrame("���ϵͳ");
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel panel=new JPanel();
		panel.setLayout(null);
		frame.add(panel);
		
		JLabel label1=new JLabel("�˵�:");
		label1.setBounds(10,10,100,20);
		panel.add(label1);
		
		JLabel label2=new JLabel("�ѵ��Ʒ:");
		label2.setBounds(410,10,100,20);
		panel.add(label2);
		
		OrderedDishesTable orderedtable=new OrderedDishesTable();
		orderedtable.setSize(350, 500);
		JScrollPane scrollPane2=new JScrollPane(orderedtable);
		scrollPane2.setBounds(410,30,370,450);
		panel.add(scrollPane2);
		
		OrderDishesTable dishesTable=new OrderDishesTable(orderedtable);
		dishesTable.setSize(350,500);
		JScrollPane scrollPane=new JScrollPane(dishesTable);
		scrollPane.setBounds(10, 30, 370, 450);
		panel.add(scrollPane);
		
		JButton orderButton=new JButton("ȷ���µ�");
		orderButton.setBounds(500, 500, 200, 40);
		orderButton.setFont(new Font("Dialog", 1, 20));
		orderButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				int rowNum=orderedtable.getRowCount();
				float money=0;
				String dishesID=new String();
				for (int i=0;i<rowNum;i++) {
					if (i<rowNum-1) dishesID=dishesID+orderedtable.getValueAt(i, 0).toString()+",";
					else dishesID=dishesID+orderedtable.getValueAt(i, 0).toString();
					money=money+Float.parseFloat(orderedtable.getValueAt(i, 2).toString());
				}
				int pNum=Integer.parseInt(JOptionPane.showInputDialog(frame,"����������������"));
				tables.get(tableNO-1).setPeopleNum(pNum);
				String VIPuser=JOptionPane.showInputDialog(frame,"�ܽ�"+money+"    VIP�û�������");
				try {
					SQL.insert2orderings(tableNO,dishesID,money,VIPuser);
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
					System.out.println("��Ӷ�����Ϣ���ִ���");
				}
				frame.setVisible(false);
			}
		});
		panel.add(orderButton);
		
		frame.setVisible(true);
		
		return 0;
	}
	
	
	public void menu() {
		JFrame frame=new JFrame("���ϵͳ���˵�");
		frame.setSize(500,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel panel=new JPanel();
		frame.add(panel);
		panel.setLayout(null);
		
		JButton dishesManagerButton=new JButton("��Ʒ����");
		dishesManagerButton.setBounds(10,10,120,30);
		dishesManagerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dishesManager();
			}
		});
		panel.add(dishesManagerButton);
		
		JButton VIPManagerButton=new JButton("VIP�û�����");
		VIPManagerButton.setBounds(10, 60, 120, 30);
		VIPManagerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				VIPmanager();
			}
		});
		panel.add(VIPManagerButton);
		
		
		JButton orderManagerButton=new JButton("��������");
		orderManagerButton.setBounds(10,110,120,30);
		orderManagerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				orderManager();
			}
		});
		panel.add(orderManagerButton);
		
		JButton checkButton=new JButton("����");
		checkButton.setBounds(10, 200, 200, 80);
		checkButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				check();
			}
		});
		checkButton.setFont(new Font("Dialog",1,30));
		panel.add(checkButton);
		
		JButton orderButton=new JButton("�㵥");
		orderButton.setBounds(250,200,200,80);
		orderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				order();
			}
		});
		orderButton.setFont(new Font("Dialog", 1, 30));
		panel.add(orderButton);
		
		
		welcomeIcon.setImage(welcomeIcon.getImage().getScaledInstance(340, 170, Image.SCALE_DEFAULT));
		JLabel pictureJLabel=new JLabel(welcomeIcon);
		pictureJLabel.setBounds(140, 10, 340, 170);
		panel.add(pictureJLabel);
		
		frame.setVisible(true);
	}
}
