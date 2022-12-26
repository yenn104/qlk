package qlk.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qlk.entity.DienThoai;

public class DienThoaiDB {
	//public static Connection conn; 
	public static List<DienThoai> AllProduct(Connection conn) throws SQLException 
	{	
		String sql = "Select MaDT, tenDT, hangDT, mauSac, thongSo, thongTin, slg, gia FROM DienThoai ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<DienThoai> list = new ArrayList<DienThoai>();
		while (rs.next()) 
		{
			int MaDT = rs.getInt("MaDT");
			String tenDT = rs.getString("tenDT");
			String hangDT = rs.getString("hangDT");
			String mauSac = rs.getString("mauSac");
			String thongSo = rs.getString("thongSo");
			String thongTin = rs.getString("thongTin");
			int slg = rs.getInt("slg");
			double gia = rs.getDouble("gia");
			
			DienThoai dt = new DienThoai();
				dt.setMaDT(MaDT);
				dt.setTenDT(tenDT);
				dt.setHangDT(hangDT);
				dt.setMauSac(mauSac);
				dt.setThongSo(thongSo);
				dt.setThongTin(thongTin);
				dt.setSlg(slg);
				dt.setGia(gia);
				list.add(dt);
		}return list;
	}

	public static void updateProduct(Connection conn, DienThoai dt) throws SQLException 
	{
		String sql = "Update DienThoai set tenDT =?, hangDT=?, mauSac=?, thongSo=?, thongTin=?, slg=?, gia=? where MaDT = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, dt.getTenDT());
		pstm.setString(2, dt.getHangDT());
		pstm.setString(3, dt.getMauSac());
		pstm.setString(4, dt.getThongSo());
		pstm.setString(5, dt.getThongTin());
		pstm.setInt(6, dt.getSlg());
		pstm.setDouble(7, dt.getGia());
		pstm.setInt(8, dt.getMaDT());
		pstm.executeUpdate();
	}
	
	public static void updateSlgProduct(Connection conn, int MaDT, int slg) throws SQLException 
	{
		String sql = "Update DienThoai set slg=? where MaDT = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1,slg);
		pstm.setInt(2, MaDT);
		pstm.executeUpdate();
	}

	public static void insertProduct(Connection conn, DienThoai dt) throws SQLException
	{
		String sql = "Insert into DienThoai(tenDT, hangDT, mauSac, thongSo, thongTin, slg, gia) values (?,?,?,?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, dt.getTenDT());
		pstm.setString(2, dt.getHangDT());
		pstm.setString(3, dt.getMauSac());
		pstm.setString(4, dt.getThongSo());
		pstm.setString(5, dt.getThongTin());
		pstm.setInt(6, dt.getSlg());
		pstm.setDouble(7, dt.getGia());
		pstm.executeUpdate();
	}

	public static void deleteProduct(Connection conn, int MaDT) throws SQLException
	{		
		String sql = "Delete From DienThoai where MaDT= ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, MaDT);
		pstm.executeUpdate();
	}

	public static DienThoai findProduct(Connection conn, int MaDT) throws SQLException
	{
		String sql = "Select MaDT, tenDT, hangDT, mauSac, thongSo, thongTin, slg, gia FROM DienThoai WHERE MaDT=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, MaDT);
		ResultSet rs = pstm.executeQuery();
		if(rs.next()){		
			String tenDT = rs.getString("tenDT");
			String hangDT = rs.getString("hangDT");
			String mauSac = rs.getString("mauSac");
			String thongSo = rs.getString("thongSo");
			String thongTin = rs.getString("thongTin");
			int slg = rs.getInt("slg");
			double gia = rs.getDouble("gia");
			
			DienThoai dt = new DienThoai();
				dt.setMaDT(MaDT);
				dt.setTenDT(tenDT);
				dt.setHangDT(hangDT);
				dt.setMauSac(mauSac);
				dt.setThongSo(thongSo);
				dt.setThongTin(thongTin);
				dt.setSlg(slg);
				dt.setGia(gia);
			return dt;
		} return null;
	}
	
	public static List<DienThoai> findListDT(Connection conn, String key) throws SQLException 
	{	
		String keys = "%"+key+"%";
		String sql="Select MaDT, tenDT, hangDT, mauSac, thongSo, thongTin, slg, gia FROM DienThoai WHERE tenDT like ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1,keys);
		ResultSet rs = pstm.executeQuery();
		List<DienThoai> list = new ArrayList<DienThoai>();	
		while (rs.next()) 
		{
			int MaDT = rs.getInt("MaDT");
			String tenDT = rs.getString("tenDT");
			String hangDT = rs.getString("hangDT");
			String mauSac = rs.getString("mauSac");
			String thongSo = rs.getString("thongSo");
			String thongTin = rs.getString("thongTin");
			int slg = rs.getInt("slg");
			double gia = rs.getDouble("gia");
			
			DienThoai dt = new DienThoai();
				dt.setMaDT(MaDT);
				dt.setTenDT(tenDT);
				dt.setHangDT(hangDT);
				dt.setMauSac(mauSac);
				dt.setThongSo(thongSo);
				dt.setThongTin(thongTin);
				dt.setSlg(slg);
				dt.setGia(gia);
				list.add(dt);
		}return list;
	}
	
	
	public static List<DienThoai> OrderbyDT(Connection conn, int key) throws SQLException 
	{	
		String i1 = "Select MaDT, tenDT, hangDT, mauSac, thongSo, thongTin, slg, gia FROM DienThoai ORDER BY tenDT ASC ";
		String i2 = "Select MaDT, tenDT, hangDT, mauSac, thongSo, thongTin, slg, gia FROM DienThoai ORDER BY tenDT DESC ";
		String i3 = "Select MaDT, tenDT, hangDT, mauSac, thongSo, thongTin, slg, gia FROM DienThoai ORDER BY slg ASC ";
		String i4 = "Select MaDT, tenDT, hangDT, mauSac, thongSo, thongTin, slg, gia FROM DienThoai ORDER BY slg DESC ";
		String sql="";
		
		if(key == 1){
			sql = i1;
		}else if(key == 2){
			sql = i2;
		}else if(key == 3){
			sql = i3;
		}else if(key == 4){
			sql = i4;
		}						
		PreparedStatement pstm = conn.prepareStatement(sql);	
		
		
		ResultSet rs = pstm.executeQuery();
		List<DienThoai> list = new ArrayList<DienThoai>();		
			
	
		while (rs.next()) 
		{
			int MaDT = rs.getInt("MaDT");
			String tenDT = rs.getString("tenDT");
			String hangDT = rs.getString("hangDT");
			String mauSac = rs.getString("mauSac");
			String thongSo = rs.getString("thongSo");
			String thongTin = rs.getString("thongTin");
			int slg = rs.getInt("slg");
			double gia = rs.getDouble("gia");
			
			DienThoai dt = new DienThoai();
				dt.setMaDT(MaDT);
				dt.setTenDT(tenDT);
				dt.setHangDT(hangDT);
				dt.setMauSac(mauSac);
				dt.setThongSo(thongSo);
				dt.setThongTin(thongTin);
				dt.setSlg(slg);
				dt.setGia(gia);
				list.add(dt);
		}return list;
	}
	
}
