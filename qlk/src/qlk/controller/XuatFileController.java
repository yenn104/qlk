package qlk.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import qlk.Interface.QuanLyNhapKho;
import qlk.Interface.QuanLySanPham;
import qlk.Interface.QuanLyTaiKhoan;
import qlk.Interface.QuanLyXuatKho;
import qlk.entity.ChiTietPhieuThongKe;
import qlk.entity.DienThoai;
import qlk.entity.PhieuNhap;
import qlk.entity.PhieuThongKe;
import qlk.entity.PhieuXuat;
import qlk.entity.TaiKhoan;
import qlk.model.DBConnection;
import qlk.model.ThongKeDB;

public class XuatFileController {
	static QuanLySanPham qlsp = new QuanLySanPham();
	static QuanLyNhapKho qlnk = new QuanLyNhapKho();
	static QuanLyXuatKho qlxk = new QuanLyXuatKho();
	static QuanLyTaiKhoan qltk = new QuanLyTaiKhoan();
	
	public static List<DienThoai> FindAllDT(List<DienThoai> dt){
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			return qlsp.FindAllDT(conn, dt);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<PhieuNhap>FindAllNK(List<PhieuNhap> pn){
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			return qlnk.FindAllNK(conn,pn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<PhieuXuat> FindAllXK(List<PhieuXuat> px){
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			return qlxk.FindAllXK(conn,px);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<TaiKhoan> FindAllTK(List<TaiKhoan> tk){
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			return qltk.FindAllTK(conn,tk);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static void XuatFile(String path) throws ClassNotFoundException, SQLException, IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		XuatSanPham(workbook);
		XuatPhieuNhap(workbook);
		XuatPhieuXuat(workbook);
		XuatTaiKhoan(workbook);
		FileOutputStream file = new FileOutputStream(path);
		workbook.write(file);
		file.close();
		workbook.close();
	}
	
	public static void XuatSanPham(HSSFWorkbook workbook) throws ClassNotFoundException, SQLException 
	{
		List<DienThoai> list = new ArrayList<DienThoai>();
		FindAllDT(list);
		
		int j=1;
		try{
			HSSFSheet sheet = workbook.createSheet("DienThoai");
			HSSFRow rowhead = sheet.createRow((short) 0);
			rowhead.createCell(0).setCellValue("STT");
			rowhead.createCell(1).setCellValue("Mã");
			rowhead.createCell(2).setCellValue("Tên");
			rowhead.createCell(3).setCellValue("Hãng sản xuất");
			rowhead.createCell(4).setCellValue("Màu sắc");
			rowhead.createCell(5).setCellValue("Thông số");
			rowhead.createCell(6).setCellValue("Thông tin");
			rowhead.createCell(7).setCellValue("Số lượng");
			rowhead.createCell(8).setCellValue("Giá");
			rowhead.createCell(9).setCellValue("Thành tiền");
			for(int i = 0 ; i < list.size(); i++) {
				DienThoai dt = list.get(i);
				HSSFRow row = sheet.createRow((short) j);
				row.createCell(0).setCellValue(j);
				row.createCell(1).setCellValue(dt.getMaDT());
				row.createCell(2).setCellValue(dt.getTenDT());
				row.createCell(3).setCellValue(dt.getHangDT());
				row.createCell(4).setCellValue(dt.getMauSac());
				row.createCell(5).setCellValue(dt.getThongSo());
				row.createCell(6).setCellValue(dt.getThongTin());
				row.createCell(7).setCellValue(dt.getSlg());
				row.createCell(8).setCellValue(dt.getGia());
				j++;
				row.createCell(9).setCellFormula(SetMul(j, j, 7, 8));
			}
			HSSFRow row = sheet.createRow((short) j);
			//sheet.addMergedRegion(new CellRangeAddress(j,j,0,8));
			row.createCell(8).setCellValue("Tổng tiền");
			row.createCell(9).setCellFormula(SetSum(2, j, 9, 9));
			j++;
			AutoSize(sheet,10);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void XuatPhieuNhap(HSSFWorkbook workbook) throws ClassNotFoundException, SQLException 
	{
		List<PhieuNhap> list = new ArrayList<PhieuNhap>();
		FindAllNK(list);
		int j=1;
		double thanhtien = 0;
		try{
			HSSFSheet sheet2 = workbook.createSheet("PhieuNhap");
			HSSFRow rowhead = sheet2.createRow((short) 0);
			rowhead.createCell(0).setCellValue("STT");
			rowhead.createCell(1).setCellValue("Số phiếu");
			rowhead.createCell(2).setCellValue("Ngày");
			rowhead.createCell(3).setCellValue("Tên nhân viên");
			rowhead.createCell(4).setCellValue("Thành tiền");
			rowhead.createCell(5).setCellValue("Tình trạng");
			for(int i = 0 ; i < list.size(); i++) {
				PhieuNhap pn = list.get(i);
				HSSFRow row = sheet2.createRow((short) j);
				row.createCell(0).setCellValue(j);
				row.createCell(1).setCellValue(pn.getSoPhieu());
				row.createCell(2).setCellValue(pn.getNgay().toString());
				row.createCell(3).setCellValue(pn.getTenNV());
				thanhtien = thanhtien + pn.getThanhTien();
				row.createCell(4).setCellValue(pn.getThanhTien());
				if(pn.isTinhTrang() == true) {
					row.createCell(5).setCellValue("Đã nhập kho");
				}else {
					row.createCell(5).setCellValue("Chưa nhập kho");
				} j++;
			}
			HSSFRow row = sheet2.createRow((short) j);
			sheet2.addMergedRegion(new CellRangeAddress(j,j,0,3));
			row.createCell(0).setCellValue("Tổng tiền");
			row.createCell(4).setCellFormula(SetSum(2, j, 4, 4));
			AutoSize(sheet2,6);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void XuatPhieuXuat(HSSFWorkbook workbook) throws ClassNotFoundException, SQLException 
	{
		List<PhieuXuat> list = new ArrayList<PhieuXuat>();
		FindAllXK(list);
		int j=1;
		try{
			HSSFSheet sheet3 = workbook.createSheet("PhieuXuat");
			HSSFRow rowhead = sheet3.createRow((short) 0);
			rowhead.createCell(0).setCellValue("STT");
			rowhead.createCell(1).setCellValue("Số phiếu");
			rowhead.createCell(2).setCellValue("Ngày");
			rowhead.createCell(3).setCellValue("Tên nhân viên");
			rowhead.createCell(4).setCellValue("Thành tiền");
			rowhead.createCell(5).setCellValue("Tình trạng");
			for(int i = 0 ; i < list.size(); i++) {
				PhieuXuat px = list.get(i);
				HSSFRow row = sheet3.createRow((short) j);
				row.createCell(0).setCellValue(j);
				row.createCell(1).setCellValue(px.getSoPhieu());
				row.createCell(2).setCellValue(px.getNgay().toString());
				row.createCell(3).setCellValue(px.getTenNV());
				row.createCell(4).setCellValue(px.getThanhTien());
				if(px.isTinhTrang() == true) {
					row.createCell(5).setCellValue("Đã xuất kho");
				}else {
					row.createCell(5).setCellValue("Chưa xuất kho");
				} j++;
			}
			HSSFRow row = sheet3.createRow((short) j);
			sheet3.addMergedRegion(new CellRangeAddress(j,j,0,3));
			row.createCell(0).setCellValue("Tổng tiền");
			row.createCell(4).setCellFormula(SetSum(2, j, 4, 4));
			AutoSize(sheet3,6);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void XuatTaiKhoan(HSSFWorkbook workbook) throws ClassNotFoundException, SQLException 
	{
		List<TaiKhoan> list = new ArrayList<TaiKhoan>();
		FindAllTK(list);
		int j=1;
		try{
			HSSFSheet sheet4 = workbook.createSheet("TaiKhoan");
			HSSFRow rowhead = sheet4.createRow((short) 0);
			rowhead.createCell(0).setCellValue("STT");
			rowhead.createCell(1).setCellValue("Username");
			rowhead.createCell(2).setCellValue("Password");
			rowhead.createCell(3).setCellValue("Tên nhân viên");
			rowhead.createCell(4).setCellValue("Quyền");
			for(int i = 0 ; i < list.size(); i++) {
				TaiKhoan tk = list.get(i);
				HSSFRow row = sheet4.createRow((short) j);
				row.createCell(0).setCellValue(j);
				row.createCell(1).setCellValue(tk.getUserName());
				String pass="";
				for(int y = 0; y < tk.getPassWord().length() ; y++) {
					pass = pass + "*";
				}
				row.createCell(2).setCellValue(pass);
				row.createCell(3).setCellValue(tk.getTenNV());
				if(tk.getQuyen() == 1) {
					row.createCell(4).setCellValue("Admin");
				}else {
					row.createCell(4).setCellValue("Nhân Viên");
				}
				j++;
			}AutoSize(sheet4,5);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void AutoSize(HSSFSheet sheet,int length) {
		for(int k = 0; k < length; k++) {
			sheet.autoSizeColumn(k);
		}
	}
	
	public static String SetSum(int from,int to, int key1, int key2) {
		char  ch[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		String setSum = "SUM("+ch[key1]+from+":"+ch[key2]+to+")";
		return setSum;
	}
	
	public static String SetMul(int from,int to, int key1, int key2) {
		char  ch[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		String setMul = ""+ch[key1]+from+"*"+ch[key2]+to+"";
		return setMul;
	}
	
	public static String GetDDMM(PhieuThongKe tk) {
		Date date1 = tk.getNgaybatdau();

		LocalDate Date1 = date1.toLocalDate();
		int day1 = Date1.getDayOfMonth();
		int month1 = Date1.getMonthValue();
		//System.out.println(localDate.getYear());
		
		Date date2 = tk.getNgayketthuc();
		LocalDate Date2 = date2.toLocalDate();
		int day2 = Date2.getDayOfMonth();
		int month2 = Date2.getMonthValue();

		return ""+ day1 + month1 + "-" + day2 + month2 + "";
	}
	
////////////////////////////////////////////////////////// XUẤT THỐNG KÊ ////////////////////////////////////////////////////////	
	
	public static List<PhieuThongKe> DSTK(){
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			return ThongKeDB.AllPhieuThongKe(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<ChiTietPhieuThongKe> CTTK(){
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			return ThongKeDB.AllChiTietTon(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void XuatThongKeTonKho(String path) throws ClassNotFoundException, SQLException 
	{
		
		List<PhieuThongKe> phieu = new ArrayList<PhieuThongKe>();
		phieu = DSTK();
		List<ChiTietPhieuThongKe> chitiet = new ArrayList<ChiTietPhieuThongKe>();
		chitiet = CTTK();
		
		
		try{
			HSSFWorkbook workbook = new HSSFWorkbook();
			for(int i = 0 ; i < phieu.size(); i++) {
				int a=1;
				PhieuThongKe tk = phieu.get(i);
				String name = GetDDMM(tk);
				HSSFSheet sheet = workbook.createSheet(name);
				HSSFRow rowhead = sheet.createRow((short) 0);
				rowhead.createCell(1).setCellValue("Mã phiếu thống kê");
				rowhead.createCell(2).setCellValue("Ngày bắt đầu");
				rowhead.createCell(3).setCellValue("Ngày kết thúc");
				
				HSSFRow row = sheet.createRow((short) a);
				//row.createCell(0).setCellValue(a);
				row.createCell(1).setCellValue(tk.getMaphieu());
				row.createCell(2).setCellValue(tk.getNgaybatdau().toString());
				row.createCell(3).setCellValue(tk.getNgayketthuc().toString());
				a++;
				
				HSSFRow rowhead2 = sheet.createRow((short) a);
				rowhead2.createCell(0).setCellValue("STT");
				rowhead2.createCell(1).setCellValue("Mã điện thoại");
				rowhead2.createCell(2).setCellValue("Tên điện thoại");
				rowhead2.createCell(3).setCellValue("Tồn đầu kỳ");
				rowhead2.createCell(4).setCellValue("Nhập trong kỳ");
				rowhead2.createCell(5).setCellValue("Xuất trong kỳ");
				rowhead2.createCell(6).setCellValue("Tồn cuối kỳ");
				rowhead2.createCell(7).setCellValue("Giá trị tồn kho");
				int stt = 1; a++; int index1 = a+1;
				for(int j = 0 ; j < chitiet.size(); j++) {
					
					ChiTietPhieuThongKe ct = chitiet.get(j);
					if (ct.getMaphieuthongke() == tk.getMaphieu()) {
						HSSFRow row2 = sheet.createRow((short) a);
						row2.createCell(0).setCellValue(stt);
						row2.createCell(1).setCellValue(ct.getMadt());
						row2.createCell(2).setCellValue(ct.getTendt());
						row2.createCell(3).setCellValue(ct.getTondauky());
						row2.createCell(4).setCellValue(ct.getNhaptrongky());
						row2.createCell(5).setCellValue(ct.getXuattrongky());
						row2.createCell(6).setCellValue(ct.getToncuoiky());
						row2.createCell(7).setCellValue(ct.getGiatritonkho());
						a++; stt++;
					}
				}
				int index2 = a;
				HSSFRow row3 = sheet.createRow((short) a);
				row3.createCell(6).setCellFormula(SetSum(index1, index2, 6, 6));
				row3.createCell(7).setCellFormula(SetSum(index1, index2, 7, 7));
				a++;
				for(int k = 0; k < a; k++) {
					sheet.autoSizeColumn(k);
				}
			}
			
			FileOutputStream file = new FileOutputStream(path);
			workbook.write(file);
			file.close();
			workbook.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
