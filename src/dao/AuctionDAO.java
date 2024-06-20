package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.AuctionDTO;
import manager.DBConnectionManager;

/*
 * create table auction(
    id int auto_increment primary key,
    user_id int,
    card_id int,
    bid_price int,
    start_date date,
    end_date date,
    foreign key(user_id) references user(id),
    foreign key(card_id) references card(id)
);
 **/
//TODO Swing Frame 구현시 메세지를 
//JOptionPane.showMessageDialog(null, "")로 바꾸기

public class AuctionDAO {

	// 완료된 옥션 올리기
	public void addAuction(AuctionDTO dto) throws SQLException {
		try (Connection connect = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = connect.prepareStatement(Query.AUCTION_ADD);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getCardId());
			
			pstmt.setInt(3, dto.getBidPrice());
			pstmt.setString(4, dto.getStartDate());
			pstmt.setString(5, dto.getEndDate());
		}
	}

	// 옥션 변경
	public void updateAuction(int id, AuctionDTO dto) throws SQLException {
		try (Connection connect = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = connect.prepareStatement(Query.AUCTION_UPDATE);
			pstmt.setInt(1, dto.getCardId());
			pstmt.setInt(2, dto.getBidPrice());
			pstmt.setString(3, dto.getEndDate());
			pstmt.setInt(4, dto.getUserId());
			pstmt.executeLargeUpdate();
		}
	}

	// 옥션 삭제
	public void deletAuction(int userId, int id) throws SQLException {
		try (Connection connect = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = connect.prepareStatement(Query.AUCTION_DELETE);
			pstmt.setInt(1, userId);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println("방삭제 완료");
		}
	}

	// 옥션 전체 조회하기
	public void searchAllAuction() throws SQLException {
		try (Connection connect = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = connect.prepareStatement(Query.AUCTION_INFO_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("조회된 옥션" + rs.getInt("a.id"));
			}
		}
	}

	// 옥션 카드이름으로 조회하기
	public void searchAuction(String name) throws SQLException {
		try (Connection connect = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = connect.prepareStatement(Query.AUCTION_INFO_CARD);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("조회된 옥션" + rs.getInt("a.id"));
			}
		}
	}

}
