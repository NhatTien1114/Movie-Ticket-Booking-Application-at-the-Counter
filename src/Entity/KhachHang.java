package Entity;

public class KhachHang {
	private String maKH;
	private int tuoiKH;
	private String tenKH;
	private String soDienThoaiKH;
	private String emailKH;

	public KhachHang(String maKH, int tuoiKH, String tenKH, String soDienThoaiKH, String emailKH) {
		super();
		this.maKH = maKH;
		this.tuoiKH = tuoiKH;
		this.tenKH = tenKH;
		this.soDienThoaiKH = soDienThoaiKH;
		this.emailKH = emailKH;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public int getTuoiKH() {
		return tuoiKH;
	}

	public void setTuoiKH(int tuoiKH) {
		this.tuoiKH = tuoiKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getSoDienThoaiKH() {
		return soDienThoaiKH;
	}

	public void setSoDienThoaiKH(String soDienThoaiKH) {
		this.soDienThoaiKH = soDienThoaiKH;
	}

	public String getEmailKH() {
		return emailKH;
	}

	public void setEmailKH(String emailKH) {
		this.emailKH = emailKH;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tuoiKH=" + tuoiKH + ", tenKH=" + tenKH + ", soDienThoaiKH="
				+ soDienThoaiKH + ", emailKH=" + emailKH + "]";
	}

}
