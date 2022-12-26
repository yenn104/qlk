package qlk.entity;

import java.sql.Date;

public class Phieu {
	private int soPhieu;
	private Date ngay;
	private String tenNV;
	private double thanhTien;
	private boolean tinhTrang;
	
	public Phieu() {
		super();
	}	
	
	public Phieu(int soPhieu, Date ngay, String tenNV, double thanhTien, boolean tinhTrang) {
		super();
		this.soPhieu = soPhieu;
		this.ngay = ngay;
		this.tenNV = tenNV;
		this.thanhTien = thanhTien;
		this.tinhTrang = tinhTrang;
	}


	public int getSoPhieu() {
		return soPhieu;
	}

	public void setSoPhieu(int soPhieu) {
		this.soPhieu = soPhieu;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}

	public boolean isTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

}
