package qlk.Interface;

import java.sql.Connection;
import java.util.List;

import qlk.entity.TaiKhoan;

public interface IQuanLyTaiKhoan {
	public void AddTK(Connection conn, TaiKhoan tk);  
	public void UpdateTK(Connection conn, TaiKhoan tk);  
	public void DeleteTK(Connection conn, String userName);
	public TaiKhoan FindTK(Connection conn, String userName);
	public List<TaiKhoan> FindAllTK(Connection conn, List<TaiKhoan> tk);
}
