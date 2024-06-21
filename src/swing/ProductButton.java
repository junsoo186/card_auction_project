package swing;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ProductButton {

	ArrayList<JButton>product = new ArrayList<>(8);
	ArrayList<Integer>serialNum = new ArrayList<>(8);
	private AuctionPanel mconText;
	private int x = 300;
	private int y = 300;
	
	public ProductButton(AuctionPanel mconText) {
		// 처음 생성 될때 8개 버튼 생성
		this.mconText = mconText;
		for(int i = 0; i < 8; i++) {
			product.add(i, new JButton());
			product.get(i).setBounds(x,y,200,550);
			if(i == 3) {
				y += 100;
				x = 300;
			} else {
				x += 100;
			}
		}
	}
	
	public void createProduct(String image) {
		System.out.println(serialNum.size());  // 시리얼 넘버 사이즈로 product버튼 인덱스 번호 지정
		product.get(serialNum.size()).setIcon(new ImageIcon(image)); // 유저가 올린 판매카드 이미지 버튼에 삽입 
		mconText.add(product.get(serialNum.size())); // 쇼핑몰 페이지에 이미지 추가
		serialNum.add(1); // 시리얼 넘버 사이즈도 증가 
	}
}
