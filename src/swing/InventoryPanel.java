package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dao.InventoryDAO;
import dto.CardDTO;
import dto.InventoryDTO;
import dto.UserDTO;

public class InventoryPanel extends JPanel {
	
	private ArrayList<JButton> productList = new ArrayList<>();
	private ArrayList<String> productTitleList = new ArrayList<>();
	private ArrayList<CardDTO> userInventory = new ArrayList<>();
	private JPanel backgroundPanel;
	private JLabel title;
	private JScrollPane scrollPane;
	private UserDTO user;
	private MainFrame mconText;
	private InventoryDetailedPanel detail;
	
	ArrayList<JButton>product = new ArrayList<>();
	ArrayList<Integer>serialNum = new ArrayList<>();
	InventoryDAO inven = new InventoryDAO();
	private int x = 500;
	private int y = 100;
	
	public InventoryPanel(UserDTO user,MainFrame mconText) {
		this.mconText = mconText;
		this.user = user;
		initData();
		setInitLayout();
	} 

	private void initData() {
		backgroundPanel=new JPanel();
	}
	
	public void createProduct(String image) {
		System.out.println(serialNum.size());  // 시리얼 넘버 사이즈로 product버튼 인덱스 번호 지정
		product.get(serialNum.size()).setIcon(new ImageIcon(image)); // 유저가 올린 판매카드 이미지 버튼에 삽입 
		serialNum.add(1); // 시리얼 넘버 사이즈도 증가 
	}
	
	public void ProductButton() {
		// 처음 생성 될때 8개 버튼 생성
		for(int i = 0; i < 16; i++) {
			product.add(i, new JButton());
			product.get(i).setBounds(x,y,150,200);
			
			if((i/4)>=1 && i%4==0) {
				x=500;
				y+=300;
			} else {
				x+=200;
			}
			add(product.get(i));
			setVisible(true);
		}
	}
	
	private void setInitLayout() {
		setSize(1920,630);
		setLocation(0,400);
		setLayout(null);
		setBackground(Color.WHITE);
		add(backgroundPanel);
		
		scrollPane=new JScrollPane();	
		scrollPane.setBounds(4,4,950,330);
		ProductButton();
		
		try {
			userInventory = inven.invenInfo(user.getName()); // 유저가 가지고있는 카드 목록 호출
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < userInventory.size(); i++) {
			createProduct(userInventory.get(i).getUrl()); // 유저가 가지고있는 카드 url 삽입
		}
		System.out.println("카드 등록 까지 ");
		addActionListner(userInventory);
		
		
		JLabel title=new JLabel("보유 카드 확인하기"+"("+product.size()+")");
		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(860, 10, 800, 50);
		add(title);
		
	}

	// 카드 정보 조회기능 추가
	public void addActionListner(ArrayList<CardDTO> userInventory) {
		
		for(int i = 0; i < userInventory.size(); i++) {
			int num = i;
			product.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setVisible(false);
					detail = new InventoryDetailedPanel(userInventory.get(num),user);
					mconText.getBackgroundPanel().add(detail);
					mconText.repaint();
				}
			});
		}
		
	}
	
	public void deletePanel() {
		detail.setVisible(false);
		mconText.repaint();
	}
}
