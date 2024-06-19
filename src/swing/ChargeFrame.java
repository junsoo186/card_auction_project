package swing;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import manager.SocketManager;

public class ChargeFrame extends JFrame {


	private JPanel backgroundPanel1;
	private JPanel backgroundPanel2;
	
	private JPanel mainPanel;
	
	private JPanel AuctionPanel;
	private JPanel LogInPanel;
	private JPanel PricePanel;
	private JPanel FinishPanel;
	private JPanel MyPagePanel;
	private JPanel JoinAuctionPanel;
	
	private JButton logInButton;
	private Icon pointIcon;
	
	private JTabbedPane tabPane;
	
	private JLabel title;
	
	
	private JPanel backgroundPanel;

	private JLabel bankAccount;
	private JLabel backgroundLabel;
	private Icon backgroundIcon;

	private JButton exitButton;
	private SocketManager socket;
	private JButton signUpButton;
	
	public ChargeFrame() {
		setInitLayout();
		initListener();
	}

	private void setInitLayout() {
		new Thread(socket = new SocketManager()).start();
		mainPanel = new JPanel();
		setTitle("[캐시 충전하기]");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		backgroundPanel = new JPanel();
		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.white);
		add(backgroundPanel);

		Icon pointIcon=new ImageIcon("image/poketpoint.gif");
		JLabel point=new JLabel();
		point.setIcon(pointIcon);
		point.setBounds(222, 150, 35, 35);
		backgroundPanel.add(point);
		
		bankAccount = new JLabel("계좌번호    :    123-456-678900");
		JLabel charge = new JLabel("충전 금액 : ");
		Choice choice = new Choice();
		choice.add("1,000원");
		choice.add("5,000원");
		choice.add("10,000원");
		choice.add("30,000원");
		choice.add("50,000원");
		choice.add("100,000원");
		signUpButton = new JButton("충전하기");
		exitButton = new JButton("종료하기");

		Icon backgroundIcon = new ImageIcon("image/back2.png");
		backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setSize(279, 192);
		backgroundLabel.setLocation(130, 50);
		backgroundLabel.setHorizontalAlignment(JLabel.CENTER);
		

		bankAccount.setBounds(150, 230, 300, 50);
		charge.setBounds(150, 265, 100, 50);
		choice.setBounds(230,280,100,20);
		signUpButton.setBounds(140, 320, 100, 20);
		exitButton.setBounds(250, 320, 100, 20);

		
		backgroundPanel.add(choice);
		backgroundPanel.add(bankAccount);
		backgroundPanel.add(charge);
		backgroundPanel.add(signUpButton);
		backgroundPanel.add(exitButton);

		setVisible(true);

	}

	private void initListener() {
		signUpButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setTitle("[캐시 충전 완료]");
				setSize(500, 500);
				setLocationRelativeTo(null);
				setResizable(false);
				setLayout(null);
				getContentPane().setBackground(Color.white);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				backgroundPanel = new JPanel();
				backgroundPanel.setSize(getWidth(), getHeight());
				backgroundPanel.setLayout(null);
				backgroundPanel.setBackground(Color.white);
				add(backgroundPanel);
			}

		});
	}
	
public static void main(String[] args) {
	new ChargeFrame();
}
}