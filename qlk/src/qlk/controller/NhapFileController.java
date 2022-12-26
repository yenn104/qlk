package qlk.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import qlk.Interface.QuanLyNhapKho;
import qlk.Interface.QuanLySanPham;
import qlk.Interface.QuanLyTaiKhoan;
import qlk.Interface.QuanLyXuatKho;
import qlk.entity.DienThoai;
import qlk.entity.PhieuNhap;
import qlk.entity.PhieuXuat;
import qlk.entity.TaiKhoan;
import qlk.model.DBConnection;

public class NhapFileController{
	static QuanLySanPham qlsp = new QuanLySanPham();
	static QuanLyNhapKho qlnk = new QuanLyNhapKho();
	static QuanLyXuatKho qlxk = new QuanLyXuatKho();
	static QuanLyTaiKhoan qltk = new QuanLyTaiKhoan();
	
	public static void AddDT(DienThoai dt)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qlsp.AddDT(conn,dt);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void AddNK(PhieuNhap nk)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qlnk.AddNK(conn,nk);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void AddXK(PhieuXuat xk) {
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qlxk.AddXK(conn,xk);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void AddTK(TaiKhoan tk)
	{
		Connection conn;
		try {
			conn = DBConnection.getMySQLConnection();
			qltk.AddTK(conn,tk);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void NhapSanPham(String Path) throws ClassNotFoundException, SQLException, IOException  {
		int j=1;
		try {
			FileInputStream file = new FileInputStream(new File(Path));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int y = sheet.getLastRowNum();
			
			for(int i = 0 ; i < y; i++) {
				DienThoai dt = new DienThoai();
				XSSFRow row = sheet.getRow((short) j);
				dt.setTenDT(row.getCell(1).getStringCellValue());
				dt.setHangDT(row.getCell(2).getStringCellValue());
				dt.setMauSac(row.getCell(3).getStringCellValue());
				dt.setThongSo(row.getCell(4).getStringCellValue());
				dt.setThongTin(row.getCell(5).getStringCellValue());
				dt.setSlg(0);
				dt.setGia(row.getCell(6).getNumericCellValue());
				AddDT(dt);
				j++;
			}
	        file.close();
	        workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void NhapPhieuNhap(String Path) throws ClassNotFoundException, SQLException, IOException  {
		int j=1;
		try {
			FileInputStream file = new FileInputStream(new File(Path));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int y = sheet.getLastRowNum();
			
			for(int i = 0 ; i < y; i++) {
				PhieuNhap pn = new PhieuNhap();
				XSSFRow row = sheet.getRow((short) j);
				Date date = row.getCell(1).getDateCellValue();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				pn.setNgay(sqlDate);
				pn.setTenNV(row.getCell(2).getStringCellValue());
				int b = (int)row.getCell(3).getNumericCellValue();
				if(b==1) {
					pn.setTinhTrang(true);
				}else {
					pn.setTinhTrang(false);
				}
				AddNK(pn);
				j++;
			}
	        file.close();
	        workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void NhapPhieuXuat(String Path) throws ClassNotFoundException, SQLException, IOException  {
		int j=1;
		try {
			FileInputStream file = new FileInputStream(new File(Path));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int y = sheet.getLastRowNum();
			
			for(int i = 0 ; i < y; i++) {
				PhieuXuat pn = new PhieuXuat();
				XSSFRow row = sheet.getRow((short) j);
				Date date = row.getCell(1).getDateCellValue();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				pn.setNgay(sqlDate);
				pn.setTenNV(row.getCell(2).getStringCellValue());
				int b = (int)row.getCell(3).getNumericCellValue();
				if(b==1) {
					pn.setTinhTrang(true);
				}else {
					pn.setTinhTrang(false);
				}
				pn.setLydo(row.getCell(4).getStringCellValue());
				AddXK(pn);
				j++;
			}
	        file.close();
	        workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void NhapTaiKhoan(String Path) throws ClassNotFoundException, SQLException, IOException  {
		int j=1;
		try {
			FileInputStream file = new FileInputStream(new File(Path));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int y = sheet.getLastRowNum();
			
			for(int i = 0 ; i < y; i++) {
				TaiKhoan tk = new TaiKhoan();
				XSSFRow row = sheet.getRow((short) j);
				tk.setUserName(row.getCell(1).getStringCellValue());
				tk.setPassWord("11111");
				tk.setTenNV(row.getCell(2).getStringCellValue());
				tk.setEmail(row.getCell(3).getStringCellValue());
				tk.setQuyen((int)row.getCell(4).getNumericCellValue());
				AddTK(tk);
				j++;
			}
	        file.close();
	        workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
