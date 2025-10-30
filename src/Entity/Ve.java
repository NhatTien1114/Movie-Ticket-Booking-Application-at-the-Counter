package Entity;

public class Ve {
	private String maVe;
	private double giaVe;
	private boolean trangThaiSuDung;
	private Ghe ghe;
	private SuatChieu suatChieu;

	public Ve(String maVe, double giaVe, boolean trangThaiSuDung, Ghe ghe, SuatChieu suatChieu) {
		super();
		this.maVe = maVe;
		this.giaVe = giaVe;
		this.trangThaiSuDung = trangThaiSuDung;
		this.ghe = ghe;
		this.suatChieu = suatChieu;
	}

	public String getMaVe() {
		return maVe;
	}

	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}

	public double getGiaVe() {
		return giaVe;
	}

	public void setGiaVe(double giaVe) {
		this.giaVe = giaVe;
	}

	public boolean isTrangThaiSuDung() {
		return trangThaiSuDung;
	}

	public void setTrangThaiSuDung(boolean trangThaiSuDung) {
		this.trangThaiSuDung = trangThaiSuDung;
	}

	public Ghe getGhe() {
		return ghe;
	}

	public void setGhe(Ghe ghe) {
		this.ghe = ghe;
	}

	public SuatChieu getSuatChieu() {
		return suatChieu;
	}

	public void setSuatChieu(SuatChieu suatChieu) {
		this.suatChieu = suatChieu;
	}

	@Override
	public String toString() {
		return "Ve [maVe=" + maVe + ", giaVe=" + giaVe + ", trangThaiSuDung=" + trangThaiSuDung + ", ghe=" + ghe
				+ ", suatChieu=" + suatChieu + "]";
	}

}
