package qlk.Interface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import qlk.entity.PhieuXuat;
import qlk.model.XuatKhoDB;

public class QuanLyXuatKho implements IQuanLyXuatKho{

	@Override
	public void AddXK(Connection conn, PhieuXuat px) {
		try {
			XuatKhoDB.AddXK(conn, px);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void UpdateXK(Connection conn, PhieuXuat px) {
		try {
			XuatKhoDB.UpdateXK(conn, px);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void DeleteXK(Connection conn, int MaPX) {
		try {
			XuatKhoDB.DeleteXK(conn, MaPX);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PhieuXuat FindXK(Connection conn, int MaPX) {
		try {
			return XuatKhoDB.FindXK(conn, MaPX);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PhieuXuat> FindAllXK(Connection conn, List<PhieuXuat> px) {
		try {
			px.addAll(XuatKhoDB.AllXK(conn));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return px;
	}

}
