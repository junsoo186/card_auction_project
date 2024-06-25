package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.AuctionDTO;
import dto.CardDTO;
import dto.UserDTO;
import manager.SocketManager;

public class FinishedPanel extends JPanel {

	private MainFrame mContext;
	private SocketManager socket;
	private ArrayList<JButton> productList = new ArrayList<>(12);
	private ArrayList<String> productTitleList = new ArrayList<>(12);
	private ArrayList<AuctionDTO> endAuctionList = new ArrayList(); // 모든 종료된 경매 목록
	private ArrayList<CardDTO> allCardList = new ArrayList<>(); // 모든 카드 목록
	private ArrayList<CardDTO> endCardList = new ArrayList<>(); // 종료된 경매의 카드목록

	private ArrayList<CardDTO> pageInventory = new ArrayList<>(); // 현재페이지 카드목록
	private ArrayList<CardDTO> searchInventory = new ArrayList<>(); // 검색된 카드목록

	private JPanel backgroundPanel;
	private JLabel title;
	private UserDTO user;
	JLabel backImage;
	private MouseListener[] listener = new MouseListener[10];

	private FinishedDetailedPanel finishedDetailedPanel;

	private ArrayList<JButton> buttons = new ArrayList<>();
	private ArrayList<Integer> serialNum = new ArrayList<>();

	private int x = 500;
	private int y = 100;

	// 페이지 버튼
	private JButton nextPage;
	private JButton previousPage;

	// 페이지 변수
	private int page = 1;
	private int pageEnd;

	public FinishedPanel(MainFrame mContext) {
		this.mContext = mContext;
		this.user = mContext.getUser();
		this.socket = mContext.getSocket();
		this.allCardList = mContext.getAllCardList();
		this.endAuctionList = mContext.getEndAuctionList();
		this.endCardList = mContext.getEndCardList();

		initData();
		setInitLayout();
		clickPage();
	}

	private void initData() {
		backgroundPanel = mContext.getBackgroundPanel();
	}

	private void setInitLayout() {
		backImage = new JLabel(new ImageIcon("image/배경.png")); 
		backImage.setBounds(450,0,1007,534);
		add(backImage);
		nextPage = new JButton(new ImageIcon("image/오른쪽.png"));
		nextPage.setBackground(null);
		nextPage.setBorderPainted(false);
		nextPage.setContentAreaFilled(false);
		nextPage.setBounds(1500, 50, 150, 50);
		add(nextPage);
		previousPage = new JButton(new ImageIcon("image/왼쪽.png"));
		previousPage.setBackground(null);
		previousPage.setBorderPainted(false);
		previousPage.setContentAreaFilled(false);
		previousPage.setBounds(300, 50, 150, 50);
		add(previousPage);

		setSize(1920, 630);
		setLocation(0, 400);
		setLayout(null);
		setBackground(Color.WHITE);
		add(backgroundPanel);

		title = new JLabel("종료된 경매" + "(" + endCardList.size() + ")");
		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(860, 10, 800, 50);
		add(title);

		productButton();
		createProduct(endCardList);
		addActionListner(endCardList);
	}

	// 버튼에 카드이미지 URL 삽입함수
	public void createProduct(ArrayList<CardDTO> inventory) {
		for (int i = 0; i < buttons.size(); i++) {
			if (i < inventory.size()) {
				ImageIcon imageIcon = new ImageIcon(inventory.get(i).getUrl());
				buttons.get(i).setIcon(imageIcon);
				buttons.get(i).setVisible(true);
			} else {
				buttons.get(i).setVisible(false);
			}
		}
	}

	// 버튼 10개 생성
	public void productButton() {
		x = 45;
		for (int i = 0; i < 10; i++) {
			if (i < 5) {
				buttons.add(i, new JButton());
				buttons.get(i).setBounds(x + i * 200, 65, 120, 167);
			} else {
				x = -958;
				buttons.add(i, new JButton());
				buttons.get(i).setBounds(x + i * 200, 6000, 120, 167);
			}
			backImage.add(buttons.get(i));
			setVisible(true);
		}
	}


	// 카드 상세보기 기능
	public void addActionListner(ArrayList<CardDTO> inventory) {
		for (int i = 0; i < buttons.size(); i++) {
			int num = i;
			listener[i] = new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					mContext.getFinishedDetailedPanel().setCardDTO(inventory.get(num));
					mContext.getFinishedDetailedPanel().clickEndDetailButton();
					setVisible(false);
					mContext.setState(7);
					mContext.setVisible(7);
				}
			};
			buttons.get(i).addMouseListener(listener[i]);
		}
	}

	// 카드이름으로 검색 기능
	public void searchEndAuction(String card_name) {
		page = 1;
		searchInventory.clear();
		if (card_name.equals("")) {
			for (int i = 0; i < endCardList.size(); i++) {
				searchInventory.add(endCardList.get(i));
			}
		} else {
			for (int i = 0; i < endCardList.size(); i++) {
				if (endCardList.get(i).getName().contains(card_name)) {
					searchInventory.add(endCardList.get(i));
				}
			}
		}
		createProduct(searchInventory);
		addActionListner(searchInventory);
	};

	// 다음페이지, 이전페이지 버튼 MouseListener
	public void clickPage() {
		nextPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pageInventory.clear();
				pageEnd = ((endCardList.size() - 1) / 10) + 1;
				if (page < pageEnd) {
					System.out.println("다음페이지로 넘김");
					page++;
					if (page == pageEnd) {
						for (int i = 0; i < (endCardList.size() % 10); i++) {
							pageInventory.add(endCardList.get(i + (page - 1) * 10));
						}
					} else {
						for (int i = 0; i < buttons.size(); i++) {
							pageInventory.add(endCardList.get(i + (page - 1) * 10));
						}
					}
					createProduct(pageInventory);
					addActionListner(pageInventory);
				}
			}
		});
		previousPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pageInventory.clear();
				if (page > 1) {
					System.out.println("이전페이지로 넘김");
					page--;
					for (int i = 0; i < buttons.size(); i++) {
						pageInventory.add(endCardList.get(i + (page - 1) * 10));
					}
					createProduct(pageInventory);
					addActionListner(pageInventory);
				}
			}
		});
	}

}