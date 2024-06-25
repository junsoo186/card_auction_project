package swing;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class LogOut extends JFrame {
	
	JLabel logoutBack;
	
	public LogOut() {
		
		initData();
		setInitLayout();
	}
	
	public void initData() {
		logoutBack = new JLabel();
	
		
		
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
		
	}
	
	
	public static void main(String[] args) {
		new LogOut();
	}
}
