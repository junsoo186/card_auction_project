package manager;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

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
