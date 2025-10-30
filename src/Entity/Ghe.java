package Entity;

public class Ghe {
	private String maGhe;
	private LoaiGhe loaiGhe;
	private boolean tinhTrang;

	public enum LoaiGhe {
		THUONG("Ghế Thường"), VIP("Ghế Vip"), DOI("Ghế Đôi");

		private String ten;

		LoaiGhe(String ten) {
			this.ten = ten;
		}

		public String getTen() {
			return ten;
		}

		public void setTen(String ten) {
			this.ten = ten;
		}

		public String toString() {
			return ten;
		}
	}

	public Ghe(String maGhe, LoaiGhe loaiGhe, boolean tinhTrang) {
		super();
		this.maGhe = maGhe;
		this.loaiGhe = loaiGhe;
		this.tinhTrang = tinhTrang;
	}

	public String getMaGhe() {
		return maGhe;
	}

	public void setMaGhe(String maGhe) {
		this.maGhe = maGhe;
	}

	public LoaiGhe getLoaiGhe() {
		return loaiGhe;
	}

	public void setLoaiGhe(LoaiGhe loaiGhe) {
		this.loaiGhe = loaiGhe;
	}

	public boolean isTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	@Override
	public String toString() {
		return "Ghe [maGhe=" + maGhe + ", loaiGhe=" + loaiGhe + ", tinhTrang=" + tinhTrang + "]";
	}

}
