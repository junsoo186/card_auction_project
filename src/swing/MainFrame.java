package swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	
	private AuctionPanel auctionPanel; // 진행중인 경매 패널
	private FinishedPanel finishedPanel; // 종료된 경매 패널
	private CheckBidPanel checkBidPanel; //시세 알아보기 패널
	private SellProductPanel sellProductPanel; // 경매 출품하기 패널
	private MyPagePanel myPagePanel; // 마이 페이지 패널
	
	private JLabel title;

	public MainFrame() {
		initData();
		setInitLayout();
		initListener();
	} 

	private void initData() {
		
		backgroundPanel=new JPanel();
		
		mainPanel=new JPanel();
		add(backgroundPanel);
	}

	private void setInitLayout() {
		setTitle("[카드 경매 사이트 포켓 옥션]");
		setSize(1920,1080);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel webTitle=new JLabel("즐거움이 살아 숨쉬는 카드 경매 사이트!");
		webTitle.setFont(new Font("Freesentation 7 Bold",Font.ITALIC,34));
		webTitle.setBounds(830,140,900,200);
		
		backgroundPanel.setSize(getWidth(),getHeight());
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.white);
	
		Icon backgroundIcon=new ImageIcon("image/back2.png");
		backgroundLabel=new JLabel(backgroundIcon);
		backgroundLabel.setSize(473,158);
		backgroundLabel.setLocation(370,150);
		backgroundLabel.setHorizontalAlignment(JLabel.CENTER);
		backgroundPanel.add(backgroundLabel);
		backgroundPanel.add(webTitle);
		
		button1=new JButton("진행 중인 경매");
		button2=new JButton("종료된 경매");
		button3=new JButton("시세 알아보기");
		button4=new JButton("경매 출품하기");
		button5=new JButton("마이 페이지");
		
		button1.setBounds(300,320,200,50);
		button2.setBounds(650,320,200,50);
		button3.setBounds(950,320,200,50);
		button4.setBounds(1250,320,200,50);
		button5.setBounds(1550,320,200,50);
		button1.setBackground(Color.BLUE.darker());
		button2.setBackground(Color.BLUE.darker());
		button3.setBackground(Color.BLUE.darker());
		button4.setBackground(Color.BLUE.darker());
		button5.setBackground(Color.BLUE.darker());
		button1.setFont(new Font("Freesentation 7 Bold",Font.BOLD,20));
		button2.setFont(new Font("Freesentation 7 Bold",Font.BOLD,20));
		button3.setFont(new Font("Freesentation 7 Bold",Font.BOLD,20));
		button4.setFont(new Font("Freesentation 7 Bold",Font.BOLD,20));
		button5.setFont(new Font("Freesentation 7 Bold",Font.BOLD,20));
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
		
		mainPanel=new JPanel();
		mainPanel.setSize(1920,50);
		mainPanel.setLocation(0,320);
		mainPanel.setBackground(Color.BLUE.darker());
		backgroundPanel.add(mainPanel);
		
		auctionPanel=new AuctionPanel();
		
		backgroundPanel.add(auctionPanel);
		
		setVisible(true);

	}
	
	private void initListener() {
		
		// 진행중인 경매 이동
		button1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				auctionPanel=new AuctionPanel();
				backgroundPanel.add(auctionPanel);
			}
		
		});
		
		// 종료된 경매 이동
		button2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				finishedPanel=new FinishedPanel();
				backgroundPanel.add(finishedPanel);
			}
		
		});
		
		// 시세 체크 이동
		button3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				checkBidPanel=new CheckBidPanel();
				backgroundPanel.add(checkBidPanel);
			}
		
		});
		
		//경매 출품 이동
		button4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sellProductPanel=new SellProductPanel();
				backgroundPanel.add(sellProductPanel);
			}
		
		});
		
		// 마이페이지 이동
		button5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				myPagePanel=new MyPagePanel();
				backgroundPanel.add(myPagePanel);
			}
		
		});
	}
	
	
	
public static void main(String[] args) {
	new MainFrame();
}

}
