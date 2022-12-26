package qlk.Interface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import qlk.entity.PhieuNhap;
import qlk.model.NhapKhoDB;

public class QuanLyNhapKho implements IQuanLyNhapKho{

	@Override
	public void AddNK(Connection conn, PhieuNhap pn) {
		try {
			NhapKhoDB.AddNK(conn, pn);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void UpdateNK(Connection conn, PhieuNhap pn) {
		try {
			NhapKhoDB.UpdateNK(conn, pn);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	public void UpdateThanhTien(Connection conn,PhieuNhap nk)
	{
		try {
			NhapKhoDB.UpdateThanhTien(conn,nk);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void DeleteNK(Connection conn, int MaPN) {
		try {
			NhapKhoDB.DeleteNK(conn, MaPN);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public PhieuNhap FindNK(Connection conn, int MaPN) {
		try {
			return NhapKhoDB.FindNK(conn, MaPN);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PhieuNhap> FindAllNK(Connection conn, List<PhieuNhap> pn) {
		try {
			pn.addAll(NhapKhoDB.AllNK(conn));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pn;
	}
	
}
