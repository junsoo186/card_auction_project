package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.Query;
import dao.UserDAO;
import dto.UserDTO;
import manager.DBConnectionManager;
import manager.SocketManager;

public class UpdateUserFrame extends JFrame {

	private UserDTO user;

	private JPanel backgroundPanel;
	private JTextField nickField;
	private JTextField passField;
	private JButton exitButton;
	private JButton insertButton;
	private MainFrame mContext;
	
	private SocketManager socket;
	
	private UserDTO newUser;
	private String userName;
	private String newNick;
	private String newPass;

	public UpdateUserFrame(UserDTO user,MainFrame mContext) {
		System.out.println("회원 정보 수정");
		this.user = user;
		this.mContext=mContext;
		setInitLayout();
		initListener();
	}

	private void setInitLayout() {
		setTitle("[회원 정보 수정]");
		setSize(500, 550);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(new Color(255, 204, 3));

		backgroundPanel = new JPanel();
		backgroundPanel.setBounds(80, 60, 350, 370);
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.WHITE);
		add(backgroundPanel);

		JLabel guidText = new JLabel(" 회원 정보 수정 ");
		guidText.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		JLabel changeNicknameText = new JLabel(" 변경할 닉네임을 입력해주세요. (현재 닉네임 :"+user.getName()+ ")");
		changeNicknameText.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 14));
		JLabel changePasswordText= new JLabel(" 비밀번호를 입력해주세요. (현재 비밀번호 :"+user.getPassword()+" )");
		changePasswordText.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 14));
		nickField = new JTextField(20);
		passField = new JTextField(20);
		insertButton = new JButton("입력하기");
		exitButton = new JButton("나가기");
		
		socket=mContext.socket;
		
		guidText.setBounds(120, 30, 400, 50);
		changeNicknameText.setBounds(50, 75, 400, 50);
		nickField.setBounds(75, 120, 200, 30);
		changePasswordText.setBounds(50, 155, 400, 50);
		passField.setBounds(75, 200, 200, 30);
		insertButton.setBounds(75, 255, 90, 30);
		exitButton.setBounds(185, 255, 90, 30);

		backgroundPanel.add(guidText);
		backgroundPanel.add(changeNicknameText);
		backgroundPanel.add(changePasswordText);
		backgroundPanel.add(nickField);
		backgroundPanel.add(passField);
		backgroundPanel.add(insertButton);
		backgroundPanel.add(exitButton);
		
		
		
		setVisible(true);
	}

	private void initListener() {
		// 회원정보 수정 버튼
		insertButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				newNick=nickField.getText();
				newPass=passField.getText();
				userName=user.getName();
				if(newNick.equals("")||newPass.equals("")||userName.equals("")) {
					JOptionPane.showMessageDialog(null,"잘못된 정보를 입력했습니다. 다시 입력해주세요.");
				}else {
					socket.sendOrder("updateUserInfo#"+newNick+"#"+newPass+"#"+userName);
					System.out.println("새로운 닉네임 : " + newNick);
					System.out.println("새로운 패스워드 : " + newPass);
					JOptionPane.showMessageDialog(null,"회원 정보가 성공적으로 수정되었습니다.");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(user);
						//UserDAO.updateUser(newPass,newUser);
					dispose();
				}	
			}

		});
		// 나가기 버튼
		exitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null,"로그아웃이 완료되었습니다.");
				dispose();
				new LogInFrame();
			}

		});
	}

}
