package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.InventoryDTO;
import manager.DBConnectionManager;

public class InventoryDAO {

	// 해당 유저 번호에 카드 추가
	public void invenAdd(InventoryDTO dto) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.INVEN_ADD);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getCardId());
			pstmt.executeUpdate();
			System.out.println("보관함에 카드 추가완료");
		}
	}

	// 해당 유저 번호가 가지고 있는 카드 조회
	public void invenInfo(int id) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.INVEN_INFO_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("====================================");
				System.out.println("이미지경로 : " + rs.getString("c.url"));
				System.out.println("카드이름 : " + rs.getString("c.name"));
			}
		}
	}
}
