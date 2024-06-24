package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dto.UserDTO;

public class UpdateUserFrame extends JFrame {

	private UserDTO user;

	private JPanel backgroundPanel;
	private JTextField addPriceField;
	private JButton exitButton;
	private JButton decidePriceButton;

	public UpdateUserFrame(UserDTO user) {
		System.out.println("회원 정보 수정");
		this.user = user;
		setInitLayout();
		initListener();
	}

	private void setInitLayout() {
		setTitle("[회원 정보 수정]");
		setSize(500, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(new Color(255, 204, 3));

		backgroundPanel = new JPanel();
		backgroundPanel.setBounds(80, 60, 350, 500);
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.WHITE);
		add(backgroundPanel);

		JLabel guidText = new JLabel(" 회원 정보 수정 ");
		guidText.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 18));
		JLabel checkText = new JLabel(" 비밀번호를 입력해주세요. ");
		checkText.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 14));
		addPriceField = new JTextField(20);
		decidePriceButton = new JButton("입력하기");
		exitButton = new JButton("나가기");
		guidText.setBounds(40, 335, 400, 50);
		checkText.setBounds(40, 355, 400, 50);
		addPriceField.setBounds(80, 385, 200, 30);
		decidePriceButton.setBounds(80, 425, 90, 30);
		exitButton.setBounds(185, 425, 90, 30);

		backgroundPanel.add(guidText);
		backgroundPanel.add(addPriceField);
		backgroundPanel.add(decidePriceButton);
		backgroundPanel.add(exitButton);

		setVisible(true);
	}

	private void initListener() {
		exitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}

		});
	}

}
