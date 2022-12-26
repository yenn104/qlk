package qlk.Interface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import qlk.entity.DienThoai;
import qlk.model.DienThoaiDB;

public class QuanLySanPham implements IQuanLySanPham{

	@Override
	public void AddDT(Connection conn, DienThoai dt) {
		try {
			DienThoaiDB.insertProduct(conn, dt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void UpdateDT(Connection conn, DienThoai dt) {
		try {
			DienThoaiDB.updateProduct(conn, dt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void UpdateSlgDT(Connection conn, int MaDT, int slg){
		try {
			DienThoaiDB.updateSlgProduct(conn, MaDT, slg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void DeleteDT(Connection conn, int MaDT) {
		try {
			DienThoaiDB.deleteProduct(conn, MaDT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public DienThoai FindDT(Connection conn, int MaDT) {
		
		try {
			return DienThoaiDB.findProduct(conn, MaDT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<DienThoai> findListDT(Connection conn, String key, List<DienThoai> dt) {
		try {
			dt.addAll(DienThoaiDB.findListDT(conn, key));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dt;
	}

	@Override
	public List<DienThoai> OrderbyDT(Connection conn, int key, List<DienThoai> dt){
		try {
			dt.addAll(DienThoaiDB.OrderbyDT(conn, key));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dt;
	}

	@Override
	public List<DienThoai> FindAllDT(Connection conn, List<DienThoai> dt) {
		try {
			dt.addAll(DienThoaiDB.AllProduct(conn));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dt;
	}
	
}
