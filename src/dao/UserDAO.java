package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CardDTO;
import dto.UserDTO;
import manager.DBConnectionManager;

// TODO Frame 에 연동시 수정

public class UserDAO {

	// 유저 추가하기 (회원가입)
	public static boolean addUser(UserDTO dto) throws SQLException {
		boolean userAdd = false;
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			if (!duplicateAddUser(conn, dto.getName())) {
				PreparedStatement pstmt = conn.prepareStatement(Query.USER_ADD);
				pstmt.setString(1, dto.getName());
				pstmt.setString(2, dto.getPassword());
				pstmt.setString(3, dto.getNickname());
				pstmt.setInt(4, dto.getPoint());
				pstmt.executeUpdate();
				System.out.println("가입완료");
				userAdd = true;
			} else {
				System.out.println("중복된 아이디가 있습니다");
			}
		}
		return userAdd;
	}

	private static boolean duplicateAddUser(Connection conn, String username) {
		boolean result = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.USER_DUPLICATE_NAME);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 유저 전체 조회하기

	public static void infoUser() throws SQLException {
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

	// 유저 이름으로 조회하기
	public static UserDTO infoUser(String name) throws SQLException {
		UserDTO user = new UserDTO();
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.USER_INFO_NAME);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setName(rs.getString("name"));
				user.setNickname(rs.getString("nickname"));
				user.setPassword(rs.getString("password"));
				user.setPoint(rs.getInt("point"));
			}
		}
		return user;
	}

	// 유저 이름으로 수정 ( 비밀번호, 닉네임)
	public static void updateUser(String name, UserDTO dto) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.USER_UPDATE_NAME);
			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getNickname());
			pstmt.setString(3, name);
			pstmt.executeUpdate();
			System.out.println("비번,닉네임 수정완료");
		}
	}

	// 유저 이름으로 포인트 수정
	public static void updatePoint(String name, int point) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.USER_POINT_NAME);
			pstmt.setInt(1, point);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
			System.out.println("포인트 수정완료");
		}
	}

	// 구매 유저 포인트 차감
	public static void subtractPoint(String name, int point) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.USER_SUBTARCT_POINT);
			pstmt.setInt(1, point);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
			System.out.println("포인트 차감완료");
		}
	}
	
	// 판매 유저 포인트 추가
	public static void addPoint(String name, int point) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.USER_ADD_POINT);
			pstmt.setInt(1, point);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
			System.out.println("포인트 추가완료");
		}
	}

	// 유저 이름으로 유저 삭제 (회원탈퇴) delete
	public static void deleteUser(String name) throws SQLException {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(Query.USER_DELETE_ID);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
			System.out.println(name + " 유저 삭제완료");
		}
	}

	// 유저 로그인 확인
	public static boolean CheckLogin(String name, String password) {
		boolean login = false;
		try (Connection conn = DBConnectionManager.getInstance().getConnection();) {
			System.out.println(name + " : " + password);
			if (authenticateUser(conn, name, password)) {
				System.out.println("로그인 성공 !");
				login = true;
			} else {
				System.out.println("로그인 실패 - name 과 password 를 확인해주세요");
				login = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return login;
	}

	private static boolean authenticateUser(Connection conn, String name, String password) {
		boolean result = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(Query.USER_LOGIN);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			result = rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
