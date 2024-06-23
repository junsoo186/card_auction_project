package swing;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import dto.UserDTO;

public class SellProductPanel extends JPanel {

	private Icon profileIcon;
	private JLabel profileLabel;

	private AuctionPanel auctionPanel;
	private JPanel backgroundPanel;
	private JPanel infoPanel;

	private JLabel title;
	private JLabel name;
	private JButton selectCardButton;
	private JLabel point;
	private JLabel hour;
	private JLabel second;
	private JLabel timeLimit;

	private JTextField nameField;
	private JTextField hoursField;
	private JTextField minField;
	private JTextField pointField;

	private JButton sellButton;

	private int x = 500;
	private int y = 100;

	private UserDTO user;
	private Choice choice;

	public SellProductPanel(UserDTO user) {
		this.user = user;
		initData();
		setInitLayout();
		initListener();
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

		title = new JLabel("경매 출품하기");
		title.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 32));
		title.setBounds(890, 10, 800, 50);
		add(title);

		profileIcon = new ImageIcon("image/poketPoint.gif");
		profileLabel = new JLabel(profileIcon);
		profileLabel.setSize(279, 192);
		profileLabel.setBounds(790, 80, 300, 300);
		profileLabel.setHorizontalAlignment(JLabel.CENTER);

		infoPanel = new JPanel();
		infoPanel.setBorder(new TitledBorder(new LineBorder(new Color(255, 204, 3), 5)));
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setBounds(500, 100, 900, 450);
		infoPanel.setLayout(null);

		name = new JLabel(" 카드 고르기 ");
		point = new JLabel(" 포인트 : ");
		hour = new JLabel(" 시 ");
		second = new JLabel(" 분 ");
		timeLimit = new JLabel(" 마감 시간 : ");
		selectCardButton = new JButton("인벤토리 들어가기");
		selectCardButton.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		selectCardButton.setBackground(new Color(255, 204, 100));
		selectCardButton.setBounds(380, 360, 190, 100);
		hoursField = new JTextField(50);
		minField = new JTextField(50);
		pointField = new JTextField(50);

		sellButton = new JButton("판매하기");
		sellButton.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		sellButton.setBounds(380, 360, 150, 60);
		sellButton.setBackground(new Color(255, 204, 30));

		name.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		name.setBounds(330, 200, 300, 50);
		selectCardButton.setBounds(440, 210, 150, 25);
		point.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		timeLimit.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		hour.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		second.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 20));
		point.setBounds(350, 270, 350, 50);
		timeLimit.setBounds(340, 250, 100, 25);
		hour.setBounds(492, 251, 50, 25);
		second.setBounds(580, 251, 50, 25);
		hoursField.setBounds(440, 251, 50, 25);
		minField.setBounds(530, 251, 50, 25);
		pointField.setBounds(440, 290, 80, 25);

		add(profileLabel);
		add(infoPanel);
		infoPanel.add(name);
		infoPanel.add(point);
		infoPanel.add(selectCardButton);
		infoPanel.add(hour);
		infoPanel.add(second);
		infoPanel.add(timeLimit);
		infoPanel.add(hoursField);
		infoPanel.add(minField);
		infoPanel.add(sellButton);
		infoPanel.add(pointField);
	}

	private void initListener() {
		// 진행중인 경매 이동
		sellButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int price = Integer.parseInt(pointField.getText());
				JOptionPane.showMessageDialog(null, "경매 참여가 완료되었습니다.");
				System.out.println("완료!");
			}

		});
		selectCardButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new SelectInventoryFrame(user);
			}

		});

	}

	public int getThisHour() {
		int give_Hour = Integer.parseInt(hoursField.getText());
		return give_Hour;
	}

	public int getThisMin() {
		int give_Min = Integer.parseInt(minField.getText());
		return give_Min;
	}

}