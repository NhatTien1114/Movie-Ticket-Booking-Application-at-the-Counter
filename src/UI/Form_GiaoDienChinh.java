package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Entity.DanhSachNhanVien;
import Entity.DanhSachPhim;
import Entity.ComboDoAn;
import Entity.DanhSachCombo;
import Entity.MoviePosterPanel;
import Entity.NhanVien;
import Entity.Phim;
import Entity.PhongChieu;
import Entity.SuatChieu;
import Entity.TaiKhoan;

import Interface.LoginListener;

import java.util.Date;
import java.util.Locale;
import java.util.List;

public class Form_GiaoDienChinh extends JFrame implements ActionListener, MouseListener, KeyListener, FocusListener {

	private LoginListener listener;
	private boolean isLoggedIn = false;
	private String loggedInUsername = "";
	private static final long serialVersionUID = 1L;
	private JLabel lblTime;
	private JButton btnLogin;
	private JButton btnRegister;
	private JMenu selectedMenu = null;
	private int currentSlide = 0;
	private final String[] imagePaths = { "src/Image/conan-movie-28-3_1752485506068.jpg",
			"src/Image/cuoc-chien-hot-xoan-627_1754364824337.jpg", "src/Image/mang-me-di-bo-2048_1753070307369.jpg",
			"src/Image/chi-dai-cuong-sat-2048_1755503651466.jpg", "src/Image/mua-do-2048_1755156128857.jpg" };
	private CardLayout cardLayout;
	private JPanel slidePanel;
	Color primary = new Color(26, 26, 46);
	Color accent = new Color(233, 69, 96);
	private DefaultTableModel model;
	private JPanel pCenter;
	private CardLayout centerCardLayout;
	private JPanel contentPanel;
	// Panel
	private JPanel quanLyNhanVienPanel;
	private JPanel quanLyPhimPanel;
	private JPanel quanLyComboPanel;

	private JMenuItem itemDsNV;
	private JTextField txtTim;
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtEmail;
	private JTextField txtSdt;
	private JTable table;
	private JButton btnTimNhanVien;
	private JButton btnXoaNhanVien;
	private JButton btnCapNhatNhanVien;
	private DanhSachNhanVien dsNV;
	private DanhSachPhim dsPhim;
	private DanhSachCombo dsCombo;
	private JTextField txtTheLoai;
	private JTextField txtThoiLuong;
	private JTextField txtDaoDien;
	private JTextField txtTrangThai;
	private JMenuItem itemDsP;
	private JMenuItem itemPDC;
	private JMenuItem itemPSR;
	private JMenuItem itemCombo;
	private JButton btnTimPhim;
	private JButton btnCapNhatPhim;
	private JButton btnXoaPhim;
	private JButton btnThemPhim;
	private JTextField txtMaPhim;
	private JTextField txtTenPhim;
	private JTable tablePhim;
	private DefaultTableModel modelPhim;
	private JTextField txtTimPhim;
	private JTextArea txtMoTa;
	private JButton btnChonAnh;
	private JTextField txtPathAnh;
	private JTextField txtSearch;
	private JPanel pMovieRow;
	private DefaultTableModel modelCombo;
	private JButton btnThemCombo;
	private JButton btnCapNhatCombo;
	private JButton btnXoaCombo;
	private JButton btnAnhCombo;
	private JTextField txtTimCombo;
	private JTextField txtSoLuong;
	private JTextField txtMaCombo;
	private JTextField txtTenCombo;
	private JTextField txtGiaCombo;
	private JTextField txtPathCombo;
	private JTable tableCombo;

	private TaiKhoan tk;

	private Runnable capNhatPhimUI;
	
	private Form_ChonCombo formCombo; 
	private JMenuBar menuBar;
	private JPanel userPanel;

	public Form_GiaoDienChinh(LoginListener listener) {
		this.listener = listener;
		setTitle("Hệ thống bán vé xem phim");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// KHAI BÁO DANH SÁCH NHÂN VIÊN | DANH SÁCH PHIM | DANH SÁCH COMBO CHO PHẦN MENU
		dsNV = new DanhSachNhanVien();
		dsPhim = new DanhSachPhim();
		dsCombo = new DanhSachCombo();

		JPanel pContainer = new JPanel(new BorderLayout());
		JPanel pNorth = new JPanel(new BorderLayout());
		pContainer.add(pNorth, BorderLayout.NORTH);
		pCenter = new JPanel(new BorderLayout());
		// ======================== PHẦN HIỂN THỊ NGÀY GIỜ ( THƯ VIỆN JDATEPICKER )
		// ========================
		centerCardLayout = new CardLayout();
		contentPanel = new JPanel(centerCardLayout);

		pNorth.setBackground(primary);
		pNorth.setPreferredSize(new Dimension(0, 45));

		lblTime = new JLabel(new ImageIcon(getClass().getResource("/Image/timetable.png")));
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Roboto", Font.BOLD, 16));
		lblTime.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
		Timer timeTimer = new Timer(1000, e -> {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss - EEEE, dd/MM/yyyy", new Locale("vi", "VN"));
			lblTime.setText(sdf.format(new Date()));
		});
		timeTimer.start();

		JLabel lblTitle = new JLabel("HỆ THỐNG BÁN VÉ XEM PHIM", SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Unbounded", Font.BOLD, 18));

		pNorth.add(lblTime, BorderLayout.WEST);
		pNorth.add(lblTitle, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane(pCenter);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(pContainer, BorderLayout.NORTH);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
// ============================ PHẦN MENU BAR ============================
		menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(0, 50));
		menuBar.setOpaque(true);
		menuBar.setBackground(accent);
		menuBar.setFont(new Font("Roboto", Font.BOLD, 16));
		pContainer.add(menuBar, BorderLayout.CENTER);

		menuBar.add(Box.createHorizontalStrut(35));
		JMenu menuTrangChu = new JMenu("Trang Chủ\r\n");
		menuTrangChu.setFont(new Font("Roboto", Font.BOLD, 14));
		menuTrangChu.setMargin(getInsets());
		menuBar.add(menuTrangChu);
		menuBar.add(Box.createHorizontalStrut(55));

		selectedMenu = menuTrangChu;
		selectedMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		selectedMenu.setForeground(Color.WHITE);

		JMenu menuNhanVien = new JMenu("Nhân Viên");
		menuNhanVien.setFont(new Font("Roboto", Font.BOLD, 14));
		menuBar.add(menuNhanVien);
		menuBar.add(Box.createHorizontalStrut(55));

		itemDsNV = new JMenuItem("Danh sách nhân viên\r\n");
		itemDsNV.setFont(new Font("Roboto", Font.PLAIN, 12));
		menuNhanVien.add(itemDsNV);

		JMenu menuPhim = new JMenu("Phim");
		menuPhim.setFont(new Font("Roboto", Font.BOLD, 14));
		menuBar.add(menuPhim);
		menuBar.add(Box.createHorizontalStrut(55));

		itemDsP = new JMenuItem("Danh sách phim");
		itemDsP.setFont(new Font("Roboto", Font.PLAIN, 12));
		menuPhim.add(itemDsP);

		JSeparator separator = new JSeparator();
		menuPhim.add(separator);

		itemPDC = new JMenuItem("Phim đang chiếu");
		itemPDC.setFont(new Font("Roboto", Font.PLAIN, 12));
		menuPhim.add(itemPDC);

		itemPSR = new JMenuItem("Phim sắp ra mắt");
		itemPSR.setFont(new Font("Roboto", Font.PLAIN, 12));
		menuPhim.add(itemPSR);

		JMenu menuCombo = new JMenu("Combo Đồ Ăn");
		menuCombo.setFont(new Font("Roboto", Font.BOLD, 14));
		menuBar.add(menuCombo);
		menuBar.add(Box.createHorizontalStrut(25));

		itemCombo = new JMenuItem("Danh sách combo");
		itemCombo.setFont(new Font("Roboto", Font.PLAIN, 12));
		menuCombo.add(itemCombo);
		menuBar.add(Box.createHorizontalStrut(15));

		menuTrangChu.setForeground(Color.WHITE);
		menuNhanVien.setForeground(Color.WHITE);
		menuCombo.setForeground(Color.WHITE);
		menuPhim.setForeground(Color.WHITE);

		menuBar.add(Box.createHorizontalGlue());
// ============================ PHẦN ĐĂNG NHẬP / ĐĂNG KÝ ============================

		userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
		userPanel.setOpaque(false);
		menuBar.add(userPanel);

		updateUserPanel();

// ======================== PHẦN SLIDE ẢNH ========================
		cardLayout = new CardLayout();
		slidePanel = new JPanel(cardLayout);
		loadSlides();

		pCenter.add(slidePanel, BorderLayout.NORTH);

		startSlideShowTimer();

//	======================== TÌM KIẾM ========================
		JPanel pSearchAndTitle = new JPanel();
		pSearchAndTitle.setLayout(new BoxLayout(pSearchAndTitle, BoxLayout.Y_AXIS));
		pSearchAndTitle.setBackground(primary);

		JLabel centerLabel = new JLabel("Bạn muốn xem phim gì?", JLabel.CENTER);
		centerLabel.setFont(new Font("Unbounded", Font.BOLD, 30));
		centerLabel.setForeground(Color.WHITE);
		centerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

		pSearchAndTitle.add(centerLabel);

		JPanel pSearchFieldContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pSearchFieldContainer.setBackground(primary);

		txtSearch = new JTextField(50);
		txtSearch.setPreferredSize(new Dimension(600, 45));
		txtSearch.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtSearch.setForeground(Color.WHITE);
		txtSearch.setBackground(new Color(90, 90, 110));
		txtSearch.setCaretColor(Color.WHITE);
		txtSearch.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		txtSearch.setText("  Tìm kiếm...");

		pSearchFieldContainer.add(txtSearch);

		pSearchAndTitle.add(pSearchFieldContainer);

// =========================== PHẦN HIỂN THỊ PHIM ===========================
		JPanel pHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
		pHeader.setBackground(primary);

		JButton btnTatCa = new JButton("TẤT CẢ");
		JButton btnDangChieu = new JButton("ĐANG CHIẾU");
		JButton btnSapRaMat = new JButton("SẮP RA MẮT");

		JButton[] buttons = { btnTatCa, btnDangChieu, btnSapRaMat };

		for (JButton btn : buttons) {
			btn.setFont(new Font("Unbounded", Font.BOLD, 20));
			btn.setForeground(Color.WHITE);
			btn.setBackground(primary);
			btn.setFocusPainted(false);
			btn.setBorderPainted(false);
			btn.setOpaque(true);
			btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		pMovieRow = new JPanel(new GridLayout(5, 4, 20, 20));
		pMovieRow.setBackground(primary);
		pMovieRow.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));

// HÀM KHI BẤM VÀO TÙY CHỌN ĐANG CHIẾU | SẮP RA MẮT | TẤT CẢ
		capNhatPhimUI = () -> {
			pMovieRow.removeAll();

			List<Phim> danhSachPhimHienThi = dsPhim.getDsPhim();
			String luaChonHienTai = "";

			if (btnDangChieu.getBackground() == Color.WHITE)
				luaChonHienTai = "ĐANG CHIẾU";
			else if (btnSapRaMat.getBackground() == Color.WHITE)
				luaChonHienTai = "SẮP RA MẮT";
			else
				luaChonHienTai = "TẤT CẢ";

			for (Phim phimHienThi : danhSachPhimHienThi) {
				boolean hienThi = switch (luaChonHienTai) {
				case "ĐANG CHIẾU" -> phimHienThi.isTrangThai();
				case "SẮP RA MẮT" -> !phimHienThi.isTrangThai();
				default -> true;
				};

				if (hienThi) {
					PhongChieu mockPhongChieu = new PhongChieu("P000", true, null);
					SuatChieu mockSuatChieu = new SuatChieu("SC_MOCK_" + phimHienThi.getMaPhim(),
							java.time.LocalDateTime.now(), phimHienThi, mockPhongChieu);

					MoviePosterPanel moviePanel = new MoviePosterPanel(mockSuatChieu);
					moviePanel.addMouseListener(this);
					pMovieRow.add(moviePanel);
				}
			}
// SAU KHI CẬP NHẬT THÌ RENDER LẠI GIAO DIỆN
			pMovieRow.revalidate();
			pMovieRow.repaint();
		};

		ActionListener changeTab = e -> {
			for (JButton btn : buttons) {
				btn.setBackground(primary);
				btn.setForeground(Color.WHITE);
			}

			JButton clicked = (JButton) e.getSource();
			clicked.setBackground(Color.WHITE);
			clicked.setForeground(primary);

			capNhatPhimUI.run();
		};

		for (JButton btn : buttons) {
			btn.addActionListener(changeTab);
		}

		btnTatCa.setBackground(Color.WHITE);
		btnTatCa.setForeground(primary);
		capNhatPhimUI.run();

		pHeader.add(btnTatCa);
		pHeader.add(btnDangChieu);
		pHeader.add(btnSapRaMat);

		JPanel pMoviesSection = new JPanel(new BorderLayout());
		pMoviesSection.setBackground(primary);
		pMoviesSection.add(pHeader, BorderLayout.NORTH);
		pMoviesSection.add(pMovieRow, BorderLayout.CENTER);

// =========================== TRANG CHỦ ===========================
		JPanel pTrangChu = new JPanel(new BorderLayout());
		pTrangChu.add(slidePanel, BorderLayout.NORTH);
		pTrangChu.add(pSearchAndTitle, BorderLayout.CENTER);
		pTrangChu.add(pMoviesSection, BorderLayout.SOUTH);

		contentPanel.add(pTrangChu, "TrangChu");
// =========================== HIỂN THỊ CÁC DANH SÁCH ===========================
		quanLyNhanVienPanel = createQuanLyNhanVienPanel();
		quanLyPhimPanel = createQuanLyPhimPanel();
		quanLyComboPanel = createQuanLyComboPanel();

		contentPanel.add(quanLyNhanVienPanel, "QuanLyNhanVien");
		contentPanel.add(quanLyPhimPanel, "QuanLyPhim");
		contentPanel.add(quanLyComboPanel, "QuanLyCombo");

		pCenter.add(contentPanel, BorderLayout.CENTER);

		// MOUSE LISTENER
		menuTrangChu.addMouseListener(this);
		menuNhanVien.addMouseListener(this);
		menuPhim.addMouseListener(this);
		menuCombo.addMouseListener(this);
		menuTrangChu.addMouseListener(this);
		table.addMouseListener(this);

		// ACTION LISTENER
		itemDsNV.addActionListener(this);
		itemDsP.addActionListener(this);
		itemCombo.addActionListener(this);
		btnTimNhanVien.addActionListener(this);
		btnXoaNhanVien.addActionListener(this);
		btnCapNhatNhanVien.addActionListener(this);
		btnChonAnh.addActionListener(this);
		btnThemPhim.addActionListener(this);
		btnXoaPhim.addActionListener(this);
		btnCapNhatPhim.addActionListener(this);
		btnTimPhim.addActionListener(this);
		btnThemCombo.addActionListener(this);
		btnXoaCombo.addActionListener(this);
		btnCapNhatCombo.addActionListener(this);
		tablePhim.addMouseListener(this);
		tableCombo.addMouseListener(this);

		// KEY LISTENER
		txtSearch.addKeyListener(this);

		// FOCUS LISTENER
		txtSearch.addFocusListener(this);

// KHẮC PHỤC LỖI GIÚP HIỂN THỊ LÊN ĐẦU TRANG
		SwingUtilities.invokeLater(() -> {
			scrollPane.getVerticalScrollBar().setValue(0);
		});

	}

// ======================= VẼ LẠI PANEL USER ĐĂNG NHẬP / ĐĂNG KÝ =======================

	private void updateUserPanel() {
		// Xóa mọi thứ cũ trong panel
		userPanel.removeAll();

		if (isLoggedIn) {
			JLabel userIcon = new JLabel(new ImageIcon(getClass().getResource("/Image/user.png")));
			// SỬA DÙNG 'loggedInUsername' thay vì 'tk' (vì tk bị null)
			JLabel userName = new JLabel("Xin chào " + loggedInUsername);
			userName.setFont(new Font("Roboto", Font.BOLD, 14));
			userName.setForeground(Color.WHITE);

			userPanel.add(userIcon);
			userPanel.add(userName);

			// Thêm nút Đăng xuất
			JButton btnLogout = new JButton("Đăng xuất");
			btnLogout.setFont(new Font("Roboto", Font.BOLD, 13));
			btnLogout.setFocusPainted(false);
			btnLogout.setBackground(Color.GRAY);
			btnLogout.setForeground(Color.WHITE);
			btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnLogout.addActionListener(e -> {
				// Xử lý đăng xuất
				this.isLoggedIn = false;
				this.loggedInUsername = "";
				updateUserPanel(); // Vẽ lại panel (hiện nút Đăng nhập)

				// Tùy chọn: gọi listener để quay về màn hình đăng nhập
				// if(listener != null) {
				// listener.onShowLogin();
				// this.dispose();
				// }
			});
			userPanel.add(btnLogout);

		} else {
			JLabel userIcon = new JLabel(new ImageIcon(getClass().getResource("/Image/user.png")));
			btnLogin = new JButton("Đăng nhập"); // Khởi tạo lại
			btnRegister = new JButton("Đăng ký"); // Khởi tạo lại

			Font btnFont = new Font("Roboto", Font.BOLD, 13);
			btnLogin.setFont(btnFont);
			btnRegister.setFont(btnFont);
			btnLogin.setFocusPainted(false);
			btnRegister.setFocusPainted(false);

			btnLogin.setBackground(Color.yellow);
			btnLogin.setForeground(Color.black);
			btnRegister.setBackground(Color.yellow);
			btnRegister.setForeground(Color.black);

			btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			// THÊM LISTENER TẠI ĐÂY (quan trọng)
			btnLogin.addActionListener(this);
			btnRegister.addActionListener(this);

			userPanel.add(userIcon);
			userPanel.add(btnLogin);
			userPanel.add(btnRegister);
		}

		// Yêu cầu panel vẽ lại
		userPanel.revalidate();
		userPanel.repaint();
	}

// ======================= LoginSuccess =======================

	public void setLoginSuccess(String username) {
	    this.isLoggedIn = true;
	    this.loggedInUsername = username;
	    
	    updateUserPanel();
	}
// ======================= DANH SÁCH NHÂN VIÊN =======================
	private JPanel createQuanLyNhanVienPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(primary);

		JPanel pNorthNV = new JPanel();
		pNorthNV.setBackground(primary);

		String[] colName = { "Mã", "Họ và Tên", "Email", "Số điện thoại" };
		model = new DefaultTableModel(colName, 0);
		table = new JTable(model);
		JScrollPane srollTable = new JScrollPane(table);
		srollTable.setPreferredSize(new Dimension(500, 0));

		Object[] rowData = { "NV001", "Nguyễn Văn A", "nva@example.com", "0901234567" };
		model.addRow(rowData);
		Object[] rowData2 = { "NV002", "Nguyễn Thị C", "ntc@example.com", "09043234137" };
		model.addRow(rowData2);

		JPanel pCenterContainer = new JPanel(new BorderLayout());
		pCenterContainer.setBackground(primary);

		JPanel pCenter_Contain = new JPanel(new BorderLayout());
		pCenter_Contain.setBackground(primary);

		Box b, b1, b2, b3, b4;
		b = Box.createVerticalBox();
		b.setBackground(primary);
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();

		JLabel lblMa = new JLabel("Mã");
		lblMa.setFont(new Font("Roboto", Font.PLAIN, 14));
		b1.add(lblMa);

		Component rigidArea = Box.createRigidArea(new Dimension(50, 20));
		b1.add(rigidArea);

		txtMa = new JTextField();
		txtMa.setFont(new Font("Roboto", Font.PLAIN, 16));
		b1.add(txtMa);
		txtMa.setColumns(10);

		JLabel lblTen = new JLabel("Họ và Tên\r\n");
		lblTen.setFont(new Font("Roboto", Font.PLAIN, 14));
		b2.add(lblTen);

		Component rigidArea_1_1 = Box.createRigidArea(new Dimension(10, 20));
		b2.add(rigidArea_1_1);

		txtTen = new JTextField();
		txtTen.setFont(new Font("Roboto", Font.PLAIN, 16));
		b2.add(txtTen);
		txtTen.setColumns(10);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Roboto", Font.PLAIN, 14));
		b3.add(lblEmail);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(40, 20));
		b3.add(rigidArea_1);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Roboto", Font.PLAIN, 16));
		b3.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblSdt = new JLabel("SĐT");
		lblSdt.setFont(new Font("Roboto", Font.PLAIN, 14));
		b4.add(lblSdt);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(50, 20));
		b4.add(rigidArea_2);

		txtSdt = new JTextField();
		txtSdt.setFont(new Font("Roboto", Font.PLAIN, 16));
		b4.add(txtSdt);
		txtSdt.setColumns(10);

		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(20, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new Dimension(20, 20)));
		b.add(b3);
		b.add(Box.createRigidArea(new Dimension(20, 20)));
		b.add(b4);
		b.add(Box.createRigidArea(new Dimension(20, 20)));

		pCenterContainer.add(Box.createRigidArea(new Dimension(100, 100)), BorderLayout.NORTH);
		pCenterContainer.add(Box.createRigidArea(new Dimension(100, 100)), BorderLayout.SOUTH);
		pCenterContainer.add(Box.createRigidArea(new Dimension(100, 100)), BorderLayout.WEST);
		pCenterContainer.add(Box.createRigidArea(new Dimension(100, 100)), BorderLayout.EAST);
		pCenter_Contain.add(b, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBackground(primary);
		b.add(panel);

		btnXoaNhanVien = new JButton("Xóa");
		btnXoaNhanVien.setBackground(new Color(220, 53, 69));
		btnXoaNhanVien.setForeground(Color.white);
		btnXoaNhanVien.setFont(new Font("Roboto", Font.PLAIN, 14));
		panel.add(btnXoaNhanVien);

		btnCapNhatNhanVien = new JButton("Cập nhật");
		btnCapNhatNhanVien.setBackground(new Color(255, 193, 7));
		btnCapNhatNhanVien.setForeground(Color.white);
		btnCapNhatNhanVien.setFont(new Font("Roboto", Font.PLAIN, 14));
		panel.add(btnCapNhatNhanVien);
		pCenterContainer.add(pCenter_Contain, BorderLayout.CENTER);

		JLabel lblTim = new JLabel("Nhập tên nhân viên");
		lblTim.setFont(new Font("Roboto", Font.PLAIN, 14));
		pNorthNV.add(lblTim);

		txtTim = new JTextField();
		txtTim.setFont(new Font("Roboto", Font.PLAIN, 14));
		pNorthNV.add(txtTim);
		txtTim.setColumns(20);

		btnTimNhanVien = new JButton("Tìm");
		btnTimNhanVien.setBackground(accent);
		btnTimNhanVien.setForeground(Color.white);
		btnTimNhanVien.setFont(new Font("Roboto", Font.PLAIN, 14));
		pNorthNV.add(btnTimNhanVien);

		mainPanel.add(pNorthNV, BorderLayout.NORTH);
		mainPanel.add(srollTable, BorderLayout.WEST);
		mainPanel.add(pCenterContainer, BorderLayout.CENTER);

		setLabelColor(mainPanel, Color.WHITE);

		return mainPanel;
	}

// ======================= DANH SÁCH PHIM =======================
	private JPanel createQuanLyPhimPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(primary);

		JPanel pNorthPhim = new JPanel();
		pNorthPhim.setBackground(primary);

		String[] colName = { "Mã", "Tên Phim", "Thể Loại", "Thời Lượng", "Đạo Diễn", "Trạng Thái", "Mô Tả",
				"File ảnh" };
		modelPhim = new DefaultTableModel(colName, 0);
		for (Phim phim : dsPhim.getDsPhim()) {
			modelPhim.addRow(new Object[] { phim.getMaPhim(), phim.getTenPhim(), phim.getTheLoai(), phim.getThoiLuong(),
					phim.getDaoDien(), phim.isTrangThai() ? "Đang chiếu" : "Sắp ra mắt", phim.getMoTa(),
					phim.getDuongDanAnh() });
		}
		tablePhim = new JTable(modelPhim);
		JScrollPane srollTable = new JScrollPane(tablePhim);
		srollTable.setPreferredSize(new Dimension(500, 0));

		JPanel pCenterContainer = new JPanel(new BorderLayout());
		pCenterContainer.setBackground(primary);

		JPanel pCenter_Contain = new JPanel(new BorderLayout());
		pCenter_Contain.setBackground(primary);

		Box b, b1, b2, b3, b4, b5, b6, b7, b8;
		b = Box.createVerticalBox();
		b.setBackground(primary);
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		b5 = Box.createHorizontalBox();
		b6 = Box.createHorizontalBox();
		b7 = Box.createHorizontalBox();
		b8 = Box.createHorizontalBox();

		JLabel lblMaPhim = new JLabel("Mã");
		lblMaPhim.setFont(new Font("Roboto", Font.PLAIN, 14));
		b1.add(lblMaPhim);
		Component rigidArea = Box.createRigidArea(new Dimension(76, 20));
		b1.add(rigidArea);

		txtMaPhim = new JTextField();
		txtMaPhim.setFont(new Font("Roboto", Font.PLAIN, 16));
		b1.add(txtMaPhim);
		txtMaPhim.setColumns(10);

		JLabel lblTenPhim = new JLabel("Tên Phim\r\n");
		lblTenPhim.setFont(new Font("Roboto", Font.PLAIN, 14));
		b2.add(lblTenPhim);
		Component rigidArea_1_1 = Box.createRigidArea(new Dimension(35, 20));
		b2.add(rigidArea_1_1);

		txtTenPhim = new JTextField();
		txtTenPhim.setFont(new Font("Roboto", Font.PLAIN, 16));
		b2.add(txtTenPhim);
		txtTenPhim.setColumns(10);

		JLabel lblTheLoai = new JLabel("Thể Loại");
		lblTheLoai.setFont(new Font("Roboto", Font.PLAIN, 14));
		b3.add(lblTheLoai);
		Component rigidArea_1 = Box.createRigidArea(new Dimension(43, 20));
		b3.add(rigidArea_1);

		txtTheLoai = new JTextField();
		txtTheLoai.setFont(new Font("Roboto", Font.PLAIN, 16));
		b3.add(txtTheLoai);
		txtTheLoai.setColumns(10);

		JLabel lblThoiLuong = new JLabel("Thời Lượng");
		lblThoiLuong.setFont(new Font("Roboto", Font.PLAIN, 14));
		b4.add(lblThoiLuong);
		Component rigidArea_2 = Box.createRigidArea(new Dimension(30, 20));
		b4.add(rigidArea_2);

		txtThoiLuong = new JTextField();
		txtThoiLuong.setFont(new Font("Roboto", Font.PLAIN, 16));
		b4.add(txtThoiLuong);
		txtThoiLuong.setColumns(10);

		JLabel lblDaoDien = new JLabel("Đạo Diễn");
		lblDaoDien.setFont(new Font("Roboto", Font.PLAIN, 14));
		b5.add(lblDaoDien);
		Component rigidArea_2_1 = Box.createRigidArea(new Dimension(47, 20));
		b5.add(rigidArea_2_1);

		txtDaoDien = new JTextField();
		txtDaoDien.setFont(new Font("Roboto", Font.PLAIN, 16));
		b5.add(txtDaoDien);
		txtDaoDien.setColumns(10);

		JLabel lblTrangThai = new JLabel("Trạng Thái");
		lblTrangThai.setFont(new Font("Roboto", Font.PLAIN, 14));
		b6.add(lblTrangThai);
		Component rigidArea_2_2 = Box.createRigidArea(new Dimension(38, 20));
		b6.add(rigidArea_2_2);

		txtTrangThai = new JTextField();
		txtTrangThai.setFont(new Font("Roboto", Font.PLAIN, 16));
		b6.add(txtTrangThai);
		txtTrangThai.setColumns(10);

		JLabel lblMoTa = new JLabel("Mô Tả");
		lblMoTa.setFont(new Font("Roboto", Font.PLAIN, 14));
		b7.add(lblMoTa);
		Component rigidArea_3 = Box.createRigidArea(new Dimension(68, 20));
		b7.add(rigidArea_3);

		txtMoTa = new JTextArea();
		txtMoTa.setFont(new Font("Roboto", Font.PLAIN, 16));
		b7.add(txtMoTa);

		JLabel lblPath = new JLabel("Ảnh");
		lblPath.setFont(new Font("Roboto", Font.PLAIN, 14));
		b8.add(lblPath);
		Component rigidArea_4 = Box.createRigidArea(new Dimension(75, 20));
		b8.add(rigidArea_4);

		txtPathAnh = new JTextField();
		txtPathAnh.setFont(new Font("Roboto", Font.PLAIN, 16));
		b8.add(txtPathAnh);

		btnChonAnh = new JButton("Chọn Ảnh");
		btnChonAnh.setBackground(accent);
		btnChonAnh.setForeground(Color.white);
		btnChonAnh.setFont(new Font("Roboto", Font.PLAIN, 14));
		b8.add(Box.createRigidArea(new Dimension(10, 0)));
		b8.add(btnChonAnh);

		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(20, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new Dimension(20, 20)));
		b.add(b3);
		b.add(Box.createRigidArea(new Dimension(20, 20)));
		b.add(b4);
		b.add(Box.createRigidArea(new Dimension(20, 20)));
		b.add(b5);
		b.add(Box.createRigidArea(new Dimension(20, 20)));
		b.add(b6);
		b.add(Box.createRigidArea(new Dimension(20, 20)));
		b.add(b7);
		b.add(Box.createRigidArea(new Dimension(20, 20)));
		b.add(b8);
		b.add(Box.createRigidArea(new Dimension(20, 20)));

		pCenterContainer.add(Box.createRigidArea(new Dimension(100, 100)), BorderLayout.NORTH);
		pCenterContainer.add(Box.createRigidArea(new Dimension(100, 100)), BorderLayout.SOUTH);
		pCenterContainer.add(Box.createRigidArea(new Dimension(100, 100)), BorderLayout.WEST);
		pCenterContainer.add(Box.createRigidArea(new Dimension(100, 100)), BorderLayout.EAST);
		pCenter_Contain.add(b, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBackground(primary);
		b.add(panel);

		btnThemPhim = new JButton("Thêm");
		btnThemPhim.setBackground(new Color(40, 167, 69));
		btnThemPhim.setForeground(Color.white);
		btnThemPhim.setFont(new Font("Roboto", Font.PLAIN, 14));
		panel.add(btnThemPhim);

		btnXoaPhim = new JButton("Xóa");
		btnXoaPhim.setBackground(new Color(220, 53, 69));
		btnXoaPhim.setForeground(Color.white);
		btnXoaPhim.setFont(new Font("Roboto", Font.PLAIN, 14));
		panel.add(btnXoaPhim);

		btnCapNhatPhim = new JButton("Cập nhật");
		btnCapNhatPhim.setBackground(new Color(255, 193, 7));
		btnCapNhatPhim.setForeground(Color.white);
		btnCapNhatPhim.setFont(new Font("Roboto", Font.PLAIN, 14));
		panel.add(btnCapNhatPhim);
		pCenterContainer.add(pCenter_Contain, BorderLayout.CENTER);

		JLabel lblTimPhim = new JLabel("Nhập tên phim");
		lblTimPhim.setFont(new Font("Roboto", Font.PLAIN, 14));
		pNorthPhim.add(lblTimPhim);

		txtTimPhim = new JTextField();
		txtTimPhim.setFont(new Font("Roboto", Font.PLAIN, 14));
		pNorthPhim.add(txtTimPhim);
		txtTimPhim.setColumns(20);

		btnTimPhim = new JButton("Tìm");
		btnTimPhim.setBackground(accent);
		btnTimPhim.setForeground(Color.white);
		btnTimPhim.setFont(new Font("Roboto", Font.PLAIN, 14));
		pNorthPhim.add(btnTimPhim);

		mainPanel.add(pNorthPhim, BorderLayout.NORTH);
		mainPanel.add(srollTable, BorderLayout.CENTER);
		mainPanel.add(pCenterContainer, BorderLayout.EAST);

		setLabelColor(mainPanel, Color.WHITE);

		return mainPanel;
	}

// ======================= DANH SÁCH Combo =======================
	private JPanel createQuanLyComboPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(primary);
		JPanel pNorthCombo = new JPanel();
		pNorthCombo.setBackground(primary);
		JLabel lblTim = new JLabel("Nhập tên combo");
		lblTim.setFont(new Font("Roboto", Font.PLAIN, 14));
		pNorthCombo.add(lblTim);

		txtTimCombo = new JTextField();
		txtTimCombo.setFont(new Font("Roboto", Font.PLAIN, 14));
		txtTimCombo.setColumns(20);
		pNorthCombo.add(txtTimCombo);
		JButton btnTimCombo = new JButton("Tìm");
		btnTimCombo.setBackground(accent);
		btnTimCombo.setForeground(Color.white);
		btnTimCombo.setFont(new Font("Roboto", Font.PLAIN, 14));
		pNorthCombo.add(btnTimCombo);
		String[] colName = { "Mã Combo", "Tên Combo", "Giá", "Số lượng", "Đường dẫn ảnh" };
		modelCombo = new DefaultTableModel(colName, 0);
		tableCombo = new JTable(modelCombo);
		JScrollPane scrollTable = new JScrollPane(tableCombo);
		scrollTable.setPreferredSize(new Dimension(550, 0));
		for (ComboDoAn combo : dsCombo.getDsCombo()) {
			modelCombo.addRow(new Object[] { combo.getMaCombo(), combo.getTenCombo(), combo.getGia(),
					combo.getSoLuong(), combo.getPath() });
		}
		JPanel pCenterContainer = new JPanel(new BorderLayout());
		pCenterContainer.setBackground(primary);
		JPanel pCenter_Contain = new JPanel(new BorderLayout());
		pCenter_Contain.setBackground(primary);
		Box b, b1, b2, b3, b4, b5;
		b = Box.createVerticalBox();
		b.setBackground(primary);
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		b5 = Box.createHorizontalBox();
		JLabel lblMaCombo = new JLabel("Mã Combo");
		lblMaCombo.setFont(new Font("Roboto", Font.PLAIN, 14));
		b1.add(lblMaCombo);
		b1.add(Box.createRigidArea(new Dimension(30, 20)));

		txtMaCombo = new JTextField();
		txtMaCombo.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtMaCombo.setColumns(10);
		b1.add(txtMaCombo);
		JLabel lblTenCombo = new JLabel("Tên Combo");
		lblTenCombo.setFont(new Font("Roboto", Font.PLAIN, 14));
		b2.add(lblTenCombo);
		b2.add(Box.createRigidArea(new Dimension(22, 20)));

		txtTenCombo = new JTextField();
		txtTenCombo.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTenCombo.setColumns(10);
		b2.add(txtTenCombo);
		JLabel lblGiaCombo = new JLabel("Giá");
		lblGiaCombo.setFont(new Font("Roboto", Font.PLAIN, 14));
		b3.add(lblGiaCombo);
		b3.add(Box.createRigidArea(new Dimension(70, 20)));

		txtGiaCombo = new JTextField();
		txtGiaCombo.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtGiaCombo.setColumns(10);
		b3.add(txtGiaCombo);
		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("Roboto", Font.PLAIN, 14));
		b4.add(lblSoLuong);
		b4.add(Box.createRigidArea(new Dimension(40, 20)));

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtSoLuong.setColumns(10);
		b4.add(txtSoLuong);
		JLabel lblPath = new JLabel("Đường dẫn ảnh");
		lblPath.setFont(new Font("Roboto", Font.PLAIN, 14));
		b5.add(lblPath);
		btnAnhCombo = new JButton("Thêm ảnh");
		btnAnhCombo.setBackground(accent);
		btnAnhCombo.setForeground(Color.white);
		btnAnhCombo.setFont(new Font("Roboto", Font.PLAIN, 14));
		b5.add(Box.createRigidArea(new Dimension(5, 20)));

		txtPathCombo = new JTextField();
		txtPathCombo.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtPathCombo.setColumns(10);
		b5.add(txtPathCombo);
		b5.add(Box.createRigidArea(new Dimension(5, 20)));
		b5.add(btnAnhCombo);
		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(20, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new Dimension(20, 20)));
		b.add(b3);
		b.add(Box.createRigidArea(new Dimension(20, 20)));
		b.add(b4);
		b.add(Box.createRigidArea(new Dimension(20, 20)));
		b.add(b5);
		b.add(Box.createRigidArea(new Dimension(20, 20)));
		JPanel panelButton = new JPanel();
		panelButton.setBackground(primary);
		btnThemCombo = new JButton("Thêm");
		btnThemCombo.setBackground(new Color(40, 167, 69));
		btnThemCombo.setForeground(Color.white);
		btnThemCombo.setFont(new Font("Roboto", Font.PLAIN, 14));
		panelButton.add(btnThemCombo);
		btnCapNhatCombo = new JButton("Cập nhật");
		btnCapNhatCombo.setBackground(new Color(255, 193, 7));
		btnCapNhatCombo.setForeground(Color.white);
		btnCapNhatCombo.setFont(new Font("Roboto", Font.PLAIN, 14));
		panelButton.add(btnCapNhatCombo);
		btnXoaCombo = new JButton("Xóa");
		btnXoaCombo.setBackground(new Color(220, 53, 69));
		btnXoaCombo.setForeground(Color.white);
		btnXoaCombo.setFont(new Font("Roboto", Font.PLAIN, 14));
		panelButton.add(btnXoaCombo);
		b.add(panelButton);
		pCenter_Contain.add(b, BorderLayout.NORTH);
		pCenterContainer.add(Box.createRigidArea(new Dimension(100, 100)), BorderLayout.NORTH);
		pCenterContainer.add(Box.createRigidArea(new Dimension(100, 100)), BorderLayout.SOUTH);
		pCenterContainer.add(Box.createRigidArea(new Dimension(100, 100)), BorderLayout.WEST);
		pCenterContainer.add(Box.createRigidArea(new Dimension(100, 100)), BorderLayout.EAST);
		pCenterContainer.add(pCenter_Contain, BorderLayout.CENTER);
		mainPanel.add(pNorthCombo, BorderLayout.NORTH);
		mainPanel.add(scrollTable, BorderLayout.WEST);
		mainPanel.add(pCenterContainer, BorderLayout.CENTER);
		setLabelColor(mainPanel, Color.WHITE);
		return mainPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(table)) {
			int row = table.getSelectedRow();
			if (row != -1) {
				String maNV = model.getValueAt(row, 0).toString();
				String hoTen = model.getValueAt(row, 1).toString();
				String email = model.getValueAt(row, 2).toString();
				String sdt = model.getValueAt(row, 3).toString();

				txtMa.setText(maNV);
				txtTen.setText(hoTen);
				txtEmail.setText(email);
				txtSdt.setText(sdt);
			}
			return;
		} else if (o.equals(tableCombo)) {
			int row = tableCombo.getSelectedRow();
			if (row != -1) {
				String maCombo = modelCombo.getValueAt(row, 0).toString();
				String tenCombo = modelCombo.getValueAt(row, 1).toString();
				String giaStr = modelCombo.getValueAt(row, 2).toString();
				String soLuongStr = modelCombo.getValueAt(row, 3).toString();
				String path = modelCombo.getValueAt(row, 4).toString();

				txtMaCombo.setText(maCombo);
				txtTenCombo.setText(tenCombo);
				txtGiaCombo.setText(giaStr);
				txtSoLuong.setText(soLuongStr);
				txtPathCombo.setText(path);
			}
			return;
		} else if (o.equals(tablePhim)) {
			int row = tablePhim.getSelectedRow();
			if (row != -1) {
				String maPhim = modelPhim.getValueAt(row, 0).toString();
				String tenPhim = modelPhim.getValueAt(row, 1).toString();
				String theLoai = modelPhim.getValueAt(row, 2).toString();
				String thoiLuong = modelPhim.getValueAt(row, 3).toString();
				String daoDien = modelPhim.getValueAt(row, 4).toString();
				String trangThai = modelPhim.getValueAt(row, 5).toString();
				String moTa = modelPhim.getValueAt(row, 6).toString();
				String pathAnh = modelPhim.getValueAt(row, 7).toString();

				txtMaPhim.setText(maPhim);
				txtTenPhim.setText(tenPhim);
				txtTheLoai.setText(theLoai);
				txtThoiLuong.setText(thoiLuong);
				txtDaoDien.setText(daoDien);
				txtTrangThai.setText(trangThai);
				txtMoTa.setText(moTa);
				txtPathAnh.setText(pathAnh);
			}
			return;
		}

		if (o instanceof MoviePosterPanel) {
			MoviePosterPanel clickedPanel = (MoviePosterPanel) o;
			SuatChieu selectedSuatChieu = clickedPanel.getSuatChieu();

			if (selectedSuatChieu != null) {
				new Form_HienThiThongTinPhim(selectedSuatChieu).setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Thông tin suất chiếu không hợp lệ!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
			return;
		}

		JMenu clicked = (JMenu) e.getSource();

		if (clicked.getText().trim().equals("Trang Chủ")) {
			centerCardLayout.show(contentPanel, "TrangChu");
		}

		if (selectedMenu != null) {
			selectedMenu.setBorder(null);
			selectedMenu.setForeground(Color.white);
		}

		clicked.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		clicked.setForeground(Color.WHITE);
		selectedMenu = clicked;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (e.getSource() == txtSearch) {
				String tuKhoa = txtSearch.getText().trim();
				List<Phim> ketQua = dsPhim.timPhimTheoTenGanDung(tuKhoa);

				if (ketQua.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Không tìm thấy phim nào có tên chứa: " + tuKhoa);
				} else {
					pMovieRow.removeAll();

					PhongChieu mockPhongChieu = new PhongChieu("P000", true, null);

					for (Phim p : ketQua) {
						SuatChieu mockSuatChieu = new SuatChieu("SC_SEARCH_" + p.getMaPhim(),
								java.time.LocalDateTime.now(), p, mockPhongChieu);

						MoviePosterPanel moviePanel = new MoviePosterPanel(mockSuatChieu);
						moviePanel.addMouseListener(this);
						pMovieRow.add(moviePanel);
					}

					pMovieRow.revalidate();
					pMovieRow.repaint();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
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
	public void focusGained(FocusEvent e) {
		if (txtSearch.getText().equals("  Tìm kiếm...")) {
			txtSearch.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (txtSearch.getText().isEmpty()) {
			txtSearch.setText("  Tìm kiếm...");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLogin)) {
	        if(listener != null) {
	            // Yêu cầu controller hiển thị Form_DangNhap
	            listener.onShowLogin(); 
	            this.dispose(); // Đóng GiaoDienChinh (nếu cần)
	        }

	    } else if (o.equals(btnRegister)) {
	        if(listener != null) {
	            // Yêu cầu controller hiển thị Form_DangKy
	            listener.onRegisterRequest();
	            this.dispose(); 
	        }
	    } else if (o.equals(itemDsNV)) {
			centerCardLayout.show(contentPanel, "QuanLyNhanVien");
		} else if (o.equals(itemDsP)) {
			centerCardLayout.show(contentPanel, "QuanLyPhim");
		} else if (o.equals(itemCombo)) {
			centerCardLayout.show(contentPanel, "QuanLyCombo");
		} else if (o.equals(btnTimNhanVien)) {
			timTheoTenNhanVien();
		} else if (o.equals(btnXoaNhanVien)) {
			xoaNhanVien();
		} else if (o.equals(btnCapNhatNhanVien)) {
			capNhatNhanVien();
		} else if (o.equals(btnChonAnh)) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Chọn ảnh poster phim");

			fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
					"Tệp ảnh (*.jpg, *.png, *.gif)", "jpg", "png", "gif"));

			int result = fileChooser.showOpenDialog(this);

			if (result == JFileChooser.APPROVE_OPTION) {
				String selectedPath = fileChooser.getSelectedFile().getAbsolutePath();

				txtPathAnh.setText(selectedPath);
			}
		} else if (o.equals(btnThemPhim)) {
			themPhim();
		} else if (o.equals(btnXoaPhim)) {
			xoaPhim();
		} else if (o.equals(btnCapNhatPhim)) {
			capNhatPhim();
		} else if (o.equals(btnTimPhim)) {
			timPhimTheoTen();
		} else if (o.equals(btnThemCombo)) {
			themCombo();
		} else if (o.equals(btnXoaCombo)) {
			xoaCombo();
		} else if (o.equals(btnAnhCombo)) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Chọn ảnh cho combo");

			fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
					"Tệp ảnh (*.jpg, *.png, *.gif)", "jpg", "png", "gif"));

			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				String selectedPath = fileChooser.getSelectedFile().getAbsolutePath();

				txtPathCombo.setText(selectedPath);
			}
		} else if (o.equals(btnCapNhatCombo)) {
			capNhatCombo();
		}
	}

	public boolean kiemTraDuLieuPhim() {
	    if (txtMaPhim.getText().trim().isEmpty() || txtTenPhim.getText().trim().isEmpty()
	            || txtTheLoai.getText().trim().isEmpty() || txtThoiLuong.getText().trim().isEmpty()
	            || txtDaoDien.getText().trim().isEmpty() || txtTrangThai.getText().trim().isEmpty()
	            || txtMoTa.getText().trim().isEmpty() || txtPathAnh.getText().trim().isEmpty()) { 
	        
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
	        return false;
	    }

	    try {
	        Integer.parseInt(txtThoiLuong.getText().trim());
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "Thời lượng phải là số!");
	        return false;
	    }
	    return true;
	}

	private boolean kiemTraDuLieuCombo() {
		if (txtMaCombo.getText().trim().isEmpty() || txtTenCombo.getText().trim().isEmpty()
				|| txtGiaCombo.getText().trim().isEmpty() || txtSoLuong.getText().trim().isEmpty()) {

			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin combo!");
			return false;
		}

		try {
			Double.parseDouble(txtGiaCombo.getText().trim());
			Integer.parseInt(txtSoLuong.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Giá và số lượng phải là số hợp lệ!");
			return false;
		}

		return true;
	}

	public Phim chuyenDuLieuVaoPhim() {
	    String ma = txtMaPhim.getText().trim();
	    String ten = txtTenPhim.getText().trim();
	    Phim.TheLoai theLoai = Phim.TheLoai.fromString(txtTheLoai.getText().trim());
	    
	    if (theLoai == null) {
	        JOptionPane.showMessageDialog(this, "Thể loại không hợp lệ! (Ví dụ: Tình Cảm, Kinh Dị, Hành Động...)");
	        return null;
	    }
	    
	    int thoiLuong = Integer.parseInt(txtThoiLuong.getText().trim());
	    String daoDien = txtDaoDien.getText().trim();
	    boolean trangThai = txtTrangThai.getText().trim().equalsIgnoreCase("true")
	            || txtTrangThai.getText().trim().equalsIgnoreCase("đang chiếu");
	    String moTa = txtMoTa.getText().trim();
	    String path = txtPathAnh.getText().trim();

	    return new Phim(ma, ten, theLoai, thoiLuong, daoDien, trangThai, moTa, path);
	}

	private ComboDoAn chuyenDuLieuVaoCombo() {
		String ma = txtMaCombo.getText().trim();
		String ten = txtTenCombo.getText().trim();
		double gia = 0;
		int soLuong = 0;
		String path = txtPathCombo.getText().trim();

		try {
			gia = Double.parseDouble(txtGiaCombo.getText().trim());
			soLuong = Integer.parseInt(txtSoLuong.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Giá và số lượng phải là số hợp lệ!");
			return null;
		}

		return new ComboDoAn(ma, ten, gia, path, soLuong);
	}

	private void themCombo() {
		if (!kiemTraDuLieuCombo())
			return;

		ComboDoAn c = chuyenDuLieuVaoCombo();
		if (c == null)
			return;

		for (ComboDoAn cb : dsCombo.getDsCombo()) {
			if (cb.getMaCombo().equalsIgnoreCase(c.getMaCombo())) {
				JOptionPane.showMessageDialog(this, "Mã combo đã tồn tại!");
				return;
			}
		}

		if (dsCombo.themCombo(c)) {
			modelCombo
					.addRow(new Object[] { c.getMaCombo(), c.getTenCombo(), c.getGia(), c.getSoLuong(), c.getPath() });
			JOptionPane.showMessageDialog(this, "Thêm combo thành công!");
			if (formCombo != null)
				formCombo.reloadComboList();
		} else {
			JOptionPane.showMessageDialog(this, "Không thể thêm combo!");
		}
	}

	private void themPhim() {
	    if (!kiemTraDuLieuPhim())
	        return;

	    Phim p = chuyenDuLieuVaoPhim();
	    if (p == null) {
	        return; 
	    }

	    if (dsPhim.timPhimTheoMa(p.getMaPhim()) != null) {
	        JOptionPane.showMessageDialog(this, "Mã phim đã tồn tại!");
	        return;
	    }

	    if (dsPhim.themPhim(p)) {
	        modelPhim.addRow(new Object[] { 
	                p.getMaPhim(), p.getTenPhim(), p.getTheLoai(),
	                p.getThoiLuong(), p.getDaoDien(), 
	                p.isTrangThai() ? "Đang chiếu" : "Sắp ra mắt", 
	                p.getMoTa(), 
	                p.getDuongDanAnh() 
	        });
	        
	        JOptionPane.showMessageDialog(this, "Thêm phim thành công!");
	        
	        capNhatPhimUI.run();
	        
	    } else {
	        JOptionPane.showMessageDialog(this, "Thêm phim vào CSDL thất bại!");
	    }
	}

	private void xoaPhim() {
	    int row = tablePhim.getSelectedRow();
	    if (row == -1) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn phim cần xóa!");
	        return;
	    }
	    int choice = JOptionPane.showConfirmDialog(this, 
	            "Bạn có chắc chắn muốn xóa phim này?", 
	            "Xác nhận xóa",
	            JOptionPane.YES_NO_OPTION);

	    if (choice == JOptionPane.YES_OPTION) {
	        String ma = modelPhim.getValueAt(row, 0).toString();

	        Phim phimCanXoa = dsPhim.timPhimTheoMa(ma); 

	        if (phimCanXoa == null) {
	            JOptionPane.showMessageDialog(this, "Không tìm thấy phim để xóa!");
	            return;
	        }

	        if (dsPhim.xoaPhim(phimCanXoa)) {
	            modelPhim.removeRow(row);
	            JOptionPane.showMessageDialog(this, "Xóa phim thành công!");
	            
	            capNhatPhimUI.run(); 
	            
	        } else {
	            JOptionPane.showMessageDialog(this, "Xóa phim khỏi CSDL thất bại!");
	        }
	    }
	}

	private void xoaCombo() {
		int row = tableCombo.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn combo cần xóa!");
			return;
		}

		String ma = modelCombo.getValueAt(row, 0).toString();
		ComboDoAn combo = new ComboDoAn(ma);

		if (dsCombo.xoaCombo(combo)) {
			modelCombo.removeRow(row);
			JOptionPane.showMessageDialog(this, "Xóa combo thành công!");
		} else {
			JOptionPane.showMessageDialog(this, "Không thể xóa combo!");
		}
	}

	private void capNhatPhim() {
	    int row = tablePhim.getSelectedRow();
	    if (row == -1) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn phim cần cập nhật!");
	        return;
	    }

	    if (!kiemTraDuLieuPhim())
	        return;

	    Phim p = chuyenDuLieuVaoPhim();

	    if (p == null) {
	        return;
	    }

	    if (dsPhim.capNhatPhim(p)) {
	        modelPhim.setValueAt(p.getTenPhim(), row, 1);
	        modelPhim.setValueAt(p.getTheLoai(), row, 2);
	        modelPhim.setValueAt(p.getThoiLuong(), row, 3);
	        modelPhim.setValueAt(p.getDaoDien(), row, 4);
	        modelPhim.setValueAt(p.isTrangThai() ? "Đang chiếu" : "Sắp ra mắt", row, 5);
	        modelPhim.setValueAt(p.getMoTa(), row, 6);
	        modelPhim.setValueAt(p.getDuongDanAnh(), row, 7); 

	        JOptionPane.showMessageDialog(this, "Cập nhật phim thành công!");
	        
	        capNhatPhimUI.run(); 
	        
	    } else {
	        JOptionPane.showMessageDialog(this, "Cập nhật phim CSDL thất bại!");
	    }
	}

	private void capNhatCombo() {
		int row = tableCombo.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn combo cần cập nhật!");
			return;
		}

		if (!kiemTraDuLieuCombo())
			return;

		ComboDoAn c = chuyenDuLieuVaoCombo();
		if (c == null)
			return;

		if (dsCombo.capNhatCombo(c)) {
			modelCombo.setValueAt(c.getTenCombo(), row, 1);
			modelCombo.setValueAt(c.getGia(), row, 2);
			modelCombo.setValueAt(c.getSoLuong(), row, 3);
			modelCombo.setValueAt(c.getPath(), row, 4);

			JOptionPane.showMessageDialog(this, "Cập nhật combo thành công!");
		} else {
			JOptionPane.showMessageDialog(this, "Không tìm thấy combo để cập nhật!");
		}
	}

	public void timPhimTheoTen() {
		String tenTim = txtTimPhim.getText().trim();

		if (!tenTim.isEmpty()) {
			try {
				Phim phim = dsPhim.timPhimTheoTen(tenTim);
				XoaHetDuLieuTrenModelPhim();
				if (phim != null) {
					modelPhim.addRow(new Object[] { phim.getMaPhim(), phim.getTenPhim(), phim.getTheLoai(),
							phim.getThoiLuong(), phim.getDaoDien(), phim.isTrangThai() });
				} else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy phim có tên: " + tenTim);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ!!!");
				txtTimPhim.selectAll();
				txtTimPhim.requestFocus();
			}
		} else {
			XoaHetDuLieuTrenModelPhim();
			DocDuLieuTuArrayListVaoModelPhim();
			tablePhim.setModel(modelPhim);
		}
	}

	public void timTheoTenNhanVien() {
		String tenTim = txtTim.getText().trim();
		if (tenTim != null) {
			try {
				NhanVien nv = dsNV.timNhanVien(tenTim);
				XoaHetDuLieuTrenModel();
				model.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.getEmail(), nv.getSoDienThoai() });
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ!!!");
				txtTim.selectAll();
				txtTim.requestFocus();
			}
		} else {
			XoaHetDuLieuTrenModel();
			DocDuLieuTuArrayListVaoModel();
			table.setModel(model);
		}
	}

	public void capNhatNhanVien() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String ma = txtMa.getText();
		String ten = txtTen.getText();
		String email = txtEmail.getText();
		String sdt = txtSdt.getText();
		int row = table.getSelectedRow();
		NhanVien nv = new NhanVien(ma, ten);
		if (dsNV.capNhatNhanVien(nv)) {
			model.setValueAt(ma, row, 0);
			model.setValueAt(ten, row, 1);
			model.setValueAt(email, row, 2);
			model.setValueAt(sdt, row, 3);
			JOptionPane.showMessageDialog(this, "Cập nhật thành công!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void xoaNhanVien() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn xóa!");
			return;
		}
		int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa?", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String ma = txtMa.getText();
		String ten = txtTen.getText();
		String email = txtEmail.getText();
		String sdt = txtSdt.getText();
		NhanVien nv = new NhanVien(ma, ten);
		if (luaChon == JOptionPane.YES_OPTION) {
			model.removeRow(row);
			dsNV.xoaNhanVien(nv);
		}
	}

	public void DocDuLieuTuArrayListVaoModel() {
		for (int i = 0; i < dsNV.getSize(); i++) {
			NhanVien nv = dsNV.getElement(i);
			model.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.getEmail(), nv.getSoDienThoai() });
		}
	}

	public void DocDuLieuTuArrayListVaoModelPhim() {
		for (int i = 0; i < dsPhim.getSize(); i++) {
			Phim p = dsPhim.getElement(i);
			modelPhim.addRow(new Object[] { p.getMaPhim(), p.getTenPhim(), p.getTheLoai(), p.getThoiLuong(),
					p.getDaoDien(), p.isTrangThai() ? "Đang chiếu" : "Sắp ra mắt" });
		}
	}

	public void XoaHetDuLieuTrenModel() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
	}

	public void XoaHetDuLieuTrenModelPhim() {
		DefaultTableModel modelPhim = (DefaultTableModel) tablePhim.getModel();
		modelPhim.setRowCount(0);
	}

	private void loadSlides() {
		int slideWidth = 1550;
		int slideHeight = 450;
		for (int i = 0; i < imagePaths.length; i++) {
			JLabel imageLabel = new JLabel();
			imageLabel.setPreferredSize(new Dimension(slideWidth, slideHeight));
			imageLabel.setHorizontalAlignment(JLabel.CENTER);
			imageLabel.setOpaque(true);

			imageLabel.setOpaque(true);
			imageLabel.setBackground(primary);

			try {
				String fileName = imagePaths[i].substring(imagePaths[i].lastIndexOf('/') + 1);
				java.net.URL imageUrl = getClass().getResource("/Image/" + fileName);

				if (imageUrl == null) {
					throw new Exception("Không tìm thấy tệp ảnh slide cục bộ: " + fileName);
				}

				ImageIcon originalIcon = new ImageIcon(imageUrl);
				Image originalImage = originalIcon.getImage();

				Image scaledImage = originalImage.getScaledInstance(slideWidth, slideHeight, Image.SCALE_SMOOTH);
				ImageIcon scaledIcon = new ImageIcon(scaledImage);

				imageLabel.setIcon(scaledIcon);

				slidePanel.add(imageLabel, "Card" + i);

			} catch (Exception e) {
				System.err.println("Lỗi tải ảnh: " + imagePaths[i]);
				e.printStackTrace();

				JLabel errorSlide = new JLabel("LỖI: Không tìm thấy ảnh " + (i + 1), JLabel.CENTER);
				errorSlide.setOpaque(true);
				errorSlide.setBackground(Color.RED);
				errorSlide.setForeground(Color.WHITE);
				errorSlide.setPreferredSize(new Dimension(slideWidth, slideHeight));
				slidePanel.add(errorSlide, "Card" + i);
			}
		}
	}

	private void startSlideShowTimer() {
		Timer timer = new Timer(3000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentSlide = (currentSlide + 1) % imagePaths.length;
				cardLayout.show(slidePanel, "Card" + currentSlide);
			}
		});
		timer.start();
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
