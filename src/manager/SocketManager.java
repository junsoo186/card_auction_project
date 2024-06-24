package manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

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

	private ArrayList<Integer> auctionList = new ArrayList<>(); // 카드 id 저장
	private ArrayList<Integer> hour = new ArrayList<>(); // 사용자가 지정한 종료 시간
	private ArrayList<Integer> min = new ArrayList<>(); // 사용자가 지정한 종료 분
	private ArrayList<Integer> startBid = new ArrayList<>(); // 사용자가 지정한 시작가격

	public SocketManager() {
	}

	public void sendOrder(String order) {
		userOrder.println(order);
	}

	@Override
	public void run() {
		String message;
		try {
			socket = new Socket("localhost", 5000);
			userOrder = new PrintWriter(socket.getOutputStream(), true);
			serverOrder = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while ((message = serverOrder.readLine()) != null) {
				System.out.println(message + " Socket에서 읽음");
				if (message.startsWith("list")) {
					String[] cardId = message.split("#");
					int id = Integer.valueOf(cardId[1]);
					int startBidDB = Integer.valueOf(cardId[2]);
					int hourDB = Integer.valueOf(cardId[3]);
					int minDB = Integer.valueOf(cardId[4]);
					System.out.println("서버에서 카드 id 보냄 : " + id);
					System.out.println("서버에서 보낸 분 : " + minDB);
					auctionList.add(id);
					hour.add(hourDB);
					min.add(minDB);
					startBid.add(startBidDB);
				} else if (message.startsWith("auction")) {

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
