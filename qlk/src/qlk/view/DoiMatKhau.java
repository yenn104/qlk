package qlk.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import qlk.controller.QuanLyTaiKhoanController;
import qlk.entity.TaiKhoan;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class DoiMatKhau extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtmkcu;
	private JPasswordField txtmkmoi;
	private JPasswordField txtnhaplaimk;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoiMatKhau frame = new DoiMatKhau(null);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DoiMatKhau(TaiKhoan tk) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DoiMatKhau.class.getResource("/images/warehouse.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mật Khẩu Cũ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel.setBounds(64, 79, 123, 13);
		contentPane.add(lblNewLabel);
		
		
		
		JLabel lblMtKhuMi = new JLabel("Mật Khẩu Mới");
		lblMtKhuMi.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblMtKhuMi.setBounds(64, 102, 123, 13);
		contentPane.add(lblMtKhuMi);
		
		txtmkcu = new JPasswordField();
		txtmkcu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (String.valueOf(txtmkcu.getPassword()).equals(""))
				{
					txtmkcu.setBackground(new Color(255, 128, 128));
				}
				else
				{
					txtmkcu.setBackground(new Color(255, 255, 255));
				}
			}
		});
		
		txtmkcu.setBounds(204, 76, 192, 19);
		
		contentPane.add(txtmkcu);
		
		txtmkmoi = new JPasswordField();
		txtmkmoi.setBounds(204, 99, 192, 19);
		contentPane.add(txtmkmoi);
		
		JLabel lblNewLabel_1 = new JLabel("Đổi Mật Khẩu");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(178, 25, 147, 28);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton(" Đổi Mật Khẩu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (String.valueOf(txtmkcu.getPassword()).equals(tk.getPassWord()))
				{
					txtmkcu.setBackground(new Color(255, 255, 255));
					if(String.valueOf(txtmkcu.getPassword()).equals(String.valueOf(txtmkmoi.getPassword())))
					{
						JOptionPane.showMessageDialog(btnNewButton, "Mật Khẩu Mới Không Được Trùng Với Mật Khẩu Cũ");
						txtmkmoi.setBackground(new Color(255, 128, 128));
					}
					else if (String.valueOf(txtmkmoi.getPassword()).equals("") || String.valueOf(txtnhaplaimk.getPassword()).equals("") )
					{
						JOptionPane.showMessageDialog(btnNewButton, "Mật Khẩu Mới Và Nhập Lại Mật Khẩu Mới Không Được Bỏ Trống");
						txtmkmoi.setBackground(new Color(255, 128, 128));
						txtnhaplaimk.setBackground(new Color(255, 128, 128));
					}
					else if (String.valueOf(txtmkmoi.getPassword()).equals(String.valueOf(txtnhaplaimk.getPassword())))
					{
						tk.setPassWord(String.valueOf(txtmkmoi.getPassword()));
						QuanLyTaiKhoanController.UpdateTK(tk);
						Home home;
						try {
							home = new Home(tk);
							home.setLocationRelativeTo(null);
							home.show();
							dispose();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(btnNewButton, "Mật Khẩu Mới Và Nhập Lại Mật Khẩu Mới không Giống Nhau");
						txtmkmoi.setBackground(new Color(255, 128, 128));
						txtnhaplaimk.setBackground(new Color(255, 128, 128));
					}
				}
				else
				{
					JOptionPane.showMessageDialog(btnNewButton, "Mật Khẩu Cũ Không Đúng");
					txtmkcu.setBackground(new Color(255, 128, 128));
				}
			}
		});
		btnNewButton.setBounds(197, 168, 115, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNhpLiMt = new JLabel("Nhập Lại Mật Khẩu Mới");
		lblNhpLiMt.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNhpLiMt.setBounds(64, 128, 123, 13);
		contentPane.add(lblNhpLiMt);
		
		txtnhaplaimk = new JPasswordField();
		txtnhaplaimk.setBounds(204, 125, 192, 19);
		contentPane.add(txtnhaplaimk);
	}
}
