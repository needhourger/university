
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
	private JLabel addressLabel = new JLabel("Address (NO COMMAS): ");
	private JLabel emContactLabel = new JLabel("Emergency Contact: ");
	private JLabel ageLabel = new JLabel("Age (INTEGER): ");
	private JLabel classLevelLabel= new JLabel("Class level: ");
	private JLabel majorLabel = new JLabel("Major: ");
	private JLabel gpaLabel = new JLabel("GPA (0.00-4.00): ");
	
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
	
	private JButton submitButton = new JButton("Save Changes");
	
	public ModifyStuCard(StudentRecords studentRecords, JTextArea textArea) {
		setLayout(new GridLayout(14,2,10,15));
		
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String fname = fNameTextField.getText();
				String lname = lNameTextField.getText();
				
				studentRecords.modifyStudent(fname, lname, textArea, fNameTextField2, lNameTextField2, phoneTextField, emailTextField, addressTextField, emContactTextField, ageTextField, classLevelComboBox, majorTextField, gpaTextField);
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
				String classLevel = (String)classLevelComboBox.getSelectedItem();
				String major = majorTextField.getText();
				Float gpa = Float.parseFloat(gpaTextField.getText());
				
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
				studentRecords.submitChanges(fname,lname,fname2,lname2,phone,email,address,emContact,age,classLevel,major,gpa);
				textArea.setText("Student changes saved successfully");
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
		classLevelLabel.setBorder(new LineBorder(Color.gray,1));
		majorLabel.setBorder(new LineBorder(Color.gray,1));
		gpaLabel.setBorder(new LineBorder(Color.gray,1));
		
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
		add(classLevelLabel);
		add(classLevelComboBox);
		add(majorLabel);
		add(majorTextField);
		add(gpaLabel);
		add(gpaTextField);
		add(submitButton);
	}
}
