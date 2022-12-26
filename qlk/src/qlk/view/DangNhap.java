package qlk.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
//import java.sql.Date;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import qlk.controller.QuanLyTaiKhoanController;
import qlk.entity.TaiKhoan;
import java.awt.Toolkit;



public class DangNhap extends JFrame {

	private JPanel contentPane;
	private JTextField txttaikhoan;
	private JPasswordField txtmatkhau;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap frame = new DangNhap();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DangNhap() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DangNhap.class.getResource("/images/warehouse.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Đăng Nhập");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(168, 21, 103, 38);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(26, 58, 408, 187);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Tài Khoản");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(55, 26, 71, 28);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Mật Khẩu");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(55, 75, 71, 28);
		panel.add(lblNewLabel_1_1);

		txttaikhoan = new JTextField();
		txttaikhoan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		txttaikhoan.setBounds(142, 25, 190, 28);
		panel.add(txttaikhoan);
		txttaikhoan.setColumns(10);

		JButton btndangnhap = new JButton("Đăng Nhập");
		btndangnhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String pass = String.valueOf(txtmatkhau.getPassword());
					if (txttaikhoan.getText().equals("") || pass.equals(""))
					{
						if (txttaikhoan.getText().equals("") && pass.equals(""))
						{
							JOptionPane.showMessageDialog(btndangnhap, "Tài Khoản Và Mật Khẩu Không Được Để Trống!");
							txttaikhoan.setBackground(new Color(255, 128, 128));
							txtmatkhau.setBackground(new Color(255, 128, 128));
						}
						else
						{
							if (txttaikhoan.getText().equals(""))
							{
								JOptionPane.showMessageDialog(btndangnhap, "Tài Khoản Không Được Để Trống");
								txttaikhoan.setBackground(new Color(255, 128, 128));
								
							}
							else if (pass.equals(""))
							{
								txttaikhoan.setBackground(new Color(255, 255, 255));
								JOptionPane.showMessageDialog(btndangnhap, "Mật Khẩu Không Được Để Trống");
								txtmatkhau.setBackground(new Color(255, 128, 128));
							}
						}
						
					}
					else
					{
						TaiKhoan checktk = QuanLyTaiKhoanController.FindTK(txttaikhoan.getText());
					
							if (checktk.getUserName().equals(txttaikhoan.getText())) 
							{
								txttaikhoan.setBackground(new Color(255, 255, 255));
								if (checktk.getPassWord().equals(pass)) 
								{
									txtmatkhau.setBackground(new Color(255, 255, 255));
								
										Home home = new Home(checktk);
										home.setLocationRelativeTo(null);
										home.show();
										dispose();
									
								}
								else
								{
									JOptionPane.showMessageDialog(btndangnhap, "Mật Khẩu Không Đúng, Vui Lòng Nhập Lại");
									txtmatkhau.setBackground(new Color(255, 128, 128));									
								}
							}
							else
							{
								JOptionPane.showMessageDialog(btndangnhap, "Tài Khoản Không Tồn Tại");
								txttaikhoan.setBackground(new Color(255, 128, 128));
							}
						}
						
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		
		btndangnhap.setBackground(new Color(214, 214, 214));
		btndangnhap.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btndangnhap.setBounds(95, 134, 99, 31);
		panel.add(btndangnhap);
		
		txtmatkhau = new JPasswordField();
		txtmatkhau.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtmatkhau.setEchoChar('*');
		txtmatkhau.setBounds(142, 76, 190, 28);
		panel.add(txtmatkhau);
		
		JButton btnNewButton = new JButton("Quên Mật Khẩu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog("Nhập Tài Khoản Cần Lấy Lại Mật Khẩu:");
				if (!input.equals(""))
				{
					TaiKhoan checktk = QuanLyTaiKhoanController.FindTK(input);
					
					if (checktk.getUserName().equals(input) && !checktk.getEmail().equals("")) 
					{
						final String fromEmail = "nhokkid19@gmail.com";
					    // Mat khai email cua ban
					    final String password = "sgseyucxuwyzdbkg";
					    // dia chi email nguoi nhan
					    final String toEmail = checktk.getEmail();
					    final String subject = "Mật Khẩu Mới";
					    double randomDouble = Math.random();
			            randomDouble = randomDouble * 100000 + 1;
			            int randomInt = (int) randomDouble;
					    Properties props = new Properties();
					    String body = String.valueOf(randomInt);
					    props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
					    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
					    props.put("mail.smtp.port", "587"); //TLS Port
					    props.put("mail.smtp.auth", "true"); //enable authentication
					    props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
					    props.put("mail.smtp.ssl.protocols", "TLSv1.2");

					    Session session_sendemail = Session.getDefaultInstance(props, new javax.mail.Authenticator() { protected PasswordAuthentication getPasswordAuthentication() {
				            return new PasswordAuthentication(fromEmail, password);}});
					   
					  
					    
					    try {
					    	MimeMessage msg = new MimeMessage(session_sendemail);
					    	msg.setFrom(new InternetAddress(fromEmail));
					    	msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
					    	msg.setSubject(subject);
					    	msg.setText(body);
					    	Transport.send(msg);
					    	checktk.setPassWord(body);
					    	QuanLyTaiKhoanController.UpdateTK(checktk);
					    	JOptionPane.showMessageDialog(btnNewButton, "Reset mật khẩu thành công kiểm tra email để lấy mật khẩu mới ");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(btndangnhap, "Tài Khoản Không Tồn Tại");
						txttaikhoan.setBackground(new Color(255, 128, 128));
					}
				}
			    
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton.setBounds(204, 134, 127, 31);
		panel.add(btnNewButton);

	
	}
}
