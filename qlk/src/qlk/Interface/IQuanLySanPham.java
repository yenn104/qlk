package qlk.Interface;

import java.sql.Connection;
import java.util.List;

import qlk.entity.DienThoai;

public interface IQuanLySanPham {
	public void AddDT(Connection conn, DienThoai dt);
	public void UpdateDT(Connection conn, DienThoai dt);
	public void UpdateSlgDT(Connection conn, int MaDT, int slg);
	public void DeleteDT(Connection conn, int MaDT);
	public DienThoai FindDT(Connection conn, int MaDT);
	public List<DienThoai> FindAllDT(Connection conn, List<DienThoai> dt);
	public List<DienThoai> OrderbyDT(Connection conn,int key, List<DienThoai> dt);
	public List<DienThoai> findListDT(Connection conn, String key, List<DienThoai> dt);
	
}
