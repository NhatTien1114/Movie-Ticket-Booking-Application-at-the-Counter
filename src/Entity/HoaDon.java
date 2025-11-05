package Entity;

import java.time.LocalDateTime;
import java.util.List;

public class HoaDon {
	private String maHoaDon;
	private LocalDateTime ngayLap;
	private List<ChiTietHoaDon> chiTiet;
	private KhachHang khachHang;

	public HoaDon() {
	}

	public HoaDon(String maHoaDon, LocalDateTime ngayLap, List<ChiTietHoaDon> chiTiet, KhachHang khachHang) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayLap = ngayLap;
		this.chiTiet = chiTiet;
		this.khachHang = khachHang;
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

	public List<ChiTietHoaDon> getChiTiet() {
		return chiTiet;
	}

	public void setChiTiet(List<ChiTietHoaDon> chiTiet) {
		this.chiTiet = chiTiet;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public double tinhTongTien() {
		double tong = 0;

		if (chiTiet != null) {
			for (ChiTietHoaDon cthd : chiTiet) {
				tong += cthd.tinhThanhTien();
			}
		}

		return tong;
	}

}
