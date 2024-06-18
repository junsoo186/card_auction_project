package swing;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// 현재 시세가 패널 (avg(price) where=?)
public class PricePanel extends JPanel {
	public PricePanel() {
		initData();
		setInitLayout();
	}

	private void setInitLayout() {
		setSize(getWidth(),getHeight());
		setLayout(null);
		setVisible(true);

		// 배열로 게시글 생성
		// 출력해야 할 정보: 사진/이름
		// 버튼: 비싼 순/저렴한 순 정렬
		
		// 클릭시 PriceDetailedPanel로 이동
	}

	private void initData() {
		
	}
}
