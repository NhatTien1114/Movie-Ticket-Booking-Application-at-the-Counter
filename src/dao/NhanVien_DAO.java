package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import Entity.NhanVien;
import Entity.TaiKhoan;

public class NhanVien_DAO {
	public List<TaiKhoan> getTaiKhoanTheoMaNV(String ma){
		List<TaiKhoan> ds = new ArrayList<>();
		String sql = "Select * from TaiKhoan where maMV=?";
		try (Connection conn = ConnectDB.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			 	ps.setString(1, ma);
			 	ResultSet rs = ps.executeQuery(sql);
	            while (rs.next()) {
	                String tenDangNhap = rs.getString("tenDangNhap");
	                String matKhau = rs.getString("matKhau");
	                String email = rs.getString("email");
	                String soDienThoai = rs.getString("soDienThoai");

	                TaiKhoan nv = new TaiKhoan(tenDangNhap, matKhau, email, soDienThoai);
	                ds.add(nv);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return ds;
	}
	
	public List<TaiKhoan> getAllTaiKhoanNV(){
		List<TaiKhoan> ds = new ArrayList<>();
		String sql = "Select * from TaiKhoan";
		try (Connection conn = ConnectDB.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            while (rs.next()) {
	                String tenDangNhap = rs.getString("tenDangNhap");
	                String matKhau = rs.getString("matKhau");
	                String email = rs.getString("email");
	                String soDienThoai = rs.getString("soDienThoai");

	                TaiKhoan nv = new TaiKhoan(tenDangNhap, matKhau, email, soDienThoai);
	                ds.add(nv);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return ds;
	}
	
	public List<NhanVien> getNhanVienTheoMaNV(String ma){
		List<NhanVien> ds = new ArrayList<>();
		String sql = """
	            SELECT nv.maNV, nv.hoTen, tk.tenDangNhap, tk.matKhau, tk.email, tk.soDienThoai
	            FROM NhanVien nv
	            JOIN TaiKhoan tk ON nv.maNV = tk.maNV
	            WHERE nv.maNV=?
	        """;
		try (Connection conn = ConnectDB.getConnection();
				 PreparedStatement ps = conn.prepareStatement(sql)) {
				 	ps.setString(1, ma);
				 	ResultSet rs = ps.executeQuery(sql);
		            while (rs.next()) {
		            	String maNV = rs.getString("maNV");
		                String hoTen = rs.getString("hoTen");
		                String tenDangNhap = rs.getString("tenDangNhap");
		                String matKhau = rs.getString("matKhau");
		                String email = rs.getString("email");
		                String soDienThoai = rs.getString("soDienThoai");

		                NhanVien nv = new NhanVien(maNV, hoTen, tenDangNhap, matKhau, email, soDienThoai);
		                ds.add(nv);
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		return ds;
	}
	
	public List<NhanVien> getAllNhanVien() {
        List<NhanVien> ds = new ArrayList<>();

        String sql = """
            SELECT nv.maNV, nv.hoTen, tk.tenDangNhap, tk.matKhau, tk.email, tk.soDienThoai
            FROM NhanVien nv
            JOIN TaiKhoan tk ON nv.maNV = tk.maNV
        """;

        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String maNV = rs.getString("maNV");
                String hoTen = rs.getString("hoTen");
                String tenDangNhap = rs.getString("tenDangNhap");
                String matKhau = rs.getString("matKhau");
                String email = rs.getString("email");
                String soDienThoai = rs.getString("soDienThoai");

                NhanVien nv = new NhanVien(maNV, hoTen, tenDangNhap, matKhau, email, soDienThoai);
                ds.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
	 	
    public boolean insert(NhanVien nv) {
        String sql = "INSERT INTO NhanVien (maNV, hoTen) VALUES (?, ?)";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getHoTen());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean update(NhanVien nv) {
        String sql = "UPDATE NhanVien SET hoTen=? WHERE maNV=?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nv.getHoTen());
            ps.setString(2, nv.getMaNV());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean delete(String maNV) {
        String sql = "DELETE FROM NhanVien WHERE maNV=?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNV);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}
