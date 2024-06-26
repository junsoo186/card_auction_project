package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dto.CardDTO;
import dto.UserDTO;
import manager.SocketManager;

public class SellFrame extends JFrame {

	private CardDTO cardDTO;
	private UserDTO userDTO;

	private JPanel backgroundPanel;
	private JTextField addPriceField;
	private JTextField addEndhour;
	private JTextField addEndmin;
	private JButton exitButton;
	private JButton decidePriceButton;
	private SocketManager socket;
	private MainFrame mContext;

	public SellFrame(UserDTO userDTO, CardDTO cardDTO, SocketManager socket , MainFrame mContext) {
		System.out.println("가격제시 생성");
		this.socket = socket;
		this.cardDTO = cardDTO;
		this.userDTO = userDTO;
		this.mContext = mContext;
		setInitLayout();
		initListener();
		addEventListener();
	}

	private void setInitLayout() {
		setTitle("[카드 판매하기]");
		setSize(500, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage("image/파비콘2.png"));
		setLayout(null);
		getContentPane().setBackground(new Color(255, 204, 3));

		backgroundPanel = new JPanel();
		backgroundPanel.setBounds(80, 60, 350, 500);
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.WHITE);
		add(backgroundPanel);

		JLabel cardTitle = new JLabel(cardDTO.getName());
		JLabel guidText = new JLabel("시작 가격을 설정해주세요 (평균 시세 :" + cardDTO.getPrice() + ")");
		JLabel guidText2 = new JLabel("경매를 끝내실 시간을 설정해주세요 시/분");
		cardTitle.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 30));
		guidText.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 18));
		guidText2.setFont(new Font("Freesentation 7 Bold", Font.BOLD, 18));
		addPriceField = new JTextField(20);
		decidePriceButton = new JButton("경매 시작");
		exitButton = new JButton("나가기");
		addEndhour = new JTextField();
		addEndmin = new JTextField();
		cardTitle.setBounds(100, 20, 400, 50);
		guidText.setBounds(30, 250, 400, 50);
		guidText2.setBounds(30, 330, 400, 50);
		addPriceField.setBounds(80, 300, 200, 30);
		addEndhour.setBounds(80, 380, 90, 30);
		addEndmin.setBounds(185, 380, 90, 30);
		decidePriceButton.setBounds(80, 425, 90, 30);
		exitButton.setBounds(185, 425, 90, 30);

		backgroundPanel.add(cardTitle);
		backgroundPanel.add(guidText);
		backgroundPanel.add(guidText2);
		backgroundPanel.add(addPriceField);
		backgroundPanel.add(decidePriceButton);
		backgroundPanel.add(exitButton);
		backgroundPanel.add(addEndmin);
		backgroundPanel.add(addEndhour);

		Icon cardImage = new ImageIcon(cardDTO.getUrl());
		JLabel cardLabel = new JLabel();
		cardLabel.setIcon(cardImage);
		cardLabel.setBounds(120, 80, 120, 167);
		backgroundPanel.add(cardLabel);

		setVisible(true);
	}

	private void initListener() {
		decidePriceButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				socket.sendOrder("auction#" + cardDTO.getId() + "#" + addPriceField.getText() + "#"
						+ addEndhour.getText() + "#" + addEndmin.getText() + "#" + userDTO.getName());
				System.out.println("서버에 카드 아이디 전송");
				dispose();
			}

		});
		exitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "입찰을 종료합니다");
				dispose();
			}
		});
	}

	private void addEventListener() {

	}
}
