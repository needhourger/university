package card;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import base.UIClass;
import window.Window;

public class AddEmpCard extends JPanel{
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
	
	private JButton submitButton = new JButton("Submit");
	
	public AddEmpCard() {
		setLayout(new GridLayout(12,2));
		
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

		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String firstName=fNameTextField.getText();
				String lastName=lNameTextField.getText();
				String phone=phoneTextField.getText();
				String email=emailTextField.getText();
				String address=addressTextField.getText();
				String emConteact=emailTextField.getText();
				String age=ageTextField.getText();
				String category=categoryComboBox.getSelectedItem().toString();
				String department=departmentTextField.getText();
				String salaryType=salaryTypeComboBox.getSelectedItem().toString();
				String salary=salaryTextField.getText();
				String args[]={firstName,lastName,phone,email,address,emConteact,age,category,department,salaryType,salary};
				for (int i=0;i<args.length;i++)
					if (args[i].equals("")) {
						Window.resTextArea.append("The data can not be empty!\n");
						return;
					}
				UIClass.empRecods.addEmployee(args);
				fNameTextField.setText("");
				lNameTextField.setText("");
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
