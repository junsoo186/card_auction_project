package dto;

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
	private String name;
	private int cardId;
	private int bidPrice;
	private String startDate;
	private String endDate;
}
