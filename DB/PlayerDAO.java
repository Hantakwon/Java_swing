package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class PlayerDAO {
	DB db = new DB();
	Vector<String> players = null;

	public Vector<String> getPlayers() {
	    return players;
	}

	// 플레이어(데이터베이스)의 모든 정보를 가져옴
	public Vector<Player> getAllPlayer() throws SQLException {
	    Vector<Player> list = new Vector<>();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select * from player";
	    
	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        
	        while(rs.next()) {
	            Player player = new Player();
	            player.setName(rs.getString("name"));
	            player.setLv(rs.getInt("lv"));
	            player.setMaxHp(rs.getInt("maxHp"));
	            player.setHp(rs.getInt("hp"));
	            player.setStr(rs.getInt("str"));
	            player.setExp(rs.getInt("exp"));
	            player.setPickLv(rs.getInt("pickLv"));
	            player.setWeaponLv(rs.getInt("weaponLv"));
	            list.add(player);
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
	
	// ID를 이용하여 플레이어의 이름을 가져오는 메서드
	public String getPlayerName(int id) throws SQLException {
	    String playerName = "";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select name from player where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            playerName = rs.getString("name");
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        db.close();
	    }
	    return playerName;
	}

	// ID를 이용하여 플레이어의 레벨을 가져오는 메서드
	public int getPlayerLv(int id) throws SQLException {
	    int lv = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select lv from player where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            lv = rs.getInt("lv");
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        db.close();
	    }
	    return lv;
	}

	// ID를 이용하여 플레이어의 레벨을 수정하는 메서드
	public void updatePlayerLv(int id, int lv) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update player set lv=? where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, lv);
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
	
	// ID를 이용하여 플레이어의 최대 HP를 가져오는 메서드
	public int getPlayerMaxHp(int id) throws SQLException {
	    int maxHp = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select maxHp from player where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            maxHp = rs.getInt("maxHp");
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        db.close();
	    }
	    return maxHp;
	}

	// ID를 이용하여 플레이어의 최대 HP를 수정하는 메서드
	public void updatePlayerMaxHp(int id, int maxHp) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update player set maxHp=? where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, maxHp);
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

	// ID를 이용하여 플레이어의 현재 HP를 가져오는 메서드
	public int getPlayerHp(int id) throws SQLException {
	    int hp = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select hp from player where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            hp = rs.getInt("hp");
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        db.close();
	    }
	    return hp;
	}

	// ID를 이용하여 플레이어의 현재 HP를 수정하는 메서드
	public void updatePlayerHp(int id, int hp) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update player set hp=? where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, hp);
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
	
	// ID를 이용하여 플레이어의 힘(STR)을 가져오는 메서드
	public int getPlayerStr(int id) throws SQLException {
	    int str = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select str from player where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            str = rs.getInt("str");
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        db.close();
	    }
	    return str;
	}

	// ID를 이용하여 플레이어의 힘(STR)을 수정하는 메서드
	public void updatePlayerStr(int id, int str) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update player set str=? where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, str);
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

	// ID를 이용하여 플레이어의 경험치(EXP)을 가져오는 메서드
	public int getPlayerExp(int id) throws SQLException {
	    int exp = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select exp from player where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            exp = rs.getInt("exp");
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        db.close();
	    }
	    return exp;
	}

	// ID를 이용하여 플레이어의 경험치(EXP)을 수정하는 메서드
	public void updatePlayerExp(int id, int exp) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update player set exp=? where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, exp);
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

	// ID를 이용하여 플레이어의 곡괭이 레벨(PickLv)을 가져오는 메서드
	public int getPlayerPickLv(int id) throws SQLException {
	    int pickLv = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select pickLv from player where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            pickLv = rs.getInt("pickLv");
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        db.close();
	    }
	    return pickLv;
	}

	// ID를 이용하여 플레이어의 곡괭이 레벨(PickLv)을 수정하는 메서드
	public void updatePlayerPickLv(int id, int pickLv) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update player set pickLv=? where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, pickLv);
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

	// ID를 이용하여 플레이어의 무기 레벨(WeaponLv)을 가져오는 메서드
	public int getPlayerWeaponLv(int id) throws SQLException {
	    int weaponLv = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select weaponLv from player where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            weaponLv = rs.getInt("weaponLv");
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        db.close();
	    }
	    return weaponLv;
	}

	// ID를 이용하여 플레이어의 무기 레벨(WeaponLv)을 수정하는 메서드
	public void updatePlayerWeaponLv(int id, int weaponLv) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update player set weaponLv=? where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, weaponLv);
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
	
	// ID를 이용하여 플레이어의 upgradeLv를 가져오는 메서드
	public int getPlayerUpgradeLv(int id) throws SQLException {
	    int upgradeLv = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select upgradeLv from player where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            upgradeLv = rs.getInt("upgradeLv");
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        db.close();
	    }
	    return upgradeLv;
	}

	// ID를 이용하여 플레이어의 upgradeLv를 수정하는 메서드
	public void updatePlayerUpgradeLv(int id, int upgradeLv) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update player set upgradeLv=? where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, upgradeLv);
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
	
	// ID를 이용하여 플레이어의 story를 가져오는 메서드
	public int getPlayerStory(int id) throws SQLException {
	    int story = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select story from player where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            story = rs.getInt("story");
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        db.close();
	    }
	    return story;
	}

	// ID를 이용하여 플레이어의 story를 수정하는 메서드
	public void updatePlayerStory(int id, int story) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "update player set story=? where id=?";

	    try {
	        conn = db.connect();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, story);
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
