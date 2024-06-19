package swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class MainFrame extends JFrame {

	private JPanel backgroundPanel;

	private JPanel mainPanel;

	private JPanel AuctionPanel;
	private JPanel LogInPanel;
	private JPanel PricePanel;
	private JPanel FinishPanel;
	private JPanel MyPagePanel;
	private JPanel JoinAuctionPanel;

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;

	private JLabel id;
	private JLabel password;
	private JLabel backgroundLabel;
	private Icon backgroundIcon;
	private JTextField idField;
	private JTextField passwordField;

	private JButton logInButton;
	private JButton signUpButton;
	private JButton sellButton; // 판매 버튼

	private AuctionPanel auctionPanel; // 진행중인 경매 패널
	private FinishedPanel finishedPanel; // 종료된 경매 패널
	private CheckBidPanel checkBidPanel; // 시세 알아보기 패널
	private SellProductPanel sellProductPanel; // 경매 출품하기 패널
	private MyPagePanel myPagePanel; // 마이 페이지 패널

	private int state = 1; // 현재 메뉴 상태 표시

	private JLabel title;
	private BufferedReader serverOrder; // 서버 명령
	private PrintWriter userOrder; // 유저 명령

	public MainFrame() {
		initData();
		setInitLayout();
		initListener();
	}

	private void initData() {

		backgroundPanel = new JPanel();

		mainPanel = new JPanel();
		add(backgroundPanel);
	}

	private void setInitLayout() {
		setTitle("[카드 경매 사이트 포켓 옥션]");
		setSize(1920, 1080);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel webTitle = new JLabel("즐거움이 살아 숨쉬는 카드 경매 사이트!");
		webTitle.setFont(new Font("Freesentation 7 Bold", Font.ITALIC, 34));
		webTitle.setBounds(830, 140, 900, 200);

		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.white);

		Icon backgroundIcon = new ImageIcon("image/background.png");
		backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setSize(1920,450);
		backgroundLabel.setLocation(0, 0);
		backgroundLabel.setHorizontalAlignment(JLabel.CENTER);
		backgroundPanel.add(backgroundLabel);

		button1 = new JButton("진행 중인 경매");
		button2 = new JButton("종료된 경매");
		button3 = new JButton("시세 알아보기");
		button4 = new JButton("경매 출품하기");
		button5 = new JButton("마이 페이지");

		button1.setBounds(300, 200, 200, 50);
		button2.setBounds(650, 200, 200, 50);
		button3.setBounds(950, 200, 200, 50);
		button4.setBounds(1250, 200, 200, 50);
		button5.setBounds(1550, 200, 200, 50);
		button1.setBackground(new Color(255,204,3));
		button2.setBackground(new Color(255,204,3));
		button3.setBackground(new Color(255,204,3));
		button4.setBackground(new Color(255,204,3));
		button5.setBackground(new Color(255,204,3));
		button1.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		button2.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		button3.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		button4.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		button5.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		button1.setBorderPainted(false);
		button2.setBorderPainted(false);
		button3.setBorderPainted(false);
		button4.setBorderPainted(false);
		button5.setBorderPainted(false);
		button1.setForeground(Color.WHITE);
		button2.setForeground(Color.WHITE);
		button3.setForeground(Color.WHITE);
		button4.setForeground(Color.WHITE);
		button5.setForeground(Color.WHITE);
		backgroundPanel.add(button1);
		backgroundPanel.add(button2);
		backgroundPanel.add(button3);
		backgroundPanel.add(button4);
		backgroundPanel.add(button5);


		auctionPanel = new AuctionPanel();

		backgroundPanel.add(auctionPanel);

		setVisible(true);

	}

	private void initListener() {

		// 진행중인 경매 이동
		button1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (state == 2) {
					finishedPanel.setVisible(false);
					auctionPanel = new AuctionPanel();
					backgroundPanel.add(auctionPanel);
					state = 1;

				} else {
				}

			}

		});

		// 종료된 경매 이동
		button2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (state == 1) {
					auctionPanel.setVisible(false);
					finishedPanel = new FinishedPanel();
					backgroundPanel.add(finishedPanel);
					state = 2;
				} else if (state == 2) {
				} else if (state == 3) {
					checkBidPanel.setVisible(false);
					finishedPanel = new FinishedPanel();
					backgroundPanel.add(finishedPanel);
					state = 2;
				} else if (state == 4) {
					sellProductPanel.setVisible(false);
					finishedPanel = new FinishedPanel();
					backgroundPanel.add(finishedPanel);
					state = 2;
				} else if (state == 5) {
					myPagePanel.setVisible(false);
					finishedPanel = new FinishedPanel();
					backgroundPanel.add(finishedPanel);
					state = 2;
				}

			}

		});

		// 시세 체크 이동
		button3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (state == 1) {
					auctionPanel.setVisible(false);
					checkBidPanel = new CheckBidPanel();
					backgroundPanel.add(checkBidPanel);
					state = 3;
				} else if (state == 2) {
					finishedPanel.setVisible(false);
					checkBidPanel = new CheckBidPanel();
					backgroundPanel.add(checkBidPanel);
				} else if (state == 3) {
				} else if (state == 4) {
					sellProductPanel.setVisible(false);
					checkBidPanel = new CheckBidPanel();
					backgroundPanel.add(checkBidPanel);
					state = 3;
				} else if (state == 5) {
					myPagePanel.setVisible(false);
					checkBidPanel = new CheckBidPanel();
					backgroundPanel.add(checkBidPanel);
					state = 3;
				}
			}

		});

		// 경매 출품 이동
		button4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (state == 1) {
					auctionPanel.setVisible(false);
					sellProductPanel = new SellProductPanel();
					backgroundPanel.add(sellProductPanel);
					state = 4;
				} else if (state == 2) {
					finishedPanel.setVisible(false);
					sellProductPanel = new SellProductPanel();
					backgroundPanel.add(sellProductPanel);
					state = 4;
				} else if (state == 3) {
					checkBidPanel.setVisible(false);
					sellProductPanel = new SellProductPanel();
					backgroundPanel.add(sellProductPanel);
					state = 4;
				} else if (state == 4) {
				} else if (state == 5) {
					myPagePanel.setVisible(false);
					sellProductPanel = new SellProductPanel();
					backgroundPanel.add(sellProductPanel);
					state = 4;
				}
			}

		});

		// 마이페이지 이동
		button5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (state == 1) {
					auctionPanel.setVisible(false);
					myPagePanel = new MyPagePanel();
					backgroundPanel.add(myPagePanel);
					state = 5;
				} else if (state == 2) {
					finishedPanel.setVisible(false);
					myPagePanel = new MyPagePanel();
					backgroundPanel.add(myPagePanel);
					state = 5;
				} else if (state == 3) {
					checkBidPanel.setVisible(false);
					myPagePanel = new MyPagePanel();
					backgroundPanel.add(myPagePanel);
					state = 5;
				} else if (state == 4) {
					sellProductPanel.setVisible(false);
					myPagePanel = new MyPagePanel();
					backgroundPanel.add(myPagePanel);
					state = 5;
				} else if (state == 5) {
				}

			}

		});
	}

	public static void main(String[] args) {
		new MainFrame();
	}

}
