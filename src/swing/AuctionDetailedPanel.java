package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dto.CardDTO;
import dto.UserDTO;
import manager.AuctionManager;

public class AuctionDetailedPanel extends JPanel {

	private AuctionPanel auctionPanel;
	
	private JPanel backgroundPanel;
	private JPanel backgroundPanel1;
	private JLabel title;
	private JScrollPane scrollPane;
	private CardDTO card;
	private UserDTO user;
	private JButton buyCard;
	private JButton goBackButton;
	private BuyFrame buyFrame;
	private AuctionManager auctionManager;

	public AuctionDetailedPanel(CardDTO card,UserDTO user,JPanel background , AuctionManager auctionManager) {
		this.card = card;
		this.user=user;
		this.auctionManager= auctionManager;
		this.backgroundPanel=backgroundPanel;
		initData();
		setInitLayout();
		initListener();
	}

	private void initData() {
		backgroundPanel1 = new JPanel();
	}

	private void setInitLayout() {
		setSize(1920, 630);
		setLocation(0, 400);
		setLayout(null);
		setBackground(Color.WHITE);
		add(backgroundPanel1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(4, 4, 950, 330);

		JLabel title = new JLabel("경매 상품 상세 보기");
		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(860, 10, 800, 50);
		add(title);
		
		JLabel cardId = new JLabel(" 카드 ID : " + card.getId());
		JLabel cardName = new JLabel(" 카드명 : " + card.getName());
		JLabel cardPrice = new JLabel(" 현재 카드 가격 : " + card.getPrice());
		JLabel cardIcon = new JLabel(new ImageIcon(card.getUrl()));
		buyCard=new JButton("가격 제시하기");
		goBackButton=new JButton("뒤로 가기");
		
		cardId.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		cardName.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		cardPrice.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		cardIcon.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		buyCard.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		goBackButton.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		
		cardId.setBounds(900, 100, 400, 100);
		cardName.setBounds(900, 150, 400, 100);
		cardPrice.setBounds(900, 200, 400, 100);
		cardIcon.setBounds(600, 150, 150, 200);
		buyCard.setBounds(900, 300, 200, 70);
		buyCard.setBackground(new Color(255,204,3));
		buyCard.setBorderPainted(false);
		goBackButton.setBounds(600, 20, 130, 50);
		goBackButton.setBackground(new Color(255,204,3));
		goBackButton.setBorderPainted(false);
		
		add(cardId);
		add(cardName);
		add(cardPrice);
		add(cardIcon);
		add(buyCard);
		add(goBackButton);

	}

	private void initListener() {
		// 진행중인 경매 이동
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}

		});
		buyCard.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				buyFrame=new BuyFrame(card,user);
			}

		});
		goBackButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				auctionPanel=new AuctionPanel(null,user);
				backgroundPanel.add(auctionPanel);
			}

		});
	}

}
