package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.InventoryDAO;
import dto.AuctionDTO;
import dto.CardDTO;
import dto.UserDTO;
import lombok.Data;
import lombok.ToString;
import manager.SocketManager;

@ToString
@Data
public class MainFrame extends JFrame implements Runnable {

	public SocketManager socket;

	private UserDTO user;
	private JLabel id;
	private JLabel password;

	private JPanel backgroundPanel;
	private JLabel backgroundLabel;
	private Icon backgroundIcon;

	private JLabel cash;

	// 검색창
	private JTextField searchBar;
	private JButton searchButton;

	private JButton logInButton;
	private JButton signUpButton;
	private JButton sellButton; // 판매 버튼
	private JButton directoryButton;

	private Icon pointIcon;

	private JButton[] buttons = new JButton[6];

	// 아래 패널들을 관리하기위한 리스트
	private List<JPanel> panels = new ArrayList<>();

	private AuctionPanel auctionPanel; // 진행중인 경매 패널
	private FinishedPanel finishedPanel; // 종료된 경매 패널
	private CheckBidPanel checkBidPanel; // 시세 알아보기 패널
	private SellProductPanel sellProductPanel; // 경매 출품하기 패널
	private MyPagePanel myPagePanel; // 마이 페이지 패널
	private InventoryPanel inventoryPanel; // 보관함 패널
	private InventoryDetailedPanel inventoryDetailedPanel; // 보관함 상세보기 패널
	private AuctionDetailedPanel auctionDetailedPanel; // 경매 상세보기 패널
	private FinishedDetailedPanel finishedDetailedPanel; // 종료된경매 상세보기 패널

	private ArrayList<CardDTO> userInventory = new ArrayList<>(); // 보관함 카드목록

	// 캐시 충전하기 서브프레임
	private ChargeFrame chargeFrame;

	// 0.진행중경매 1.종료된경매 2.시세체크 3.경매출품 4. 마이페이지 5.인벤토리
	// 6.보관함상세보기 7.경매상세보기 8.종료된경매상세보기
	private int state = 0; // 현재 메뉴 상태 표시

	private JButton poketPoint;

	private BufferedReader serverOrder; // 서버 명령
	private PrintWriter userOrder; // 유저 명령
//	private int size; // 경매중인 카드 수

	public MainFrame(UserDTO user, SocketManager socket) {
//		size = 0;
		this.socket = socket;
		this.user = user;
		initData();
		setInitLayout();
		initListener();
	}

	public JPanel getBackgroundPanel() {
		return this.backgroundPanel;
	}

	public List<JPanel> getPanles() {
		return this.panels;
	}

	private void initData() {

		try {
			userInventory = InventoryDAO.invenInfo(user.getName()); // 유저가 가지고있는 카드 목록 호출
		} catch (SQLException e) {
			e.printStackTrace();
		}
		backgroundPanel = new JPanel();

		auctionPanel = new AuctionPanel(panels, this);
		finishedPanel = new FinishedPanel(this);
		checkBidPanel = new CheckBidPanel(this);
		sellProductPanel = new SellProductPanel(user);
		myPagePanel = new MyPagePanel(this);
		inventoryPanel = new InventoryPanel(this);
		inventoryDetailedPanel = new InventoryDetailedPanel(this);
		finishedDetailedPanel = new FinishedDetailedPanel(this);

		chargeFrame = new ChargeFrame(this);

		panels.add(auctionPanel);
		panels.add(finishedPanel);
		panels.add(checkBidPanel);
		panels.add(sellProductPanel);
		panels.add(myPagePanel);
		panels.add(inventoryPanel);
		panels.add(inventoryDetailedPanel);
		panels.add(finishedDetailedPanel);

		add(backgroundPanel);
	}

	private void setInitLayout() {
		setTitle("[카드 경매 사이트 포켓 옥션]");
		setSize(1920, 1000);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage("image/파비콘2.png"));
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.white);

		Icon backgroundIcon = new ImageIcon("image/background.png");
		backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setSize(1920, 414);
		backgroundLabel.setLocation(0, 0);
		backgroundLabel.setHorizontalAlignment(JLabel.CENTER);
		backgroundPanel.add(backgroundLabel);

		// 캐시 라벨
		cash = new JLabel(user.getPoint() + " 원");
		cash.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 18));
		cash.setBounds(1460, 64, 500, 20);
		backgroundLabel.add(cash);

		pointIcon = new ImageIcon("image/poketpoint.gif");
		poketPoint = new JButton();
		poketPoint.setIcon(pointIcon);
		poketPoint.setSize(35, 35);
		poketPoint.setBorder(null);
		poketPoint.setLocation(1610, 55);
		backgroundLabel.add(poketPoint);
		user.setPoint(user.getPoint());

		searchBar = new JTextField(400);
		searchBar.setBounds(685, 278, 490, 40);
		searchBar.setBorder(null);
		backgroundLabel.add(searchBar);

		searchButton = new JButton();
		searchButton.setBounds(1175, 270, 60, 60);
		searchButton.setBackground(null);
		searchButton.setBorderPainted(false);
		searchButton.setContentAreaFilled(false);
		searchButton.setFocusPainted(false);
		backgroundLabel.add(searchButton);

		// 버튼 설정 0.진행중경매 1.종료된경매 2.시세체크 3.경매출품 4.마이페이지 5.인벤토리
		buttons[0] = new JButton("진행 중인 경매");
		buttons[1] = new JButton("종료된 경매");
		buttons[2] = new JButton("시세 알아보기");
		buttons[3] = new JButton("경매 출품하기");
		buttons[4] = new JButton("마이 페이지");
		buttons[5] = new JButton();
		buttons[5].setBounds(1657, 50, 70, 70);
		buttons[5].setBackground(null);
		buttons[5].setBorderPainted(true);
		buttons[5].setContentAreaFilled(false);
		backgroundLabel.add(buttons[5]);
		for (int i = 0; i < 5; i++) {
			buttons[i].setBounds(300 + i * 300, 175, 200, 50);
			buttons[i].setBackground(new Color(255, 204, 3));
			buttons[i].setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
			buttons[i].setBorderPainted(false);
			buttons[i].setForeground(Color.WHITE);
			buttons[i].setFocusPainted(false);
			backgroundLabel.add(buttons[i]);
		}

		for (int i = 0; i < panels.size(); i++) {
			backgroundPanel.add(panels.get(i));
			panels.get(i).setVisible(false);
		}
		auctionPanel.setVisible(true);
		backgroundPanel.setVisible(true);
		setVisible(true);
	}

	public void setVisible(int state) {
		for (int i = 0; i < panels.size(); i++) {
			panels.get(i).setVisible(false);
		}
		panels.get(state).setVisible(true);
		this.state = state;
	}

	public void addPanel(int state) {
		backgroundPanel.add(panels.get(state));
	}

//	public void removePanel() {
//		backgroundPanel.remove(7);
//		panels.remove(6);
//		auctionPanel.removeData();
//		System.out.println("판넬 사이즈 : " + panels.size());
//	}

	private void initListener() {

		// 0. 진행중인 경매 이동
		buttons[0].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("진행중 경매로 이동");
//				if (panels.size() > 6) {
//					removePanel();
//				}
				auctionPanel.removeData();
				auctionPanel.addAuction();
				setVisible(0);
			}
		});

		// 1. 종료된 경매 이동
		buttons[1].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("종료된 경매로 이동");
//				if (panels.size() > 6) {
//					removePanel();
//				}
				setVisible(1);
			}
		});

		// 2. 시세 체크 이동
		buttons[2].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("시세 체크로 이동");
//				if (panels.size() > 6) {
//					removePanel();
//				}
				setVisible(2);
			}
		});

		// 3. 경매 출품 이동
		buttons[3].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("경매 출품으로 이동");
//				if (panels.size() > 6) {
//					removePanel();
//				}
				setVisible(3);
			}
		});

		// 4. 마이페이지
		buttons[4].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("마이페이지로 이동");
//				if (panels.size() > 6) {
//					removePanel();
//				}
				setVisible(4);
			}
		});

		// 5. 인벤토리 이동
		buttons[5].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("인벤토리로 이동");
//				// 보관함 정보가 열려있으면 닫아주기
//				if (panels.size() > 6) {
//					removePanel();
//				}
				setVisible(5);
			}
		});

		// 캐시충전 서브프레임 열기
		poketPoint.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				chargeFrame.setVisible(true);
			}
		});

		// 검색버튼
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 보관함에서 검색
				if (state == 5 || state == 6) {
					inventoryPanel.searchInventory(searchBar.getText());
					setVisible(5);
				} else if (state == 1 || state == 8) {
					finishedPanel.searchEndAuction(searchBar.getText());
					setVisible(1);
				}
			}
		});

		searchBar.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					if (state == 5 || state == 6) {
						inventoryPanel.searchInventory(searchBar.getText());
						setVisible(5);
					} else if (state == 1 || state == 7) {
						finishedPanel.searchEndAuction(searchBar.getText());
						setVisible(1);
					}
					break;
				default:
					break;
				}
			}

		});

	}

	@Override
	public void run() {
	}
}