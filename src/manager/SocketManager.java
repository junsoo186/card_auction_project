package manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import dto.AuctionDTO;
import dto.CardDTO;
import dto.UserDTO;
import lombok.Data;
import swing.LogInFrame;

@Data
public class SocketManager implements Runnable {

	private LogInFrame logInFrame;

	private Socket socket;
	private PrintWriter userOrder;
	private BufferedReader serverOrder;

	private UserDTO userDTO = new UserDTO();
	private CardDTO cardDTO = new CardDTO();
	private AuctionDTO auctionDTO = new AuctionDTO();

	private ArrayList<CardDTO> allCardList = new ArrayList<>(); // 모든 카드 목록
	private ArrayList<CardDTO> userInventory = new ArrayList<>(); // 보관함 카드 목록
	private ArrayList<AuctionDTO> endAuctionList = new ArrayList<>(); // 모든 종료된 경매목록

	private ArrayList<CardDTO> auctionList = new ArrayList<>(); // 카드 정보 저장
	private ArrayList<Integer> hour = new ArrayList<>(); // 사용자가 지정한 종료 시간
	private ArrayList<Integer> min = new ArrayList<>(); // 사용자가 지정한 종료 분
	private ArrayList<Integer> startBid = new ArrayList<>(); // 사용자가 지정한 시작가격

	private ArrayList<String> seller = new ArrayList<>(); // 판매자
	private ArrayList<Integer> highBid = new ArrayList<>(); // 최고 비드
	private ArrayList<String> bidUser = new ArrayList<>(); // 비드한 유저

	private boolean login = false;

	public void deleteData(int num) {
		auctionList.remove(num);
		hour.remove(num);
		min.remove(num);
		startBid.remove(num);
		highBid.remove(num);
		seller.remove(num);
		if (!bidUser.isEmpty()) {
			bidUser.remove(num);
		}
		System.out.println("데이터 지움");
	}

	public SocketManager(LogInFrame mContext) {
		this.logInFrame = mContext;
	}

	public void sendOrder(String order) {
		userOrder.println(order);
	}

	@Override
	public void run() {
		String message;
		try {
			socket = new Socket("192.168.0.29", 5000);
			userOrder = new PrintWriter(socket.getOutputStream(), true);
			serverOrder = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("소켓 프로토콜 스레드 실행");
			while ((message = serverOrder.readLine()) != null) {
				if (message.startsWith("list")) {
					CardDTO dto = new CardDTO();
					String[] cardId = message.split("#");
					int id = Integer.valueOf(cardId[1]);
					String name = cardId[2];
					String url = cardId[3];
					int startBidDB = Integer.valueOf(cardId[4]);
					int hourDB = Integer.valueOf(cardId[5]);
					int minDB = Integer.valueOf(cardId[6]);
					dto.setId(id);
					dto.setName(name);
					dto.setUrl(url);
					auctionList.add(dto);
					hour.add(hourDB);
					min.add(minDB);
					startBid.add(startBidDB);
					seller.add(cardId[7]);
					if (cardId.length > 8) {
						int price = Integer.valueOf(cardId[8]);
						highBid.add(price);
					} else {
						highBid.add(startBidDB);
					}
				} else if (message.startsWith("bid")) {
					String[] bid = message.split("#");
					int money = Integer.valueOf(bid[1]);
					int page = Integer.valueOf(bid[2]);
					String user = bid[3];
					highBid.add(page, money);
					bidUser.add(page, user);
				} else if (message.startsWith("auction")) {

				} else if (message.startsWith("EndAuctionList")) {
					String[] msg = message.split("#");
					AuctionDTO auctionDTO = new AuctionDTO();
					auctionDTO.setId(Integer.parseInt(msg[1]));
					auctionDTO.setName((msg[2]));
					auctionDTO.setCardId(Integer.parseInt(msg[3]));
					auctionDTO.setBidPrice(Integer.parseInt(msg[4]));
					auctionDTO.setStartDate((msg[5]));
					auctionDTO.setEndDate((msg[6]));
					endAuctionList.add(auctionDTO);
				} else if (message.startsWith("AllCardList")) {
					String[] msg = message.split("#");
					CardDTO cardDTO = new CardDTO();
					cardDTO.setId(Integer.parseInt(msg[1]));
					cardDTO.setUrl(msg[2]);
					cardDTO.setName(msg[3]);
					cardDTO.setPrice(Integer.parseInt(msg[4]));
					allCardList.add(cardDTO);
				} else if (message.startsWith("UserInventory")) {
					String[] msg = message.split("#");
					CardDTO cardDTO = new CardDTO();
					cardDTO.setId(Integer.parseInt(msg[1]));
					cardDTO.setUrl(msg[2]);
					cardDTO.setName(msg[3]);
					cardDTO.setPrice(Integer.parseInt(msg[4]));
					userInventory.add(cardDTO);
				} else if (message.startsWith("Login")) {
					login = true;
					String[] msg = message.split("#");
					userDTO.setName(msg[1]);
					userDTO.setPassword(msg[2]);
					userDTO.setNickname(msg[3]);
					userDTO.setPoint(Integer.parseInt(msg[4]));
				} else if (message.startsWith("userDTO")) {
					String[] msg = message.split("#");
					int point = Integer.valueOf(msg[4]);
					userDTO.setName(msg[1]);
					userDTO.setNickname(msg[2]);
					userDTO.setPassword(msg[3]);
					userDTO.setPoint(point);
				} else if (message.startsWith("failLogin")) {
					logInFrame.failLogin();
				} else if (message.startsWith("failSignUp")) {
					System.out.println("회원가입 실패.,...");
					logInFrame.failSignUp();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
