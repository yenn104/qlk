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

import qlk.controller.QuanLyNhapKhoController;
import qlk.entity.PhieuNhap;
import qlk.entity.TaiKhoan;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class SuaPhieuNhapForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtnv;
	private JTextField txtthanhtien;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuaPhieuNhapForm frame = new SuaPhieuNhapForm(null, null);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SuaPhieuNhapForm(TaiKhoan tk, PhieuNhap pn) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SuaPhieuNhapForm.class.getResource("/images/warehouse.png")));
		addWindowListener(new WindowAdapter() 
		{

			@Override
			public void windowClosing(WindowEvent e) {
				Home home;
				try {
					home = new Home(tk);
					home.setLocationRelativeTo(null);
					home.show();
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		setBounds(100, 100, 395, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Sửa Phiếu Nhập Kho");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(116, 10, 180, 40);
		contentPane.add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 45, 358, 240);
		contentPane.add(panel);

		JLabel lblNgyNhp = new JLabel("Ngày Nhập");
		lblNgyNhp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNgyNhp.setBounds(21, 37, 104, 18);
		panel.add(lblNgyNhp);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePicker.getJFormattedTextField().setText(pn.getNgay().toString());
		SpringLayout springLayout = (SpringLayout) datePicker.getLayout();
		springLayout.putConstraint(SpringLayout.WEST, datePicker.getJFormattedTextField(), 0, SpringLayout.WEST,datePicker);
		datePicker.setBounds(135, 37, 151, 27);
		panel.add(datePicker);
		
		UtilDateModel modelngaytt = new UtilDateModel();
		JDatePanelImpl datePanelngaytt = new JDatePanelImpl(modelngaytt, p);
		
		JLabel lblThKho = new JLabel("Nhân Viên");
		lblThKho.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblThKho.setBounds(21, 75, 104, 18);
		panel.add(lblThKho);
		
		txtnv = new JTextField();
		txtnv.setColumns(10);
		txtnv.setBounds(135, 75, 202, 20);
		txtnv.setText(tk.getTenNV());
		panel.add(txtnv);
		
		JLabel lblNgiXutKho = new JLabel("Thành Tiền");
		lblNgiXutKho.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNgiXutKho.setBounds(21, 105, 104, 18);
		panel.add(lblNgiXutKho);
		
		txtthanhtien = new JTextField();
		txtthanhtien.setColumns(10);
		txtthanhtien.setBounds(135, 105, 202, 20);
		txtthanhtien.setText(String.format("%.0f%n",pn.getThanhTien()));
		panel.add(txtthanhtien);
		
		JLabel lblKhchHng = new JLabel("Tình Trạng");
		lblKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblKhchHng.setBounds(21, 133, 104, 18);
		panel.add(lblKhchHng);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Chưa Nhập Kho", "Đã Nhập Kho"}));
		comboBox.setBounds(135, 134, 202, 21);
		panel.add(comboBox);
		
		JButton btnToPhiuXut = new JButton("Sửa Phiếu Nhập");
		btnToPhiuXut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Date ngaynhapkho;
				try {
					java.util.Date datepck = model.getValue();
					ngaynhapkho = new Date(datepck.getTime());
				} catch (Exception e2) {
					ngaynhapkho = pn.getNgay();
				}

				PhieuNhap pnk = new PhieuNhap();
				pnk.setSoPhieu(pn.getSoPhieu());
				pnk.setNgay(ngaynhapkho);
				pnk.setTenNV(txtnv.getText());
				pnk.setThanhTien(Double.parseDouble(txtthanhtien.getText()));
				pnk.setTinhTrang(false);
				
				if (comboBox.getSelectedItem().toString().equals("Đã Nhập Kho"))
				{
					pnk.setTinhTrang(true);
				}
				else
				{
					pnk.setTinhTrang(false);
				}
				QuanLyNhapKhoController.UpdateNK(pnk);
				
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
		btnToPhiuXut.setBounds(135, 179, 131, 38);
		panel.add(btnToPhiuXut);
	}
}
