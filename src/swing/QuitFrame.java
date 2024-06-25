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

public class QuitFrame extends JFrame {

	private UserDTO user;

	private JPanel backgroundPanel;
	private JTextField checkQuitField;
	private JButton exitButton;
	private JButton decidePriceButton;
	private Choice insertReason;

	public QuitFrame(UserDTO user) throws SQLException {
		System.out.println("회원 탈퇴");
		this.user = user;
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

		JLabel guidText = new JLabel(" 정말로 탈퇴하시겠어요? 탈퇴하실 경우 '네, 탈퇴합니다.'를 입력해주세요.");
		guidText.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 18));
		checkQuitField = new JTextField(20);
		guidText.setBounds(60, 75, 400, 50);
		checkQuitField.setBounds(80, 135, 200, 30);
		
		insertReason.add("서비스가 아쉬워서");
		insertReason.add("찾는 카드가 존재하지 않아서");
		insertReason.add("상품들의 가격이 비싸서");
		insertReason.add("사이트 사용이 불편해서");
		insertReason.add("더 편한 사이트를 찾게 되어서");
		insertReason.add("포켓몬스터 카드에 흥미가 떨어져서");
		insertReason.add("현생이 너무 힘들어서");
		insertReason.setBounds(100,100,300,70);
		add(insertReason);
		
		decidePriceButton = new JButton("회원 탈퇴하기");
		exitButton = new JButton("회원 정보 유지하기");
		decidePriceButton.setBounds(80, 185, 90, 30);
		exitButton.setBounds(185, 185, 90, 30);

		backgroundPanel.add(guidText);
		backgroundPanel.add(checkQuitField);
		backgroundPanel.add(decidePriceButton);
		backgroundPanel.add(exitButton);

		setVisible(true);
	}

	private void initListener() throws SQLException {
		// 비밀번호 입력 후 회원정보와 대조
		decidePriceButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					UserDTO checkUser=UserDAO.infoUser(user.getName());
					if(user.getPassword()==checkUser.getPassword()) {
						new QuitFrame(user);
						dispose();
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
