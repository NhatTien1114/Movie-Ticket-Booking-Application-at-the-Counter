package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import Entity.DanhSachPhongChieu;
import Entity.Ghe;
import Entity.Phim;
import Entity.PhongChieu;
import Entity.SuatChieu;
import java.util.function.Consumer;

public class Form_ChonGhe extends JPanel implements ActionListener {
	private Phim phim;
	private Ghe ghe;
	private List<Ghe> gheDaChon = new ArrayList<>();
	private JPanel pnlGhe;
	private JButton btnQuayLai;
	private JButton btnGhe;
	private PhongChieu phongChieu;
	private JButton btnXacNhan;
	private Consumer<List<Ghe>> actionTiepTheo;
	private Runnable actionQuayLai;
	private SuatChieu suatChieu;
	private String gioBatDauStr;

	public Form_ChonGhe(Phim phim, PhongChieu phongChieu, SuatChieu suatChieu,String gioBatDauStr, Runnable actionQuayLai, 
			Consumer<List<Ghe>> actionTiepTheo) {
		this.phim = phim;
		this.phongChieu = phongChieu;
		this.actionQuayLai = actionQuayLai;
		this.suatChieu = suatChieu;
		this.actionTiepTheo = actionTiepTheo;
		this.gioBatDauStr = gioBatDauStr;
		setLayout(new BorderLayout());
		setBackground(new Color(10, 14, 35));

		// ==== PANEL CHÍNH ====
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(new Color(10, 14, 35));

		// ==== BÊN TRÁI: POSTER PHIM ====
		JLabel lblPoster = new JLabel();
		lblPoster.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoster.setVerticalAlignment(SwingConstants.CENTER);
		lblPoster.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		try {
			ImageIcon icon = new ImageIcon(phim.getDuongDanAnh());
			Image img = icon.getImage().getScaledInstance(280, 420, Image.SCALE_SMOOTH);
			lblPoster.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			lblPoster.setText("Không tìm thấy ảnh");
			lblPoster.setForeground(Color.LIGHT_GRAY);
		}
		mainPanel.add(lblPoster, BorderLayout.WEST);

		// ==== BÊN PHẢI: KHU VỰC CHỌN GHẾ ====
		JPanel pnlRight = new JPanel(new BorderLayout());
		pnlRight.setBackground(new Color(15, 20, 40));
		pnlRight.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// --- PHẦN TRÊN: CHÚ THÍCH GHẾ ---
		JPanel pnlLegend = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
		pnlLegend.setBackground(new Color(15, 20, 40));
		pnlLegend.add(createLegend("Thường", new Color(0, 120, 255)));
		pnlLegend.add(createLegend("VIP", new Color(255, 80, 80)));
		pnlLegend.add(createLegend("Đôi", new Color(150, 80, 255)));
		pnlLegend.add(createLegend("Đã đặt", Color.GRAY));
		pnlLegend.add(createLegend("Đang chọn", new Color(255, 0, 255)));
		pnlRight.add(pnlLegend, BorderLayout.NORTH);

		// --- PHẦN GIỮA: DANH SÁCH GHẾ ---
		pnlGhe = new JPanel(new GridLayout(0, 10, 10, 10));
		pnlGhe.setBackground(new Color(15, 20, 40));
		hienThiDanhSachGhe();

		JScrollPane scrollPane = new JScrollPane(pnlGhe);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		pnlRight.add(scrollPane, BorderLayout.CENTER);

		// --- PHẦN DƯỚI: NÚT CHỨC NĂNG ---
		JPanel pnlBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		pnlBottom.setBackground(new Color(15, 20, 40));

		btnQuayLai = new JButton("Quay lại");
		btnQuayLai.setBackground(new Color(60, 60, 100));
		btnQuayLai.setForeground(Color.WHITE);
		btnQuayLai.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnQuayLai.addActionListener(e -> actionQuayLai.run());

		btnXacNhan = new JButton("Xác nhận ghế");
		btnXacNhan.setBackground(new Color(180, 0, 255));
		btnXacNhan.setForeground(Color.WHITE);
		btnXacNhan.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnXacNhan.addActionListener(e -> {
			if (gheDaChon.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn ghế nào!");
				return;
			}
			String ghe = gheDaChon.stream().map(Ghe::getMaGhe).reduce((a, b) -> a + ", " + b).get();
			JOptionPane.showMessageDialog(this, "Ghế đã chọn: " + ghe);
		});

		pnlBottom.add(btnQuayLai);
		pnlBottom.add(btnXacNhan);
		pnlRight.add(pnlBottom, BorderLayout.SOUTH);

		btnQuayLai.addActionListener(this);
		btnXacNhan.addActionListener(this);

		// GẮN PHẦN PHẢI VÀ TRÁI VÀO PANEL CHÍNH
		mainPanel.add(pnlRight, BorderLayout.CENTER);
		add(mainPanel, BorderLayout.CENTER);
	}

	private JPanel createLegend(String text, Color color) {
		JPanel legend = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		legend.setBackground(new Color(15, 20, 40));
		JPanel colorBox = new JPanel();
		colorBox.setBackground(color);
		colorBox.setPreferredSize(new Dimension(20, 20));
		legend.add(colorBox);
		JLabel lbl = new JLabel(text);
		lbl.setForeground(Color.WHITE);
		lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		legend.add(lbl);
		return legend;
	}

	private void hienThiDanhSachGhe() {
		pnlGhe.removeAll();

		// Lấy phòng chiếu tương ứng với phim
		DanhSachPhongChieu dsPhong = new DanhSachPhongChieu();
		int indexPhim = Math.min(2, phim.getMaPhim().hashCode() % 3);
		phongChieu = dsPhong.getPhongTheoChiSo(indexPhim);

		SuatChieu suatChieu = new SuatChieu("SC" + (indexPhim + 1), LocalDateTime.now(), phim, phongChieu);
		List<Ghe> dsGhe = suatChieu.getPhongChieu().getDanhSachGhe();

		for (Ghe ghe : dsGhe) {
			JButton btnGhe = new JButton(ghe.getMaGhe());
			btnGhe.addActionListener(this);
			btnGhe.setFocusPainted(false);
			btnGhe.setFont(new Font("Segoe UI", Font.BOLD, 12));
			btnGhe.setForeground(Color.WHITE);
			btnGhe.setOpaque(true);
			btnGhe.setBorderPainted(false);
			btnGhe.setCursor(new Cursor(Cursor.HAND_CURSOR));

			if (ghe.isTinhTrang()) {
				btnGhe.setBackground(Color.GRAY);
				btnGhe.setEnabled(false);
			} else {
				switch (ghe.getLoaiGhe()) {
				case THUONG -> btnGhe.setBackground(new Color(0, 120, 255));
				case VIP -> btnGhe.setBackground(new Color(255, 80, 80));
				case DOI -> btnGhe.setBackground(new Color(150, 80, 255));
				}
			}

			btnGhe.addActionListener(e -> {
				if (gheDaChon.contains(ghe)) {
					gheDaChon.remove(ghe);
					switch (ghe.getLoaiGhe()) {
					case THUONG -> btnGhe.setBackground(new Color(0, 120, 255));
					case VIP -> btnGhe.setBackground(new Color(255, 80, 80));
					case DOI -> btnGhe.setBackground(new Color(150, 80, 255));
					}
				} else {
					gheDaChon.add(ghe);
					btnGhe.setBackground(new Color(255, 0, 255));
				}
			});

			pnlGhe.add(btnGhe);
		}

		pnlGhe.revalidate();
		pnlGhe.repaint();
	}

	public List<Ghe> getGheDaChon() {
		return gheDaChon;
	}

	private Ghe timGheCap(PhongChieu phongChieu, Ghe ghe) {
		try {
			String ma = ghe.getMaGhe();
			char hang = ma.charAt(0);
			int so = Integer.parseInt(ma.substring(1));
			int soCap = (so % 2 == 0) ? so - 1 : so + 1;
			String maCap = hang + String.valueOf(soCap);

			for (Ghe g : phongChieu.getDanhSachGhe()) {
				if (g.getMaGhe().equals(maCap) && g.getLoaiGhe() == Ghe.LoaiGhe.DOI)
					return g;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXacNhan)) {
			if (gheDaChon.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn ghế nào!");
				return;
			}
			actionTiepTheo.accept(gheDaChon);
		} else if (o.equals(btnQuayLai)) {
	        actionQuayLai.run();
	    }
	}
}
