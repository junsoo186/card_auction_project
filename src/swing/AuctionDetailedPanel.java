package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dao.AuctionDAO;
import dao.UserDAO;
import dto.AuctionDTO;
import dto.CardDTO;
import dto.UserDTO;
import lombok.Data;
import manager.AuctionManager;

@Data
public class AuctionDetailedPanel extends JPanel {

	private MainFrame mContext;

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

	private JLabel cardId;
	private JLabel cardName;
	private JLabel cardPrice;
	private JLabel cardBid;
	private JLabel cardIcon;
	private JLabel endTime;

	// 옥션 매니저
	private AuctionManager auctionManager;
	private boolean flag = true;
	private SellProductPanel sell;
	private int time; // 시간
	private MainFrame mconText;
	private int bid;
	private int page;
	private String seller;

	public AuctionDetailedPanel(CardDTO card, UserDTO user, AuctionManager auctionManager, int hour, int min,
			int startbid, MainFrame mconText, int num ,String seller) {
		this.page = num;
		this.mconText = mconText;
		this.bid = startbid;
		this.card = card;
		this.user = user;
		this.seller = seller;
		auctionManager = new AuctionManager(bid, user, card, hour, min);
		this.auctionManager = auctionManager;
		sell = new SellProductPanel(user);
		initData();
		setInitLayout();
		initListener();
		System.out.println("경매 선택됨 : " + card.getUrl());
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

		cardId = new JLabel(" 카드 ID : " + card.getId());
		cardIcon = new JLabel(new ImageIcon(card.getUrl()));
		cardName = new JLabel(" 카드명 : " + card.getName());
		cardPrice = new JLabel(" 시작 가격 : " + auctionManager.getStartBid());
		cardBid = new JLabel(" 현재 비드 가격 : " + mconText.socket.getHighBid().get(page));
		endTime = new JLabel("종료시간" + auctionManager.getCurrent_time());

		buyCard = new JButton("가격 제시하기");
		goBackButton = new JButton("뒤로 가기");

		cardId.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		cardName.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		cardPrice.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		cardBid.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		cardIcon.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		endTime.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		buyCard.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		goBackButton.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));

		cardId.setBounds(900, 100, 400, 100);
		cardName.setBounds(900, 150, 400, 100);
		cardPrice.setBounds(900, 200, 400, 100);
		cardBid.setBounds(900, 250, 400, 100);
		cardIcon.setBounds(600, 150, 150, 200);
		endTime.setBounds(900, 0, 400, 200);
		buyCard.setBounds(900, 350, 200, 70);
		buyCard.setBackground(new Color(255, 204, 3));
		buyCard.setBorderPainted(false);
		goBackButton.setBounds(600, 20, 130, 50);
		goBackButton.setBackground(new Color(255, 204, 3));
		goBackButton.setBorderPainted(false);

		add(cardBid);
		add(endTime);
		add(cardId);
		add(cardName);
		add(cardPrice);
		add(cardIcon);
		add(buyCard);
		add(goBackButton);
// 시간 확인 쓰레드
		new Thread(() -> {
			while (flag) {
				String m = Long.toString(auctionManager.getCurrent_time());
				int s = Integer.valueOf(m);
				if (time != s) {
					time = s;
					endTime.setText("남은시간 :" + time + "초");
					if (time <= 0) {
						endTime.setText("경매 종료");
						endTime.setForeground(Color.RED);
						if(mconText.socket.getHighBid().get(page) == auctionManager.getStartBid()){
							mconText.socket.deleteData(page);
							mconText.getAuctionPanel().buttons.get(page).setIcon(null);
							flag = false;
							System.out.println("유찰되었습니다!!!!!");
							if(seller.equals(user.getName())) {
								mconText.socket.sendOrder("remove#" + page);
								mconText.socket.sendOrder("return#" + seller + "#" + card.getId());
								System.out.println("돌려받는 유저 이름 : " + seller);
							}
							break;
						} else {
							addDto();
							mconText.socket.deleteData(page);
							mconText.getAuctionPanel().buttons.get(page).setIcon(null);
							buyCard.setVisible(false);
							flag = false;
							break;
						}
					}
				}
				if (auctionManager.getHighbid() != mconText.socket.getHighBid().get(page)) {
					auctionManager.setHighbid(mconText.socket.getHighBid().get(page));
					cardBid.setText(" 현재 비드 가격 : " + auctionManager.getHighbid());
					System.out.println("가격 변경됨 : " + mconText.socket.getHighBid().get(page));
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}).start();

	}

	private void addDto() {
		if(user.getName().equals(mconText.socket.getBidUser().get(page))) {
		System.out.println("현재 유저 네임 : " + user.getName());
		System.out.println("최고비드 유저 네임 : " + mconText.socket.getBidUser().get(page));
		AuctionDTO auction = new AuctionDTO();
		auction.setEndDate(LocalDateTime.now().toString());
		auction.setStartDate(auctionManager.getStartTime());
		auction.setBidPrice(mconText.socket.getHighBid().get(page));
		auction.setCardId(card.getId());
		auction.setName(mconText.socket.getBidUser().get(page));
		mconText.socket.sendOrder("endAuctionDB#" + auction.getEndDate() + "#" + auction.getStartDate() + "#" 
		+ auction.getBidPrice() + "#" + auction.getCardId() + "#" + auction.getName() + "#" + 
		mconText.socket.getBidUser().get(page) + "#" + page);
		mconText.socket.sendOrder("remove#" + page);
		System.out.println("addDto 발동 되버림");
		}
	}

	private void initListener() {
		// 진행중인 경매 이동
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}

		});
		buyCard.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				buyFrame = new BuyFrame(card, user, mconText, auctionManager.getHighbid(), page);
			}

		});
		goBackButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mconText.setVisible(0);
			}
		});
	}

}