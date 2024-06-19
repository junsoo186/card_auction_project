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
public class CardDTO {
	private int id;
	private String url; // 경로는 DB에 저장, 서버에 이미지파일 저장
	private String name;
	private int price;
}
