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
	private ArrayList<Integer> auctionList = new ArrayList<>();
	
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
					System.out.println("서버에서 카드 id 보냄 : " + id);
					auctionList.add(id);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
