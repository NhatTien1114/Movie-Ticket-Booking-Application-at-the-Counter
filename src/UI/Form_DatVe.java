package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import Entity.DanhSachCombo;
import Entity.Ghe;
import Entity.HoaDon;
import Entity.Phim;
import Entity.PhongChieu;
import Entity.SuatChieu;

import java.util.function.Consumer;
import java.util.List;

public class Form_DatVe extends JFrame implements ActionListener, MouseListener {

	private static final Color primary = new Color(10, 15, 45);
	private static final Color secondary = new Color(20, 25, 60);
	private static final Color primaryFade = primary.brighter();
	private static final Color secondaryFade = secondary.brighter();
	private static final Color accent = new Color(80, 100, 255);
	private static final Color highlight = new Color(180, 100, 255);
	private static final Color textColor = Color.WHITE;
	private JLabel lblDate;
	private JButton btnSeatings;
	private JPanel pnl;
	private JPanel pnlPoster;
	private JPanel pnlInfo;
	private JButton btnTime;
	private JButton btnCinema;
	private Phim phimDangChon;
	private DanhSachCombo dsCombo;
	private PhongChieu phongChieuDangChon;
	private SuatChieu suatChieuHienTai;
	private List<Ghe> gheDaChon;
	private String gioBatDauStr;
	private Consumer<HoaDon> onPayment;

	private PhongChieu phong1 = new PhongChieu("P01", true, null);
	private PhongChieu phong2 = new PhongChieu("P02", true, null);
	private boolean[] selected = { false };
	private JButton btnTime2;

	public Form_DatVe(SuatChieu suatChieu) {
		this.suatChieuHienTai = suatChieu;
		this.phimDangChon = suatChieu.getPhim();
		this.gheDaChon = new ArrayList<>();
		dsCombo = new DanhSachCombo();
		setTitle("Đặt vé - " + suatChieu.getPhim().getTenPhim());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(900, 500);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// === PANEL TRÁI: ẢNH PHIM ===
		pnlPoster = taoPanelPoster(suatChieu.getPhim().getDuongDanAnh());
		add(pnlPoster, BorderLayout.WEST);

		// === PANEL PHẢI: THÔNG TIN MUA VÉ ===
		pnlInfo = taoPanelThongTin(suatChieu.getPhim());
		add(pnlInfo, BorderLayout.CENTER);

		getContentPane().setBackground(primary);
		setVisible(true);
	}

	private JPanel taoPanelPoster(String duongDanAnh) {
		JPanel pnl = new JPanel(new BorderLayout());
		pnl.setPreferredSize(new Dimension(300, 0));
		pnl.setBackground(primary);

		JLabel lblPoster = new JLabel();
		lblPoster.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoster.setVerticalAlignment(SwingConstants.CENTER);

		try {
			ImageIcon icon = new ImageIcon(duongDanAnh);
			Image img = icon.getImage().getScaledInstance(280, 420, Image.SCALE_SMOOTH);
			lblPoster.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			lblPoster.setText("Không tìm thấy ảnh");
			lblPoster.setForeground(Color.LIGHT_GRAY);
		}

		pnl.add(lblPoster, BorderLayout.CENTER);
		return pnl;
	}

	private JPanel taoPanelThongTin(Phim phim) {
		pnl = new JPanel();
		pnl.setBackground(primary);
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
		pnl.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

		JLabel lblTitle = new JLabel("ĐẶT VÉ");
		lblTitle.setFont(new Font("Poppins", Font.BOLD, 28));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		lblDate = new JLabel();
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Roboto", Font.BOLD, 16));
		lblDate.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
		Timer timeTimer = new Timer(1000, e -> {
			SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM/yyyy", new Locale("vi", "VN"));
			lblDate.setText(sdf.format(new Date()));
		});
		timeTimer.start();

		btnCinema = new JButton("CGV Gò Vấp");
		btnCinema.setBackground(accent);
		btnCinema.setForeground(Color.WHITE);
		btnCinema.setFocusPainted(false);
		btnCinema.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnCinema.setFont(new Font("Poppins", Font.BOLD, 16));
		btnCinema.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCinema.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JPanel pnlTime = new JPanel();
		pnlTime.setOpaque(false);
		pnlTime.setLayout(new BoxLayout(pnlTime, BoxLayout.Y_AXIS));
		pnlTime.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlTime.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

		btnTime = new JButton("14:00");
		btnTime.setBackground(accent);
		btnTime.setForeground(Color.WHITE);
		btnTime.setFont(new Font("Poppins", Font.BOLD, 18));
		btnTime.setFocusPainted(false);
		btnTime.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		btnTime.setAlignmentX(Component.CENTER_ALIGNMENT);

		btnTime2 = new JButton("17:00");
		btnTime2.setBackground(accent);
		btnTime2.setForeground(Color.WHITE);
		btnTime2.setFont(new Font("Poppins", Font.BOLD, 18));
		btnTime2.setFocusPainted(false);
		btnTime2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		btnTime2.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblSeats = new JLabel("còn 96 chổ");
		lblSeats.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblSeats.setForeground(Color.LIGHT_GRAY);
		lblSeats.setAlignmentX(Component.CENTER_ALIGNMENT);

		pnlTime.add(btnTime);
		pnlTime.add(btnTime2);
		pnlTime.add(Box.createVerticalStrut(5));
		pnlTime.add(lblSeats);

		btnSeatings = new JButton("Tiếp theo");
		btnSeatings.setBackground(highlight);
		btnSeatings.setForeground(Color.WHITE);
		btnSeatings.setFont(new Font("Poppins", Font.BOLD, 15));
		btnSeatings.setFocusPainted(false);
		btnSeatings.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSeatings.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnSeatings.setCursor(new Cursor(Cursor.HAND_CURSOR));

		pnl.add(lblTitle);
		pnl.add(Box.createVerticalStrut(20));
		pnl.add(lblDate);
		pnl.add(Box.createVerticalStrut(20));
		pnl.add(btnCinema);
		pnl.add(pnlTime);
		pnl.add(Box.createVerticalStrut(20));
		pnl.add(btnSeatings);

		btnSeatings.addActionListener(this);
		btnTime.addActionListener(this);
		btnTime2.addActionListener(this);
		btnCinema.addActionListener(this);

		return pnl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		// =======================================================
		// --- KHỐI XỬ LÝ CHUYỂN MÀN HÌNH (BTN SEATINGS) ---
		// =======================================================
		if (o.equals(btnSeatings)) {
			if (phongChieuDangChon == null) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn giờ chiếu trước!");
				return;
			}

			final SuatChieu suatChieuDuocChon = new SuatChieu(suatChieuHienTai.getMaSuatChieu(),
					suatChieuHienTai.getNgayGio(), phimDangChon, phongChieuDangChon);

			getContentPane().removeAll();

			Form_ChonGhe panelChonGhe = new Form_ChonGhe(phimDangChon, phongChieuDangChon, suatChieuDuocChon,
					gioBatDauStr, () -> {
						getContentPane().removeAll();
						add(pnlPoster, BorderLayout.WEST);
						add(pnlInfo, BorderLayout.CENTER);
						revalidate();
						repaint();
					}, (List<Ghe> gheList) -> {
						gheDaChon.clear();
						gheDaChon.addAll(gheList);

						getContentPane().removeAll();

						Form_ChonCombo formCombo = new Form_ChonCombo(dsCombo, suatChieuDuocChon, gioBatDauStr,
								gheDaChon, () -> {
									getContentPane().removeAll();
									add(pnlPoster, BorderLayout.WEST);
									add(pnlInfo, BorderLayout.CENTER);
									revalidate();
									repaint();
								}, onPayment);
						add(formCombo, BorderLayout.CENTER);
						revalidate();
						repaint();
					});
			add(panelChonGhe, BorderLayout.CENTER);
			revalidate();
			repaint();
		} else if (o.equals(btnTime)) {
			gioBatDauStr = btnTime.getText();
			phongChieuDangChon = phong1;
			btnTime.setBackground(highlight);
			btnTime2.setBackground(accent);
			btnSeatings.setEnabled(true);

		} else if (o.equals(btnTime2)) {
			gioBatDauStr = btnTime2.getText();
			phongChieuDangChon = phong2;
			btnTime2.setBackground(highlight);
			btnTime.setBackground(accent);
			btnSeatings.setEnabled(true);

		} else if (o.equals(btnCinema)) {
			if (btnCinema.getBackground().equals(accent)) {
				btnCinema.setBackground(highlight);
			} else {
				btnCinema.setBackground(accent);
			}
		}
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

}
