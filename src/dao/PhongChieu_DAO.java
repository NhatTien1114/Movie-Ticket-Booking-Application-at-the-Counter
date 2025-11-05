package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entity.Ghe;
import Entity.PhongChieu;
import Entity.SuatChieu;
import Entity.Ve;
import connectDB.ConnectDB;

public class PhongChieu_DAO {
	private Ghe_DAO ghe_dao = new Ghe_DAO();
	
	public List<PhongChieu> getPhongChieuTheoMaPhong(String maPhong){
		List<PhongChieu> ds = new ArrayList<>();
		String sql = "SELECT * FROM PhongChieu WHERE maPhong=?";
		try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, maPhong);
				ResultSet rs = stmt.executeQuery(sql);
	            while (rs.next()) {
	                Boolean trangThai = rs.getBoolean("trangThai");
	                List<Ghe> listG = ghe_dao.getAllGheTheoMaPhong(maPhong); // lay ds Ghe cua Phong Chieu

	                PhongChieu p = new PhongChieu(maPhong, trangThai, listG);
	                ds.add(p);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return ds;
	}
	
	public List<PhongChieu> getAllPhongChieu() {
        List<PhongChieu> ds = new ArrayList<>();
        String sql = "SELECT * FROM PhongChieu";
        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String maPhong = rs.getString("maPhong");
                Boolean trangThai = rs.getBoolean("trangThai");
                List<Ghe> listG = ghe_dao.getAllGheTheoMaPhong(maPhong); // lay ds Ghe cua Phong Chieu

                PhongChieu p = new PhongChieu(maPhong, trangThai, listG);
                ds.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
	
	public boolean insert(PhongChieu pc) {
        String sql = "INSERT INTO PhongChieu (maPhong, trangThai) VALUES (?, ?)";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pc.getMaPhong());
            ps.setBoolean(2, pc.isTrangThai());
            for(Ghe g : pc.getDanhSachGhe()) {
            	ghe_dao.insert(g, pc.getMaPhong());
            }
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(PhongChieu pc) {
        String sql = "UPDATE PhongChieu SET trangThai = ? WHERE maPhong = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, pc.isTrangThai());
            ps.setString(2, pc.getMaPhong());
            for(Ghe g : pc.getDanhSachGhe()) {
            	ghe_dao.update(g, pc.getMaPhong());
            }
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String maPhong) {
        String sql = "DELETE FROM PhongChieu WHERE maPhong = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maPhong);
            ghe_dao.deleteTheoMaPhong(maPhong); // xoa tat ca ghe theo ma phong
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
