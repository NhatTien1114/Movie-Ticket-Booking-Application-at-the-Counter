package Entity;

import java.util.ArrayList;
import java.util.List;

public class DanhSachTaiKhoan {
	private List<TaiKhoan> dsTaiKhoan;

	public DanhSachTaiKhoan() {
		dsTaiKhoan = new ArrayList<TaiKhoan>();
	}

	public List<TaiKhoan> getDsTaiKhoan() {
		return dsTaiKhoan;
	}

	public void setDsTaiKhoan(List<TaiKhoan> dsTaiKhoan) {
		this.dsTaiKhoan = dsTaiKhoan;
	}

	public boolean dangKy(TaiKhoan tkMoi) {
		for (TaiKhoan tk : dsTaiKhoan) {
			if (tk.getTenDangNhap().equalsIgnoreCase(tkMoi.getTenDangNhap())) {
				return false;
			}
			if (tk.getEmail().equalsIgnoreCase(tkMoi.getEmail())) {
				return false;
			}
		}

		dsTaiKhoan.add(tkMoi);
		return true;
	}
	
	public boolean dangNhap(String tenDangNhap, String matKhau) {
        for (TaiKhoan tk : dsTaiKhoan) {
            if (tk.getTenDangNhap().equalsIgnoreCase(tenDangNhap)
                    && tk.getMatKhau().equals(matKhau)) {
                return true;
            }
        }
        return false;
    }
	
	public boolean doiMatKhau(String tenDangNhap, String matKhauCu, String matKhauMoi) {
        for (TaiKhoan tk : dsTaiKhoan) {
            if (tk.getTenDangNhap().equalsIgnoreCase(tenDangNhap)
                    && tk.getMatKhau().equals(matKhauCu)) {
                tk.setMatKhau(matKhauMoi);
                return true;
            }
        }
        return false;
    }
}
