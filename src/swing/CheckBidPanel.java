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

import dto.CardDTO;

public class CheckBidPanel extends JPanel {

	private MainFrame mContext;

	private MouseListener[] listener = new MouseListener[10];
	private ArrayList<JButton> buttons = new ArrayList<>(); // 버튼 목록
	private ArrayList<CardDTO> allCardList = new ArrayList<>(); // 모든 카드 목록
	private ArrayList<CardDTO> currentCardList = new ArrayList<>(); // 현재 카드 목록

	private JPanel backgroundPanel;
	private JLabel title;

	// 가방
	private JLabel bag;

	// 페이지 버튼
	private JButton nextPage;
	private JButton previousPage;

	// 페이지 변수
	private int page = 1;
	private int pageEnd;

	public CheckBidPanel(MainFrame mContext) {
		this.mContext = mContext;
		backgroundPanel = mContext.getBackgroundPanel();
		allCardList = mContext.getAllCardList();
		currentCardList = allCardList;

		checkBidThread();
		initData();
		setInitLayout();
	}

	private void initData() {
		bag = new JLabel(new ImageIcon("image/배경.png"));
		bag.setBounds(450, 0, 1007, 534);

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
		createProduct(allCardList);
		addActionListner();
		clickPage(allCardList);
	}

	private void setInitLayout() {
		setSize(1920, 630);
		setLocation(0, 400);
		setLayout(null);
		setBackground(Color.WHITE);

		add(backgroundPanel);
		add(bag);
		add(nextPage);
		add(previousPage);
	}

	// 10초에 한번씩 모든 카드 정보(가격)를 갱신
	private void checkBidThread() {
		new Thread(() -> {
			while (true) {
				mContext.getSocket().getAllCardList().clear();
				mContext.getSocket().sendOrder("AllCardList");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				allCardList = mContext.getSocket().getAllCardList();
			}
		}).start();
	}

	// 시세 체크 클릭시 초기화
	public void clickBidCheckPanel() {
		page = 1;
		createProduct(allCardList);
		currentCardList = allCardList;
	}

	// 버튼 10개 생성
	public void productButton() {
		int x = 45;
		for (int i = 0; i < 10; i++) {
			if (i < 5) {
				buttons.add(i, new JButton());
				buttons.get(i).setBounds(x + i * 199, 65, 120, 167);
			} else {
				x = -957;;
				buttons.add(i, new JButton());
				buttons.get(i).setBounds(x + i * 200, 315, 120, 167);
			}
			bag.add(buttons.get(i));
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

	// 카드 상세보기 MouseEvent 삽입
	public void addActionListner() {
		for (int i = 0; i < buttons.size(); i++) {
			int num = i;
			listener[i] = new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					mContext.getCheckBidDetailedPanel().setCardDTO(currentCardList.get(num));
					mContext.getCheckBidDetailedPanel().clickCheckBidDetail();
					setVisible(false);
					mContext.setState(8);
					mContext.setVisible(8);
				}
			};
			buttons.get(i).addMouseListener(listener[i]);
		}
	}

	// 카드이름으로 검색 기능
	public void searchInventory(String card_name) {
		ArrayList<CardDTO> searchInventory = new ArrayList<>();
		page = 1;
		if (card_name.equals("")) {
			for (int i = 0; i < allCardList.size(); i++) {
				searchInventory.add(allCardList.get(i));
			}
		} else {
			for (int i = 0; i < allCardList.size(); i++) {
				if (allCardList.get(i).getName().contains(card_name)) {
					searchInventory.add(allCardList.get(i));
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
