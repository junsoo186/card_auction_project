package swing;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.AuctionDTO;
import dto.CardDTO;

public class FinishedPanel extends JPanel {

	private MainFrame mContext;

	private MouseListener[] listener = new MouseListener[10];
	private ArrayList<JButton> buttons = new ArrayList<>(); // 버튼 목록
	private ArrayList<AuctionDTO> endAuctionList; // 종료된 경매 경매목록
	private ArrayList<CardDTO> allCardList; // 모든 카드목록
	private ArrayList<CardDTO> endCardList = new ArrayList<>(); // 종료된 경매의 카드목록
	private ArrayList<CardDTO> currentCardList = new ArrayList<>(); // 현재 종료된 경매 카드목록

	private JPanel backgroundPanel;
	private JLabel backImage;

	// 페이지 버튼
	private JButton nextPage;
	private JButton previousPage;

	// 페이지 변수
	private int page = 1;
	private int pageEnd;

	public FinishedPanel(MainFrame mContext) {
		this.mContext = mContext;
		backgroundPanel = mContext.getBackgroundPanel();
		allCardList = mContext.getAllCardList();
		endAuctionList = mContext.getEndAuctionList();
		currentCardList = endCardList;

		for (int i = 0; i < endAuctionList.size(); i++) {
			for (int j = 0; j < allCardList.size(); j++) {
				if (endAuctionList.get(i).getCardId() == allCardList.get(j).getId()) {
					endCardList.add(allCardList.get(j));
				}
			}
		}

		initData();
		setInitLayout();
	}

	private void initData() {
		backImage = new JLabel(new ImageIcon("image/배경.png"));
		backImage.setBounds(450, 0, 1007, 534);

		nextPage = new JButton(new ImageIcon("image/오른쪽.png"));
		nextPage.setBackground(null);
		nextPage.setBorderPainted(false);
		nextPage.setContentAreaFilled(false);
		nextPage.setBounds(1500, 50, 150, 50);

		previousPage = new JButton(new ImageIcon("image/왼쪽.png"));
		previousPage.setBackground(null);
		previousPage.setBorderPainted(false);
		previousPage.setContentAreaFilled(false);
		previousPage.setBounds(300, 50, 150, 50);

		productButton();
		createProduct(endCardList);
		addActionListner();
		clickPage(endCardList);
	}

	private void setInitLayout() {
		setSize(1920, 630);
		setLocation(0, 400);
		setLayout(null);
		setBackground(Color.WHITE);

		add(backgroundPanel);
		add(backImage);
		add(nextPage);
		add(previousPage);
	}

	// 패널 클릭시 초기화
	public void clickEndAuctionPanel() {
		page = 1;
		createProduct(endCardList);
		currentCardList = endCardList;
	}

	// 버튼 10개 생성
	public void productButton() {
		int x = 45;
		for (int i = 0; i < 10; i++) {
			if (i < 5) {
				buttons.add(i, new JButton());
				buttons.get(i).setBounds(x + i * 199, 65, 120, 167);
			} else {
				x = -957;
				buttons.add(i, new JButton());
				buttons.get(i).setBounds(x + i * 200, 315, 120, 167);
			}
			backImage.add(buttons.get(i));
			setVisible(true);
		}
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

	// 카드 상세보기 기능
	public void addActionListner() {
		for (int i = 0; i < buttons.size(); i++) {
			int num = i;
			listener[i] = new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					mContext.getFinishedDetailedPanel().setCardDTO(currentCardList.get(num));
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
		ArrayList<CardDTO> searchInventory = new ArrayList<>();
		page = 1;
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
		currentCardList = searchInventory;
	};

	// 다음페이지, 이전페이지 버튼 MouseListener
	public void clickPage(ArrayList<CardDTO> inventory) {
		nextPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ArrayList<CardDTO> pageInventory = new ArrayList<>();
				pageEnd = ((inventory.size() - 1) / 10) + 1;
				if (page < pageEnd) {
					System.out.println("다음페이지로 넘김");
					page++;
					if (page == pageEnd) {
						for (int i = 0; i < (inventory.size() % 10); i++) {
							pageInventory.add(inventory.get(i + (page - 1) * 10));
						}
					} else {
						for (int i = 0; i < buttons.size(); i++) {
							pageInventory.add(inventory.get(i + (page - 1) * 10));
						}
					}
					createProduct(pageInventory);
					currentCardList = pageInventory;
				}
			}
		});
		previousPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ArrayList<CardDTO> pageInventory = new ArrayList<>();
				if (page > 1) {
					System.out.println("이전페이지로 넘김");
					page--;
					for (int i = 0; i < buttons.size(); i++) {
						pageInventory.add(inventory.get(i + (page - 1) * 10));
					}
					createProduct(pageInventory);
					currentCardList = pageInventory;
				}
			}
		});
	}

}
