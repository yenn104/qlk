package qlk.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import qlk.controller.ChiTietNhapKhoController;
import qlk.controller.ChiTietXuatKhoController;
import qlk.controller.NhapFileController;
import qlk.controller.QuanLyNhapKhoController;
import qlk.controller.QuanLySanPhamController;
import qlk.controller.QuanLyTaiKhoanController;
import qlk.controller.QuanLyXuatKhoController;
import qlk.controller.XuatFileController;
import qlk.entity.ChiTietPhieuThongKe;
import qlk.entity.DienThoai;
import qlk.entity.FileTypeFilter;
import qlk.entity.PhieuNhap;
import qlk.entity.PhieuThongKe;
import qlk.entity.PhieuXuat;
import qlk.entity.TaiKhoan;
import qlk.model.DBConnection;
import qlk.model.DienThoaiDB;
import qlk.model.NhapKhoDB;
import qlk.model.ThongKeDB;
import qlk.model.XuatKhoDB;

import javax.swing.JPasswordField;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField txttimdienthoai;
	private JTextField txtmaphieuxuattimkiem;
	private JTextField txttimmaphieunhap;
	private JTextField txttaikhoan;
	private JTextField txttimtk;
	private JTextField txttennv;
	private JPasswordField txtpassword;
	private JTextField txttimkiemphieuthongketonkho;
	private JTextField txtemail;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home(null);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}		
			}
		});
	}

	public void tablethongke(DefaultTableModel modeltablephieuthongke) throws ClassNotFoundException, SQLException {
		modeltablephieuthongke.setRowCount(0);
		List<PhieuThongKe> listtable = new ArrayList<PhieuThongKe>();
		Connection connection = DBConnection.getMySQLConnection();
		listtable = ThongKeDB.AllPhieuThongKe(connection);
		for (int i = 0; i < listtable.size(); i++) {
			PhieuThongKe tktemp = listtable.get(i);
			modeltablephieuthongke
					.addRow(new Object[] { tktemp.getMaphieu(), tktemp.getNgaybatdau(), tktemp.getNgayketthuc() });
		}
	}

	public void addthongke(java.sql.Date ngaybd, java.sql.Date ngaykt) throws ClassNotFoundException, SQLException {

		List<ChiTietPhieuThongKe> ctptktk = new ArrayList<ChiTietPhieuThongKe>();
		Connection connection = DBConnection.getMySQLConnection();
		ctptktk = ThongKeDB.thongke(connection, ngaybd, ngaykt);
		PhieuThongKe tempptk = new PhieuThongKe(0, ngaybd, ngaykt);
		PhieuThongKe getptk = ThongKeDB.insertPhieuThongKeTonKho(connection, tempptk);
		for (int i = 0; i < ctptktk.size(); i++) {
			ChiTietPhieuThongKe ctptktktemp = ctptktk.get(i);
			ctptktktemp.setMaphieuthongke(getptk.getMaphieu());

		}
		ThongKeDB.insertchitietphieuthongke(connection, ctptktk);
	}

	public PhieuThongKe getselecttablethongke(JTable tabledanhsachthongke) throws ClassNotFoundException, SQLException {
		int indextablequanlysp = tabledanhsachthongke.getSelectedRow();
		List<PhieuThongKe> listtable = new ArrayList<PhieuThongKe>();
		Connection connection = DBConnection.getMySQLConnection();
		listtable = ThongKeDB.AllPhieuThongKe(connection);
		PhieuThongKe pttk = listtable.get(indextablequanlysp);
		return pttk;
	}

	public void tablechitietthongke(DefaultTableModel modeltablechitietthongke, int maphieu)
			throws ClassNotFoundException, SQLException {
		modeltablechitietthongke.setRowCount(0);
		List<ChiTietPhieuThongKe> ctptktk = new ArrayList<ChiTietPhieuThongKe>();
		Connection connection = DBConnection.getMySQLConnection();
		ctptktk = ThongKeDB.findchitietphieuthongke(connection, maphieu);

		for (int i = 0; i < ctptktk.size(); i++) {
			ChiTietPhieuThongKe ctptktktemp = ctptktk.get(i);
			modeltablechitietthongke.addRow(new Object[] { ctptktktemp.getMadt(), ctptktktemp.getTendt(),
					ctptktktemp.getTondauky(), ctptktktemp.getNhaptrongky(), ctptktktemp.getXuattrongky(),
					ctptktktemp.getToncuoiky(), String.format("%.0f",ctptktktemp.getGiatritonkho()) });
		}
	}

	public Home(TaiKhoan tk) throws ClassNotFoundException, SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/images/warehouse.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1245, 617);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Hệ Thống");
		mnNewMenu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Đăng Xuất");
		mntmNewMenuItem_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DangNhap dn = new DangNhap();
				dn.setLocationRelativeTo(null);
				dn.show();
				dispose();
			}
		});

		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("Đổi Mật Khẩu");
		mntmNewMenuItem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DoiMatKhau dmk = new DoiMatKhau(tk);
				dmk.setLocationRelativeTo(null);
				dmk.show();
				dispose();
			}
		});

		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnNewMenu_1 = new JMenu("Thống Kê");
		mnNewMenu_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Xuất Dữ Liệu");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File("C:Desktop\\"));
				fs.setDialogTitle("Save a File");
				fs.setFileFilter(new FileTypeFilter(".xls", "Chọn file"));
				int result = fs.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File fi = fs.getSelectedFile();
					String path = fi.getPath();
					String pa = path + ".xls";
					try {
						XuatFileController.XuatFile(pa);
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						e1.printStackTrace();
					}JOptionPane.showMessageDialog(mntmNewMenuItem_2, "Xuất file thành công!");
				}
			}
		});
		mntmNewMenuItem_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Xuất Thống Kê");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File("D:\\"));
				fs.setDialogTitle("Save a File");
				fs.setFileFilter(new FileTypeFilter(".xls", "Chọn file"));
				int result = fs.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File fi = fs.getSelectedFile();
					String path = fi.getPath();
					String pa = path + ".xls";
					try {
						XuatFileController.XuatThongKeTonKho(pa);
						JOptionPane.showMessageDialog(mntmNewMenuItem_2, "Xuất dữ liệu thống kê thành công!");
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		mntmNewMenuItem_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		mnNewMenu_1.add(mntmNewMenuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setBounds(100, 100, 704, 524);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		tabbedPane.setBounds(0, 0, 1231, 553);
		tabbedPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(tabbedPane);

		/////////////////////////// Quản Lý Sản Phẩm//////////////////////////////////

		JPanel panelquanlysanpham = new JPanel();
		tabbedPane.addTab("Quản Lý Sản Phẩm", null, panelquanlysanpham, null);
		panelquanlysanpham.setLayout(null);

		String[] header = { "Mã Điện Thoại", "Tên Điện Thoại", "Hãng Điện Thoại", "Màu", "Thông Số", "Thông Tin",
				"Số Lượng", "Giá" };
		String[][] data = {};

		JTable tablequanlysanpham = new JTable();
		tablequanlysanpham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tablequanlysanpham.setModel(new DefaultTableModel(data, header));
		DefaultTableModel modelquanlysanpham = new DefaultTableModel(data, header) {
			 @Override
			   public boolean isCellEditable(int row, int column) {
			       //Only the third column
			       if (column == 6 || column == 0)
			       {
			    	   return false;
			       }
			       else
			       {
			    	   return true;
			       }
			   }
		};
		tablequanlysanpham.setModel(modelquanlysanpham);

		QuanLySanPhamController.tablequanlysanpham(modelquanlysanpham);
		JScrollPane spTablequanlysanpham = new JScrollPane(tablequanlysanpham);
		spTablequanlysanpham.setBounds(20, 99, 1035, 352);
		panelquanlysanpham.add(spTablequanlysanpham);

		JLabel lblNewLabel = new JLabel("Quản Lý Sản Phẩm");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(183, 35, 177, 22);
		panelquanlysanpham.add(lblNewLabel);

		JButton btnthemsanphammoi = new JButton("Thêm Mới");
		btnthemsanphammoi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnthemsanphammoi.setFocusPainted(false);
		btnthemsanphammoi.setBackground(new Color(214, 214, 214));
		btnthemsanphammoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThemSanPhamForm tsp = new ThemSanPhamForm(tk);
				tsp.setLocationRelativeTo(null);
				tsp.show();
				dispose();
			}
		});
		btnthemsanphammoi.setBounds(511, 487, 104, 36);
		panelquanlysanpham.add(btnthemsanphammoi);

		JButton btnxoasanpham = new JButton("Xóa Sản Phẩm");
		btnxoasanpham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnxoasanpham.setFocusPainted(false);
		btnxoasanpham.setBackground(new Color(214, 214, 214));
		btnxoasanpham.setBounds(761, 487, 130, 36);
		panelquanlysanpham.add(btnxoasanpham);btnxoasanpham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choose = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa sản phẩm đã chọn?", "Question", JOptionPane.YES_NO_OPTION);
				if (choose == JOptionPane.YES_OPTION) {
					try {
					DienThoai newsp = QuanLySanPhamController.getselectsanpham(tablequanlysanpham);
					QuanLySanPhamController.DeleteDT(newsp.getMaDT());
					QuanLySanPhamController.tablequanlysanpham(modelquanlysanpham);
					}catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				} 
			}
		});
		

		JButton btnsuasanpham = new JButton("Cập Nhật");
		btnsuasanpham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnsuasanpham.setFocusPainted(false);
		btnsuasanpham.setBackground(new Color(214, 214, 214));
		btnsuasanpham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for(int i = 0; i < modelquanlysanpham.getRowCount(); i++)
				{
					int j = 0;
						
						try {
							Connection cnn = DBConnection.getMySQLConnection();
							DienThoai dt = new DienThoai();
							dt.setMaDT(Integer.parseInt(String.valueOf(modelquanlysanpham.getValueAt(i, j))));
							j++;
							dt.setTenDT(String.valueOf(modelquanlysanpham.getValueAt(i, j)));
							j++;
							dt.setHangDT(String.valueOf(modelquanlysanpham.getValueAt(i, j)));
							j++;
							dt.setMauSac(String.valueOf(modelquanlysanpham.getValueAt(i, j)));
							j++;
							dt.setThongSo(String.valueOf(modelquanlysanpham.getValueAt(i, j)));
							j++;
							dt.setThongTin(String.valueOf(modelquanlysanpham.getValueAt(i, j)));
							j++;
							
							dt.setSlg(Integer.parseInt(String.valueOf(modelquanlysanpham.getValueAt(i, j))));
							j++;
							
							dt.setGia(Double.parseDouble(String.valueOf(modelquanlysanpham.getValueAt(i, j))));
							DienThoaiDB.updateProduct(cnn, dt);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}

			}
		});
		btnsuasanpham.setBounds(635, 487, 104, 36);
		panelquanlysanpham.add(btnsuasanpham);

		JButton btntimkiemsanpham = new JButton("Tìm Kiếm");
		btntimkiemsanpham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btntimkiemsanpham.setFocusPainted(false);
		btntimkiemsanpham.setBackground(new Color(214, 214, 214));
		btntimkiemsanpham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txttimdienthoai.getText() != "Nhập Tên Điện Thoại Cần Tìm") {
					try {
						QuanLySanPhamController.tabletimkiemquanlysanpham(modelquanlysanpham,
								txttimdienthoai.getText());
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btntimkiemsanpham.setBounds(837, 29, 104, 36);
		panelquanlysanpham.add(btntimkiemsanpham);

		txttimdienthoai = new JTextField();
		txttimdienthoai.setHorizontalAlignment(SwingConstants.CENTER);
		txttimdienthoai.setForeground(new Color(128, 128, 128));
		txttimdienthoai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txttimdienthoai.setText("");
			}
		});
		txttimdienthoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txttimdienthoai.setText("Nhập Tên Điện Thoại Cần Tìm");
		txttimdienthoai.setBounds(614, 30, 210, 36);
		panelquanlysanpham.add(txttimdienthoai);
		txttimdienthoai.setColumns(10);

		JButton btndanhsachsanpham = new JButton("Làm Mới");
		btndanhsachsanpham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					QuanLySanPhamController.tablequanlysanpham(modelquanlysanpham);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btndanhsachsanpham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btndanhsachsanpham.setFocusPainted(false);
		btndanhsachsanpham.setBackground(new Color(214, 214, 214));
		btndanhsachsanpham.setBounds(957, 29, 98, 36);
		panelquanlysanpham.add(btndanhsachsanpham);

		JLabel lblNewLabel_1 = new JLabel("Sắp Xếp");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(67, 493, 62, 24);
		panelquanlysanpham.add(lblNewLabel_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Tăng Dần Theo Tên", "Giảm Dần Theo Tên",
				"Tăng Dần Theo Số Lượng", "Giảm Dần Theo Số Lượng" }));
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		comboBox.setBounds(138, 487, 185, 36);
		panelquanlysanpham.add(comboBox);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString().equals("Tăng Dần Theo Tên")) {
					try {
						QuanLySanPhamController.tablesapxepsanpham(modelquanlysanpham, 1);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else if (comboBox.getSelectedItem().toString().equals("Giảm Dần Theo Tên")) {
					try {
						QuanLySanPhamController.tablesapxepsanpham(modelquanlysanpham, 2);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else if (comboBox.getSelectedItem().toString().equals("Tăng Dần Theo Số Lượng")) {
					try {
						QuanLySanPhamController.tablesapxepsanpham(modelquanlysanpham, 3);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else if (comboBox.getSelectedItem().toString().equals("Giảm Dần Theo Số Lượng")) {
					try {
						QuanLySanPhamController.tablesapxepsanpham(modelquanlysanpham, 4);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		

		JButton btnNewButton_1 = new JButton("Nhập Dữ Liệu");
		btnNewButton_1.setBackground(new Color(214, 214, 214));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File("D:\\"));
				fs.setDialogTitle("Open a File");
				fs.setFileFilter(new FileTypeFilter(".xlsx", "Chọn file"));
				int result = fs.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File fi = fs.getSelectedFile();
					String path = fi.getPath();
					try {
						NhapFileController.NhapSanPham(path);
						
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						e1.printStackTrace();
					} JOptionPane.showMessageDialog(mntmNewMenuItem_2, "Nhập dữ liệu sản phẩm thành công!");
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_1.setBounds(912, 487, 122, 36);
		panelquanlysanpham.add(btnNewButton_1);

		////////////////////////// Xuất Kho////////////////////////

		JPanel panelxuatkho = new JPanel();
		tabbedPane.addTab("Xuất Kho", null, panelxuatkho, null);
		panelxuatkho.setLayout(null);
		String[] headerxuatkho = { "Mã Phiếu Xuất", "Ngày Xuất", "Tên Nhân Viên", "Thành Tiền", "Tình Trạng", "Lý Do" };
		String[][] dataxuatkho = {};
		JTable tablexuatkho = new JTable();
		tablexuatkho.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tablexuatkho.setModel(new DefaultTableModel(dataxuatkho, headerxuatkho));
		DefaultTableModel modelxuatkho = new DefaultTableModel(dataxuatkho, headerxuatkho) {
			 @Override
			   public boolean isCellEditable(int row, int column) {
			       //Only the third column
			       if (column == 3 || column == 0)
			       {
			    	   return false;
			       }
			       else
			       {
			    	   return true;
			       }
			   }
		};
		tablexuatkho.setModel(modelxuatkho);

		QuanLyXuatKhoController.tablephieuxuatkho(modelxuatkho);
		JScrollPane spTablexuatkho = new JScrollPane(tablexuatkho);
		spTablexuatkho.setBounds(25, 109, 1039, 316);
		panelxuatkho.add(spTablexuatkho);

		JButton btntaophieuxuat = new JButton("Tạo Phiếu Xuất");
		btntaophieuxuat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btntaophieuxuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TaoPhieuXuatForm px = new TaoPhieuXuatForm(tk);
				px.setLocationRelativeTo(null);
				px.show();
				dispose();
			}
		});
		btntaophieuxuat.setBackground(new Color(214, 214, 214));
		btntaophieuxuat.setBounds(50, 463, 172, 31);
		panelxuatkho.add(btntaophieuxuat);

		JButton btnxoaphieuxuat = new JButton("Xóa Phiếu Xuất");
		btnxoaphieuxuat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnxoaphieuxuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choose = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa phiếu xuất đã chọn?", "Question", JOptionPane.YES_NO_OPTION);
				if (choose == JOptionPane.YES_OPTION) {
					try {
						PhieuXuat xoapxkho = QuanLyXuatKhoController.getselectphieuxuatkho(tablexuatkho);
						QuanLyXuatKhoController.DeleteXK(xoapxkho.getSoPhieu());
						QuanLyXuatKhoController.tablephieuxuatkho(modelxuatkho);
					}catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}		
			}
		});
		btnxoaphieuxuat.setBackground(new Color(214, 214, 214));
		btnxoaphieuxuat.setBounds(452, 463, 172, 31);
		panelxuatkho.add(btnxoaphieuxuat);

		JButton btnsuaphieuxuat = new JButton("Cập Nhật");
		btnsuaphieuxuat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnsuaphieuxuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				for(int i = 0; i < modelxuatkho.getRowCount(); i++)
				{
					int j = 0;
						
						try {
							Connection cnn = DBConnection.getMySQLConnection();
							PhieuXuat pxtemp = new PhieuXuat();
							pxtemp.setSoPhieu(Integer.parseInt(String.valueOf(modelxuatkho.getValueAt(i, j))));
							j++;
							java.sql.Date date= Date.valueOf(String.valueOf(modelxuatkho.getValueAt(i, j)));
							pxtemp.setNgay(date);
							j++;
							pxtemp.setTenNV(String.valueOf(modelxuatkho.getValueAt(i, j)));
							j++;
							pxtemp.setThanhTien(Double.parseDouble(String.valueOf(modelxuatkho.getValueAt(i, j))));
							j++;
							String tinhtrang = String.valueOf(modelxuatkho.getValueAt(i, j));
							if(tinhtrang.equals("Đã Xuất Kho"))
							{
								pxtemp.setTinhTrang(true);
							}
							else
							{
								pxtemp.setTinhTrang(false);
							}
							j++;
							pxtemp.setLydo(String.valueOf(modelxuatkho.getValueAt(i, j)));
							
							
							XuatKhoDB.UpdateXK(cnn, pxtemp);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}

			}
		});
		btnsuaphieuxuat.setBackground(new Color(214, 214, 214));
		btnsuaphieuxuat.setBounds(249, 463, 172, 31);
		panelxuatkho.add(btnsuaphieuxuat);

		JButton btntimkiemphieuxuat = new JButton("Tìm Kiếm");
		btntimkiemphieuxuat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btntimkiemphieuxuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtmaphieuxuattimkiem.getText() != "Nhập Mã Phiếu Xuất") {
					try {

						QuanLyXuatKhoController.tabletimphieuxuatkho(modelxuatkho,
								Integer.parseInt(txtmaphieuxuattimkiem.getText()));
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btntimkiemphieuxuat.setBackground(new Color(214, 214, 214));
		btntimkiemphieuxuat.setBounds(843, 39, 101, 31);
		panelxuatkho.add(btntimkiemphieuxuat);

		JButton btnxemthongtinchitietphieuxuat = new JButton("Xem Chi Tiết Phiếu");
		btnxemthongtinchitietphieuxuat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnxemthongtinchitietphieuxuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhieuXuat px;
				try {
					px = QuanLyXuatKhoController.getselectphieuxuatkho(tablexuatkho);
					ChiTietXuatKhoForm ctxk = new ChiTietXuatKhoForm(tk, px);
					ctxk.setLocationRelativeTo(null);
					ctxk.show();
					dispose();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnxemthongtinchitietphieuxuat.setBackground(new Color(214, 214, 214));
		btnxemthongtinchitietphieuxuat.setBounds(648, 463, 172, 31);
		panelxuatkho.add(btnxemthongtinchitietphieuxuat);

		txtmaphieuxuattimkiem = new JTextField();
		txtmaphieuxuattimkiem.setForeground(new Color(128, 128, 128));
		txtmaphieuxuattimkiem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtmaphieuxuattimkiem.setText("");
			}
		});
		txtmaphieuxuattimkiem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtmaphieuxuattimkiem.setHorizontalAlignment(SwingConstants.CENTER);
		txtmaphieuxuattimkiem.setText("Nhập Mã Phiếu Xuất");
		txtmaphieuxuattimkiem.setBounds(633, 39, 200, 31);
		panelxuatkho.add(txtmaphieuxuattimkiem);
		txtmaphieuxuattimkiem.setColumns(10);

		JButton btnDanhSchPhiu = new JButton("Làm Mới");
		btnDanhSchPhiu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					QuanLyXuatKhoController.tablephieuxuatkho(modelxuatkho);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDanhSchPhiu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnDanhSchPhiu.setBackground(new Color(214, 214, 214));
		btnDanhSchPhiu.setBounds(954, 39, 101, 31);
		panelxuatkho.add(btnDanhSchPhiu);

		JLabel lblNewLabel_2 = new JLabel("Quản Lý Xuất Kho");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(180, 35, 222, 36);
		panelxuatkho.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Nhập Dữ Liệu");
		btnNewButton.setBackground(new Color(214, 214, 214));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File("D:\\"));
				fs.setDialogTitle("Open a File");
				fs.setFileFilter(new FileTypeFilter(".xlsx", "Chọn file"));
				int result = fs.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File fi = fs.getSelectedFile();
					String path = fi.getPath();
					try {
						NhapFileController.NhapPhieuXuat(path);
						
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						e1.printStackTrace();
					} JOptionPane.showMessageDialog(mntmNewMenuItem_2, "Nhập dữ liệu phiếu xuất thành công!");
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton.setBounds(838, 463, 172, 31);
		panelxuatkho.add(btnNewButton);

		///////////////////// Nhập Kho//////////////////////////////

		JPanel panelnhapkho = new JPanel();
		tabbedPane.addTab("Nhập Kho", null, panelnhapkho, null);
		panelnhapkho.setLayout(null);

		String[] headernhapkho = { "Mã Phiếu Nhập", "Ngày Nhập", "Tên Nhân Viên", "Thành Tiền", "Tình Trạng" };
		String[][] datanhapkho = {};

		JTable tablenhapkho = new JTable();
		tablenhapkho.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		tablenhapkho.setModel(new DefaultTableModel(datanhapkho, headernhapkho));
		DefaultTableModel modelnhapkho = new DefaultTableModel(datanhapkho, headernhapkho) {
			 @Override
			   public boolean isCellEditable(int row, int column) {
			       //Only the third column
			       if (column == 3 || column == 0)
			       {
			    	   return false;
			       }
			       else
			       {
			    	   return true;
			       }
			   }
		};
		tablenhapkho.setModel(modelnhapkho);
		QuanLyNhapKhoController.tablephieunhapkho(modelnhapkho);

		JScrollPane spTablenhapkho = new JScrollPane(tablenhapkho);
		spTablenhapkho.setBounds(10, 107, 1065, 322);
		panelnhapkho.add(spTablenhapkho);

		JButton btntaophieunhap = new JButton("Tạo Phiếu Nhập");
		btntaophieunhap.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btntaophieunhap.setFocusPainted(false);
		btntaophieunhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaoPhieuNhapForm pn = new TaoPhieuNhapForm(tk);
				pn.setLocationRelativeTo(null);
				pn.show();
				dispose();
			}
		});
		btntaophieunhap.setBackground(new Color(214, 214, 214));
		btntaophieunhap.setBounds(121, 479, 150, 31);
		panelnhapkho.add(btntaophieunhap);

		JButton btnxoaphieunhap = new JButton("Xóa Phiếu Nhập");
		btnxoaphieunhap.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnxoaphieunhap.setFocusPainted(false);
		btnxoaphieunhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choose = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa phiếu nhập đã chọn?", "Question", JOptionPane.YES_NO_OPTION);
				if (choose == JOptionPane.YES_OPTION) {
					try {
						PhieuNhap pn = QuanLyNhapKhoController.getselectphieunhapkho(tablenhapkho);
						QuanLyNhapKhoController.DeleteNK(pn.getSoPhieu());
						QuanLyNhapKhoController.tablephieunhapkho(modelnhapkho);
					}catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnxoaphieunhap.setBackground(new Color(214, 214, 214));
		btnxoaphieunhap.setBounds(461, 479, 150, 31);
		panelnhapkho.add(btnxoaphieunhap);

		JButton btnsuaphieunhap = new JButton("Cập Nhật");
		btnsuaphieunhap.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnsuaphieunhap.setFocusPainted(false);
		btnsuaphieunhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for(int i = 0; i < modelnhapkho.getRowCount(); i++)
				{
					int j = 0;
						
						try {
							Connection cnn = DBConnection.getMySQLConnection();
							PhieuNhap pntemp = new PhieuNhap();
							pntemp.setSoPhieu(Integer.parseInt(String.valueOf(modelnhapkho.getValueAt(i, j))));
							j++;
							java.sql.Date date= Date.valueOf(String.valueOf(modelnhapkho.getValueAt(i, j)));
							pntemp.setNgay(date);
							j++;
							pntemp.setTenNV(String.valueOf(modelnhapkho.getValueAt(i, j)));
							j++;
							pntemp.setThanhTien(Double.parseDouble(String.valueOf(modelnhapkho.getValueAt(i, j))));
							j++;
							String tinhtrang = String.valueOf(modelnhapkho.getValueAt(i, j));
							if(tinhtrang.equals("Đã Nhập Kho"))
							{
								pntemp.setTinhTrang(true);
							}
							else
							{
								pntemp.setTinhTrang(false);
							}
							
							
							NhapKhoDB.UpdateNK(cnn, pntemp);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			}
		});
		btnsuaphieunhap.setBackground(new Color(214, 214, 214));
		btnsuaphieunhap.setBounds(304, 479, 123, 31);
		panelnhapkho.add(btnsuaphieunhap);

		JButton btntimkiemphieunhap = new JButton("Tìm Kiếm");
		btntimkiemphieunhap.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btntimkiemphieunhap.setFocusPainted(false);
		btntimkiemphieunhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txttimmaphieunhap.getText() != "Nhập mã phiếu nhập") {
					try {
						QuanLyNhapKhoController.tabletimphieunhapkho(modelnhapkho,
								Integer.parseInt(txttimmaphieunhap.getText()));
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btntimkiemphieunhap.setBackground(new Color(214, 214, 214));
		btntimkiemphieunhap.setBounds(856, 42, 96, 31);
		panelnhapkho.add(btntimkiemphieunhap);

		JButton btnxemthongtinchitietphieunhap = new JButton("Xem Chi Tiết Phiếu");
		btnxemthongtinchitietphieunhap.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnxemthongtinchitietphieunhap.setFocusPainted(false);
		btnxemthongtinchitietphieunhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhieuNhap pn;
				try {
					pn = QuanLyNhapKhoController.getselectphieunhapkho(tablenhapkho);
					ChiTietNhapKhoForm ctnk = new ChiTietNhapKhoForm(tk, pn);
					ctnk.setLocationRelativeTo(null);
					ctnk.show();
					//dispose();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnxemthongtinchitietphieunhap.setBackground(new Color(214, 214, 214));
		btnxemthongtinchitietphieunhap.setBounds(651, 479, 170, 31);
		panelnhapkho.add(btnxemthongtinchitietphieunhap);

		JButton btndsphieu = new JButton("Làm Mới");
		btndsphieu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					QuanLyNhapKhoController.tablephieunhapkho(modelnhapkho);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btndsphieu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btndsphieu.setFocusPainted(false);
		btndsphieu.setBackground(new Color(214, 214, 214));
		btndsphieu.setBounds(957, 42, 96, 31);
		panelnhapkho.add(btndsphieu);

		txttimmaphieunhap = new JTextField();
		txttimmaphieunhap.setForeground(new Color(128, 128, 128));
		txttimmaphieunhap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txttimmaphieunhap.setText("");
			}
		});
		txttimmaphieunhap.setText("Nhập Mã Phiếu Nhập");
		txttimmaphieunhap.setHorizontalAlignment(SwingConstants.CENTER);
		txttimmaphieunhap.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txttimmaphieunhap.setColumns(10);
		txttimmaphieunhap.setBounds(638, 42, 208, 31);
		panelnhapkho.add(txttimmaphieunhap);

		JLabel lblNewLabel_6 = new JLabel("Quản Lý Nhập Kho");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(109, 41, 187, 31);
		panelnhapkho.add(lblNewLabel_6);

		JButton btnNhpDLiu = new JButton("Nhập Dữ Liệu");
		btnNhpDLiu.setBackground(new Color(214, 214, 214));
		btnNhpDLiu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File("C:\\NhapFile"));
				fs.setDialogTitle("Open a File");
				fs.setFileFilter(new FileTypeFilter(".xlsx", "Chọn file"));
				int result = fs.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File fi = fs.getSelectedFile();
					String path = fi.getPath();
					try {
						NhapFileController.NhapPhieuNhap(path);
						JOptionPane.showMessageDialog(mntmNewMenuItem_2, "Nhập dữ liệu phiếu nhập thành công!");
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						e1.printStackTrace();
					} 
				}
			}
		});
		btnNhpDLiu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNhpDLiu.setBounds(857, 479, 138, 31);
		panelnhapkho.add(btnNhpDLiu);

		
//////////////// THONG KE TON KHO///////////////////////////
		
		JPanel panelthongke = new JPanel();
		tabbedPane.addTab("Thống Kê", null, panelthongke, null);
		panelthongke.setLayout(null);
		UtilDateModel modeltungay = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		UtilDateModel modeldenngay = new UtilDateModel();
		JDatePanelImpl datePaneldenngay = new JDatePanelImpl(modeldenngay, p);
		JDatePanelImpl datePanel = new JDatePanelImpl(modeltungay, p);
		JDatePickerImpl datePickertungay = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePickertungay.getJFormattedTextField().setFont(new Font("Times New Roman", Font.PLAIN, 12));
		datePickertungay.getJFormattedTextField().setBackground(new Color(214, 214, 214));
		datePickertungay.setBackground(new Color(214, 214, 214));
		SpringLayout springLayout = (SpringLayout) datePickertungay.getLayout();
		springLayout.putConstraint(SpringLayout.WEST, datePickertungay.getJFormattedTextField(), 0, SpringLayout.WEST,
				datePickertungay);
		datePickertungay.setBounds(205, 26, 151, 27);
		panelthongke.add(datePickertungay);
		JDatePickerImpl datePickerdenngay = new JDatePickerImpl(datePaneldenngay, new DateComponentFormatter());
		datePickerdenngay.getJFormattedTextField().setFont(new Font("Times New Roman", Font.PLAIN, 12));
		datePickerdenngay.getJFormattedTextField().setBackground(new Color(214, 214, 214));
		datePickerdenngay.setBackground(new Color(214, 214, 214));
		SpringLayout springLayoutdenngay = (SpringLayout) datePickerdenngay.getLayout();
		springLayout.putConstraint(SpringLayout.WEST, datePickerdenngay.getJFormattedTextField(), 0, SpringLayout.WEST,
				datePickerdenngay);
		datePickerdenngay.setBounds(205, 72, 151, 27);
		panelthongke.add(datePickerdenngay);
		String[] headertablechitietthongke = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Tồn Đầu Kỳ", "Nhập Trong Kỳ",
				"Xuất Trong Kỳ", "Tồn Cuối Kỳ", "Giá Trị Tồn Kho" };
		String[] headertablethongke = { "Mã Phiếu", "Ngày Bắt Đầu", "Ngày Kết Thúc", };
		String[][] datatablechitietthongke = {};
		String[][] datatablethongke = {};

		JTable tablechitietthongke = new JTable();
		tablechitietthongke.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tablechitietthongke.setModel(new DefaultTableModel(datatablechitietthongke, headertablechitietthongke));
		DefaultTableModel modeltablechitietthongke = (DefaultTableModel) tablechitietthongke.getModel();

		JScrollPane spTablechitietthongketonkho = new JScrollPane(tablechitietthongke);

		spTablechitietthongketonkho.setBounds(23, 326, 1032, 210);
		panelthongke.add(spTablechitietthongketonkho);

		JTable tablethongke = new JTable();

		tablethongke.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					PhieuThongKe ptk = getselecttablethongke(tablethongke);
					tablechitietthongke(modeltablechitietthongke, ptk.getMaphieu());
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		tablethongke.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tablethongke.setModel(new DefaultTableModel(datatablethongke, headertablethongke));
		DefaultTableModel modeltablethongke = (DefaultTableModel) tablethongke.getModel();
		tablethongke(modeltablethongke);
		JScrollPane spTablethongke = new JScrollPane(tablethongke);
		spTablethongke.setBounds(115, 143, 845, 162);
		panelthongke.add(spTablethongke);

		JLabel lblNewLabel_21 = new JLabel("Từ Ngày: ");
		lblNewLabel_21.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_21.setBounds(115, 26, 80, 24);
		panelthongke.add(lblNewLabel_21);

		JLabel lblNewLabel_2_1 = new JLabel("Đến Ngày: ");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(115, 75, 91, 24);
		panelthongke.add(lblNewLabel_2_1);

		JButton btntaophieuthongketonkho = new JButton("Tạo Phiếu");
		btntaophieuthongketonkho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date datetungayutil = modeltungay.getValue();
				Date datetungay = new Date(datetungayutil.getTime());
				java.util.Date datedenngayutil = modeldenngay.getValue();
				Date datedenngay = new Date(datedenngayutil.getTime());
				try {
					addthongke(datetungay, datedenngay);
					tablethongke(modeltablethongke);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btntaophieuthongketonkho.setFocusPainted(false);
		btntaophieuthongketonkho.setBackground(new Color(214, 214, 214));
		btntaophieuthongketonkho.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btntaophieuthongketonkho.setBounds(390, 26, 98, 27);
		panelthongke.add(btntaophieuthongketonkho);

		JButton btntimphieuthongke = new JButton("Tìm Kiếm");
		btntimphieuthongke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection cnn = DBConnection.getMySQLConnection();
					PhieuThongKe ptk = ThongKeDB.findphieuthongke(cnn,
							Integer.parseInt(txttimkiemphieuthongketonkho.getText()));
					modeltablethongke.setRowCount(0);
					modeltablethongke
							.addRow(new Object[] { ptk.getMaphieu(), ptk.getNgaybatdau(), ptk.getNgayketthuc() });

					tablechitietthongke(modeltablechitietthongke,
							Integer.parseInt(txttimkiemphieuthongketonkho.getText()));
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btntimphieuthongke.setFocusPainted(false);
		btntimphieuthongke.setBackground(new Color(214, 214, 214));
		btntimphieuthongke.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btntimphieuthongke.setBounds(957, 26, 98, 32);
		panelthongke.add(btntimphieuthongke);

		txttimkiemphieuthongketonkho = new JTextField();
		txttimkiemphieuthongketonkho.setForeground(new Color(128, 128, 128));
		txttimkiemphieuthongketonkho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txttimkiemphieuthongketonkho.setText("");
			}
		});
		txttimkiemphieuthongketonkho.setHorizontalAlignment(SwingConstants.CENTER);
		txttimkiemphieuthongketonkho.setText("Nhập Mã Phiếu");
		txttimkiemphieuthongketonkho.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txttimkiemphieuthongketonkho.setBounds(684, 26, 263, 32);
		panelthongke.add(txttimkiemphieuthongketonkho);
		txttimkiemphieuthongketonkho.setColumns(10);

		JButton btndanhsachphieuthongke = new JButton("Làm Mới");
		btndanhsachphieuthongke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tablethongke(modeltablethongke);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btndanhsachphieuthongke.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btndanhsachphieuthongke.setFocusPainted(false);
		btndanhsachphieuthongke.setBackground(new Color(214, 214, 214));
		btndanhsachphieuthongke.setBounds(957, 84, 98, 32);
		panelthongke.add(btndanhsachphieuthongke);
		
		JButton btnNewButton_3 = new JButton("Xuất Dữ Liệu");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File("C:\\"));
				fs.setDialogTitle("Save a File");
				fs.setFileFilter(new FileTypeFilter(".xls", "Chọn file"));
				int result = fs.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File fi = fs.getSelectedFile();
					String path = fi.getPath();
					String pa = path;
					if (path.indexOf(".xls")==-1) {
						pa = path + ".xls";
					}
					try {
						XuatFileController.XuatThongKeTonKho(pa);
						JOptionPane.showMessageDialog(mntmNewMenuItem_2, "Xuất file thống kê thành công!");
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					} 
				}
			}
		});
		btnNewButton_3.setBackground(new Color(214, 214, 214));
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_3.setBounds(816, 84, 131, 32);
		panelthongke.add(btnNewButton_3);

		/////////////////////////// Quản Lý Tài Khoản/////////////////////////

		JPanel panelquanlytaikhoan = new JPanel();
//		if (tk.getQuyen() == 1) {

		tabbedPane.addTab("Quản Lý Tài Khoản", null, panelquanlytaikhoan, null);
		String[] headeruser = { "Tài Khoản", "Mật Khẩu", "Tên Nhân Viên","Email", "Quyền" };
		String[][] datauser = {};
		JComboBox cbbox1 = new JComboBox();
		cbbox1.setModel(new DefaultComboBoxModel(new String[] { "Admin\t", "Nhân Viên" }));
		cbbox1.setSelectedIndex(1);
		cbbox1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cbbox1.setBounds(505, 487, 97, 22);
		panelquanlytaikhoan.add(cbbox1);
		JTable tablequanlytaikhoan = new JTable();
		tablequanlytaikhoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					TaiKhoan newtk = QuanLyTaiKhoanController.getselecttaikhoan(tablequanlytaikhoan);
					txttaikhoan.setText(newtk.getUserName());
					txtpassword.setText(newtk.getPassWord());
					txttennv.setText(newtk.getTenNV());
					txtemail.setText(newtk.getEmail());
					if (newtk.getQuyen() == 0) {
						cbbox1.setSelectedIndex(1);
					} else {
						cbbox1.setSelectedIndex(0);
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		tablequanlytaikhoan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tablequanlytaikhoan.setModel(new DefaultTableModel(datauser, headeruser));
		DefaultTableModel modelquanlytaikhoan = (DefaultTableModel) tablequanlytaikhoan.getModel();

		QuanLyTaiKhoanController.tablequanlytaikhoan(modelquanlytaikhoan);
		panelquanlytaikhoan.setLayout(null);
		JScrollPane sptablequanlytaikhoan = new JScrollPane(tablequanlytaikhoan);
		sptablequanlytaikhoan.setBounds(48, 102, 981, 298);
		panelquanlytaikhoan.add(sptablequanlytaikhoan);

		JButton btntaotaikhoan = new JButton("Tạo Tài Khoản");
		btntaotaikhoan.setBounds(754, 439, 137, 36);
		btntaotaikhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaiKhoan user = new TaiKhoan();

				user.setUserName(txttaikhoan.getText());
				user.setPassWord(String.valueOf(txtpassword.getPassword()));
				user.setQuyen(0);
				if (cbbox1.getSelectedItem().toString().equals("Nhân Viên")) {
					user.setQuyen(0);
				} else {
					user.setQuyen(1);
				}
				user.setTenNV(txttennv.getText());
				user.setEmail(txtemail.getText());
				QuanLyTaiKhoanController.AddTK(user);

				try {
					QuanLyTaiKhoanController.tablequanlytaikhoan(modelquanlytaikhoan);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btntaotaikhoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btntaotaikhoan.setFocusPainted(false);
		btntaotaikhoan.setBackground(new Color(214, 214, 214));
		panelquanlytaikhoan.add(btntaotaikhoan);

		JButton btnsuataikhoan = new JButton("Cập Nhật");
		btnsuataikhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaiKhoan tktemp = new TaiKhoan();
				try {
					tktemp = QuanLyTaiKhoanController.getselecttaikhoan(tablequanlytaikhoan);
				} catch (ClassNotFoundException e2) {
					e2.printStackTrace();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}

				TaiKhoan user = new TaiKhoan();

				user.setUserName(tktemp.getUserName());
				user.setPassWord(String.valueOf(txtpassword.getPassword()));
				if (cbbox1.getSelectedItem().toString().equals("Nhân Viên")) {
					user.setQuyen(0);
				} else {
					user.setQuyen(1);
				}
				user.setTenNV(txttennv.getText());
				user.setEmail(txtemail.getText());
				QuanLyTaiKhoanController.UpdateTK(user);

				try {
					QuanLyTaiKhoanController.tablequanlytaikhoan(modelquanlytaikhoan);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

			}

		});
		btnsuataikhoan.setBounds(754, 487, 137, 36);
		btnsuataikhoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnsuataikhoan.setFocusPainted(false);
		btnsuataikhoan.setBackground(new Color(214, 214, 214));
		panelquanlytaikhoan.add(btnsuataikhoan);

		JButton btnxoataikhoan = new JButton("Xóa Tài Khoản");
		btnxoataikhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choose = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa tài khoản đã chọn?", "Question", JOptionPane.YES_NO_OPTION);
				if (choose == JOptionPane.YES_OPTION) {
					try {
						TaiKhoan tktemp = QuanLyTaiKhoanController.getselecttaikhoan(tablequanlytaikhoan);
						QuanLyTaiKhoanController.DeleteTK(tktemp.getUserName());
						QuanLyTaiKhoanController.tablequanlytaikhoan(modelquanlytaikhoan);
					}catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnxoataikhoan.setBounds(901, 439, 142, 36);
		btnxoataikhoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnxoataikhoan.setFocusPainted(false);
		btnxoataikhoan.setBackground(new Color(214, 214, 214));
		panelquanlytaikhoan.add(btnxoataikhoan);

		JButton btntimtaikhoan = new JButton("Tìm Kiếm");
		btntimtaikhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txttimtk.getText() != "Nhập Tên Tài khoản Cần Tìm") {
					try {
						QuanLyTaiKhoanController.tabletimkiemquanlytaikhoan(modelquanlytaikhoan, txttimtk.getText());
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		btntimtaikhoan.setBounds(829, 32, 102, 36);
		btntimtaikhoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btntimtaikhoan.setFocusPainted(false);
		btntimtaikhoan.setBackground(new Color(214, 214, 214));
		panelquanlytaikhoan.add(btntimtaikhoan);

		JLabel lblNewLabel_3 = new JLabel("Tài Khoản ");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(87, 449, 78, 14);
		panelquanlytaikhoan.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Mật Khẩu ");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(87, 478, 78, 14);
		panelquanlytaikhoan.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Quyền ");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(439, 491, 49, 14);
		panelquanlytaikhoan.add(lblNewLabel_5);

		txttaikhoan = new JTextField();
		txttaikhoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txttaikhoan.setBounds(175, 449, 164, 20);
		txttaikhoan.setColumns(10);
		panelquanlytaikhoan.add(txttaikhoan);

		JButton btndanhsachtaikhoan = new JButton("Làm Mới");
		btndanhsachtaikhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					QuanLyTaiKhoanController.tablequanlytaikhoan(modelquanlytaikhoan);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btndanhsachtaikhoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btndanhsachtaikhoan.setFocusPainted(false);
		btndanhsachtaikhoan.setBackground(new Color(214, 214, 214));
		btndanhsachtaikhoan.setBounds(941, 32, 102, 36);
		panelquanlytaikhoan.add(btndanhsachtaikhoan);

		txttimtk = new JTextField();
		txttimtk.setForeground(new Color(128, 128, 128));
		txttimtk.setHorizontalAlignment(SwingConstants.CENTER);
		txttimtk.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txttimtk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txttimtk.setText("");
			}
		});
		txttimtk.setText("Nhập Tên Tài khoản Cần Tìm");
		txttimtk.setColumns(10);
		txttimtk.setBounds(596, 34, 223, 34);
		panelquanlytaikhoan.add(txttimtk);

		JLabel lblNewLabel_4_1 = new JLabel("Tên Nhân Viên");
		lblNewLabel_4_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_4_1.setBounds(391, 460, 104, 14);
		panelquanlytaikhoan.add(lblNewLabel_4_1);

		txttennv = new JTextField();
		txttennv.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txttennv.setColumns(10);
		txttennv.setBounds(505, 457, 178, 20);
		panelquanlytaikhoan.add(txttennv);

		txtpassword = new JPasswordField();
		txtpassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtpassword.setBounds(175, 477, 164, 19);
		panelquanlytaikhoan.add(txtpassword);
		
		JButton btnNewButton_2 = new JButton("Nhập Dữ Liệu");
		btnNewButton_2.setBackground(new Color(214, 214, 214));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File("D:\\"));
				fs.setDialogTitle("Open a File");
				fs.setFileFilter(new FileTypeFilter(".xlsx", "Chọn file"));
				int result = fs.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File fi = fs.getSelectedFile();
					String path = fi.getPath();
					try {
						NhapFileController.NhapTaiKhoan(path);
						
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						e1.printStackTrace();
					} JOptionPane.showMessageDialog(mntmNewMenuItem_2, "Nhập dữ liệu tài khoản thành công!");
				}
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_2.setBounds(901, 489, 142, 34);
		panelquanlytaikhoan.add(btnNewButton_2);
		
		JLabel lblNewLabel_4_2 = new JLabel("Email");
		lblNewLabel_4_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_4_2.setBounds(87, 511, 78, 14);
		panelquanlytaikhoan.add(lblNewLabel_4_2);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtemail.setColumns(10);
		txtemail.setBounds(175, 506, 164, 20);
		panelquanlytaikhoan.add(txtemail);

//		}

	}
}
