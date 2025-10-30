package Entity;

import java.util.Objects;

public class NhanVien extends TaiKhoan {
	private String maNV;
	private String hoTen;

	public NhanVien(String tenDangNhap, String matKhau, String email, String soDienThoai, String maNV, String hoTen) {
		super(tenDangNhap, matKhau, email, soDienThoai);
		this.maNV = maNV;
		this.hoTen = hoTen;
	}

	public NhanVien(String maNV, String hoTen) {
		super(null, null, null, null);
		this.maNV = maNV;
		this.hoTen = hoTen;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hoTen);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(hoTen, other.hoTen);
	}

}
