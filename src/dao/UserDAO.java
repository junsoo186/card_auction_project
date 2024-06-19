package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UserDTO;
import manager.DBConnectionManager;

// TODO Frame 에 연동시 수정

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
	public static void addUser(UserDTO dto) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(ADD_USER);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getNickname());
			pstmt.executeUpdate();
			System.out.println("가입완료");
		}
	}

<<<<<<< HEAD
	// 유저 전체조회하기
	public static void infoUser() throws SQLException {
=======
	// 유저 전체 조회하기
	public void infoUser() throws SQLException {
>>>>>>> b282f1ffd4cf4a8c46a76d2210881246d13b48b2
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(INFO_ALL_USER);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("====================================");
				System.out.println("유저번호 : " + rs.getInt("id"));
				System.out.println("유저이름 : " + rs.getString("name"));
				System.out.println("비밀번호 : " + rs.getString("password"));
				System.out.println("닉네임 : " + rs.getString("nickname"));
				System.out.println("포인트 : " + rs.getInt("point"));
			}
		}
	}

	// 유저 번호로 조회하기
	public static void infoUser(int id) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(INFO_ID_USER);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("====================================");
				System.out.println("유저번호 : " + rs.getInt("id"));
				System.out.println("유저이름 : " + rs.getString("name"));
				System.out.println("비밀번호 : " + rs.getString("password"));
				System.out.println("닉네임 : " + rs.getString("nickname"));
				System.out.println("포인트 : " + rs.getInt("point"));
			}
		}
	}

	// 유저 이름으로 조회하기
	public static void infoUser(String name) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(INFO_NAME_USER);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("====================================");
				System.out.println("유저번호 : " + rs.getInt("id"));
				System.out.println("유저이름 : " + rs.getString("name"));
				System.out.println("비밀번호 : " + rs.getString("password"));
				System.out.println("닉네임 : " + rs.getString("nickname"));
				System.out.println("포인트 : " + rs.getInt("point"));
			}
		}
	}

<<<<<<< HEAD
	// 해당 유저 번호의 비밀번호, 닉네임 수정하기 update
	public static void updateUser(int id, String password, String nickname) throws SQLException {
=======
	// 유저 번호로 수정 ( 비밀번호, 닉네임 )
	public void updateUser(int id, UserDTO dto) throws SQLException {
>>>>>>> b282f1ffd4cf4a8c46a76d2210881246d13b48b2
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_ID_USER);
			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getNickname());
			pstmt.setInt(3, id);
			pstmt.executeUpdate();
			System.out.println("비번,닉네임 수정완료");
		}
	}

<<<<<<< HEAD
	// 해당 유저 이름의 비밀번호, 닉네임 수정하기
	public static void updateUser(String name, String password, String nickname) throws SQLException {
=======
	// 유저 이름으로 수정 ( 비밀번호, 닉네임)
	public void updateUser(String name, UserDTO dto) throws SQLException {
>>>>>>> b282f1ffd4cf4a8c46a76d2210881246d13b48b2
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_NAME_USER);
			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getNickname());
			pstmt.setString(3, name);
			pstmt.executeUpdate();
			System.out.println("비번,닉네임 수정완료");
		}
	}

<<<<<<< HEAD
	// 해당 유저 번호의 포인트 수정하기
	public static void updatePoint(int id, int point) throws SQLException {
=======
	// 유저 번호로 포인트 수정
	public void updatePoint(int id, int point) throws SQLException {
>>>>>>> b282f1ffd4cf4a8c46a76d2210881246d13b48b2
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_ID_POINT);
			pstmt.setInt(1, point);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			System.out.println("포인트 수정완료");
		}
	}

<<<<<<< HEAD
	// 해당 유저번호로 유저 삭제하기 (회원탈퇴) delete
	public static void deleteUser(int id) throws SQLException {
=======
	// 유저 번호로 유저 삭제 (회원탈퇴) delete
	public void deleteUser(int id) throws SQLException {
>>>>>>> b282f1ffd4cf4a8c46a76d2210881246d13b48b2
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(DELETE_ID_USER);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println(id + " 유저 삭제완료");
		}
	}
}
