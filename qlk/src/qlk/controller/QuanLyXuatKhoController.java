package qlk.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import qlk.Interface.QuanLyXuatKho;
import qlk.entity.PhieuXuat;
import qlk.model.DBConnection;

public class QuanLyXuatKhoController {
	public static QuanLyXuatKho qlxk = new QuanLyXuatKho();

	public static void AddXK(PhieuXuat xk) {
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qlxk.AddXK(conn,xk);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void UpdateXK(PhieuXuat xk) {
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qlxk.UpdateXK(conn,xk);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static void DeleteXK(int MaPX) {
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qlxk.DeleteXK(conn,MaPX);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static List<PhieuXuat> FindAllXK(List<PhieuXuat> px) {
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			return qlxk.FindAllXK(conn,px);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 public static PhieuXuat FindXK(int MaPX)
	 { 
			Connection conn;
			try {
				conn = DBConnection.getMySQLConnection();
				 return qlxk.FindXK(conn,MaPX);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
	 }
	 

	public static void tablephieuxuatkho(DefaultTableModel modelphieuxuatkho)
			throws ClassNotFoundException, SQLException {
		modelphieuxuatkho.setRowCount(0);
		List<PhieuXuat> listtable = new ArrayList<PhieuXuat>();
		FindAllXK(listtable);
		for (int i = 0; i < listtable.size(); i++) {
			PhieuXuat sptemp = listtable.get(i);
			String tinhtrang = "";
			if (sptemp.isTinhTrang() == true) {
				tinhtrang = "Đã Xuất Kho";
			} else {
				tinhtrang = "Chưa Xuất Kho";
			}
			modelphieuxuatkho.addRow(new Object[] { sptemp.getSoPhieu(), sptemp.getNgay(), sptemp.getTenNV(),
					String.format("%.0f",sptemp.getThanhTien()), tinhtrang, sptemp.getLydo() });
		}
	}

	public static void tabletimphieuxuatkho(DefaultTableModel modelphieuxuatkho,int mapx)
			throws ClassNotFoundException, SQLException {
		modelphieuxuatkho.setRowCount(0);
		PhieuXuat px = FindXK(mapx);
		String tinhtrang = "";
		if (px.isTinhTrang() == true) {
			tinhtrang = "Đã Xuất Kho";
		} else {
			tinhtrang = "Chưa Xuất Kho";
		}
		modelphieuxuatkho.addRow(new Object[] { px.getSoPhieu(), px.getNgay(), px.getTenNV(), px.getThanhTien(),
				tinhtrang, px.getLydo() });

	}

	public static PhieuXuat getselectphieuxuatkho(JTable tablephieuxuatkho)
		throws ClassNotFoundException, SQLException {
		int indextablequanlysp = tablephieuxuatkho.getSelectedRow();
		List<PhieuXuat> listtable = new ArrayList<PhieuXuat>();
		FindAllXK(listtable);
		PhieuXuat newsp = listtable.get(indextablequanlysp);
		return newsp;
	}

}
