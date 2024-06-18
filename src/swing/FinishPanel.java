package swing;

import javax.swing.JPanel;

// 경매 종료 정보 패널
public class FinishPanel extends JPanel {
	public FinishPanel() {
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
