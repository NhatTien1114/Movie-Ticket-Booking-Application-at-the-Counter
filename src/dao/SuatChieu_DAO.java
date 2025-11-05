package dao;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Entity.*;
import connectDB.ConnectDB;
public class SuatChieu_DAO {
	private Phim_DAO phim_dao = new Phim_DAO();
	private PhongChieu_DAO phong_dao = new PhongChieu_DAO();
	
	public List<SuatChieu> getSuatChieuTheoMa(String ma) {
        List<SuatChieu> ds = new ArrayList<>();
        String sql = "SELECT * FROM SuatChieu WHERE maSuatChieu=?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	stmt.setString(1, ma);
        	ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maSC = rs.getString("maSuatChieu");
                LocalDateTime ngayGio = rs.getTimestamp("ngayGio").toLocalDateTime();
                
                //lay thong tin Phim cua SuatChieu
                List<Phim> ListPhim = phim_dao.getPhimTheoMaPhim(rs.getString("maPhim")); 
                //lay thong tin PhongChieu cua SuatChieu
                List<PhongChieu> ListPhong= phong_dao.getPhongChieuTheoMaPhong(rs.getString("maPhong")); 

                SuatChieu s = new SuatChieu(maSC, ngayGio, ListPhim.get(0), ListPhong.get(0));
                ds.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
	
	public List<SuatChieu> getAllSuatChieu() {
        List<SuatChieu> ds = new ArrayList<>();
        String sql = "SELECT * FROM SuatChieu";
        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String maSC = rs.getString("maSuatChieu");
                LocalDateTime ngayGio = rs.getTimestamp("ngayGio").toLocalDateTime();
                
                //lay thong tin Phim cua SuatChieu
                List<Phim> ListPhim = phim_dao.getPhimTheoMaPhim(rs.getString("maPhim"));
                //lay thong tin PhongChieu cua SuatChieu
                List<PhongChieu> ListPhong= phong_dao.getPhongChieuTheoMaPhong(rs.getString("maPhong"));

                SuatChieu s = new SuatChieu(maSC, ngayGio, ListPhim.get(0), ListPhong.get(0));
                ds.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
	
	public boolean insert(SuatChieu s) {
        String sql = "INSERT INTO SuatChieu (maSuatChieu, ngayGio, maPhong, maPhim) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getMaSuatChieu());
            ps.setTimestamp(2, Timestamp.valueOf(s.getNgayGio()));
            ps.setString(3, s.getPhongChieu().getMaPhong());
            ps.setString(4, s.getPhim().getMaPhim());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean update(SuatChieu s) {
        String sql = "UPDATE SuatChieu SET ngayGio=?, maPhong=?, maPhim=? WHERE maSuatChieu=?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(s.getNgayGio()));
            ps.setString(2, s.getPhongChieu().getMaPhong());
            ps.setString(3, s.getPhim().getMaPhim());
            ps.setString(4, s.getMaSuatChieu());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean delete(String maSuatChieu) {
        String sql = "DELETE FROM SuatChieu WHERE maSuatChieu=?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maSuatChieu);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}
