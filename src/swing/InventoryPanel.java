package swing;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import dao.InventoryDAO;
import dto.CardDTO;
import dto.UserDTO;
import lombok.Setter;

@Setter
public class InventoryPanel extends JPanel {

	private MainFrame mconText;

	private MouseListener[] listener = new MouseListener[10];

	private ArrayList<JButton> productList = new ArrayList<>();
	private ArrayList<String> productTitleList = new ArrayList<>();
	private ArrayList<CardDTO> userInventory = new ArrayList<>(); // 유저인벤토리 전체 카드목록
	private ArrayList<CardDTO> pageInventory = new ArrayList<>(); // 현재페이지 유저인벤토리 카드목록

	private JPanel backgroundPanel;
	private UserDTO user;

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

	public InventoryPanel(UserDTO user, MainFrame mconText) {
		this.mconText = mconText;
		this.user = user;
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

		try {
			userInventory = InventoryDAO.invenInfo(user.getName()); // 유저가 가지고있는 카드 목록 호출
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		for (int i = 0; i < 10; i++) {
			if (i < 5) {
				buttons.add(i, new JButton());
				buttons.get(i).setBounds(x + i * 200, 50, 150, 200);
			} else {
				x = -500;
				buttons.add(i, new JButton());
				buttons.get(i).setBounds(x + i * 200, 300, 150, 200);
			}
			add(buttons.get(i));
			setVisible(true);
		}
	}

	// 버튼에 카드이미지 URL 삽입
	public void createProduct(ArrayList<CardDTO> inventory) {
		for (int i = 0; i < buttons.size(); i++) {
			if (i < inventory.size()) {
				buttons.get(i).setIcon(null);
				ImageIcon imageIcon = new ImageIcon(inventory.get(i).getUrl());
				buttons.get(i).setIcon(imageIcon);
			} else {
				buttons.get(i).setIcon(null);
			}
		}
	}

	// 카드 정보 조회기능 추가
	public void addActionListner(ArrayList<CardDTO> inventory) {
		for (int i = 0; i < buttons.size(); i++) {
//			buttons.get(i).removeMouseListener(listener[i]);
			int num = i;
			listener[i] = new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					mconText.getInventoryDetailedPanel().setCard(inventory.get(num));
					mconText.getInventoryDetailedPanel().clickDetailedButton();
					setVisible(false);
					mconText.setVisible(6);
					System.out.println("보관함 클릭이벤트 : " + inventory.get(num));
				}
			};
			buttons.get(i).addMouseListener(listener[i]);
		}
	}

	// 이전, 다음 페이지 버튼 MouseListener
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
