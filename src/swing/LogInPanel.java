package swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogInPanel extends JPanel {
	
	private JButton button;
	
	private JLabel label;
	private JTextField idField;
	private JTextField passwordField;
	private JLabel idLabel;
	private JLabel passwordLabel;
	
	public LogInPanel() {
		initData();
		setInitLayout();
	}

	private void setInitLayout() {
		setSize(getWidth(),getHeight());
		setLayout(null);
		setVisible(true);

		idLabel.setBounds(70, 380, 80, 20);
		idField.setBounds(160, 380, 80, 20);
		passwordLabel.setBounds(110, 410, 60, 20);
		passwordField.setBounds(160, 410, 80, 20);
		
		add(idLabel);
		add(idField);
		add(passwordField);
		add(button);
	}

	private void initData() {
		button=new JButton("로그인");
		idLabel=new JLabel("ID : ");
		idField = new JTextField(10);
		passwordLabel=new JLabel("비밀번호 : ");
		passwordField = new JTextField(10);
		
	}
	
	// 로그인 시 연결
	private void logIn() {
		button.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
			}
			
		});
	}

}
