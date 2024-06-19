package manager;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import dao.AuctionDAO;
import dto.AuctionDTO;
import dto.CardDTO;
import dto.UserDTO;

public class AuctionManager extends Thread {
	
	Server mContext;
	
	// user 
	UserDTO maker;
	Vector<UserDTO> visiter = new Vector<>();
	AuctionDTO dto = new AuctionDTO();
	
	CardDTO card;
	

	// 시간 관련 변수
	LocalTime time = LocalTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
	static String h;
	static String m;
	// BID 관련 변수
	static int startBid;
	int highbid;
	int moreBid;
	
	
	public AuctionManager(String h, String m, int startBid, UserDTO maker , CardDTO card ,Server mContext, int moreBid) {
		
		this.h = h;
		this.m = m;
		this.startBid = startBid;
		
		// DTO     
		this.maker = maker;
		this.card = card;
		
		
		
		
	
	}
	

	@Override
	public void run() {
		   while (true) {
	            time = LocalTime.now();
	            System.out.println(time.format(formatter));
	            try {
	                Thread.sleep(1000);
	                
	                
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            if (time.format(formatter).equals(h+"시"+m+"분"+"00초")){
	            	 timeOver();
	            	 
	                break;
	            }
	            ;
	        }

	}

	public void timeOver() {
		System.out.println("경매 종료");
		AuctionDAO dao = new AuctionDAO();
		
	}
	
	public  void addBid() {
		this.startBid += moreBid; 
	}
	
	
	public static void main(String[] args) {
		new AuctionManager(h, m, startBid, null, null, null, startBid);
	}

}
