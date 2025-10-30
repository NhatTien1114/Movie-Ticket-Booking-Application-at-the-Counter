package Entity;

public class TaiKhoan {
	private String tenDangNhap;
	private String matKhau;
	private String email;
	private String soDienThoai;

	public TaiKhoan(String tenDangNhap, String matKhau, String email, String soDienThoai) {
		super();
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.email = email;
		this.soDienThoai = soDienThoai;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

}
