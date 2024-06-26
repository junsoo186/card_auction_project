package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.UserDAO;
import dto.UserDTO;
import manager.SocketManager;

public class CheckUserFrame extends JFrame {

	private UserDTO user;
	// checkUserFrame의 용도
	// true: 회원정보 수정
	// false: 회원 탈퇴
	private boolean purpose;
	private SocketManager socket;

	private JPanel backgroundPanel;
	private JTextField passwordField;
	private JButton exitButton;
	private JButton decidePriceButton;
	private MainFrame mContext;

	public CheckUserFrame(UserDTO user,Boolean purpose,MainFrame mContext) throws SQLException {
		System.out.println("회원 탈퇴");
		this.user = user;
		this.purpose=purpose;
		this.mContext=mContext;
		setInitLayout();
		initListener();
	}

	private void setInitLayout() {
		setTitle("[회원 정보 확인]");
		setSize(500, 550);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(new Color(255, 204, 3));

		backgroundPanel = new JPanel();
		backgroundPanel.setBounds(70, 60, 350, 370);
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.WHITE);
		add(backgroundPanel);

		JLabel guidText = new JLabel(" 비밀번호를 입력해주세요. ");
		guidText.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 18));
		passwordField = new JTextField(20);
		decidePriceButton = new JButton("비밀번호 입력");
		exitButton = new JButton("회원 정보 유지");
		guidText.setBounds(90, 75, 400, 50);
		passwordField.setBounds(80, 135, 200, 30);
		decidePriceButton.setBounds(50, 185, 120, 30);
		exitButton.setBounds(190, 185, 120, 30);

		backgroundPanel.add(guidText);
		backgroundPanel.add(passwordField);
		backgroundPanel.add(decidePriceButton);
		backgroundPanel.add(exitButton);
		
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						if(user.getPassword().equals(passwordField.getText())) {
							if(purpose==false) {
								new QuitUserFrame(user,mContext);
								dispose();
							} else {
								new UpdateUserFrame(user,mContext);
								dispose();
							}
						} else {
							if(purpose==true) {
								JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다.");
								JOptionPane.showMessageDialog(null,"회원 정보 수정을 종료합니다.");
								dispose();
							} else {
								JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다.");
								JOptionPane.showMessageDialog(null,"회원 탈퇴를 종료합니다.");
								dispose();
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		setVisible(true);
	}

	private void initListener() throws SQLException {
		// 비밀번호 입력 후 회원정보와 대조
		decidePriceButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if(user.getPassword().equals(passwordField.getText())) {
						if(purpose==false) {
							new QuitUserFrame(user,mContext);
							dispose();
						} else {
							new UpdateUserFrame(user,mContext);
							dispose();
						}
					} else {
						JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다.");
						JOptionPane.showMessageDialog(null,"회원 탈퇴를 종료합니다.");
						dispose();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});
		exitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}

		});
	}
	
	public static void main(String[] args) {
		
	}

}
