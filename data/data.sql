CREATE DATABASE QLBanVe;
GO
USE QLBanVe;
GO
CREATE TABLE NhanVien (
    maNV VARCHAR(20) PRIMARY KEY,
    hoTen NVARCHAR(100)
)

CREATE TABLE KhachHang (
    maKH VARCHAR(20) PRIMARY KEY,
    tuoiKH INT,
    tenKH VARCHAR(100),
    sdt VARCHAR(12),
    email VARCHAR(100)

)

CREATE TABLE TaiKhoan (
    tenDangNhap VARCHAR(50) PRIMARY KEY,
    matKhau VARCHAR(100),
    email VARCHAR(100),
    soDienThoai VARCHAR(20),
    maNV VARCHAR(20),
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV)
)

CREATE TABLE Phim (
    maPhim VARCHAR(20) PRIMARY KEY,
    tenPhim NVARCHAR(100),
    theLoai NVARCHAR(20),
    thoiLuong INT,
    daoDien VARCHAR(100),
    trangThai BIT
)

ALTER TABLE Phim
ADD moTa NVARCHAR(1000);

ALTER TABLE Phim
ADD duongDanAnh NVARCHAR(500);

CREATE TABLE PhongChieu (
    maPhong VARCHAR(20) PRIMARY KEY,
    trangThai BIT,
)

CREATE TABLE Ghe (
    maGhe VARCHAR(20) PRIMARY KEY,
    loaiGhe VARCHAR(10),        -- Enum {THUONG, VIP, DOI}
    tinhTrang BIT,
    maPhong VARCHAR(20),
    FOREIGN KEY (maPhong) REFERENCES PhongChieu(maPhong)
)

CREATE TABLE SuatChieu (
    maSuatChieu VARCHAR(20) PRIMARY KEY,
    ngayGio DATETIME,
    maPhong VARCHAR(20),
    maPhim VARCHAR(20),
    FOREIGN KEY (maPhong) REFERENCES PhongChieu(maPhong),
    FOREIGN KEY (maPhim) REFERENCES Phim(maPhim)
)

CREATE TABLE Ve (
    maVe VARCHAR(20) PRIMARY KEY,
    giaVe float,
    trangThaiSuDung BIT,
    maGhe VARCHAR(20),
    maSuatChieu VARCHAR(20),
    FOREIGN KEY (maGhe) REFERENCES Ghe(maGhe),
    FOREIGN KEY (maSuatChieu) REFERENCES SuatChieu(maSuatChieu)
)

CREATE TABLE ComboDoAn (
    maCombo VARCHAR(20) PRIMARY KEY,
    tenCombo NVARCHAR(100),
    gia FLOAT,
    moTa NVARCHAR(255)
)

-- 1. Bỏ cột moTa
ALTER TABLE ComboDoAn
DROP COLUMN moTa;

-- 2. Thêm cột path
ALTER TABLE ComboDoAn
ADD path NVARCHAR(255);

-- 3. Thêm cột soLuong
ALTER TABLE ComboDoAn
ADD soLuong INT;

CREATE TABLE HoaDon (
    maHoaDon VARCHAR(20) PRIMARY KEY,
    ngayLap DATETIME,
    maKH VARCHAR(20),
    maGiamGia float,
    trangThaiThanhToan BIT,
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH)
)

ALTER TABLE HoaDon
DROP COLUMN maGiamGia;

ALTER TABLE HoaDon
DROP COLUMN trangThaiThanhToan;

CREATE TABLE ChiTietHoaDon (
    maVe VARCHAR(20),
    maCombo VARCHAR(20),
    soLuong INT,
    maHoaDon VARCHAR(20),
    PRIMARY KEY (maHoaDon, maVe, maCombo),
    FOREIGN KEY (maHoaDon) REFERENCES HoaDon(maHoaDon),
    FOREIGN KEY (maVe) REFERENCES Ve(maVe),
    FOREIGN KEY (maCombo) REFERENCES ComboDoAn(maCombo)
)