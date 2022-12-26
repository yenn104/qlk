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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import qlk.controller.QuanLyXuatKhoController;
import qlk.entity.PhieuXuat;
import qlk.entity.TaiKhoan;
import java.awt.Toolkit;

public class TaoPhieuXuatForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtnv;
	private JTextField txtlydo;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaoPhieuXuatForm frame = new TaoPhieuXuatForm(null);

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TaoPhieuXuatForm(TaiKhoan tk) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TaoPhieuXuatForm.class.getResource("/images/warehouse.png")));
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
		setBounds(100, 100, 409, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(22, 50, 361, 204);
		contentPane.add(panel);

		JLabel lblNgyXut = new JLabel("Ngày Xuất");
		lblNgyXut.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNgyXut.setBounds(21, 25, 104, 18);
		panel.add(lblNgyXut);

		JLabel lblThKho = new JLabel("Nhân Viên");
		lblThKho.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblThKho.setBounds(21, 60, 104, 18);
		panel.add(lblThKho);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePicker.setBounds(135, 25, 202, 27);
		panel.add(datePicker);

		txtnv = new JTextField();
		txtnv.setColumns(10);
		txtnv.setBounds(135, 60, 202, 20);
		txtnv.setText(tk.getTenNV());
		panel.add(txtnv);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Chưa Xuất Kho", "Đã Xuất Kho"}));
		comboBox.setBounds(135, 114, 202, 21);
		panel.add(comboBox);

		JButton btnToPhiuXut = new JButton("Tạo Phiếu Xuất Kho");
		btnToPhiuXut.setFocusPainted(false);
		btnToPhiuXut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Date ngayxuatkho = null;
					
					try {

						java.util.Date datepck = model.getValue();
						ngayxuatkho = new Date(datepck.getTime());
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(btnToPhiuXut, "Vui Lòng Nhập Ngày Xuất Kho");
					}
					PhieuXuat pxk = new PhieuXuat();
					pxk.setNgay(ngayxuatkho);
					pxk.setTenNV(txtnv.getText());
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
					
					QuanLyXuatKhoController.AddXK(pxk);
					
					
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
		btnToPhiuXut.setBackground(new Color(214, 214, 214));
		btnToPhiuXut.setBounds(134, 145, 131, 38);
		panel.add(btnToPhiuXut);

		JLabel lblKhchHng = new JLabel("Tình Trạng");
		lblKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblKhchHng.setBounds(21, 113, 104, 18);
		panel.add(lblKhchHng);
		
		
		
		JLabel lblLDoXut = new JLabel("Lý Do Xuất");
		lblLDoXut.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblLDoXut.setBounds(21, 86, 104, 18);
		panel.add(lblLDoXut);
		
		txtlydo = new JTextField();
		txtlydo.setColumns(10);
		txtlydo.setBounds(135, 86, 202, 20);
		panel.add(txtlydo);

		JLabel lblNewLabel_1 = new JLabel("Tạo Phiếu Xuất Kho");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(120, 20, 165, 40);
		contentPane.add(lblNewLabel_1);
	}
}
