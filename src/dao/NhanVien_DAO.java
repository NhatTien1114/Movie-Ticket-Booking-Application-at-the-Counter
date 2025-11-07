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
	
	public NhanVien getNhanVienByTenDangNhap(String tenDN) {
		NhanVien nv = null;
		String sql = """
				SELECT nv.maNV, nv.hoTen, tk.tenDangNhap, tk.matKhau, tk.email, tk.soDienThoai
				FROM NhanVien nv
				JOIN TaiKhoan tk ON nv.maNV = tk.maNV
				WHERE tk.tenDangNhap = ?
			""";
		try (Connection conn = ConnectDB.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setString(1, tenDN);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String maNV = rs.getString("maNV");
				String hoTen = rs.getString("hoTen");
				String tenDangNhap = rs.getString("tenDangNhap");
				String matKhau = rs.getString("matKhau");
				String email = rs.getString("email");
				String soDienThoai = rs.getString("soDienThoai");
				nv = new NhanVien(maNV, hoTen, tenDangNhap, matKhau, email, soDienThoai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}
	
	public boolean checkTaiKhoanTonTai(String tenDN) {
		String sql = "SELECT 1 FROM TaiKhoan WHERE tenDangNhap = ?";
		try (Connection conn = ConnectDB.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, tenDN);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next(); // True nếu đã tồn tại
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return true; // Mặc định là có lỗi thì không cho đăng ký
		}
	}
	
	public int getSoLuongNhanVien() {
		String sql = "SELECT COUNT(*) FROM NhanVien";
		try (Connection conn = ConnectDB.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	 	
	public boolean insert(NhanVien nv) {
		String sqlNhanVien = "INSERT INTO NhanVien (maNV, hoTen) VALUES (?, ?)";
		String sqlTaiKhoan = "INSERT INTO TaiKhoan (tenDangNhap, matKhau, email, soDienThoai, maNV) VALUES (?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement psNV = null;
		PreparedStatement psTK = null;
		
		try {
			conn = ConnectDB.getConnection();
			conn.setAutoCommit(false); 

			// 1. Thêm vào bảng NhanVien
			psNV = conn.prepareStatement(sqlNhanVien);
			psNV.setString(1, nv.getMaNV());
			psNV.setString(2, nv.getHoTen());
			int rsNV = psNV.executeUpdate();

			// 2. Thêm vào bảng TaiKhoan
			psTK = conn.prepareStatement(sqlTaiKhoan);
			psTK.setString(1, nv.getTenDangNhap()); 
			psTK.setString(2, nv.getMatKhau());    
			psTK.setString(3, nv.getEmail());      
			psTK.setString(4, nv.getSoDienThoai()); 
			psTK.setString(5, nv.getMaNV());     
			int rsTK = psTK.executeUpdate();

			
			if (rsNV > 0 && rsTK > 0) {
				conn.commit(); // Hoàn tất Transaction
				return true;
			} else {
				conn.rollback(); // Hủy bỏ Transaction
				return false;
			}

		} catch (SQLException e) {
			try {
				if (conn != null) conn.rollback(); // Hủy bỏ Transaction nếu có lỗi
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (psNV != null) psNV.close();
				if (psTK != null) psTK.close();
				if (conn != null) {
					conn.setAutoCommit(true); // Thiết lập lại auto-commit
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
