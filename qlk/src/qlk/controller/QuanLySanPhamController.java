package qlk.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import qlk.Interface.QuanLySanPham;
import qlk.entity.DienThoai;
import qlk.model.DBConnection;

public class QuanLySanPhamController  {
	static QuanLySanPham qlsp = new QuanLySanPham();
	
	public static void AddDT(DienThoai dt)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qlsp.AddDT(conn,dt);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		
	}
	public static void UpdateDT(DienThoai dt)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qlsp.UpdateDT(conn,dt);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void UpdateSlgDT(int masp, int slg)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qlsp.UpdateSlgDT(conn,masp, slg);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void DeleteDT(int masp)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qlsp.DeleteDT(conn,masp);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static DienThoai FindDT(int masp)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			return qlsp.FindDT(conn, masp);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static List<DienThoai> FindAllDT(List<DienThoai> dt)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			return qlsp.FindAllDT(conn, dt);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static List<DienThoai> OrderbyDT(int key, List<DienThoai> dt)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			return qlsp.OrderbyDT(conn, key,dt);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<DienThoai> findListDT(String key, List<DienThoai> dt)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			return qlsp.findListDT(conn, key, dt);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void tablequanlysanpham(DefaultTableModel modelquanlysanpham) throws ClassNotFoundException, SQLException 
	{
		modelquanlysanpham.setRowCount(0);
		List<DienThoai> listtable = new ArrayList<DienThoai>();
		FindAllDT(listtable);
		for (int i = 0; i < listtable.size(); i++) 
		{
			DienThoai sptemp = listtable.get(i);
			modelquanlysanpham.addRow(new Object[] {sptemp.getMaDT(), sptemp.getTenDT(), sptemp.getHangDT(),
			sptemp.getMauSac(), sptemp.getThongSo(), sptemp.getThongTin(),sptemp.getSlg(), String.format("%.0f",sptemp.getGia()) });
		}
	}
	
	public static void tablesapxepsanpham(DefaultTableModel modelquanlysanpham, int key) throws ClassNotFoundException, SQLException 
	{
		modelquanlysanpham.setRowCount(0);
		List<DienThoai> listtable = new ArrayList<DienThoai>();
		OrderbyDT(key, listtable);
		for (int i = 0; i < listtable.size(); i++) 
		{
			DienThoai sptemp = listtable.get(i);
			modelquanlysanpham.addRow(new Object[] {sptemp.getMaDT(), sptemp.getTenDT(), sptemp.getHangDT(),
			sptemp.getMauSac(), sptemp.getThongSo(), sptemp.getThongTin(),sptemp.getSlg(), String.format("%.0f",sptemp.getGia()) });
		}
	}
	
	
	
	
//	public static void tabletimkiemquanlysanpham(DefaultTableModel modelquanlysanpham,int masp) throws ClassNotFoundException, SQLException 
//	{
//		modelquanlysanpham.setRowCount(0);
//		DienThoai sptemp = FindDT(masp);
//		modelquanlysanpham.addRow(new Object[] { sptemp.getMaDT(), sptemp.getTenDT(), sptemp.getHangDT(),
//		sptemp.getMauSac(), sptemp.getThongSo(), sptemp.getThongTin(),sptemp.getSlg(), String.format("%.0f",sptemp.getGia()) });
//	}
	
	public static void tabletimkiemquanlysanpham(DefaultTableModel modelquanlysanpham, String key) throws ClassNotFoundException, SQLException 
	{
		modelquanlysanpham.setRowCount(0);
		List<DienThoai> listtable = new ArrayList<DienThoai>();
		findListDT(key, listtable);
		for (int i = 0; i < listtable.size(); i++) 
		{
			DienThoai sptemp = listtable.get(i);
			modelquanlysanpham.addRow(new Object[] {sptemp.getMaDT(), sptemp.getTenDT(), sptemp.getHangDT(),
			sptemp.getMauSac(), sptemp.getThongSo(), sptemp.getThongTin(),sptemp.getSlg(), String.format("%.0f",sptemp.getGia()) });
		}
	}

	public static DienThoai getselectsanpham(JTable tablequanlysanpham) throws ClassNotFoundException, SQLException 
	{
		int indextablequanlysp = tablequanlysanpham.getSelectedRow();
		List<DienThoai> listtable = new ArrayList<DienThoai>();
		FindAllDT(listtable);
		DienThoai newsp = listtable.get(indextablequanlysp);
		return newsp;
	}
} 
