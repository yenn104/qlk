package qlk.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import qlk.controller.QuanLyXuatKhoController;
import qlk.entity.PhieuXuat;
import qlk.entity.TaiKhoan;
import java.awt.Toolkit;

public class SuaPhieuXuatForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtnv;
	private JTextField txtthanhtien;
	private JTextField txtlydo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuaPhieuXuatForm frame = new SuaPhieuXuatForm(null,  null);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public SuaPhieuXuatForm(TaiKhoan tk, PhieuXuat px) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SuaPhieuXuatForm.class.getResource("/images/warehouse.png")));
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
		setBounds(100, 100, 394, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Sửa Phiếu Xuất Kho");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(118, 11, 192, 40);
		contentPane.add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 61, 357, 210);
		contentPane.add(panel);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePicker.getJFormattedTextField().setText(px.getNgay().toString());
		SpringLayout springLayout = (SpringLayout) datePicker.getLayout();
		springLayout.putConstraint(SpringLayout.WEST, datePicker.getJFormattedTextField(), 0, SpringLayout.WEST, datePicker);
		datePicker.setBounds(134, 10, 202, 27);
		panel.add(datePicker);
		
		JLabel lblNgyXut = new JLabel("Ngày Xuất");
		lblNgyXut.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNgyXut.setBounds(20, 10, 104, 18);
		panel.add(lblNgyXut);
		
		JLabel lblThKho = new JLabel("Nhân Viên");
		lblThKho.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblThKho.setBounds(20, 45, 104, 18);
		panel.add(lblThKho);
		
		JLabel lblNgiXutKho = new JLabel("Thành Tiền");
		lblNgiXutKho.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNgiXutKho.setBounds(20, 73, 104, 18);
		panel.add(lblNgiXutKho);
		
		txtnv = new JTextField();
		txtnv.setColumns(10);
		txtnv.setBounds(134, 45, 202, 20);
		txtnv.setText(tk.getTenNV());
		panel.add(txtnv);
		
		txtthanhtien = new JTextField();
		txtthanhtien.setColumns(10);
		txtthanhtien.setBounds(134, 73, 202, 20);
		txtthanhtien.setText(String.format("%.0f%n",px.getThanhTien()));
		panel.add(txtthanhtien);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Chưa Xuất Kho", "Đã Xuất Kho"}));
		comboBox.setBounds(134, 129, 202, 21);
		panel.add(comboBox);
		
		JButton btnSaPhiuXut = new JButton("Sửa Phiếu Xuất Kho");
		btnSaPhiuXut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				
				Date ngayxuatkho;
				try {
					java.util.Date datepck = model.getValue();
					ngayxuatkho = new Date(datepck.getTime());
				} catch (Exception e2) {
					ngayxuatkho = px.getNgay();
				}
				
				
				PhieuXuat pxk = new PhieuXuat();
				pxk.setSoPhieu(px.getSoPhieu());
				pxk.setNgay(ngayxuatkho);
				pxk.setTenNV(txtnv.getText());
				pxk.setThanhTien(Double.parseDouble(txtthanhtien.getText()));
				pxk.setLydo(txtlydo.getText());
				pxk.setTinhTrang(false);
				if (comboBox.getSelectedItem().toString().equals("Đã Xuất Kho"))
				{
					pxk.setTinhTrang(true);
				}
				else
				{
					pxk.setTinhTrang(false);
				}
				
				QuanLyXuatKhoController.UpdateXK(pxk);
				
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
		btnSaPhiuXut.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnSaPhiuXut.setFocusPainted(false);
		btnSaPhiuXut.setBackground(new Color(214, 214, 214));
		btnSaPhiuXut.setBounds(134, 162, 131, 38);
		panel.add(btnSaPhiuXut);
		
		JLabel lblKhchHng = new JLabel("Tình Trạng");
		lblKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblKhchHng.setBounds(20, 128, 104, 18);
		panel.add(lblKhchHng);
		
		JLabel lblLDoXut = new JLabel("Lý Do Xuất");
		lblLDoXut.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblLDoXut.setBounds(20, 101, 104, 18);
		panel.add(lblLDoXut);
		
		txtlydo = new JTextField();
		txtlydo.setColumns(10);
		txtlydo.setBounds(134, 101, 202, 20);
		txtlydo.setText(px.getLydo());
		panel.add(txtlydo);
	}
}
