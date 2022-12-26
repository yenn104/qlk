package qlk.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import qlk.controller.QuanLySanPhamController;
import qlk.entity.DienThoai;
import qlk.entity.TaiKhoan;
import java.awt.Toolkit;

public class SuaSanPhamForm extends JFrame {

	private JPanel contentPane;
	private JTextField txttensp;
	private JTextField txtmau;
	private JTextField txtthongtin;
	private JTextField txtgia;
	private JTextField txtthongso;
	private JTextField txthangdt;
	private JTextField txtsoluong;

	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuaSanPhamForm frame = new SuaSanPhamForm(null, null);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SuaSanPhamForm(TaiKhoan tk, DienThoai dt) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SuaSanPhamForm.class.getResource("/images/warehouse.png")));

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				Home home;
				try
				{
					home = new Home(tk);
					home.setLocationRelativeTo(null);
					home.show();
					dispose();
				} 
				catch (ClassNotFoundException | SQLException e1) 
				{
					e1.printStackTrace();
				}

			}
		});

		setBounds(100, 100, 404, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Sửa Sản Phẩm");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(140, 10, 150, 40);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(11, 46, 369, 266);
		contentPane.add(panel);
		
		JLabel lblTnSnPhm = new JLabel("Tên Điện Thoại");
		lblTnSnPhm.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTnSnPhm.setBounds(21, 10, 104, 18);
		panel.add(lblTnSnPhm);
		
		JLabel lblGi = new JLabel("Màu");
		lblGi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblGi.setBounds(21, 70, 104, 18);
		panel.add(lblGi);
		
		JLabel lblKhiLng = new JLabel("Thông tin");
		lblKhiLng.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblKhiLng.setBounds(21, 131, 104, 18);
		panel.add(lblKhiLng);
		
		JLabel lblnVTnh = new JLabel("Giá");
		lblnVTnh.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblnVTnh.setBounds(21, 188, 104, 18);
		panel.add(lblnVTnh);
		
		txttensp = new JTextField();
		txttensp.setColumns(10);
		txttensp.setBounds(135, 10, 202, 20);
		txttensp.setText(dt.getTenDT());
		panel.add(txttensp);
		
		txtmau = new JTextField();
		txtmau.setColumns(10);
		txtmau.setBounds(135, 70, 202, 20);
		txtmau.setText(dt.getMauSac());
		panel.add(txtmau);
		
		txtthongtin = new JTextField();
		txtthongtin.setColumns(10);
		txtthongtin.setBounds(135, 129, 202, 20);
		txtthongtin.setText(dt.getThongTin());
		panel.add(txtthongtin);
		
		txtgia = new JTextField();
		txtgia.setColumns(10);
		txtgia.setBounds(135, 188, 202, 20);
		txtgia.setText(String.format("%.0f",dt.getGia()));
		panel.add(txtgia);
		
		JButton btnSaSnPhm = new JButton("Sửa Sản Phẩm");
		btnSaSnPhm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DienThoai sp = new DienThoai();
				sp.setMaDT(dt.getMaDT());
				sp.setTenDT(txttensp.getText());
				sp.setHangDT(txthangdt.getText());
				sp.setMauSac(txtmau.getText());
				sp.setSlg(Integer.parseInt(txtsoluong.getText()));
				sp.setThongSo(txtthongso.getText());
				sp.setThongTin(txtthongtin.getText());
				sp.setGia(Double.parseDouble(txtgia.getText()));

				QuanLySanPhamController.UpdateDT(sp);
				try {
					Home home = new Home(tk);
					home.setLocationRelativeTo(null);
					home.show();
					dispose();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSaSnPhm.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnSaSnPhm.setBackground(new Color(214, 214, 214));
		btnSaSnPhm.setBounds(135, 218, 131, 38);
		panel.add(btnSaSnPhm);
		
		txtthongso = new JTextField();
		txtthongso.setColumns(10);
		txtthongso.setBounds(135, 98, 202, 20);
		txtthongso.setText(dt.getThongSo());
		panel.add(txtthongso);
		
		JLabel txtlo = new JLabel("Thông Số");
		txtlo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtlo.setBounds(21, 100, 104, 18);
		panel.add(txtlo);
		
		JLabel lblHnginThoi = new JLabel("Hãng Điện Thoại");
		lblHnginThoi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblHnginThoi.setBounds(21, 38, 104, 18);
		panel.add(lblHnginThoi);
		
		txthangdt = new JTextField();
		txthangdt.setColumns(10);
		txthangdt.setBounds(135, 38, 202, 20);
		txthangdt.setText(dt.getHangDT());
		panel.add(txthangdt);
		
		JLabel lblSLng = new JLabel("Số Lượng");
		lblSLng.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblSLng.setBounds(21, 161, 104, 18);
		panel.add(lblSLng);
		
		txtsoluong = new JTextField();
		txtsoluong.setColumns(10);
		txtsoluong.setBounds(135, 159, 202, 20);
		txtsoluong.setText(String.valueOf(dt.getSlg()));
		panel.add(txtsoluong);
	}
}
