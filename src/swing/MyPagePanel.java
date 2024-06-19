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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class MyPagePanel extends JPanel {
	
	private JPanel backgroundPanel;
	private JLabel title;
	private JScrollPane scrollPane;
	
	private int x = 500;
	private int y = 100;
	
	public MyPagePanel() {
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
		
		JLabel title=new JLabel("마이 페이지");
		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(860, 10, 800, 50);
		add(title);
		
		Icon profileIcon = new ImageIcon("image/profile.gif");
		JLabel profileLabel = new JLabel(profileIcon);
		profileLabel.setSize(279, 192);
		profileLabel.setBounds(550,180,300,300);
		profileLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel infoPanel=new JPanel();
		infoPanel.setBorder(new TitledBorder(new LineBorder(new Color(255,204,3),5)));
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setBounds(500,100,900,450);
		infoPanel.setLayout(null);
		
		JLabel ID = new JLabel(" ID : ");
		JLabel name = new JLabel(" 이름 : ");
		JLabel nickName = new JLabel(" 닉네임 : ");
		JLabel currentPoint = new JLabel(" 포인트 : ");
		
		ID.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		ID.setBounds(500, 100, 100, 50);
		name.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		name.setBounds(500, 150, 100, 50);
		nickName.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		nickName.setBounds(500, 200, 100, 50);
		currentPoint.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		currentPoint.setBounds(500, 250, 150, 50);
		
		add(profileLabel);
		add(infoPanel);
		infoPanel.add(ID);
		infoPanel.add(name);
		infoPanel.add(nickName);
		infoPanel.add(currentPoint);
	}

}
