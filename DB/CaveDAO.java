package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class CaveDAO {
	DB db = new DB();
    Vector<String> caves = null;

    public Vector<String> getCaves() {
        return caves;
    }

    // Cave(데이터베이스)의 모든 정보를 가져옴
    public Vector<CaveDB> getAllCaves() throws SQLException {
        Vector<CaveDB> list = new Vector<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from cave";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                CaveDB cave = new CaveDB();
                cave.setId(rs.getInt("id"));
                cave.setCavegrade(rs.getInt("cavegrade"));
                cave.setCaveexp(rs.getInt("caveexp"));
                list.add(cave);
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
    public int getCaveId(int id) throws SQLException {
        int caveId = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select id from cave where id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                caveId = rs.getInt("id");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
        return caveId;
    }

    // Cavegrade 가져오는 메서드
    public int getCaveGrade(int id) throws SQLException {
        int cavegrade = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select cavegrade from cave where id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                cavegrade = rs.getInt("cavegrade");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
        return cavegrade;
    }

    // Cavegrade 수정하는 메서드
    public void updateCaveGrade(int id, int cavegrade) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update cave set cavegrade=? where id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cavegrade);
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

    // CaveMaxgrade 가져오는 메서드
    public int getCaveMaxGrade(int id) throws SQLException {
        int cavemaxgrade = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select cavemaxgrade from cave where id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                cavemaxgrade = rs.getInt("cavemaxgrade");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
        return cavemaxgrade;
    }

    // CaveMaxgrade 수정하는 메서드
    public void updateCaveMaxGrade(int id, int cavemaxgrade) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update cave set cavemaxgrade=? where id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cavemaxgrade);
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
    
    // Caveexp 가져오는 메서드
    public int getCaveExp(int id) throws SQLException {
        int caveexp = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select caveexp from cave where id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                caveexp = rs.getInt("caveexp");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
        return caveexp;
    }

    // Caveexp 수정하는 메서드
    public void updateCaveExp(int id, int caveexp) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update cave set caveexp=? where id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, caveexp);
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
    
	// Oremaxhp 가져오는 메서드
    public int getOreMaxHp(int id) throws SQLException {
        int oremaxhp = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select oremaxhp from cave where id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                oremaxhp = rs.getInt("oremaxhp");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
        return oremaxhp;
    }

    // Oremaxhp 수정하는 메서드
    public void updateOreMaxHp(int id, int oremaxhp) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update cave set oremaxhp=? where id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, oremaxhp);
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
    
    // Orehp 가져오는 메서드
    public int getOreHp(int id) throws SQLException {
        int orehp = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select orehp from cave where id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                orehp = rs.getInt("orehp");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
        return orehp;
    }

    // Orehp 수정하는 메서드
    public void updateOreHp(int id, int orehp) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update cave set orehp=? where id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orehp);
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
    
 // Monstermaxhp 가져오는 메서드
    public int getMonsterMaxHp(int id) throws SQLException {
        int monstermaxhp = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select monstermaxhp from cave where id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                monstermaxhp = rs.getInt("monstermaxhp");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
        return monstermaxhp;
    }

    // Monstermaxhp 수정하는 메서드
    public void updateMonsterMaxHp(int id, int monstermaxhp) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update cave set monstermaxhp=? where id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, monstermaxhp);
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
    
    // Monsterhp 가져오는 메서드
    public int getMonsterHp(int id) throws SQLException {
        int monsterhp = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select monsterhp from cave where id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                monsterhp = rs.getInt("monsterhp");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
        return monsterhp;
    }

    // Monsterhp 수정하는 메서드
    public void updateMonsterHp(int id, int monsterhp) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update cave set monsterhp=? where id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, monsterhp);
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
    
    // Division 가져오는 메서드
    public int getDivison(int id) throws SQLException {
        int division = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select division from cave where id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                division = rs.getInt("division");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
        return division;
    }

    // Division 수정하는 메서드
    public void updateDivision(int id, int division) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update cave set division=? where id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, division);
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
