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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import qlk.controller.ChiTietXuatKhoController;
import qlk.controller.QuanLySanPhamController;
import qlk.controller.QuanLyXuatKhoController;
import qlk.entity.ChiTietPX;
import qlk.entity.DienThoai;
import qlk.entity.PhieuXuat;
import qlk.entity.TaiKhoan;
import java.awt.Toolkit;

public class ChiTietXuatKhoForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtsoluong;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChiTietXuatKhoForm frame = new ChiTietXuatKhoForm(null, null);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ChiTietXuatKhoForm(TaiKhoan tk, PhieuXuat px) throws ClassNotFoundException, SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChiTietXuatKhoForm.class.getResource("/images/warehouse.png")));
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				this.windowClosed(e);
			}
		});
		
		setBounds(100, 100, 817, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 789, 298);
		contentPane.add(panel);
		panel.setLayout(null);

		JComboBox comboBoxmasanpham = new JComboBox();

		comboBoxmasanpham.setBackground(new Color(214, 214, 214));
		comboBoxmasanpham.setBounds(79, 204, 313, 21);
		panel.add(comboBoxmasanpham);

		List<DienThoai> listmasp = new ArrayList<DienThoai>();
		QuanLySanPhamController.FindAllDT(listmasp);
		for (int i = 0; i < listmasp.size(); i++) {
			DienThoai tempmasp = listmasp.get(i);
			comboBoxmasanpham.addItem(tempmasp.getMaDT() + " - " + tempmasp.getTenDT() + " - SL: " + tempmasp.getSlg());
		}

		JLabel lbmasp = new JLabel("New label");
		lbmasp.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbmasp.setVisible(false);
		lbmasp.setBounds(420, 206, 77, 17);
		panel.add(lbmasp);

		String[] header = { "Mã Chi Tiết Xuất Kho", "Mã Phiếu Xuất Kho", "Mã Điện Thoại", "Số Lượng" };
		String[][] data = {};

		JTable tablechitietxuakho = new JTable();
		tablechitietxuakho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ChiTietPX ctxk = ChiTietXuatKhoController.getselectchitietphieuxuat(tablechitietxuakho, px.getSoPhieu());
					lbmasp.setText("Mã Sản Phẩm Cũ :" + ctxk.getMaDT());
					lbmasp.setVisible(true);
				
					txtsoluong.setText(String.valueOf(ctxk.getSlg()));

				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		tablechitietxuakho.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		tablechitietxuakho.setModel(new DefaultTableModel(data, header));
		DefaultTableModel model = (DefaultTableModel) tablechitietxuakho.getModel();
		ChiTietXuatKhoController.tablechitietphieuxuat(model, px.getSoPhieu());

		JScrollPane spTablechitietxuakho = new JScrollPane(tablechitietxuakho);
		spTablechitietxuakho.setBounds(10, 11, 771, 156);
		panel.add(spTablechitietxuakho);

		JButton btnNewButton = new JButton("Sửa");
		btnNewButton.setBackground(new Color(214, 214, 214));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ChiTietPX pxct = ChiTietXuatKhoController.getselectchitietphieuxuat(tablechitietxuakho, px.getSoPhieu());
					int newsl = Integer.parseInt(txtsoluong.getText()) - pxct.getSlg();
				
						
						DienThoai dt = QuanLySanPhamController.FindDT(pxct.getMaDT());
						double tt = dt.getGia() * pxct.getSlg();
						px.setThanhTien(px.getThanhTien() - tt);
						pxct.setSlg(Integer.parseInt(txtsoluong.getText()));
						
						dt.setSlg(dt.getSlg() - newsl);
						if (dt.getSlg() < 0)
						{
							JOptionPane.showMessageDialog(btnNewButton, "Không Đủ Số Lượng Sản Phẩm Để Xuất");
						}
						else
						{
							QuanLySanPhamController.UpdateSlgDT(dt.getMaDT(), dt.getSlg());
							ChiTietXuatKhoController.Update(pxct);
							double ttnew = dt.getGia() * Integer.parseInt(txtsoluong.getText()); 
							px.setThanhTien(px.getThanhTien() + ttnew);
							QuanLyXuatKhoController.UpdateXK(px);
						}
					ChiTietXuatKhoController.tablechitietphieuxuat(model, px.getSoPhieu());
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton.setBounds(527, 228, 89, 23);
		panel.add(btnNewButton);

		JLabel lblMSnPhm = new JLabel("Sản Phẩm");
		lblMSnPhm.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblMSnPhm.setBackground(new Color(214, 214, 214));
		lblMSnPhm.setBounds(10, 204, 115, 14);
		panel.add(lblMSnPhm);

		JLabel lblSLng = new JLabel("Số Lượng");
		lblSLng.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblSLng.setBackground(new Color(214, 214, 214));
		lblSLng.setBounds(10, 231, 115, 14);
		panel.add(lblSLng);

		txtsoluong = new JTextField();
		txtsoluong.setColumns(10);
		txtsoluong.setBounds(79, 231, 313, 20);
		panel.add(txtsoluong);

		JButton btnNewButton_2 = new JButton("Thêm");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChiTietPX ctpx = new ChiTietPX();
				ctpx.setSoPhieu(px.getSoPhieu());
				
				String masp = comboBoxmasanpham.getSelectedItem().toString();
				for (int i = 0; i < listmasp.size(); i++) {
					DienThoai tempmasp = listmasp.get(i);
					String tempstr = tempmasp.getMaDT() + " - " + tempmasp.getTenDT() + " - SL: " + tempmasp.getSlg();
					if (tempstr.equals(masp))
					{
						ctpx.setMaDT(tempmasp.getMaDT());
					}
				}
				ctpx.setSlg(Integer.parseInt(txtsoluong.getText()));
				DienThoai dt = QuanLySanPhamController.FindDT(ctpx.getMaDT());
				dt.setSlg(dt.getSlg() - ctpx.getSlg());
				if (dt.getSlg() < 0)
				{
					JOptionPane.showMessageDialog(btnNewButton, "Không Đủ Số Lượng Sản Phẩm Để Xuất");
					
				}
				else
				{
					QuanLySanPhamController.UpdateSlgDT(dt.getMaDT(), dt.getSlg());
					ChiTietXuatKhoController.Add(ctpx);
					
					double tt = dt.getGia() * ctpx.getSlg();
					px.setThanhTien(px.getThanhTien() + tt);
					QuanLyXuatKhoController.UpdateXK(px);
					
					try {
						ChiTietXuatKhoController.tablechitietphieuxuat(model, px.getSoPhieu());
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton_2.setBackground(new Color(214, 214, 214));
		btnNewButton_2.setBounds(408, 227, 89, 23);
		panel.add(btnNewButton_2);

		JButton btnTrV = new JButton("Trở về");
		btnTrV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home;
				try {
					home = new Home(tk);
					home.show();
					dispose();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnTrV.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnTrV.setBackground(new Color(214, 214, 214));
		btnTrV.setBounds(643, 228, 89, 23);
		panel.add(btnTrV);

	}
}
