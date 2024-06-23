package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import dto.UserDTO;

public class MyPagePanel extends JPanel {

	private MainFrame mContext;

	private JPanel backgroundPanel;
	private JLabel title;
	private UpdateUserFrame updateUserFrame;
	private JButton updateButton;

	private JPanel infoPanel;
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

	private void initData() {
		backgroundPanel = new JPanel();
	}

	private void setInitLayout() {
		setSize(1920, 630);
		setLocation(0, 400);
		setLayout(null);
		setBackground(Color.WHITE);
		add(backgroundPanel);

		title = new JLabel("마이 페이지");
		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(860, 10, 800, 50);

		updateButton = new JButton("회원정보 수정");
		updateButton.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		updateButton.setBounds(400, 360, 150, 50);
		updateButton.setBackground(new Color(255, 204, 3));
		updateButton.setBorderPainted(false);

		profileIcon = new ImageIcon("image/profile.gif");
		profileLabel = new JLabel(profileIcon);
		profileLabel.setSize(279, 192);
		profileLabel.setBounds(600, 170, 300, 300);
		profileLabel.setHorizontalAlignment(JLabel.CENTER);

		infoPanel = new JPanel();
		infoPanel.setBorder(new TitledBorder(new LineBorder(new Color(255, 204, 3), 5)));
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setBounds(500, 100, 900, 450);
		infoPanel.setLayout(null);

		name = new JLabel(" 아이디 : " + user.getName());
		name.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		name.setBounds(500, 130, 300, 50);
		nickName = new JLabel(" 닉네임 : " + user.getNickname());
		nickName.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		nickName.setBounds(500, 180, 300, 50);
		currentPoint = new JLabel(" 포인트 : " + user.getPoint());
		currentPoint.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		currentPoint.setBounds(500, 230, 350, 50);
		cardNumber = new JLabel(" 보유 카드 수 : " + mContext.getUserInventory().size());
		cardNumber.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		cardNumber.setBounds(500, 280, 350, 50);

		add(title);
		infoPanel.add(name);
		infoPanel.add(nickName);
		infoPanel.add(currentPoint);
		infoPanel.add(cardNumber);
		infoPanel.add(updateButton);
		add(profileLabel);
		add(infoPanel);
	}

	private void initListener() {
		// 진행중인 경매 이동
		updateButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("회원정보수정 클릭");
				new UpdateUserFrame(user);
			}
		});
	}
}
