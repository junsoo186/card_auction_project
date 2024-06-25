package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import dto.UserDTO;
import lombok.Data;
import manager.SocketManager;


public class MyPagePanel extends JPanel {

	public JLabel getNickName() {
		return nickName;
	}

	public void setNickName(JLabel nickName) {
		this.nickName = nickName;
	}

	private MainFrame mContext;
	private SocketManager socket;

	private JPanel backgroundPanel;
	private JLabel title;
	private UpdateUserFrame updateUserFrame;
	private JButton updateButton;
	private JButton quitButton;

	private JLabel name;
	private JLabel nickName;
	private JLabel currentPoint;
	private JLabel cardNumber;
	private JLabel profileLabel;
	private Icon profileIcon;

	private int x = 500;
	private int y = 100;

	private UserDTO user;

	public MyPagePanel(MainFrame mContext) {
		this.mContext = mContext;
		this.user = mContext.getUser();
		this.socket=mContext.socket;
		initData();
		setInitLayout();
		initListener();
	}

	public JLabel getPoint() {
		return currentPoint;
	}
	
	private void initData() {
		backgroundPanel = new JPanel();
	}

	private void setInitLayout() {
		setSize(1920, 630);
		setLocation(0, 400);
		setLayout(null);
		setBackground(Color.WHITE);
		add(backgroundPanel);
		
		JLabel myBack = new JLabel(new ImageIcon("image/마이페이지.png")); 
		JLabel metmong = new JLabel(new ImageIcon("image/메타몽.gif"));
		
		myBack.setBounds(450,0,1089,575);
		add(myBack);
		metmong.setBounds(200,100,640,360);
		myBack.add(metmong);
		title = new JLabel("마이 페이지");
		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(500, 150, 800, 50);
		title.setForeground(Color.white);

		updateButton = new JButton(new ImageIcon("image/회원정보수정.png"));
		updateButton.setBounds(950, 200, 150, 50);
		updateButton.setBorderPainted(false);
		updateButton.setContentAreaFilled(false);
		updateButton.setFocusPainted(false);
		
		
		quitButton = new JButton(new ImageIcon("image/탈퇴.png"));
		quitButton.setBounds(950, 250, 150, 50);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		profileIcon = new ImageIcon("image/profile.gif");
		profileLabel = new JLabel(profileIcon);
		profileLabel.setSize(279, 192);
		profileLabel.setBounds(600, 210, 300, 300);
		profileLabel.setHorizontalAlignment(JLabel.CENTER);

		name = new JLabel(" 아이디 : " + user.getName());
		name.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		name.setForeground(Color.white);
		name.setBounds(500, 200, 300, 50);
		nickName = new JLabel(" 닉네임 : " + user.getNickname());
		nickName.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		nickName.setForeground(Color.white);
		nickName.setBounds(500, 250, 300, 50);
		currentPoint = new JLabel(" 포인트 : " + user.getPoint());
		currentPoint.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		currentPoint.setForeground(Color.white);
		currentPoint.setBounds(500, 300, 350, 50);
		cardNumber = new JLabel(" 보유 카드 수 : " + mContext.getUserInventory().size());
		cardNumber.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		cardNumber.setForeground(Color.white);
		cardNumber.setBounds(500, 350, 350, 50);

		myBack.add(title);
		myBack.add(name);
		myBack.add(nickName);
		myBack.add(currentPoint);
		myBack.add(cardNumber);
		myBack.add(updateButton);
		myBack.add(quitButton);
	}

	private void initListener() {
		// 회원 정보 수정
		updateButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("회원정보수정 클릭");
				try {
					new CheckUserFrame(user,true,mContext);
					Thread.sleep(5000);
				} catch (SQLException | InterruptedException e1) {
					e1.printStackTrace();
				}
				
			}
			
			
		});
		// 진행중인 경매 이동
		quitButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						System.out.println("회원탈퇴 클릭");
						try {
							new CheckUserFrame(user,false,mContext);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					
				});
	}
}
