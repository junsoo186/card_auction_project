package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.CardDTO;
import manager.DBConnectionManager;

//TODO Frame 에 연동시 수정

// 카드 이미지 경로는 DB에 저장, 서버에 이미지파일 저장

/**
 * create table card(
    id int auto_increment primary key,
    url varchar(100),
    name varchar(50),
    price int
	);
*/
public class CardDAO {

	
	private static final String ADD_CARD = " INSERT INTO card(url, name, price) VALUES(?, ?, ?) ";
	private static final String INFO_ID_CARD = " SELECT * FROM card WHERE id = ? ";
	private static final String INFO_NAME_CARD = " SELECT * FROM card WHERE name = ? ";
	private static final String UPDATE_ID_CARD = " UPDATE card SET url = ?, name = ?, price = ? WHERE id = ? ";
	private static final String UPDATE_NAME_CARD = " UPDATE card SET url = ?, name = ?, price = ? WHERE name = ? ";
	private static final String DELETE_ID_CARD = " DELETE FROM card WHERE id = ? ";

	// 카드 추가 ( 이미지경로, 이름, 가격 )
	public void addCard(CardDTO dto) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(ADD_CARD);
			pstmt.setString(1, dto.getUrl());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getPrice());
			pstmt.executeUpdate();
			System.out.println("카드 추가완료");
		}
	}
	
	
	
	// 카드 번호로 조회
	public void infoCard(int id) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(INFO_ID_CARD);
			pstmt.setInt(1, id);
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

	// 카드 이름으로 조회
	public void infoCard(String name) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(INFO_NAME_CARD);
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
	public void updateCard(int id, CardDTO dto) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_ID_CARD);
			pstmt.setString(1, dto.getUrl());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setInt(4, id);
			pstmt.executeUpdate();
			System.out.println("카드 번호로 수정완료");
		}
	}

	// 카드 이름으로 수정 ( 경로, 이름, 가격 )
	public void updateCard(String name, CardDTO dto) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_NAME_CARD);
			pstmt.setString(1, dto.getUrl());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setString(4, name);
			pstmt.executeUpdate();
			System.out.println("카드 이름으로 수정완료");
		}
	}

	// 카드 번호로 카드 삭제하기
	public void deleteCard(int id) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(DELETE_ID_CARD);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println("해당 카드 번호 삭제완료");
		}
	}
	}


