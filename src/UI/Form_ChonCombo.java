package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import Entity.ComboDoAn;
import Entity.DanhSachCombo;
import Entity.Ghe;
import Entity.HoaDon;
import Entity.SuatChieu;

public class Form_ChonCombo extends JPanel implements ActionListener {
	private static final Color primary = new Color(10, 15, 45);
	private static final Color accent = new Color(50, 90, 255);
	private static final Color highlight = new Color(180, 100, 255);

	private JLabel lblTongChon;
	private Runnable onBack;
	private DanhSachCombo dsCombo;
	private JButton btnInfo;
	private List<Ghe> gheDaChon;
	private SuatChieu suatChieu;
	private String gioBatDauStr;
	private Consumer<HoaDon> onPayment;
	

	public Form_ChonCombo(DanhSachCombo dsCombo, SuatChieu suatChieu, String gioBatDauStr, List<Ghe> gheDaChon, Runnable onBack, Consumer<HoaDon> onPayment) {
		this.onBack = onBack;
		this.dsCombo = dsCombo;
		this.suatChieu = suatChieu;
		this.gheDaChon = gheDaChon;
		this.gioBatDauStr = gioBatDauStr;
		this.onPayment =  onPayment;

		setLayout(new BorderLayout());
		setBackground(primary);
		setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

		add(taoDanhSachCombo(), BorderLayout.CENTER);
		add(taoPanelDuoi(), BorderLayout.SOUTH);
	}

	public void reloadComboList() {
		removeAll();
		add(taoDanhSachCombo(), BorderLayout.CENTER);
		add(taoPanelDuoi(), BorderLayout.SOUTH);
		revalidate();
		repaint();
	}

	private JPanel taoDanhSachCombo() {
		JPanel pnl = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
		pnl.setOpaque(false);

		for (ComboDoAn combo : dsCombo.getDsCombo()) {
			pnl.add(taoItemCombo(combo));
		}

		return pnl;
	}

	private JPanel taoItemCombo(ComboDoAn combo) {
		JPanel card = new JPanel();
		card.setPreferredSize(new Dimension(250, 200));
		card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
		card.setBackground(accent);
		card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		card.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Ảnh
		JLabel lblImg = new JLabel();
		lblImg.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			ImageIcon icon = new ImageIcon(combo.getPath());
			Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			lblImg.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			lblImg.setText("Không có ảnh");
		}

		// Tên + giá
		JLabel lblTen = new JLabel(combo.getTenCombo());
		lblTen.setForeground(Color.WHITE);
		lblTen.setFont(new Font("Poppins", Font.BOLD, 14));
		lblTen.setAlignmentX(Component.CENTER_ALIGNMENT);

		DecimalFormat df = new DecimalFormat("#,### đ");
		JLabel lblGia = new JLabel(df.format(combo.getGia()));
		lblGia.setForeground(Color.WHITE);
		lblGia.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblGia.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Nút cộng trừ
		JLabel lblSoLuong = new JLabel(String.format("%02d", combo.getSoLuong()));
		lblSoLuong.setForeground(Color.WHITE);
		lblSoLuong.setFont(new Font("Poppins", Font.BOLD, 16));

		JButton btnTru = new JButton("-");
		JButton btnCong = new JButton("+");
		styleButton(btnTru);
		styleButton(btnCong);

		JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
		pnlButtons.setOpaque(false);
		pnlButtons.add(btnTru);
		pnlButtons.add(lblSoLuong);
		pnlButtons.add(btnCong);

		// === SỰ KIỆN ===
		btnCong.addActionListener(e -> {
			combo.setSoLuong(combo.getSoLuong() + 1);
			lblSoLuong.setText(String.format("%02d", combo.getSoLuong()));
			capNhatTong();
		});

		btnTru.addActionListener(e -> {
			if (combo.getSoLuong() > 0)
				combo.setSoLuong(combo.getSoLuong() - 1);
			lblSoLuong.setText(String.format("%02d", combo.getSoLuong()));
			capNhatTong();
		});

		card.add(lblImg);
		card.add(Box.createVerticalStrut(5));
		card.add(lblTen);
		card.add(Box.createVerticalStrut(5));
		card.add(lblGia);
		card.add(Box.createVerticalStrut(5));
		card.add(pnlButtons);

		return card;
	}

	private void styleButton(JButton btn) {
		btn.setBackground(highlight);
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("Poppins", Font.BOLD, 18));
		btn.setFocusPainted(false);
		btn.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	private JPanel taoPanelDuoi() {
		JPanel pnl = new JPanel(new BorderLayout());
		pnl.setOpaque(false);
		pnl.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

		lblTongChon = new JLabel("Snack: 0 combo", SwingConstants.LEFT);
		lblTongChon.setFont(new Font("Poppins", Font.PLAIN, 14));
		lblTongChon.setForeground(Color.WHITE);

		JButton btnBack = new JButton("Quay lại");
		btnBack.addActionListener(e -> {
			if (onBack != null)
				onBack.run();
		});

		btnInfo = new JButton("Thông tin");
		btnInfo.setBackground(highlight);
		btnInfo.setFont(new Font("Poppins", Font.BOLD, 16));
		btnInfo.setForeground(Color.WHITE);
		btnInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JPanel pnlRight = new JPanel();
		pnlRight.setOpaque(false);
		pnlRight.add(btnBack);
		pnlRight.add(btnInfo);

		pnl.add(lblTongChon, BorderLayout.WEST);
		pnl.add(pnlRight, BorderLayout.EAST);

		btnInfo.addActionListener(this);

		return pnl;
	}

	private void capNhatTong() {
		int tong = dsCombo.getDsCombo().stream().mapToInt(ComboDoAn::getSoLuong).sum();
		lblTongChon.setText("Snack: " + tong + " combo");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    Object o = e.getSource();
	    if (o.equals(btnInfo)) {
	        // === Xử lý lỗi NullPointerException ===

	        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Form_ChonCombo.this);
	        
	        String tenPhim = suatChieu.getPhim().getTenPhim();
	        int thoiLuong = suatChieu.getPhim().getThoiLuong(); 

	        // Tính toán thông tin ghế (giữ nguyên logic của bạn)
	        int soGheDaChon = gheDaChon.size();
	        String danhSachGhe = gheDaChon.stream()
	                .map(Ghe::getMaGhe)
	                .reduce((a, b) -> a + ", " + b)
	                .orElse("Chưa chọn");
	        
	        long gheThuong = gheDaChon.stream().filter(g -> g.getLoaiGhe().toString().equals("Ghế Thường")).count();
	        long gheVip = gheDaChon.stream().filter(g -> g.getLoaiGhe().toString().equals("Ghế Vip")).count();
	        long gheDoi = gheDaChon.stream().filter(g -> g.getLoaiGhe().toString().equals("Ghế Đôi")).count();
	        double giaVe = gheThuong * 50_000 + gheVip * 70_000 + gheDoi * 100_000;


	        java.util.List<ComboDoAn> combos = dsCombo.getDsCombo().stream()
	                .filter(c -> c.getSoLuong() > 0)
	                .toList();

	        Form_ThongTinKhachHang formThongTin = new Form_ThongTinKhachHang(
	                tenPhim, 
	                thoiLuong, 
	                soGheDaChon, 
	                danhSachGhe, 
	                giaVe, 
	                combos, 
	                gioBatDauStr, 
	                () -> { // Tham số thứ 8: Runnable onBack (quay lại form Combo này)
	                    // Gắn lại chính instance Form_ChonCombo đang chạy để bảo toàn trạng thái
	                    frame.setContentPane(Form_ChonCombo.this); 
	                    frame.revalidate();
	                    frame.repaint();
	                },
	                this.onPayment // Tham số thứ 9: Consumer<HoaDon> onPayment
	            );
	        
	        frame.setContentPane(formThongTin);
	        frame.setSize(1000, 700);
	        frame.setLocationRelativeTo(null);
	        
	        frame.revalidate();
	        frame.repaint();
	    }
	}
}