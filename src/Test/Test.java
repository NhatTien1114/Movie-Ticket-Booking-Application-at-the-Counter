package Test;

import java.awt.EventQueue;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarkLaf;

import UI.Form_GiaoDienChinh;
import UI.Form_DangKy;
import UI.Form_DangNhap;
import Interface.LoginListener;

public class Test implements LoginListener {

	private Form_GiaoDienChinh mainFrame;
	private Form_DangNhap loginFrame;
	private Form_DangKy registerFrame;

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
		mainFrame = new Form_GiaoDienChinh(this); 
		mainFrame.setVisible(false);

		loginFrame = new Form_DangNhap(this);
		loginFrame.setVisible(true);
	}

	@Override
	public boolean onLoginAttempt(String username, String password) {
		if ("admin_Tien".equals(username) && "123".equals(password)) {
			mainFrame.setLoginSuccess(username);
			mainFrame.setVisible(true);
			return true;
		}
		
		// TODO: Thêm logic kiểm tra tài khoản từ database (đã đăng ký)
		// ...

		// Đăng nhập thất bại
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