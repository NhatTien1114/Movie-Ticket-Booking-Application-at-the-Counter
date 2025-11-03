package Test;

import java.awt.EventQueue;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarkLaf;

import UI.Form_GiaoDienChinh;
import UI.Form_DangKy;
import UI.Form_DangNhap;
import Interface.LoginListener;

// 1. Implement LoginListener
public class Test implements LoginListener {

	private Form_GiaoDienChinh mainFrame;
	private Form_DangNhap loginFrame;
	private Form_DangKy registerFrame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatDarkLaf());
					// 2. Khởi tạo Test (controller) và gọi start()
					new Test().start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Bắt đầu ứng dụng
	 */
	public void start() {
		// Tạo Form_GiaoDienChinh nhưng KHÔNG hiển thị
		// Sửa ở đây: Truyền 'this' (Test controller) vào constructor
		mainFrame = new Form_GiaoDienChinh(this); 
		mainFrame.setVisible(false);

		// Tạo và hiển thị Form_DangNhap.
		// Truyền 'this' (chính là Test) vào làm listener.
		loginFrame = new Form_DangNhap(this);
		loginFrame.setVisible(true);
	}

	// 3. Xử lý logic khi Form_DangNhap gọi
	@Override
	public boolean onLoginAttempt(String username, String password) {
		// Logic kiểm tra đăng nhập của bạn
		// Ví dụ kiểm tra tài khoản admin
		if ("admin_Tien".equals(username) && "123".equals(password)) {
			// Đăng nhập thành công!
			// 1. Gọi phương thức trong Form_GiaoDienChinh để cập nhật UI
			mainFrame.setLoginSuccess(username);
			// 2. Hiển thị Form_GiaoDienChinh
			mainFrame.setVisible(true);
			// 3. Form_DangNhap sẽ tự đóng (nhờ return true)
			return true;
		}
		
		// TODO: Thêm logic kiểm tra tài khoản từ database (đã đăng ký)
		// ...

		// Đăng nhập thất bại
		return false;
	}

	// 4. Xử lý khi Form_DangNhap muốn mở Form_DangKy
	@Override
	public void onRegisterRequest() {
		if (loginFrame != null) {
			loginFrame.dispose(); // Đóng hẳn form đăng nhập
		}
        if (mainFrame != null) {
            mainFrame.dispose(); // Đóng luôn form chính nếu đang mở
        }
		
		// Tạo và hiển thị form đăng ký, truyền listener 'this'
		registerFrame = new Form_DangKy(this);
		registerFrame.setVisible(true);
	}

	// 5. Xử lý khi Form_DangKy đăng ký xong
	@Override
	public void onShowLogin() {
		if (registerFrame != null) {
			registerFrame.dispose(); // Đóng hẳn form đăng ký
		}
        if (mainFrame != null) {
            mainFrame.dispose(); // Đóng luôn form chính nếu đang mở
        }
		
		// Hiển thị lại form đăng nhập
		if (loginFrame == null || !loginFrame.isDisplayable()) { // Kiểm tra nếu đã bị dispose
			loginFrame = new Form_DangNhap(this);
		}
		loginFrame.setVisible(true);
	}
}