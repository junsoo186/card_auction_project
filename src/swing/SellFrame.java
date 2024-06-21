package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;

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
import manager.SocketManager;

public class SellFrame extends JFrame{
		
		private CardDTO card;
		private UserDTO user;
		
		private JPanel backgroundPanel;
		private JTextField addPriceField;
		private JButton exitButton;
		private JButton decidePriceButton;
		private SocketManager socket;
		
		
		public SellFrame(CardDTO card,UserDTO user,SocketManager socket) {
			System.out.println("가격제시 생성");
			this.socket = socket;
			this.card=card;
			this.user=user;
			setInitLayout();
			initListener();
		}

		private void setInitLayout() {
			setTitle("[카드 판매하기]");
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
			
			JLabel cardTitle=new JLabel(card.getName());
			JLabel guidText=new JLabel("시작 가격을 설정해주세요 (평균 시세 :"+card.getPrice()+")");
			cardTitle.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 30));
			guidText.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 18));
			addPriceField=new JTextField(20);
			decidePriceButton=new JButton("경매 시작");
			exitButton=new JButton("나가기");
			cardTitle.setBounds(55, 40, 400, 50);
			guidText.setBounds(30, 335, 400, 50);
			addPriceField.setBounds(80, 385, 200, 30);
			decidePriceButton.setBounds(80, 425, 90, 30);
			exitButton.setBounds(185, 425, 90, 30);
			
			backgroundPanel.add(cardTitle);
			backgroundPanel.add(guidText);
			backgroundPanel.add(addPriceField);
			backgroundPanel.add(decidePriceButton);
			backgroundPanel.add(exitButton);
			
			Icon cardImage=new ImageIcon(card.getUrl());
			JLabel cardLabel=new JLabel();
			cardLabel.setIcon(cardImage);
			cardLabel.setBounds(100, 110, 150, 210);
			backgroundPanel.add(cardLabel);
			
			setVisible(true);	
		}

		private void initListener() {
			decidePriceButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					socket.sendOrder("auction#" + card.getId());
					System.out.println("서버에 카드 아이디 전송");
					dispose();
				}

			});
			exitButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					JOptionPane.showMessageDialog(null,"입찰을 종료합니다");
					dispose();
				}

			});
		}
		public static void main(String[] args) {
			new BuyFrame(new CardDTO(0,"image/card1.png","포켓몬스터 나오하 카드",1000), new UserDTO("엄송현","12345","클라이언트1",555));
		}

	}


