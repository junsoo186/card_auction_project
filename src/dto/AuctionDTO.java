package dto;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AuctionDTO {
	private int id;
	private int userId;
	private int cardId;
	private int bidPrice;
	private String startDate; 
	private String endDate;
}
