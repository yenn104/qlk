package qlk.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qlk.entity.ChiTietPX;

public class ChiTietXuatDB {

	public static void Add(Connection conn, ChiTietPX ctpx) throws SQLException {
		String sql = "INSERT INTO ChiTietPhieuXuat (soPhieu, maDT, slg)  values (?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, ctpx.getSoPhieu());
		pstm.setInt(2, ctpx.getMaDT());
		pstm.setInt(3, ctpx.getSlg());
		pstm.executeUpdate();
	}

	public static void Update(Connection conn, ChiTietPX ctpx) throws SQLException {
		String sql = "UPDATE ChiTietPhieuXuat SET slg=? WHERE id=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, ctpx.getSlg());
		pstm.setInt(2, ctpx.getId());
		pstm.executeUpdate();
		
	}

	public static List<ChiTietPX> Find(Connection conn, int soPhieu) throws SQLException {
		String sql = "SELECT id, maDT, slg FROM ChiTietPhieuXuat WHERE soPhieu = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, soPhieu);
		ResultSet rs = pstm.executeQuery();
		
		List<ChiTietPX> list = new ArrayList<ChiTietPX>();
		while (rs.next())
		{			
			int id = rs.getInt("id");
			int maDT = rs.getInt("MaDT");
			int slg = rs.getInt("slg");
			
			ChiTietPX ctpx = new ChiTietPX();
			ctpx.setId(id);
			ctpx.setMaDT(maDT);
			ctpx.setSlg(slg);
			
			list.add(ctpx);
		}return list;
	}

}
