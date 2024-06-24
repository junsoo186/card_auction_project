
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
import java.util.Vector;

import dao.InventoryDAO;
import dao.UserDAO;
import dto.AuctionDTO;
import dto.InventoryDTO;
import dto.UserDTO;

public class Server {

	private static Vector<Socket> client = new Vector<>(); // 접속자 소켓을 저장할 벡터
	private static PrintWriter serverOrder; // 서버가 사용자들에게 보내는 메세지
	private static ArrayList<Integer> productId; // 상품 id
	private static ArrayList<String> productName; // 상품 이름
	private static ArrayList<Integer> auctionList = new ArrayList<>(); // 경매 물품 리스트
	private static ArrayList<AuctionDTO> auctionData = new ArrayList<>();// 경매 물품 정보
	private static ArrayList<Integer> hour = new ArrayList<>(); // 시간 저장
	private static ArrayList<Integer> min = new ArrayList<>(); // 분 저장
	private static ArrayList<Integer> highBid = new ArrayList<>(); // 경매 현재 가격
	private static Random random = new Random();

	public Server() {
		try (ServerSocket server = new ServerSocket(5000)) {
			while (true) {
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
	private static void broadCast(String message) {
		for (Socket socket : client) {
			try {
				serverOrder = new PrintWriter(socket.getOutputStream(), true);
				serverOrder.println(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static class Service extends Thread {

		private Socket socket;

		private BufferedReader userOrder;
		private PrintWriter printWriter;

		public Service(Socket socket) {
			this.socket = socket;
		}

//		// 서버 -> 클라이언트 메세지 전송하기
//		private void sendOrder(String msg) {
//			printWriter.println(msg);
//		}

		@Override
		public void run() {
			try {
				userOrder = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				printWriter = new PrintWriter(socket.getOutputStream(), true);
				String message;
				UserDTO user = new UserDTO();
				InventoryDTO invenDTO = new InventoryDTO();
				// 처음에 들어온 사용자들에게 현재 경매물품 리스트 송출
				for (int i = 0; i < auctionList.size(); i++) {
					broadCast("list#" + auctionList.get(i));
				}
				while ((message = userOrder.readLine()) != null) {
					System.out.println(message + " Server에서 읽음 ");
					if (message.startsWith("chat")) {
						broadCast(message);
					}  else if (message.startsWith("sell")) {
						String[] sell = message.split("#");
						productName.add(sell[1]);
					} else if (message.startsWith("sendDB")) {
						try {
							String[] DB = message.split("#");
							user.setNickname(DB[1]);
							user.setName(DB[2]);
							user.setPassword(DB[3]);
							user.setPoint(500);
							// 회원가입시 카드 5개 랜덤으로 증정
							if (UserDAO.addUser(user)) {
								for (int i = 0; i < 5; i++) {
									int cardId = random.nextInt(4) + 1;
									invenDTO.setName(user.getName());
									invenDTO.setCardId(cardId);
									InventoryDAO.invenAdd(invenDTO);
								}
								System.out.println("DB보냄");
							} else {
								System.out.println("회원 가입 실패 !! 중복아이디");
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else if (message.startsWith("login")) {
						String[] login = message.split("#");
						if (UserDAO.loginUser(login[1], login[2])) {
							printWriter.println("success");
						} else {
							printWriter.println("wrong");
						}
					} else if (message.startsWith("bid")) {
						broadCast(message);
					} else if (message.startsWith("cash")) {
						String[] cash = message.split("#");
						UserDAO.updatePoint(cash[1], Integer.parseInt(cash[2]));
					} else if (message.startsWith("addCard")) {
						String[] card = message.split("#");

					} else if (message.startsWith("auction")) {
						String[] cardId = message.split("#");
						int id = Integer.valueOf(cardId[1]);
						int startBid = Integer.valueOf(cardId[2]);
						int hourDB = Integer.valueOf(cardId[3]);
						int minDB = Integer.valueOf(cardId[4]);
						System.out.println("카드 id 받아옴 : " + id);
						System.out.println("시간 받아옴 : " + hourDB);
						System.out.println("분 받아옴 : " + minDB);
						auctionList.add(id);
						hour.add(hourDB);
						min.add(minDB);
						broadCast("list#" + id + "#" + startBid + "#" + hourDB + "#" + minDB);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Server();
	}

}
