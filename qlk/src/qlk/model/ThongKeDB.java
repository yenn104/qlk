package qlk.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qlk.Interface.ChiTietPhieuNhap;
import qlk.Interface.ChiTietPhieuXuat;
import qlk.entity.ChiTietPN;
import qlk.entity.ChiTietPX;
import qlk.entity.ChiTietPhieuThongKe;
import qlk.entity.DienThoai;
import qlk.entity.PhieuNhap;
import qlk.entity.PhieuThongKe;
import qlk.entity.PhieuXuat;


public class ThongKeDB {
	public static List<ChiTietPhieuThongKe> thongke(Connection connection, Date ngaybd, Date ngaykt)
			throws SQLException, ClassNotFoundException {
		List<ChiTietPhieuThongKe> CTPTKTK = new ArrayList<ChiTietPhieuThongKe>();
		List<DienThoai> ALLSANPHAM = new ArrayList<DienThoai>();
		ALLSANPHAM = DienThoaiDB.AllProduct(connection);
		String sqlnhapdauky = "SELECT soPhieu FROM phieunhap WHERE ngay <= ?";
		PreparedStatement pstm = connection.prepareStatement(sqlnhapdauky);
		pstm.setDate(1, ngaybd);
		ResultSet rsnhapdauky = pstm.executeQuery();
		
		String sqlxuatdauky = "SELECT soPhieu FROM phieuxuat WHERE ngay <= ?";
		PreparedStatement pstmxuatdauky = connection.prepareStatement(sqlxuatdauky);
		pstmxuatdauky.setDate(1, ngaybd);
		ResultSet rsxuatdauky = pstmxuatdauky.executeQuery();

		// list phieu xuat nhap trước ngày bắt đầu
		List<PhieuNhap> listphieunhapdauky = new ArrayList<PhieuNhap>();
		while (rsnhapdauky.next()) {
			int sophieu = rsnhapdauky.getInt("soPhieu");
			PhieuNhap pntemp = new PhieuNhap();
			pntemp.setSoPhieu(sophieu);
			listphieunhapdauky.add(pntemp);
		}
		List<PhieuXuat> listphieuxuatdauky = new ArrayList<PhieuXuat>();
		while (rsxuatdauky.next()) {
			int sophieu = rsxuatdauky.getInt("soPhieu");
			PhieuXuat pxtemp = new PhieuXuat();
			pxtemp.setSoPhieu(sophieu);
			listphieuxuatdauky.add(pxtemp);
		}

		// thao tác trong list để cho ra kết quả là tồn đầu kỳ
		List<ChiTietPN> listCTPNdauky = new ArrayList<ChiTietPN>();
		List<ChiTietPX> listCTPXdauky = new ArrayList<ChiTietPX>();

		for (int i = 0; i < listphieunhapdauky.size(); i ++)
		{
			listCTPNdauky.addAll(ChiTietNhapDB.Find(connection, listphieunhapdauky.get(i).getSoPhieu()));
		}
		for (int i = 0; i < listphieuxuatdauky.size(); i ++)
		{
			listCTPXdauky.addAll(ChiTietXuatDB.Find(connection, listphieuxuatdauky.get(i).getSoPhieu()));
		}
		
		
		// list xuất nhập trong khoản ngày bắt đầu và ngày kết thúc
		
		String sqlnhaptrongky = "SELECT soPhieu FROM phieunhap WHERE ngay >= ? AND ngay <= ? ";
		PreparedStatement pstmnhaptrongky = connection.prepareStatement(sqlnhaptrongky);
		pstmnhaptrongky.setDate(1, ngaybd);
		pstmnhaptrongky.setDate(2, ngaykt);
		ResultSet rsnhaptrongky = pstmnhaptrongky.executeQuery();
		
		String sqlxuattrongky = "SELECT soPhieu FROM phieuxuat WHERE ngay >= ? AND ngay <= ? ";
		PreparedStatement pstmxuattrongky = connection.prepareStatement(sqlxuattrongky);
		pstmxuattrongky.setDate(1, ngaybd);
		pstmxuattrongky.setDate(2, ngaykt);
		ResultSet rsxuattrongky = pstmxuattrongky.executeQuery();
		
		
		// list phieu xuat nhap trong kỳ
		List<PhieuNhap> listphieunhaptrongky = new ArrayList<PhieuNhap>();
		while (rsnhaptrongky.next()) {
			int sophieu = rsnhaptrongky.getInt("soPhieu");
			PhieuNhap pntemp = new PhieuNhap();
			
			pntemp.setSoPhieu(sophieu);
			listphieunhaptrongky.add(pntemp);
		}
		List<PhieuXuat> listphieuxuattrongky = new ArrayList<PhieuXuat>();
		while (rsxuattrongky.next()) {
			int sophieu = rsxuattrongky.getInt("soPhieu");
			PhieuXuat pxtemp = new PhieuXuat();
			pxtemp.setSoPhieu(sophieu);
			listphieuxuattrongky.add(pxtemp);
		}

		// thao tác trong list để cho ra kết quả là tồn trong kỳ
		List<ChiTietPN> listCTPNtrongky = new ArrayList<ChiTietPN>();
		List<ChiTietPX> listCTPXtrongky = new ArrayList<ChiTietPX>();

		for (int i = 0; i < listphieunhaptrongky.size(); i ++)
		{
			listCTPNtrongky.addAll(ChiTietNhapDB.Find(connection, listphieunhaptrongky.get(i).getSoPhieu()));
		}
		for (int i = 0; i < listphieuxuattrongky.size(); i ++)
		{
			listCTPXtrongky.addAll(ChiTietXuatDB.Find(connection, listphieuxuattrongky.get(i).getSoPhieu()));
		}
		

		
		// thiết lập dữ liệu tồn đầu kỳ, tồn cuối kỳ, nhập trong kỳ, xuất trong kỳ  và tồn cuối kỳ cho danh sách sản phẩm
		for (int i = 0; i < ALLSANPHAM.size(); i++) {
			ChiTietPhieuThongKe tempctptk = new ChiTietPhieuThongKe();
			//tempctptk.setMaphieuthongke(i);
			tempctptk.setMadt(ALLSANPHAM.get(i).getMaDT());
			tempctptk.setTendt(ALLSANPHAM.get(i).getTenDT());
			int slsptondaukynhap = 0;
			for (int j = 0; j < listCTPNdauky.size(); j++) {
				if (listCTPNdauky.get(j).getMaDT() == ALLSANPHAM.get(i).getMaDT()) {
					slsptondaukynhap += listCTPNdauky.get(j).getSlg();
				}
			}

			int slsptondaukyxuat = 0;
			for (int j = 0; j < listCTPXdauky.size(); j++) {
				if (listCTPXdauky.get(j).getMaDT() == ALLSANPHAM.get(i).getMaDT()) {
					slsptondaukyxuat += listCTPXdauky.get(j).getSlg();
				}
			}
			int slsptondauky = slsptondaukynhap - slsptondaukyxuat;

			tempctptk.setTondauky(slsptondauky);

			int slspnhaptrongky = 0;
			for (int j = 0; j < listCTPNtrongky.size(); j++) {
				
				if (listCTPNtrongky.get(j).getMaDT() == ALLSANPHAM.get(i).getMaDT()) {
					slspnhaptrongky += listCTPNtrongky.get(j).getSlg();
				}
			}
			tempctptk.setNhaptrongky(slspnhaptrongky);

			int slspxuattrongky = 0;
			for (int j = 0; j < listCTPXtrongky.size(); j++) {
				if (listCTPXtrongky.get(j).getMaDT() == ALLSANPHAM.get(i).getMaDT()) {
					slspxuattrongky += listCTPXtrongky.get(j).getSlg();
				}
			}
			tempctptk.setXuattrongky(slspxuattrongky);
			tempctptk.setToncuoiky((slsptondauky + slspnhaptrongky) - slspxuattrongky);
			double giatriton = tempctptk.getToncuoiky() * ALLSANPHAM.get(i).getGia();
			tempctptk.setGiatritonkho(giatriton);
			CTPTKTK.add(tempctptk);
		}
		return CTPTKTK;
	}

	public static PhieuThongKe insertPhieuThongKeTonKho(Connection conn, PhieuThongKe tktk) throws SQLException {
		String sql = "INSERT INTO `phieuthongke`(ngaybatdau, ngayketthuc) VALUES (?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setDate(1, tktk.getNgaybatdau());
		pstm.setDate(2, tktk.getNgayketthuc());
		pstm.executeUpdate();
		
		String layphieuvuamoithem = "SELECT * FROM phieuthongke ORDER by maphieu DESC LIMIT 1";
		pstm = conn.prepareStatement(layphieuvuamoithem);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			int maphieu = rs.getInt("maphieu");
			PhieuThongKe newptk = new PhieuThongKe();
			newptk.setMaphieu(maphieu);
			return newptk;
		}
		return null;
	}
	
	public static List<PhieuThongKe> AllPhieuThongKe(Connection conn) throws SQLException {
		String sql = "SELECT maphieu, ngaybatdau, ngayketthuc FROM phieuthongke ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<PhieuThongKe> list = new ArrayList<PhieuThongKe>();
		while (rs.next()) {
			int maphieu = rs.getInt("maphieu");
			Date ngaybd = rs.getDate("ngaybatdau");
			Date ngaykt = rs.getDate("ngayketthuc");
			PhieuThongKe newptk = new PhieuThongKe(maphieu, ngaybd, ngaykt);
			list.add(newptk);
		}
		return list;
	}

	public static void insertchitietphieuthongke(Connection conn, List<ChiTietPhieuThongKe	> cttktk) throws SQLException {
		for (int i = 0; i < cttktk.size(); i++)
		{
			String sql = "INSERT INTO chitietphieuthongke( maphieuthongke, madt, tendt, tondauky, nhaptrongky, xuattrongky, toncuoiky, giatritonkho) values (?,?,?,?,?,?,?,?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, cttktk.get(i).getMaphieuthongke());
			pstm.setInt(2, cttktk.get(i).getMadt());
			pstm.setString(3, cttktk.get(i).getTendt());
			pstm.setInt(4, cttktk.get(i).getTondauky());
			pstm.setInt(5, cttktk.get(i).getNhaptrongky());
			pstm.setInt(6, cttktk.get(i).getXuattrongky());
			pstm.setInt(7, cttktk.get(i).getToncuoiky());
			pstm.setDouble(8, cttktk.get(i).getGiatritonkho());
			pstm.executeUpdate();
		}
	}
	
	
	public static PhieuThongKe findphieuthongke(Connection conn, int maphieu) throws SQLException {
		String layphieuvuamoithem = "SELECT * FROM phieuthongke WHERE maphieu = ?";
		PreparedStatement pstm  = conn.prepareStatement(layphieuvuamoithem);
		pstm.setInt(1, maphieu);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {

			int maphieutk = rs.getInt("maphieu");
			Date ngaybd = rs.getDate("ngaybatdau");
			Date ngaykt = rs.getDate("ngayketthuc");
			PhieuThongKe newptk = new PhieuThongKe(maphieutk, ngaybd, ngaykt);
			return newptk;
		}
		return null;
	}
	
	public static List<ChiTietPhieuThongKe> findchitietphieuthongke(Connection conn, int maphieu) throws SQLException {
		String sql = "SELECT maphieu, maphieuthongke, madt, tendt, tondauky, nhaptrongky, xuattrongky, toncuoiky, giatritonkho FROM chitietphieuthongke WHERE maphieuthongke = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, maphieu);
		ResultSet rs = pstm.executeQuery();
		List<ChiTietPhieuThongKe> list = new ArrayList<ChiTietPhieuThongKe>();
		while (rs.next()) {
			int maphieuchitiet = rs.getInt("maphieu");
			int maphieutonkho = rs.getInt("maphieuthongke");
			int masp = rs.getInt("madt");
			String tensp = rs.getString("tendt");
			int sltondauky = rs.getInt("tondauky");
			int slnhaptronky = rs.getInt("nhaptrongky");
			int slxuattrongky = rs.getInt("xuattrongky");
			int sltoncuoiky = rs.getInt("toncuoiky");
			Double giatritonkho = rs.getDouble("giatritonkho");
			ChiTietPhieuThongKe newptk = new ChiTietPhieuThongKe(maphieuchitiet, maphieutonkho, masp, sltondauky, sltoncuoiky, slnhaptronky, slxuattrongky, giatritonkho, tensp);
			list.add(newptk);
		}
		return list;
	}
	
	public static List<ChiTietPhieuThongKe> AllChiTietTon(Connection conn) throws SQLException {
		String sql = "SELECT maphieu, maphieuthongke, madt, tendt, tondauky, nhaptrongky, xuattrongky, toncuoiky, giatritonkho FROM chitietphieuthongke";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<ChiTietPhieuThongKe> list = new ArrayList<ChiTietPhieuThongKe>();
		while (rs.next()) {
			int maphieuchitiet = rs.getInt("maphieu");
			int maphieutonkho = rs.getInt("maphieuthongke");
			int masp = rs.getInt("madt");
			String tensp = rs.getString("tendt");
			int sltondauky = rs.getInt("tondauky");
			int slnhaptronky = rs.getInt("nhaptrongky");
			int slxuattrongky = rs.getInt("xuattrongky");
			int sltoncuoiky = rs.getInt("toncuoiky");
			Double giatritonkho = rs.getDouble("giatritonkho");
			ChiTietPhieuThongKe newptk = new ChiTietPhieuThongKe(maphieuchitiet, maphieutonkho, masp, sltondauky, sltoncuoiky, slnhaptronky, slxuattrongky, giatritonkho, tensp);
			list.add(newptk);
		}
		return list;
	}
	
}
