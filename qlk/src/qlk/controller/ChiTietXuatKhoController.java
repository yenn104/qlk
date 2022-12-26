package qlk.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import qlk.Interface.ChiTietPhieuXuat;
import qlk.entity.ChiTietPX;
import qlk.model.DBConnection;

public class ChiTietXuatKhoController {
	public static ChiTietPhieuXuat ctpx = new ChiTietPhieuXuat();
	
	public static void Add(ChiTietPX ct)
	{
		try {
			Connection conn = DBConnection.getMySQLConnection();
			ctpx.Add(conn, ct);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void Update(ChiTietPX ct)
	{
		try {
			Connection conn = DBConnection.getMySQLConnection();
			ctpx.Update(conn, ct);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static  List<ChiTietPX> Find(int soPhieu)
	{
		try {
			Connection conn = DBConnection.getMySQLConnection();
			return ctpx.Find(conn,soPhieu);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  null;
	}
	
	public static void tablechitietphieuxuat(DefaultTableModel modelchitietphieuxuat, int mapx)
			throws ClassNotFoundException, SQLException {
		modelchitietphieuxuat.setRowCount(0);
		List<ChiTietPX> listtable = new ArrayList<ChiTietPX>();
	//	Connection connection = DBConnection.getMySQLConnection();
		listtable = Find(mapx);
		for (int i = 0; i < listtable.size(); i++) {
			ChiTietPX sptemp = listtable.get(i);
			modelchitietphieuxuat
					.addRow(new Object[] { sptemp.getId(), mapx,
							 sptemp.getMaDT(), sptemp.getSlg() });
		}
	}

	public static ChiTietPX getselectchitietphieuxuat(JTable tablechitietphieuxuat, int mapx)
			throws ClassNotFoundException, SQLException {
		int index = tablechitietphieuxuat.getSelectedRow();
		List<ChiTietPX> listtable = new ArrayList<ChiTietPX>();
		listtable = Find(mapx);
		ChiTietPX ctxk = listtable.get(index);
		return ctxk;
	}
}
