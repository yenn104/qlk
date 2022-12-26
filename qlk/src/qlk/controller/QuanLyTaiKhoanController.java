package qlk.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import qlk.Interface.QuanLyTaiKhoan;
import qlk.entity.TaiKhoan;
import qlk.model.DBConnection;

public class QuanLyTaiKhoanController {
	public static QuanLyTaiKhoan qltk = new QuanLyTaiKhoan();
	public static void AddTK(TaiKhoan tk)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qltk.AddTK(conn,tk);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public static void UpdateTK(TaiKhoan tk)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qltk.UpdateTK(conn,tk);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void DeleteTK(String tentk)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qltk.DeleteTK(conn, tentk);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static TaiKhoan FindTK(String tentk)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			return qltk.FindTK(conn, tentk);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static List<TaiKhoan> FindAllTK(List<TaiKhoan> tk)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			return qltk.FindAllTK(conn,tk);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static void tablequanlytaikhoan(DefaultTableModel modelquanlytaikhoan ) throws ClassNotFoundException, SQLException
	{
		modelquanlytaikhoan.setRowCount(0);
		List<TaiKhoan> listtable = new ArrayList<TaiKhoan>();
		FindAllTK(listtable);
		for (int i = 0; i < listtable.size(); i++) 
		{
			TaiKhoan temp = listtable.get(i);
			modelquanlytaikhoan.addRow(new Object[] {temp.getUserName(), temp.getPassWord(),temp.getTenNV(), temp.getEmail(),temp.getQuyen()}); 
		}
	}

	public static void tabletimkiemquanlytaikhoan(DefaultTableModel modelquanlytaikhoan,String Key) throws ClassNotFoundException, SQLException 
	{
		modelquanlytaikhoan.setRowCount(0);
		
		TaiKhoan temp = FindTK(Key);
		modelquanlytaikhoan.addRow(new Object[] {temp.getUserName(), temp.getPassWord(),temp.getTenNV(), temp.getEmail(),temp.getQuyen()}); 
		
	}

	
	public static TaiKhoan getselecttaikhoan(JTable tablequanlytaikhoan) throws ClassNotFoundException, SQLException 
	{
		int indextablequanlytaikhoan = tablequanlytaikhoan.getSelectedRow();
		List<TaiKhoan> listtable = new ArrayList<TaiKhoan>();
		FindAllTK(listtable);
		TaiKhoan tk = listtable.get(indextablequanlytaikhoan);
		return tk;
	}
}
