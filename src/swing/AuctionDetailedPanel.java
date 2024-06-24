package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dao.AuctionDAO;
import dao.UserDAO;
import dto.AuctionDTO;
import dto.CardDTO;
import dto.UserDTO;
import manager.AuctionManager;

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
	boolean flag = true;
	SellProductPanel sell;
	String time; // 시간
	MainFrame mconText;
	int bid;
	int page;

	public AuctionDetailedPanel(CardDTO card, UserDTO user, AuctionManager auctionManager,int hour , int min , int startbid, MainFrame mconText,int num) {
		this.page = num;
		this.mconText =mconText;
		this.bid = startbid;
		this.card = card;
		this.user = user;
		auctionManager = new AuctionManager(bid, user, card, hour, min);
		this.auctionManager = auctionManager;
		sell = new SellProductPanel(user);
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

		cardId = new JLabel(" 카드 ID : " + card.getId());
		cardIcon = new JLabel(new ImageIcon(card.getUrl()));
		cardName = new JLabel(" 카드명 : " + card.getName());
		cardPrice = new JLabel(" 시작 가격 : " + auctionManager.getStartBid());
		cardBid = new JLabel(" 현재 비드 가격 : " + auctionManager.getHighbid());
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
				String s = Long.toString(auctionManager.getCurrent_time());
				if (!s.equals(time)) {
					time = s;
					System.out.println("남은시간 dd : " + time);
					endTime.setText("남은시간 :" + time + "초");
					if (time.equals("0")) {
						endTime.setText("경매 종료");
						endTime.setForeground(Color.RED);
						addDto();
						buyCard.setVisible(false);
						flag = false;
					}
				}
				if(auctionManager.getHighbid() != mconText.socket.getHighBid().get(page)) {
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
		AuctionDTO auction = new AuctionDTO();
		auction.setEndDate(LocalDateTime.now().toString());
		auction.setStartDate(auctionManager.getStartTime());
		auction.setBidPrice(auctionManager.getHighbid());
		auction.setCardId(card.getId());
		auction.setName(mconText.socket.getBidUser().get(page));
		System.out.println("데이터 보냄 (경매종료)");
		AuctionDAO dao = new AuctionDAO();
		UserDAO dao2 = new UserDAO();
		try {
			dao.addAuction(auction);
			dao2.subtractPoint(mconText.socket.getBidUser().get(page), auctionManager.getHighbid());
			System.out.println("구매한 사람 : " + mconText.socket.getBidUser().get(page));
			System.out.println("차감되는 포인트 : " + auctionManager.getHighbid());
		} catch (SQLException e) {
			e.printStackTrace();
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
				buyFrame = new BuyFrame(card, user, mconText,auctionManager.getHighbid(),page);
			}

		});
		goBackButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				backgroundPanel.add(auctionPanel);
			}
		});
	}

}