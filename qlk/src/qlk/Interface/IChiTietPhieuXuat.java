package qlk.Interface;

import java.sql.Connection;
import java.util.List;

import qlk.entity.ChiTietPX;

public interface IChiTietPhieuXuat {
	public void Add(Connection conn,ChiTietPX ctpx);  
	public void Update(Connection conn,ChiTietPX ctpx);  
	//public void Delete(Connection conn,int MaPX);
	//public void UpdateSlg(Connection conn,int id, int slg);
	public List<ChiTietPX> Find(Connection conn,int soPhieu);

}
