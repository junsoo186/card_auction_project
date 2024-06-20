package swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import dao.UserDAO;
import dto.UserDTO;
import manager.SocketManager;

public class LogInFrame extends JFrame {


		private JPanel backgroundPanel1;
		private JPanel backgroundPanel2;
		
		private JPanel mainPanel;
		
		private JPanel AuctionPanel;
		private JPanel LogInPanel;
		private JPanel PricePanel;
		private JPanel FinishPanel;
		private JPanel MyPagePanel;
		private JPanel JoinAuctionPanel;
		private UserDTO user;
		
		private JLabel id;
		private JLabel password;
		private JLabel backgroundLabel;
		private Icon backgroundIcon;
		private JTextField idField;
		private JTextField passwordField;
		
		private JButton logInButton;
		private JButton signUpButton;
		
		private JTabbedPane tabPane;
		
		private JLabel title;
		private SocketManager socket;
		private MainFrame mainFrame;
		
		public LogInFrame() {
			initData();
			setInitLayout();
			initListener();
		} 

		private void initData() {
			
			backgroundPanel1=new JPanel();
			backgroundPanel2=new JPanel();
			
			mainPanel=new JPanel();
			add(backgroundPanel1);
			add(backgroundPanel2);
			
			title=new JLabel("카드 경매 사이트에 오신 것을 환영합니다.");
			title.setFont(new Font("Freesentation 7 Bold",Font.BOLD,20));
		}

		private void setInitLayout() {
			new Thread(socket = new SocketManager()).start();
			setTitle("[로그인하기]");
			setSize(1920,1080);
			setLocationRelativeTo(null);
			setResizable(false);
			setLayout(null);
			getContentPane().setBackground(Color.white);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			backgroundPanel1.setSize(getWidth(),getHeight());
			backgroundPanel1.setLayout(null);
			backgroundPanel1.setBackground(Color.WHITE);
			
			backgroundPanel2.setSize(getWidth(),getHeight());
			backgroundPanel2.setLayout(null);
			backgroundPanel2.setBackground(Color.BLUE.darker());
			
			JLabel id=new JLabel("아이디 : ");
			id.setFont(new Font("Freesentation 7 Bold",Font.BOLD,15));
			Icon backgroundIcon=new ImageIcon("image/back.png");
			JLabel password=new JLabel("비밀번호 : ");
			password.setFont(new Font("Freesentation 7 Bold",Font.BOLD,15));
			idField=new JTextField(10);
			passwordField=new JTextField(10);
			logInButton=new JButton("로그인");
			logInButton.setFont(new Font("Freesentation 7 Bold",Font.BOLD,17));
			signUpButton=new JButton("회원가입");
			signUpButton.setFont(new Font("Freesentation 7 Bold",Font.BOLD,17));
			
			backgroundLabel=new JLabel(backgroundIcon);
			backgroundLabel.setSize(601,194);
			backgroundLabel.setLocation(700,270);
			backgroundLabel.setHorizontalAlignment(JLabel.CENTER);
			backgroundPanel1.add(backgroundLabel);
			
			id.setBounds(900,545,50,50);
			password.setBounds(900,560,100,100);
			idField.setBounds(960,560,80,20);
			passwordField.setBounds(960,600,80,20);
			
			logInButton.setBounds(830,650,120,50);
			signUpButton.setBounds(970,650,120,50);
			title.setBounds(850,480,300,60);
			mainPanel=new JPanel();
			
			
			backgroundPanel1.add(title);
			backgroundPanel1.add(id);
			backgroundPanel1.add(password);
			backgroundPanel1.add(idField);
			backgroundPanel1.add(passwordField);
			backgroundPanel1.add(logInButton);
			backgroundPanel1.add(signUpButton);
			
			setVisible(true);

		}
		
		private void initListener() {
			signUpButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					new MakeNewAccountFrame();
				}
			});
			logInButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					String id = idField.getText();
					String password = passwordField.getText();
					socket.sendOrder("login#" + id + "#" + password);
					UserDAO dao = new UserDAO();
					try {
						new MainFrame(dao.infoUser(id));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					dispose();
					}
			});
		}
	
		
	public static void main(String[] args) {
		new LogInFrame();
	}
	
}
	
