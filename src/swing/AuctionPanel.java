package swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.CardDTO;
import dto.UserDTO;
import manager.AuctionManager;

public class AuctionPanel extends JPanel {

	public ArrayList<CardDTO> getCardList() {
		return cardList;
	}

	public void setCardList(ArrayList<CardDTO> cardList) {
		this.cardList = cardList;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	private ArrayList<JButton> productList = new ArrayList<>(20);
	private ArrayList<String> productTitleList = new ArrayList<>(20);
	public ArrayList<CardDTO> cardList = new ArrayList(10);
	private JLabel title;
	private CardDTO cardDTO;
	private UserDTO user;
	private AuctionDetailedPanel detailPage;
	private AuctionManager auctionManager;

	ArrayList<JButton> product = new ArrayList<>(20);
	ArrayList<Integer> serialNum = new ArrayList<>(20);
	private int x = 500;
	private int y = 100;

	private JPanel backgroundPanel;

	public AuctionPanel(JPanel backgroundPanel, UserDTO user) {
		this.backgroundPanel = backgroundPanel;
		this.user = user;
		initData();
		setInitLayout();
		initListener();
	}

	private void initData() {
		cardList.add(new CardDTO(0, "image/card1.png", "[포켓몬스터] 나오하 카드", 1000));
		cardList.add(new CardDTO(1, "image/card2.png", "[포켓몬스터] 개굴닌자 카드", 2000));
		cardList.add(new CardDTO(2, "image/card3.jpg", "[포켓몬스터] 이상해꽃 카드", 4000));
		cardList.add(new CardDTO(3, "image/card4.jpg", "[포켓몬스터] 라이츄 카드", 7000));
		cardList.add(new CardDTO(4, "image/card5.jpg", "[포켓몬스터] 리자몽 카드", 50000));
		cardList.add(new CardDTO(5, "image/card6.png", "[포켓몬스터] 루카리오 카드", 8000));
		cardList.add(new CardDTO(6, "image/card7.jpg", "[포켓몬스터] 레시라무 카드", 20000));
		cardList.add(new CardDTO(7, "image/card8.jpg", "[포켓몬스터] 마자용 카드", 30000));
	}

	public void createProduct(CardDTO card) {
		System.out.println(serialNum.size()); // 시리얼 넘버 사이즈로 product버튼 인덱스 번호 지정
		product.get(serialNum.size()).setIcon(new ImageIcon(card.getUrl())); // 유저가 올린 판매카드 이미지 버튼에 삽입
		// cardList.add(serialNum.size(),card); // 카드 정보 불러오기
		System.out.println(cardList.get(serialNum.size()));
		serialNum.add(1); // 시리얼 넘버 사이즈도 증가
	}

	public void ProductButton() {
		// 처음 생성 될때 8개 버튼 생성
		for (int i = 1; i < 12; i++) {
			product.add(i - 1, new JButton());
			product.get(i - 1).setBounds(x, y, 150, 200);

			if ((i / 5) >= 1 && i % 5 == 0) {
				x = 500;
				y += 220;
			} else {
				x += 200;
			}
			add(product.get(i - 1));
			setVisible(true);
		}
	}

	private void setInitLayout() {
		setSize(1920, 630);
		setLocation(0, 400);
		setLayout(null);
		setBackground(Color.WHITE);

		JLabel title = new JLabel("진행 중인 경매" + "(" + cardList.size() + ")");
		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(860, 10, 800, 50);
		add(title);
		ProductButton();
		createProduct(cardList.get(0));
		createProduct(cardList.get(1));
		createProduct(cardList.get(2));
		createProduct(cardList.get(3));
		createProduct(cardList.get(4));
		createProduct(cardList.get(5));
		createProduct(cardList.get(6));
		createProduct(cardList.get(7));
	}

	private void initListener() {
		// 진행중인 경매 이동
		product.get(0).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				detailPage = new AuctionDetailedPanel(cardList.get(0), user, backgroundPanel);
				backgroundPanel.add(detailPage);
				detailPage.setVisible(true);
			}
		});
		product.get(1).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				detailPage = new AuctionDetailedPanel(cardList.get(1), user, backgroundPanel);
				backgroundPanel.add(detailPage);
				detailPage.setVisible(true);
			}
		});
		product.get(2).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				detailPage = new AuctionDetailedPanel(cardList.get(2), user, backgroundPanel);
				backgroundPanel.add(detailPage);
				detailPage.setVisible(true);
			}
		});
		product.get(3).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				detailPage = new AuctionDetailedPanel(cardList.get(3), user, backgroundPanel);
				backgroundPanel.add(detailPage);
				detailPage.setVisible(true);
			}
		});
		product.get(4).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				detailPage = new AuctionDetailedPanel(cardList.get(4), user, backgroundPanel);
				backgroundPanel.add(detailPage);
				detailPage.setVisible(true);
			}
		});
		product.get(5).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				detailPage = new AuctionDetailedPanel(cardList.get(5), user, backgroundPanel);
				backgroundPanel.add(detailPage);
				detailPage.setVisible(true);
			}
		});
		product.get(6).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				detailPage = new AuctionDetailedPanel(cardList.get(6), user, backgroundPanel);
				backgroundPanel.add(detailPage);
				detailPage.setVisible(true);
			}
		});
		product.get(7).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				detailPage = new AuctionDetailedPanel(cardList.get(7), user, backgroundPanel);
				backgroundPanel.add(detailPage);
				detailPage.setVisible(true);
			}
		});
		product.get(8).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				detailPage = new AuctionDetailedPanel(cardList.get(8), user, backgroundPanel);
				backgroundPanel.add(detailPage);
				detailPage.setVisible(true);
			}
		});
		product.get(9).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				detailPage = new AuctionDetailedPanel(cardList.get(9), user, backgroundPanel);
				backgroundPanel.add(detailPage);
				detailPage.setVisible(true);
			}
		});
		product.get(10).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				detailPage = new AuctionDetailedPanel(cardList.get(10), user, backgroundPanel);
				backgroundPanel.add(detailPage);
				detailPage.setVisible(true);
			}
		});

		;
	}

}
