package swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import manager.SocketManager;

public class MakeNewAccountFrame extends JFrame implements ActionListener {

	private JPanel backgroundPanel;
	private JPanel mainPanel;

	private JLabel backgroundLabel;
	private Icon backgroundIcon;

	private JLabel name;
	private JLabel id;
	private JLabel password;
	private JLabel phoneNumber;

	private JButton exitButton;
	private SocketManager socket;
	private JButton signUpButton;
	private JTextField nameField;
	private JTextField passwordField;
	private JTextField idField;
	
	public MakeNewAccountFrame() {
		setInitLayout();
		initListener();
	}

	private void setInitLayout() {
		System.out.println("확인");
		new Thread(socket = new SocketManager()).start();
		mainPanel = new JPanel();
		setTitle("[회원가입]");
		setSize(500, 700);
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

		JLabel name = new JLabel("이름 : ");
		JLabel id = new JLabel("아이디 : ");
		JLabel password = new JLabel("비밀번호 : ");
		JLabel phoneNumber = new JLabel("전화번호 : ");
		idField = new JTextField(10);
		passwordField = new JTextField(10);
		nameField = new JTextField(10);
		JTextField phoneNumberField = new JTextField(10);
		signUpButton = new JButton("회원가입");
		exitButton = new JButton("종료하기");

		Icon backgroundIcon = new ImageIcon("image/back2.png");
		backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setSize(279, 192);
		backgroundLabel.setLocation(130, 50);
		backgroundLabel.setHorizontalAlignment(JLabel.CENTER);
		backgroundPanel.add(backgroundLabel);

		name.setBounds(160, 230, 50, 50);
		id.setBounds(150, 260, 50, 50);
		password.setBounds(150, 290, 100, 100);
		phoneNumber.setBounds(150, 320, 100, 100);

		nameField.setBounds(230, 245, 80, 20);
		idField.setBounds(230, 275, 80, 20);
		passwordField.setBounds(230, 330, 80, 20);
		phoneNumberField.setBounds(230, 370, 80, 20);
		signUpButton.setBounds(140, 430, 100, 40);
		exitButton.setBounds(250, 430, 100, 40);
		signUpButton.addActionListener(this);

		backgroundPanel.add(name);
		backgroundPanel.add(id);
		backgroundPanel.add(password);
		backgroundPanel.add(phoneNumber);

		backgroundPanel.add(idField);
		backgroundPanel.add(passwordField);
		backgroundPanel.add(nameField);
		backgroundPanel.add(phoneNumberField);
		backgroundPanel.add(signUpButton);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton selectedButton = (JButton)e.getSource();
		if(selectedButton == signUpButton) {
			String name = nameField.getText();
			String id = idField.getText();
			String password = passwordField.getText();
			socket.sendOrder("sendDB" + "#" + name +"#" + id + "#"+ password);
		}
	}

}
