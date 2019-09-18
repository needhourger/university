package card;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import window.Window;

public class StudentManagerCard extends JPanel{
	private JButton addButton=new JButton("Add");
	private JButton viewButton=new JButton("View");
	private JButton removeButton=new JButton("Remove");
	private JButton modifyButton=new JButton("Modify");
	private JLabel label=new JLabel("Student Manager Card");
	
	public StudentManagerCard() {
		this.setLayout(null);
		
		label.setBounds(30, 10, 200, 30);
		addButton.setBounds(30, 50, 100, 30);
		viewButton.setBounds(30, 100,100,30);
		removeButton.setBounds(30, 150,100,30);
		modifyButton.setBounds(30, 200,100,30);
		this.add(label);
		this.add(addButton);
		this.add(viewButton);
		this.add(removeButton);
		this.add(modifyButton);
		addActionListener();
	}

	private void addActionListener() {
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.showAddStuCard();
			}
		});
		
		viewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.showViewStuCard();
			}
		});
		
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.showRemoveStuCard();
			}
		});
		
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.showModifyStuCard();
			}
		});
	}
	
	
}
