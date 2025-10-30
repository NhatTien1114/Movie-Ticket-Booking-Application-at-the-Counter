package UI;

import javax.swing.*;
import Entity.ComboDoAn;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;

public class Form_ThongTinKhachHang extends JPanel {
	private static final Color primary = new Color(26, 26, 46);
	private static final Color accent = new Color(233, 69, 96);
	private static final Color highlight = new Color(180, 100, 255);
	private static final Font fontTitle = new Font("Poppins", Font.BOLD, 20);
	private static final Font fontText = new Font("Poppins", Font.PLAIN, 14);

	private JTextField txtName, txtEmail, txtPhone;
	private Runnable onBack;
	private String tenPhim;
	private int thoiLuongPhim;
	private int soGheDaChon;
	private String danhSachGhe;
	private double giaVe;
	private List<ComboDoAn> comboDaChon;
	private String gioBatDauStr;
	

	public Form_ThongTinKhachHang(String tenPhim, int thoiLuongPhim, int soGheDaChon, String danhSachGhe, double giaVe,
			java.util.List<ComboDoAn> comboDaChon, String gioBatDauStr, Runnable onBack) {
		this.onBack = onBack;
		this.tenPhim = tenPhim;
		this.thoiLuongPhim = thoiLuongPhim;
		this.soGheDaChon = soGheDaChon;
		this.danhSachGhe = danhSachGhe;
		this.giaVe = giaVe;
		this.comboDaChon = comboDaChon;
		this.gioBatDauStr = gioBatDauStr;

		setLayout(new BorderLayout(30, 30));
		setBackground(primary);
		setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

		// ✅ Tăng kích thước panel tổng thể

		JScrollPane scrollPaneCustomer = new JScrollPane(createCustomerFormPanel());
		add(createTicketDetailPanel(), BorderLayout.WEST);
		add(scrollPaneCustomer, BorderLayout.CENTER);
		add(createTopPanel(), BorderLayout.NORTH); 
	}

	// 🔙 Tạo thanh tiêu đề có nút "Quay lại"
	private JPanel createTopPanel() {
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(primary);
		topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		JButton btnBack = new JButton("Quay lại");
		btnBack.setBackground(highlight);
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Poppins", Font.BOLD, 14));
		btnBack.setFocusPainted(false);
		btnBack.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
		btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));

		btnBack.addActionListener(e -> {
			if (onBack != null)
				onBack.run();
		});

		topPanel.add(btnBack, BorderLayout.WEST);

		JLabel lblHeader = new JLabel("XÁC NHẬN VÉ", SwingConstants.CENTER);
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setFont(new Font("Poppins", Font.BOLD, 22));
		topPanel.add(lblHeader, BorderLayout.CENTER);

		return topPanel;
	}

	private JPanel createTicketDetailPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(20, 25, 65));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
		panel.setPreferredSize(new Dimension(350, 0));

		JLabel lblTitle = new JLabel("CHI TIẾT VÉ");
		lblTitle.setFont(fontTitle);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(lblTitle);
		panel.add(Box.createVerticalStrut(10));

		java.time.LocalDate today = java.time.LocalDate.now();
		String dateStr = today.getDayOfWeek() + ", " + today;
		java.time.LocalTime gioBatDau = java.time.LocalTime.parse(gioBatDauStr);
		java.time.LocalTime gioKetThuc = gioBatDau.plusMinutes(thoiLuongPhim);

		double tongTienCombo = comboDaChon.stream().mapToDouble(c -> c.getGia() * c.getSoLuong()).sum();
		double tongTien = giaVe * soGheDaChon + tongTienCombo;

		DecimalFormat df = new DecimalFormat("#,### đ");

		JLabel lblMovie = createLabel("Movie: " + tenPhim);
		JLabel lblAddress = createLabel("Address: 242 Nguyễn Văn Lượng, Gò Vấp");
		JLabel lblDate = createLabel("Date: " + dateStr);
		JLabel lblTickets = createLabel("Tickets: " + soGheDaChon);
		JLabel lblSeats = createLabel("Seats: " + danhSachGhe);
		JLabel lblTime = createLabel("Time: " + gioBatDau + " - " + gioKetThuc);
		JLabel lblScreen = createLabel("Screen: 3");

		String comboText = comboDaChon.isEmpty() ? "None"
				: comboDaChon.stream().filter(c -> c.getSoLuong() > 0)
						.map(c -> c.getTenCombo() + " x" + c.getSoLuong())
						.reduce((a, b) -> a + ", " + b).orElse("Không có");
		JLabel lblCombo = createLabel("Combo: " + comboText);
		JLabel lblTotal = createLabel("Total: " + df.format(tongTien));

		for (JLabel lbl : new JLabel[] { lblMovie, lblAddress, lblDate, lblTickets, lblSeats, lblTime, lblScreen, lblCombo, lblTotal }) {
	        panel.add(Box.createVerticalStrut(10)); // tăng khoảng cách
	        panel.add(lbl);
	    }

		panel.add(Box.createVerticalGlue());
		return panel;
	}

	private JPanel createCustomerFormPanel() {
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    panel.setOpaque(false);
	    panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

	    // 🏷️ Tiêu đề
	    JLabel lblTitle = new JLabel("THÔNG TIN KHÁCH HÀNG");
	    lblTitle.setFont(new Font("Poppins", Font.BOLD, 24));
	    lblTitle.setForeground(Color.WHITE);
	    lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panel.add(lblTitle);
	    panel.add(Box.createVerticalStrut(25));

	    // 🧾 Form nhập liệu
	    JPanel pnlForm = new JPanel();
	    pnlForm.setBackground(primary);
	    pnlForm.setLayout(new BoxLayout(pnlForm, BoxLayout.Y_AXIS));
	    pnlForm.setOpaque(false);
	    pnlForm.setAlignmentX(Component.CENTER_ALIGNMENT);
	    pnlForm.setMaximumSize(new Dimension(400, 250));

	    // ============ Họ tên ============
	    pnlForm.add(createInputGroup("Họ và tên", txtName = createTextField()));
	    pnlForm.add(Box.createVerticalStrut(20));

	    // ============ Email ============
	    pnlForm.add(createInputGroup("Email", txtEmail = createTextField()));
	    pnlForm.add(Box.createVerticalStrut(20));

	    // ============ Số điện thoại ============
	    pnlForm.add(createInputGroup("Số điện thoại", txtPhone = createTextField()));
	    pnlForm.add(Box.createVerticalStrut(30));

	    panel.add(pnlForm);

	    // 🪙 Nút thanh toán
	    JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    pnlButtons.setOpaque(false);

	    JButton btnPayment = new JButton("Thanh Toán");
	    btnPayment.setPreferredSize(new Dimension(220, 45));
	    btnPayment.setBackground(accent);
	    btnPayment.setForeground(Color.WHITE);
	    btnPayment.setFont(new Font("Poppins", Font.BOLD, 16));
	    btnPayment.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    btnPayment.setBorder(BorderFactory.createEmptyBorder());
	    btnPayment.setFocusPainted(false);

	    pnlButtons.add(btnPayment);
	    panel.add(pnlButtons);

	    return panel;
	}

	// 👉 Hàm tạo nhóm label + textfield
	private JPanel createInputGroup(String labelText, JTextField textField) {
	    JPanel group = new JPanel();
	    group.setLayout(new BoxLayout(group, BoxLayout.Y_AXIS));
	    group.setOpaque(false);
	    group.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JLabel lbl = new JLabel(labelText);
	    lbl.setFont(new Font("Poppins", Font.BOLD, 14));
	    lbl.setForeground(Color.WHITE);
	    lbl.setAlignmentX(Component.LEFT_ALIGNMENT);

	    textField.setAlignmentX(Component.LEFT_ALIGNMENT);

	    group.add(lbl);
	    group.add(Box.createVerticalStrut(5)); // khoảng cách nhỏ giữa label và field
	    group.add(textField);
	    return group;
	}

	private JTextField createTextField() {
	    JTextField txt = new JTextField();
	    txt.setFont(new Font("Roboto", Font.PLAIN, 14));
	    txt.setMaximumSize(new Dimension(400, 35));
	    txt.setAlignmentX(Component.LEFT_ALIGNMENT);
	    txt.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
	            BorderFactory.createEmptyBorder(5, 10, 5, 10)
	    ));
	    return txt;
	}

	
	private JLabel createLabel(String text) {
	    JLabel label = new JLabel(text);
	    label.setFont(new Font("Poppins", Font.BOLD, 14));
	    label.setForeground(Color.WHITE);
	    label.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái cho đẹp khi dùng BoxLayout
	    label.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 0)); // tạo khoảng cách nhỏ với textfield
	    return label;
	}


}
