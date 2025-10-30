package Entity;

import java.util.ArrayList;
import java.util.List;

public class DanhSachNhanVien {
	private List<NhanVien> dsNhanVien;

	public DanhSachNhanVien() {
		dsNhanVien = new ArrayList<NhanVien>();
	}

	public List<NhanVien> getDsNhanVien() {
		return dsNhanVien;
	}

	public void setDsNhanVien(List<NhanVien> dsNhanVien) {
		this.dsNhanVien = dsNhanVien;
	}

	public boolean xoaNhanVien(NhanVien nv) {
		if (!dsNhanVien.contains(nv))
			return false;
		dsNhanVien.remove(nv);
		return true;
	}

	public NhanVien timNhanVien(String tenNV) {
		for (NhanVien nv : dsNhanVien) {
			if (nv.getHoTen().equalsIgnoreCase(tenNV)) {
				return nv;
			}
		}
		return null;
	}

	public boolean capNhatNhanVien(NhanVien nvMoi) {
		NhanVien nvCu = timNhanVien(nvMoi.getMaNV());
		if (nvCu == null)
			return false;

		if (nvMoi.getHoTen() != null && !nvMoi.getHoTen().trim().isEmpty())
			nvCu.setHoTen(nvMoi.getHoTen());

		if (nvMoi.getSoDienThoai() != null && !nvMoi.getSoDienThoai().trim().isEmpty())
			nvCu.setSoDienThoai(nvMoi.getSoDienThoai());

		return true;
	}

	public int getSize() {
		return dsNhanVien.size();
	}

	public NhanVien getElement(int index) {
		if (index < 0 || index > dsNhanVien.size())
			return null;
		return dsNhanVien.get(index);
	}
}
