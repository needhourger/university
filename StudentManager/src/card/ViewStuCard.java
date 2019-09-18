package card;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import window.Window;

public class ViewStuCard extends JPanel{
	private JLabel fNameLabel = new JLabel("First Name: ");
	private JLabel lNameLabel = new JLabel("Last Name: ");
	
	private JTextField fNameTextField = new JTextField();
	private JTextField lNameTextField = new JTextField();
	
	private JButton viewButton = new JButton("Search");
	public ViewStuCard() {
		setLayout(new GridLayout(3,2,10,160));
		
		add(fNameLabel);
		add(lNameLabel);
		add(fNameTextField);
		add(lNameTextField);
		
		viewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String firstName=fNameTextField.getText();
				String lastName=lNameTextField.getText();
				Window.showStuPanelCard(firstName,lastName);
				fNameTextField.setText("");
				lNameTextField.setText("");
			}
		});
		add(viewButton);
	}
}