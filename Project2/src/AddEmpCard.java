import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class AddEmpCard extends JPanel{
	private JLabel instructionsLabel = new JLabel("Please enter ALL fields to add employee.");
	private JLabel emptyLabel1 = new JLabel("");
	private JLabel fNameLabel = new JLabel("First Name: ");
	private JLabel lNameLabel = new JLabel("Last Name: ");
	private JLabel phoneLabel = new JLabel("Phone: ");
	private JLabel emailLabel = new JLabel("Email: ");
	private JLabel addressLabel = new JLabel("Address (NO COMMAS): ");
	private JLabel emContactLabel = new JLabel("Emergency Contact: ");
	private JLabel ageLabel = new JLabel("Age (INTEGER): ");
	private JLabel categoryLabel= new JLabel("Category: ");
	private JLabel departmentLabel = new JLabel("Department: ");
	private JLabel salaryTypeLabel = new JLabel("SalaryType: ");
	private JLabel salaryLabel = new JLabel("Salary (INTEGER): ");
	
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
	
	private JButton submitButton = new JButton("Submit");
	
	public AddEmpCard(EmployeeRecords employeeRecords, JTextArea textArea) {
		setLayout(new GridLayout(13,2,10,15));
		
		submitButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//get info from text fields
				String fname = fNameTextField.getText();
				String lname = lNameTextField.getText();
				String phone = phoneTextField.getText();
				String email = emailTextField.getText();
				String address = addressTextField.getText();
				String emContact = emContactTextField.getText();
				String age = ageTextField.getText();
				String category = (String) categoryComboBox.getSelectedItem();
				String department = departmentTextField.getText();
				String salaryType = (String) salaryTypeComboBox.getSelectedItem();
				String salary = salaryTextField.getText();
				
				fNameTextField.setText("");
				lNameTextField.setText("");
				phoneTextField.setText("");
				emailTextField.setText("");
				addressTextField.setText("");
				emContactTextField.setText("");
				ageTextField.setText("");
				departmentTextField.setText("");
				salaryTextField.setText("");
				
				employeeRecords.addEmployee(fname, lname, phone, email, address, emContact, age, category, department, salaryType, salary);
				textArea.setText("Employee added successfully");
				}
			
			});
		instructionsLabel.setBorder(new LineBorder(Color.blue,2));
		fNameLabel.setBorder(new LineBorder(Color.gray,1));
		lNameLabel.setBorder(new LineBorder(Color.gray,1));
		phoneLabel.setBorder(new LineBorder(Color.gray,1));
		emailLabel.setBorder(new LineBorder(Color.gray,1));
		addressLabel.setBorder(new LineBorder(Color.gray,1));
		emContactLabel.setBorder(new LineBorder(Color.gray,1));
		ageLabel.setBorder(new LineBorder(Color.gray,1));
		categoryLabel.setBorder(new LineBorder(Color.gray,1));
		departmentLabel.setBorder(new LineBorder(Color.gray,1));
		salaryTypeLabel.setBorder(new LineBorder(Color.gray,1));
		salaryLabel.setBorder(new LineBorder(Color.gray,1));
		
		
		add(instructionsLabel);
		add(emptyLabel1);
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

		add(submitButton);
	}
}
