package qlk.Interface;

import java.sql.Connection;
import java.util.List;
import qlk.entity.PhieuXuat;

public interface IQuanLyXuatKho {
	public void AddXK(Connection conn,PhieuXuat px);
	public void UpdateXK(Connection conn,PhieuXuat px);
	public void DeleteXK(Connection conn,int MaPX);
	public PhieuXuat FindXK(Connection conn,int MaPX);
	public List<PhieuXuat>FindAllXK(Connection conn,List<PhieuXuat> px);
}
