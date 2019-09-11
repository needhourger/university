package simpleapp;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class HistoryPanel extends JPanel {
	private JLabel historyLabel;
	private Vector<String> histories;
	private static final int MAX_NUM=10;
	
	public HistoryPanel() {
		histories=new Vector<String>();
		
		historyLabel=new JLabel();
		historyLabel.setBorder(new LineBorder(Color.DARK_GRAY,1));
		historyLabel.setHorizontalAlignment(JLabel.LEFT);
		historyLabel.setPreferredSize(new Dimension(470,200));
		this.add(historyLabel);
	}

	public void addHistory(String input) {
		if (input=="")
			return;
		if (histories.size()==MAX_NUM) {
			histories.remove(0);
			histories.add(input);
			updateText();
		}
		else {
			histories.add(input);
			updateText();
		}
		
	}

	public void clearHistory() {
		histories.clear();
		updateText();
	}
	
	private void updateText() {
		String text="<html>";
		for (int i=0;i<histories.size();i++) 
			text=text.concat(histories.get(i)).concat("<br/>");
		text=text.concat("</html>");
		historyLabel.setText(text);;
	}
}
