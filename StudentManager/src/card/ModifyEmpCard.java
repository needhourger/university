package card;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import base.EmployeeRecords;
import base.UIClass;
import window.Window;

public class ModifyEmpCard extends JPanel{
	private JLabel fNameLabel = new JLabel("First Name: ");
	private JLabel lNameLabel = new JLabel("Last Name: ");
	
	private JTextField fNameTextField = new JTextField();
	private JTextField lNameTextField = new JTextField();
	
	private JButton searchButton = new JButton("Search");
	private JLabel emptyLabel = new JLabel("");
	
	private JLabel fNameLabel2 = new JLabel("First Name: ");
	private JLabel lNameLabel2 = new JLabel("Last Name: ");
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
	
	private JTextField fNameTextField2 = new JTextField();
	private JTextField lNameTextField2 = new JTextField();
	private JTextField phoneTextField = new JTextField();
	private JTextField emailTextField = new JTextField();
	private JTextField addressTextField = new JTextField();
	private JTextField emContactTextField = new JTextField();
	private JTextField ageTextField = new JTextField();
	private JComboBox<String> categoryComboBox = new JComboBox<String>(categories);
	private JTextField departmentTextField = new JTextField();
	private JComboBox<String> salaryTypeComboBox = new JComboBox<String>(salaryTypes);
	private JTextField salaryTextField = new JTextField();
	
	private JButton submitButton = new JButton("Submit");
	
	public ModifyEmpCard() {
		setLayout(new GridLayout(15,2));
		
		add(fNameLabel);
		add(lNameLabel);
		add(fNameTextField);
		add(lNameTextField);
		
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index=UIClass.empRecods.getEmployeeIndex(fNameTextField.getText(), lNameTextField.getText());
				if (index==-1) {
					Window.resTextArea.append("Employer not in the records!\n");
					return;
				}
				fNameTextField2.setText(UIClass.empRecods.employeeArray[index].getFirstName());
				lNameTextField2.setText(UIClass.empRecods.employeeArray[index].getLastName());
				phoneTextField.setText(String.valueOf(UIClass.empRecods.employeeArray[index].getPhone()));
				emailTextField.setText(UIClass.empRecods.employeeArray[index].getEmail());
				addressTextField.setText(UIClass.empRecods.employeeArray[index].getAddress());
				emContactTextField.setText(UIClass.empRecods.employeeArray[index].getEmContact());
				ageTextField.setText(String.valueOf(UIClass.empRecods.employeeArray[index].getAge()));
				categoryComboBox.setSelectedItem(UIClass.empRecods.employeeArray[index].getCategory());
				departmentTextField.setText(UIClass.empRecods.employeeArray[index].getDepartment());
				salaryTypeComboBox.setSelectedItem(UIClass.empRecods.employeeArray[index].getSalaryType());
				salaryTextField.setText(String.valueOf(UIClass.empRecods.employeeArray[index].getSalary()));
			}
		});
		add(searchButton);
		add(emptyLabel);
		
		add(fNameLabel2);
		add(fNameTextField2);
		add(lNameLabel2);
		add(lNameTextField2);
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
		
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String oldFirstName=fNameTextField.getText();
				String oldLastName=lNameTextField.getText();
				String firstName=fNameTextField2.getText();
				String lastName=lNameTextField2.getText();
				String phone=phoneTextField.getText();
				String email=emailTextField.getText();
				String address=addressTextField.getText();
				String emConteact=emailTextField.getText();
				String age=ageTextField.getText();
				String category=categoryComboBox.getSelectedItem().toString();
				String department=departmentTextField.getText();
				String salaryType=salaryTypeComboBox.getSelectedItem().toString();
				String salary=salaryTextField.getText();
				String args[]={oldFirstName,oldLastName,firstName,lastName,phone,email,address,emConteact,age,category,department,salaryType,salary};
				for (int i=0;i<args.length;i++)
					if (args[i].equals("")) {
						Window.resTextArea.append("The data can not be empty!\n");
						return;
					}
				UIClass.empRecods.modifyEmployee(args);
				fNameTextField.setText("");
				lNameTextField.setText("");
				fNameTextField2.setText("");
				lNameTextField2.setText("");
				phoneTextField.setText("");
				emailTextField.setText("");
				addressTextField.setText("");
				emContactTextField.setText("");
				ageTextField.setText("");
				categoryComboBox.setSelectedIndex(0);
				departmentTextField.setText("");
				salaryTypeComboBox.setSelectedIndex(0);
				salaryTextField.setText("");
			}
		});
		add(submitButton);
	}
}