package manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.Vector;

import org.w3c.dom.UserDataHandler;

import dao.UserDAO;
import dto.CardDTO;
import dto.UserDTO;
import swing.MainFrame;
import swing.ProductButton;

public class Server {
	
	private static Vector<Socket>client = new Vector<>(); // 접속자 소켓을 저장할 벡터
	private static PrintWriter serverOrder; // 서버가 사용자들에게 보내는 메세지
	private static MainFrame mconText;
	private static ArrayList<Integer> productId; // 상품 id
	private static ArrayList<String> productName; // 상품 이름
	private static Random random = new Random();
	
	public Server() {
		try (ServerSocket server = new ServerSocket(5000)){
			
			while(true) {
				Socket sample = server.accept(); // 서버에 접속자가 들어올떄마다 임시로 소켓에 저장
				client.add(sample);
				System.out.println(client.size());
				int socketNum = client.size() - 1;
				new Service(client.get(socketNum)).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 서버가 사용자들에게 명령을 보내는 메소드
	private static void broadCast (String message) { 
		for (Socket socket : client) {
			try {
				serverOrder = new PrintWriter(socket.getOutputStream(),true);
				serverOrder.write(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private static class Service extends Thread {
		
		private Socket socket;
		
		public Service(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			try (BufferedReader userOrder = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter printWriter = new PrintWriter(socket.getOutputStream())){
				String message;
				ArrayList<CardDTO>cardDto = new ArrayList<>();
				UserDTO user = new UserDTO();
				UserDAO dao = new UserDAO();
				while((message = userOrder.readLine()) != null) {
					System.out.println("와일문 작동");
					if(message.startsWith("chat")) {
						broadCast(message);
					} else if (message.startsWith("bid")) {
						String[] bid = message.split("#");
						int id = Integer.valueOf(bid[1]);
						int bidmoney = Integer.valueOf(bid[2]);
						if(productId.get(id) < bidmoney) {
							productId.set(id, bidmoney);
						}
					} else if (message.startsWith("sell")) {
						String[] sell = message.split("#");
						productName.add(sell[1]);
					}  else if (message.startsWith("sendDB")) {
						try {
							String[] DB = message.split("#");
							user.setNickname(DB[1]);
							user.setName(DB[2]);
							user.setPassword(DB[3]);
							System.out.println("DB보냄");
							
							dao.addUser(user);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else if (message.startsWith("login")) {
						String[] login = message.split("#");
						if(dao.loginUser(login[1], login[2])) {
							user.setName(login[1]);
							user.setPassword(login[2]);
						} else {
							printWriter.println("wrong");
						}
					} else if (message.startsWith("cash")) {
						String[] cash = message.split("#");
						
					} else if (message.startsWith("addCard")) {
						String[] card = message.split("#");
						
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new Server();
	}
	
}
