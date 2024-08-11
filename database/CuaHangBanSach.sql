
create database QLHS
go
USE QLHS


SELECT GETDATE()
set dateformat dmy
--Tạo bảng Nhân viên
CREATE TABLE NhanVien
(
	MaNV NVARCHAR(6) PRIMARY KEY NOT NULL check(MaNV like 'NV%'),
	TenNV NVARCHAR(100) NOT NULL,
	SDT NVARCHAR(10) NOT NULL CHECK (LEN(SDT) = 10 AND SDT like '0%'),
	DiaCHi NVARCHAR(100) NOT NULL,
	HeSoLuong FLOAT NOT NULL check(HeSoLuong >0) ,
	--GioiTinh NVARCHAR(100) NOT NULL check(GioiTinh = N'Nam' or GioiTinh = N'Nữ'),
	GioiTinh Bit NOT NULL,
	NgaySinh DATETIME NOT NULL CHECK( YEAR(GETDATE())-YEAR(NgaySinh)>=18),
	NgayVaoLam DATETIME NOT NULL DEFAULT GETDATE()
	
)

--Tạo bảng khách hàng
CREATE TABLE KhachHang
(
	MaKH NVARCHAR(6) PRIMARY KEY NOT NULL check(MaKH like 'KH%') ,
	TenKH NVARCHAR(100) NOT NULL,
	SDT NVARCHAR(10) NOT NULL CHECK (LEN(SDT) = 10 AND SDT like '0%'),
	DiaCHi NVARCHAR(100) NOT NULL,
	GioiTinh Bit NOT NULL
)

--Tạo bảng Tác giả
CREATE TABLE TacGia
(
	MaTG NVARCHAR(6) PRIMARY KEY NOT NULL check(MaTG like 'TG%'),
	TenTG NVARCHAR(100) NOT NULL
)
--Tạo bảng Nhà xuất bản
CREATE TABLE NhaXuatBan
(
	MaNXB NVARCHAR(6) PRIMARY KEY NOT NULL check(MaNXB like 'XB%'),
	TenNXB NVARCHAR(100) NOT NULL
)
--Tạo bảng Thể loại sách
CREATE TABLE TheLoai
(
	MaTheLoai NVARCHAR(6) PRIMARY KEY NOT NULL check(MaTheLoai like 'TL%'),
	TenTheLoai NVARCHAR(100) NOT NULL
)
--Tạo bảng Sách
CREATE TABLE Sach
(
	MaSach NVARCHAR(6) PRIMARY KEY NOT NULL check(MaSach like 'SP%'),
	TenSach NVARCHAR(100) NOT NULL,
	SoLuong int NOT NULL check (SoLuong >=0),
	DonGia MONEY NOT NULL check (DonGia>=0),
	MaTG NVARCHAR(6) NOT NULL,
	MaNXB NVARCHAR(6) NOT NULL,
	MaTheLoai NVARCHAR(6) NOT NULL
)

--Tạo bảng hóa đơn
CREATE TABLE HoaDon
(
	MaHoaDon NVARCHAR(6) PRIMARY KEY NOT NULL check(MaHoaDon like 'HD%'),
	MaNV NVARCHAR(6) NOT NULL,
	MaKH NVARCHAR(6) NOT NULL,
	TenHoaDon NVARCHAR(100) NOT NULL,
	NgayXuatHD DATETIME

)

--Tạo bảng Chi Tiết Hóa Đơn
CREATE TABLE ChiTietHoaDon
(
	MaHoaDon NVARCHAR(6) NOT NULL,
	MaSach NVARCHAR(6) NOT NULL,
	SoLuong int,
	DonGia MONEY
)

--Tạo bảng Phiếu đặt hàng
CREATE TABLE PhieuDatHang
(	
	MaPhieuDatHang NVARCHAR(6) PRIMARY KEY NOT NULL check(MaPhieuDatHang like 'DH%'),
	MaKH NVARCHAR(6) NOT NULL,
	NgayDatHang DATETIME
)

-- Tạo bảng Chi tiết phiếu đặt hàng
CREATE TABLE ChiTietPhieuDatHang
(
	MaPhieuDatHang NVARCHAR(6) NOT NULL,
	MaSach NVARCHAR(6) NOT NULL ,
	SoLuong int NOT NULL check (SoLuong >0),
	DonGia Money
)

--Ràng buộc khóa ngoại
ALTER TABLE Sach 
	ADD CONSTRAINT FK_MATG FOREIGN KEY(MaTG)REFERENCES TacGia(MaTG)
ALTER TABLE Sach 
	ADD CONSTRAINT FK_MANXB FOREIGN KEY(MaNXB)REFERENCES NhaXuatBan(MaNXB)

ALTER TABLE Sach 
	ADD CONSTRAINT FK_MATL FOREIGN KEY(MaTheLoai)REFERENCES TheLoai(MaTheLoai)

ALTER TABLE HoaDon 
	ADD CONSTRAINT FK_MANV FOREIGN KEY(MaNV)REFERENCES NhanVien(MaNV)

ALTER TABLE HoaDon
	ADD CONSTRAINT FK_MAKH FOREIGN KEY(MaKH)REFERENCES KhachHang(MaKH)

ALTER TABLE ChiTietHoaDon 
	ADD CONSTRAINT FK_MAHD FOREIGN KEY(MaHoaDon)REFERENCES HoaDon(MaHoaDon)
	
ALTER TABLE ChiTietHoaDon
	ADD CONSTRAINT FK_MaSach FOREIGN KEY(MaSach) REFERENCES Sach(MaSach)

ALTER TABLE PhieuDatHang
	ADD CONSTRAINT FK_MAKHDH FOREIGN KEY(MaKH)REFERENCES KhachHang(MaKH)

ALTER TABLE ChiTietPhieuDatHang
	ADD CONSTRAINT FK_MaSPDH FOREIGN KEY (MaSach) REFERENCES Sach(MaSach)

ALTER TABLE ChiTietPhieuDatHang
	ADD CONSTRAINT FK_MaPDH FOREIGN KEY (MaPhieuDatHang) REFERENCES PhieuDatHang(MaPhieuDatHang)

--Khóa chính
ALTER TABLE ChiTietPhieuDatHang
	ADD CONSTRAINT PK_MaPDH_MaSach PRIMARY KEY (MaPhieuDatHang,MaSach)		

ALTER TABLE ChiTietHoaDon
	ADD CONSTRAINT PK_MaHD_MaSach PRIMARY KEY (MaHoaDon,MaSach)	


---Tạo dữ liệu các bảng

--Bảng Nhân viên
INSERT NhanVien VALUES (N'NV0001', N'Nguyễn Văn Hai',N'0987654321',N'Tân Bình', 1.8,0,'12/02/2002','02-03-2023')
INSERT NhanVien VALUES (N'NV0002', N'Nguyễn Thị Ba',N'0123456789',N'Gò Vấp', 1.6,1,'15/01/2002','02-01-2023')

select * from NhanVien

--Bảng Khách hàng
INSERT INTO KhachHang(MaKH, TenKH, SDT, DiaCHi, GioiTinh) VALUES (N'KH0001', N'Lê Văn Một', '0987654321',N'Tân Bình',0);
INSERT INTO KhachHang(MaKH, TenKH, SDT, DiaCHi, GioiTinh) VALUES (N'KH0002', N'Lê Thị Hai', '0967654321',N'Tân Bình',1);


--Bảng Tác giả
INSERT TacGia VALUES (N'TG0001',N'Nguyễn Nhật Ánh')
INSERT TacGia VALUES (N'TG0002',N'Hàn Mặc Tử')
INSERT TacGia VALUES (N'TG0003',N'Trác Nhã')
INSERT TacGia VALUES (N'TG0004',N'Hồ Chí Minh')
INSERT TacGia VALUES (N'TG0005',N'NXB Hồng Đức')
INSERT TacGia VALUES (N'TG0006',N'Nguyễn Ngọc Hạnh')
INSERT TacGia VALUES (N'TG0007',N'Xuân Quỳnh')
INSERT TacGia VALUES (N'TG0008',N'Đông Mai')
INSERT TacGia VALUES (N'TG0009',N'Đặng Thuỳ Trâm')
INSERT TacGia VALUES (N'TG0010',N'Robin Sharma')
INSERT TacGia VALUES (N'TG0011',N'Phan Văn Trường')
INSERT TacGia VALUES (N'TG0012',N'Ryuichiro Nakao')
INSERT TacGia VALUES (N'TG0013',N'James Adonis')
INSERT TacGia VALUES (N'TG0014',N'Việt Chương')
INSERT TacGia VALUES (N'TG0015',N'Philip Kotler')
INSERT TacGia VALUES (N'TG0016',N'Jeff Rosenblum, Jordan Berg')
INSERT TacGia VALUES (N'TG0017',N'Dale Lovell')
INSERT TacGia VALUES (N'TG0018',N'Negi Haruba')
INSERT TacGia VALUES (N'TG0019',N'Gosho Aoyama')
INSERT TacGia VALUES (N'TG0020',N'Kim Khánh')
INSERT TacGia VALUES (N'TG0021',N'Fujiko F Fujio, Mugiwara Shintaro')
INSERT TacGia VALUES (N'TG0022',N'Mai Lan Hương, Hà Thanh Uyên') 
INSERT TacGia VALUES (N'TG0023',N'The Windy')
INSERT TacGia VALUES (N'TG0024',N'Tony Buổi Sáng')
INSERT TacGia VALUES (N'TG0025',N'Lê Đỗ Quỳnh Hương')
INSERT TacGia VALUES (N'TG0026',N'Bộ Giáo Dục Và Đào Tạo') 
INSERT TacGia VALUES (N'TG0027',N'Vũ Công Tấn Tài')
INSERT TacGia VALUES (N'TG0028',N'Clifford Stoll')
INSERT TacGia VALUES (N'TG0029',N'Nguyễn Doãn Cẩm Vân') 
INSERT TacGia VALUES (N'TG0030',N'Đức Nguyễn')
INSERT TacGia VALUES (N'TG0031',N'Trương Văn Khoa') 
INSERT TacGia VALUES (N'TG0032',N'YAYA')
INSERT TacGia VALUES (N'TG0033',N'Trịnh Công Sơn') 
INSERT TacGia VALUES (N'TG0034',N'Nhiều tác giả') 
select * from TacGia

--Bảng Thể loại
INSERT TheLoai VALUES (N'TL0001', N'Văn Học')
INSERT TheLoai VALUES (N'TL0002', N'Kinh Tế')
INSERT TheLoai VALUES (N'TL0003', N'Tâm Lý')
INSERT TheLoai VALUES (N'TL0004', N'Sách Thiếu Nhi')
INSERT TheLoai VALUES (N'TL0005', N'Sách Giáo Khoa')
INSERT TheLoai VALUES (N'TL0006', N'Ngoại Ngữ')
INSERT TheLoai VALUES (N'TL0007', N'Khoa Học - Kỹ Thuật')
INSERT TheLoai VALUES (N'TL0008', N'Dạy Nấu Ăn')
INSERT TheLoai VALUES (N'TL0009', N'Âm Nhạc - Mỹ Thuật - Thời Trang')
INSERT TheLoai VALUES (N'TL0010', N'Báo - Tạp Chí')
select* from TheLoai

--Bảng Nhà xuất bảng
INSERT NhaXuatBan VALUES (N'XB0001',N'NXB Văn Học')
INSERT NhaXuatBan VALUES (N'XB0002',N'NXB Dân Trí')
INSERT NhaXuatBan VALUES (N'XB0003',N'NXB Hồng Đức')
INSERT NhaXuatBan VALUES (N'XB0004',N'NXB Hội Nhà Văn')
INSERT NhaXuatBan VALUES (N'XB0005',N'NXB Kim Đồng')
INSERT NhaXuatBan VALUES (N'XB0006',N'NXB Trẻ')
INSERT NhaXuatBan VALUES (N'XB0007',N'NXB Kinh Tế Quốc Dân')
INSERT NhaXuatBan VALUES (N'XB0008',N'NXB Đà Nẵng')
INSERT NhaXuatBan VALUES (N'XB0009',N'NXB Lao Động')
INSERT NhaXuatBan VALUES (N'XB0010',N'NXB Đồng Nai')
INSERT NhaXuatBan VALUES (N'XB0011',N'NXB Tổng Hợp TPHCM')
INSERT NhaXuatBan VALUES (N'XB0012',N'NXB Giáo Dục Việt Nam')
INSERT NhaXuatBan VALUES (N'XB0013',N'NXB Thanh Niên')
INSERT NhaXuatBan VALUES (N'XB0014',N'NXB Thế Giới')
INSERT NhaXuatBan VALUES (N'XB0015',N'NXB Thanh Hóa')
INSERT NhaXuatBan VALUES (N'XB0016',N'Báo Sinh Viên Việt Nam - Hoa Học Trò')
INSERT NhaXuatBan VALUES (N'XB0017',N'NXB Công Thương')
select * from  NhaXuatBan

--Bảng Sách
INSERT Sach VALUES (N'SP0001', N'Kính Vạn Hoa', 121, 63000, N'TG0001', N'XB0001',N'TL0001')
INSERT Sach VALUES (N'SP0002', N'Thơ Hàn Mặc Tử - Văn Học Trong Nhà Trường  ', 125,48000, N'TG0002',N'XB0001', N'TL0001')
INSERT Sach VALUES (N'SP0003', N'Khéo Ăn Khéo Nói Sẽ Có Được Thiên Hạ (Tái Bản 2018)', 72, 240000,N'TG0003',N'XB0005',N'TL0003')
INSERT Sach VALUES (N'SP0004', N'Nhật Ký Trong Tù (Tái Bản 2020)', 80,30000, N'TG0004', N'XB0002',N'TL0001')
INSERT Sach VALUES (N'SP0005', N'Truyện Thúy Kiều',32, 88000, N'TG0005', N'XB0003', N'TL0001')
INSERT Sach VALUES (N'SP0006', N'Phơi Cơn Mưa Lên Chiều',19, 90000, N'TG0006', N'XB0004', N'TL0001')
INSERT Sach VALUES (N'SP0007', N'Văn Học Trong Nhà Trường: Thơ Xuân Quỳnh',240, 35000, N'TG0007', N'XB0005', N'TL0001')
INSERT Sach VALUES (N'SP0008', N'Xuân Quỳnh - Một Nửa Cuộc Đời Tôi', 29, 55000, N'TG0008',N'XB0005', N'TL0001')
INSERT Sach VALUES (N'SP0009', N'Nhật Ký Đặng Thùy Trâm (Tái Bản 2018)', 32, 76000, N'TG0009', N'XB0004', N'TL0001')
INSERT Sach VALUES (N'SP0010', N'Nhà Lãnh Đạo Không Chức Danh', 167, 80000, N'TG0010', N'XB0006', N'TL0002')
INSERT Sach VALUES (N'SP0011', N'Một Đời Quản Trị (Tái Bản 2019)', 115, 170000, N'TG0011', N'XB0006', N'TL0002')
INSERT Sach VALUES (N'SP0012', N'KPI - Công Cụ Quản Lý Nhân Sự Hiệu Quả', 101, 129000, N'TG0012', N'XB0007', N'TL0002')
INSERT Sach VALUES (N'SP0013', N'Đừng Làm Nhân Viên Nổi Khùng', 11, 79000, N'TG0013', N'XB0008', N'TL0002')
INSERT Sach VALUES (N'SP0014', N'Vận Dụng Khoa Nhân Tướng Học Trong Ứng Xử Và Quản Lý (Tái Bản 2019)', 144, 128000, N'TG0014', N'XB0003', N'TL0002')
INSERT Sach VALUES (N'SP0015', N'Thấu Hiểu Tiếp Thị Từ A Đến Z - 80 Khái Niệm Nhà Quản Lý Cần Biết (Tái Bản 2020)', 55, 115000, N'TG0015', N'XB0006', N'TL0002')
INSERT Sach VALUES (N'SP0016', N'Đột Phá Tư Duy Thương Hiệu', 25, 120000, N'TG0016', N'XB0011', N'TL0002')
INSERT Sach VALUES (N'SP0017', N'Quảng Cáo Tự Nhiên', 247, 111000, N'TG0017', N'XB0002', N'TL0002')
INSERT Sach VALUES (N'SP0018', N'Nhà Có 5 Nàng Dâu - Tập 1', 4, 21000, N'TG0018', N'XB0004', N'TL0004')
INSERT Sach VALUES (N'SP0019', N'Thám Tử Lừng Danh Conan - Tập 93 (Tái Bản 2019)',110, 20000, N'TG0019', N'XB0006', N'TL0004')
INSERT Sach VALUES (N'SP0020', N'Trạng Quỷnh Tập 252 - Đánh Con Quan', 100, 12000, N'TG0020', N'XB0010', N'TL0004')
INSERT Sach VALUES (N'SP0021', N'Trạng Quỷnh Tập 409 - Thím Mắm Chủ Nợ', 50,12000, N'TG0020', N'XB0007', N'TL0004')
INSERT Sach VALUES (N'SP0022', N'Truyện Tranh Trạng Quỷnh - Tập 304: Vì Phải Đền Ơn', 25, 12000, N'TG0020', N'XB0010', N'TL0004')
INSERT Sach VALUES (N'SP0023', N'Doraemon Truyện Dài - Tập 1 - Chú Khủng Long Của Nobita (Tái Bản 2019)', 54, 18000, N'TG0021', N'XB0009', N'TL0004')
INSERT Sach VALUES (N'SP0024', N'Giải Thích Ngữ Pháp Tiếng Anh (Bài Tập & Đáp Án) (Tái Bản 2019)', 1, 220000, N'TG0022', N'XB0008', N'TL0006')
INSERT Sach VALUES (N'SP0025', N'Hướng Dẫn Sử Dụng Ngữ Pháp Tiếng Anh', 55, 179000, N'TG0023', N'XB0003', N'TL0006')
INSERT Sach VALUES (N'SP0026', N'Sổ Tay Tiếng Anh Lớp 11 (2021)', 15, 28000, N'TG0022', N'XB0001', N'TL0006')
INSERT Sach VALUES (N'SP0027', N'Trên Đường Băng (Tái Bản 2017)', 32, 80000, N'TG0024', N'XB0005', N'TL0003')
INSERT Sach VALUES (N'SP0028', N'Thay Đổi Cuộc Sống Với Nhân Số Học', 25, 248000, N'TG0025', N'XB0011', N'TL0003')
INSERT Sach VALUES (N'SP0029', N'Tiếng Anh 3 - Tập 1 - Sách Học Sinh (2021)', 209, 30000, N'TG0026', N'XB0012', N'TL0005')
INSERT Sach VALUES (N'SP0030', N'Vật Lí 10 (2021)',60, 17000, N'TG0026', N'XB0012', N'TL0005')
INSERT Sach VALUES (N'SP0031', N'Hành Trang Lập Trình - Những Kỹ Năng Lập Trình Viên Chuyên Nghiệp Cần Có', 20, 143000, N'TG0027', N'XB0013', N'TL0007')
INSERT Sach VALUES (N'SP0032', N'Lập Trình Hệ Thống Thương Mại Điện Tử', 20, 170000, N'TG0034', N'XB0013', N'TL0007')
INSERT Sach VALUES (N'SP0033', N'Gián Điệp Mạng', 20, 183000, N'TG0028', N'XB0017', N'TL0007')
INSERT Sach VALUES (N'SP0034', N'500 Món Chay Thanh Tịnh - Tập 16', 20, 28000, N'TG0029', N'XB0003', N'TL0008')
INSERT Sach VALUES (N'SP0035', N'Về Nhà Ăn Cơm - 45 Công Thức Thuần Chay Cho Mâm Cơm Nhà Bạn (Tái Bản 2021)', 20, 84000, N'TG0030', N'XB0014', N'TL0008')
INSERT Sach VALUES (N'SP0036', N'Về Nhà Ăn Cơm - 45 Công Thức Thuần Chay Cho Mâm Cơm Nhà Bạn (Tái Bản 2018)', 20, 84000, N'TG0030', N'XB0014', N'TL0008')
INSERT Sach VALUES (N'SP0037', N'Những Bóng Hồng Trong Âm Nhạc', 20, 108000, N'TG0032', N'XB0004', N'TL0009')
INSERT Sach VALUES (N'SP0038', N'Mĩ Nữ Hoa - Nghệ Thuật Vẽ Màu Nước Cổ Trang', 20, 157000, N'TG0031', N'XB0005', N'TL0009')
INSERT Sach VALUES (N'SP0039', N'Bách Khoa Toàn Thư Thực Hành Hội Họa', 44, 157000, N'TG0033', N'XB0015', N'TL0009')
INSERT Sach VALUES (N'SP0040', N'Hoa Học Trò Số 1367', 344, 20000, N'TG0034', N'XB0016', N'TL0010')
INSERT Sach VALUES (N'SP0041', N'Hoa Học Trò Số 1345', 13, 27000, N'TG0034', N'XB0016', N'TL0010')
INSERT Sach VALUES (N'SP0042', N'Hoa Học Trò Số 1356', 14, 20000, N'TG0034', N'XB0016', N'TL0010')
INSERT Sach VALUES (N'SP0043', N'Hoa Học Trò Số 1351', 17, 27000, N'TG0034', N'XB0016', N'TL0010')
select * from Sach

--Bảng Hóa đơn
INSERT HoaDon VALUES (N'HD0001', N'NV0001', N'KH0001', N'Hóa đơn bán hàng', '26/01/2023')
INSERT HoaDon VALUES (N'HD0002', N'NV0001', N'KH0001', N'Hóa đơn bán hàng', '06/01/2023')
INSERT HoaDon VALUES (N'HD0003', N'NV0001', N'KH0001', N'Hóa đơn bán hàng', '16/02/2023')
INSERT HoaDon VALUES (N'HD0004', N'NV0001', N'KH0002', N'Hóa đơn bán hàng', '18/06/2023')
INSERT HoaDon VALUES (N'HD0005', N'NV0001', N'KH0001', N'Hóa đơn bán hàng', '15/02/2023')
INSERT HoaDon VALUES (N'HD0006', N'NV0002', N'KH0002', N'Hóa đơn bán hàng', '21/09/2023')
INSERT HoaDon VALUES (N'HD0007', N'NV0002', N'KH0001', N'Hóa đơn bán hàng', '27/08/2023')
INSERT HoaDon VALUES (N'HD0008', N'NV0002', N'KH0001', N'Hóa đơn bán hàng', '28/06/2023')
INSERT HoaDon VALUES (N'HD0009', N'NV0002', N'KH0001', N'Hóa đơn bán hàng', '13/07/2023')
INSERT HoaDon VALUES (N'HD0010', N'NV0002', N'KH0002', N'Hóa đơn bán hàng', '09/02/2023')

select * from HoaDon

--Bảng Chi tiết hóa đơn
INSERT ChiTietHoaDon VALUES (N'HD0001',N'SP0001', 2, 63000)
INSERT ChiTietHoaDon VALUES (N'HD0002',N'SP0005', 3, 88000)
INSERT ChiTietHoaDon VALUES (N'HD0003',N'SP0018', 1, 21000)
INSERT ChiTietHoaDon VALUES (N'HD0004',N'SP0021', 1, 12000)
INSERT ChiTietHoaDon VALUES (N'HD0004',N'SP0023', 6, 18000)
INSERT ChiTietHoaDon VALUES (N'HD0005',N'SP0010', 2, 80000)
INSERT ChiTietHoaDon VALUES (N'HD0006',N'SP0019', 1, 20000)
INSERT ChiTietHoaDon VALUES (N'HD0007',N'SP0017', 2, 111000)
INSERT ChiTietHoaDon VALUES (N'HD0008',N'SP0017', 1, 111000)
INSERT ChiTietHoaDon VALUES (N'HD0009',N'SP0004', 3, 30000)
INSERT ChiTietHoaDon VALUES (N'HD0010',N'SP0002', 3, 48000)
INSERT ChiTietHoaDon VALUES (N'HD0010',N'SP0005', 1, 88000)


--Bảng phiếu đặt hàng
INSERT PhieuDatHang VALUES (N'DH0001',N'KH0002','12/09/2023')
INSERT PhieuDatHang VALUES (N'DH0002',N'KH0002','18/07/2023')
INSERT PhieuDatHang VALUES (N'DH0003',N'KH0001','12/07/2023')
INSERT PhieuDatHang VALUES (N'DH0004',N'KH0002','23/02/2023')
INSERT PhieuDatHang VALUES (N'DH0005',N'KH0002','12/05/2023')
INSERT PhieuDatHang VALUES (N'DH0006',N'KH0001','12/01/2023')
INSERT PhieuDatHang VALUES (N'DH0007',N'KH0002','23/02/2023')
INSERT PhieuDatHang VALUES (N'DH0008',N'KH0001','25/09/2023')
INSERT PhieuDatHang VALUES (N'DH0009',N'KH0002','31/01/2023')
INSERT PhieuDatHang VALUES (N'DH0010',N'KH0002','08/09/2023')
select * from PhieuDatHang

--Bảng Chi tiết phiếu đặt hàng
INSERT ChiTietPhieuDatHang VALUES (N'DH0001',N'SP0031',2,170000)
INSERT ChiTietPhieuDatHang VALUES (N'DH0002',N'SP0033',3,183000)
INSERT ChiTietPhieuDatHang VALUES (N'DH0003',N'SP0035',1,84000)
INSERT ChiTietPhieuDatHang VALUES (N'DH0004',N'SP0039',1,157000)
INSERT ChiTietPhieuDatHang VALUES (N'DH0005',N'SP0032',3,170000)
INSERT ChiTietPhieuDatHang VALUES (N'DH0006',N'SP0034',6,280000)
INSERT ChiTietPhieuDatHang VALUES (N'DH0007',N'SP0036',1,84000)
INSERT ChiTietPhieuDatHang VALUES (N'DH0008',N'SP0040',4,20000)
INSERT ChiTietPhieuDatHang VALUES (N'DH0009',N'SP0041',2,27000)
INSERT ChiTietPhieuDatHang VALUES (N'DH0010',N'SP0043',7,27000)
INSERT ChiTietPhieuDatHang VALUES (N'DH0005',N'SP0042',6,20000)
INSERT ChiTietPhieuDatHang VALUES (N'DH0006',N'SP0035',3,84000)
INSERT ChiTietPhieuDatHang VALUES (N'DH0007',N'SP0031',1,143000)
INSERT ChiTietPhieuDatHang VALUES (N'DH0008',N'SP0016',5,120000)
INSERT ChiTietPhieuDatHang VALUES (N'DH0004',N'SP0033',4,183000)
select * from ChiTietPhieuDatHang





SELECT ct.MaSach, s.TenSach, ct.DonGia, ct.SoLuong,s.SoLuong, s.SoLuong - ct.SoLuong AS SoLuongTon, ct.SoLuong * ct.DonGia AS ThanhTien 
	              FROM ChiTietHoaDon ct 
	              JOIN HoaDon hd ON ct.MaHoaDon = hd.MaHoaDon 
	             JOIN Sach s ON ct.MaSach = s.MaSach 
	              WHERE hd.NgayXuatHD BETWEEN '01/01/2023' AND '31/12/2023'


SELECT SUM(ct.SoLuong)
		                     FROM ChiTietHoaDon ct 
		                     JOIN HoaDon hd ON ct.MaHoaDon = hd.MaHoaDon
		                     WHERE hd.NgayXuatHD BETWEEN '01/01/2023' AND '31/12/2023'


select Sum(ct.SoLuong) from ChiTietHoaDon ct
		        		join  HoaDon hd
		        		on ct.MaHoaDon= hd.MaHoaDon
		        		where YEAR(hd.NgayXuatHD) = 2023 and (MONTH(hd.NgayXuatHD) = 4)

SELECT TOP(5) ct.MaSach, s.TenSach, ct.DonGia, SUM(ct.SoLuong) AS SoLuongBan, s.SoLuong AS SoLuongTon, SUM(ct.SoLuong * ct.DonGia) AS thanhtien
				FROM ChiTietHoaDon ct JOIN HoaDon hd ON ct.MaHoaDon = hd.MaHoaDon JOIN Sach s ON ct.MaSach = s.MaSach
				WHERE hd.NgayXuatHD BETWEEN '01/01/2023' AND '31/12/2023'
				GROUP BY ct.MaSach, s.TenSach, ct.DonGia, s.SoLuong
				ORDER BY SUM(ct.SoLuong) DESC


SELECT SUM(subquery.total_sales) FROM (
					SELECT TOP(5) SUM(ct.SoLuong) AS total_sales FROM ChiTietHoaDon ct
					JOIN HoaDon hd ON ct.MaHoaDon = hd.MaHoaDon JOIN Sach s ON ct.MaSach = s.MaSach
					WHERE hd.NgayXuatHD BETWEEN '01/01/2023' AND '31/12/2023'
					GROUP BY ct.MaSach, s.TenSach, ct.DonGia 
					ORDER BY sum(ct.SoLuong) DESC
					)AS subquery