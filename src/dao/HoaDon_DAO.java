package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Entity.ChiTietHoaDon;
import Entity.HoaDon;
import Entity.KhachHang;
import connectDB.ConnectDB;

public class HoaDon_DAO {
	private ChiTietHoaDon_DAO chitiet_dao = new ChiTietHoaDon_DAO();
	private KhachHang_DAO kh_dao = new KhachHang_DAO();

	public List<HoaDon> getHoaDonTheoMaHD(String ma) {
		List<HoaDon> ds = new ArrayList<>();
		String sql = "Select * from HoaDon WHERE maHoaDon=?";
		try (Connection conn = ConnectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String maHoaDon = rs.getString("maHoaDon");
				LocalDateTime ngayLap = rs.getTimestamp("ngayLap").toLocalDateTime();

				String maKH = rs.getString("maKH");
				KhachHang kh = null;
				if (maKH != null) {
					List<KhachHang> listKH = kh_dao.getKhachHangTheoMaKH(maKH);
					if (!listKH.isEmpty()) {
						kh = listKH.get(0);
					}
				}

				List<ChiTietHoaDon> ListCTHD = chitiet_dao.getChiTietTheoMaHD(maHoaDon);

				HoaDon s = new HoaDon(maHoaDon, ngayLap, ListCTHD, kh);
				ds.add(s);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public List<HoaDon> getAllHoaDon() {
		List<HoaDon> ds = new ArrayList<>();
		String sql = "Select * from HoaDon";
		try (Connection conn = ConnectDB.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				String maHoaDon = rs.getString("maHoaDon");
				LocalDateTime ngayLap = rs.getTimestamp("ngayLap").toLocalDateTime();

				String maKH = rs.getString("maKH");
				KhachHang kh = null;
				if (maKH != null) {
					List<KhachHang> listKH = kh_dao.getKhachHangTheoMaKH(maKH);
					if (!listKH.isEmpty()) {
						kh = listKH.get(0);
					}
				}

				List<ChiTietHoaDon> ListCTHD = chitiet_dao.getChiTietTheoMaHD(maHoaDon);

				HoaDon s = new HoaDon(maHoaDon, ngayLap, ListCTHD, kh);
				ds.add(s);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public boolean insert(HoaDon h) {
		String sql = "INSERT INTO HoaDon (maHoaDon, ngayLap, maKH) VALUES (?, ?, ?)";
		try (Connection conn = ConnectDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, h.getMaHoaDon());
			ps.setTimestamp(2, Timestamp.valueOf(h.getNgayLap()));

			if (h.getKhachHang() != null) {
				ps.setString(3, h.getKhachHang().getMaKH());
			} else {
				ps.setNull(3, Types.VARCHAR);
			}

			for (ChiTietHoaDon c : h.getChiTiet()) {
				chitiet_dao.insert(c, h.getMaHoaDon());
			}
			int n = ps.executeUpdate();
			return n > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(HoaDon h) {
		String sql = "UPDATE HoaDon SET ngayLap=?, maKH=? WHERE maHoaDon=?";
		try (Connection conn = ConnectDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setTimestamp(1, Timestamp.valueOf(h.getNgayLap()));

			if (h.getKhachHang() != null) {
				ps.setString(2, h.getKhachHang().getMaKH());
			} else {
				ps.setNull(2, Types.VARCHAR);
			}
			ps.setString(3, h.getMaHoaDon());

			for (ChiTietHoaDon c : h.getChiTiet()) {
				chitiet_dao.update(c, h.getMaHoaDon());
			}
			int n = ps.executeUpdate();
			return n > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(String maHoaDon) {
		String sql = "DELETE FROM HoaDon WHERE maHoaDon=?";
		try (Connection conn = ConnectDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, maHoaDon);

			chitiet_dao.deleteTheoMaHD(maHoaDon);

			int n = ps.executeUpdate();
			return n > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}