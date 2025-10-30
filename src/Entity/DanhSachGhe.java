package Entity;

import java.util.List;

public class DanhSachGhe {
	private List<Ghe> dsGhe;

	public DanhSachGhe(PhongChieu phongChieu) {
		dsGhe = phongChieu.getDanhSachGhe();
	}

	public List<Ghe> getDsGhe() {
		return dsGhe;
	}

	public void setDsGhe(List<Ghe> dsGhe) {
		this.dsGhe = dsGhe;
	}

}
