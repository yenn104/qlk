package qlk.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import qlk.Interface.QuanLyNhapKho;
import qlk.entity.PhieuNhap;
import qlk.model.DBConnection;

public class QuanLyNhapKhoController {
	public static QuanLyNhapKho qlnk = new QuanLyNhapKho();
	public static void AddNK(PhieuNhap nk)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qlnk.AddNK(conn,nk);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void UpdateNK(PhieuNhap nk)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qlnk.UpdateNK(conn,nk);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void UpdateThanhTien(PhieuNhap nk)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qlnk.UpdateThanhTien(conn,nk);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void DeleteNK(int MaPN)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qlnk.DeleteNK(conn,MaPN);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static List<PhieuNhap>FindAllNK(List<PhieuNhap> pn)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			return qlnk.FindAllNK(conn,pn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static PhieuNhap FindNK(int MaPN)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			 return qlnk.FindNK(conn,MaPN); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	 

	public static void tablephieunhapkho(DefaultTableModel modelphieunhapkho) throws ClassNotFoundException, SQLException 
	{
		modelphieunhapkho.setRowCount(0);
		
		List<PhieuNhap> listtable = new ArrayList<PhieuNhap>();
		FindAllNK(listtable);
		for (int i = 0; i < listtable.size(); i++) 
		{
			PhieuNhap temp = listtable.get(i);
			String tinhtrang = "";
			if(temp.isTinhTrang() == true)
			{
				tinhtrang = "Đã Nhập Kho";
			}
			else
			{
				tinhtrang = "Chưa Nhập Kho";
			}
			modelphieunhapkho.addRow(new Object[] { temp.getSoPhieu(), temp.getNgay(),
					temp.getTenNV(), String.format("%.0f",temp.getThanhTien()), tinhtrang });
		}
	}
	public static void tabletimphieunhapkho(DefaultTableModel modelphieunhapkho, int MaPN) throws ClassNotFoundException, SQLException 
	{
			modelphieunhapkho.setRowCount(0);
		
			PhieuNhap pn = FindNK(MaPN);
			String tinhtrang = "";
			if(pn.isTinhTrang() == true)
			{
				tinhtrang = "Đã Xuất Kho";
			}
			else
			{
				tinhtrang = "Chưa Xuất Kho";
			}
			modelphieunhapkho.addRow(new Object[] { pn.getSoPhieu(), pn.getNgay(),
					pn.getTenNV(), String.format("%.0f",pn.getThanhTien()), tinhtrang});
		
	}

	public static PhieuNhap getselectphieunhapkho(JTable tablephieunhapkho)
			throws ClassNotFoundException, SQLException 
	{
		int indextablequanlysp = tablephieunhapkho.getSelectedRow();
		List<PhieuNhap> listtable = new ArrayList<PhieuNhap>();
		FindAllNK(listtable);
		PhieuNhap newsp = listtable.get(indextablequanlysp);
		return newsp;
	}

}
