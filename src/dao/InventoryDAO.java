package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CardDTO;
import dto.InventoryDTO;
import manager.DBConnectionManager;

public class InventoryDAO {

	// 해당 유저 번호에 카드 추가
	public static void invenAdd(InventoryDTO dto) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.INVEN_ADD);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getCardId());
			pstmt.executeUpdate();
			System.out.println("보관함에 카드 추가완료");
		}
	}

	// 해당 유저 번호에 카드 제거
	public static void invenRemove(InventoryDTO dto) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.INVEN_REMOVE);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getCardId());
			pstmt.executeUpdate();
			System.out.println("보관함에 카드 제거완료");
		}
	}
	
	// 해당 유저 번호가 가지고 있는 카드이미지경로, 카드이름 조회
	public static ArrayList<CardDTO> invenInfo(String name) throws SQLException {
		ArrayList<CardDTO> card = new ArrayList<>();
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.INVEN_INFO_ID);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CardDTO add = new CardDTO();
				add.setId(rs.getInt("c.id"));
				add.setName(rs.getString("c.name"));
				add.setPrice(rs.getInt("c.price"));
				add.setUrl(rs.getString("c.url"));
				card.add(add);
			}
			return card;
		}
	}
}
