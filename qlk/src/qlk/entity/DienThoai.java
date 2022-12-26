package qlk.entity;

public class DienThoai {
	private int maDT;
	private String tenDT;
	private String hangDT; 
	private String mauSac;
	private String thongSo;
	private String thongTin;
	private int slg;
	private double gia;
	
	
	public DienThoai() {

	}
	
	public DienThoai(int maDT, String tenDT, String hangDT, String mauSac, String thongSo, String thongTin,
			double gia) {
		this.maDT = maDT;
		this.tenDT = tenDT;
		this.hangDT = hangDT;
		this.mauSac = mauSac;
		this.thongSo = thongSo;
		this.thongTin = thongTin;
		this.gia = gia;
	}
	

	public int getMaDT() {
		return maDT;
	}
	public void setMaDT(int maDT) {
		this.maDT = maDT;
	}
	public String getTenDT() {
		return tenDT;
	}
	public void setTenDT(String tenDT) {
		this.tenDT = tenDT;
	}
	public String getHangDT() {
		return hangDT;
	}
	public void setHangDT(String hangDT) {
		this.hangDT = hangDT;
	}
	public String getMauSac() {
		return mauSac;
	}
	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}
	public String getThongSo() {
		return thongSo;
	}
	public void setThongSo(String thongSo) {
		this.thongSo = thongSo;
	}
	public String getThongTin() {
		return thongTin;
	}
	public void setThongTin(String thongTin) {
		this.thongTin = thongTin;
	}
	public int getSlg() {
		return slg;
	}
	public void setSlg(int slg) {
		this.slg = slg;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	
}
