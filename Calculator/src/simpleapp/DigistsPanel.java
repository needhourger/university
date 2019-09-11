package simpleapp;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DigistsPanel extends JPanel{
	
	public DigistsPanel() {
		String[] name= {
				"%","C","CE","<=",
				"7","8","9","+",
				"4","5","6","-",
				"1","2","3","*",
				"0",".","/","+/-",
				"","","","="};
		this.setLayout(new GridLayout(6, 4, 2, 2));
		this.setSize(new Dimension(450,200));
		for (int i=0;i<name.length;i++) {
			JButton button=new JButton(name[i]);
			button.addActionListener(new MyActionListener());
			this.add(button);
		}
	}
}
