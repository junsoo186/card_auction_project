package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;

import dto.UserDTO;
import manager.DBConnectionManager;

// TODO Frame 에 연동시 수정

public class UserDAO {

	// 유저 추가하기 (회원가입)
	public static void addUser(UserDTO dto) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.USER_ADD);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getNickname());
			pstmt.executeUpdate();
			System.out.println("가입완료");
		}
	}

	// 유저 전체 조회하기

	public void infoUser() throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.USER_INFO_ALL);
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
			PreparedStatement pstmt = conn.prepareStatement(Query.USER_INFO_ID);
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
			PreparedStatement pstmt = conn.prepareStatement(Query.USER_INFO_NAME);
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

	// 유저 번호로 수정 ( 비밀번호, 닉네임 )
	public void updateUser(int id, UserDTO dto) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.USER_UPDATE_ID);
			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getNickname());
			pstmt.setInt(3, id);
			pstmt.executeUpdate();
			System.out.println("비번,닉네임 수정완료");
		}
	}

	// 유저 이름으로 수정 ( 비밀번호, 닉네임)
	public void updateUser(String name, UserDTO dto) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.USER_UPDATE_NAME);
			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getNickname());
			pstmt.setString(3, name);
			pstmt.executeUpdate();
			System.out.println("비번,닉네임 수정완료");
		}
	}

	// 해당 유저 번호의 포인트 수정하기

	// 유저 번호로 포인트 수정
	public void updatePoint(int id, int point) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.USER_POINT_ID);
			pstmt.setInt(1, point);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			System.out.println("포인트 수정완료");
		}
	}

	// 유저 번호로 유저 삭제 (회원탈퇴) delete
	public void deleteUser(int id) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.USER_DELETE_ID);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println(id + " 유저 삭제완료");
		}
	}

	// 유저 로그인 확인
	public void loginUser(String name, String password) {
		try (Connection conn = DBConnectionManager.getInstance().getConnection();) {
			if (authenticateUser(conn, name, password)) {
				System.out.println("로그인 성공 !");
			} else {
				System.out.println("로그인 실패 - name 과 password 를 확인해주세요");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static boolean authenticateUser(Connection conn, String name, String password) {
		boolean result = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.USER_LOGIN);
		} catch (Exception e) {
		}
}
}
