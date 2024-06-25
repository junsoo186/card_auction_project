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
	private JButton exitButton;
	private JButton decidePriceButton;
	private Choice insertReason;
	private MainFrame mContext;
	
	private SocketManager socket;

	public QuitUserFrame(UserDTO user,MainFrame mContext) throws SQLException {
		System.out.println("회원 탈퇴");
		this.user = user;
		this.mContext=mContext;
		setInitLayout();
		initListener();
	}

	private void setInitLayout() {
		setTitle("[회원 탈퇴]");
		setSize(500, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(new Color(255, 204, 3));

		backgroundPanel = new JPanel();
		backgroundPanel.setBounds(80, 60, 350, 500);
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.WHITE);
		add(backgroundPanel);

		JLabel guidText1 = new JLabel(" 정말로 탈퇴하시겠어요? ");
		JLabel guidText2 = new JLabel(" 탈퇴하실 경우 '네, 탈퇴합니다.'를 입력해주세요. ");
		guidText1.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 18));
		guidText2.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 18));
		checkQuitField = new JTextField(20);
		guidText1.setBounds(90, 25, 400, 50);
		guidText2.setBounds(20, 55, 400, 50);
		checkQuitField.setBounds(80, 105, 200, 30);
		
		insertReason=new Choice();
		insertReason.add("서비스가 아쉬워서");
		insertReason.add("찾는 카드가 존재하지 않아서");
		insertReason.add("상품들의 가격이 비싸서");
		insertReason.add("사이트 사용이 불편해서");
		insertReason.add("더 편한 사이트를 찾게 되어서");
		insertReason.add("포켓몬스터 카드에 흥미가 떨어져서");
		insertReason.add("현생이 너무 힘들어서");
		insertReason.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 12));
		insertReason.setBounds(40,200,300,70);
		backgroundPanel.add(insertReason);
		
		decidePriceButton = new JButton("회원 탈퇴하기");
		exitButton = new JButton("회원 정보 유지하기");
		decidePriceButton.setBounds(40, 255, 90, 30);
		exitButton.setBounds(90, 255, 90, 30);

		backgroundPanel.add(guidText1);
		backgroundPanel.add(guidText2);
		backgroundPanel.add(checkQuitField);
		backgroundPanel.add(decidePriceButton);
		backgroundPanel.add(exitButton);

		setVisible(true);
	}

	private void initListener() throws SQLException {
		// 비밀번호 입력 후 회원정보와 대조
		decidePriceButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String a=checkQuitField.getText();
				boolean aa=a.equals("네, 탈퇴합니다.");
				if(aa) {
					JOptionPane.showMessageDialog(null,"회원 탈퇴가 완료되었습니다.");
					try {
						UserDAO.deleteUser(user.getName());
						System.out.println(user.getName()+"의 회원 탈퇴 완료");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					dispose();
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
