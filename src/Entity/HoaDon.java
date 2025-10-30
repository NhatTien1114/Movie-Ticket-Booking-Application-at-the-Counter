package Entity;

import java.time.LocalDateTime;
import java.util.List;

public class HoaDon {
	private String maHoaDon;
	private LocalDateTime ngayLap;
	private KhachHang khachHang;
	private List<ChiTietHoaDon> chiTiet;
	private double maGiamGia;
	private boolean trangThaiThanhToan;

	public HoaDon(String maHoaDon, LocalDateTime ngayLap, KhachHang khachHang, List<ChiTietHoaDon> chiTiet,
			double maGiamGia, boolean trangThaiThanhToan) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayLap = ngayLap;
		this.khachHang = khachHang;
		this.chiTiet = chiTiet;
		this.maGiamGia = maGiamGia;
		this.trangThaiThanhToan = trangThaiThanhToan;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public LocalDateTime getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(LocalDateTime ngayLap) {
		this.ngayLap = ngayLap;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public List<ChiTietHoaDon> getChiTiet() {
		return chiTiet;
	}

	public void setChiTiet(List<ChiTietHoaDon> chiTiet) {
		this.chiTiet = chiTiet;
	}

	public double getMaGiamGia() {
		return maGiamGia;
	}

	public void setMaGiamGia(double maGiamGia) {
		this.maGiamGia = maGiamGia;
	}

	public boolean isTrangThaiThanhToan() {
		return trangThaiThanhToan;
	}

	public void setTrangThaiThanhToan(boolean trangThaiThanhToan) {
		this.trangThaiThanhToan = trangThaiThanhToan;
	}
	
	public double tinhTongTien() {
	    double tong = 0;

	    if (chiTiet != null) {
	        for (ChiTietHoaDon cthd : chiTiet) {
	            tong += cthd.tinhThanhTien();
	        }
	    }

	    if (maGiamGia > 0 && maGiamGia <= 100) {
	        tong -= tong * (maGiamGia / 100);
	    }

	    return tong;
	}

}
