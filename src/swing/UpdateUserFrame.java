package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.java.accessibility.util.GUIInitializedListener;

import dto.CardDTO;
import dto.UserDTO;

public class UpdateUserFrame extends JFrame {
	
	private UserDTO user;
	
	private JPanel backgroundPanel;
	private JTextField addPriceField;
	private JButton exitButton;
	private JButton decidePriceButton;
	
	public UpdateUserFrame(UserDTO user) {
		System.out.println("회원 정보 수정");
		this.user=user;
		setInitLayout();
		initListener();
	}

	private void setInitLayout() {
		setTitle("[가격 제시하기]");
		setSize(500, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(new Color(255,204,3));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		backgroundPanel = new JPanel();
		backgroundPanel.setBounds(80,60,350,500);
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.WHITE);
		add(backgroundPanel);
		
		JLabel guidText=new JLabel(" 얼마를 제시할까요? (현재 가격 :");
		guidText.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 18));
		addPriceField=new JTextField(20);
		decidePriceButton=new JButton("가격 제시");
		exitButton=new JButton("나가기");
		guidText.setBounds(40, 335, 400, 50);
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
	public static void main(String[] args) {
		new UpdateUserFrame(new UserDTO("엄송현","12345","클라이언트1",555));
	}

}
