package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StoreDAO {
	DB db = new DB();
	Vector<String> store = null;

	public Vector<String> getStore() {
		return store;
	}

	public Vector<StoreDB> getAllStore() throws SQLException {
		Vector<StoreDB> list = new Vector<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from store";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				StoreDB item = new StoreDB();
				item.setProductId(rs.getInt("PRODUCTID"));
				item.setCount(rs.getInt("COUNT"));
				item.setPrice(rs.getInt("PRICE"));
				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return list;
	}

	// PRODUCTID를 가져오는 메서드
	public int getStoreProductId(int productId) throws SQLException {
		int id = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select PRODUCTID from store where PRODUCTID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt("PRODUCTID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return id;
	}

	// PRODUCTID 수정하는 메서드
	public void updateStoreProductId(int productId, int id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update store set PRODUCTID=? where PRODUCTID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, productId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	// COUNT를 가져오는 메서드
	public int getStoreCount(int productId) throws SQLException {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select COUNT from store where PRODUCTID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("COUNT");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return count;
	}

	// COUNT 수정하는 메서드
	public void updateStoreCount(int productId, int count) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update store set COUNT=? where PRODUCTID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setInt(2, productId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	// PRICE를 가져오는 메서드
	public int getStorePrice(int productId) throws SQLException {
		int price = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select PRICE from store where PRODUCTID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				price = rs.getInt("PRICE");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return price;
	}

	// PRICE 수정하는 메서드
	public void updateStorePrice(int productId, int price) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update store set PRICE=? where PRODUCTID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, price);
			pstmt.setInt(2, productId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	// COUNT를 배열로 가져오는 메서드
	public int[] getStoreCountArray() throws SQLException {
		Vector<Integer> countVector = new Vector<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select COUNT from store";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				countVector.add(rs.getInt("COUNT"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		int[] countArray = new int[countVector.size()];
		for (int i = 0; i < countVector.size(); i++) {
			countArray[i] = countVector.get(i);
		}

		return countArray;
	}

	// PRICE를 배열로 가져오는 메서드
	public int[] getStorePriceArray() throws SQLException {
		Vector<Integer> priceVector = new Vector<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select PRICE from store";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				priceVector.add(rs.getInt("PRICE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		int[] priceArray = new int[priceVector.size()];
		for (int i = 0; i < priceVector.size(); i++) {
			priceArray[i] = priceVector.get(i);
		}

		return priceArray;
	}
}
