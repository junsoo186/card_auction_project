package manager;

import java.util.Timer;
import java.util.TimerTask;

public class AdManager extends Thread {
	
	Timer adTimmer;
	
	
	TimerTask task = new TimerTask() {
		
		@Override
		public void run() {
			
				System.out.println("5초뒤에 팝업");
			
		}
		

		
	};
	
	public AdManager() {
		adTimmer = new Timer();
		adTimmer.schedule(task,5000,5000);
		start();
	}
	
	
	

	public static void main(String[] args) {
		new AdManager();
	}
	
}
