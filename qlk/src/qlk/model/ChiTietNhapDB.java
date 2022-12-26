package qlk.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qlk.entity.ChiTietPN;

public class ChiTietNhapDB {
	public static List<ChiTietPN> Find(Connection conn, int soPhieu) throws SQLException {
		String sql = "SELECT id, maDT, slg, giaNhap, tongTien FROM ChiTietPhieuNhap WHERE soPhieu = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, soPhieu);
		ResultSet rs = pstm.executeQuery();
		
		List<ChiTietPN> list = new ArrayList<ChiTietPN>();
		while (rs.next())
		{			
			int id = rs.getInt("id");
			int maDT = rs.getInt("MaDT");
			int slg = rs.getInt("slg");
			double giaNhap = rs.getDouble("giaNhap");
			double tongTien = rs.getDouble("tongTien");
			
			ChiTietPN ctpn = new ChiTietPN();
			ctpn.setId(id);
			ctpn.setMaDT(maDT);
			ctpn.setSlg(slg);
			ctpn.setGiaNhap(giaNhap);
			ctpn.setTongTien(tongTien);
			
			list.add(ctpn);
		}return list;
	}
	
	public static void Add(Connection conn, ChiTietPN ctpn) throws SQLException {
		String sql = "INSERT INTO ChiTietPhieuNhap (soPhieu, maDT, slg, giaNhap, tongTien)  values (?,?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, ctpn.getSoPhieu());
		pstm.setInt(2, ctpn.getMaDT());
		pstm.setInt(3, ctpn.getSlg());
		pstm.setDouble(4, ctpn.getGiaNhap());
		pstm.setDouble(5, (ctpn.getGiaNhap()*ctpn.getSlg()));
		pstm.executeUpdate();
	}

	public static void Update(Connection conn, ChiTietPN ctpn) throws SQLException {
		String sql = "UPDATE ChiTietPhieuNhap SET slg=?, giaNhap=?, tongTien=? WHERE id=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setInt(1, ctpn.getSlg());
		pstm.setDouble(2, ctpn.getGiaNhap());
		pstm.setDouble(3, (ctpn.getGiaNhap()*ctpn.getSlg()));
		pstm.setInt(4, ctpn.getId());
		pstm.executeUpdate();
	}
	

//	public static void UpdateSlg(Connection conn, int id, int slg) {
//		String sql = "UPDATE ChiTietPhieuNhap SET slg=?, tongTien=? WHERE id=?";
//		PreparedStatement pstm = conn.prepareStatement(sql);
//		pstm.setInt(1, slg);
//		pstm.setDouble(2, (ctpn.getGiaNhap()*ctpn.getSlg()));
//		pstm.setInt(3, id);
//
//		
//		pstm.executeUpdate();
//	}


}
