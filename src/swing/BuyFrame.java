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

import dto.CardDTO;
import dto.UserDTO;

public class BuyFrame extends JFrame {

	private CardDTO card;
	private UserDTO user;

	private JPanel backgroundPanel;
	private JTextField addPriceField;
	private JButton exitButton;
	private JButton decidePriceButton;
	private MainFrame mconText;
	private int price;
	private int page;
	
	public BuyFrame(CardDTO card,UserDTO user,MainFrame mconText , int price , int page) {
		this.page = page;
		this.price = price;
		this.mconText = mconText;
		this.card=card;
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
		getContentPane().setBackground(new Color(255, 204, 3));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage("image/파비콘2.png"));
		backgroundPanel = new JPanel();
		backgroundPanel.setBounds(80, 60, 350, 500);
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.WHITE);
		add(backgroundPanel);

		JLabel cardTitle = new JLabel(card.getName());
		JLabel guidText = new JLabel(" 얼마를 제시할까요? (현재 가격 :" + card.getPrice() + ")");
		cardTitle.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 30));
		guidText.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 18));
		addPriceField = new JTextField(20);
		decidePriceButton = new JButton("가격 제시");
		exitButton = new JButton("나가기");
		cardTitle.setBounds(55, 40, 400, 50);
		guidText.setBounds(40, 335, 400, 50);
		addPriceField.setBounds(80, 385, 200, 30);
		decidePriceButton.setBounds(80, 425, 90, 30);
		exitButton.setBounds(185, 425, 90, 30);

		backgroundPanel.add(cardTitle);
		backgroundPanel.add(guidText);
		backgroundPanel.add(addPriceField);
		backgroundPanel.add(decidePriceButton);
		backgroundPanel.add(exitButton);

		Icon cardImage = new ImageIcon(card.getUrl());
		JLabel cardLabel = new JLabel();
		cardLabel.setIcon(cardImage);
		cardLabel.setBounds(100, 110, 120, 167);
		backgroundPanel.add(cardLabel);

		setVisible(true);
	}

	private void initListener() {
		decidePriceButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int checkPrice=Integer.parseInt(addPriceField.getText());
				if(checkPrice>user.getPoint()) {
					JOptionPane.showMessageDialog(null,"소지중인 포인트 이상을 제시할 수 없습니다.");
				} else if(checkPrice <= price){
					JOptionPane.showMessageDialog(null,"현재 가격보다 높은 금액을 제시해주세요.");
				} else {
					mconText.socket.sendOrder("bid#" + checkPrice + "#" + page + "#" + user.getName());
					JOptionPane.showMessageDialog(null,checkPrice + "원을 제시했습니다.");
				} 
				dispose();
			}

		});
		exitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "입찰을 종료합니다");
				dispose();
			}

		});
	}
}

