package swing;

import javax.swing.JFrame;

//상품 상세보기 페이지

//사진 출력
//상품 이름, 가격들 목록 
public class DetailedFrame extends JFrame {

	public DetailedFrame() {
		initData();
		setInitLayout();
	}

	private void setInitLayout() {
		setSize(getWidth(),getHeight());
		setLayout(null);
		setVisible(true);

		// 배열로 게시글 생성
		// 출력해야 할 정보: 사진/이름/종료된 날짜/최종 가격
		// 버튼: 비싼 순/저렴한 순 정렬
		
		// 클릭시 PriceDetailedPanel로 이동
	}

	private void initData() {
		
	}
	
}
