package swing;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.CardDTO;
import dto.UserDTO;
import manager.SocketManager;

public class FinishedPanel extends JPanel {

	private MainFrame mContext;
	private SocketManager socket;
	private ArrayList<JButton> productList = new ArrayList<>(12);
	private ArrayList<String> productTitleList = new ArrayList<>(12);
	public ArrayList<CardDTO> endAuctionList = new ArrayList(10);
	private JPanel backgroundPanel;
	private JLabel title;
	private UserDTO user;

	private FinishedDetailedPanel finishedDetailedPanel;

	ArrayList<JButton> buttons = new ArrayList<>(8);
	ArrayList<Integer> serialNum = new ArrayList<>(8);
	private int x = 500;
	private int y = 100;

	public FinishedPanel(MainFrame mContext) {
		this.mContext = mContext;
		this.user = mContext.getUser();
		this.socket = mContext.getSocket();
		socket.sendOrder("endAuctionList");
		initData();
		setInitLayout();
		initListener();
	}

	private void initData() {
		backgroundPanel = mContext.getBackgroundPanel();
	}

	public void createProduct(CardDTO card) {
		System.out.println(serialNum.size()); // 시리얼 넘버 사이즈로 product버튼 인덱스 번호 지정
		buttons.get(serialNum.size()).setIcon(new ImageIcon(card.getUrl())); // 유저가 올린 판매카드 이미지 버튼에 삽입
		// cardList.add(serialNum.size(),card); // 카드 정보 불러오기
		System.out.println(endAuctionList.get(serialNum.size()));
		serialNum.add(1); // 시리얼 넘버 사이즈도 증가
	}

	// 버튼 10개 생성
	public void productButton() {
		x = 500;
		y = 200;
		for (int i = 0; i < 10; i++) {
			if (i < 5) {
				buttons.add(i, new JButton());
				buttons.get(i).setBounds(x + i * y, 50, 150, 200);
			} else {
				x = -500;
				buttons.add(i, new JButton());
				buttons.get(i).setBounds(x + i * y, 300, 150, 200);
			}
			add(buttons.get(i));
			setVisible(true);
		}
	}
//	public void ProductButton() {
//		// 처음 생성 될때 8개 버튼 생성
//		for (int i = 1; i < 6; i++) {
//			product.add(i - 1, new JButton());
//			product.get(i - 1).setBounds(x, y, 150, 200);
//
//			if ((i / 5) >= 1 && i % 5 == 0) {
//				x = 500;
//				y += 220;
//			} else {
//				x += 200;
//			}
//			add(product.get(i - 1));
//			setVisible(true);
//		}
//	}

	private void setInitLayout() {
		setSize(1920, 630);
		setLocation(0, 400);
		setLayout(null);
		setBackground(Color.WHITE);
		add(backgroundPanel);

		productButton();

		JLabel title = new JLabel("종료된 경매" + "(" + buttons.size() + ")");
		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(860, 10, 800, 50);
		add(title);

	}

	private void initListener() {

	}

}