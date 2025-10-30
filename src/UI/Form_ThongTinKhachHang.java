package UI;

import javax.swing.*;

import Entity.ComboDoAn;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;

public class Form_ThongTinKhachHang extends JPanel {
    private static final Color primary = new Color(10, 15, 45);
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

    public Form_ThongTinKhachHang(
            String tenPhim,
            int thoiLuongPhim,
            int soGheDaChon,
            String danhSachGhe,
            double giaVe,
            java.util.List<ComboDoAn> comboDaChon,
            Runnable onBack
    ) {
        this.onBack = onBack;
        this.tenPhim = tenPhim;
        this.thoiLuongPhim = thoiLuongPhim;
        this.soGheDaChon = soGheDaChon;
        this.danhSachGhe = danhSachGhe;
        this.giaVe = giaVe;
        this.comboDaChon = comboDaChon;

        setLayout(new BorderLayout(30, 30));
        setBackground(primary);
        setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        add(createTicketDetailPanel(), BorderLayout.WEST);
        add(createCustomerFormPanel(), BorderLayout.CENTER);
    }


    private JPanel createTicketDetailPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(20, 25, 65));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblTitle = new JLabel("TICKET DETAILS");
        lblTitle.setFont(fontTitle);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(10));

        // 🕐 Ngày hiện tại
        java.time.LocalDate today = java.time.LocalDate.now();
        String dateStr = today.getDayOfWeek() + ", " + today;

        // 🕒 Giờ bắt đầu giả định (ví dụ 09:00)
        java.time.LocalTime gioBatDau = java.time.LocalTime.of(9, 0);
        java.time.LocalTime gioKetThuc = gioBatDau.plusMinutes(thoiLuongPhim);

        // 🎟️ Tổng tiền
        double tongTienCombo = comboDaChon.stream()
                .mapToDouble(c -> c.getGia() * c.getSoLuong())
                .sum();
        double tongTien = giaVe * soGheDaChon + tongTienCombo;

        DecimalFormat df = new DecimalFormat("#,### đ");

        // 📝 Các label động
        JLabel lblMovie = createLabel("<b>Movie:</b> " + tenPhim);
        JLabel lblAddress = createLabel("<b>Address:</b> CGV Gò Vấp, 242 Nguyễn Văn Lượng, Gò Vấp");
        JLabel lblDate = createLabel("<b>Date:</b> " + dateStr);
        JLabel lblTickets = createLabel("<b>Tickets:</b> " + soGheDaChon);
        JLabel lblSeats = createLabel("<b>Seats:</b> " + String.join(", ", danhSachGhe));
        JLabel lblTime = createLabel("<b>Time:</b> " + gioBatDau + " - " + gioKetThuc);
        JLabel lblScreen = createLabel("<b>Screen:</b> 3");

        // 🍿 Combo
        String comboText = comboDaChon.isEmpty() ? "None"
                : comboDaChon.stream()
                    .filter(c -> c.getSoLuong() > 0)
                    .map(c -> c.getTenCombo() + " x" + c.getSoLuong())
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("None");
        JLabel lblCombo = createLabel("<b>Combo:</b> " + comboText);

        JLabel lblTotal = createLabel("<b>Total:</b> " + df.format(tongTien));

        for (JLabel lbl : new JLabel[]{lblMovie, lblAddress, lblDate, lblTickets, lblSeats, lblTime, lblScreen, lblCombo, lblTotal}) {
            panel.add(Box.createVerticalStrut(5));
            panel.add(lbl);
        }

        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JLabel createLabel(String html) {
        JLabel lbl = new JLabel("<html>" + html + "</html>");
        lbl.setForeground(Color.WHITE);
        lbl.setFont(fontText);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }


    private JPanel createCustomerFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel lblTitle = new JLabel("THÔNG TIN KHÁCH HÀNG");
        lblTitle.setFont(fontTitle);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtName = createTextField("Tên");
        txtEmail = createTextField("Email");
        txtPhone = createTextField("Số điện thoại");

        JButton btnPayment = new JButton("Thanh toán");
        btnPayment.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPayment.setBackground(highlight);
        btnPayment.setForeground(Color.WHITE);
        btnPayment.setFont(new Font("Poppins", Font.BOLD, 16));
        btnPayment.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton btnBack = new JButton("←");
        btnBack.setFont(new Font("Poppins", Font.BOLD, 18));
        btnBack.setBackground(new Color(50, 50, 80));
        btnBack.setForeground(Color.WHITE);
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.addActionListener(e -> {
            if (onBack != null) onBack.run();
        });

        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(30));
        panel.add(txtName);
        panel.add(Box.createVerticalStrut(20));
        panel.add(txtEmail);
        panel.add(Box.createVerticalStrut(20));
        panel.add(txtPhone);
        panel.add(Box.createVerticalStrut(40));

        JPanel pnlButtons = new JPanel();
        pnlButtons.setOpaque(false);
        pnlButtons.add(btnBack);
        pnlButtons.add(btnPayment);
        panel.add(pnlButtons);

        return panel;
    }

    private JTextField createTextField(String placeholder) {
        JTextField txt = new JTextField();
        txt.setFont(new Font("Poppins", Font.PLAIN, 14));
        txt.setPreferredSize(new Dimension(300, 35));
        txt.setMaximumSize(new Dimension(400, 40));
        txt.setBackground(new Color(230, 230, 230));
        txt.setBorder(BorderFactory.createTitledBorder(placeholder));
        return txt;
    }
}
