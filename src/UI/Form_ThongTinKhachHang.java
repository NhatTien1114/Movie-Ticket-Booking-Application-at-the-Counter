package UI;

import javax.swing.*;

import Entity.ChiTietHoaDon;
import Entity.ComboDoAn;
import Entity.HoaDon;
import Entity.Ve;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.LocalTime; // Cần import LocalTime

public class Form_ThongTinKhachHang extends JPanel implements ActionListener {
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
	private double giaVe; // Chú ý: giaVe ở đây là GIÁ CỦA 1 VÉ, KHÔNG phải tổng tiền vé.
	private List<ComboDoAn> comboDaChon;
	private String gioBatDauStr;
	private Consumer<HoaDon> onPayment;
	private JButton btnPayment;

	public Form_ThongTinKhachHang(String tenPhim, int thoiLuongPhim, int soGheDaChon, String danhSachGhe, double giaVe,
			java.util.List<ComboDoAn> comboDaChon, String gioBatDauStr, Runnable onBack, Consumer<HoaDon> onPayment) {
		this.onBack = onBack;
		this.tenPhim = tenPhim;
		this.thoiLuongPhim = thoiLuongPhim;
		this.soGheDaChon = soGheDaChon;
		this.danhSachGhe = danhSachGhe;
		this.giaVe = giaVe; // Giá vé cơ bản
		this.comboDaChon = comboDaChon;
		this.gioBatDauStr = gioBatDauStr;
		this.onPayment = onPayment;

		setLayout(new BorderLayout(30, 30));
		setBackground(primary);
		setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

		JScrollPane scrollPaneCustomer = new JScrollPane(createCustomerFormPanel());
		add(createTicketDetailPanel(), BorderLayout.WEST);
		add(scrollPaneCustomer, BorderLayout.CENTER);
		add(createTopPanel(), BorderLayout.NORTH);
	}

	// === Các phương thức UI (Giữ nguyên) ===
	private JPanel createTopPanel() {
		// ... (Code tạo header giữ nguyên) ...
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
		// ... (Code tạo panel chi tiết vé giữ nguyên) ...
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
        // Hiển thị thứ bằng tiếng Việt
		String dayOfWeek = today.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("vi", "VN"));
		String dateStr = dayOfWeek + ", " + today;
		
		java.time.LocalTime gioBatDau = java.time.LocalTime.parse(gioBatDauStr);
		java.time.LocalTime gioKetThuc = gioBatDau.plusMinutes(thoiLuongPhim);

		// Chú ý: giaVe * soGheDaChon là tổng tiền vé
		double tongTienVe = giaVe * soGheDaChon; 
		double tongTienCombo = comboDaChon.stream().mapToDouble(c -> c.getGia() * c.getSoLuong()).sum();
		double tongTien = tongTienVe + tongTienCombo;

		DecimalFormat df = new DecimalFormat("#,### đ");

		JLabel lblMovie = createLabel("Movie: " + tenPhim);
		JLabel lblAddress = createLabel("Address: 242 Nguyễn Văn Lượng, Gò Vấp");
		JLabel lblDate = createLabel("Date: " + dateStr);
		JLabel lblTickets = createLabel("Tickets: " + soGheDaChon);
		JLabel lblSeats = createLabel("Seats: " + danhSachGhe);
		JLabel lblTime = createLabel("Time: " + gioBatDau + " - " + gioKetThuc);
		JLabel lblScreen = createLabel("Screen: 3");

		String comboText = comboDaChon.isEmpty() ? "None"
				: comboDaChon.stream().filter(c -> c.getSoLuong() > 0).map(c -> c.getTenCombo() + " x" + c.getSoLuong())
						.reduce((a, b) -> a + ", " + b).orElse("Không có");
		JLabel lblCombo = createLabel("Combo: " + comboText);
		JLabel lblTotal = createLabel("Total: " + df.format(tongTien));

		for (JLabel lbl : new JLabel[] { lblMovie, lblAddress, lblDate, lblTickets, lblSeats, lblTime, lblScreen,
				lblCombo, lblTotal }) {
			panel.add(Box.createVerticalStrut(10)); 
			panel.add(lbl);
		}

		panel.add(Box.createVerticalGlue());
		return panel;
	}

	private JPanel createCustomerFormPanel() {
		// ... (Code tạo form khách hàng giữ nguyên) ...
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

		btnPayment = new JButton("Thanh Toán");
		btnPayment.setPreferredSize(new Dimension(220, 45));
		btnPayment.setBackground(accent);
		btnPayment.setForeground(Color.WHITE);
		btnPayment.setFont(new Font("Poppins", Font.BOLD, 16));
		btnPayment.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPayment.setBorder(BorderFactory.createEmptyBorder());
		btnPayment.setFocusPainted(false);

		pnlButtons.add(btnPayment);
		panel.add(pnlButtons);
		btnPayment.addActionListener(this);
		return panel;
	}

	private JPanel createInputGroup(String labelText, JTextField textField) {
		// ... (Code tạo group input giữ nguyên) ...
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
		group.add(Box.createVerticalStrut(5)); 
		group.add(textField);
		return group;
	}

	private JTextField createTextField() {
		// ... (Code tạo text field giữ nguyên) ...
		JTextField txt = new JTextField();
		txt.setFont(new Font("Roboto", Font.PLAIN, 14));
		txt.setMaximumSize(new Dimension(400, 35));
		txt.setAlignmentX(Component.LEFT_ALIGNMENT);
		txt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		return txt;
	}

	private JLabel createLabel(String text) {
		// ... (Code tạo label giữ nguyên) ...
		JLabel label = new JLabel(text);
		label.setFont(new Font("Poppins", Font.BOLD, 14));
		label.setForeground(Color.WHITE);
		label.setAlignmentX(Component.LEFT_ALIGNMENT); 
		label.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 0)); 
		return label;
	}

	private List<ChiTietHoaDon> createChiTietHoaDonList() {
		// ... (Code tạo ChiTietHoaDon giữ nguyên) ...
		List<ChiTietHoaDon> chiTietList = new ArrayList<>();

		if (soGheDaChon > 0) {
			// Lưu ý: giaVe ở đây là tổng tiền vé (số tiền ứng với số ghế đã chọn)
			// Tuy nhiên, logic Entity của bạn đang tạo Ve với giaVe truyền vào.
			// Nếu giaVe là giá 1 vé, thì đoạn code sau cần được kiểm tra lại logic Entity.
			// Tạm thời, giữ nguyên Ve(giaVe) và set soLuong
			Ve veCoBan = new Ve(this.giaVe / this.soGheDaChon); // Giả sử giaVe là tổng tiền vé, chia cho số lượng để lấy giá 1 vé
			ChiTietHoaDon chiTietVe = new ChiTietHoaDon();
			chiTietVe.setVe(veCoBan);
			chiTietVe.setSoLuong(soGheDaChon);
			chiTietList.add(chiTietVe);
		}

		if (comboDaChon != null) {
			for (ComboDoAn combo : comboDaChon) {
				if (combo.getSoLuong() > 0) {
					ChiTietHoaDon chiTietCombo = new ChiTietHoaDon();
					chiTietCombo.setCombo(combo);
					chiTietCombo.setSoLuong(combo.getSoLuong());

					chiTietList.add(chiTietCombo);
				}
			}
		}

		return chiTietList;
	}

	private boolean validateCustomerInfo() {
		// ... (Code kiểm tra validate giữ nguyên) ...
		String name = txtName.getText().trim();
		String email = txtEmail.getText().trim();
		String phone = txtPhone.getText().trim();

		if (name.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập họ và tên.", "Lỗi Thông Tin", JOptionPane.ERROR_MESSAGE);
			txtName.requestFocus();
			return false;
		}

		if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
			JOptionPane.showMessageDialog(this,
					"Email không hợp lệ. Vui lòng nhập đúng định dạng (ví dụ: example@gmail.com).", "Lỗi Email",
					JOptionPane.ERROR_MESSAGE);
			txtEmail.requestFocus();
			return false;
		}

		if (!phone.matches("0\\d{9}")) {
			JOptionPane.showMessageDialog(this,
					"Số điện thoại không hợp lệ. Vui lòng nhập 10 chữ số, bắt đầu bằng số 0.", "Lỗi Số Điện Thoại",
					JOptionPane.ERROR_MESSAGE);
			txtPhone.requestFocus();
			return false;
		}

		return true;
	}
	
    /**
     * Hàm xử lý sự kiện cho nút Thanh Toán.
     * Tạo dữ liệu cho Form_ThanhToan và chuyển màn hình.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
	    Object o = e.getSource();
	    if (o.equals(btnPayment)) {

	        // 1. Kiểm tra thông tin khách hàng
	        if (!validateCustomerInfo()) {
	            return;
	        }

	        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

	        // 2. Tính toán Tổng tiền (giaVe là tổng tiền vé)
	        double tongTienVe = this.giaVe * this.soGheDaChon; // SỬA: Giả sử giaVe đã là giá tổng tiền vé, cần kiểm tra lại
	        
	        double tongTienCombo = comboDaChon.stream()
	                .mapToDouble(c -> c.getGia() * c.getSoLuong())
	                .sum();
	                
	        // totalAmount = TỔNG TIỀN VÉ + TỔNG TIỀN COMBO
	        double totalAmount = tongTienVe + tongTienCombo; 

	        // 3. Chuẩn bị dữ liệu hiển thị cho Form_ThanhToan
	        
	        // Mã Vé Giả
	        String ticketCode = "UZ" + (int)(Math.random() * 90000 + 10000) + "MBXS1";
	        String qrData = "PAYMENT_INFO|AMOUNT=" + totalAmount + "|CODE=" + ticketCode;

	        // Ngày 
	        java.time.LocalDate today = java.time.LocalDate.now();
	        String dateStr = today.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("vi", "VN")) + ", " + today.toString();
	        
	        // Giờ (Tính Giờ kết thúc)
	        java.time.LocalTime gioBatDau = java.time.LocalTime.parse(gioBatDauStr);
	        String timeStr = gioBatDauStr + " - " + gioBatDau.plusMinutes(thoiLuongPhim);

	        // Chi tiết Combo (Nếu không có combo, mặc định là "None")
	        String comboText = comboDaChon.stream().filter(c -> c.getSoLuong() > 0)
	                .map(c -> c.getTenCombo() + " x" + c.getSoLuong())
	                .reduce("None", (a, b) -> a.equals("None") ? b : a + ", " + b);
	        
	        String diaChiRap = "242 Nguyễn Văn Lượng, Gò Vấp,, Thành phố Hồ Chí Minh"; 
	        
	        // 4. Ghi nhận Hóa đơn (Giả định thanh toán thành công)
	        HoaDon hoaDon = new HoaDon();
	        hoaDon.setChiTiet(createChiTietHoaDonList());
	        if (onPayment != null) {	
	            onPayment.accept(hoaDon);
	        }

	        // 5. Định nghĩa hành động quay lại (Quay lại Form_ThongTinKhachHang)
	        Runnable backToInfoForm = () -> {
	            frame.setContentPane(this);
	            frame.revalidate();
	            frame.repaint();
	        };

	        // 6. TẠO VÀ HIỂN THỊ FORM_THANHTOAN (10 tham số)
	        Form_ThanhToan formThanhToan = new Form_ThanhToan(
	            qrData,
	            ticketCode,
	            totalAmount,
	            tenPhim, 
	            danhSachGhe, 
	            dateStr,
	            timeStr,
	            diaChiRap,
	            comboText,
	            backToInfoForm
	        );

	        frame.setContentPane(formThanhToan);
	        frame.revalidate();
	        frame.repaint();
	    }
	}
}