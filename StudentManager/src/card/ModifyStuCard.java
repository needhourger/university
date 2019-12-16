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

public class ModifyStuCard extends JPanel{
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
	private JLabel classLevelLabel= new JLabel("Class level: ");
	private JLabel majorLabel = new JLabel("Major: ");
	private JLabel gpaLabel = new JLabel("GPA: ");
	
	private static String[] classLevels = {"First-year","Sophomore","Junior","Senior"};
	
	private JTextField fNameTextField2 = new JTextField();
	private JTextField lNameTextField2 = new JTextField();
	private JTextField phoneTextField = new JTextField();
	private JTextField emailTextField = new JTextField();
	private JTextField addressTextField = new JTextField();
	private JTextField emContactTextField = new JTextField();
	private JTextField ageTextField = new JTextField();
	private JComboBox<String> classLevelComboBox = new JComboBox<String>(classLevels);
	private JTextField majorTextField = new JTextField();
	private JTextField gpaTextField = new JTextField();
	
	private JButton submitButton = new JButton("Submit");
	
	public ModifyStuCard() {
		setLayout(new GridLayout(14,2));
		
		add(fNameLabel);
		add(lNameLabel);
		add(fNameTextField);
		add(lNameTextField);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index=UIClass.stuRecods.getStudentIndex(fNameTextField.getText(), lNameTextField.getText());
				if (index==-1) {
					Window.resTextArea.append("Student not in the records!\n");
					return;
				}
				fNameTextField2.setText(UIClass.stuRecods.studentArray[index].getFirstName());
				lNameTextField2.setText(UIClass.stuRecods.studentArray[index].getLastName());
				phoneTextField.setText(String.valueOf(UIClass.stuRecods.studentArray[index].getPhone()));
				emailTextField.setText(UIClass.stuRecods.studentArray[index].getEmail());
				addressTextField.setText(UIClass.stuRecods.studentArray[index].getAddress());
				emContactTextField.setText(UIClass.stuRecods.studentArray[index].getEmContact());
				ageTextField.setText(String.valueOf(UIClass.stuRecods.studentArray[index].getAge()));
				classLevelComboBox.setSelectedItem(UIClass.stuRecods.studentArray[index].getClassLevel());
				majorTextField.setText(UIClass.stuRecods.studentArray[index].getMajor());
				gpaTextField.setText(String.valueOf(UIClass.stuRecods.studentArray[index].getGpa()));
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
		add(classLevelLabel);
		add(classLevelComboBox);
		add(majorLabel);
		add(majorTextField);
		add(gpaLabel);
		add(gpaTextField);
		
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
				String classLevel=classLevelComboBox.getSelectedItem().toString();
				String major=majorTextField.getText();
				String gpa=gpaTextField.getText();
				String args[]={oldFirstName,oldLastName,firstName,lastName,phone,email,address,emConteact,age,classLevel,major,gpa};
				for (int i=0;i<args.length;i++)
					if (args[i].equals("")) {
						Window.resTextArea.append("The data can not be empty!\n");
						return;
					}
				UIClass.stuRecods.modifyStudent(args);
				fNameTextField.setText("");
				lNameTextField.setText("");
				fNameTextField2.setText("");
				lNameTextField2.setText("");
				phoneTextField.setText("");
				emailTextField.setText("");
				addressTextField.setText("");
				emContactTextField.setText("");
				ageTextField.setText("");
				classLevelComboBox.setSelectedIndex(0);
				majorTextField.setText("");
				gpaTextField.setText("");
				
			}
		});
		add(submitButton);
	}
}
