package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entity.Ghe;
import Entity.Ghe.LoaiGhe;
import connectDB.ConnectDB;

public class Ghe_DAO {
	public List<Ghe> getAllGheTheoMaPhong(String maPhong){
		List<Ghe> ds = new ArrayList<>();
		String sql = "Select * from Ghe WHERE maGhe=?";
		try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, maPhong);
				ResultSet rs = stmt.executeQuery(sql);
	            while (rs.next()) {
	                String maGhe = rs.getString("maGhe");
	                Boolean tinhTrang= rs.getBoolean("tinhTrang");
	                String loai = rs.getString("loaiGhe");
	                LoaiGhe loaiGhe = LoaiGhe.valueOf(loai);

	                Ghe g = new Ghe(maGhe, loaiGhe, tinhTrang);
	                ds.add(g);
	            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public List<Ghe> getGheTheoMaGhe(String ma){
		List<Ghe> ds = new ArrayList<>();
		String sql = "Select * from Ghe WHERE maGhe=?";
		try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, ma);
				ResultSet rs = stmt.executeQuery(sql);
	            while (rs.next()) {
	                String maGhe = rs.getString("maGhe");
	                Boolean tinhTrang= rs.getBoolean("tinhTrang");
	                String loai = rs.getString("loaiGhe");
	                LoaiGhe loaiGhe = LoaiGhe.valueOf(loai);

	                Ghe g = new Ghe(maGhe, loaiGhe, tinhTrang);
	                ds.add(g);
	            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public List<Ghe> getAllGhe(){
		List<Ghe> ds = new ArrayList<>();
		String sql = "Select * from Ghe";
		try (Connection conn = ConnectDB.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            while (rs.next()) {
	                String maGhe = rs.getString("maGhe");
	                Boolean tinhTrang= rs.getBoolean("tinhTrang");
	                String loai = rs.getString("loaiGhe");
	                LoaiGhe loaiGhe = LoaiGhe.valueOf(loai);

	                Ghe g = new Ghe(maGhe, loaiGhe, tinhTrang);
	                ds.add(g);
	            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public boolean insert(Ghe g, String maPhong) {
        String sql = "INSERT INTO Ghe (maGhe, loaiGhe, tinhTrang, maPhong) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, g.getMaGhe());
            ps.setString(2, g.getLoaiGhe().name());
            ps.setBoolean(3, g.isTinhTrang());
            ps.setString(4, maPhong);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Ghe g, String maPhong) {
        String sql = "UPDATE Ghe SET loaiGhe = ?, tinhTrang = ?, maPhong = ? WHERE maGhe = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, g.getLoaiGhe().name());
            ps.setBoolean(2, g.isTinhTrang());
            ps.setString(3, maPhong);
            ps.setString(4, g.getMaGhe());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String maGhe) {
        String sql = "DELETE FROM Ghe WHERE maGhe = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maGhe);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteTheoMaPhong(String maPhong) {
        String sql = "DELETE FROM Ghe WHERE maPhong = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maPhong);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
