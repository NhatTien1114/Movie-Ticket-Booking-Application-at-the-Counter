package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import Entity.NhanVien;
import Interface.LoginListener;
import dao.NhanVien_DAO;

import javax.swing.JButton;

public class Form_DangKy extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtTaiKhoan;
	private JPasswordField txtMatKhau;
	private JTextField txtEmail;
	private JTextField txtSdt;
	private JTextField txtHoVaTen;
	private JButton btnDangKy;
	private LoginListener listener;
	private NhanVien_DAO nhanVienDAO;

	public Form_DangKy(LoginListener listener) {
		this.listener = listener;
		this.nhanVienDAO = new NhanVien_DAO();

		Color primary = new Color(26, 26, 46);
		Color accent = new Color(233, 69, 96);
		Color hoverColor = accent.brighter();
		Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Image/add.png"));
		setIconImage(icon);
		setSize(400, 450);
		setTitle("Đăng Ký");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		JPanel pNorth = new JPanel();
		JPanel pCenter = new JPanel();

		getContentPane().add(pNorth, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("ĐĂNG KÝ");
		lblTitle.setFont(new Font("Roboto", Font.BOLD, 28));
		lblTitle.setForeground(primary);
		pNorth.add(lblTitle);
		getContentPane().add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(null);

		JLabel lblTaiKhoan = new JLabel("Tài khoản\r\n");
		lblTaiKhoan.setIcon(new ImageIcon("src/Image/user.png"));
		lblTaiKhoan.setForeground(primary);
		lblTaiKhoan.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblTaiKhoan.setBounds(37, 30, 88, 29);
		pCenter.add(lblTaiKhoan);

		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setBounds(128, 35, 221, 20);
		txtTaiKhoan.setColumns(10);
		pCenter.add(txtTaiKhoan);

		JLabel lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setForeground(primary);
		lblMatKhau.setIcon(new ImageIcon("src/Image/padlock.png"));
		lblMatKhau.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblMatKhau.setBounds(37, 130, 88, 29);
		pCenter.add(lblMatKhau);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setColumns(10);
		txtMatKhau.setBounds(128, 135, 221, 20);
		pCenter.add(txtMatKhau);

		btnDangKy = new JButton("Đăng ký");
		btnDangKy.setFont(new Font("Roboto", Font.BOLD, 14));
		btnDangKy.setBackground(accent);
		btnDangKy.setOpaque(true);
		btnDangKy.setBorderPainted(false);
		btnDangKy.setForeground(Color.white);
		btnDangKy.setBounds(146, 296, 107, 23);
		btnDangKy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pCenter.add(btnDangKy);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(primary);
		lblEmail.setIcon(new ImageIcon("src/Image/email.png"));
		lblEmail.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblEmail.setBounds(37, 180, 88, 29);
		pCenter.add(lblEmail);

		JLabel lblSdt = new JLabel("SĐT");
		lblSdt.setForeground(primary);
		lblSdt.setIcon(new ImageIcon("src/Image/phone.png"));
		lblSdt.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblSdt.setBounds(37, 230, 88, 29);
		pCenter.add(lblSdt);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(128, 185, 221, 20);
		pCenter.add(txtEmail);

		txtSdt = new JTextField();
		txtSdt.setColumns(10);
		txtSdt.setBounds(128, 235, 221, 20);
		pCenter.add(txtSdt);

		JLabel lblHoTen = new JLabel("Họ và Tên\r\n");
		lblHoTen.setForeground(primary);
		lblHoTen.setIcon(new ImageIcon("src/Image/name.png"));
		lblHoTen.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblHoTen.setBounds(37, 80, 88, 38);
		pCenter.add(lblHoTen);

		txtHoVaTen = new JTextField();
		txtHoVaTen.setColumns(10);
		txtHoVaTen.setBounds(128, 90, 221, 20);
		pCenter.add(txtHoVaTen);
		btnDangKy.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				btnDangKy.setBackground(hoverColor);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				btnDangKy.setBackground(accent);
			}
		});

		setLabelColor(this, Color.white);

		btnDangKy.addActionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnDangKy)) {

			String username = txtTaiKhoan.getText().trim();
			String password = new String(txtMatKhau.getPassword()).trim();
			String email = txtEmail.getText().trim();
			String sdt = txtSdt.getText().trim();
			String hoTen = txtHoVaTen.getText().trim();

			// ====== VALIDATION ======
			if (username.isEmpty() || password.isEmpty() || email.isEmpty() || sdt.isEmpty() || hoTen.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Cảnh báo",
						JOptionPane.WARNING_MESSAGE);
				txtTaiKhoan.requestFocus();
				return;
			}

			if (!username.matches("^[A-Za-z]{2}.*$")) {
				JOptionPane.showMessageDialog(this, "Tên đăng nhập phải bắt đầu bằng 2 chữ cái bất kỳ!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				txtTaiKhoan.selectAll();
				txtTaiKhoan.requestFocus();
				return;
			}

			if (hoTen.trim().split("\\s+").length < 2) {
				JOptionPane.showMessageDialog(this, "Họ và tên phải có ít nhất 2 từ trở lên!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				txtHoVaTen.selectAll();
				txtHoVaTen.requestFocus();
				return;
			}

			if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
				JOptionPane.showMessageDialog(this, "Mật khẩu phải ít nhất 8 ký tự và chứa cả chữ và số!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				txtMatKhau.selectAll();
				txtMatKhau.requestFocus();
				return;
			}

			if (!email.matches("^[A-Za-z0-9._%+-]*\\d+[A-Za-z0-9._%+-]*@gmail\\.com$")) {
				JOptionPane.showMessageDialog(this,
						"Email phải có đuôi @gmail.com và có ít nhất 1 số trước đó (VD: abc123@gmail.com)!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				txtEmail.selectAll();
				txtEmail.requestFocus();
				return;
			}

			if (!sdt.matches("^0\\d{9}$")) {
				JOptionPane.showMessageDialog(this, "Số điện thoại phải bắt đầu bằng 0 và có đúng 10 số!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				txtSdt.selectAll();
				txtSdt.requestFocus();
				return;
			}

			// ====== LOGIC ĐĂNG KÝ MỚI ======

			// 1. Kiểm tra tài khoản đã tồn tại chưa
			if (nhanVienDAO.checkTaiKhoanTonTai(username)) {
				JOptionPane.showMessageDialog(this, "Tên tài khoản đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				txtTaiKhoan.selectAll();
				txtTaiKhoan.requestFocus();
				return;
			}

			// 2. Tạo mã nhân viên mới (Ví dụ: NV001, NV002...)
			int soLuongNV = nhanVienDAO.getSoLuongNhanVien() + 1;
			String maNV = String.format("NV%03d", soLuongNV); 

			// 3. Tạo đối tượng NhanVien
			NhanVien nvMoi = new NhanVien(maNV, hoTen, username, password, email, sdt);

			// 4. Gọi listener để Controller xử lý việc insert
			boolean dangKyThanhCong = false;
			if (listener != null) {
				dangKyThanhCong = listener.onRegisterAttempt(nvMoi);
			}

			// 5. Xử lý kết quả
			if (dangKyThanhCong) {
				JOptionPane.showMessageDialog(this, "Đăng ký thành công! Vui lòng đăng nhập.");
				if (listener != null) {
					listener.onShowLogin(); // Yêu cầu controller mở lại form đăng nhập
				}
				dispose(); 
			} else {
				JOptionPane.showMessageDialog(this, "Đã có lỗi xảy ra trong quá trình đăng ký.", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void setLabelColor(Container container, Color color) {
		for (Component comp : container.getComponents()) {
			if (comp instanceof JLabel) {
				((JLabel) comp).setForeground(color);
			} else if (comp instanceof Container) {
				setLabelColor((Container) comp, color);
			}
		}
	}
}
