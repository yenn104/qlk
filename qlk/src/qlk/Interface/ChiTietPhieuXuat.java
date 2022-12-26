package qlk.Interface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import qlk.entity.ChiTietPX;
import qlk.model.ChiTietXuatDB;

public class ChiTietPhieuXuat implements IChiTietPhieuXuat{

	@Override
	public void Add(Connection conn, ChiTietPX ctpx) {
		try {
			ChiTietXuatDB.Add(conn, ctpx);
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}

	@Override
	public void Update(Connection conn, ChiTietPX ctpx) {
		try {
			ChiTietXuatDB.Update(conn, ctpx);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}


//	@Override
//	public void UpdateSlg(Connection conn, int id, int slg) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public List<ChiTietPX> Find(Connection conn, int soPhieu) {
		try {
			return ChiTietXuatDB.Find(conn, soPhieu);
		} catch (SQLException e) {
			e.printStackTrace();
		}return null; //////////////RETURN GI DAY
	}

	
}
