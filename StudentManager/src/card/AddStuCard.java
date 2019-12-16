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

public class AddStuCard extends JPanel{
	private JLabel fNameLabel = new JLabel("First Name: ");
	private JLabel lNameLabel = new JLabel("Last Name: ");
	private JLabel phoneLabel = new JLabel("Phone: ");
	private JLabel emailLabel = new JLabel("Email: ");
	private JLabel addressLabel = new JLabel("Address: ");
	private JLabel emContactLabel = new JLabel("Emergency Contact: ");
	private JLabel ageLabel = new JLabel("Age: ");
	private JLabel classLevelLabel= new JLabel("Class level: ");
	private JLabel majorLabel = new JLabel("Major: ");
	private JLabel gpaLabel = new JLabel("GPA: ");
	
	private static String[] classLevels = {"First-year","Sophomore","Junior","Senior"};
	
	private JTextField fNameTextField = new JTextField();
	private JTextField lNameTextField = new JTextField();
	private JTextField phoneTextField = new JTextField();
	private JTextField emailTextField = new JTextField();
	private JTextField addressTextField = new JTextField();
	private JTextField emContactTextField = new JTextField();
	private JTextField ageTextField = new JTextField();
	private JComboBox<String> classLevelComboBox = new JComboBox<String>(classLevels);
	private JTextField majorTextField = new JTextField();
	private JTextField gpaTextField = new JTextField();
	
	private JButton submitButton = new JButton("Submit");
	
	public AddStuCard() {
		setLayout(new GridLayout(11,2));
		
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
		add(classLevelLabel);
		add(classLevelComboBox);
		add(majorLabel);
		add(majorTextField);
		add(gpaLabel);
		add(gpaTextField);
		
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
				String classLevel=classLevelComboBox.getSelectedItem().toString();
				String major=majorTextField.getText();
				String gpa=gpaTextField.getText();
				String args[]={firstName,lastName,phone,email,address,emConteact,age,classLevel,major,gpa};
				for (int i=0;i<args.length;i++)
					if (args[i].equals("")) {
						Window.resTextArea.append("The data can not be empty!\n");
						return;
					}
				UIClass.stuRecods.addStudent(args);
				fNameTextField.setText("");
				lNameTextField.setText("");
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
