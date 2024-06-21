package swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.CardDAO;
import dto.CardDTO;
import dto.UserDTO;
import manager.AuctionManager;
import manager.SocketManager;

public class AuctionPanel extends JPanel {


	private ArrayList<JButton> productList = new ArrayList<>(20);
	private ArrayList<String> productTitleList = new ArrayList<>(20);
	public ArrayList<CardDTO> cardList = new ArrayList(10);
	private JLabel title;
	private CardDTO cardDTO;
	private UserDTO user;
	private AuctionDetailedPanel detailPage;
	private AuctionManager auctionManager;
	private SocketManager socket;

	int state;
	List<JPanel>panel;
	ArrayList<JButton> product = new ArrayList<>(20);
	ArrayList<Integer> serialNum = new ArrayList<>(20);
	private int x = 500;
	private int y = 100;


	public AuctionPanel(List<JPanel> panels, UserDTO user,SocketManager socket) {
		this.panel = panels;
		this.user = user;
		this.socket = socket;
		setInitLayout();
		initListener();
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
	
	public void addAuction() {
		for(int i = 0; i < socket.getAuctionList().size(); i++) {
			CardDAO dao = new CardDAO();
			CardDTO card;
			try {
				card = dao.infoCard(socket.getAuctionList().get(i));
				cardList.add(card);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for(int i = 0; i < cardList.size(); i++) {
			createProduct(cardList.get(i));
		}
		for(int i = 0; i < cardList.size(); i++) {
			detailPage = new AuctionDetailedPanel(cardList.get(i), user);
			panel.add(detailPage);
		}
		
	}

	private void setVisible(int state) {
		for (int i = 0; i < panel.size(); i++) {
			panel.get(i).setVisible(false);
		}
		panel.get(state).setVisible(true);
		this.state = state;
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
		
	}


	private void initListener() {
		// 진행중인 경매 이동
		product.get(0).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(0);
			}
		});
		product.get(1).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(1);
			}
		});
		product.get(2).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(2);
			}
		});
		product.get(3).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(3);
			}
		});
		product.get(4).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(4);
			}
		});
		product.get(5).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(5);
			}
		});
		product.get(6).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(6);
			}
		});
		product.get(7).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(7);
			}
		});
		product.get(8).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(8);
			}
		});
		product.get(9).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(9);
			}
		});
		product.get(10).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(10);
			}
		});

		;
	}

}
