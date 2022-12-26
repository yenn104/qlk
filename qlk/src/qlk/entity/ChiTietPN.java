package qlk.entity;

public class ChiTietPN extends ChiTietPhieu{
	private double giaNhap;
	private double tongTien;
	
	public ChiTietPN() {
		super();
	}
	
	public ChiTietPN(int id, int soPhieu, int maDT, int slg, double giaNhap, double tongTien) {
		super(id, soPhieu, maDT, slg);
		this.giaNhap = giaNhap;
		this.tongTien = tongTien;
	}

	public double getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

}
