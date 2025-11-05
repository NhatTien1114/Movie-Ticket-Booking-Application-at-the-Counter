package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entity.ComboDoAn;
import connectDB.ConnectDB;

public class ComboDoAn_DAO {

    public List<ComboDoAn> getComboTheoMaCombo(String ma) {
        List<ComboDoAn> ds = new ArrayList<>();
        String sql = "Select * from ComboDoAn WHERE maCombo=?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                String maCombo = rs.getString("maCombo");
                double gia = rs.getDouble("gia");
                String tenCombo = rs.getString("tenCombo");
                String path = rs.getString("path"); 
                int soLuong = rs.getInt("soLuong"); 

                ComboDoAn g = new ComboDoAn(maCombo, tenCombo, gia, path, soLuong);
                ds.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public List<ComboDoAn> getAllCombo() {
        List<ComboDoAn> ds = new ArrayList<>();
        String sql = "Select maCombo, tenCombo, gia, path, soLuong from ComboDoAn";
        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String maCombo = rs.getString("maCombo");
                double gia = rs.getDouble("gia");
                String tenCombo = rs.getString("tenCombo");
                String path = rs.getString("path"); 
                int soLuong = rs.getInt("soLuong"); 
                
                ComboDoAn g = new ComboDoAn(maCombo, tenCombo, gia, path, soLuong);
                ds.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public boolean insert(ComboDoAn c) {
        String sql = "INSERT INTO ComboDoAn (maCombo, tenCombo, gia, path, soLuong) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectDB.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, c.getMaCombo());
            ps.setString(2, c.getTenCombo());
            ps.setDouble(3, c.getGia());
            ps.setString(4, c.getPath());    
            ps.setInt(5, c.getSoLuong()); 
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean update(ComboDoAn c) {
        String sql = "UPDATE ComboDoAn SET tenCombo=?, gia=?, path=?, soLuong=? WHERE maCombo=?";
        try (Connection conn = ConnectDB.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, c.getTenCombo());
            ps.setDouble(2, c.getGia());
            ps.setString(3, c.getPath());   
            ps.setInt(4, c.getSoLuong()); 
            ps.setString(5, c.getMaCombo()); 
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean delete(String maCombo) {
        String sql = "DELETE FROM ComboDoAn WHERE maCombo=?";
        try (Connection conn = ConnectDB.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, maCombo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}