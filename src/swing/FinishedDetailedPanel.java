package swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dto.CardDTO;
import lombok.Data;
import manager.AuctionManager;

@Data
public class FinishedDetailedPanel extends JPanel {

	private JPanel backgroundPanel;
	private JScrollPane scrollPane;
	private CardDTO cardDTO;
	private BuyFrame buyFrame;
	private AuctionManager auctionManager;
	private JLabel title = new JLabel();
	private JLabel cardId = new JLabel();
	private JLabel cardName = new JLabel();
	private JLabel cardPrice = new JLabel();
	private JLabel cardIcon = new JLabel();

	public FinishedDetailedPanel(MainFrame mContext) {
		initData();
		setInitLayout();
	}

	private void initData() {
	}

	private void setInitLayout() {
		setSize(1920, 630);
		setLocation(0, 400);
		setLayout(null);
		setBackground(Color.WHITE);

		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(860, 10, 800, 50);
		add(title);

		cardId.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		cardName.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		cardPrice.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		cardIcon.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));

		cardId.setBounds(900, 100, 400, 100);
		cardName.setBounds(900, 150, 400, 100);
		cardPrice.setBounds(900, 200, 400, 100);
		cardIcon.setBounds(600, 150, 120, 167);

		add(cardId);
		add(cardName);
		add(cardPrice);
		add(cardIcon);

		setVisible(true);

	}

	public void clickEndDetailButton() {
		title.setText(" 종료된 카드 보기 : " + cardDTO.getName());
		cardId.setText(" 카드 ID : " + cardDTO.getId());
		cardName.setText(" 카드명 : " + cardDTO.getName());
		cardPrice.setText(" 판매된 카드 가격 : " + cardDTO.getPrice());
		cardIcon.setIcon(new ImageIcon(cardDTO.getUrl()));
	}

}
