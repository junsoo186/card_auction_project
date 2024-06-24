package swing;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import dto.CardDTO;
import lombok.Setter;

@Setter

// TODO 카드검색 후에 페이지 넘기기 기능

public class InventoryPanel extends JPanel {

	private MainFrame mContext;

	private MouseListener[] listener = new MouseListener[10];

//	private ArrayList<JButton> productList = new ArrayList<>();
//	private ArrayList<String> productTitleList = new ArrayList<>();
	private ArrayList<CardDTO> userInventory = new ArrayList<>(); // 보관함 전체 카드목록
	private ArrayList<CardDTO> pageInventory = new ArrayList<>(); // 현재페이지 카드목록
	private ArrayList<CardDTO> searchInventory = new ArrayList<>(); // 검색된 카드목록

	private JPanel backgroundPanel;

	// 버튼 좌표
	private int x;
	private int y;

	// 페이지 버튼
	private JButton nextPage;
	private JButton previousPage;

	// 페이지 변수
	private int page = 1;
	private int pageEnd;

	ArrayList<JButton> buttons = new ArrayList<>();
	ArrayList<Integer> serialNum = new ArrayList<>();

	public InventoryPanel(MainFrame mContext) {
		this.mContext = mContext;
		this.userInventory = mContext.getUserInventory();
		initData();
		setInitLayout();
		clickPage();
	}

	private void initData() {
		backgroundPanel = new JPanel();
	}

	private void setInitLayout() {

		setSize(1920, 630);
		setLocation(0, 400);
		setLayout(null);
		setBackground(Color.WHITE);
		add(backgroundPanel);

		productButton();
		createProduct(userInventory);

		System.out.println("카드 등록 까지 ");
		addActionListner(userInventory);

//		JLabel title=new JLabel("보유 카드 확인하기"+"("+product.size()+")");
//		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
//		title.setBounds(860, 10, 800, 50);
//		add(title);

		nextPage = new JButton("다음");
		nextPage.setBounds(1500, 50, 150, 50);
		add(nextPage);
		previousPage = new JButton("이전");
		previousPage.setBounds(300, 50, 150, 50);
		add(previousPage);

	}

	// 버튼 10개 생성
	public void productButton() {
		x = 500;
		y = 200;
		for (int i = 0; i < 10; i++) {
			if (i < 5) {
				buttons.add(i, new JButton());
				buttons.get(i).setBounds(x + i * y, 50, 150, 200);
			} else {
				x = -500;
				buttons.add(i, new JButton());
				buttons.get(i).setBounds(x + i * y, 300, 150, 200);
			}
			add(buttons.get(i));
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
		page = 1;
		searchInventory.clear();
		if (card_name.equals("")) {
			for (int i = 0; i < userInventory.size(); i++) {
				searchInventory.add(userInventory.get(i));
			}
		} else {
			for (int i = 0; i < userInventory.size(); i++) {
				if (userInventory.get(i).getName().equals(card_name)) {
					searchInventory.add(userInventory.get(i));
				}
			}
		}
		createProduct(searchInventory);
		addActionListner(searchInventory);
	};

	// 카드 상세보기 기능
	public void addActionListner(ArrayList<CardDTO> inventory) {
		for (int i = 0; i < buttons.size(); i++) {
			int num = i;
			listener[i] = new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					mContext.getInventoryDetailedPanel().setCardDTO(inventory.get(num));
					mContext.getInventoryDetailedPanel().clickDetailedButton();
					setVisible(false);
					mContext.setVisible(6);
					System.out.println("보관함 클릭이벤트 : " + inventory.get(num));
				}
			};
			buttons.get(i).addMouseListener(listener[i]);
		}
	}

	// 다음페이지, 이전페이지 버튼 MouseListener
	public void clickPage() {
		nextPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pageInventory.clear();
				pageEnd = ((userInventory.size() - 1) / 10) + 1;
				if (page < pageEnd) {
					System.out.println("다음페이지로 넘김");
					page++;
					if (page == pageEnd) {
						for (int i = 0; i < (userInventory.size() % 10); i++) {
							pageInventory.add(userInventory.get(i + (page - 1) * 10));
						}
					} else {
						for (int i = 0; i < buttons.size(); i++) {
							pageInventory.add(userInventory.get(i + (page - 1) * 10));
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
						pageInventory.add(userInventory.get(i + (page - 1) * 10));
					}
					createProduct(pageInventory);
					addActionListner(pageInventory);
				}
			}
		});
	}
}
