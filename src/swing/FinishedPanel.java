package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dto.CardDTO;
import dto.UserDTO;

public class FinishedPanel extends JPanel {

	private ArrayList<JButton> productList = new ArrayList<>(12);
	private ArrayList<String> productTitleList = new ArrayList<>(12);
	public ArrayList<CardDTO> cardList = new ArrayList(10);
	private JPanel backgroundPanel;
	private JLabel title;
	private JScrollPane scrollPane;
	private UserDTO user;
	private MainFrame mContext;

	private FinishedDetailedPanel finishedDetailedPanel;

	ArrayList<JButton> product = new ArrayList<>(8);
	ArrayList<Integer> serialNum = new ArrayList<>(8);
	private int x = 500;
	private int y = 100;

	public FinishedPanel(MainFrame mContext) {
		this.user = mContext.getUser();
		this.mContext = mContext;
		initData();
		setInitLayout();
		initListener();
	}

	private void initData() {
		backgroundPanel = mContext.getBackgroundPanel();
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
		for (int i = 1; i < 6; i++) {
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
		add(backgroundPanel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(4, 4, 950, 330);
		ProductButton();

		cardList.add(new CardDTO(0, "image/card6.png", "[포켓몬스터] 루카리오 카드", 1000));
		cardList.add(new CardDTO(1, "image/card2.png", "[포켓몬스터] 개굴닌자 카드", 2000));
		cardList.add(new CardDTO(2, "image/card7.jpg", "[포켓몬스터] 레시라무 카드", 4000));
		cardList.add(new CardDTO(3, "image/card4.jpg", "[포켓몬스터] 라이츄 카드", 7000));
		cardList.add(new CardDTO(4, "image/card5.jpg", "[포켓몬스터] 리자몽 카드", 50000));

		createProduct(cardList.get(0));
		createProduct(cardList.get(1));
		createProduct(cardList.get(2));
		createProduct(cardList.get(3));
		createProduct(cardList.get(4));

		JLabel title = new JLabel("종료된 경매" + "(" + product.size() + ")");
		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(860, 10, 800, 50);
		add(title);

	}

	private void initListener() {

		// 진행중인 경매 이동
		product.get(0).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				finishedDetailedPanel = new FinishedDetailedPanel(cardList.get(0));
				backgroundPanel.add(finishedDetailedPanel);
				finishedDetailedPanel.setVisible(true);
			}
		});
		product.get(1).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				finishedDetailedPanel = new FinishedDetailedPanel(cardList.get(1));
				backgroundPanel.add(finishedDetailedPanel);
				finishedDetailedPanel.setVisible(true);
			}
		});
		product.get(2).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				finishedDetailedPanel = new FinishedDetailedPanel(cardList.get(2));
				backgroundPanel.add(finishedDetailedPanel);
				finishedDetailedPanel.setVisible(true);
			}
		});
		product.get(3).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				finishedDetailedPanel = new FinishedDetailedPanel(cardList.get(3));
				backgroundPanel.add(finishedDetailedPanel);
				finishedDetailedPanel.setVisible(true);
			}
		});
		product.get(4).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				finishedDetailedPanel = new FinishedDetailedPanel(cardList.get(4));
				backgroundPanel.add(finishedDetailedPanel);
				finishedDetailedPanel.setVisible(true);
			}
		});
	}

}