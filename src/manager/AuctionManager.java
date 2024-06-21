package manager;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

import dto.AuctionDTO;
import dto.CardDTO;
import dto.UserDTO;
import lombok.Data;
import swing.SellProductPanel; 

@Data
public class AuctionManager extends Thread {

	Server mContext;
	int room_Id;
	// user
	UserDTO user_DTO;
	AuctionDTO dto = new AuctionDTO();
	SellProductPanel sellProductPanel;
	CardDTO card_DTO;

	// 시간 관련 변수
	long current_time; // 로컬 컴퓨터 시간 변수 담을
	
	int set_Hour ; // 수정예정
	int set_Min ; // 수정예정
	
	LocalDateTime time = LocalDateTime.now();
	LocalDateTime endTime;
	// BID 관련 변수
	int startBid;
	int highbid;

	public AuctionManager(int room, int startBid,
			UserDTO user_DTO, CardDTO card_DTO,
			Server mContext, int moreBid) {
		this.room_Id = room;
		sellProductPanel = new SellProductPanel(user_DTO);
		endTime = LocalDateTime.of(2024, 06, 21,sellProductPanel.getThisHour(),
				sellProductPanel.getThisMin(), 0);
		this.startBid = startBid;
		highbid = startBid;
		// DTO 
		this.user_DTO = user_DTO;
		this.card_DTO = card_DTO;
		Vector<UserDTO> userID = new Vector<>();
		start();
	}
	@Override
	public void run() {

		while (true) {
			time = LocalDateTime.now();
			long remainSecond = time.until(endTime, ChronoUnit.SECONDS);
			see(remainSecond);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void see(long remainSecond) {
		this.current_time = remainSecond;
	}

}
