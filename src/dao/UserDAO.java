package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UserDTO;
import manager.DBConnectionManager;

//create table user(
//	id int auto_increment primary key,
//	name varchar(50),
//	password varchar(50),
//	nickname varchar(50),
//	point int);

// TODO Swing Frame 구현시 메세지를 
// JOptionPane.showMessageDialog(null, "")로 바꾸기

public class UserDAO {

	private static final String ADD_USER = " INSERT INTO user(name, password, nickname) VALUES(?, ?, ?) ";
	private static final String INFO_ALL_USER = " SELECT * FROM user ";
	private static final String INFO_ID_USER = " SELECT * FROM user WHERE id = ? ";
	private static final String INFO_NAME_USER = " SELECT * FROM user WHERE name = ? ";
	private static final String UPDATE_ID_USER = " UPDATE user SET password = ?, nickname = ? WHERE id = ? ";
	private static final String UPDATE_NAME_USER = " UPDATE user SET password = ?, nickname = ? WHERE name = ? ";
	private static final String UPDATE_ID_POINT = " UPDATE user SET point = ? WHERE id = ? ";
	private static final String DELETE_ID_USER = " DELETE FROM user WHERE id = ? ";

	// 유저 추가하기 (회원가입)
	public void addUser(UserDTO dto) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(ADD_USER);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getNickname());
			pstmt.executeUpdate();
			System.out.println("가입완료");
		}
	}

	// 유저 전체조회하기
	public void infoUser() throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(INFO_ALL_USER);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("====================================");
				System.out.println("유저번호 : " + rs.getInt("id"));
				System.out.println("이름 : " + rs.getString("name"));
				System.out.println("비밀번호 : " + rs.getString("password"));
				System.out.println("닉네임 : " + rs.getString("nickname"));
				System.out.println("포인트 : " + rs.getInt("point"));
			}
		}
	}

	// 유저 번호로 조회하기
	public void infoUser(int id) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(INFO_ID_USER);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("====================================");
				System.out.println("유저번호 : " + rs.getInt("id"));
				System.out.println("이름 : " + rs.getString("name"));
				System.out.println("비밀번호 : " + rs.getString("password"));
				System.out.println("닉네임 : " + rs.getString("nickname"));
				System.out.println("포인트 : " + rs.getInt("point"));
			}
		}
	}

	// 유저 이름으로 조회하기
	public void infoUser(String name) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(INFO_NAME_USER);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("====================================");
				System.out.println("유저번호 : " + rs.getInt("id"));
				System.out.println("이름 : " + rs.getString("name"));
				System.out.println("비밀번호 : " + rs.getString("password"));
				System.out.println("닉네임 : " + rs.getString("nickname"));
				System.out.println("포인트 : " + rs.getInt("point"));
			}
		}
	}

	// 해당 유저 번호의 비밀번호, 닉네임 수정하기 update
	public void updateUser(int id, String password, String nickname) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_ID_USER);
			pstmt.setString(1, password);
			pstmt.setString(2, nickname);
			pstmt.setInt(3, id);
			pstmt.executeUpdate();
			System.out.println("비번,닉네임 수정완료");
		}
	}

	// 해당 유저 이름의 비밀번호, 닉네임 수정하기
	public void updateUser(String name, String password, String nickname) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_NAME_USER);
			pstmt.setString(1, password);
			pstmt.setString(2, nickname);
			pstmt.setString(3, name);
			pstmt.executeUpdate();
			System.out.println("비번,닉네임 수정완료");
		}
	}

	// 해당 유저 번호의 포인트 수정하기
	public void updatePoint(int id, int point) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_ID_POINT);
			pstmt.setInt(1, point);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			System.out.println("포인트 수정완료");
		}
	}

	// 해당 유저번호로 유저 삭제하기 (회원탈퇴) delete
	public void deleteUser(int id) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(DELETE_ID_USER);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println(id + " 유저 삭제완료");
		}
	}
}
