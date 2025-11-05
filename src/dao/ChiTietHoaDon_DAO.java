package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entity.ChiTietHoaDon;
import Entity.ComboDoAn;
import Entity.Ve;
import connectDB.ConnectDB;

public class ChiTietHoaDon_DAO {
	private Ve_DAO ve_dao = new Ve_DAO();
	private ComboDoAn_DAO combo_dao = new ComboDoAn_DAO();
	
	public List<ChiTietHoaDon> getChiTietTheoMaHD(String ma){
		List<ChiTietHoaDon> ds = new ArrayList<>();
		String sql = "Select * from ChiTietHoaDon WHERE maHoaDon=?";
		try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, ma);
				ResultSet rs = stmt.executeQuery(); 
	            
				while (rs.next()) {
	                int soLuong = rs.getInt("soLuong");
	                
	                // Lấy thông tin Vé
	                List<Ve> ListVe = ve_dao.getVeTheoMaVe(rs.getString("maVe"));
	                Ve ve = ListVe.isEmpty() ? null : ListVe.get(0);

	                // Lấy thông tin Combo
	                List<ComboDoAn> ListComboDoAn = combo_dao.getComboTheoMaCombo(rs.getString("maCombo"));
	                ComboDoAn combo = ListComboDoAn.isEmpty() ? null : ListComboDoAn.get(0);

	                // Gọi constructor đúng thứ tự: (Ve, Combo, soLuong)
	                ChiTietHoaDon s = new ChiTietHoaDon(ve, combo, soLuong);
	                ds.add(s);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return ds;
	}
	
	public List<ChiTietHoaDon> getAllChiTietHD(){
		List<ChiTietHoaDon> ds = new ArrayList<>();
		String sql = "Select * from ChiTietHoaDon";
		try (Connection conn = ConnectDB.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            while (rs.next()) {
	            	int soLuong = rs.getInt("soLuong");
	                
	                List<Ve> ListVe = ve_dao.getVeTheoMaVe(rs.getString("maVe"));
	                Ve ve = ListVe.isEmpty() ? null : ListVe.get(0);
					
					List<ComboDoAn> ListComboDoAn = combo_dao.getComboTheoMaCombo(rs.getString("maCombo"));
	                ComboDoAn combo = ListComboDoAn.isEmpty() ? null : ListComboDoAn.get(0);
	                
					// Gọi constructor đúng thứ tự: (Ve, Combo, soLuong)
	                ChiTietHoaDon s = new ChiTietHoaDon(ve, combo, soLuong);
	                ds.add(s);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return ds;
	}
	
	// Phương thức này tuân thủ mối quan hệ:
	// Nó chỉ cần maHD (String) chứ không cần đối tượng HoaDon
	public boolean insert(ChiTietHoaDon v, String maHD) {
        String sql = "INSERT INTO ChiTietHoaDon (soLuong, maVe, maCombo, maHoaDon) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
			ps.setInt(1, v.getSoLuong());
            ps.setString(2, (v.getVe() != null) ? v.getVe().getMaVe() : null);
            ps.setString(3, (v.getCombo() != null) ? v.getCombo().getMaCombo() : null);
            ps.setString(4, maHD); // Dùng maHD làm khóa ngoại

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	// Tương tự, phương thức này chỉ cần maHD
    public boolean update(ChiTietHoaDon v, String maHD) {
        String sql = "UPDATE ChiTietHoaDon SET soLuong = ?, maVe = ?, maCombo = ? WHERE maHoaDon=?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	ps.setInt(1, v.getSoLuong());
        	ps.setString(2, (v.getVe() != null) ? v.getVe().getMaVe() : null);
            ps.setString(3, (v.getCombo() != null) ? v.getCombo().getMaCombo() : null);
            ps.setString(4, maHD);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteTheoMaHD(String maHD) {
        String sql = "DELETE FROM ChiTietHoaDon WHERE maHoaDon = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHD);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(String maVe, String maCombo) {
        String sql = "DELETE FROM ChiTietHoaDon WHERE maVe = ? AND maCombo=?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maVe);
            ps.setString(2, maCombo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}