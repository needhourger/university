
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
	private JLabel addressLabel = new JLabel("Address (NO COMMAS): ");
	private JLabel emContactLabel = new JLabel("Emergency Contact: ");
	private JLabel ageLabel = new JLabel("Age (INTEGER): ");
	private JLabel categoryLabel= new JLabel("Category: ");
	private JLabel departmentLabel = new JLabel("Department: ");
	private JLabel salaryTypeLabel = new JLabel("SalaryType: ");
	private JLabel salaryLabel = new JLabel("Salary (INTEGER): ");
	
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
	
	private JButton submitButton = new JButton("Save Changes");
	
	public ModifyEmpCard(EmployeeRecords employeeRecords, JTextArea textArea) {
		setLayout(new GridLayout(15,2,10,15));
		
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String fname = fNameTextField.getText();
				String lname = lNameTextField.getText();
				
				employeeRecords.modifyEmployee(fname, lname, textArea, fNameTextField2, lNameTextField2, phoneTextField, emailTextField, addressTextField, emContactTextField, ageTextField, categoryComboBox, departmentTextField, salaryTypeComboBox, salaryTextField);
			}	
		});
		
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String fname = fNameTextField.getText();
				String lname = lNameTextField.getText();
				String fname2 = fNameTextField2.getText();
				String lname2 = lNameTextField2.getText();
				String phone = phoneTextField.getText();
				String email = emailTextField.getText();
				String address = addressTextField.getText();
				String emContact = emContactTextField.getText();
				int age = Integer.parseInt(ageTextField.getText());
				String category = (String)categoryComboBox.getSelectedItem();
				String department = departmentTextField.getText();
				String salaryType = (String)salaryTypeComboBox.getSelectedItem();
				int salary = Integer.parseInt(salaryTextField.getText());
				
				//clear text fields
				fNameTextField.setText("");
				lNameTextField.setText("");
				fNameTextField2.setText("");
				lNameTextField2.setText("");
				phoneTextField.setText("");
				emailTextField.setText("");
				addressTextField.setText("");
				emContactTextField.setText("");
				ageTextField.setText("");
				departmentTextField.setText("");
				salaryTextField.setText("");
				
				//add student to records
				employeeRecords.submitChanges(fname,lname,fname2,lname2,phone,email,address,emContact,age,category,department,salaryType,salary);
				textArea.setText("Employee changes saved successfully");
			}
		});
		fNameLabel.setBorder(new LineBorder(Color.gray,1));
		lNameLabel.setBorder(new LineBorder(Color.gray,1));
		fNameLabel2.setBorder(new LineBorder(Color.gray,1));
		lNameLabel2.setBorder(new LineBorder(Color.gray,1));
		phoneLabel.setBorder(new LineBorder(Color.gray,1));
		emailLabel.setBorder(new LineBorder(Color.gray,1));
		addressLabel.setBorder(new LineBorder(Color.gray,1));
		emContactLabel.setBorder(new LineBorder(Color.gray,1));
		ageLabel.setBorder(new LineBorder(Color.gray,1));
		categoryLabel.setBorder(new LineBorder(Color.gray,1));
		departmentLabel.setBorder(new LineBorder(Color.gray,1));
		salaryTypeLabel.setBorder(new LineBorder(Color.gray,1));
		salaryLabel.setBorder(new LineBorder(Color.gray,1));
		
		add(fNameLabel);
		add(lNameLabel);
		add(fNameTextField);
		add(lNameTextField);
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
		add(submitButton);
	}
}