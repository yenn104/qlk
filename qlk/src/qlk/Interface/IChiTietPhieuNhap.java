package qlk.Interface;

import java.sql.Connection;
import java.util.List;

import qlk.entity.ChiTietPN;

public interface IChiTietPhieuNhap{ 
	public void Add(Connection conn, ChiTietPN ctpn);  
	public void Update(Connection conn, ChiTietPN ctpn);
	//public void UpdateSlg(Connection conn,int id, int slg);
	public List<ChiTietPN> Find(Connection conn, int soPhieu);
}
