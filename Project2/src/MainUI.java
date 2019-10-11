import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class MainUI extends JFrame{
	private static StudentRecords studentRecords = new StudentRecords();
	private static EmployeeRecords employeeRecords = new EmployeeRecords();

	private String[] stuBoxItems = {"STUDENT","Add Student","Remove Student", "View Student","Modify Student"};
	private String[] empBoxItems = {"EMPLOYEE","Add Employee","Remove Employee", "View Employee","Modify Employee"};
	private JComboBox<String> stuComboBox = new JComboBox<String>(stuBoxItems);
	private JComboBox<String> empComboBox = new JComboBox<String>(empBoxItems);
	private JPanel boxPanel = new JPanel();
	
	private CardLayout cl = new CardLayout();
	private JPanel cards = new JPanel();
	
	//public because textArea is set to non-editable and does not have any actual information inside
	public JTextArea textArea = new JTextArea(10,10);
	
	private AddStuCard addStuCard = new AddStuCard(studentRecords, textArea);
	private RemoveStuCard removeStuCard = new RemoveStuCard(studentRecords, textArea);
	private ViewStuCard viewStuCard = new ViewStuCard(studentRecords, textArea);
	private ModifyStuCard modifyStuCard = new ModifyStuCard(studentRecords, textArea);
	private AddEmpCard addEmpCard = new AddEmpCard(employeeRecords, textArea);
	private RemoveEmpCard removeEmpCard = new RemoveEmpCard(employeeRecords, textArea);
	private ViewEmpCard viewEmpCard = new ViewEmpCard(employeeRecords, textArea);
	private ModifyEmpCard modifyEmpCard = new ModifyEmpCard(employeeRecords, textArea);
	
	public MainUI() {
		super("Records Menu");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				writeStudentRecords(studentRecords);
				writeEmployeeRecords(employeeRecords);
				dispose();
			}
			
		});
		setResizable(true);
		setSize(900,900);
		setLayout(new BorderLayout());
		setVisible(true);
		
		boxPanel.add(stuComboBox);
		boxPanel.add(empComboBox);
		
		cards.setLayout(cl);
		cards.add(addStuCard, "Add Student");
		cards.add(removeStuCard, "Remove Student");
		cards.add(viewStuCard, "View Student");
		cards.add(modifyStuCard, "Modify Student");
		cards.add(addEmpCard, "Add Employee");
		cards.add(removeEmpCard, "Remove Employee");
		cards.add(viewEmpCard, "View Employee");
		cards.add(modifyEmpCard, "Modify Employee");
		
		textArea.setEditable(false);
		textArea.setBorder(new LineBorder(Color.GRAY,2));
		//stuComboBox.setSelectedItem(null);
		stuComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				Object item = stuComboBox.getSelectedItem();
				if("Add Student".equals(item)) {
					cl.show(cards, "Add Student");
				}
				else if("Remove Student".equals(item)) {
					cl.show(cards, "Remove Student");
				}
				else if("View Student".equals(item)) {
					cl.show(cards, "View Student");
				}
				else if("Modify Student".equals(item)) {
					cl.show(cards, "Modify Student");
				}	
			}
		});
		
		empComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				Object item = empComboBox.getSelectedItem();
				if("Add Employee".equals(item)) {
					cl.show(cards, "Add Employee");
				}
				else if("Remove Employee".equals(item)) {
					cl.show(cards, "Remove Employee");
				}
				else if("View Employee".equals(item)) {
					cl.show(cards, "View Employee");
				}
				else if("Modify Employee".equals(item)) {
					cl.show(cards, "Modify Employee");
				}
			}
		});
		
		add(boxPanel, BorderLayout.NORTH);
		add(cards, BorderLayout.CENTER);
		add(textArea, BorderLayout.SOUTH);
		
	}
	
	public static void main(String[] args) {
		readStudentRecords(studentRecords);
		readEmployeeRecords(employeeRecords);
		new MainUI();
	}
	
	public static void readStudentRecords(StudentRecords studentRecords) {
		File file = new File("StudentRecords.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			ArrayList<String[]> arrayOfStudentData = new ArrayList<String[]>();
			while((line = br.readLine())!=null) {
				arrayOfStudentData.add(line.split(","));
			}
			for(int i=0; i<arrayOfStudentData.size();i++) {
				String fname = arrayOfStudentData.get(i)[0];
				String lname = arrayOfStudentData.get(i)[1];
				String phone = arrayOfStudentData.get(i)[2];
				String email = arrayOfStudentData.get(i)[3];
				String address = arrayOfStudentData.get(i)[4];
				String emContact = arrayOfStudentData.get(i)[5];
				String age = arrayOfStudentData.get(i)[6];
				String classLevel = arrayOfStudentData.get(i)[7];
				String major = arrayOfStudentData.get(i)[8];
				String gpa = arrayOfStudentData.get(i)[9];

				studentRecords.addStudent(fname, lname, phone, email, address, emContact, age, classLevel, major, gpa);
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}
	
	public static void readEmployeeRecords(EmployeeRecords employeeRecords) {
		File file = new File("EmployeeRecords.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			ArrayList<String[]> arrayOfEmployeeData = new ArrayList<String[]>();
			while((line = br.readLine())!=null) {
				arrayOfEmployeeData.add(line.split(","));
			}
			for(int i=0; i<arrayOfEmployeeData.size();i++) {
				String fname = arrayOfEmployeeData.get(i)[0];
				String lname = arrayOfEmployeeData.get(i)[1];
				String phone = arrayOfEmployeeData.get(i)[2];
				String email = arrayOfEmployeeData.get(i)[3];
				String address = arrayOfEmployeeData.get(i)[4];
				String emContact = arrayOfEmployeeData.get(i)[5];
				String age = arrayOfEmployeeData.get(i)[6];
				String category = arrayOfEmployeeData.get(i)[7];
				String department = arrayOfEmployeeData.get(i)[8];
				String salaryType = arrayOfEmployeeData.get(i)[9];
				String salary = arrayOfEmployeeData.get(i)[10];

				employeeRecords.addEmployee(fname, lname, phone, email, address, emContact, age, category, department, salaryType, salary);
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}
	
	public static void writeEmployeeRecords(EmployeeRecords employeeRecords) {
		File file = new File("EmployeeRecords.txt");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,false));
			
			for(int i=0;i<employeeRecords.employeeArray.size();i++) {
				String fname = employeeRecords.employeeArray.get(i).getFirstName();
				String lname = employeeRecords.employeeArray.get(i).getLastName();
				String phone = employeeRecords.employeeArray.get(i).getPhone();
				String email = employeeRecords.employeeArray.get(i).getEmail();
				String address = employeeRecords.employeeArray.get(i).getAddress();
				String emContact = employeeRecords.employeeArray.get(i).getEmContact();
				String age = Integer.toString(employeeRecords.employeeArray.get(i).getAge());
				String category = employeeRecords.employeeArray.get(i).getCategory();
				String department = employeeRecords.employeeArray.get(i).getDepartment();
				String salaryType = employeeRecords.employeeArray.get(i).getSalaryType();
				String salary = Integer.toString(employeeRecords.employeeArray.get(i).getSalary());
				
				bw.write(fname+","+lname+","+phone+","+email+","+address+","+emContact+","+age+","+category+","+department+","+salaryType+","+salary+"\n");
			}
			
			bw.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("File not found");
		}
		System.out.println("Write Success");
	}
	
	public static void writeStudentRecords(StudentRecords studentRecords) {
		File file = new File("StudentRecords.txt");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,false));
			
			for(int i=0;i<studentRecords.studentArray.size();i++) {
				if(studentRecords.studentArray.get(i).equals(null)) {
					bw.write("");
					break;
				}
				else {
					String fname = studentRecords.studentArray.get(i).getFirstName();
					String lname = studentRecords.studentArray.get(i).getLastName();
					String phone = studentRecords.studentArray.get(i).getPhone();
					String email = studentRecords.studentArray.get(i).getEmail();
					String address = studentRecords.studentArray.get(i).getAddress();
					String emContact = studentRecords.studentArray.get(i).getEmContact();
					String age = Integer.toString(studentRecords.studentArray.get(i).getAge());
					String classLevel = studentRecords.studentArray.get(i).getClassLevel();
					String major = studentRecords.studentArray.get(i).getMajor();
					String gpa = Float.toString(studentRecords.studentArray.get(i).getGpa());
					
					bw.write(fname+","+lname+","+phone+","+email+","+address+","+emContact+","+age+","+classLevel+","+major+","+gpa+"\n");
				}
			}	
			
			bw.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("File not found");
		}
		System.out.println("Write Success");
	}
}
