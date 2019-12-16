package window;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import card.AddEmpCard;
import card.AddStuCard;
import card.EmployeeManagerCard;
import card.ModifyEmpCard;
import card.ModifyStuCard;
import card.RemoveEmpCard;
import card.RemoveStuCard;
import card.StudentManagerCard;
import card.ViewEmpCard;
import card.ViewEmpPanel;
import card.ViewStuCard;
import card.ViewStuPanel;

public class Window extends JFrame{
	public static JTextArea resTextArea=new JTextArea(10, 50);
	public static JScrollPane scrollPane=new JScrollPane(resTextArea);
	
	private static JButton stuManagerButton=new JButton("Student Manager");
	private static JButton empManagerButton=new JButton("Employee Manager");
	private static JButton breakButton=new JButton("Break");
	private static JPanel cardPanel=new JPanel();
	private static CardLayout cardLayout=new CardLayout();
	private static JPanel buttonJPanel=new JPanel();
	
	
	private static AddEmpCard addEmpCard=new AddEmpCard();
	private static AddStuCard addStuCard=new AddStuCard();
	private static ModifyEmpCard modifyEmpCard=new ModifyEmpCard();
	private static ModifyStuCard modifyStuCard=new ModifyStuCard();
	private static RemoveEmpCard removeEmpCard=new RemoveEmpCard();
	private static RemoveStuCard removeStuCard=new RemoveStuCard();
	private static ViewEmpCard viewEmpCard=new ViewEmpCard();
	private static ViewStuCard viewStuCard=new ViewStuCard();
	private static EmployeeManagerCard employeeManagerCard=new EmployeeManagerCard();
	private static StudentManagerCard studentManagerCard=new StudentManagerCard();
	private static ViewEmpPanel viewEmpPanel=new ViewEmpPanel();
	private static ViewStuPanel viewStuPanel=new ViewStuPanel();
	
	private static int flag=1;
	
	public Window() {
		this.setTitle("Manager Console");
		this.setSize(800, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new BorderLayout(10, 5));
		
		buttonJPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
		buttonJPanel.add(stuManagerButton);
		buttonJPanel.add(empManagerButton);
		buttonJPanel.add(breakButton);
		this.add(buttonJPanel,BorderLayout.NORTH);
		
		cardPanel.setLayout(cardLayout);
		cardPanel.add("studentManagerCard",studentManagerCard);
		cardPanel.add("employeeManagerCard",employeeManagerCard);
		cardPanel.add("addEmpCard",addEmpCard);
		cardPanel.add("addStuCard",addStuCard);
		cardPanel.add("modifyEmpCard",modifyEmpCard);
		cardPanel.add("modifyStuCard",modifyStuCard);
		cardPanel.add("removeEmpCard",removeEmpCard);
		cardPanel.add("removeStuCard",removeStuCard);
		cardPanel.add("viewEmpCard",viewEmpCard);
		cardPanel.add("viewStuCard",viewStuCard);
		cardPanel.add("viewEmpPanelCard",viewEmpPanel);
		cardPanel.add("viewStuPanelCard",viewStuPanel);
		cardPanel.setBorder(new LineBorder(Color.GRAY, 2));
		this.add(cardPanel,BorderLayout.CENTER);
		
		
		resTextArea.setEditable(false);
		resTextArea.setLineWrap(false);
		resTextArea.setBorder(new LineBorder(Color.gray,2));
		this.add(scrollPane,BorderLayout.SOUTH);
		
		stuManagerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println(e.getActionCommand());
				cardLayout.show(cardPanel,"studentManagerCard");
				flag=1;
			}
		});
		empManagerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println(e.getActionCommand());
				cardLayout.show(cardPanel,"employeeManagerCard");
				flag=2;
			}
		});
		breakButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println(e.getActionCommand());
				if (flag==1) cardLayout.show(cardPanel,"studentManagerCard");
				else if (flag==2) cardLayout.show(cardPanel,"employeeManagerCard");
				else cardLayout.show(cardPanel,"studentManagerCard");
			}
		});
		
		this.setVisible(true);
	}
	
	public static void showAddStuCard() {
		cardLayout.show(cardPanel, "addStuCard");
	}
	
	public static void showAddEmpCard() {
		cardLayout.show(cardPanel, "addEmpCard");
	}

	public static void showModifyEmpCard() {
		cardLayout.show(cardPanel, "modifyEmpCard");
	}
	
	public static void showModifyStuCard() {
		cardLayout.show(cardPanel, "modifyStuCard");
	}
	
	public static void showRemoveEmpCard() {
		cardLayout.show(cardPanel, "removeEmpCard");
	}
	
	public static void showRemoveStuCard() {
		cardLayout.show(cardPanel, "removeStuCard");
	}
	
	public static void showViewEmpCard() {
		cardLayout.show(cardPanel, "viewEmpCard");
	}
	
	public static void showViewStuCard() {
		cardLayout.show(cardPanel, "viewStuCard");
	}

	public static void showViewEmpPanelCard(String fname,String lname) {
		viewEmpPanel.update(fname, lname);
		cardLayout.show(cardPanel, "viewEmpPanelCard");
	}

	public static void showStuPanelCard(String fname,String lname) {
		viewStuPanel.update(fname, lname);
		cardLayout.show(cardPanel, "viewStuPanelCard");
		
	}
}
