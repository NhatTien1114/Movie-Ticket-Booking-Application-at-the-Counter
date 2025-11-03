package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
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

import Interface.LoginListener;

import javax.swing.JButton;

public class Form_DangNhap extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtTaiKhoan;
	private JPasswordField txtMatKhau;
	private JButton btnDangKy;
	private JButton btnDangNhap;
	private LoginListener listener;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Form_DangNhap frame = new Form_DangNhap();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Form_DangNhap(LoginListener listener) {
		this.listener = listener;
		Color primary = new Color(26, 26, 46);
		Color accent = new Color(233, 69, 96);
		Color hoverColor = accent.brighter();
		Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Image/login.png"));
		setIconImage(icon);
		setSize(400, 300);
		setTitle("Đăng Nhập");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		JPanel pNorth = new JPanel();
		JPanel pCenter = new JPanel();

		getContentPane().add(pNorth, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("ĐĂNG NHẬP");
		lblTitle.setFont(new Font("Roboto", Font.BOLD, 28));
		lblTitle.setForeground(Color.white);
		pNorth.add(lblTitle);
		getContentPane().add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(null);

		JLabel lblTaiKhoan = new JLabel("Tài khoản\r\n");
		lblTaiKhoan.setIcon(new ImageIcon("src/Image/user.png"));
		lblTaiKhoan.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblTaiKhoan.setBounds(37, 24, 88, 38);
		pCenter.add(lblTaiKhoan);

		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setBounds(128, 34, 221, 20);
		pCenter.add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);

		JLabel lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setIcon(new ImageIcon("src/Image/padlock.png"));
		lblMatKhau.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblMatKhau.setBounds(37, 81, 88, 24);
		pCenter.add(lblMatKhau);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setColumns(10);
		txtMatKhau.setBounds(128, 84, 221, 20);
		pCenter.add(txtMatKhau);

		btnDangNhap = new JButton("Đăng nhập\r\n");
		btnDangNhap.setFont(new Font("Roboto", Font.BOLD, 14));
		btnDangNhap.setBackground(accent);
		btnDangNhap.setOpaque(true);
		btnDangNhap.setBorderPainted(false);
		btnDangNhap.setForeground(Color.white);
		btnDangNhap.setBounds(66, 153, 107, 23);
		btnDangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pCenter.add(btnDangNhap);
		btnDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				btnDangNhap.setBackground(hoverColor);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				btnDangNhap.setBackground(accent);
			}
		});

		btnDangKy = new JButton("Đăng ký");
		btnDangKy.setFont(new Font("Roboto", Font.BOLD, 14));
		btnDangKy.setBackground(accent);
		btnDangKy.setOpaque(true);
		btnDangKy.setBorderPainted(false);
		btnDangKy.setForeground(Color.white);
		btnDangKy.setBounds(217, 153, 107, 23);
		btnDangKy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pCenter.add(btnDangKy);
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
		btnDangKy.addActionListener(this);
		btnDangNhap.addActionListener(this);
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
			// Thay vì tạo mới, hãy gọi listener
			if (listener != null) {
				listener.onRegisterRequest();
			}
			dispose(); // Tự đóng

		} else if (o.equals(btnDangNhap)) { // <--- ĐÃ ĐƯA RA NGOÀI
			String user = txtTaiKhoan.getText();
			String pass = new String(txtMatKhau.getPassword());

			if (listener != null) {
				// Sửa ở đây: Gọi onLoginAttempt
				boolean loginSuccess = listener.onLoginAttempt(user, pass);

				if (loginSuccess) {
					dispose(); // Đăng nhập đúng -> tự đóng
				} else {
					// Đăng nhập sai -> báo lỗi và không đóng
					JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng!", "Lỗi Đăng Nhập",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		}
	}
}
