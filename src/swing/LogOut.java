package swing;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LogOut extends JFrame {
	
	JLabel logoutBack;
	JButton logoutButton;
	
	public LogOut() {
		initData();
		setInitLayout();
		
	}
	
	public void initData() {
		logoutBack = new JLabel();
		logoutButton = new JButton("LOGOUT");
		
	}
	
	public void setInitLayout() {
		setTitle("LOGOUT");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setVisible(true);
		setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage("image/파비콘2.png"));
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logoutButton.setBounds(90,90,100,30);
		add(logoutButton);
	}
	
	
	public static void main(String[] args) {
		new LogOut();
	}
}
