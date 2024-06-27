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
import lombok.Setter;

@Setter

// TODO 카드검색 후에 페이지 넘기기 기능

public class InventoryPanel extends JPanel {

	private MainFrame mContext;

	private MouseListener[] listener = new MouseListener[10];

	private ArrayList<JButton> buttons = new ArrayList<>(); // 버튼 목록
	private ArrayList<CardDTO> userInventory; // 유저의 보관함 카드목록
	private ArrayList<CardDTO> currentInventory; // 현재 보관함 카드목록

	private JPanel backgroundPanel;
	private JLabel bag;

	// 페이지 버튼
	private JButton nextPage;
	private JButton previousPage;

	// 페이지 변수
	private int page = 1;
	private int pageEnd;

	public InventoryPanel(MainFrame mContext) {
		this.mContext = mContext;
		userInventory = mContext.getUserInventory();
		currentInventory = userInventory;

		checkInvenThread();
		initData();
		setInitLayout();
	}

	private void initData() {
		bag = new JLabel(new ImageIcon("image/가방.png"));
		bag.setBounds(400, 10, 1090, 575);

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
		createProduct(userInventory);
		addActionListner();
		clickPage(userInventory);
	}

	private void setInitLayout() {
		setSize(1920, 630);
		setLocation(0, 400);
		setLayout(null);
		setBackground(Color.WHITE);

		add(bag);
		add(nextPage);
		add(previousPage);
	}

	// 인벤토리 카드 정보를 갱신
	private void checkInvenThread() {
		new Thread(() -> {
			while (true) {
				mContext.getSocket().getUserInventory().clear();
				mContext.getSocket().sendOrder("UserInventory#" + mContext.getUser().getName());
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				userInventory = mContext.getSocket().getUserInventory();
			}
		}).start();
	}

	// 패널 클릭시 초기화
	public void clickInventoryPanel() {
		page = 1;
		createProduct(userInventory);
		currentInventory = userInventory;
	}

	// 버튼 10개 생성
	public void productButton() {
		int x = 106;
		for (int i = 0; i < 10; i++) {
			if (i < 5) {
				buttons.add(i, new JButton());
				buttons.get(i).setBounds(x + i * 190, 120, 120, 167);
			} else {
				x = -844;
				buttons.add(i, new JButton());
				buttons.get(i).setBounds(x + i * 190, 330, 120, 167);
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

	// 카드이름으로 검색 기능
	public void searchInventory(String card_name) {
		ArrayList<CardDTO> searchInventory = new ArrayList<>();
		page = 1;
		if (card_name.equals("")) {
			for (int i = 0; i < userInventory.size(); i++) {
				searchInventory.add(userInventory.get(i));
			}
		} else {
			for (int i = 0; i < userInventory.size(); i++) {
				if (userInventory.get(i).getName().contains(card_name)) {
					searchInventory.add(userInventory.get(i));
				}
			}
		}
		createProduct(searchInventory);
		currentInventory = searchInventory;
	};

	// 카드 상세보기 기능
	public void addActionListner() {
		for (int i = 0; i < buttons.size(); i++) {
			int num = i;
			listener[i] = new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					mContext.getInventoryDetailedPanel().setCardDTO(currentInventory.get(num));
					mContext.getInventoryDetailedPanel().clickDetailedButton();
					setVisible(false);
					mContext.setState(6);
					mContext.setVisible(6);
				}
			};
			buttons.get(i).addMouseListener(listener[i]);
		}
	}

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
					currentInventory = pageInventory;
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
					currentInventory = pageInventory;
				}
			}
		});
	}
}