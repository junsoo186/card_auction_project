package dao;

public class Query {

	// UserDAO 쿼리
	public static final String USER_ADD = " INSERT INTO user(name, password, nickname, point) VALUES(?, ?, ?, ?) ";
	public static final String USER_INFO_ALL = " SELECT * FROM user ";
	public static final String USER_INFO_NAME = " SELECT * FROM user WHERE name = ? ";
	public static final String USER_UPDATE_NAME = " UPDATE user SET password = ?, nickname = ? WHERE name = ? ";
	public static final String USER_POINT_NAME = " UPDATE user SET point = ? WHERE name = ? ";
	public static final String USER_DELETE_ID = " DELETE FROM user WHERE name = ? ";
	public static final String USER_LOGIN = " SELECT * FROM user WHERE name = ? and password = ? ";
	public static final String USER_DUPLICATE_NAME = " SELECT * FROM user WHERE name = ? ";

	// CardDAO 쿼리
	public static final String CARD_ADD = " INSERT INTO card(url, name, price) VALUES(?, ?, ?) ";
	public static final String CARD_INFO_ID = " SELECT * FROM card WHERE id = ? ";
	public static final String CARD_INFO_NAME = " SELECT * FROM card WHERE name = ? ";
	public static final String CARD_UPDATE_ID = " UPDATE card SET url = ?, name = ?, price = ? WHERE id = ? ";
	public static final String CARD_UPDATE_NAME = " UPDATE card SET url = ?, name = ?, price = ? WHERE name = ? ";
	public static final String CARD_DELETE_ID = " DELETE FROM card WHERE id = ? ";

	// AuctionDAO 쿼리
	public static final String AUCTION_ADD = " INSERT INTO auction(name, card_id, bid_price, start_date, end_date) VALUES(?, ?, ?, ?, ?) ";
	public static final String AUCTION_UPDATE = " UPDATE auction SET name = ?, card_id = ?, end_date =? WHERE user_id = ? ";
	public static final String AUCTION_DELETE = " DELETE FROM auction WHERE name = ? AND id = ? ";
	public static final String AUCTION_INFO_ALL = " SELECT a.id FROM auction a JOIN card c ON a.card_id = c.id ";
	public static final String AUCTION_INFO_CARD = " SELECT a.id FROM auction a JOIN card c ON a.card_id = c.id WHERE c.name = ? ";

	// InventoryDAO 쿼리
	public static final String INVEN_ADD = " INSERT INTO inventory(name, card_id) VALUES(?, ?) ";
	public static final String INVEN_INFO_ID = " SELECT c.url, c.name , c.id , c.price FROM inventory i JOIN user u ON i.name = u.name JOIN card c ON i.card_id = c.id WHERE i.name = ? ";
}
