package swing;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
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

public class QuitUserFrame extends JFrame {

	private UserDTO user;

	private JPanel backgroundPanel;
	private JTextField checkQuitField;
	private JButton remainButton;
	private JButton quitButton;
	private Choice insertReason;
	private MainFrame mContext;
	
	private SocketManager socket;
	private String userName;

	public QuitUserFrame(UserDTO user,MainFrame mContext) throws SQLException {
		System.out.println("회원 탈퇴");
		this.user = user;
		this.mContext=mContext;
		setInitLayout();
		initListener();
	}

	private void setInitLayout() {
		setTitle("[회원 탈퇴]");
		setSize(500, 420);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(new Color(255, 204, 3));

		backgroundPanel = new JPanel();
		backgroundPanel.setBounds(80, 60, 350, 250);
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.WHITE);
		add(backgroundPanel);

		JLabel guidText1 = new JLabel(" 정말로 탈퇴하시겠어요? ");
		JLabel guidText2 = new JLabel(" 탈퇴하실 경우 '포켓 옥션 탈퇴'를 입력해주세요. ");
		guidText1.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 18));
		guidText2.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 18));
		checkQuitField = new JTextField(20);
		guidText1.setBounds(90, 25, 400, 50);
		guidText2.setBounds(20, 55, 400, 50);
		checkQuitField.setBounds(80, 110, 200, 30);
		
		quitButton = new JButton("회원 탈퇴");
		remainButton = new JButton("회원 정보 유지");
		quitButton.setBounds(50, 160, 120, 30);
		remainButton.setBounds(190, 160, 120, 30);

		backgroundPanel.add(guidText1);
		backgroundPanel.add(guidText2);
		backgroundPanel.add(checkQuitField);
		backgroundPanel.add(quitButton);
		backgroundPanel.add(remainButton);

		userName=user.getName();
		socket=mContext.socket;
		
		setVisible(true);
	}

	private void initListener() throws SQLException {
		// 비밀번호 입력 후 회원정보와 대조
		quitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String a=checkQuitField.getText();
				boolean aa=a.equals("포켓 옥션 탈퇴");
				boolean ab=a.equals("포켓옥션탈퇴");
				if(aa||ab) {
					JOptionPane.showMessageDialog(null,"회원 탈퇴가 완료되었습니다.");
					socket.sendOrder("quitUser#"+userName);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
					new LogInFrame();
				}
			}

		});
		remainButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}

		});
	}
	
	public static void main(String[] args) {
		
	}
	
}
