package qlk.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import qlk.controller.QuanLySanPhamController;
import qlk.entity.DienThoai;
import qlk.entity.TaiKhoan;
import java.awt.Toolkit;

public class ThemSanPhamForm extends JFrame {

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
					ThemSanPhamForm frame = new ThemSanPhamForm(null);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
			}
		});
	}


	public ThemSanPhamForm(TaiKhoan tk) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ThemSanPhamForm.class.getResource("/images/warehouse.png")));

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

		setBounds(100, 100, 397, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 47, 369, 271);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTnSnPhm = new JLabel("Tên Điện Thoại");
		lblTnSnPhm.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTnSnPhm.setBounds(10, 10, 104, 18);
		panel.add(lblTnSnPhm);

		JLabel lblGi = new JLabel("Màu");
		lblGi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblGi.setBounds(10, 70, 104, 18);
		panel.add(lblGi);

		JLabel lblKhiLng = new JLabel("Thông tin");
		lblKhiLng.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblKhiLng.setBounds(10, 131, 104, 18);
		panel.add(lblKhiLng);

		JLabel lblnVTnh = new JLabel("Giá");
		lblnVTnh.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblnVTnh.setBounds(10, 193, 104, 18);
		panel.add(lblnVTnh);

		txttensp = new JTextField();
		txttensp.setColumns(10);
		txttensp.setBounds(124, 10, 202, 20);
		panel.add(txttensp);

		txtmau = new JTextField();
		txtmau.setColumns(10);
		txtmau.setBounds(124, 70, 202, 20);
		panel.add(txtmau);

		txtthongtin = new JTextField();
		txtthongtin.setColumns(10);
		txtthongtin.setBounds(124, 129, 202, 20);
		panel.add(txtthongtin);

		txtgia = new JTextField();
		txtgia.setColumns(10);
		txtgia.setBounds(124, 193, 202, 20);
		panel.add(txtgia);

		JButton btnNewButton = new JButton("Thêm Sản Phẩm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DienThoai sp = new DienThoai();
				sp.setTenDT(txttensp.getText());
				sp.setHangDT(txthangdt.getText());
				sp.setMauSac(txtmau.getText());
				sp.setSlg(Integer.parseInt(txtsoluong.getText()));
				sp.setThongSo(txtthongso.getText());
				sp.setThongTin(txtthongtin.getText());
				sp.setGia(Double.parseDouble(txtgia.getText()));

				QuanLySanPhamController.AddDT(sp);
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
		btnNewButton.setBackground(new Color(214, 214, 214));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton.setBounds(134, 223, 131, 38);
		panel.add(btnNewButton);

		txtthongso = new JTextField();
		txtthongso.setColumns(10);
		txtthongso.setBounds(124, 98, 202, 20);
		panel.add(txtthongso);

		JLabel txtlo = new JLabel("Thông Số");
		txtlo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtlo.setBounds(10, 100, 104, 18);
		panel.add(txtlo);
		
		JLabel lblHnginThoi = new JLabel("Hãng Điện Thoại");
		lblHnginThoi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblHnginThoi.setBounds(10, 38, 104, 18);
		panel.add(lblHnginThoi);
		
		txthangdt = new JTextField();
		txthangdt.setColumns(10);
		txthangdt.setBounds(124, 38, 202, 20);
		panel.add(txthangdt);
		
		JLabel lblSLng = new JLabel("Số Lượng");
		lblSLng.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblSLng.setBounds(10, 161, 104, 18);
		panel.add(lblSLng);
		
		txtsoluong = new JTextField();
		txtsoluong.setColumns(10);
		txtsoluong.setBounds(124, 159, 202, 20);
		panel.add(txtsoluong);

		JLabel lblNewLabel_1 = new JLabel("Thêm Sản Phẩm");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(124, 10, 150, 40);
		contentPane.add(lblNewLabel_1);
	}
}
