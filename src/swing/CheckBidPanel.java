package swing;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class CheckBidPanel extends JPanel {
	
	private ArrayList<JButton> productList = new ArrayList<>(12);
	private ArrayList<String> productTitleList = new ArrayList<>(12);
	private JPanel backgroundPanel;
	private JLabel title;
	private JScrollPane scrollPane;
	
	ArrayList<JButton>product = new ArrayList<>(8);
	ArrayList<Integer>serialNum = new ArrayList<>(8);
	private int x = 500;
	private int y = 100;
	
	public CheckBidPanel() {
		initData();
		setInitLayout();
	} 

	private void initData() {
		backgroundPanel=new JPanel();
	}
	
	public void createProduct(String image) {
		System.out.println(serialNum.size());  // 시리얼 넘버 사이즈로 product버튼 인덱스 번호 지정
		product.get(serialNum.size()).setIcon(new ImageIcon(image)); // 유저가 올린 판매카드 이미지 버튼에 삽입 
		serialNum.add(1); // 시리얼 넘버 사이즈도 증가 
	}
	
	public void ProductButton() {
		// 처음 생성 될때 8개 버튼 생성
		for(int i = 0; i < 4; i++) {
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
		add(backgroundPanel);
		
		scrollPane=new JScrollPane();	
		scrollPane.setBounds(4,4,950,330);
		ProductButton();
		
		createProduct("image/card1.png");
		createProduct("image/card2.png");
		createProduct("image/card3.jpg");
		createProduct("image/card4.jpg");
		
		JLabel title=new JLabel("시세 확인하기"+"("+product.size()+")");
		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(860, 10, 800, 50);
		add(title);
		
	}

}
