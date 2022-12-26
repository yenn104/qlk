package qlk.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import qlk.controller.ChiTietNhapKhoController;
import qlk.controller.QuanLyNhapKhoController;
import qlk.controller.QuanLySanPhamController;
import qlk.entity.ChiTietPN;
import qlk.entity.DienThoai;
import qlk.entity.PhieuNhap;
import qlk.entity.TaiKhoan;
import java.awt.Toolkit;

public class ChiTietNhapKhoForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtgianhap;
	private JTextField txtsoluong;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChiTietNhapKhoForm frame = new ChiTietNhapKhoForm(null,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	public ChiTietNhapKhoForm(TaiKhoan tk, PhieuNhap pn) throws ClassNotFoundException, SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChiTietNhapKhoForm.class.getResource("/images/warehouse.png")));
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				this.windowClosed(e);
//				Home home;
//				try {
//					home = new Home(tk);
//					home.setLocationRelativeTo(null);
//					home.show();
//					dispose();
//				} catch (ClassNotFoundException | SQLException e1) {
//					e1.printStackTrace();
//				}

			}
		});

		setBounds(100, 100, 1136, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1112, 426);
		contentPane.add(panel);
		panel.setLayout(null);

		JComboBox comboBoxmasanpham = new JComboBox();
		comboBoxmasanpham.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		comboBoxmasanpham.setBackground(new Color(214, 214, 214));
		comboBoxmasanpham.setBounds(125, 320, 446, 21);
		panel.add(comboBoxmasanpham);

		

		List<DienThoai> listmasp = new ArrayList<DienThoai>();
		QuanLySanPhamController.FindAllDT(listmasp);
		for (int i = 0; i < listmasp.size(); i++) {
			DienThoai tempmasp = listmasp.get(i);
			comboBoxmasanpham.addItem(tempmasp.getMaDT() + " - " + tempmasp.getTenDT() + " - SL: " + tempmasp.getSlg()+ " - Gia: "+ String.format("%.0f",tempmasp.getGia()));
		}

		JLabel lbmasp = new JLabel("New label");
		lbmasp.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lbmasp.setVisible(false);
		lbmasp.setBounds(581, 322, 194, 17);
		panel.add(lbmasp);

		String[] header = { "Mã Chi Tiết Nhập Kho", "Mã Phiếu Nhập Kho", "Mã Điện Thoại", "Số Lượng", "Giá Nhập", "Tổng Tiền" };
		String[][] data = {};

		JTable tablechitietnhapkho = new JTable();
		tablechitietnhapkho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ChiTietPN ctnk = ChiTietNhapKhoController.getselectchitietphieunhap(tablechitietnhapkho, pn.getSoPhieu());
					
					lbmasp.setText("Mã Sản Phẩm Cũ :" + ctnk.getMaDT());
					lbmasp.setVisible(true);
				
					txtsoluong.setText(String.valueOf(ctnk.getSlg()));
					txtgianhap.setText(String.format("%.0f",ctnk.getGiaNhap()));

				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		
		tablechitietnhapkho.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tablechitietnhapkho.setModel(new DefaultTableModel(data, header));
		DefaultTableModel model = (DefaultTableModel) tablechitietnhapkho.getModel();
		ChiTietNhapKhoController.tablechitietphieunhap(model, pn.getSoPhieu());
		
		JScrollPane spTablechitietnhapkho = new JScrollPane(tablechitietnhapkho);
		spTablechitietnhapkho.setBounds(39, 66, 1027, 223);
		panel.add(spTablechitietnhapkho);

		JLabel lblMSnPhm = new JLabel("Sản Phẩm");
		lblMSnPhm.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMSnPhm.setBackground(new Color(214, 214, 214));
		lblMSnPhm.setBounds(39, 324, 115, 14);
		panel.add(lblMSnPhm);

		JLabel lblTnSnPhm = new JLabel("Giá Nhập");
		lblTnSnPhm.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTnSnPhm.setBackground(new Color(214, 214, 214));
		lblTnSnPhm.setBounds(39, 378, 115, 14);
		panel.add(lblTnSnPhm);

		txtgianhap = new JTextField();
		txtgianhap.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtgianhap.setColumns(10);
		txtgianhap.setBounds(125, 372, 446, 20);
		panel.add(txtgianhap);

		JButton btnthem = new JButton("Thêm");
		btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChiTietPN ctpn = new ChiTietPN();
				ctpn.setSoPhieu(pn.getSoPhieu());
				String masp = comboBoxmasanpham.getSelectedItem().toString();
				for (int i = 0; i < listmasp.size(); i++) {
					DienThoai tempmasp = listmasp.get(i);
					String tempstr = tempmasp.getMaDT() + " - " + tempmasp.getTenDT() + " - SL: " + tempmasp.getSlg()+ " - Gia: "+ String.format("%.0f",tempmasp.getGia());
					if (tempstr.equals(masp))
					{
						ctpn.setMaDT(tempmasp.getMaDT());
					}
				}
				ctpn.setSlg(Integer.parseInt(txtsoluong.getText()));
				ctpn.setGiaNhap(Double.parseDouble(txtgianhap.getText()));
				
				DienThoai dt = QuanLySanPhamController.FindDT(ctpn.getMaDT());
				dt.setSlg(dt.getSlg() + ctpn.getSlg());
				
				QuanLySanPhamController.UpdateSlgDT(dt.getMaDT(), dt.getSlg());
				ChiTietNhapKhoController.Add(ctpn);
				QuanLyNhapKhoController.UpdateThanhTien(pn);
				try {
					ChiTietNhapKhoController.tablechitietphieunhap(model, pn.getSoPhieu());
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnthem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnthem.setBackground(new Color(214, 214, 214));
		btnthem.setBounds(722, 361, 87, 35);
		panel.add(btnthem);

		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ChiTietPN pxct = ChiTietNhapKhoController.getselectchitietphieunhap(tablechitietnhapkho, pn.getSoPhieu());
					int newsl = Integer.parseInt(txtsoluong.getText()) - pxct.getSlg();
					pxct.setSlg(Integer.parseInt(txtsoluong.getText()));
					DienThoai dt = QuanLySanPhamController.FindDT(pxct.getMaDT());
					dt.setSlg(dt.getSlg() + newsl);
					QuanLySanPhamController.UpdateSlgDT(dt.getMaDT(), dt.getSlg());
					ChiTietNhapKhoController.Update(pxct);
					
					ChiTietNhapKhoController.tablechitietphieunhap(model, pn.getSoPhieu());
					QuanLyNhapKhoController.UpdateThanhTien(pn);

				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnSua.setBackground(new Color(214, 214, 214));
		btnSua.setBounds(819, 361, 87, 35);
		panel.add(btnSua);

		JButton btnTrV = new JButton("Trở về");
		btnTrV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
//				Home home;
//				try {
//					home = new Home(tk);
//					home.setLocationRelativeTo(null);
//					home.show();
//					dispose();
//				} catch (ClassNotFoundException | SQLException e1) {
//					e1.printStackTrace();
//				}

			}
		});
		btnTrV.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnTrV.setBackground(new Color(214, 214, 214));
		btnTrV.setBounds(916, 361, 87, 35);
		panel.add(btnTrV);
		
		JLabel lblSLng = new JLabel("Số Lượng");
		lblSLng.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSLng.setBackground(new Color(214, 214, 214));
		lblSLng.setBounds(39, 351, 115, 14);
		panel.add(lblSLng);
		
		txtsoluong = new JTextField();
		txtsoluong.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtsoluong.setColumns(10);
		txtsoluong.setBounds(125, 347, 446, 20);
		panel.add(txtsoluong);
	}
}
