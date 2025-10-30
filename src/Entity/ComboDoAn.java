package Entity;

import java.util.Objects;

public class ComboDoAn {
	private String maCombo;
	private String tenCombo;
	private double gia;
	private String path;
	private int soLuong;

	public ComboDoAn(String maCombo, String tenCombo, double gia, String path, int soLuong) {
		super();
		this.maCombo = maCombo;
		this.tenCombo = tenCombo;
		this.gia = gia;
		this.path = path;
		this.soLuong = soLuong;
	}

	public ComboDoAn(String maCombo) {
		super();
		this.maCombo = maCombo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCombo, tenCombo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComboDoAn other = (ComboDoAn) obj;
		return Objects.equals(maCombo, other.maCombo) && Objects.equals(tenCombo, other.tenCombo);
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getMaCombo() {
		return maCombo;
	}

	public void setMaCombo(String maCombo) {
		this.maCombo = maCombo;
	}

	public String getTenCombo() {
		return tenCombo;
	}

	public void setTenCombo(String tenCombo) {
		this.tenCombo = tenCombo;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "ComboDoAn [maCombo=" + maCombo + ", tenCombo=" + tenCombo + ", gia=" + gia + ", path=" + path + "]";
	}

}
