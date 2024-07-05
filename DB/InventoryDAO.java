package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class InventoryDAO {
	DB db = new DB();
	Vector<String> inventory = null;

	public Vector<String> getInventory() {
		return inventory;
	}

	// Inventory(데이터베이스)의 모든 정보를 가져옴
	public Vector<Inventory> getAllInventory() throws SQLException {
		Vector<Inventory> list = new Vector<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from inventory";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Inventory item = new Inventory();
				item.setId(rs.getInt("ID"));
				item.setPotion1(rs.getInt("POTION1"));
				item.setPotion2(rs.getInt("POTION2"));
				item.setGoldOre(rs.getInt("GOLDORE"));
				item.setRuby(rs.getInt("RUBY"));
				item.setEmerald(rs.getInt("EMERALD"));
				item.setCore(rs.getInt("CORE"));
				item.setShoulder(rs.getInt("SHOULDER"));
				item.setClaw(rs.getInt("CLAW"));
				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return list;
	}

	// 각 아이템들을 가져오는 메서드들과 수정하는 메서드들이 필요할 것 같습니다.
	// 예를 들어, POTION1을 가져오는 메서드는 다음과 같이 작성할 수 있습니다.
	public int getInventoryPotion1(int id) throws SQLException {
		int potion1 = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select POTION1 from inventory where ID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				potion1 = rs.getInt("POTION1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return potion1;
	}

	// POTION1 수정하는 메서드
	public void updateInventoryPotion1(int id, int potion1) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update inventory set POTION1=? where ID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, potion1);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	// POTION2 가져오는 메서드
	public int getInventoryPotion2(int id) throws SQLException {
		int potion2 = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select POTION2 from inventory where ID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				potion2 = rs.getInt("POTION2");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return potion2;
	}

	// POTION2 수정하는 메서드
	public void updateInventoryPotion2(int id, int potion2) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update inventory set POTION2=? where ID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, potion2);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	// GOLDORE 가져오는 메서드
	public int getInventoryGoldOre(int id) throws SQLException {
		int goldOre = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select GOLDORE from inventory where ID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				goldOre = rs.getInt("GOLDORE");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return goldOre;
	}

	// GOLDORE 수정하는 메서드
	public void updateInventoryGoldOre(int id, int goldOre) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update inventory set GOLDORE=? where ID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goldOre);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	// RUBY 가져오는 메서드
	public int getInventoryRuby(int id) throws SQLException {
		int ruby = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select RUBY from inventory where ID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				ruby = rs.getInt("RUBY");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return ruby;
	}

	// RUBY 수정하는 메서드
	public void updateInventoryRuby(int id, int ruby) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update inventory set RUBY=? where ID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ruby);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	// EMERALD 가져오는 메서드
	public int getInventoryEmerald(int id) throws SQLException {
		int emerald = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select EMERALD from inventory where ID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				emerald = rs.getInt("EMERALD");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return emerald;
	}

	// EMERALD 수정하는 메서드
	public void updateInventoryEmerald(int id, int emerald) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update inventory set EMERALD=? where ID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emerald);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	// CORE 가져오는 메서드
	public int getInventoryCore(int id) throws SQLException {
		int core = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select CORE from inventory where ID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				core = rs.getInt("CORE");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return core;
	}

	// CORE 수정하는 메서드
	public void updateInventoryCore(int id, int core) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update inventory set CORE=? where ID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, core);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	// SHOULDER 가져오는 메서드
	public int getInventoryShoulder(int id) throws SQLException {
		int shoulder = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select SHOULDER from inventory where ID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				shoulder = rs.getInt("SHOULDER");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return shoulder;
	}

	// SHOULDER 수정하는 메서드
	public void updateInventoryShoulder(int id, int shoulder) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update inventory set SHOULDER=? where ID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, shoulder);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	// CLAW 가져오는 메서드
	public int getInventoryClaw(int id) throws SQLException {
		int claw = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select CLAW from inventory where ID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				claw = rs.getInt("CLAW");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return claw;
	}

	// CLAW 수정하는 메서드
	public void updateInventoryClaw(int id, int claw) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update inventory set CLAW=? where ID=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, claw);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	// BOSSJEWEL1 가져오는 메서드
	public int getInventoryBossjewel1(int id) throws SQLException {
	    int bossjewel1 = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select BOSSJEWEL1 from inventory where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            bossjewel1 = rs.getInt("BOSSJEWEL1");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	    return bossjewel1;
	}

	// BOSSJEWEL1 수정하는 메서드
	public void updateInventoryBossjewel1(int id, int bossjewel1) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update inventory set BOSSJEWEL1=? where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, bossjewel1);
	        pstmt.setInt(2, id);
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	}

	// BOSSJEWEL2 가져오는 메서드
	public int getInventoryBossjewel2(int id) throws SQLException {
	    int bossjewel2 = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select BOSSJEWEL2 from inventory where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            bossjewel2 = rs.getInt("BOSSJEWEL2");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	    return bossjewel2;
	}

	// BOSSJEWEL2 수정하는 메서드
	public void updateInventoryBossjewel2(int id, int bossjewel2) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update inventory set BOSSJEWEL2=? where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, bossjewel2);
	        pstmt.setInt(2, id);
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	}

	// BOSSJEWEL3 가져오는 메서드
	public int getInventoryBossjewel3(int id) throws SQLException {
	    int bossjewel3 = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select BOSSJEWEL3 from inventory where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            bossjewel3 = rs.getInt("BOSSJEWEL3");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	    return bossjewel3;
	}

	// BOSSJEWEL3 수정하는 메서드
	public void updateInventoryBossjewel3(int id, int bossjewel3) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update inventory set BOSSJEWEL3=? where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, bossjewel3);
	        pstmt.setInt(2, id);
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	}

	// BOSSJEWEL4 가져오는 메서드
	public int getInventoryBossjewel4(int id) throws SQLException {
	    int bossjewel4 = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select BOSSJEWEL4 from inventory where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            bossjewel4 = rs.getInt("BOSSJEWEL4");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	    return bossjewel4;
	}

	// BOSSJEWEL4 수정하는 메서드
	public void updateInventoryBossjewel4(int id, int bossjewel4) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update inventory set BOSSJEWEL4=? where ID=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, bossjewel4);
	        pstmt.setInt(2, id);
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        db.close();
	    }
	}

}
