package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dto.UserDTO;
import lombok.ToString;

@ToString
public class MainFrame extends JFrame implements Runnable {

	private UserDTO user;
	private ChargeFrame chargeFrame;

	private JPanel backgroundPanel;

	private JLabel id;
	private JLabel password;
	private JLabel backgroundLabel;
	private Icon backgroundIcon;
	private JTextField idField;
	private JTextField passwordField;

	private JLabel cash;

	private JTextField searchBar;

	private JButton logInButton;
	private JButton signUpButton;
	private JButton sellButton; // 판매 버튼
	private JButton directoryButton;

	private Icon pointIcon;

	// 버튼 0.진행중경매 1.종료된경매 2.시세체크 3.경매출품 4.마이페이지 5.인벤토리
	private JButton[] buttons = new JButton[6];

	// 아래 패널들을 관리하기위한 리스트
	private List<JPanel> panels = new ArrayList<>();
	private AuctionPanel auctionPanel; // 진행중인 경매 패널
	private FinishedPanel finishedPanel; // 종료된 경매 패널
	private CheckBidPanel checkBidPanel; // 시세 알아보기 패널
	private SellProductPanel sellProductPanel; // 경매 출품하기 패널
	private MyPagePanel myPagePanel; // 마이 페이지 패널
	private InventoryPanel inventoryPanel; // 보관함 패널

	private int state = 0; // 현재 메뉴 상태 표시
	private JButton poketPoint;

	private BufferedReader serverOrder; // 서버 명령
	private PrintWriter userOrder; // 유저 명령
	private MainFrame mconText = this;

	public MainFrame(UserDTO user) {
		this.user = user;
		initData();
		setInitLayout();
		initListener();
	}

	public JPanel getBackgroundPanel() {
		return this.backgroundPanel;
	}

	private void initData() {

		backgroundPanel = new JPanel();
		auctionPanel = new AuctionPanel(backgroundPanel, user);
		finishedPanel = new FinishedPanel(user);
		checkBidPanel = new CheckBidPanel();
		sellProductPanel = new SellProductPanel(user);
		myPagePanel = new MyPagePanel(user);
		inventoryPanel = new InventoryPanel(user, mconText);
		panels.add(auctionPanel);
		panels.add(finishedPanel);
		panels.add(checkBidPanel);
		panels.add(sellProductPanel);
		panels.add(myPagePanel);
		panels.add(inventoryPanel);

		add(backgroundPanel);
	}

	private void setInitLayout() {
		setTitle("[카드 경매 사이트 포켓 옥션]");
		setSize(1920, 1000);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
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

		cash = new JLabel(user.getPoint() + "");
		cash.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 18));
		cash.setBounds(1470, 64, 500, 20);
		backgroundLabel.add(cash);

		pointIcon = new ImageIcon("image/poketpoint.gif");
		poketPoint = new JButton();
		poketPoint.setIcon(pointIcon);
		poketPoint.setSize(35, 35);
		poketPoint.setBorder(null);
		poketPoint.setLocation(1610, 55);
		backgroundLabel.add(poketPoint);
		user.setPoint(auctionPanel.getUser().getPoint());

		searchBar = new JTextField(400);
		searchBar.setBounds(685, 278, 490, 40);
		searchBar.setBorder(null);
		backgroundLabel.add(searchBar);

		JButton searchButton = new JButton();
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

	// 포인트 갱신
	public void changePoint(UserDTO user) {
		remove(cash);
		cash = new JLabel(user.getPoint() + "");
		cash.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 18));
		cash.setBounds(1470, 64, 500, 20);
		backgroundLabel.add(cash);

	}

	private void setVisible(int state) {
		for (int i = 0; i < panels.size(); i++) {
			panels.get(i).setVisible(false);
		}
		panels.get(state).setVisible(true);
		this.state = state;
	}

	private void initListener() {

		// 0. 진행중인 경매 이동
		buttons[0].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (state != 0) {
					System.out.println("진행중 경매로 이동");
					setVisible(0);
				}
			}
		});

		// 1. 종료된 경매 이동
		buttons[1].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (state != 1) {
					System.out.println("종료된 경매로 이동");
					setVisible(1);
				}
			}
		});

		// 2. 시세 체크 이동
		buttons[2].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (state != 2) {
					System.out.println("시세 체크로 이동");
					setVisible(2);
				}
			}
		});

		// 3. 경매 출품 이동
		buttons[3].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (state != 3) {
					System.out.println("경매 출품으로 이동");
					setVisible(3);
				}
			}
		});

		// 4. 마이페이지
		buttons[4].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (state != 4) {
					System.out.println("마이페이지로 이동");
					setVisible(4);
				}
			}
		});

		// 5. 인벤토리 이동
		buttons[5].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (state != 5) {
					System.out.println("인벤토리로 이동");
					setVisible(5);
				}
			}
		});

		// 포인트 충전
		poketPoint.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				chargeFrame = new ChargeFrame(user);
				changePoint(chargeFrame.getUser());
			}
		});
	}

	@Override
	public void run() {
		while (true) {

		}
	}
}