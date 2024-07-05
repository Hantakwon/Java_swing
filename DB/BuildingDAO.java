package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class BuildingDAO {
	DB db = new DB();

	// Building의 모든 정보를 가져옴
	public Vector<Building> getAllBuildings() throws SQLException {
	    Vector<Building> list = new Vector<>();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select * from building";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();

	        while(rs.next()) {
	            Building building = new Building();
	            building.setId(rs.getInt("ID"));
	            building.setLumberyardGrade(rs.getInt("LUMBERYARDGRADE"));
	            building.setQuarryGrade(rs.getInt("QUARRYGRADE"));
	            list.add(building);
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
	public int getBuildingId(int id) throws SQLException {
	    int buildingId = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select ID from building where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            buildingId = rs.getInt("ID");
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        db.close();
	    }
	    return buildingId;
	}

	// LUMBERYARDGRADE 가져오는 메서드
	public int getLumberyardGrade(int id) throws SQLException {
	    int lumberyardGrade = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select LUMBERYARDGRADE from building where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            lumberyardGrade = rs.getInt("LUMBERYARDGRADE");
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        db.close();
	    }
	    return lumberyardGrade;
	}

	// LUMBERYARDGRADE 수정하는 메서드
	public void updateLumberyardGrade(int id, int lumberyardGrade) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update building set LUMBERYARDGRADE=? where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, lumberyardGrade);
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

	// QUARRYGRADE 가져오는 메서드
	public int getQuarryGrade(int id) throws SQLException {
	    int quarryGrade = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select QUARRYGRADE from building where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            quarryGrade = rs.getInt("QUARRYGRADE");
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        db.close();
	    }
	    return quarryGrade;
	}

	// QUARRYGRADE 수정하는 메서드
	public void updateQuarryGrade(int id, int quarryGrade) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update building set QUARRYGRADE=? where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, quarryGrade);
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
	
	// CASINOGRADE 가져오는 메서드
	public int getCasinoGrade(int id) throws SQLException {
	    int casinoGrade = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select CASINOGRADE from building where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            casinoGrade = rs.getInt("CASINOGRADE");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	    return casinoGrade;
	}

	// CASINOGRADE 수정하는 메서드
	public void updateCasinoGrade(int id, int casinoGrade) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update building set CASINOGRADE=? where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, casinoGrade);
	        pstmt.setInt(2, id);
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	}

	// BUILDING1GRADE 가져오는 메서드
	public int getBuilding1Grade(int id) throws SQLException {
	    int building1Grade = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select BUILDING1GRADE from building where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            building1Grade = rs.getInt("BUILDING1GRADE");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	    return building1Grade;
	}

	// BUILDING1GRADE 수정하는 메서드
	public void updateBuilding1Grade(int id, int building1Grade) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update building set BUILDING1GRADE=? where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, building1Grade);
	        pstmt.setInt(2, id);
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	}

	// BUILDING1GRADE 가져오는 메서드
	public int getBuilding2Grade(int id) throws SQLException {
	    int building2Grade = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select BUILDING2GRADE from building where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            building2Grade = rs.getInt("BUILDING2GRADE");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	    return building2Grade;
	}

	// BUILDING2GRADE 수정하는 메서드
	public void updateBuilding2Grade(int id, int building2Grade) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update building set BUILDING2GRADE=? where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, building2Grade);
	        pstmt.setInt(2, id);
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	}
	// BUILDING3GRADE 가져오는 메서드
	public int getBuilding3Grade(int id) throws SQLException {
	    int building3Grade = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select BUILDING3GRADE from building where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            building3Grade = rs.getInt("BUILDING3GRADE");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	    return building3Grade;
	}

	// BUILDING3GRADE 수정하는 메서드
	public void updateBuilding3Grade(int id, int building3Grade) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update building set BUILDING3GRADE=? where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, building3Grade);
	        pstmt.setInt(2, id);
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	}

	// BUILDING4GRADE 가져오는 메서드
	public int getBuilding4Grade(int id) throws SQLException {
	    int building4Grade = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select BUILDING4GRADE from building where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            building4Grade = rs.getInt("BUILDING4GRADE");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	    return building4Grade;
	}

	// BUILDING4GRADE 수정하는 메서드
	public void updateBuilding4Grade(int id, int building4Grade) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update building set BUILDING4GRADE=? where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, building4Grade);
	        pstmt.setInt(2, id);
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	}

	// BUILDING5GRADE 가져오는 메서드
	public int getBuilding5Grade(int id) throws SQLException {
	    int building5Grade = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select BUILDING5GRADE from building where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            building5Grade = rs.getInt("BUILDING5GRADE");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	    return building5Grade;
	}

	// BUILDING5GRADE 수정하는 메서드
	public void updateBuilding5Grade(int id, int building5Grade) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update building set BUILDING5GRADE=? where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, building5Grade);
	        pstmt.setInt(2, id);
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	}

}
