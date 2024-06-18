package swing;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FinishedPanel extends JPanel {
	
	private ArrayList<JButton> productList = new ArrayList<>(12);
	
	private JPanel backgroundPanel;
	private JLabel title;
	
	public FinishedPanel() {
		initData();
		setInitLayout();
	} 

	private void initData() {
		backgroundPanel=new JPanel();
	}
	
	private void setInitLayout() {
		title=new JLabel("종료된 경매 둘러보기");
		title.setFont(new Font("Freesentation 7 Bold",Font.PLAIN,28));
		title.setBounds(900, 230, 100, 50);
		setSize(1920,710);
		setLocation(0,370);
		setLayout(null);
		backgroundPanel.setBounds(0, 370, 1920, 710);
		
		add(backgroundPanel);
		backgroundPanel.add(title);
		
		setVisible(true);

	}
	

}
