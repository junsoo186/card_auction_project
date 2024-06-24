package manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import lombok.Data;
import swing.AuctionPanel;
import swing.LogInFrame;

@Data
public class SocketManager implements Runnable{

	private  Socket socket;
	private  PrintWriter userOrder;
	private BufferedReader serverOrder;
	private ArrayList<Integer> auctionList = new ArrayList<>(); // 카드 id 저장
	private ArrayList<Integer> hour = new ArrayList<>(); // 사용자가 지정한 종료 시간
	private ArrayList<Integer> min = new ArrayList<>(); // 사용자가 지정한 종료 분
	private ArrayList<Integer> startBid = new ArrayList<>(); // 사용자가 지정한 시작가격
	private ArrayList<Integer> highBid = new ArrayList<>(); // 최고 비드
	
	public SocketManager() {
	}
	
	public void sendOrder(String order) {
		userOrder.println(order);
	}

	@Override
	public void run() {
		String message;
		try {
			socket = new Socket("localhost",5000);
			userOrder = new PrintWriter(socket.getOutputStream(), true);
			serverOrder = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while((message = serverOrder.readLine()) != null) {
				if(message.startsWith("list")) {
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
				} else if (message.startsWith("bid")) {
					String[] bid = message.split("#");
					int page = Integer.valueOf(bid[2]);
					int money = Integer.valueOf(bid[1]);
					highBid.add(page,money);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
