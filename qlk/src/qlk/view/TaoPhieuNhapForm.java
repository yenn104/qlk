package qlk.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


import qlk.controller.QuanLyNhapKhoController;
import qlk.controller.QuanLyXuatKhoController;
import qlk.entity.PhieuNhap;
import qlk.entity.PhieuXuat;
import qlk.entity.TaiKhoan;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class TaoPhieuNhapForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtnv;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaoPhieuNhapForm frame = new TaoPhieuNhapForm(null);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TaoPhieuNhapForm(TaiKhoan tk) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TaoPhieuNhapForm.class.getResource("/images/warehouse.png")));
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
		setBounds(100, 100, 399, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 45, 369, 211);
		contentPane.add(panel);

		JLabel lblNgyNhp = new JLabel("Ngày Nhập");
		lblNgyNhp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNgyNhp.setBounds(26, 32, 104, 18);
		panel.add(lblNgyNhp);


		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePicker.setBounds(140, 32, 202, 27);
		panel.add(datePicker);
		
		
		UtilDateModel modelngaytt = new UtilDateModel();
		
		txtnv = new JTextField();
		txtnv.setColumns(10);
		txtnv.setBounds(140, 69, 202, 20);
		txtnv.setText(tk.getTenNV());
		panel.add(txtnv);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Chưa Nhập Kho", "Đã Nhập Kho"}));
		comboBox.setBounds(140, 100, 202, 21);
		panel.add(comboBox);
		
		JButton btnToPhiuXut = new JButton("Tạo Phiếu Nhập Kho");
		btnToPhiuXut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date ngaynhapkho = null;
				try {
					java.util.Date datepck = model.getValue();
					ngaynhapkho = new Date(datepck.getTime());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(btnToPhiuXut, "Vui Lòng Nhập Ngày Nhập Kho");
				}
				
				PhieuNhap pnk = new PhieuNhap();
				pnk.setNgay(ngaynhapkho);
				pnk.setTenNV(txtnv.getText());
				pnk.setTinhTrang(false);
				if (comboBox.getSelectedItem().toString().equals("Đã Nhập Kho"))
				{
					pnk.setTinhTrang(true);
				}
				else
				{
					pnk.setTinhTrang(false);
				}
				QuanLyNhapKhoController.AddNK(pnk);
				
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
		btnToPhiuXut.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnToPhiuXut.setFocusPainted(false);
		btnToPhiuXut.setBackground(new Color(214, 214, 214));
		btnToPhiuXut.setBounds(140, 142, 131, 38);
		panel.add(btnToPhiuXut);
		
		JLabel lblKhchHng = new JLabel("Tình Trạng");
		lblKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblKhchHng.setBounds(26, 99, 104, 18);
		panel.add(lblKhchHng);
		
		JLabel lblThKho = new JLabel("Nhân Viên");
		lblThKho.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblThKho.setBounds(26, 69, 104, 18);
		panel.add(lblThKho);

		JLabel lblNewLabel_1 = new JLabel("Tạo Phiếu Nhập Kho");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(116, 10, 180, 40);
		contentPane.add(lblNewLabel_1);
	}
}
