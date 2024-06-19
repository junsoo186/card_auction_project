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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

import dto.CardDTO;


public class AuctionPanel extends JPanel {
	
	private ArrayList<JButton> productList = new ArrayList<>(20);
	private ArrayList<String> productTitleList = new ArrayList<>(20);
	private ArrayList<CardDTO> cardList=new ArrayList(10);
	private JPanel backgroundPanel1;
	private JLabel title;
	private JScrollPane scrollPane;
	
	private AuctionDetailedPanel detailPage;
	
	ArrayList<JButton>product = new ArrayList<>(20);
	ArrayList<Integer>serialNum = new ArrayList<>(20);
	private int x = 500;
	private int y = 100;
	
	private JPanel backgroundPanel;
	
	public AuctionPanel(JPanel backgroundPanel) {
		this.backgroundPanel=backgroundPanel;
		initData();
		setInitLayout();
		//initListener();
	} 

	private void initData() {
		backgroundPanel1=new JPanel();
	}
	
	public void createProduct(CardDTO card) {
		System.out.println(serialNum.size());  // 시리얼 넘버 사이즈로 product버튼 인덱스 번호 지정
		product.get(serialNum.size()).setIcon(new ImageIcon(card.getUrl())); // 유저가 올린 판매카드 이미지 버튼에 삽입 
		cardList.add(serialNum.size(),card); // 카드 정보 불러오기
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
		
		JLabel title=new JLabel("진행 중인 경매"+"("+product.size()+")");
		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(860, 10, 800, 50);
		add(title);
		
	}
	
	
	
	private void ScrollPane(Component view) {
		
	}
	
	private void initListener() {

		// 진행중인 경매 이동
		product.get(0).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println(backgroundPanel);
				setVisible(false);
				detailPage=new AuctionDetailedPanel(new ImageIcon("image/card1.png"));
				backgroundPanel.add(detailPage);
				detailPage.setVisible(true);
			}
			});}

}
