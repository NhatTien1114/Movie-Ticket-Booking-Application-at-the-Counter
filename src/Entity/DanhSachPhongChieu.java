package Entity;

import java.util.ArrayList;
import java.util.List;


public class DanhSachPhongChieu {
	private List<PhongChieu> dsPhong;

	public DanhSachPhongChieu() {
		dsPhong = new ArrayList<PhongChieu>();
		khoiTaoDuLieuDemo();
	}

	public List<PhongChieu> getDsPhong() {
		return dsPhong;
	}

	public void setDsPhong(List<PhongChieu> dsPhong) {
		this.dsPhong = dsPhong;
	}
	
	public boolean themPhong(PhongChieu pc) {
		if (dsPhong.contains(pc)) {
			return false;
		}
		dsPhong.add(pc);
		return true;
	}
	
	public boolean xoaPhong(PhongChieu pc) {
		if (!dsPhong.contains(pc)) {
			return false;
		}
		dsPhong.remove(pc);
		return true;
	}
	
	private void khoiTaoDuLieuDemo() {
		List<Ghe> ghePC1 = PhongChieu.taoDanhSachGhe(new char[] {'A', 'B', 'C', 'D', 'E'}, 10);
		List<Ghe> ghePC2 = PhongChieu.taoDanhSachGhe(new char[] {'A', 'B', 'C', 'D', 'E', 'F'}, 12);
		List<Ghe> ghePC3 = PhongChieu.taoDanhSachGhe(new char[] {'A', 'B', 'C', 'D'}, 8);

		dsPhong.add(new PhongChieu("PC01", ghePC1));
		dsPhong.add(new PhongChieu("PC02", ghePC2));
		dsPhong.add(new PhongChieu("PC03", ghePC3));
	}
	
	public PhongChieu getPhongTheoChiSo(int index) {
		if (index < 0 || index >= dsPhong.size()) return null;
		return dsPhong.get(index);
	}
}
