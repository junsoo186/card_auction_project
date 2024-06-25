package swing;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import manager.SocketManager;

public class LogOut extends JFrame {
	SocketManager mContext;
	MainFrame mainFrame;
	JLabel logoutBack;
	JButton logoutButton;
	
	public LogOut(SocketManager mContext, MainFrame mainFrame) {
		this.mContext = mContext;
		this.mainFrame = mainFrame;
		initData();
		setInitLayout();
		initListener();
		
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
	public void initListener() {
		logoutButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			
			dispose();
		}
		});
	}
	
	
	
}
