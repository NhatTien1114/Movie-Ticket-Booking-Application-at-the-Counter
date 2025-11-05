package dao;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import Entity.KhachHang;

import connectDB.ConnectDB;
public class KhachHang_DAO {
	public List<KhachHang> getKhachHangTheoMaKH(String ma){
		List<KhachHang> ds = new ArrayList<>();
		String sql = "Select * from KhachHang WHERE maKH=?";
		try (Connection conn = ConnectDB.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, ma);
				ResultSet rs = stmt.executeQuery(sql);
	            while (rs.next()) {
	                String maKH = rs.getString("maKH");
	                int tuoiKH = rs.getInt("tuoiKH");
	                String tenKH = rs.getString("tenKH");
	                String sdt = rs.getString("sdt");
	                String email = rs.getString("email");
	     
	                KhachHang s = new KhachHang(maKH, tuoiKH, tenKH, sdt, email);
	                ds.add(s);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return ds;
	}
	
	public List<KhachHang> getAllKhachHang(){
		List<KhachHang> ds = new ArrayList<>();
		String sql = "Select * from KhachHang";
		try (Connection conn = ConnectDB.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            while (rs.next()) {
	                String maKH = rs.getString("maKH");
	                int tuoiKH = rs.getInt("tuoiKH");
	                String tenKH = rs.getString("tenKH");
	                String sdt = rs.getString("sdt");
	                String email = rs.getString("email");
	     
	                KhachHang s = new KhachHang(maKH, tuoiKH, tenKH, sdt, email);
	                ds.add(s);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return ds;
	}
	
	public boolean insert(KhachHang k) {
        String sql = "INSERT INTO KhachHang (maKH, tuoiKH, tenKH, sdt, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, k.getMaKH());
            ps.setInt(2, k.getTuoiKH());
            ps.setString(3, k.getTenKH());
            ps.setString(4, k.getSoDienThoaiKH());
            ps.setString(5, k.getEmailKH());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean update(KhachHang k) {
        String sql = "UPDATE KhachHang SET tuoiKH=?, tenKH=?, sdt=?, email=? WHERE maKH=?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, k.getTuoiKH());
            ps.setString(2, k.getTenKH());
            ps.setString(3, k.getSoDienThoaiKH());
            ps.setString(4, k.getEmailKH());
            ps.setString(5, k.getMaKH());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean delete(String maKH) {
        String sql = "DELETE FROM KhachHang WHERE maKH=?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maKH);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}
