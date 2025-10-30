package Entity;

import java.util.Objects;

public class Phim {
	private String maPhim;
	private String tenPhim;
	private TheLoai theLoai;
	private int thoiLuong;
	private String daoDien;
	private boolean trangThai;
	private String moTa;
	private String duongDanAnh;

	public enum TheLoai {
		HANH_DONG("Hành Động"), HAI_KICH("Hài Kịch"), TINH_CAM("Tình Cảm"), KINH_DI("Kinh Dị");

		private String ten;

		TheLoai(String ten) {
			this.ten = ten;
		}

		public String getTen() {
			return ten;
		}

		public void setTen(String ten) {
			this.ten = ten;
		}

		public String toString() {
			return ten;
		}

		public static TheLoai fromString(String text) {
			for (TheLoai tl : TheLoai.values()) {
				if (tl.ten.equalsIgnoreCase(text.trim()) || tl.name().equalsIgnoreCase(text.trim())) {
					return tl;
				}
			}
			return null;
		}
	}

	public Phim(String maPhim, String tenPhim, TheLoai theLoai, int thoiLuong, String daoDien, boolean trangThai,
			String moTa, String duongDanAnh) {
		super();
		this.maPhim = maPhim;
		this.tenPhim = tenPhim;
		this.theLoai = theLoai;
		this.thoiLuong = thoiLuong;
		this.daoDien = daoDien;
		this.trangThai = trangThai;
		this.moTa = moTa;
		this.duongDanAnh = duongDanAnh;
	}

	public String getMoTa() {
		return moTa;
	}

	public Phim(String maPhim) {
		super();
		this.maPhim = maPhim;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getDuongDanAnh() {
		return duongDanAnh;
	}

	public void setDuongDanAnh(String duongDanAnh) {
		this.duongDanAnh = duongDanAnh;
	}

	public String getMaPhim() {
		return maPhim;
	}

	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}

	public String getTenPhim() {
		return tenPhim;
	}

	public void setTenPhim(String tenPhim) {
		this.tenPhim = tenPhim;
	}

	public TheLoai getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(TheLoai theLoai) {
		this.theLoai = theLoai;
	}

	public int getThoiLuong() {
		return thoiLuong;
	}

	public void setThoiLuong(int thoiLuong) {
		this.thoiLuong = thoiLuong;
	}

	public String getDaoDien() {
		return daoDien;
	}

	public void setDaoDien(String daoDien) {
		this.daoDien = daoDien;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "Phim [maPhim=" + maPhim + ", tenPhim=" + tenPhim + ", theLoai=" + theLoai + ", thoiLuong=" + thoiLuong
				+ ", daoDien=" + daoDien + ", trangThai=" + trangThai + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhim, tenPhim);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phim other = (Phim) obj;
		return Objects.equals(maPhim, other.maPhim) && Objects.equals(tenPhim, other.tenPhim);
	}

}
