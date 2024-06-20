package swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

import dto.CardDTO;
import dto.UserDTO;


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
	public ArrayList<CardDTO> cardList=new ArrayList(10);
	private JPanel backgroundPanel1;
	private JLabel title;
	private UserDTO user;
	private AuctionDetailedPanel detailPage;
	
	
	ArrayList<JButton>product = new ArrayList<>(20);
	ArrayList<Integer>serialNum = new ArrayList<>(20);
	private int x = 500;
	private int y = 100;
	
	private JPanel backgroundPanel;
	
	public AuctionPanel(JPanel backgroundPanel,UserDTO user) {
		this.backgroundPanel=backgroundPanel;
		this.user=user;
		initData();
		setInitLayout();
		initListener();
	} 

	private void initData() {
		backgroundPanel1=new JPanel();
		
		cardList.add(new CardDTO(0,"image/card1.png","포켓몬스터 나오하 카드",1000));
		cardList.add(new CardDTO(1,"image/card2.png","포켓몬스터 개굴닌자 카드",2000));
		cardList.add(new CardDTO(2,"image/card3.jpg","포켓몬스터 이상해꽃 카드",4000));
		cardList.add(new CardDTO(3,"image/card4.jpg","포켓몬스터 라이츄 카드",7000));
	}
	
	public void createProduct(CardDTO card) {
		System.out.println(serialNum.size());  // 시리얼 넘버 사이즈로 product버튼 인덱스 번호 지정
		product.get(serialNum.size()).setIcon(new ImageIcon(card.getUrl())); // 유저가 올린 판매카드 이미지 버튼에 삽입 
		//cardList.add(serialNum.size(),card); // 카드 정보 불러오기
		System.out.println(cardList.get(serialNum.size()));
		serialNum.add(1); // 시리얼 넘버 사이즈도 증가 
	}
	
	public void ProductButton() {
		// 처음 생성 될때 8개 버튼 생성
		for(int i = 0; i < 17; i++) {
			product.add(i, new JButton());
			product.get(i).setBounds(x,y,150,200);
			
			if((i/4)>=1 && i%4==0) {
				x=500;
				y+=300;
			} else {
				x+=200;
			}
			add(product.get(i));
			setVisible(true);
		}
	}
	
	private void setInitLayout() {
		setSize(1920,630);
		setLocation(0,400);
		setLayout(null);
		setBackground(Color.WHITE);
		
		JLabel title=new JLabel("진행 중인 경매"+"("+cardList.size()+")");
		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(860, 10, 800, 50);
		add(title);
		ProductButton();
		createProduct(cardList.get(0));
		createProduct(cardList.get(1));
		createProduct(cardList.get(2));
		createProduct(cardList.get(3));
		
	}
	
	
	
	private void ScrollPane(Component view) {
		
	}
	
	private void initListener() {

		// 진행중인 경매 이동
		product.get(0).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				detailPage=new AuctionDetailedPanel(cardList.get(0),user,backgroundPanel);
				backgroundPanel.add(detailPage);
				detailPage.setVisible(true);
			}
			});
		product.get(1).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				detailPage=new AuctionDetailedPanel(cardList.get(1),user,backgroundPanel);
				backgroundPanel.add(detailPage);
				detailPage.setVisible(true);
			}
			});
		product.get(2).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				detailPage=new AuctionDetailedPanel(cardList.get(2),user,backgroundPanel);
				backgroundPanel.add(detailPage);
				detailPage.setVisible(true);
			}
			});
		product.get(3).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				detailPage=new AuctionDetailedPanel(cardList.get(3),user,backgroundPanel);
				backgroundPanel.add(detailPage);
				detailPage.setVisible(true);
			}
			});
		
		
		;}

}
