package Entity;

import java.time.LocalDateTime;

public class SuatChieu {
	private String maSuatChieu;
	private LocalDateTime ngayGio;
	private Phim phim;
	private PhongChieu phongChieu;

	public SuatChieu(String maSuatChieu, LocalDateTime ngayGio, Phim phim, PhongChieu phongChieu) {
		super();
		this.maSuatChieu = maSuatChieu;
		this.ngayGio = ngayGio;
		this.phim = phim;
		this.phongChieu = phongChieu;
	}

	public String getMaSuatChieu() {
		return maSuatChieu;
	}

	public void setMaSuatChieu(String maSuatChieu) {
		this.maSuatChieu = maSuatChieu;
	}

	public LocalDateTime getNgayGio() {
		return ngayGio;
	}

	public void setNgayGio(LocalDateTime ngayGio) {
		this.ngayGio = ngayGio;
	}

	public Phim getPhim() {
		return phim;
	}

	public void setPhim(Phim phim) {
		this.phim = phim;
	}

	public PhongChieu getPhongChieu() {
		return phongChieu;
	}

	public void setPhongChieu(PhongChieu phongChieu) {
		this.phongChieu = phongChieu;
	}

	@Override
	public String toString() {
		return "SuatChieu [maSuatChieu=" + maSuatChieu + ", ngayGio=" + ngayGio + ", phim=" + phim + ", phongChieu="
				+ phongChieu + "]";
	}

}
