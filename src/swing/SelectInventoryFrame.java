package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dao.InventoryDAO;
import dto.CardDTO;
import dto.UserDTO;

public class SelectInventoryFrame extends JFrame {

	private ArrayList<JButton> productList = new ArrayList<>(7);
	private ArrayList<String> productTitleList = new ArrayList<>(7);
	private ArrayList<CardDTO> userInventory = new ArrayList<>(7);
	private JPanel backgroundPanel;
	private JLabel title;
	private JScrollPane scrollPane;
	private UserDTO user;
	private MainFrame mconText;
	private InventoryDetailedPanel detail;

	private JPanel mainPanel;
	private JButton exitButton;
	private JButton decideButton;

	private CardDTO selectCard;
	private SellProductPanel sellProductPanel;

	ArrayList<JButton> product = new ArrayList<>(7);
	ArrayList<Integer> serialNum = new ArrayList<>(7);
	InventoryDAO inven = new InventoryDAO();
	private int x = 130;
	private int y = 150;

	public CardDTO getSelectCard() {
		return selectCard;
	}

	public void setSelectCard(CardDTO selectCard) {
		this.selectCard = selectCard;
	}

	public SelectInventoryFrame(UserDTO user) {
		this.user = user;
		initLayout();
		initListener();
	}

	public void createProduct(String image) {
		System.out.println(serialNum.size()); // 시리얼 넘버 사이즈로 product버튼 인덱스 번호 지정
		product.get(serialNum.size()).setIcon(new ImageIcon(image)); // 유저가 올린 판매카드 이미지 버튼에 삽입
		serialNum.add(1); // 시리얼 넘버 사이즈도 증가
	}

	public void ProductButton() {
		// 처음 생성 될때 8개 버튼 생성
		for (int i = 0; i < 7; i++) {
			product.add(i, new JButton());
			product.get(i).setBounds(x, y, 150, 200);

			if ((i / 3) >= 1 && i % 3 == 0) {
				x = 130;
				y += 250;
			} else {
				x += 200;
			}
			mainPanel.add(product.get(i));
			setVisible(true);
		}
	}

	private void initLayout() {
		setTitle("[나의 카드 인벤토리]");
		setSize(1400, 900);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(new Color(255, 204, 3));
		System.out.println("인벤토리 입장");

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		JLabel titleLabel = new JLabel("판매할 카드 고르기");
		titleLabel.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(200, 80, 1000, 700);
		add(mainPanel);
		titleLabel.setBounds(400, 50, 400, 70);
		mainPanel.add(titleLabel);

		exitButton = new JButton("나가기");
		exitButton.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		exitButton.setBackground(new Color(255, 204, 100));
		exitButton.setBounds(360, 620, 120, 50);

		decideButton = new JButton("결정하기");
		decideButton.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		decideButton.setBackground(new Color(255, 204, 100));
		decideButton.setBounds(530, 620, 120, 50);

		mainPanel.add(exitButton);
		mainPanel.add(decideButton);

		ProductButton();
		try {
			userInventory = inven.invenInfo(user.getName()); // 유저가 가지고있는 카드 목록 호출
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < userInventory.size(); i++) {
			createProduct(userInventory.get(i).getUrl()); // 유저가 가지고있는 카드 url 삽입
		}
		setVisible(true);
	}

	private void initListener() {
		for (int i = 0; i < userInventory.size(); i++) {
			int num = i;
			product.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					selectCard = userInventory.get(num);
					System.out.println(selectCard);
				}
			});
		}

		// 결정하기
		decideButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (selectCard != null) {
					System.out.println(selectCard);
					JOptionPane.showMessageDialog(null, selectCard.getName() + " 을/를 선택합니다.");
					dispose();
				}
			}
		});

		// 나가기
		exitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "상품 선택을 종료합니다.");
				dispose();
			}
		});
	}

}
