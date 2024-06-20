package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import dto.CardDTO;
import dto.UserDTO;


public class SellProductPanel extends JPanel {
	
	private Icon profileIcon;
	private JLabel profileLabel;
	
	private AuctionPanel auctionPanel;
	private JPanel backgroundPanel;
	private JPanel infoPanel;
	
	private JLabel title;
	private JLabel name;
	private JLabel photo;
	private JLabel point;
	
	private JTextField nameField;
	private JTextField photoField;
	private JTextField pointField;

	private JButton sellButton;
	
	private int x = 500;
	private int y = 100;
	
	private UserDTO user;
	
	public SellProductPanel() {
		initData();
		setInitLayout();
	} 

	private void initData() {
		backgroundPanel=new JPanel();
	}
	
	
	private void setInitLayout() {
		setSize(1920,630);
		setLocation(0,400);
		setLayout(null);
		setBackground(Color.WHITE);
		add(backgroundPanel);
		
		title=new JLabel("경매 출품하기");
		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(890, 10, 800, 50);
		add(title);

		profileIcon = new ImageIcon("image/poketPoint.gif");
		profileLabel = new JLabel(profileIcon);
		profileLabel.setSize(279, 192);
		profileLabel.setBounds(790,80,300,300);
		profileLabel.setHorizontalAlignment(JLabel.CENTER);
		
		infoPanel=new JPanel();
		infoPanel.setBorder(new TitledBorder(new LineBorder(new Color(255,204,3),5)));
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setBounds(500,100,900,450);
		infoPanel.setLayout(null);
		
		name = new JLabel(" 카드 이름 : ");
		photo = new JLabel(" 사진 : ");
		point = new JLabel(" 포인트 : ");
		nameField=new JTextField(50);
		photoField=new JTextField(50);
		pointField=new JTextField(50);
		
		sellButton=new JButton("판매하기");
		sellButton.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		sellButton.setBounds(380, 360, 150, 50);
		sellButton.setBackground(new Color(255,204,3));
		sellButton.setBorderPainted(false);
		
		name.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		name.setBounds(330, 200, 300, 50);
		nameField.setBounds(440, 210, 150, 25);
		photo.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		photo.setBounds(360, 240, 300, 50);
		photoField.setBounds(440, 250, 150, 25);
		point.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		point.setBounds(350, 280, 350, 50);
		pointField.setBounds(440, 290, 150, 25);
		
		add(profileLabel);
		add(infoPanel);
		infoPanel.add(name);
		infoPanel.add(photo);
		infoPanel.add(point);
		infoPanel.add(nameField);
		infoPanel.add(photoField);
		infoPanel.add(pointField);
		infoPanel.add(sellButton);
	}
	
	private void initListener() {
		// 진행중인 경매 이동
		sellButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int id=0;
				String name=nameField.getText();
				String image=photoField.getText();
				int price=Integer.parseInt(pointField.getText());
				CardDTO card=new CardDTO(id,name,image,price);
				
				auctionPanel.cardList.add(card);
				JOptionPane.showMessageDialog(null,"경매 참여가 완료되었습니다.");
				System.out.println("완료!");
			}

		});
		
	}

}
