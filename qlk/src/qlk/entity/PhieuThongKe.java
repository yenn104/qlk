package qlk.entity;

import java.sql.Date;

public class PhieuThongKe {
	private int maphieu;
	private Date ngaybatdau,ngayketthuc;
	public PhieuThongKe(int maphieu, Date ngaybatdau, Date ngayketthuc) {
		super();
		this.maphieu = maphieu;
		this.ngaybatdau = ngaybatdau;
		this.ngayketthuc = ngayketthuc;
	}
	public PhieuThongKe() {
		super();
	}
	public int getMaphieu() {
		return maphieu;
	}
	public void setMaphieu(int maphieu) {
		this.maphieu = maphieu;
	}
	public Date getNgaybatdau() {
		return ngaybatdau;
	}
	public void setNgaybatdau(Date ngaybatdau) {
		this.ngaybatdau = ngaybatdau;
	}
	public Date getNgayketthuc() {
		return ngayketthuc;
	}
	public void setNgayketthuc(Date ngayketthuc) {
		this.ngayketthuc = ngayketthuc;
	}
	
	
}
