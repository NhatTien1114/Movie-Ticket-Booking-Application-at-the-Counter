package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import UI.Form_ThanhToan;


public class Form_ThanhToan extends JPanel implements ActionListener {
	private static final Color primary = new Color(26, 26, 46);
	private static final Color accent = new Color(233, 69, 96);
	private static final Color highlight = new Color(180, 100, 255);
	private static final Font fontTitle = new Font("Unbounded", Font.BOLD, 26);
	private static final Font fontHeader = new Font("Unbounded", Font.BOLD, 18);
	private static final Font fontText = new Font("Poppins", Font.PLAIN, 14);

	private String qrCodeData;
	private double totalAmount;
	private String movieName;
	private String seats;
	private String date;
	private String time;
	private String address;
	private String snackDetails;
	private String ticketCode;

	private JButton btnReturnHome, btnDownload;
	private Runnable onReturnHome;

	public Form_ThanhToan(String qrCodeData, String ticketCode, double totalAmount, String movieName, String seats,
			String date, String time, String address, String snackDetails, Runnable onReturnHome) {
		this.qrCodeData = qrCodeData;
		this.ticketCode = ticketCode;
		this.totalAmount = totalAmount;
		this.movieName = movieName;
		this.seats = seats;
		this.date = date;
		this.time = time;
		this.address = address;
		this.snackDetails = snackDetails;
		this.onReturnHome = onReturnHome;

		setLayout(new BorderLayout());
		setBackground(primary);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		add(createHeaderPanel(), BorderLayout.NORTH);
		add(createContentPanel(), BorderLayout.CENTER);
		add(createFooterPanel(), BorderLayout.SOUTH);
	}

	private JPanel createHeaderPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(primary);
		JLabel lblHeader = new JLabel("THANH TOÁN THÀNH CÔNG!", SwingConstants.CENTER);
		lblHeader.setFont(fontTitle);
		lblHeader.setForeground(Color.WHITE);
		panel.add(lblHeader);
		return panel;
	}

	private JPanel createContentPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 30, 0));
		panel.setBackground(primary);
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		panel.add(createLeftPanel());
		panel.add(createRightPanel());

		return panel;
	}

	private JPanel createLeftPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(20, 20, 60));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

		JLabel lblTicket = new JLabel("VÉ", SwingConstants.CENTER);
		lblTicket.setFont(fontHeader);
		lblTicket.setForeground(Color.WHITE);
		lblTicket.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblTicket);
		panel.add(Box.createVerticalStrut(20));

		try {
			ImageIcon qrImage = generateQRCodeImage(ticketCode, 200, 200); 
			JLabel lblQr = new JLabel(qrImage);
			lblQr.setAlignmentX(Component.CENTER_ALIGNMENT);
			panel.add(lblQr);
			
			JLabel lblTicketCode = new JLabel(ticketCode, SwingConstants.CENTER);
			lblTicketCode.setFont(fontText.deriveFont(Font.BOLD, 16));
			lblTicketCode.setForeground(Color.WHITE);
			lblTicketCode.setAlignmentX(Component.CENTER_ALIGNMENT);
			panel.add(lblTicketCode);
			
		} catch (Exception e) {
			JLabel lblError = new JLabel("QR Code Error", SwingConstants.CENTER);
			lblError.setForeground(Color.RED);
			lblError.setAlignmentX(Component.CENTER_ALIGNMENT);
			panel.add(lblError);
			e.printStackTrace();
		}
		
		panel.add(Box.createVerticalGlue()); 
		
		return panel;
	}

	private JPanel createRightPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(20, 20, 60));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

		JLabel lblTitle = new JLabel("CHI TIẾT VÉ", SwingConstants.LEFT);
		lblTitle.setFont(fontHeader);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(lblTitle);
		panel.add(Box.createVerticalStrut(20));

		addDetail(panel, "Phim:", movieName);
		addDetail(panel, "Địa Chỉ:", address);
		addDetail(panel, "Ngày Đặt:", date);
		addDetail(panel, "Ghế:", seats);
		addDetail(panel, "Combo:", snackDetails);
		addDetail(panel, "Thời Gian:", time);
		addDetail(panel, "Phòng:", "3");
		
		DecimalFormat df = new DecimalFormat("#,###");
		addDetail(panel, "Total:", df.format(totalAmount) + " vnd", fontHeader.deriveFont(Font.BOLD, 20), accent);

		return panel;
	}

	private void addDetail(JPanel parent, String label, String value) {
		addDetail(parent, label, value, fontText, Color.WHITE);
	}
	
	private void addDetail(JPanel parent, String label, String value, Font valueFont, Color valueColor) {
	    JPanel detailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
	    detailPanel.setOpaque(false);
	    detailPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    
	    JLabel lblLabel = new JLabel(label + " ");
	    lblLabel.setFont(fontText.deriveFont(Font.BOLD));
	    lblLabel.setForeground(Color.WHITE);
	    
	    JLabel lblValue = new JLabel(value);
	    lblValue.setFont(valueFont);
	    lblValue.setForeground(valueColor);
	    
	    detailPanel.add(lblLabel);
	    detailPanel.add(lblValue);
	    
	    parent.add(detailPanel);
	    parent.add(Box.createVerticalStrut(8)); 
	}

	private JPanel createFooterPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		panel.setBackground(primary);

		btnReturnHome = createButton("QUAY LẠI", highlight);
		btnDownload = createButton("TẢI XUỐNG", accent);

		panel.add(btnReturnHome);
		panel.add(btnDownload);

		btnReturnHome.addActionListener(this);
		btnDownload.addActionListener(this);
		
		return panel;
	}

	private JButton createButton(String text, Color bgColor) {
		JButton btn = new JButton(text);
		btn.setPreferredSize(new Dimension(180, 50));
		btn.setBackground(bgColor);
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("Poppins", Font.BOLD, 16));
		btn.setFocusPainted(false);
		btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return btn;
	}

	private ImageIcon generateQRCodeImage(String data, int width, int height) throws Exception {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
		BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
		return new ImageIcon(image);
	}
	
	private void savePanelAsImage() {
	    String fileName = "Ticket_" + this.ticketCode + ".png";
	    
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Lưu vé điện tử");
	    fileChooser.setSelectedFile(new File(fileName));

	    int userSelection = fileChooser.showSaveDialog(this);

	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        File fileToSave = fileChooser.getSelectedFile();
	        
	        try {
	            BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
	            Graphics2D g2d = image.createGraphics();
	            this.paint(g2d); 
	            g2d.dispose();

	            ImageIO.write(image, "png", fileToSave);
	            JOptionPane.showMessageDialog(this, 
	                "Đã lưu vé thành công tại: " + fileToSave.getAbsolutePath(), 
	                "Thành công", 
	                JOptionPane.INFORMATION_MESSAGE);

	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, 
	                "Lỗi khi lưu file: " + ex.getMessage(), 
	                "Lỗi", 
	                JOptionPane.ERROR_MESSAGE);
	            ex.printStackTrace();
	        }
	    }
	}
	
 
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnReturnHome) {
			if (onReturnHome != null) {
				onReturnHome.run(); 
			}
		} else if (e.getSource() == btnDownload) {
			savePanelAsImage();
		}
	}
}