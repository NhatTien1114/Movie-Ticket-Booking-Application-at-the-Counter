package Entity;

public class ChiTietHoaDon {
	private Ve ve;
	private ComboDoAn combo;
	private int soLuong;

	public ChiTietHoaDon() {
		super();
	}

	public ChiTietHoaDon(Ve ve, ComboDoAn combo, int soLuong) {
		super();
		this.ve = ve;
		this.combo = combo;
		this.soLuong = soLuong;
	}

	public Ve getVe() {
		return ve;
	}

	public void setVe(Ve ve) {
		this.ve = ve;
	}

	public ComboDoAn getCombo() {
		return combo;
	}

	public void setCombo(ComboDoAn combo) {
		this.combo = combo;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double tinhThanhTien() {
		double tong = 0;

		if (ve != null)
			tong += ve.getGiaVe();

		if (combo != null)
			tong += combo.getGia() * soLuong;

		return tong;
	}

}
