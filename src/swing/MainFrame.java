package swing;

import java.awt.Color;

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
		
		private JLabel title;

		public MainFrame() {
			initData();
			setInitLayout();
		} 

		private void initData() {
			
			backgroundPanel=new JPanel();
			
			mainPanel=new JPanel();
			add(backgroundPanel);
			
			title=new JLabel("카드 경매 사이트에 오신 것을 환영합니다.");
		}

		private void setInitLayout() {
			setTitle("[카드 경매 사이트]");
			setSize(900,700);
			setLocationRelativeTo(null);
			setResizable(false);
			setLayout(null);
			getContentPane().setBackground(Color.white);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JTabbedPane tabPane=new JTabbedPane(JTabbedPane.NORTH);
			mainPanel=new JPanel();
			
			tabPane.setBounds(0,0,getWidth(),getHeight());
			backgroundPanel.add(tabPane);
			
			tabPane.addTab("로그인",null,LogInPanel,null);
			tabPane.addTab("진행 중인 경매",null,AuctionPanel,null);
			tabPane.addTab("종료된 경매",null,FinishPanel,null);
			tabPane.addTab("시세 확인하기",null,PricePanel,null);
			tabPane.addTab("경매 참여하기",null,JoinAuctionPanel,null);
			tabPane.addTab("마이페이지",null,MyPagePanel,null);
			

			add(mainPanel);
			setVisible(true);

		}
		
	public static void main(String[] args) {
		new MainFrame();
	}
	
}
	
