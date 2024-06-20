 package swing;

import java.awt.Color;
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
import javax.swing.JTextField;

import dto.UserDTO;
import lombok.ToString;

@ToString

public class MainFrame extends JFrame{

	private UserDTO user;
	private ChargeFrame chargeFrame;
	
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
	
	private JLabel cash;
	
	private JTextField searchBar;

	private JButton logInButton;
	private JButton signUpButton;
	private JButton sellButton; // 판매 버튼
	private JButton directoryButton;
	private JButton inventoryButton;
	
	private Icon pointIcon;

	private AuctionPanel auctionPanel; // 진행중인 경매 패널
	private FinishedPanel finishedPanel; // 종료된 경매 패널
	private CheckBidPanel checkBidPanel; // 시세 알아보기 패널
	private SellProductPanel sellProductPanel; // 경매 출품하기 패널
	private MyPagePanel myPagePanel; // 마이 페이지 패널
	private InventoryPanel inventoryPanel; // 보관함

	private int state = 1; // 현재 메뉴 상태 표시
	private JButton poketPoint;
	
	private JLabel title;
	private BufferedReader serverOrder; // 서버 명령
	private PrintWriter userOrder; // 유저 명령

	public MainFrame(UserDTO user) {
		this.user = user;
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
		setSize(1920, 1000);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		user=new UserDTO();
		
		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.white);

		Icon backgroundIcon = new ImageIcon("image/background.png");
		backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setSize(1920,414);
		backgroundLabel.setLocation(0, 0);
		backgroundLabel.setHorizontalAlignment(JLabel.CENTER);
		backgroundPanel.add(backgroundLabel);

		cash=new JLabel(user.getPoint()+"");
		cash.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 18));
		cash.setBounds(1470, 64, 500, 20);
		backgroundLabel.add(cash);
		
		pointIcon=new ImageIcon("image/poketpoint.gif");
		poketPoint=new JButton();
		poketPoint.setIcon(pointIcon);
		poketPoint.setSize(35,35);
		poketPoint.setBorder(null);
		poketPoint.setLocation(1610, 55);
		backgroundLabel.add(poketPoint);
		
		searchBar=new JTextField(400);
		searchBar.setBounds(685,278,490,40);
		searchBar.setBorder(null);
		backgroundLabel.add(searchBar);
		
		inventoryButton= new JButton();
		inventoryButton.setBounds(1657, 50, 70, 70);
		inventoryButton.setBackground(null);
		inventoryButton.setBorderPainted(true);
		inventoryButton.setContentAreaFilled(false);
		backgroundLabel.add(inventoryButton);
		
		JButton searchButton = new JButton();
		searchButton.setBounds(1175, 270, 60, 60);
		searchButton.setBackground(null);
		searchButton.setBorderPainted(false);
		searchButton.setContentAreaFilled(false);
		searchButton.setFocusPainted(false);
		backgroundLabel.add(searchButton);
		
		button1 = new JButton("진행 중인 경매");
		button2 = new JButton("종료된 경매");
		button3 = new JButton("시세 알아보기");
		button4 = new JButton("경매 출품하기");
		button5 = new JButton("마이 페이지");

		button1.setBounds(270, 175, 200, 50);
		button2.setBounds(620, 175, 200, 50);
		button3.setBounds(920, 175, 200, 50);
		button4.setBounds(1220, 175, 200, 50);
		button5.setBounds(1520, 175, 200, 50);
		button1.setBackground(new Color(255,204,3));
		button2.setBackground(new Color(255,204,3));
		button3.setBackground(new Color(255,204,3));
		button4.setBackground(new Color(255,204,3));
		button5.setBackground(new Color(255,204,3));
		button1.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		button2.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		button3.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		button4.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		button5.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
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
		button1.setFocusPainted(false);
		button2.setFocusPainted(false);
		button3.setFocusPainted(false);
		button4.setFocusPainted(false);
		button5.setFocusPainted(false);
		backgroundLabel.add(button1);
		backgroundLabel.add(button2);
		backgroundLabel.add(button3);
		backgroundLabel.add(button4);
		backgroundLabel.add(button5);


		auctionPanel = new AuctionPanel(backgroundPanel,user);
		backgroundPanel.add(auctionPanel);

		setVisible(true);

	}

	// 포인트 갱신
	public void changePoint(UserDTO user) {
		remove(cash);
		cash=new JLabel(user.getPoint()+"");
		cash.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 18));
		cash.setBounds(1470, 64, 500, 20);
		backgroundLabel.add(cash);
	}
	
	private void initListener() {

		// 1. 진행중인 경매 이동
		button1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (state == 1) {
					auctionPanel = new AuctionPanel(backgroundPanel,user);
					backgroundPanel.add(auctionPanel);
				} else if (state == 2) {
					finishedPanel.setVisible(false);
					auctionPanel = new AuctionPanel(backgroundPanel,user);
					backgroundPanel.add(auctionPanel);
				} else if (state == 3) {
					checkBidPanel.setVisible(false);
					auctionPanel = new AuctionPanel(backgroundPanel,user);
					backgroundPanel.add(auctionPanel);
					state = 1;
				} else if (state == 4) {
					sellProductPanel.setVisible(false);
					auctionPanel = new AuctionPanel(backgroundPanel,user);
					backgroundPanel.add(auctionPanel);
					state = 1;
				} else if (state == 5) {
					myPagePanel.setVisible(false);
					auctionPanel = new AuctionPanel(backgroundPanel,user);
					backgroundPanel.add(auctionPanel);
					state = 1;
				} else if (state == 6) {
					inventoryPanel.setVisible(false);
					auctionPanel = new AuctionPanel(backgroundPanel,user);
					backgroundPanel.add(auctionPanel);
					state = 1;
				}
				user.setPoint(auctionPanel.getUser().getPoint());

			}

		});

		// 2. 종료된 경매 이동
		button2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (state == 1) {
					auctionPanel.setVisible(false);
					finishedPanel = new FinishedPanel(user);
					backgroundPanel.add(finishedPanel);
					state = 2;
				} else if (state == 2) {
					finishedPanel = new FinishedPanel(user);
					backgroundPanel.add(finishedPanel);
				} else if (state == 3) {
					checkBidPanel.setVisible(false);
					finishedPanel = new FinishedPanel(user);
					backgroundPanel.add(finishedPanel);
					state = 2;
				} else if (state == 4) {
					sellProductPanel.setVisible(false);
					finishedPanel = new FinishedPanel(user);
					backgroundPanel.add(finishedPanel);
					state = 2;
				} else if (state == 5) {
					myPagePanel.setVisible(false);
					finishedPanel = new FinishedPanel(user);
					backgroundPanel.add(finishedPanel);
					state = 2;
				} else if (state == 6) {
					inventoryPanel.setVisible(false);
					finishedPanel = new FinishedPanel(user);
					backgroundPanel.add(finishedPanel);
					state = 2;
				}

			}

		});

		// 3. 시세 체크 이동
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
					state=3;
				} else if (state == 3) {
					checkBidPanel = new CheckBidPanel();
					backgroundPanel.add(checkBidPanel);
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
				}else if (state == 6) {
					inventoryPanel.setVisible(false);
					checkBidPanel = new CheckBidPanel();
					backgroundPanel.add(checkBidPanel);
					state = 3;
				}
				
			}

		});

		// 4. 경매 출품 이동 
		button4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (state == 1) {
					auctionPanel.setVisible(false);
					sellProductPanel = new SellProductPanel(user);
					backgroundPanel.add(sellProductPanel);
					state = 4;
				} else if (state == 2) {
					finishedPanel.setVisible(false);
					sellProductPanel = new SellProductPanel(user);
					backgroundPanel.add(sellProductPanel);
					state = 4;
				} else if (state == 3) {
					checkBidPanel.setVisible(false);
					sellProductPanel = new SellProductPanel(user);
					backgroundPanel.add(sellProductPanel);
					state = 4;
				} else if (state == 4) {
					sellProductPanel = new SellProductPanel(user);
					backgroundPanel.add(sellProductPanel);
				} else if (state == 5) {
					myPagePanel.setVisible(false);
					sellProductPanel = new SellProductPanel(user);
					backgroundPanel.add(sellProductPanel);
					state = 4;
				}else if (state == 6) {
					inventoryPanel.setVisible(false);
					sellProductPanel = new SellProductPanel(user);
					backgroundPanel.add(sellProductPanel);
					state = 4;
				}
			}

		});
		
		// 포인트 충전
		poketPoint.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				chargeFrame=new ChargeFrame(user); 
				changePoint(chargeFrame.getUser());
			}
		});
		// 5. 마이페이지
		button5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("인벤토리 클릭");
				if (state == 1) {
					auctionPanel.setVisible(false);
					myPagePanel = new MyPagePanel(user);
					backgroundPanel.add(myPagePanel);
					state = 5;
				} else if (state == 2) {
					finishedPanel.setVisible(false);
					myPagePanel = new MyPagePanel(user);
					backgroundPanel.add(myPagePanel);
					state = 5;
				} else if (state == 3) {
					checkBidPanel.setVisible(false);
					myPagePanel = new MyPagePanel(user);
					backgroundPanel.add(myPagePanel);
					state = 5;
				} else if (state == 4) {
					sellProductPanel.setVisible(false);
					myPagePanel = new MyPagePanel(user);
					backgroundPanel.add(myPagePanel);
					state = 5;
				} else if (state == 5) {
					myPagePanel = new MyPagePanel(user);
					backgroundPanel.add(myPagePanel);
				} else if (state == 6) {
					inventoryPanel.setVisible(false);
					myPagePanel = new MyPagePanel(user);
					backgroundPanel.add(myPagePanel);
				}
			}
		});
		// 6. 인벤토리 이동
			inventoryButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (state == 1) {
							auctionPanel.setVisible(false);
<<<<<<< HEAD
							InventoryPanel_wonseok inventoryPanel = new InventoryPanel_wonseok(user);
=======
							inventoryPanel = new InventoryPanel();
>>>>>>> ab9557331f40ea40669eb783c52fd1b18afa2a7f
							backgroundPanel.add(inventoryPanel);
							state = 6;
						} else if (state == 2) {
							finishedPanel.setVisible(false);
<<<<<<< HEAD
							InventoryPanel_wonseok inventoryPanel = new InventoryPanel_wonseok(user);
=======
							inventoryPanel = new InventoryPanel();
>>>>>>> ab9557331f40ea40669eb783c52fd1b18afa2a7f
							backgroundPanel.add(inventoryPanel);
							state = 6;
						} else if (state == 3) {
							checkBidPanel.setVisible(false);
<<<<<<< HEAD
							InventoryPanel_wonseok inventoryPanel = new InventoryPanel_wonseok(user);
=======
							inventoryPanel = new InventoryPanel();
>>>>>>> ab9557331f40ea40669eb783c52fd1b18afa2a7f
							backgroundPanel.add(inventoryPanel);
							state = 6;
						} else if (state == 4) {
							sellProductPanel.setVisible(false);
<<<<<<< HEAD
							InventoryPanel_wonseok inventoryPanel = new InventoryPanel_wonseok(user);
=======
							inventoryPanel = new InventoryPanel();
>>>>>>> ab9557331f40ea40669eb783c52fd1b18afa2a7f
							backgroundPanel.add(inventoryPanel);
							state = 6;
						} else if (state == 5) {
							myPagePanel.setVisible(false);
<<<<<<< HEAD
							InventoryPanel_wonseok inventoryPanel = new InventoryPanel_wonseok(user);
							backgroundPanel.add(inventoryPanel);
							state = 6;
						} else if(state==6) {
							InventoryPanel_wonseok inventoryPanel = new InventoryPanel_wonseok(user);
=======
							inventoryPanel = new InventoryPanel();
							backgroundPanel.add(inventoryPanel);
							state = 6;
						} else if(state==6) {
							inventoryPanel = new InventoryPanel();
>>>>>>> ab9557331f40ea40669eb783c52fd1b18afa2a7f
							backgroundPanel.add(inventoryPanel);
						}

					}

				});
	}
	
	
	public static void main(String[] args) {
		new MainFrame(new UserDTO(0,"a","a","a",0));
	}
	

}