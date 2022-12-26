package qlk.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qlk.entity.PhieuNhap;

public class NhapKhoDB {
	public static Connection conn;
	public static List<PhieuNhap> AllNK(Connection conn) throws SQLException 
	{
		String sql = "SELECT soPhieu, ngay, tenNV, thanhTien, tinhTrang FROM PhieuNhap";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<PhieuNhap> list = new ArrayList<PhieuNhap>();
		while (rs.next())
		{
			int soPhieu = rs.getInt("soPhieu");
			Date ngay = rs.getDate("ngay");
			String tenNV = rs.getString("tenNV");
			double thanhTien = rs.getDouble("thanhTien");
			boolean tinhTrang = rs.getBoolean("tinhTrang");
			
			PhieuNhap pn = new PhieuNhap();
			pn.setSoPhieu(soPhieu);
			pn.setNgay(ngay);
			pn.setTenNV(tenNV);
			pn.setThanhTien(thanhTien);
			pn.setTinhTrang(tinhTrang);
			
			list.add(pn);
		}
		return list;
	}
	
	public static void AddNK(Connection conn, PhieuNhap pn) throws SQLException 
	{
		String sql = "INSERT INTO PhieuNhap (ngay, tenNV, tinhTrang)  values (?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setDate(1, pn.getNgay());
		pstm.setString(2, pn.getTenNV());
		pstm.setBoolean(3, pn.isTinhTrang());
		pstm.executeUpdate();
	}
	
	public static void UpdateNK(Connection conn, PhieuNhap pn) throws SQLException
	{
		String sql = "UPDATE PhieuNhap SET  ngay=?, tenNV=?, thanhTien=?, tinhTrang=? WHERE soPhieu=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setDate(1, pn.getNgay());
		pstm.setString(2, pn.getTenNV());
		pstm.setDouble(3, pn.getThanhTien());
		pstm.setBoolean(4, pn.isTinhTrang());
		pstm.setInt(5, pn.getSoPhieu());
		pstm.executeUpdate();
	}
	
	public static void DeleteNK(Connection conn, int MaPN) throws SQLException 
	{
		String sql = "DELETE FROM PhieuNhap WHERE soPhieu= ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, MaPN);
		pstm.executeUpdate();
	}
	public static void UpdateThanhTien(Connection conn, PhieuNhap pn) throws SQLException
	{
		String sql = "UPDATE phieunhap set thanhTien = (SELECT SUM(pn.tongTien) FROM chitietphieunhap pn WHERE pn.soPhieu =? GROUP by soPhieu) WHERE soPhieu =?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, pn.getSoPhieu());
		pstm.setInt(2, pn.getSoPhieu());
		pstm.executeUpdate();
	}
	
	public static PhieuNhap FindNK(Connection conn, int MaPN) throws SQLException
	{
		String sql = "SELECT ngay, tenNV, thanhTien, tinhTrang FROM PhieuNhap WHERE soPhieu = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, MaPN);
		ResultSet rs = pstm.executeQuery();
		
		if(rs.next()) 
		{			
			Date ngay = rs.getDate("ngay");
			String tenNV = rs.getString("tenNV");
			double thanhTien = rs.getDouble("thanhTien");
			boolean tinhTrang = rs.getBoolean("tinhTrang");
			
			PhieuNhap pn = new PhieuNhap();
			pn.setSoPhieu(MaPN);
			pn.setNgay(ngay);
			pn.setTenNV(tenNV);
			pn.setThanhTien(thanhTien);
			pn.setTinhTrang(tinhTrang);
			return pn;
		}
		return null;
	}
	
}
