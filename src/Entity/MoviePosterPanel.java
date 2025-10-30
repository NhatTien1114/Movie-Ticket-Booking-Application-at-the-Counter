package Entity;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
// Import Entity.Phim
import Entity.Phim;

public class MoviePosterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final Color primary = new Color(26, 26, 46);
	private final Color accent = new Color(233, 69, 96);

	private JLabel lblPoster;
	private JPanel overlay;
	private JLabel lblTitle;
	private JTextArea txtDescription;
	private SuatChieu suatChieu;

	public MoviePosterPanel(SuatChieu suatChieu) {
		this.suatChieu = suatChieu;

		JPanel layeredPanel = new JPanel();
		layeredPanel.setLayout(new OverlayLayout(layeredPanel));
		layeredPanel.setPreferredSize(new Dimension(250, 370));
		layeredPanel.setBackground(primary);

		lblPoster = new JLabel();
		lblPoster.setHorizontalAlignment(JLabel.CENTER);
		lblPoster.setIcon(resizeImage(suatChieu.getPhim().getDuongDanAnh(), 250, 370));
		lblPoster.setAlignmentX(0.5f);
		lblPoster.setAlignmentY(0.5f);

		overlay = new JPanel();
		overlay.setLayout(new BoxLayout(overlay, BoxLayout.Y_AXIS));
		overlay.setBackground(new Color(0, 0, 0, 220));
		overlay.setVisible(false);
		overlay.setBorder(new EmptyBorder(20, 15, 20, 15));
		overlay.setAlignmentX(0.5f);
		overlay.setAlignmentY(0.5f);

		lblTitle = new JLabel(suatChieu.getPhim().getTenPhim());
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Roboto", Font.BOLD, 16));

		String shortDesc = suatChieu.getPhim().getMoTa().length() > 150 ? suatChieu.getPhim().getMoTa().substring(0, 150) + "..." : suatChieu.getPhim().getMoTa();

		txtDescription = new JTextArea(shortDesc);
		txtDescription.setWrapStyleWord(true);
		txtDescription.setLineWrap(true);
		txtDescription.setEditable(false);
		txtDescription.setOpaque(false);
		txtDescription.setForeground(Color.WHITE);
		txtDescription.setFont(new Font("Roboto", Font.PLAIN, 12));

		overlay.add(lblTitle);
		overlay.add(Box.createVerticalStrut(5));
		overlay.add(txtDescription);
		overlay.add(Box.createVerticalGlue());

		layeredPanel.add(overlay);
		layeredPanel.add(lblPoster);
		setBackground(primary);
		add(layeredPanel, BorderLayout.CENTER);

		// 🧩 Hover event
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				overlay.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				overlay.setVisible(false);
			}
		});
	}

	// Trong Entity/MoviePosterPanel.java
	// ...
	public SuatChieu getSuatChieu() {
	    return suatChieu; // Trả về đối tượng SuatChieu đã được lưu trong constructor
	}

	public Phim getPhim() {
	    // Nên gọi qua SuatChieu để nhất quán
	    return suatChieu.getPhim();
	}
	// ...

	private ImageIcon resizeImage(String path, int width, int height) {
		String fileName = path.substring(path.lastIndexOf('/') + 1);

		java.net.URL imageUrl = getClass().getResource("/Image/" + fileName);

		if (imageUrl == null) {
			System.err.println("Lỗi tải ảnh poster: Không tìm thấy tệp trong classpath: /Image/" + fileName);
			return new ImageIcon();
		}

		try {
			File file = new File(path);
			BufferedImage img = ImageIO.read(file);
			Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			return new ImageIcon(scaledImg);
		} catch (IOException e) {
			System.err.println("Không thể đọc ảnh từ đường dẫn: " + path);
			e.printStackTrace();
			return new ImageIcon();
		}
	}
}