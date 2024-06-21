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

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import dto.CardDTO;
	import dto.UserDTO;
	import manager.AuctionManager;

	public class FinishedDetailedPanel extends JPanel {


		private JPanel backgroundPanel;
		private JLabel title;
		private JScrollPane scrollPane;
		private CardDTO card;
		private JButton buyCard;
		private JButton goBackButton;
		private BuyFrame buyFrame;
		private AuctionManager auctionManager;

		public FinishedDetailedPanel(CardDTO card, JPanel backgroundPanel) {
			this.backgroundPanel=backgroundPanel;
			this.card = card;
			initData();
			setInitLayout();
		}

		private void initData() {
			backgroundPanel = new JPanel();
		}

		private void setInitLayout() {
			setSize(1920, 630);
			setLocation(0, 400);
			setLayout(null);
			setBackground(Color.WHITE);
			add(backgroundPanel);

			JLabel title = new JLabel("종료된 카드 보기 : " + card.getName());
			title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
			title.setBounds(860, 10, 800, 50);
			backgroundPanel.add(title);

			JLabel cardId = new JLabel(" 카드 ID : " + card.getId());
			JLabel cardName = new JLabel(" 카드명 : " + card.getName());
			JLabel cardPrice = new JLabel(" 현재 카드 가격 : " + card.getPrice());
			JLabel cardIcon = new JLabel(new ImageIcon(card.getUrl()));
			buyCard = new JButton("판매하기");

			cardId.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
			cardName.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
			cardPrice.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
			cardIcon.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
			buyCard.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));

			cardId.setBounds(900, 100, 400, 100);
			cardName.setBounds(900, 150, 400, 100);
			cardPrice.setBounds(900, 200, 400, 100);
			cardIcon.setBounds(600, 150, 150, 200);
			buyCard.setBounds(900, 300, 200, 70);
			buyCard.setBackground(new Color(255, 204, 3));
			buyCard.setBorderPainted(false);

			backgroundPanel.add(cardId);
			backgroundPanel.add(cardName);
			backgroundPanel.add(cardPrice);
			backgroundPanel.add(cardIcon);
			backgroundPanel.add(buyCard);
			
			setVisible(true);

		}
		

	}
