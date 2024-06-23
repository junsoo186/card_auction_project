package swing;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import dto.UserDTO;
import manager.SocketManager;

public class ChargeFrame extends JFrame {

	private MainFrame mContext;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	private JPanel backgroundPanel1;
	private JPanel backgroundPanel2;

	private JPanel mainPanel;

	private JButton logInButton;
	private Icon pointIcon;
	private Choice choice;

	private JTabbedPane tabPane;

	private JLabel title;

	private JPanel backgroundPanel;

	private JLabel backIcon;

	private JLabel bankAccount;
	private JLabel backgroundLabel;
	private JLabel point;
	private JLabel charge;
	private JLabel nowMoney;

	private Icon backgroundIcon;

	private SocketManager socket;

	// 충전하기 버튼
	private JButton signUpButton;
	private JLabel buttonImg;

	private UserDTO user;

	public ChargeFrame(MainFrame mContext) {
		this.mContext = mContext;
		this.user = mContext.getUser();
		this.socket = mContext.getSocket();
		setInitLayout();
		initListener();
	}

	private void setInitLayout() {
		mainPanel = new JPanel();
		setTitle("[캐시 충전하기]");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage("image/파비콘2.png"));
		getContentPane().setBackground(Color.white);

		backgroundIcon = new ImageIcon("image/충전창.png");
		backIcon = new JLabel(backgroundIcon);
		buttonImg = new JLabel(new ImageIcon("image/충전하기.png"));
		buttonImg.setBounds(185, 320, 115, 65);
		backIcon.setBounds(0, 0, 500, 500);
		backgroundPanel = new JPanel();
		backgroundPanel.setSize(500, 500);
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.white);
		add(backgroundPanel);
		backgroundPanel.add(backIcon);

		pointIcon = new ImageIcon("image/poketpoint.gif");
		point = new JLabel();
		point.setIcon(pointIcon);
		point.setBounds(222, 130, 35, 35);
		backIcon.add(point);
		backIcon.add(buttonImg);

		bankAccount = new JLabel("계좌번호    :    123-456-678900");
		charge = new JLabel("충전 금액 : ");
		nowMoney = new JLabel("현재 금액 :       " + user.getPoint() + " 원");
		choice = new Choice();
		choice.add("1,000원");
		choice.add("5,000원");
		choice.add("10,000원");
		choice.add("30,000원");
		choice.add("50,000원");
		choice.add("100,000원");

		Icon backgroundIcon = new ImageIcon("image/back2.png");
		backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setSize(279, 192);
		backgroundLabel.setLocation(130, 50);
		backgroundLabel.setHorizontalAlignment(JLabel.CENTER);

		nowMoney.setBounds(150, 200, 300, 50);
		bankAccount.setBounds(150, 230, 300, 50);
		charge.setBounds(150, 265, 100, 50);
		choice.setBounds(230, 280, 100, 20);

		backIcon.add(nowMoney);
		backIcon.add(choice);
		backIcon.add(bankAccount);
		backIcon.add(charge);

		signUpButton = new JButton();
		signUpButton.setBounds(185, 320, 110, 60);
		signUpButton.setBackground(null);
		signUpButton.setBorderPainted(false);
		signUpButton.setContentAreaFilled(false);
//		buttonImg.add(signUpButton);
		add(signUpButton);
	}

	private void initListener() {
		signUpButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("충전하기 클릭");
				int chargeMoney = choice.getSelectedIndex();
				if (chargeMoney == 0) {
					user.setPoint(user.getPoint() + 1000);
				} else if (chargeMoney == 1) {
					user.setPoint(user.getPoint() + 5000);
				} else if (chargeMoney == 2) {
					user.setPoint(user.getPoint() + 10000);
				} else if (chargeMoney == 3) {
					user.setPoint(user.getPoint() + 30000);
				} else if (chargeMoney == 4) {
					user.setPoint(user.getPoint() + 50000);
				} else if (chargeMoney == 5) {
					user.setPoint(user.getPoint() + 100000);
				}
				socket.sendOrder("cash#" + user.getName() + "#" + user.getPoint());

				JOptionPane.showMessageDialog(point, "충전이 완료되었습니다.");
				mContext.getUser().setPoint(user.getPoint());
				mContext.getCash().setText(user.getPoint() + " 원");
				dispose();
			}
		});
	}

	private void successPanel() {
		setTitle("[캐시 충전 완료]");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(Color.white);

		backgroundPanel = new JPanel();
		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.white);
		add(backgroundPanel);
	}
}