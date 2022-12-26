package qlk.Interface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import qlk.entity.TaiKhoan;
import qlk.model.TaiKhoanDB;

public class QuanLyTaiKhoan implements IQuanLyTaiKhoan{

	@Override
	public void AddTK(Connection conn, TaiKhoan tk) {
		try {
			TaiKhoanDB.addUser(conn, tk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void UpdateTK(Connection conn, TaiKhoan tk) {
		try {
			TaiKhoanDB.updateUser(conn, tk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void DeleteTK(Connection conn, String userName) {
		try {
			TaiKhoanDB.deleteUser(conn, userName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TaiKhoan FindTK(Connection conn, String userName){
		
		try {
			return TaiKhoanDB.findUser(conn, userName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}	
	
	@Override
	public List<TaiKhoan> FindAllTK(Connection conn, List<TaiKhoan> tk){
		try {
			tk.addAll(TaiKhoanDB.AllUser(conn));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tk;
	}
	


}
