package qlk.Interface;

import java.sql.Connection;
import java.util.List;

import qlk.entity.PhieuNhap;
//sửa
public interface IQuanLyNhapKho {
	public void AddNK(Connection conn,PhieuNhap pn);
	public void UpdateNK(Connection conn,PhieuNhap pn);
	public void DeleteNK(Connection conn,int MaPN);
	public PhieuNhap FindNK(Connection conn,int MaPN);
	public List<PhieuNhap>FindAllNK(Connection conn,List<PhieuNhap> pn);
	public void UpdateThanhTien(Connection conn, PhieuNhap pn);
}
