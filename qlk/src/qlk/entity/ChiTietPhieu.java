package qlk.entity;

public class ChiTietPhieu{
	protected int id;
	protected int soPhieu;
	protected int maDT;
	protected int slg;
	
	
	public ChiTietPhieu() {
		super();
	}

	public ChiTietPhieu(int id, int soPhieu, int maDT, int slg) {
		this.id = id;
		this.soPhieu = soPhieu;
		this.maDT = maDT;
		this.slg = slg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSoPhieu() {
		return soPhieu;
	}

	public void setSoPhieu(int soPhieu) {
		this.soPhieu = soPhieu;
	}

	public int getMaDT() {
		return maDT;
	}

	public void setMaDT(int maDT) {
		this.maDT = maDT;
	}

	public int getSlg() {
		return slg;
	}

	public void setSlg(int slg) {
		this.slg = slg;
	}
	
		
} 
