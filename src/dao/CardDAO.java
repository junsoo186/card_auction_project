package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CardDTO;
import manager.DBConnectionManager;

//TODO Frame 에 연동시 수정

// 카드 이미지 경로는 DB에 저장, 서버에 이미지파일 저장

/**
 * create table card( id int auto_increment primary key, url varchar(100), name
 * varchar(50), price int );
 */
public class CardDAO {

	// 카드 추가 ( 이미지경로, 이름, 가격 )
	public static void addCard(CardDTO dto) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.CARD_ADD);
			pstmt.setString(1, dto.getUrl());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getPrice());
			pstmt.executeUpdate();
			System.out.println("카드 추가완료");
		}
	}

	// 모든 카드 정보 조회
	public static ArrayList<CardDTO> allCardList() throws SQLException {
		ArrayList<CardDTO> allCardList = new ArrayList<>();
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.CARD_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CardDTO cardDTO = new CardDTO();
				cardDTO.setId(rs.getInt("id"));
				cardDTO.setUrl(rs.getString("url"));
				cardDTO.setName(rs.getString("name"));
				cardDTO.setPrice(rs.getInt("price"));
				allCardList.add(cardDTO);
			}
		}
		return allCardList;
	}

	// 카드 번호로 조회
	public static CardDTO infoCard(int id) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.CARD_INFO_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			CardDTO card = new CardDTO();
			while (rs.next()) {
				card.setId(rs.getInt("id"));
				card.setName(rs.getString("name"));
				card.setUrl(rs.getString("url"));
			}
			return card;
		}
	}

	// 카드 이름으로 조회
	public static void infoCard(String name) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.CARD_INFO_NAME);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("====================================");
				System.out.println("카드번호 : " + rs.getInt("id"));
				System.out.println("이미지경로 : " + rs.getString("url"));
				System.out.println("카드이름 : " + rs.getString("name"));
				System.out.println("카드가격 : " + rs.getInt("price"));
			}
		}
	}

	// 카드 번호로 수정 ( 경로, 이름, 가격 )
	public static void updateCard(int id, CardDTO dto) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.CARD_UPDATE_ID);
			pstmt.setString(1, dto.getUrl());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setInt(4, id);
			pstmt.executeUpdate();
			System.out.println("카드 번호로 수정완료");
		}
	}

	// 카드 이름으로 수정 ( 경로, 이름, 가격 )
	public static void updateCard(String name, CardDTO dto) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.CARD_UPDATE_NAME);
			pstmt.setString(1, dto.getUrl());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setString(4, name);
			pstmt.executeUpdate();
			System.out.println("카드 이름으로 수정완료");
		}
	}

	// 카드 번호로 카드 삭제하기
	public static void deleteCard(int id) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.CARD_DELETE_ID);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println("해당 카드 번호 삭제완료");
		}
	}

	public static void cardAvg(String name) {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.CARD_AVGPRICE);
			pstmt.setString(1, name);
			pstmt.setString(2, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("====================================");
				System.out.println("카드번호 : " + rs.getInt("id"));
				System.out.println("이미지경로 : " + rs.getString("url"));
				System.out.println("카드이름 : " + rs.getString("name"));
				System.out.println("카드가격 : " + rs.getInt("price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 해당 id 카드의 가격 갱신 ( 종료된 옥션의 평균가로 )
	public static void setCardPrice(int id) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.CARD_SET_PRICE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
	}

}