package manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
	
	private static Vector<Socket>client = new Vector<>(); // 접속자 소켓을 저장할 벡터
	private static PrintWriter serverOrder; // 서버가 사용자들에게 보내는 메세지
	
	public Server() {
		try (ServerSocket server = new ServerSocket(5000)){
			
			while(true) {
				Socket sample = server.accept(); // 서버에 접속자가 들어올떄마다 임시로 소켓에 저장
				client.add(sample);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 서버가 사용자들에게 명령을 보내는 메소드
	private static void broadCast (String message) { 
		for (Socket socket : client) {
			try {
				serverOrder = new PrintWriter(socket.getOutputStream());
				serverOrder.write(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class Service extends Thread {
		
		private Socket socket;
		public Service(Socket socket,int num) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			try (BufferedReader userOrder = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
				String message;
				while((message = userOrder.readLine()) != null) {
					broadCast(message);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
