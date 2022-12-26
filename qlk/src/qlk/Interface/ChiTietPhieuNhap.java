package qlk.Interface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import qlk.entity.ChiTietPN;
import qlk.model.ChiTietNhapDB;

public class ChiTietPhieuNhap implements IChiTietPhieuNhap {

	@Override
	public void Add(Connection conn, ChiTietPN ctpn) {
		try {
			ChiTietNhapDB.Add(conn, ctpn);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void Update(Connection conn, ChiTietPN ctpn) {
		try {
			ChiTietNhapDB.Update(conn, ctpn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	@Override
//	public void UpdateSlg(Connection conn, int id, int slg) {
//		ChiTietNhapDB.UpdateSlg(conn, id, slg);
//		
//	}

	@Override
	public List<ChiTietPN> Find(Connection conn, int soPhieu) {
		try {
			return ChiTietNhapDB.Find(conn, soPhieu);
		} catch (SQLException e) {
			e.printStackTrace();
		}return null; //////////////RETURN GI DAY
	}

	
}
