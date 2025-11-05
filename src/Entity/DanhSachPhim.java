package Entity;

import java.util.ArrayList;
import java.util.List;
import dao.Phim_DAO;

public class DanhSachPhim {
	private List<Phim> dsPhim;
	private Phim_DAO phim_dao;

	public DanhSachPhim() {
		phim_dao = new Phim_DAO(); 
		dsPhim = phim_dao.getAllPhim(); 

		if (dsPhim == null || dsPhim.isEmpty()) {
			System.out.println("--- CSDL Phim đang trống. Khởi tạo dữ liệu demo...");
			
			dsPhim = new ArrayList<Phim>();
			
			khoiTaoDuLieuDemo(); 
			
			for (Phim p : dsPhim) {
				phim_dao.insert(p);
			}
			System.out.println("--- Đã thêm " + dsPhim.size() + " phim demo vào CSDL.");
			
		} else {
			System.out.println("--- Đã tải thành công " + dsPhim.size() + " phim từ CSDL.");
		}
	}

	public List<Phim> getDsPhim() {
		return dsPhim;
	}

	public void setDsPhim(List<Phim> dsPhim) {
		this.dsPhim = dsPhim;
	}
	
	public boolean themPhim(Phim phim) {
		if (dsPhim.contains(phim)) {
			return false;
		}
		if (phim_dao.insert(phim)) { 
			dsPhim.add(phim); 
			return true;
		}
		return false;
	}
	
	public boolean xoaPhim(Phim phim) {
		if (!dsPhim.contains(phim))
			return false;
		
		if (phim_dao.delete(phim.getMaPhim())) { 
			dsPhim.remove(phim);
			return true;
		}
		return false;
	}
	
	
	
	public boolean capNhatPhim(Phim phimMoi) {
	    if (phim_dao.update(phimMoi)) { 
			for (Phim phimCu : dsPhim) {
				if (phimCu.getMaPhim().equalsIgnoreCase(phimMoi.getMaPhim())) {

					if (phimMoi.getTenPhim() != null && !phimMoi.getTenPhim().trim().isEmpty()) {
						phimCu.setTenPhim(phimMoi.getTenPhim());
					}
					if (phimMoi.getTheLoai() != null) {
						phimCu.setTheLoai(phimMoi.getTheLoai());
					}
					if (phimMoi.getThoiLuong() > 0) {
						phimCu.setThoiLuong(phimMoi.getThoiLuong());
					}
					if (phimMoi.getDaoDien() != null && !phimMoi.getDaoDien().trim().isEmpty()) {
						phimCu.setDaoDien(phimMoi.getDaoDien());
					}
					phimCu.setTrangThai(phimMoi.isTrangThai());
					phimCu.setMoTa(phimMoi.getMoTa()); 
					phimCu.setDuongDanAnh(phimMoi.getDuongDanAnh());

					return true; 
				}
			}
		}
	    return false; 
	}
	
	public Phim timPhimTheoTen(String tenPhim) {
	    for (Phim phim : dsPhim) {
	        if (phim.getTenPhim().equalsIgnoreCase(tenPhim)) {
	            return phim; 
	        }
	    }
	    return null;
	}
	
	public List<Phim> timPhimTheoTenGanDung(String tuKhoa) {
	    List<Phim> ketQua = new ArrayList<>();
	    for (Phim phim : dsPhim) {
	        if (phim.getTenPhim().toLowerCase().contains(tuKhoa.toLowerCase())) {
	            ketQua.add(phim);
	        }
	    }
	    return ketQua;
	}

	
	public Phim timPhimTheoMa(String maPhim) {
	    for (Phim phim : dsPhim) {
	        if (phim.getMaPhim().equalsIgnoreCase(maPhim)) {
	            return phim;
	        }
	    }
	    return null;
	}
	
	public int getSize() {
		return dsPhim.size();
	}
	
	public Phim getElement(int index) {
		if (index < 0 || index > dsPhim.size())
			return null;
		return dsPhim.get(index);
	}
	
	private void khoiTaoDuLieuDemo() {
        dsPhim.add(new Phim("P001", "The Conjuring", Phim.TheLoai.KINH_DI, 120, "Goten Yuzuru", true,
                "The Conjuring là một bộ phim kinh dị siêu nhiên của Mỹ, xoay quanh các nhà điều tra hiện tượng siêu nhiên Ed và Lorraine Warren.", "src/Image/anh1.jpg"));
        dsPhim.add(new Phim("P002", "Kimetsu no Yaiba", Phim.TheLoai.HANH_DONG, 115, "Nhật Tiến", true,
                "Một bộ phim khoa học viễn tưởng với hiệu ứng mãn nhãn và cốt truyện hấp dẫn về cuộc chiến giữa các hành tinh.",
                "src/Image/anh2.jpg"));
        dsPhim.add(new Phim("P003", "Elio", Phim.TheLoai.TINH_CAM, 98, "Trần Văn", true,
                "Một bộ phim hài hước, xúc động về tình mẫu tử và những hiểu lầm trong gia đình.", "src/Image/anh3.jpg"));
        dsPhim.add(new Phim("P004", "The Smashing Machine", Phim.TheLoai.HANH_DONG, 130, "Lê Hùng", false,
                "Phim hành động kịch tính về một nữ sát thủ giải nghệ, phải chiến đấu để bảo vệ những người thân yêu.", "src/Image/anh4.jpg"));
        dsPhim.add(new Phim("P005", "The Strangers", Phim.TheLoai.HAI_KICH, 105, "Phạm Quyên", true,
                "Một chuyện tình lãng mạn đầy nước mắt dưới những cơn mưa. Phim mang lại nhiều tiếng cười và cả sự suy ngẫm.", "src/Image/anh5.jpg"));
        dsPhim.add(new Phim("P006", "After the Hunt", Phim.TheLoai.KINH_DI, 90, "Anna Smith", false,
                "Câu chuyện về quái vật huyền thoại ẩn mình dưới hồ sâu, gieo rắc nỗi sợ hãi cho thị trấn ven hồ.", "src/Image/anh6.jpg"));
        dsPhim.add(new Phim("P007", "Fantastic Four", Phim.TheLoai.KINH_DI, 120, "Goten Yuzuru", true,
                "The Conjuring là một bộ phim kinh dị siêu nhiên của Mỹ, xoay quanh các nhà điều tra hiện tượng siêu nhiên Ed và Lorraine Warren.", "src/Image/anh7.jpeg"));
        dsPhim.add(new Phim("P008", "Superman", Phim.TheLoai.HANH_DONG, 115, "Nhật Tiến", true,
                "Một bộ phim khoa học viễn tưởng với hiệu ứng mãn nhãn và cốt truyện hấp dẫn về cuộc chiến giữa các hành tinh.",
                "src/Image/anh8.jpg"));
        dsPhim.add(new Phim("P009", "Jurassic World", Phim.TheLoai.TINH_CAM, 98, "Trần Văn", true,
                "Một bộ phim hài hước, xúc động về tình mẫu tử và những hiểu lầm trong gia đình.", "src/Image/anh9.jpg"));
        dsPhim.add(new Phim("P010", "28 Years Later", Phim.TheLoai.HANH_DONG, 130, "Lê Hùng", false,
                "Phim hành động kịch tính về một nữ sát thủ giải nghệ, phải chiến đấu để bảo vệ những người thân yêu.", "src/Image/anh10.jpg"));
        dsPhim.add(new Phim("P011", "Karate Kid Legends", Phim.TheLoai.HAI_KICH, 105, "Phạm Quyên", true,
                "Một chuyện tình lãng mạn đầy nước mắt dưới những cơn mưa. Phim mang lại nhiều tiếng cười và cả sự suy ngẫm.", "src/Image/anh11.jpg"));
        dsPhim.add(new Phim("P012", "Minecraft Movie", Phim.TheLoai.KINH_DI, 90, "Anna Smith", false,
                "Câu chuyện về quái vật huyền thoại ẩn mình dưới hồ sâu, gieo rắc nỗi sợ hãi cho thị trấn ven hồ.", "src/Image/anh12.jpg"));
        dsPhim.add(new Phim("P013", "Five Nights at Freddy's", Phim.TheLoai.KINH_DI, 120, "Goten Yuzuru", true,
                "The Conjuring là một bộ phim kinh dị siêu nhiên của Mỹ, xoay quanh các nhà điều tra hiện tượng siêu nhiên Ed và Lorraine Warren.", "src/Image/anh13.jpg"));
        dsPhim.add(new Phim("P014", "Detective Conan", Phim.TheLoai.HANH_DONG, 115, "Nhật Tiến", true,
                "Một bộ phim khoa học viễn tưởng với hiệu ứng mãn nhãn và cốt truyện hấp dẫn về cuộc chiến giữa các hành tinh.",
                "src/Image/anh14.jpg"));
        dsPhim.add(new Phim("P015", "Zootopia", Phim.TheLoai.TINH_CAM, 98, "Trần Văn", true,
                "Một bộ phim hài hước, xúc động về tình mẫu tử và những hiểu lầm trong gia đình.", "src/Image/anh15.jpg"));
        dsPhim.add(new Phim("P016", "Lilo & Stitch", Phim.TheLoai.HANH_DONG, 130, "Lê Hùng", false,
                "Phim hành động kịch tính về một nữ sát thủ giải nghệ, phải chiến đấu để bảo vệ những người thân yêu.", "src/Image/anh16.jpg"));
        dsPhim.add(new Phim("P017", "Final Destination", Phim.TheLoai.HAI_KICH, 105, "Phạm Quyên", true,
                "Một chuyện tình lãng mạn đầy nước mắt dưới những cơn mưa. Phim mang lại nhiều tiếng cười và cả sự suy ngẫm.", "src/Image/anh17.jpg"));
        dsPhim.add(new Phim("P018", "Thunderbolts", Phim.TheLoai.KINH_DI, 90, "Anna Smith", false,
                "Câu chuyện về quái vật huyền thoại ẩn mình dưới hồ sâu, gieo rắc nỗi sợ hãi cho thị trấn ven hồ.", "src/Image/anh18.jpg"));
    }
}