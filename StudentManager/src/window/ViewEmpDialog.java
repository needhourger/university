package window;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import base.Employee;
import base.Student;
import base.UIClass;

public class ViewEmpDialog extends JDialog {
	private JLabel fNameLabel = new JLabel("First Name: ");
	private JLabel lNameLabel = new JLabel("Last Name: ");
	private JLabel phoneLabel = new JLabel("Phone: ");
	private JLabel emailLabel = new JLabel("Email: ");
	private JLabel addressLabel = new JLabel("Address: ");
	private JLabel emContactLabel = new JLabel("Emergency Contact: ");
	private JLabel ageLabel = new JLabel("Age: ");
	private JLabel categoryLabel= new JLabel("Category: ");
	private JLabel departmentLabel = new JLabel("Department: ");
	private JLabel salaryTypeLabel = new JLabel("SalaryType: ");
	private JLabel salaryLabel = new JLabel("Salary: ");
	
	private static String[] categories = {"Faculty","Staff"};
	private static String[] salaryTypes = {"Weekly", "Bi-Weekly","Monthly","Quarterly"};
	
	private JTextField fNameTextField = new JTextField();
	private JTextField lNameTextField = new JTextField();
	private JTextField phoneTextField = new JTextField();
	private JTextField emailTextField = new JTextField();
	private JTextField addressTextField = new JTextField();
	private JTextField emContactTextField = new JTextField();
	private JTextField ageTextField = new JTextField();
	private JComboBox<String> categoryComboBox = new JComboBox<String>(categories);
	private JTextField departmentTextField = new JTextField();
	private JComboBox<String> salaryTypeComboBox = new JComboBox<String>(salaryTypes);
	private JTextField salaryTextField = new JTextField();
	
	public ViewEmpDialog(Component p,String fName,String lName) {
		super();
		
		setLayout(new GridLayout(11,2));
		
		fNameTextField.setEditable(false);
		lNameTextField.setEditable(false);
		phoneTextField.setEditable(false);
		emailTextField.setEditable(false);
		addressTextField.setEditable(false);
		emContactTextField.setEditable(false);
		ageTextField.setEditable(false);
		categoryComboBox.setEditable(false);
		departmentTextField.setEditable(false);
		salaryTypeComboBox.setEditable(false);
		salaryTextField.setEditable(false);
		
		add(fNameLabel);
		add(fNameTextField);
		add(lNameLabel);
		add(lNameTextField);
		add(phoneLabel);
		add(phoneTextField);
		add(emailLabel);
		add(emailTextField);
		add(addressLabel);
		add(addressTextField);
		add(emContactLabel);
		add(emContactTextField);
		add(ageLabel);
		add(ageTextField);
		add(categoryLabel);
		add(categoryComboBox);
		add(departmentLabel);
		add(departmentTextField);
		add(salaryTypeLabel);
		add(salaryTypeComboBox);
		add(salaryLabel);
		add(salaryTextField);
		
		this.setModal(true);
		this.setSize(500, 500);
		this.setLocationRelativeTo(p);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int i=UIClass.empRecods.getEmployeeIndex(fName, lName);
		if (i==-1) {
			Window.resTextArea.append("Employee not in the record!\n");
			return;
		}
		Employee temp=UIClass.empRecods.employeeArray[i];
		fNameTextField.setText(temp.getFirstName());
		lNameTextField.setText(temp.getLastName());
		phoneTextField.setText(String.valueOf(temp.getPhone()));
		emailTextField.setText(temp.getEmail());
		addressTextField.setText(temp.getAddress());
		emContactTextField.setText(temp.getEmContact());
		ageTextField.setText(String.valueOf(temp.getAge()));
		categoryComboBox.setSelectedItem(temp.getCategory());
		departmentTextField.setText(temp.getDepartment());
		salaryTypeComboBox.setSelectedItem(temp.getSalaryType());
		salaryTextField.setText(String.valueOf(temp.getSalary()));
		this.setVisible(true);
	}
}
