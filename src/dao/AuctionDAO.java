package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	// 진행중인 옥션 올리기
	public static void addAuction(AuctionDTO dto) throws SQLException {
		try (Connection connect = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = connect.prepareStatement(Query.AUCTION_ADD);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getCardId());
			pstmt.setInt(3, dto.getBidPrice());
			pstmt.setString(4, dto.getStartDate());
			pstmt.setString(5, dto.getEndDate());
			pstmt.executeUpdate();
			System.out.println("옥션 추가 완료");
		}
	}

	// 옥션 변경
	public void updateAuction(int id, AuctionDTO dto) throws SQLException {
		try (Connection connect = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = connect.prepareStatement(Query.AUCTION_UPDATE);
			pstmt.setInt(1, dto.getCardId());
			pstmt.setInt(2, dto.getBidPrice());
			pstmt.setString(3, dto.getEndDate());
			pstmt.executeLargeUpdate();
		}
	}

	// 옥션 삭제
	public void deletAuction(String userId, int id) throws SQLException {
		try (Connection connect = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = connect.prepareStatement(Query.AUCTION_DELETE);
			pstmt.setString(1, userId);
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

	// 모든 종료된 경매 목록 조회
	public static ArrayList<AuctionDTO> endAuctionList() throws SQLException {
		ArrayList<AuctionDTO> endAuctionList = new ArrayList<>();
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.AUCTION_LIST);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AuctionDTO auctionDTO = new AuctionDTO();
				auctionDTO.setId(rs.getInt("id"));
				auctionDTO.setName(rs.getString("name"));
				auctionDTO.setCardId(rs.getInt("card_id"));
				auctionDTO.setBidPrice(rs.getInt("bid_price"));
				auctionDTO.setStartDate(rs.getString("start_date"));
				auctionDTO.setEndDate(rs.getString("end_date"));
				endAuctionList.add(auctionDTO);
			}
		}
		return endAuctionList;
	}
}
