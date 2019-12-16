package simpleapp;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class MainControl extends JFrame{
	public static String input;
	
	private static DigistsPanel digistsPanel;
	private static TextPanel textPanel;
	private static HistoryPanel historyPanel;
	
	public MainControl() {
		input="";
		this.setTitle("Calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		textPanel=new TextPanel();
		this.add(textPanel,BorderLayout.NORTH);
		
		digistsPanel=new DigistsPanel();
		this.add(digistsPanel,BorderLayout.CENTER);
		
		historyPanel=new HistoryPanel();
		this.add(historyPanel,BorderLayout.AFTER_LAST_LINE);
		
		this.setVisible(true);
	}
	
	public static String calculate() throws Exception{
		return Operations.calculate(input);
	}
	
	public static String calculate(String string) throws Exception{
		return Operations.calculate(input,string);
	}
	
	public static void updateTextAndHistory() {
		textPanel.setText(input);
		historyPanel.addHistory(input);
	}
	
	public static void updateText() {
		textPanel.setText(input);
	}
	
	public static void clearHistory() {
		historyPanel.clearHistory();
	}
	
	public static void clearAll() {
		input="";
		historyPanel.clearHistory();
		textPanel.setText(input);
	}
	
	public static void main(String[] args) {
		MainControl mainControl=new MainControl();
	}


}
