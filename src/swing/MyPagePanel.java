package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import dto.CardDTO;
import dto.UserDTO;


public class MyPagePanel extends JPanel {
	
	private JPanel backgroundPanel;
	private JLabel title;
	private JScrollPane scrollPane;
	private UpdateUserFrame updateUserFrame;
	private JButton updateButton;
	
	private int x = 500;
	private int y = 100;
	
	private UserDTO user;
	
	public MyPagePanel(UserDTO user) {
		this.user=user;
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
		
		updateButton=new JButton("회원정보 수정");
		updateButton.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		updateButton.setBounds(400, 360, 150, 50);
		updateButton.setBackground(new Color(255,204,3));
		updateButton.setBorderPainted(false);
		
		Icon profileIcon = new ImageIcon("image/profile.gif");
		JLabel profileLabel = new JLabel(profileIcon);
		profileLabel.setSize(279, 192);
		profileLabel.setBounds(600,170,300,300);
		profileLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel infoPanel=new JPanel();
		infoPanel.setBorder(new TitledBorder(new LineBorder(new Color(255,204,3),5)));
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setBounds(500,100,900,450);
		infoPanel.setLayout(null);
		
		JLabel ID = new JLabel(" ID : "+user.getId());
		JLabel name = new JLabel(" 이름 : "+user.getName());
		JLabel nickName = new JLabel(" 닉네임 : "+user.getNickname());
		JLabel currentPoint = new JLabel(" 포인트 : "+user.getPoint());
		JLabel cardNumber = new JLabel(" 보유 카드 수 : ");
		
		ID.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		ID.setBounds(500, 110, 300, 50);
		name.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		name.setBounds(500, 160, 300, 50);
		nickName.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		nickName.setBounds(500, 210, 300, 50);
		currentPoint.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		currentPoint.setBounds(500, 260, 350, 50);
		cardNumber.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		cardNumber.setBounds(500, 310, 350, 50);
		
		add(profileLabel);
		add(infoPanel);
		infoPanel.add(ID);
		infoPanel.add(name);
		infoPanel.add(nickName);
		infoPanel.add(currentPoint);
		infoPanel.add(updateButton);
	}
	
	private void initListener() {
		// 진행중인 경매 이동
		updateButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new UpdateUserFrame(user);
			}

		});
		
	}

}
