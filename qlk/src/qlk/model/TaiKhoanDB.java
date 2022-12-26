package qlk.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qlk.entity.TaiKhoan;

public class TaiKhoanDB { 
	public static List<TaiKhoan> AllUser(Connection conn) throws SQLException, ClassNotFoundException
	{
		String sql = "SELECT userName, passWord, tenNV,email, quyen FROM TaiKhoan";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<TaiKhoan> list = new ArrayList<TaiKhoan>();
		while (rs.next()) 
		{
			String userName = rs.getString("userName");
			String passWord = rs.getString("passWord");
			String tenNV = rs.getString("tenNV");
			String email = rs.getString("email");
			int quyen = rs.getInt("quyen");
		
			TaiKhoan user = new TaiKhoan();
			user.setUserName(userName);
			user.setPassWord(passWord);
			user.setTenNV(tenNV);
			user.setEmail(email);
			user.setQuyen(quyen);			
			list.add(user);
		} return list;
	}
	
	
	public static void addUser(Connection conn, TaiKhoan user) throws SQLException
	{
		String sql = "Insert into TaiKhoan(userName, passWord, tenNV,email, quyen) values (?,?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, user.getUserName());
		pstm.setString(2, user.getPassWord());
		pstm.setString(3, user.getTenNV());
		pstm.setString(4, user.getEmail());
		pstm.setInt(5, user.getQuyen());
		pstm.executeUpdate();
	}
	
	public static void updateUser(Connection conn, TaiKhoan user) throws SQLException
	{
		String sql = "UPDATE TaiKhoan SET passWord=?, tenNV=?,email=?, quyen=? WHERE userName=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, user.getPassWord());
		pstm.setString(2, user.getTenNV());
		pstm.setString(3, user.getEmail());
		pstm.setInt(4, user.getQuyen());
		pstm.setString(5, user.getUserName());
		pstm.executeUpdate();
	}
	
	public static void deleteUser(Connection conn, String userName) throws SQLException
	{
		String sql = "Delete FROM TaiKhoan WHERE userName= ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		pstm.executeUpdate();
	}
	
	public static TaiKhoan findUser(Connection conn, String userName) throws SQLException
	{
		String sql = "SELECT passWord, tenNV,email, quyen FROM TaiKhoan WHERE userName=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		ResultSet rs = pstm.executeQuery();
		
		if (rs.next())
		{			
			String passWord = rs.getString("passWord");
			String tenNV = rs.getString("tenNV");
			String email = rs.getString("email");
			int quyen = rs.getInt("quyen");
		
			TaiKhoan user = new TaiKhoan();
			user.setUserName(userName);
			user.setPassWord(passWord);
			user.setTenNV(tenNV);
			user.setEmail(email);
			user.setQuyen(quyen);
			return user;
		}return null;
	}
		
}

