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

	public MakeNewAccountFrame(SocketManager socket) {
		this.socket = socket;
		setInitLayout();
		initListener();
	}

	private void setInitLayout() {
		System.out.println("확인");
		mainPanel = new JPanel();
		setTitle("[회원가입]");
		setSize(500, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(Color.white);

		backgroundPanel = new JPanel();
		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.white);
		add(backgroundPanel);

		idField = new JTextField(10);
		passwordField = new JTextField(10);
		nameField = new JTextField(10);
		JTextField phoneNumberField = new JTextField(10);

		idField.setBorder(null);
		passwordField.setBorder(null);
		nameField.setBorder(null);
		phoneNumberField.setBorder(null);

		signUpButton = new JButton();
		exitButton = new JButton();
		signUpButton.setBackground(null);
		signUpButton.setBorderPainted(false);
		signUpButton.setContentAreaFilled(false);
		exitButton.setBackground(null);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);

		Icon image1 = new ImageIcon("image/Account.png");

		JLabel background = new JLabel(image1);
		background.setBounds(0, 0, 500, 700);
		background.setHorizontalAlignment(JLabel.CENTER);
		backgroundPanel.add(background);

		JLabel exit = new JLabel(new ImageIcon("image/exit.png"));
		JLabel signUp = new JLabel(new ImageIcon("image/Account(2).png"));

		nameField.setBounds(170, 225, 170, 30);
		idField.setBounds(170, 310, 170, 30);
		passwordField.setBounds(170, 390, 170, 30);
		phoneNumberField.setBounds(170, 470, 170, 30);
		signUp.setBounds(90, 520, 160, 60);
		signUpButton.setBounds(90, 520, 160, 60);
		exit.setBounds(250, 520, 160, 60);
		exitButton.setBounds(250, 520, 160, 60);
		signUpButton.addActionListener(this);

		background.add(idField);
		background.add(passwordField);
		background.add(nameField);
		background.add(phoneNumberField);
		background.add(exit);
		background.add(signUp);
		background.add(signUpButton);
		background.add(exitButton);

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
		JButton selectedButton = (JButton) e.getSource();
		if (selectedButton == signUpButton) {
			String name = nameField.getText();
			String id = idField.getText();
			String password = passwordField.getText();
			socket.sendOrder("sendDB" + "#" + name + "#" + id + "#" + password);
			dispose();
		}
	}

}
