package Test;

import java.awt.EventQueue;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarkLaf;

import Entity.NhanVien;
import UI.Form_GiaoDienChinh;
import dao.NhanVien_DAO;
import UI.Form_DangKy;
import UI.Form_DangNhap;
import Interface.LoginListener;

public class Test implements LoginListener {

	private Form_GiaoDienChinh mainFrame;
	private Form_DangNhap loginFrame;
	private Form_DangKy registerFrame;
	private NhanVien_DAO nhanVienDAO;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatDarkLaf());
					new Test().start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void start() {
		nhanVienDAO = new NhanVien_DAO();
		mainFrame = new Form_GiaoDienChinh(this); 
		mainFrame.setVisible(false);

		loginFrame = new Form_DangNhap(this);
		loginFrame.setVisible(true);
	}

	@Override
	public boolean onLoginAttempt(String username, String password) {
		// Giữ lại tài khoản admin gốc
		if ("admin_Tien".equals(username) && "123".equals(password)) {
			mainFrame.setLoginSuccess(username);
			mainFrame.setVisible(true);
			return true;
		}
		NhanVien nv = nhanVienDAO.getNhanVienByTenDangNhap(username);
		
		if (nv != null && nv.getMatKhau().equals(password)) {
			mainFrame.setLoginSuccess(nv.getHoTen()); 
			mainFrame.setVisible(true);
			return true;
		}

		return false;
	}
	
	@Override
	public boolean onRegisterAttempt(NhanVien nv) {
		if (nhanVienDAO != null) {
			return nhanVienDAO.insert(nv); 
		}
		return false;
	}

	@Override
	public void onRegisterRequest() {
		if (loginFrame != null) {
			loginFrame.dispose(); 
		}
        if (mainFrame != null) {
            mainFrame.dispose(); 
        }
		
		registerFrame = new Form_DangKy(this);
		registerFrame.setVisible(true);
	}

	@Override
	public void onShowLogin() {
		if (registerFrame != null) {
			registerFrame.dispose(); 
		}
        if (mainFrame != null) {
            mainFrame.dispose();
        }
		
		if (loginFrame == null || !loginFrame.isDisplayable()) {
			loginFrame = new Form_DangNhap(this);
		}
		loginFrame.setVisible(true);
	}
}