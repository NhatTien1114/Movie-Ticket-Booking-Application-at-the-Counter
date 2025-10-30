package Entity;

import java.util.ArrayList;
import java.util.List;

public class PhongChieu {
	private String maPhong;
	private boolean trangThai;
	private List<Ghe> danhSachGhe;

	public PhongChieu(String maPhong, boolean trangThai, List<Ghe> danhSachGhe) {
		this.maPhong = maPhong;
		this.trangThai = trangThai;
		this.danhSachGhe = danhSachGhe != null ? danhSachGhe : new ArrayList<>();
	}

	public static List<Ghe> taoDanhSachGhe(char[] hangGhe, int soGheMoiHang) {
		List<Ghe> ds = new ArrayList<>();
		for (char hang : hangGhe) {
			for (int so = 1; so <= soGheMoiHang; so++) {
				String maGhe = "" + hang + so;
				Ghe.LoaiGhe loai;

				if (hang == 'A' || hang == 'B')
					loai = Ghe.LoaiGhe.THUONG;
				else if (hang == 'C' || hang == 'D')
					loai = Ghe.LoaiGhe.VIP;
				else
					loai = Ghe.LoaiGhe.DOI;

				ds.add(new Ghe(maGhe, loai, false));
			}
		}
		return ds;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public List<Ghe> getDanhSachGhe() {
		return danhSachGhe;
	}

	public void setDanhSachGhe(List<Ghe> danhSachGhe) {
		this.danhSachGhe = danhSachGhe;
	}

	@Override
	public String toString() {
		return "PhongChieu [maPhong=" + maPhong + ", trangThai=" + trangThai + ", danhSachGhe=" + danhSachGhe + "]";
	}

}
