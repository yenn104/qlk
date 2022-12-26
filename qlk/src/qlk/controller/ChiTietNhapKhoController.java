package qlk.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import qlk.Interface.ChiTietPhieuNhap;
import qlk.entity.ChiTietPN;
import qlk.model.DBConnection;

public class ChiTietNhapKhoController {
public static ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap();
	
	public static void Add(ChiTietPN ct)
	{
		try {
			Connection conn = DBConnection.getMySQLConnection();
			ctpn.Add(conn, ct);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void Update(ChiTietPN ct)
	{
		try {
			Connection conn = DBConnection.getMySQLConnection();
			ctpn.Update(conn, ct);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static  List<ChiTietPN> Find(int soPhieu)
	{
		try {
			Connection conn = DBConnection.getMySQLConnection();
			return ctpn.Find(conn,soPhieu);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  null;
	}
	
	public static void tablechitietphieunhap(DefaultTableModel modelchitietphieunhap, int mapn)
			throws ClassNotFoundException, SQLException {
		modelchitietphieunhap.setRowCount(0);
		List<ChiTietPN> listtable = new ArrayList<ChiTietPN>();
		listtable = Find(mapn);
		for (int i = 0; i < listtable.size(); i++) {
			ChiTietPN sptemp = listtable.get(i);
			modelchitietphieunhap
					.addRow(new Object[] { sptemp.getId(),mapn,
							 sptemp.getMaDT(), sptemp.getSlg(), String.format("%.0f",sptemp.getGiaNhap()), String.format("%.0f",sptemp.getTongTien()) });
		}
	}

	public static ChiTietPN getselectchitietphieunhap(JTable tablechitietphieunhap, int mapn)
			throws ClassNotFoundException, SQLException {
		int index = tablechitietphieunhap.getSelectedRow();
		List<ChiTietPN> listtable = new ArrayList<ChiTietPN>();
		listtable = Find(mapn);
		ChiTietPN ctnk = listtable.get(index);
		return ctnk;
	}
}
