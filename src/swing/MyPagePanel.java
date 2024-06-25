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

public class MyPagePanel extends JPanel {

	private MainFrame mContext;

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
		user = mContext.getUser();
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
		
		JLabel myBack = new JLabel(new ImageIcon("image/노이즈2.GIF")); 
		myBack.setBounds(405,0,1089,575);
		add(myBack);

		title = new JLabel("마이 페이지");
		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(500, 150, 800, 50);

		updateButton = new JButton("회원정보 수정");
		updateButton.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		updateButton.setBounds(400, 400, 150, 50);
		updateButton.setBackground(new Color(255, 204, 3));
		updateButton.setBorderPainted(false);
		
		quitButton = new JButton("회원 탈퇴");
		quitButton.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		quitButton.setBounds(570, 400, 150, 50);
		quitButton.setBackground(new Color(255, 204, 3));
		quitButton.setBorderPainted(false);

		profileIcon = new ImageIcon("image/profile.gif");
		profileLabel = new JLabel(profileIcon);
		profileLabel.setSize(279, 192);
		profileLabel.setBounds(600, 210, 300, 300);
		profileLabel.setHorizontalAlignment(JLabel.CENTER);

		name = new JLabel(" 아이디 : " + user.getName());
		name.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		name.setBounds(500, 200, 300, 50);
		nickName = new JLabel(" 닉네임 : " + user.getNickname());
		nickName.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		nickName.setBounds(500, 250, 300, 50);
		currentPoint = new JLabel(" 포인트 : " + user.getPoint());
		currentPoint.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		currentPoint.setBounds(500, 300, 350, 50);
		cardNumber = new JLabel(" 보유 카드 수 : " + mContext.getUserInventory().size());
		cardNumber.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
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
		// 진행중인 경매 이동
		updateButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("회원정보수정 클릭");
				try {
					new CheckUserFrame(user,true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		// 진행중인 경매 이동
		quitButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						System.out.println("회원탈퇴 클릭");
						try {
							new CheckUserFrame(user,false);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					
				});
	}
}
