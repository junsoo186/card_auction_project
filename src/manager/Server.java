
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

import javax.swing.JOptionPane;

import dao.AuctionDAO;
import dao.CardDAO;
import dao.InventoryDAO;
import dao.UserDAO;
import dto.AuctionDTO;
import dto.CardDTO;
import dto.InventoryDTO;
import dto.UserDTO;
import swing.LogInFrame;

public class Server {

	private static Vector<Socket> client = new Vector<>(); // 접속자 소켓을 저장할 벡터
	private static PrintWriter serverOrder; // 서버가 사용자들에게 보내는 메세지
	private static ArrayList<Integer> productId; // 상품 id
	private static ArrayList<String> productName; // 상품 이름
	private static ArrayList<CardDTO> auctionList = new ArrayList<>(); // 경매 물품 리스트
	private static ArrayList<AuctionDTO> auctionData = new ArrayList<>();// 경매 물품 정보
	private static ArrayList<Integer> hour = new ArrayList<>(); // 시간 저장
	private static ArrayList<Integer> min = new ArrayList<>(); // 분 저장
	private static ArrayList<Integer> highBid = new ArrayList<>(); // 경매 현재 가격
	private static ArrayList<Integer> startBid = new ArrayList<>(); // 경매 시작 가격
	private static ArrayList<String> seller = new ArrayList<>(); // 판매자 이름
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

	private class Service extends Thread {

		private Socket socket;

		private BufferedReader userOrder;
		private PrintWriter printWriter;

		public Service(Socket socket) {
			this.socket = socket;
		}

		// 서버 -> 클라이언트 메세지 전송하기
		private void sendOrder(String msg) {
			printWriter.println(msg);
		}

		@Override
		public void run() {
			try {
				System.out.println("서버프로토콜 스레드실행");
				userOrder = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				printWriter = new PrintWriter(socket.getOutputStream(), true);
				String message;
				UserDTO user = new UserDTO();
				InventoryDTO invenDTO = new InventoryDTO();
				// 처음에 들어온 사용자들에게 현재 경매물품 리스트 송출
				for (int i = 0; i < auctionList.size(); i++) {
					sendOrder("list#" + auctionList.get(i).getId() + "#" +auctionList.get(i).getName()
							+ "#" + auctionList.get(i).getUrl() + "#" + startBid.get(i) + "#" + hour.get(i) + "#" + min.get(i) + 
							"#" + highBid.get(i));
					System.out.println("옥션 갯수 : " + auctionList.size());
				}
				while ((message = userOrder.readLine()) != null) {
					System.out.println(message + " Server에서 읽음 ");
					if (message.startsWith("chat")) {
					} else if (message.startsWith("sell")) {
						String[] sell = message.split("#");
						productName.add(sell[1]);
					} else if (message.startsWith("sendDB")) {
						try {
							String[] DB = message.split("#");
							user.setNickname(DB[1]);
							user.setName(DB[2]);
							user.setPassword(DB[3]);
							user.setPoint(500);
							// 회원가입시 카드 15개 랜덤으로 증정
							if (UserDAO.addUser(user)) {
								for (int i = 0; i < 15; i++) {
									int cardId = random.nextInt(14) + 1;
									invenDTO.setName(user.getName());
									invenDTO.setCardId(cardId);
									InventoryDAO.invenAdd(invenDTO);
								}
								System.out.println("DB보냄");
							} else {
								System.out.println("회원 가입 실패 !! 중복아이디");
								sendOrder("failSignUp");
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else if (message.startsWith("Login")) {
						String[] login = message.split("#");
						if (UserDAO.CheckLogin(login[1], login[2])) {
							UserDTO userDTO = new UserDTO();
							userDTO = UserDAO.infoUser(login[1]);
							sendOrder("Login#" + userDTO.getName() + "#" + userDTO.getPassword() + "#"
									+ userDTO.getNickname() + "#" + userDTO.getPoint());
						} else {
							sendOrder("failLogin");
						}
					} else if (message.startsWith("bid")) {
						String[] msg = message.split("#");
						int price = Integer.valueOf(msg[1]);
						int num = Integer.valueOf(msg[2]);
						highBid.add(num, price);
						System.out.println(highBid.get(num) + " 하이비드 변경 완료");
						broadCast(message);
					} else if (message.startsWith("cash")) {
						String[] cash = message.split("#");
						UserDAO.updatePoint(cash[1], Integer.parseInt(cash[2]));
					} else if (message.startsWith("addCard")) {
						String[] card = message.split("#");

					} else if (message.startsWith("auction")) {
						String[] cardId = message.split("#");
						int id = Integer.valueOf(cardId[1]);
						int price = Integer.valueOf(cardId[2]);
						int hourDB = Integer.valueOf(cardId[3]);
						int minDB = Integer.valueOf(cardId[4]);
						String name = cardId[5];
						System.out.println("카드 id 받아옴 : " + id);
						System.out.println("시간 받아옴 : " + hourDB);
						System.out.println("분 받아옴 : " + minDB);
						hour.add(hourDB);
						min.add(minDB);
						startBid.add(price);
						highBid.add(price);
						seller.add(name);
						System.out.println("판매자 : " +  name);
						CardDTO dto = new CardDTO();
						dto = CardDAO.infoCard(id);
						auctionList.add(dto);
						System.out.println(dto.getName() + "카드 이름");
						System.out.println(dto.getUrl() + " 카드 가격");
						broadCast("list#" + dto.getId() + "#" + dto.getName() + "#" + dto.getUrl() + "#" + price
								+ "#" + hourDB + "#" + minDB);

					} else if (message.startsWith("EndAuctionList")) {
						ArrayList<AuctionDTO> endAuctionList = new ArrayList<>();
						endAuctionList = AuctionDAO.endAuctionList();
						for (int i = 0; i < endAuctionList.size(); i++) {
							sendOrder("EndAuctionList#" + endAuctionList.get(i).getId() + "#"
									+ endAuctionList.get(i).getName() + "#" + endAuctionList.get(i).getCardId() + "#"
									+ endAuctionList.get(i).getBidPrice() + "#" + endAuctionList.get(i).getStartDate()
									+ "#" + endAuctionList.get(i).getEndDate());
						}
					} else if (message.startsWith("AllCardList")) {
						ArrayList<CardDTO> allCardList = new ArrayList<>();
						allCardList = CardDAO.allCardList();
						for (int i = 0; i < allCardList.size(); i++) {
							sendOrder("AllCardList#" + allCardList.get(i).getId() + "#" + allCardList.get(i).getUrl()
									+ "#" + allCardList.get(i).getName() + "#" + allCardList.get(i).getPrice());
						}
					} else if (message.startsWith("UserInventory")) {
						String[] msg = message.split("#");
						ArrayList<CardDTO> userInventory = new ArrayList<>();
						userInventory = InventoryDAO.invenInfo(msg[1]);
						for (int i = 0; i < userInventory.size(); i++) {
							sendOrder("UserInventory#" + userInventory.get(i).getId() + "#"
									+ userInventory.get(i).getUrl() + "#" + userInventory.get(i).getName() + "#"
									+ userInventory.get(i).getPrice());
						}
					} else if (message.startsWith("endAuctionDB")) {
						String[] msg = message.split("#");
						AuctionDTO dto = new AuctionDTO();
						InventoryDTO inven = new InventoryDTO();
						InventoryDTO inven2 = new InventoryDTO();
						int price = Integer.valueOf(msg[3]);
						int card = Integer.valueOf(msg[4]);
						int num = Integer.valueOf(msg[7]);
						dto.setBidPrice(price);
						dto.setCardId(card);
						dto.setEndDate(msg[1]);
						dto.setStartDate(msg[2]);
						dto.setName(msg[5]);
						inven.setCardId(card);
						inven.setName(msg[5]);
						InventoryDAO.invenAdd(inven); // 구매 유저 카드 추가
						System.out.println(msg[5] + " : 카드 추가");
						inven2.setCardId(card);
						inven2.setName(seller.get(num));
						InventoryDAO.invenRemove(inven); // 판매 유저 카드 제거
						System.out.println(seller.get(num) + "  : 카드 제거");
						AuctionDAO.addAuction(dto);
						CardDAO.setCardPrice(card); // 카드가격갱신(옥션평균가로)
						UserDAO.subtractPoint(msg[6], price); // 구매 유저 포인트 제거
						UserDAO.addPoint(seller.get(num), price); // 판매 유저 포인트 추가
					} else if (message.startsWith("refresh")) {
						String[] msg = message.split("#");
						UserDTO dto = new UserDTO();
						dto = UserDAO.infoUser(msg[1]);
						sendOrder("userDTO#" + dto.getName() + "#" + dto.getNickname() + "#" + dto.getPassword() + "#"
								+ dto.getPoint());
						System.out.println("aaa"+dto.getNickname());
						System.out.println("aaa"+dto.getPassword());
					} else if (message.startsWith("remove")) {
						String[] msg = message.split("#");
						int num = Integer.valueOf(msg[1]);
						auctionList.remove(num);
						hour.remove(num);
						min.remove(num);
						startBid.remove(num);
						highBid.remove(num);
						seller.remove(num);
						System.out.println("경매 데이터 삭제 완료!!!!");
						
						// 회원 정보 수정 프로토콜
					} else if(message.startsWith("updateUserInfo")) {
						String [] msg=message.split("#");
						UserDAO.updateUser(msg[1], msg[2], msg[3]);
						System.out.println("회원 정보 수정 완료");
						JOptionPane.showMessageDialog(null,"회원 정보 수정이 완료되었습니다.");
						
						// 회원 탈퇴 프로토콜
					} else if(message.startsWith("quitUser")) {
						String [] msg=message.split("#");
						UserDAO.deleteUser(msg[1]);
						System.out.println("회원 탈퇴 완료");
						JOptionPane.showMessageDialog(null,"회원 탈퇴가 완료되었습니다.");
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