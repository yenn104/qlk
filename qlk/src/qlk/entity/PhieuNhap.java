package qlk.entity;

import java.sql.Date;

public class PhieuNhap extends Phieu{
	
	public PhieuNhap() {
		super();
	}
	
	public PhieuNhap(int soPhieu, Date ngay, String tenNV, double thanhTien, boolean tinhTrang) {
		super(soPhieu, ngay, tenNV, thanhTien, tinhTrang);
	}
	
}
