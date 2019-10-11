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

public class AddStuCard extends JPanel{
	private JLabel instructionsLabel = new JLabel("Please enter ALL fields to add a new student.");
	private JLabel emptyLabel = new JLabel("");
	private JLabel fNameLabel = new JLabel("First Name: ");
	private JLabel lNameLabel = new JLabel("Last Name: ");
	private JLabel phoneLabel = new JLabel("Phone (INTEGER): ");
	private JLabel emailLabel = new JLabel("Email: ");
	private JLabel addressLabel = new JLabel("Address (NO COMMAS): ");
	private JLabel emContactLabel = new JLabel("Emergency Contact: ");
	private JLabel ageLabel = new JLabel("Age: ");
	private JLabel classLevelLabel= new JLabel("Class level: ");
	private JLabel majorLabel = new JLabel("Major: ");
	private JLabel gpaLabel = new JLabel("GPA (0.00-4.00): ");
	
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
	
	public AddStuCard(StudentRecords studentRecords, JTextArea textArea) {
		
		setLayout(new GridLayout(12,2,10,15));
		
		submitButton.addActionListener(new ActionListener() {
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
				String classLevel = (String)classLevelComboBox.getSelectedItem();
				String major = majorTextField.getText();
				String gpa = gpaTextField.getText();
				
				//clear text fields
				fNameTextField.setText("");
				lNameTextField.setText("");
				phoneTextField.setText("");
				emailTextField.setText("");
				addressTextField.setText("");
				emContactTextField.setText("");
				ageTextField.setText("");
				majorTextField.setText("");
				gpaTextField.setText("");
				
				//add student to records
				studentRecords.addStudent(fname,lname,phone,email,address,emContact,age,classLevel,major,gpa);
				textArea.setText("Student added successfully");
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
		classLevelLabel.setBorder(new LineBorder(Color.gray,1));
		majorLabel.setBorder(new LineBorder(Color.gray,1));
		gpaLabel.setBorder(new LineBorder(Color.gray,1));
		
		
		add(instructionsLabel);
		add(emptyLabel);
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
		add(submitButton);
	}
}
