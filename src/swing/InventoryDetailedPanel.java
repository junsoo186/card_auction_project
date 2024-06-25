package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.CardDTO;
import dto.UserDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import manager.AuctionManager;
import manager.SocketManager;

@Getter
@Setter
public class InventoryDetailedPanel extends JPanel {

	private JPanel backgroundPanel1;
	private JLabel title = new JLabel();
	private JLabel cardId = new JLabel();
	private JLabel cardName = new JLabel();
	private JLabel cardPrice = new JLabel();
	private JLabel cardIcon = new JLabel();

	private CardDTO cardDTO = new CardDTO();
	private UserDTO userDTO;

	private JButton sellCard;
	private JButton goBackButton;
	private BuyFrame buyFrame;
	private AuctionManager auctionManager;
	private SocketManager socket;
	private MainFrame mContext;

	public InventoryDetailedPanel(MainFrame mContext) {
		this.userDTO = mContext.getUser();
		this.socket = mContext.getSocket();
		this.mContext = mContext;
		initData();
		setInitLayout();
	}

	private void initData() {
		backgroundPanel1 = new JPanel();
	}

	private void setInitLayout() {
		setSize(1920, 630);
		setLocation(0, 400);
		setLayout(null);
		setBackground(Color.WHITE);
		add(backgroundPanel1);

		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(860, 10, 800, 50);

		cardId.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		cardName.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		cardPrice.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		cardIcon.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));

		cardId.setBounds(900, 100, 400, 100);
		cardName.setBounds(900, 150, 400, 100);
		cardPrice.setBounds(900, 200, 400, 100);
		cardIcon.setBounds(600, 150, 120, 167);

		sellCard = new JButton("판매하기");
		sellCard.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 24));
		sellCard.setBounds(900, 300, 200, 70);
		sellCard.setBackground(new Color(255, 204, 3));
		sellCard.setBorderPainted(false);

		sellCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SellFrame(userDTO, cardDTO, socket , mContext);
			}
		});

		add(title);
		add(cardId);
		add(cardName);
		add(cardPrice);
		add(cardIcon);
		add(sellCard);
	}

	public void clickDetailedButton() {
		title.setText("내 카드 보기 : " + cardDTO.getName());
		cardId.setText(" 카드 ID : " + cardDTO.getId());
		cardName.setText(" 카드명 : " + cardDTO.getName());
		cardPrice.setText(" 현재 카드 가격 : " + cardDTO.getPrice());
		cardIcon.setIcon(new ImageIcon(cardDTO.getUrl()));
	}
}
