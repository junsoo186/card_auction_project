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

public class AuctionPanel extends JPanel {
	
	private ArrayList<JButton> productList = new ArrayList<>(12);
	private ProductButton productButton = new ProductButton(this);
	private JPanel backgroundPanel;
	private JLabel title;
	
	public AuctionPanel() {
		initData();
		setInitLayout();
	} 

	private void initData() {
		backgroundPanel=new JPanel();
	}
	
	private void setInitLayout() {
		setSize(1920,710);
		setLocation(0,370);
		setLayout(null);
		add(backgroundPanel);
		
		title=new JLabel("진행 중인 경매 둘러보기");
		title.setFont(new Font("Freesentation 7 Bold",Font.PLAIN,28));
		backgroundPanel.setBounds(0, 50, 1920, 710);
		backgroundPanel.add(title);
		productButton.createProduct(TOOL_TIP_TEXT_KEY);
		setVisible(true);

	}
	

}
