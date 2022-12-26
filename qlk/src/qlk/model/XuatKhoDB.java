package qlk.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qlk.entity.PhieuXuat;

public class XuatKhoDB {
	public static List<PhieuXuat> AllXK(Connection conn) throws SQLException 
	{
		String sql = "SELECT soPhieu, ngay, tenNV, thanhTien, tinhTrang, lydo FROM PhieuXuat";
		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();
		List<PhieuXuat> list = new ArrayList<PhieuXuat>();
		while (rs.next())
		{
			int soPhieu = rs.getInt("soPhieu");
			Date ngay = rs.getDate("ngay");
			String tenNV = rs.getString("tenNV");
			double thanhTien = rs.getDouble("thanhTien");
			boolean tinhTrang = rs.getBoolean("tinhTrang");
			String lydo = rs.getString("lydo");
			
			PhieuXuat px = new PhieuXuat();
			px.setSoPhieu(soPhieu);
			px.setNgay(ngay);
			px.setTenNV(tenNV);
			px.setThanhTien(thanhTien);
			px.setTinhTrang(tinhTrang);
			px.setLydo(lydo);;
			
			list.add(px);
		}
		return list;
	}
	
	public static void AddXK(Connection conn, PhieuXuat px) throws SQLException 
	{
		String sql = "INSERT INTO PhieuXuat (ngay, tenNV, tinhTrang, lydo)  values (?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setDate(1, px.getNgay());
		pstm.setString(2, px.getTenNV());
		pstm.setBoolean(3, px.isTinhTrang());
		pstm.setString(4, px.getLydo());
		pstm.executeUpdate();
	}
	
	public static void UpdateXK(Connection conn, PhieuXuat px) throws SQLException
	{
		String sql = "UPDATE PhieuXuat SET ngay=?, tenNV=?, thanhTien=?, tinhTrang=?, lydo=? WHERE soPhieu=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setDate(1, px.getNgay());
		pstm.setString(2, px.getTenNV());
		pstm.setDouble(3, px.getThanhTien());
		pstm.setBoolean(4, px.isTinhTrang());
		pstm.setString(5, px.getLydo());
		pstm.setInt(6, px.getSoPhieu());
		pstm.executeUpdate();
	}
	
	public static void DeleteXK(Connection conn, int MaPX) throws SQLException 
	{
		String sql = "DELETE FROM PhieuXuat WHERE soPhieu= ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, MaPX);
		pstm.executeUpdate();
	}
	
	
	public static PhieuXuat FindXK(Connection conn,  int MaPX) throws SQLException
	{
		String sql = "SELECT ngay, tenNV, thanhTien, tinhTrang, lydo FROM PhieuXuat WHERE soPhieu = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, MaPX);
		ResultSet rs = pstm.executeQuery();
		
		if(rs.next()) 
		{			
			Date ngay = rs.getDate("ngay");
			String tenNV = rs.getString("tenNV");
			double thanhTien = rs.getDouble("thanhTien");
			boolean tinhTrang = rs.getBoolean("tinhTrang");
			String lydo = rs.getString("lydo");
			
			PhieuXuat px = new PhieuXuat();
			px.setSoPhieu(MaPX);
			px.setNgay(ngay);
			px.setTenNV(tenNV);
			px.setThanhTien(thanhTien);
			px.setTinhTrang(tinhTrang);
			px.setLydo(lydo);
			return px;
		}
		return null;
	}
}
