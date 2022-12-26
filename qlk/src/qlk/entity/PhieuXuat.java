package qlk.entity;

import java.sql.Date;

public class PhieuXuat extends Phieu{
	private String lydo;

	public PhieuXuat() {
		super();
	}

	public PhieuXuat(int soPhieu, Date ngay, String tenNV, double thanhTien, boolean tinhTrang, String lydo) {
		super(soPhieu, ngay, tenNV, thanhTien, tinhTrang);
		this.lydo = lydo;
	}

	public String getLydo() {
		return lydo;
	}

	public void setLydo(String lydo) {
		this.lydo = lydo;
	}

}
