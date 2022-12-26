-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 06, 2022 lúc 08:46 AM
-- Phiên bản máy phục vụ: 10.4.25-MariaDB
-- Phiên bản PHP: 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlykhodienthoai`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `id` int(11) NOT NULL,
  `soPhieu` int(11) NOT NULL,
  `maDT` int(11) NOT NULL,
  `slg` int(11) NOT NULL,
  `giaNhap` double NOT NULL,
  `tongTien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`id`, `soPhieu`, `maDT`, `slg`, `giaNhap`, `tongTien`) VALUES
(60, 19, 1, 20, 20000000, 400000000),
(61, 19, 2, 10, 20000000, 200000000),
(62, 19, 4, 15, 6000000, 90000000),
(63, 19, 8, 5, 4190000, 20950000),
(64, 19, 9, 13, 3890000, 50570000),
(65, 19, 10, 7, 3890000, 27230000),
(66, 19, 11, 5, 3780000, 18900000),
(67, 19, 14, 12, 31998000, 383976000),
(68, 19, 16, 10, 30998000, 309980000),
(69, 19, 19, 6, 700000, 4200000),
(70, 20, 3, 22, 2000000, 44000000),
(71, 20, 5, 4, 12000000, 48000000),
(72, 20, 6, 9, 20500000, 184500000),
(73, 20, 7, 11, 4190000, 46090000),
(74, 20, 12, 19, 3690000, 70110000),
(75, 20, 13, 21, 37998000, 797958000),
(76, 20, 15, 3, 41998000, 125994000),
(77, 20, 17, 6, 11000000, 66000000),
(78, 20, 18, 7, 16490000, 115430000),
(79, 21, 4, 5, 6000000, 30000000),
(80, 21, 15, 10, 41998000, 419980000),
(81, 21, 13, 5, 37998000, 189990000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieuthongke`
--

CREATE TABLE `chitietphieuthongke` (
  `maphieu` int(11) NOT NULL,
  `maphieuthongke` int(11) NOT NULL,
  `madt` int(11) NOT NULL,
  `tendt` varchar(50) NOT NULL,
  `tondauky` int(11) NOT NULL,
  `nhaptrongky` int(11) NOT NULL,
  `xuattrongky` int(11) NOT NULL,
  `toncuoiky` int(11) NOT NULL,
  `giatritonkho` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitietphieuthongke`
--

INSERT INTO `chitietphieuthongke` (`maphieu`, `maphieuthongke`, `madt`, `tendt`, `tondauky`, `nhaptrongky`, `xuattrongky`, `toncuoiky`, `giatritonkho`) VALUES
(77, 6, 1, 'SamSung Galaxy A30', 20, 20, 0, 40, 800000000),
(78, 6, 2, 'Iphone Xs Max', 10, 10, 0, 20, 400000000),
(79, 6, 3, 'Vivi Y11', 0, 22, 0, 22, 44000000),
(80, 6, 4, 'SamSung Galaxy A80', 15, 15, 0, 30, 180000000),
(81, 6, 5, 'iPhone 11 64GB', 0, 4, 0, 4, 48000000),
(82, 6, 6, 'Samsung Galaxy Z Flip4 128GB', 0, 9, 0, 9, 184500000),
(83, 6, 7, 'Oppo A57', 0, 11, 0, 11, 46090000),
(84, 6, 8, 'Realme C25Y', 5, 5, 0, 10, 41900000),
(85, 6, 9, 'Xiaomi Redmi 10 (2022)', 13, 13, 0, 26, 101140000),
(86, 6, 10, 'Nokia G21', 7, 7, 0, 14, 54460000),
(87, 6, 11, 'SamSung Galaxy A20', 5, 5, 0, 10, 37800000),
(88, 6, 12, 'Samsung Galaxy A04s', 0, 19, 0, 19, 70110000),
(89, 6, 13, ' Samsung Galaxy Z Fold4', 0, 21, 0, 21, 797958000),
(90, 6, 14, ' Samsung Galaxy Z Fold3 ', 12, 12, 0, 24, 767952000),
(91, 6, 15, 'Samsung Galaxy Z Fold4 512GB', 0, 3, 0, 3, 125994000),
(92, 6, 16, 'Samsung Galaxy S22 Ultra 5G 512GB', 10, 10, 0, 20, 619960000),
(93, 6, 17, 'iPhone 11 1TB', 0, 6, 0, 6, 66000000),
(94, 6, 18, 'iPhone 12 64GB', 0, 7, 0, 7, 115430000),
(95, 6, 19, 'Masstel IZI 10 4G', 6, 6, 0, 12, 8400000),
(96, 7, 1, 'SamSung Galaxy A30', 20, 0, 10, 10, 200000000),
(97, 7, 2, 'Iphone Xs Max', 10, 0, 3, 7, 140000000),
(98, 7, 3, 'Vivi Y11', 22, 22, 15, 29, 58000000),
(99, 7, 4, 'SamSung Galaxy A80', 15, 5, 12, 8, 48000000),
(100, 7, 5, 'iPhone 11 64GB', 4, 4, 1, 7, 84000000),
(101, 7, 6, 'Samsung Galaxy Z Flip4 128GB', 9, 9, 3, 15, 307500000),
(102, 7, 7, 'Oppo A57', 11, 11, 6, 16, 67040000),
(103, 7, 8, 'Realme C25Y', 5, 0, 4, 1, 4190000),
(104, 7, 9, 'Xiaomi Redmi 10 (2022)', 13, 0, 7, 6, 23340000),
(105, 7, 10, 'Nokia G21', 7, 0, 2, 5, 19450000),
(106, 7, 11, 'SamSung Galaxy A20', 5, 0, 3, 2, 7560000),
(107, 7, 12, 'Samsung Galaxy A04s', 19, 19, 9, 29, 107010000),
(108, 7, 13, ' Samsung Galaxy Z Fold4', 21, 26, 18, 29, 1101942000),
(109, 7, 14, ' Samsung Galaxy Z Fold3 ', 12, 0, 6, 6, 191988000),
(110, 7, 15, 'Samsung Galaxy Z Fold4 512GB', 3, 13, 2, 14, 587972000),
(111, 7, 16, 'Samsung Galaxy S22 Ultra 5G 512GB', 10, 0, 5, 5, 154990000),
(112, 7, 17, 'iPhone 11 1TB', 6, 6, 5, 7, 77000000),
(113, 7, 18, 'iPhone 12 64GB', 7, 7, 2, 12, 197880000),
(114, 7, 19, 'Masstel IZI 10 4G', 6, 0, 1, 5, 3500000),
(115, 8, 1, 'SamSung Galaxy A30', 20, 0, 0, 20, 400000000),
(116, 8, 2, 'Iphone Xs Max', 10, 0, 0, 10, 200000000),
(117, 8, 3, 'Vivi Y11', 22, 22, 0, 44, 88000000),
(118, 8, 4, 'SamSung Galaxy A80', 15, 0, 0, 15, 90000000),
(119, 8, 5, 'iPhone 11 64GB', 4, 4, 0, 8, 96000000),
(120, 8, 6, 'Samsung Galaxy Z Flip4 128GB', 9, 9, 0, 18, 369000000),
(121, 8, 7, 'Oppo A57', 11, 11, 0, 22, 92180000),
(122, 8, 8, 'Realme C25Y', 5, 0, 0, 5, 20950000),
(123, 8, 9, 'Xiaomi Redmi 10 (2022)', 13, 0, 0, 13, 50570000),
(124, 8, 10, 'Nokia G21', 7, 0, 0, 7, 27230000),
(125, 8, 11, 'SamSung Galaxy A20', 5, 0, 0, 5, 18900000),
(126, 8, 12, 'Samsung Galaxy A04s', 19, 19, 0, 38, 140220000),
(127, 8, 13, ' Samsung Galaxy Z Fold4', 21, 21, 0, 42, 1595916000),
(128, 8, 14, ' Samsung Galaxy Z Fold3 ', 12, 0, 0, 12, 383976000),
(129, 8, 15, 'Samsung Galaxy Z Fold4 512GB', 3, 3, 0, 6, 251988000),
(130, 8, 16, 'Samsung Galaxy S22 Ultra 5G 512GB', 10, 0, 0, 10, 309980000),
(131, 8, 17, 'iPhone 11 1TB', 6, 6, 0, 12, 132000000),
(132, 8, 18, 'iPhone 12 64GB', 7, 7, 0, 14, 230860000),
(133, 8, 19, 'Masstel IZI 10 4G', 6, 0, 0, 6, 4200000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieuxuat`
--

CREATE TABLE `chitietphieuxuat` (
  `id` int(11) NOT NULL,
  `soPhieu` int(11) NOT NULL,
  `maDT` int(11) NOT NULL,
  `slg` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitietphieuxuat`
--

INSERT INTO `chitietphieuxuat` (`id`, `soPhieu`, `maDT`, `slg`) VALUES
(38, 13, 1, 10),
(39, 13, 2, 3),
(40, 13, 4, 12),
(41, 13, 8, 4),
(42, 13, 9, 7),
(43, 14, 10, 2),
(44, 14, 11, 3),
(45, 14, 14, 6),
(46, 14, 16, 5),
(47, 14, 19, 1),
(48, 15, 3, 15),
(49, 15, 5, 1),
(50, 15, 6, 3),
(51, 15, 7, 6),
(52, 15, 12, 9),
(53, 16, 13, 18),
(54, 16, 15, 2),
(55, 16, 17, 5),
(56, 16, 18, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dienthoai`
--

CREATE TABLE `dienthoai` (
  `maDT` int(11) NOT NULL,
  `tenDT` varchar(50) NOT NULL,
  `hangDT` varchar(50) NOT NULL,
  `mauSac` varchar(50) NOT NULL,
  `thongSo` varchar(50) NOT NULL,
  `thongTin` varchar(50) NOT NULL,
  `slg` int(11) DEFAULT NULL,
  `gia` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `dienthoai`
--

INSERT INTO `dienthoai` (`maDT`, `tenDT`, `hangDT`, `mauSac`, `thongSo`, `thongTin`, `slg`, `gia`) VALUES
(1, 'SamSung Galaxy A30', 'Samsung', 'Trắng', 'Exynos 850, RAM:4GB', ' IPS LCD6.5\"HD', 10, 20000000),
(2, 'Iphone Xs Max', 'Táo', 'Đen', 'Exynos 850, RAM:4GB', 'IPS LCD6.5\"HD+', 7, 20000000),
(3, 'Vivi Y11', 'Vivo', 'Xanh Đen', 'MediaTek Helio G35', 'RAM 4GB , IPS LCD6.5\"HD+', 7, 2000000),
(4, 'SamSung Galaxy A80', 'SamSung', 'Trắng', 'MediaTek Helio G35', ' RAM :4GB, IPS LCD6.5\"HD+', 8, 6000000),
(5, 'iPhone 11 64GB', 'Apple', 'Trắng', 'Camera Selfie 12.0 MP, RAM 4 GB', 'Apple A13 Bionic', 3, 12000000),
(6, 'Samsung Galaxy Z Flip4 128GB', 'Samsung', 'Đen', 'Exynos 850', 'IPX8, 4K@30fps RAM 8 GB', 6, 20500000),
(7, 'Oppo A57', 'OPPO', 'Xanh ngọc', 'MediaTek Helio G35', ' RAM :4GB, IPS LCD6.5\"HD+', 5, 4190000),
(8, 'Realme C25Y', 'Realme', 'Xanh dương', 'Unisoc T618', ' RAM :4GB, IPS LCD6.5\"HD+', 1, 4190000),
(9, 'Xiaomi Redmi 10 (2022)', 'Xiaomi', 'Xám', 'MediaTek Helio G88 ', 'RAM:4GB, IPS LCD6.5\"Full HD+', 6, 3890000),
(10, 'Nokia G21', 'Nokia', 'Tím', 'Unisoc T606 G88', ' RAM:4GB , IPS LCD6.5 \"Full HD+', 5, 3890000),
(11, 'SamSung Galaxy A20', 'Samsung', 'Đen', 'Exynos 850', 'RAM: 4GB, IPS LCD6.5\"HD+', 2, 3780000),
(12, 'Samsung Galaxy A04s', 'Samsung', 'Đen', 'Exynos 850', 'RAM:4GB, IPS LCD6.5\"HD+', 10, 3690000),
(13, ' Samsung Galaxy Z Fold4', 'Samsung', 'Đen', 'Snapdragon 8+ Gen 1', ' RAM: 12GB, Dynamic AMOLED 2XC', 8, 37998000),
(14, ' Samsung Galaxy Z Fold3 ', 'Samsung', 'Bạc', 'Snapdragon 888', 'RAM: 12GB, Dynamic AMOLED 2XChính ', 6, 31998000),
(15, 'Samsung Galaxy Z Fold4 512GB', 'Samsung', 'Đen', 'Snapdragon 8+ Gen 1', 'RAM: 12GB, Dynamic AMOLED 2XC', 11, 41998000),
(16, 'Samsung Galaxy S22 Ultra 5G 512GB', 'Samsung', 'Đen', 'Snapdragon 8 Gen 1', ' RAM:12, Dynamic AMOLED 2X6.8\" ', 5, 30998000),
(17, 'iPhone 11 1TB', 'Apple', 'Trắng', 'Apple A13 Bionic', ' IPS LCD6.1\"Liquid Retina', 1, 11000000),
(18, 'iPhone 12 64GB', 'Apple', 'Xanh dương', 'Apple A14 Bionic', 'OLED6.1\"Super Retina XDR', 5, 16490000),
(19, 'Masstel IZI 10 4G', 'Masstel', 'Xanh dương', 'TFT LCD1.77\"262.000 màu', '2 Nano SIMHỗ trợ 4G VoLTE', 5, 700000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `soPhieu` int(11) NOT NULL,
  `ngay` date NOT NULL,
  `tenNV` varchar(50) NOT NULL,
  `thanhTien` double DEFAULT NULL,
  `tinhTrang` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`soPhieu`, `ngay`, `tenNV`, `thanhTien`, `tinhTrang`) VALUES
(19, '2022-11-13', 'Bảo Yến', 1505806000, 1),
(20, '2022-11-15', 'Bảo Yến', 1498082000, 1),
(21, '2022-11-22', 'Bảo Yến', 639970000, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieuthongke`
--

CREATE TABLE `phieuthongke` (
  `maphieu` int(11) NOT NULL,
  `ngaybatdau` date NOT NULL,
  `ngayketthuc` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `phieuthongke`
--

INSERT INTO `phieuthongke` (`maphieu`, `ngaybatdau`, `ngayketthuc`) VALUES
(6, '2022-11-13', '2022-11-15'),
(7, '2022-11-15', '2022-11-22'),
(8, '2022-11-15', '2022-11-15');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieuxuat`
--

CREATE TABLE `phieuxuat` (
  `soPhieu` int(11) NOT NULL,
  `ngay` date NOT NULL,
  `tenNV` varchar(50) NOT NULL,
  `thanhTien` double DEFAULT NULL,
  `tinhTrang` tinyint(1) NOT NULL,
  `lydo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `phieuxuat`
--

INSERT INTO `phieuxuat` (`soPhieu`, `ngay`, `tenNV`, `thanhTien`, `tinhTrang`, `lydo`) VALUES
(13, '2022-11-17', 'Bảo Yến', 375990000, 1, 'Xuất Kho'),
(14, '2022-11-18', 'Bảo Yến', 366798000, 1, 'Xuất kho'),
(15, '2022-11-20', 'Bảo Yến', 161850000, 1, 'Xuất kho'),
(16, '2022-11-21', 'Bảo Yến', 855940000, 1, 'Xuất kho');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `userName` varchar(50) NOT NULL,
  `passWord` varchar(50) NOT NULL,
  `tenNV` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `quyen` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`userName`, `passWord`, `tenNV`, `email`, `quyen`) VALUES
('thaiduong', '12345', 'Thái Dương', 'thaiduong@gmail.com', 0),
('thaohuyen', '12345', 'Thảo Huyền', 'thaohuyen@gmail.com', 0),
('tung', '3070', 'Thanh Tùng', 'nhokkid20@gmail.com', 1),
('tuonganh', '3', 'Tường Anh', 'tuonganh@gmail.com', 0),
('yen', '12345', 'Bảo Yến', 'baoyen@gmail.com', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`id`),
  ADD KEY `soPhieu` (`soPhieu`,`maDT`),
  ADD KEY `maDT` (`maDT`);

--
-- Chỉ mục cho bảng `chitietphieuthongke`
--
ALTER TABLE `chitietphieuthongke`
  ADD PRIMARY KEY (`maphieu`),
  ADD KEY `maphieuthongke` (`maphieuthongke`);

--
-- Chỉ mục cho bảng `chitietphieuxuat`
--
ALTER TABLE `chitietphieuxuat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `soPhieu` (`soPhieu`,`maDT`),
  ADD KEY `maDT` (`maDT`);

--
-- Chỉ mục cho bảng `dienthoai`
--
ALTER TABLE `dienthoai`
  ADD PRIMARY KEY (`maDT`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`soPhieu`),
  ADD KEY `maNV` (`tenNV`);

--
-- Chỉ mục cho bảng `phieuthongke`
--
ALTER TABLE `phieuthongke`
  ADD PRIMARY KEY (`maphieu`);

--
-- Chỉ mục cho bảng `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD PRIMARY KEY (`soPhieu`),
  ADD KEY `maNV` (`tenNV`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`userName`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=82;

--
-- AUTO_INCREMENT cho bảng `chitietphieuthongke`
--
ALTER TABLE `chitietphieuthongke`
  MODIFY `maphieu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=134;

--
-- AUTO_INCREMENT cho bảng `chitietphieuxuat`
--
ALTER TABLE `chitietphieuxuat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT cho bảng `dienthoai`
--
ALTER TABLE `dienthoai`
  MODIFY `maDT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `soPhieu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `phieuthongke`
--
ALTER TABLE `phieuthongke`
  MODIFY `maphieu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `phieuxuat`
--
ALTER TABLE `phieuxuat`
  MODIFY `soPhieu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `chitietphieunhap_ibfk_1` FOREIGN KEY (`maDT`) REFERENCES `dienthoai` (`maDT`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `chitietphieunhap_ibfk_2` FOREIGN KEY (`soPhieu`) REFERENCES `phieunhap` (`soPhieu`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `chitietphieuthongke`
--
ALTER TABLE `chitietphieuthongke`
  ADD CONSTRAINT `chitietphieuthongke_ibfk_1` FOREIGN KEY (`maphieuthongke`) REFERENCES `phieuthongke` (`maphieu`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `chitietphieuxuat`
--
ALTER TABLE `chitietphieuxuat`
  ADD CONSTRAINT `chitietphieuxuat_ibfk_1` FOREIGN KEY (`soPhieu`) REFERENCES `phieuxuat` (`soPhieu`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `chitietphieuxuat_ibfk_2` FOREIGN KEY (`maDT`) REFERENCES `dienthoai` (`maDT`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
