package qlk.entity;


public class TaiKhoan {
	private String userName;
	private String passWord;
	private String tenNV;
	private String email;
	private int quyen;
		
	public TaiKhoan() {
		super();
	}

	public TaiKhoan(String userName, String passWord, String tenNV, String email, int quyen) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.tenNV = tenNV;
		this.email = email;
		this.quyen = quyen;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getQuyen() {
		return quyen;
	}

	public void setQuyen(int quyen) {
		this.quyen = quyen;
	}

	
}
