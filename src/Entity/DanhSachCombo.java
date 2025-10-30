package Entity;

import java.util.ArrayList;
import java.util.List;

public class DanhSachCombo {
	private List<ComboDoAn> dsCombo;

	public DanhSachCombo() {
		dsCombo = new ArrayList<ComboDoAn>();
		khoiTaoDemo();
	}

	public List<ComboDoAn> getDsCombo() {
		return dsCombo;
	}

	public void setDsCombo(List<ComboDoAn> dsCombo) {
		this.dsCombo = dsCombo;
	}
	
	public boolean themCombo(ComboDoAn combo) {
		if (dsCombo.contains(combo)) {
			return false;
		}
		dsCombo.add(combo);
		return true;
	}
	
	public boolean xoaCombo(ComboDoAn combo) {
		if (!dsCombo.contains(combo))
			return false;
		dsCombo.remove(combo);
		return true;
	}
	
	public boolean capNhatCombo(ComboDoAn comboMoi) {
	    for (ComboDoAn comboCu : dsCombo) {
	        if (comboCu.getMaCombo().equalsIgnoreCase(comboMoi.getMaCombo())) {

	            if (comboMoi.getTenCombo() != null && !comboMoi.getTenCombo().trim().isEmpty()) {
	                comboCu.setTenCombo(comboMoi.getTenCombo());
	            }

	            if (comboMoi.getGia() > 0) { 
	                comboCu.setGia(comboMoi.getGia());
	            }

	            return true;
	        }
	    }
	    return false; 
	}
	
	public void khoiTaoDemo() {
		dsCombo.add(new ComboDoAn("CB01", "Bắp lớn", 100000, "src/Image/bắp lớn.png", 100));
		dsCombo.add(new ComboDoAn("CB02", "Coca", 40000, "src/Image/coca.png", 100));
	}

}
