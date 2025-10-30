package UI;

import javax.swing.*;

import Entity.Phim;
import Entity.SuatChieu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form_HienThiThongTinPhim extends JFrame {

	private static final int FRAME_WIDTH = 650;
	private static final int FRAME_HEIGHT = 450;
	private final Color primary = new Color(26, 26, 46); 
	private final Color accent = new Color(233, 69, 96); 
	private final Color textColor = Color.WHITE;
	private JButton btnDatVe;
	private SuatChieu suatChieu;

	public Form_HienThiThongTinPhim(SuatChieu suatChieu) {
		this.suatChieu = suatChieu;
		setTitle("Thông Tin Chi Tiết Phim: " + suatChieu.getPhim().getTenPhim());
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		setResizable(false);

		setLayout(new BorderLayout(10, 0));

		// ======================= PHẦN BÊN TRÁI: HIỂN THỊ ẢNH =======================
		JPanel pnlAnh = taoPanelAnh(suatChieu.getPhim().getDuongDanAnh());
		add(pnlAnh, BorderLayout.WEST);

		// ======================= PHẦN BÊN PHẢI: HIỂN THỊ THÔNG TIN =======================
		JPanel pnlThongTin = taoPanelThongTin(suatChieu.getPhim());
		add(pnlThongTin, BorderLayout.CENTER);

		setVisible(true);
		getContentPane().setBackground(primary);
		setLabelColor(this, textColor);
	}

	private JPanel taoPanelAnh(String duongDanAnh) {
		JPanel pnl = new JPanel(new BorderLayout());
		pnl.setPreferredSize(new Dimension(220, FRAME_HEIGHT));
		pnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));
		pnl.setBackground(primary);
		JLabel lblAnh = new JLabel();
		lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnh.setVerticalAlignment(SwingConstants.CENTER);

		try {
			ImageIcon iconGoc = new ImageIcon(duongDanAnh);
			Image imgGoc = iconGoc.getImage();
			Image imgScale = imgGoc.getScaledInstance(200, 380, Image.SCALE_SMOOTH);
			lblAnh.setIcon(new ImageIcon(imgScale));
		} catch (Exception e) {
			lblAnh.setText("Không tìm thấy ảnh");
			lblAnh.setFont(new Font("Roboto", Font.ITALIC, 14));
			lblAnh.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		}

		pnl.add(lblAnh, BorderLayout.CENTER);

		setLabelColor(this, textColor);
		return pnl;
	}

	private JPanel taoPanelThongTin(Phim phim) {
		JPanel pnl = new JPanel(new GridBagLayout());
		pnl.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10));
		GridBagConstraints gbc = new GridBagConstraints();
		pnl.setBackground(primary);
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;

		int row = 0;

		JLabel lblTenPhim = new JLabel(phim.getTenPhim());
		lblTenPhim.setFont(new Font("Roboto", Font.BOLD, 24));
		gbc.gridx = 0;
		gbc.gridy = row++;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(lblTenPhim, gbc);

		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		gbc.gridy = row++;
		gbc.insets = new Insets(0, 0, 10, 0);
		pnl.add(separator, gbc);
		gbc.insets = new Insets(5, 5, 5, 5);

		String[] labels = { "Thể loại:", "Thời lượng:", "Đạo diễn:", "Trạng thái:" };

		String trangThaiString = phim.isTrangThai() ? "Đang Chiếu" : "Ngừng Chiếu";

		String[] values = { phim.getTheLoai().toString(),

				phim.getThoiLuong() + " phút",

				phim.getDaoDien(),

				trangThaiString };

		gbc.gridwidth = 1;
		for (int i = 0; i < labels.length; i++) {
			JLabel lbl = new JLabel(labels[i]);
			lbl.setFont(new Font("Roboto", Font.BOLD, 13));
			gbc.gridx = 0;
			gbc.gridy = row;
			pnl.add(lbl, gbc);

			JLabel lblVal = new JLabel(values[i]);
			lblVal.setFont(new Font("Roboto", Font.PLAIN, 13));
			gbc.gridx = 1;
			gbc.gridy = row;
			pnl.add(lblVal, gbc);

			row++;
		}

		JLabel lblMoTaTitle = new JLabel("Mô tả:");
		lblMoTaTitle.setFont(new Font("Roboto", Font.BOLD, 13));
		gbc.gridx = 0;
		gbc.gridy = row++;
		pnl.add(lblMoTaTitle, gbc);

		JTextArea txtMoTa = new JTextArea(phim.getMoTa());
		txtMoTa.setFont(new Font("Roboto", Font.PLAIN, 12));
		txtMoTa.setWrapStyleWord(true);
		txtMoTa.setLineWrap(true);
		txtMoTa.setEditable(false);
		txtMoTa.setBackground(pnl.getBackground());

		JScrollPane scrollPane = new JScrollPane(txtMoTa);
		scrollPane.setPreferredSize(new Dimension(300, 150));
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		gbc.gridx = 0;
		gbc.gridy = row++;
		gbc.gridwidth = 2;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		pnl.add(scrollPane, gbc);
		
		btnDatVe = new JButton("ĐẶT VÉ NGAY");
        btnDatVe.setFont(new Font("Roboto", Font.BOLD, 16));
        btnDatVe.setBackground(accent); 
        btnDatVe.setForeground(textColor); 
        btnDatVe.setFocusPainted(false); 
        btnDatVe.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        btnDatVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new Form_DatVe(suatChieu);
                dispose();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2; 
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 0, 0, 0); 
        pnl.add(btnDatVe, gbc);
		
		setLabelColor(this, textColor);
		return pnl;
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

//	public static void main(String[] args) {
//		String duongDanAnhTest = "src/Image/anh1.jpg";
//
//		Phim phimTest = new Phim("P003", "Avatar: Dòng Chảy Của Nước", Phim.TheLoai.HANH_DONG, 192, "James Cameron",
//				true,
//				"Jake Sully hiện đang sống cùng gia đình mới trên hành tinh Pandora. Mọi thứ thay đổi khi một mối đe dọa cũ quay trở lại...",
//				duongDanAnhTest);
//
//		SwingUtilities.invokeLater(() -> {
//			new Form_HienThiThongTinPhim(phimTest);
//		});
//	}
}