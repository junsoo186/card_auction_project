package swing;

import java.awt.Color;
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

	private MainFrame mconText;

	private ArrayList<JButton> productList = new ArrayList<>();
	private ArrayList<String> productTitleList = new ArrayList<>();
	public ArrayList<CardDTO> cardList = new ArrayList();
	private JLabel title;
	private CardDTO cardDTO;
	private UserDTO user;
	private AuctionDetailedPanel detailPage;
	private AuctionManager auctionManager;
	private SocketManager socket;
	private ArrayList<Integer> hour = new ArrayList<>();
	private ArrayList<Integer> min = new ArrayList<>();
	private ArrayList<Integer> startBid = new ArrayList<>();

	int state;
	List<JPanel> panel;

	ArrayList<JButton> buttons = new ArrayList<>();
	ArrayList<Integer> serialNum = new ArrayList<>();

	// 페이지 버튼
	private JButton nextPage;
	private JButton previousPage;

	// 페이지 변수
	private int page = 1;
	private int pageEnd;

	// 버튼 좌표
	private int x;
	private int y;

	public AuctionPanel(List<JPanel> panels, MainFrame mContext) {
		this.mconText = mContext;
		this.panel = panels;
		this.user = mContext.getUser();
		this.socket = mContext.getSocket();
		setInitLayout();
		initListener();
	}

	public void createProduct(CardDTO card) {
		System.out.println(serialNum.size()); // 시리얼 넘버 사이즈로 product버튼 인덱스 번호 지정
		buttons.get(serialNum.size()).setIcon(new ImageIcon(card.getUrl())); // 유저가 올린 판매카드 이미지 버튼에 삽입
		System.out.println(cardList.get(serialNum.size()));
		serialNum.add(1); // 시리얼 넘버 사이즈도 증가
	}

	// 버튼 10개 생성
	public void productButton() {
		x = 500;
		for (int i = 0; i < 10; i++) {
			if (i < 5) {
				buttons.add(i, new JButton());
				buttons.get(i).setBounds(x + i * 200, 70, 150, 200);
			} else {
				x = -500;
				buttons.add(i, new JButton());
				buttons.get(i).setBounds(x + i * 200, 320, 150, 200);
			}
			add(buttons.get(i));
			setVisible(true);
		}
	}

	public void addAuction() {
		for (int i = 0; i < socket.getAuctionList().size(); i++) {
			CardDAO dao = new CardDAO();
			CardDTO card;
			hour.add(socket.getHour().get(i));
			min.add(socket.getMin().get(i));
			startBid.add(socket.getStartBid().get(i));
			try {
				card = dao.infoCard(socket.getAuctionList().get(i));
				cardList.add(card);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < cardList.size(); i++) {
			System.out.println("경매 카드리스트 사이즈 : " + cardList.size());
			createProduct(cardList.get(i));
		}
	}

	private void setVisible(int state) {
		for (int i = 0; i < panel.size(); i++) {
			panel.get(i).setVisible(false);
		}
		panel.get(state).setVisible(true);
		System.out.println("판넬 선택 : " + panel.get(state));
		this.state = state;
	}

	private void setInitLayout() {
		nextPage = new JButton("다음");
		nextPage.setBounds(1500, 50, 150, 50);
		add(nextPage);
		previousPage = new JButton("이전");
		previousPage.setBounds(300, 50, 150, 50);
		add(previousPage);

		setSize(1920, 630);
		setLocation(0, 400);
		setLayout(null);
		setBackground(Color.WHITE);

		title = new JLabel("진행 중인 경매" + "(" + cardList.size() + ")");
		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(860, 10, 800, 50);
		add(title);
		productButton();
	}

	public void removeData() {
		cardList.removeAll(cardList);
		serialNum.removeAll(serialNum);
		startBid.removeAll(startBid);
		hour.removeAll(hour);
		min.removeAll(min);
	}

	private void initListener() {
		// 진행중인 경매 이동
		buttons.get(0).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(0);
			}
		});
		for (int i = 0; i < buttons.size(); i++) {
			int num = i;
			buttons.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println(cardList.size());
					detailPage = new AuctionDetailedPanel(cardList.get(num), user,auctionManager,hour.get(num),min.get(num),startBid.get(num),mconText,num);
					panel.add(detailPage);
					mconText.addPanel(7);
					setVisible(7);
				}
			});
		}
	}

}
