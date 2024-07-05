package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class GoodsDAO {
    DB db = new DB();
    Vector<String> goods = null;

    public Vector<String> getGoods() {
        return goods;
    }

    // Goods(데이터베이스)의 모든 정보를 가져옴
    public Vector<Goods> getAllGoods() throws SQLException {
        Vector<Goods> list = new Vector<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from goods";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                Goods good = new Goods();
                good.setId(rs.getInt("ID"));
                good.setGold(rs.getInt("GOLD"));
                good.setWood(rs.getInt("WOOD"));
                good.setRock(rs.getInt("ROCK"));
                list.add(good);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
        return list;
    }

    // ID 가져오는 메서드
    public int getGoodsId(int id) throws SQLException {
        int goodsId = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select ID from goods where ID=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                goodsId = rs.getInt("ID");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
        return goodsId;
    }

    // GOLD 가져오는 메서드
    public int getGoodsGold(int id) throws SQLException {
        int gold = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select GOLD from goods where ID=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                gold = rs.getInt("GOLD");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
        return gold;
    }

    // GOLD 수정하는 메서드
    public void updateGoodsGold(int id, int gold) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update goods set GOLD=? where ID=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gold);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
    }

    // WOOD 가져오는 메서드
    public int getGoodsWood(int id) throws SQLException {
        int wood = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select WOOD from goods where ID=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                wood = rs.getInt("WOOD");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
        return wood;
    }

    // WOOD 수정하는 메서드
    public void updateGoodsWood(int id, int wood) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update goods set WOOD=? where ID=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, wood);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
    }

    // ROCK 가져오는 메서드
    public int getGoodsRock(int id) throws SQLException {
        int rock = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select ROCK from goods where ID=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                rock = rs.getInt("ROCK");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
        return rock;
    }

    // ROCK 수정하는 메서드
    public void updateGoodsRock(int id, int rock) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update goods set ROCK=? where ID=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, rock);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
    }

	// POPULATOIN 가져오는 메서드
	public int getGoodsPopulation(int id) throws SQLException {
		int population = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select population from goods where ID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				population = rs.getInt("population");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return population;
	}

    // POPULATOIN 수정하는 메서드
    public void updateGoodsPopulation(int id, int population) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update goods set population=? where ID=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, population);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
    }
}
