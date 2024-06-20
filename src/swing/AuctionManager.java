package swing;

import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import dao.AuctionDAO;
import dto.AuctionDTO;
import dto.CardDTO;
import dto.UserDTO;
import lombok.Data;
import manager.Server;

@Data
public class AuctionManager extends Thread {
	
	Server mContext;
	int roomId;
	// user 
	UserDTO maker;
	static AuctionDTO dto = new AuctionDTO();
	CardDTO card;
	

	// 시간 관련 변수
	LocalTime time = LocalTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
	static String h;
	static String m;
	// BID 관련 변수
	int startBid;
	int highbid;

	
	
	public AuctionManager(int roomId, String h, String m, int startBid, UserDTO maker , CardDTO card ,Server mContext, int moreBid) {
		this.roomId = roomId;
		this.h = h;
		this.m = m;
		this.startBid = startBid;
		highbid = startBid;
		// DTO     
		this.maker = maker;
		this.card = card;
		
		Vector<UserDTO> userID = new Vector<>();
		start();
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
		dto.setBidPrice(highbid);
		dto.setEndDate(h+"시"+m+"분"+"00초");
	try {
		dao.addAuction(dto);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
		

	
	

}
