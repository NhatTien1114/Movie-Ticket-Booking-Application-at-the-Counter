package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entity.*;
import connectDB.ConnectDB;
public class Ve_DAO {
	private Ghe_DAO ghe_dao = new Ghe_DAO();
	private SuatChieu_DAO suat_dao = new SuatChieu_DAO();
	
	public List<Ve> getVeTheoMaVe(String ma){
		List<Ve> ds = new ArrayList<>();
		String sql = "SELECT * FROM Ve WHERE maVe=?";
		try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, ma);
				ResultSet rs = stmt.executeQuery(sql);
	            while (rs.next()) {
	                String maVe = rs.getString("maVe");
	                Double giaVe = rs.getDouble("giaVe");
	                Boolean trangThai = rs.getBoolean("trangThaiSuDung");
	                
	                //Thong tin Ghe cua Ve
	                List<Ghe> ListGhe = ghe_dao.getGheTheoMaGhe(rs.getString("maGhe"));
	                //Thong tin SuatChieu cua Ve
	                List<SuatChieu> ListSuatChieu = suat_dao.getSuatChieuTheoMa(rs.getString("maSuatChieu"));

	                Ve v = new Ve(maVe, giaVe, trangThai, ListGhe.get(0), ListSuatChieu.get(0));
	                ds.add(v);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return ds;
	}
	
	public List<Ve> getAllVe() {
        List<Ve> ds = new ArrayList<>();
        String sql = "SELECT * FROM Ve";
        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String maVe = rs.getString("maVe");
                Double giaVe = rs.getDouble("giaVe");
                Boolean trangThai = rs.getBoolean("trangThaiSuDung");
                List<Ghe> ListGhe = ghe_dao.getGheTheoMaGhe(rs.getString("maGhe"));
                List<SuatChieu> ListSuatChieu = suat_dao.getSuatChieuTheoMa(rs.getString("maSuatChieu"));

                Ve v = new Ve(maVe, giaVe, trangThai, ListGhe.get(0), ListSuatChieu.get(0));
                ds.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
	
	 public boolean insert(Ve v) {
	        String sql = "INSERT INTO Ve (maVe, giaVe, trangThaiSuDung, maGhe, maSuatChieu) VALUES (?, ?, ?, ?, ?)";
	        try (Connection conn = ConnectDB.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, v.getMaVe());
	            ps.setDouble(2, v.getGiaVe());
	            ps.setBoolean(3, v.isTrangThaiSuDung());
	            ps.setString(4, v.getGhe().getMaGhe());
	            ps.setString(5, v.getSuatChieu().getMaSuatChieu());
	            return ps.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public boolean update(Ve v) {
	        String sql = "UPDATE Ve SET giaVe = ?, trangThaiSuDung = ?, maGhe = ?, maSuatChieu = ? WHERE maVe = ?";
	        try (Connection conn = ConnectDB.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setDouble(1, v.getGiaVe());
	            ps.setBoolean(2, v.isTrangThaiSuDung());
	            ps.setString(3, v.getGhe().getMaGhe());
	            ps.setString(4, v.getSuatChieu().getMaSuatChieu());
	            ps.setString(5, v.getMaVe());
	            return ps.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public boolean delete(String maVe) {
	        String sql = "DELETE FROM Ve WHERE maVe = ?";
	        try (Connection conn = ConnectDB.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, maVe);
	            return ps.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
}
