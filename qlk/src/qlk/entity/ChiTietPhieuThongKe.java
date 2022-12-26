package qlk.entity;

public class ChiTietPhieuThongKe {
	private int maphieu,maphieuthongke, madt,tondauky,toncuoiky,nhaptrongky,xuattrongky;
	private double giatritonkho;
	private String tendt;
	public ChiTietPhieuThongKe() {
		super();
	}
	public ChiTietPhieuThongKe(int maphieu, int maphieuthongke, int madt, int tondauky, int toncuoiky, int nhaptrongky,
			int xuattrongky, double giatritonkho, String tendt) {
		super();
		this.maphieu = maphieu;
		this.maphieuthongke = maphieuthongke;
		this.madt = madt;
		this.tondauky = tondauky;
		this.toncuoiky = toncuoiky;
		this.nhaptrongky = nhaptrongky;
		this.xuattrongky = xuattrongky;
		this.giatritonkho = giatritonkho;
		this.tendt = tendt;
	}
	public int getMaphieu() {
		return maphieu;
	}
	public void setMaphieu(int maphieu) {
		this.maphieu = maphieu;
	}
	public int getMaphieuthongke() {
		return maphieuthongke;
	}
	public void setMaphieuthongke(int maphieuthongke) {
		this.maphieuthongke = maphieuthongke;
	}
	public int getMadt() {
		return madt;
	}
	public void setMadt(int madt) {
		this.madt = madt;
	}
	public int getTondauky() {
		return tondauky;
	}
	public void setTondauky(int tondauky) {
		this.tondauky = tondauky;
	}
	public int getToncuoiky() {
		return toncuoiky;
	}
	public void setToncuoiky(int toncuoiky) {
		this.toncuoiky = toncuoiky;
	}
	public int getNhaptrongky() {
		return nhaptrongky;
	}
	public void setNhaptrongky(int nhaptrongky) {
		this.nhaptrongky = nhaptrongky;
	}
	public int getXuattrongky() {
		return xuattrongky;
	}
	public void setXuattrongky(int xuattrongky) {
		this.xuattrongky = xuattrongky;
	}
	public double getGiatritonkho() {
		return giatritonkho;
	}
	public void setGiatritonkho(double giatritonkho) {
		this.giatritonkho = giatritonkho;
	}
	public String getTendt() {
		return tendt;
	}
	public void setTendt(String tendt) {
		this.tendt = tendt;
	}
	
}
