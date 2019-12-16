
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ViewStuCard extends JPanel{
	private JLabel fNameLabel = new JLabel("First Name: ");
	private JLabel lNameLabel = new JLabel("Last Name: ");
	
	private JTextField fNameTextField = new JTextField();
	private JTextField lNameTextField = new JTextField();
	
	private JButton viewButton = new JButton("Search");
	public ViewStuCard(StudentRecords studentRecords, JTextArea textArea) {
		setLayout(new GridLayout(3,2,50,250));
		
		viewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//get info from text fields
				String fname = fNameTextField.getText();
				String lname = lNameTextField.getText();
				
				
				//clear text fields
				fNameTextField.setText("");
				lNameTextField.setText("");
				
				//view student info
				studentRecords.retrieveStudentInfo(fname, lname, textArea);
			}
		});
		
		add(fNameLabel);
		add(lNameLabel);
		add(fNameTextField);
		add(lNameTextField);
		add(viewButton);
	}
}