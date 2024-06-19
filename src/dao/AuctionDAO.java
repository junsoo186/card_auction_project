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

    
	// 옥션 추가기능
	private void addAuction(AuctionDTO dto) {
		String query = "INSERT INTO auction(user_id, card_id,bid_price, start_date,end_date) VALUES(?,?,?,?,?)";
		try (Connection connect = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = connect.prepareStatement(query);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getCardId());
			pstmt.setInt(3, dto.getBidPrice());
			pstmt.setString(4, dto.getStartDate());
			pstmt.setString(5, dto.getEndDate());
		} catch (SQLException e) {
		}
	}

	// 옥션 변경
	private void updateAuction(int id, AuctionDTO dto) {
		String query = "UPDATE auction SET user_id = ?, card_id = ?,end_date =?   WHERE user_id = ?";
		try (Connection connect = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = connect.prepareStatement(query);
			pstmt.setInt(1, dto.getCardId());
			pstmt.setInt(2, dto.getBidPrice());
			pstmt.setString(3, dto.getEndDate());
			pstmt.setInt(4, dto.getUserId());
			pstmt.executeLargeUpdate();
		} catch (SQLException e) {
		}
	}

	// 옥션 삭제
	private void deletAuction(int userId, int id) {
		String query = "DELETE FROM auction WHERE user_id = ? AND id = ?";
		try (Connection connect = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = connect.prepareStatement(query);
			pstmt.setInt(1, userId);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println("방삭제 완료");
		} catch (SQLException e) {
		}
	}

	// 옥션 검색 전체 조회하기
	private void searchAuction(String name) {
		String query = "SELECT a.id "
				+ "FROM auction as a join card as c on  a.card_id  = c.id "
				+ "WHERE c.name = ? ";
		try (Connection connect = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = connect.prepareStatement(query);
			pstmt.setString(1, name);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println("조회된 옥션" + rs.getInt("a.id"));
			}
			
		}catch (SQLException e) {
		}
	}
	
	private void searchAllAuction(){
		String query = "SELECT a.id "
				+ "FROM auction as a join card as c on  a.card_id  = c.id ";
		try (Connection connect = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = connect.prepareStatement(query);	ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println("조회된 옥션" + rs.getInt("a.id"));
			}
		}catch (SQLException e) {
		}
	}
	

}
