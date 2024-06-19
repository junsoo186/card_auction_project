package manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import lombok.Data;

@Data
public class SocketManager implements Runnable{

	private static Socket socket;
	private static PrintWriter userOrder;
	private static BufferedReader serverOrder;
	
	public SocketManager() {
		
	}
	
	public static void sendOrder(String order) {
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
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
