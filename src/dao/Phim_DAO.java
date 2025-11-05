package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entity.*;
import Entity.Phim.TheLoai; 
import connectDB.ConnectDB;

public class Phim_DAO {
	public List<Phim> getPhimTheoMaPhim(String ma){
		List<Phim> ds = new ArrayList<>();
		String sql = "Select * from Phim WHERE maPhim=?";
		try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery(); 
	        
			while (rs.next()) {
	            String maPhim = rs.getString("maPhim");
	            String tenPhim = rs.getString("tenPhim");
	            String theLoaiStr = rs.getString("theLoai");
				TheLoai theL = Phim.TheLoai.fromString(theLoaiStr);
	            int thoiLuong = rs.getInt("thoiLuong");
	            String daoDien = rs.getString("daoDien");
	            boolean trangThai = rs.getBoolean("trangThai");
				String moTa = rs.getString("moTa");
				String duongDanAnh = rs.getString("duongDanAnh");

	            Phim p = new Phim(maPhim, tenPhim, theL, thoiLuong, daoDien, trangThai, moTa, duongDanAnh);
	            ds.add(p);
	        }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public List<Phim> getAllPhim(){
		List<Phim> ds = new ArrayList<>();
		String sql = "Select * from Phim";
		try (Connection conn = ConnectDB.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
				String maPhim = rs.getString("maPhim");
				String tenPhim = rs.getString("tenPhim");
				String theLoaiStr = rs.getString("theLoai");
				TheLoai theL = Phim.TheLoai.fromString(theLoaiStr);
				int thoiLuong = rs.getInt("thoiLuong");
				String daoDien = rs.getString("daoDien");
				boolean trangThai = rs.getBoolean("trangThai");
				String moTa = rs.getString("moTa");
				String duongDanAnh = rs.getString("duongDanAnh");

				Phim p = new Phim(maPhim, tenPhim, theL, thoiLuong, daoDien, trangThai, moTa, duongDanAnh);
	            ds.add(p);
	        }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public boolean insert(Phim p) {
        String sql = "INSERT INTO Phim (maPhim, tenPhim, theLoai, thoiLuong, daoDien, trangThai, moTa, duongDanAnh) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectDB.getConnection(); 
			 PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getMaPhim());
            ps.setString(2, p.getTenPhim());
            ps.setString(3, p.getTheLoai().name());
            ps.setInt(4, p.getThoiLuong());
            ps.setString(5, p.getDaoDien());
            ps.setBoolean(6, p.isTrangThai());
			ps.setString(7, p.getMoTa());
			ps.setString(8, p.getDuongDanAnh());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { 
			e.printStackTrace(); 
			return false; 
		}
    }

    public boolean update(Phim p) {
        String sql = "UPDATE Phim SET tenPhim=?, theLoai=?, thoiLuong=?, daoDien=?, trangThai=?, moTa=?, duongDanAnh=? WHERE maPhim=?";
        try (Connection conn = ConnectDB.getConnection(); 
			 PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getTenPhim());
            ps.setString(2, p.getTheLoai().name());
            ps.setInt(3, p.getThoiLuong());
            ps.setString(4, p.getDaoDien());
            ps.setBoolean(5, p.isTrangThai());
			ps.setString(6, p.getMoTa());
			ps.setString(7, p.getDuongDanAnh());
            ps.setString(8, p.getMaPhim()); 
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { 
			e.printStackTrace(); 
			return false; 
		}
    }

    public boolean delete(String maPhim) {
        String sql = "DELETE FROM Phim WHERE maPhim=?";
        try (Connection conn = ConnectDB.getConnection(); 
			 PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maPhim);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { 
			e.printStackTrace(); 
			return false; 
		}
    }
}