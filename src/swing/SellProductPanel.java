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
import javax.swing.JScrollPane;


public class SellProductPanel extends JPanel {
	
	private JPanel backgroundPanel;
	private JLabel title;
	private JScrollPane scrollPane;
	
	private int x = 500;
	private int y = 100;
	
	public SellProductPanel() {
		initData();
		setInitLayout();
	} 

	private void initData() {
		backgroundPanel=new JPanel();
	}
	
	
	private void setInitLayout() {
		setSize(1920,630);
		setLocation(0,400);
		setLayout(null);
		setBackground(Color.WHITE);
		add(backgroundPanel);
		
		scrollPane=new JScrollPane();	
		scrollPane.setBounds(4,4,950,330);
		
		JLabel title=new JLabel("경매 출품하기");
		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(860, 10, 800, 50);
		add(title);
		
	}

}
