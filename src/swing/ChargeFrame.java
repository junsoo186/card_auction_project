package swing;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import dao.UserDAO;
import dto.UserDTO;
import manager.SocketManager;

public class ChargeFrame extends JFrame {	

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	private UserDAO userDAO;
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
	private Choice choice; 
	
	private JTabbedPane tabPane;
	
	private JLabel title;
	
	
	private JPanel backgroundPanel;

	private JLabel bankAccount;
	private JLabel backgroundLabel;
	private JLabel point;
	private JLabel charge;
	private JLabel nowMoney;
	
	private Icon backgroundIcon;

	private JButton exitButton;
	private SocketManager socket;
	private JButton signUpButton;
	
	private UserDTO user;
	
	public ChargeFrame(UserDTO user) {
		this.user=user;
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

		backgroundPanel = new JPanel();
		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.white);
		add(backgroundPanel);

		pointIcon=new ImageIcon("image/poketpoint.gif");
		point=new JLabel();
		point.setIcon(pointIcon);
		point.setBounds(222, 130, 35, 35);
		backgroundPanel.add(point);
		
		bankAccount = new JLabel("계좌번호    :    123-456-678900");
		bankAccount = new JLabel("계좌번호    :    123-456-678900");
		charge = new JLabel("충전 금액 : ");
		nowMoney = new JLabel("현재 금액 :       "+user.getPoint()+" 원");
		choice = new Choice();
		choice.add("1,000원");
		choice.add("5,000원");
		choice.add("10,000원");
		choice.add("30,000원");
		choice.add("50,000원");
		choice.add("100,000원");
		signUpButton = new JButton("충전하기");
		exitButton = new JButton("종료하기");

		userDAO=new UserDAO();
		
		Icon backgroundIcon = new ImageIcon("image/back2.png");
		backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setSize(279, 192);
		backgroundLabel.setLocation(130, 50);
		backgroundLabel.setHorizontalAlignment(JLabel.CENTER);
		
		nowMoney.setBounds(150, 200, 300, 50);
		bankAccount.setBounds(150, 230, 300, 50);
		charge.setBounds(150, 265, 100, 50);
		choice.setBounds(230,280,100,20);
		signUpButton.setBounds(140, 320, 100, 20);
		exitButton.setBounds(250, 320, 100, 20);

		
		backgroundPanel.add(nowMoney);
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
				JOptionPane.showMessageDialog(point,"충전이 완료되었습니다.");
				int chargeMoney=choice.getSelectedIndex();
				if(chargeMoney==0) {
					user.setPoint(user.getPoint()+1000);
				}else if(chargeMoney==1) {
					user.setPoint(user.getPoint()+5000);
				}else if(chargeMoney==2) {
					user.setPoint(user.getPoint()+10000);
				}else if(chargeMoney==3) {
					user.setPoint(user.getPoint()+30000);
				}else if(chargeMoney==4) {
					user.setPoint(user.getPoint()+50000);
				}else if(chargeMoney==5) {
					user.setPoint(user.getPoint()+100000);
				}
				
				try {
					userDAO.updatePoint(user.getName(),user.getPoint());
					System.out.println(userDAO.infoUser(user.getName()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			
				System.out.println(user.getPoint());
				dispose();
			}

		});
		exitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		backgroundPanel = new JPanel();
		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.white);
		add(backgroundPanel);
	}
	
public static void main(String[] args) {
}
}