package gui;

import java.awt.EventQueue;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import com.toedter.calendar.JDateChooser;
import dao.ThongKe_dao;
import entity.ThongKe;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class FrmThongKe extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2052997222795488297L;
	protected static Component textArea;
	private JPanel contentPane;
	private JScrollPane scrollPaneTable;
	private JTable table;
	private JPanel panel;
	private JButton btnThongKe;
	private JPanel pnlTitle;
	private JMenuBar menuBar;
	private JMenu mnTrangChu;
	private JMenuItem mntmTrangChu;
	private JMenu mnSach;
	private JMenuItem mntmQuanLySach;
	private JMenuItem mntmTimKiemSach;
	private JMenu mnKhachHang;
	private JMenuItem mntmQuanLyKhachHang;
	private JMenuItem mntmTimKiemKhachHang;
	private JMenu mnNhanVien;
	private JMenuItem mntmQuanLyNhanVien;
	private JMenuItem mntmTimKiemNhanVien;
	private JMenu mnThongKe;
	private JMenuItem mntmThongKe;
	private JMenu mnLapHoaDon;
	private JMenuItem mntmLapHoaDon;
	private JMenuItem mntmDanhMucSach;
	private JMenu mnDatHang;
	private JMenuItem mntmDatHang;
	private JDateChooser dateStart;
	private JDateChooser dateEnd;
	private static final SimpleDateFormat DATE_FORMAT_SQL = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField tfTongDoanhThu;
	private JLabel lblTngSLng;
	private JTextField tfTongSL;
	private JLabel lblchonnam;
	private JComboBox<String> comboBox;
	private JButton btnThngKQuy_1;
	private LocalDateTime localDate;
	private JButton btnThngKQuy_4;
	private JButton btnThngKQuy_2;
	private JButton btnThngKQuy_3;
	private JButton btnThongKeSPBanChay;
	private JMenuItem mntmDsHoaDon;
	JLabel lblSPBan;
	private JMenuItem mntmTacGia;
	private JMenuItem mntmNXB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmThongKe frame = new FrmThongKe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmThongKe() {
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1935, 1098);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnTrangChu = new JMenu("Trang chủ");
		mnTrangChu.setIcon(new ImageIcon(FrmMainMenu.class.getResource("/img/Actions-system-shutdown-icon.png")));
		mnTrangChu.setForeground(Color.BLACK);
		mnTrangChu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		menuBar.add(mnTrangChu);
		
		mntmTrangChu = new JMenuItem("Về trang chủ");
		mnTrangChu.add(mntmTrangChu);
		
		mnSach = new JMenu("Sách");
		mnSach.setForeground(Color.BLACK);
		mnSach.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mnSach.setIcon(new ImageIcon(FrmMainMenu.class.getResource("/img/Book-icon.png")));
		menuBar.add(mnSach);
		
		mntmQuanLySach = new JMenuItem("Quản Lý Sách");
		mnSach.add(mntmQuanLySach);
		
		mntmTimKiemSach = new JMenuItem("Tìm Kiếm Sách");
		mnSach.add(mntmTimKiemSach);
		
		mntmDanhMucSach = new JMenuItem("Quản Lý Thể Loại");
		mnSach.add(mntmDanhMucSach);
		
		mntmTacGia = new JMenuItem("Quản Lý Tác Giả");
		mnSach.add(mntmTacGia);
		
		mntmNXB = new JMenuItem("Quản Lý Nhà Xuất Bản");
		mnSach.add(mntmNXB);
		
		mnKhachHang = new JMenu("Khách hàng");
		mnKhachHang.setForeground(Color.BLACK);
		mnKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mnKhachHang.setIcon(new ImageIcon(FrmMainMenu.class.getResource("/img/People-icon.png")));
		menuBar.add(mnKhachHang);
		
		mntmQuanLyKhachHang = new JMenuItem("Quản lý khách hàng");
		mnKhachHang.add(mntmQuanLyKhachHang);
		
		mntmTimKiemKhachHang = new JMenuItem("Tìm kiếm khách hàng");
		mnKhachHang.add(mntmTimKiemKhachHang);
		
		mntmDatHang = new JMenuItem("Đặt hàng");
		mnKhachHang.add(mntmDatHang);
		mntmDatHang.addActionListener(this);
		
		mnNhanVien = new JMenu("Nhân viên");
		mnNhanVien.setForeground(Color.BLACK);
		mnNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mnNhanVien.setIcon(new ImageIcon(FrmMainMenu.class.getResource("/img/folder-blue-image-people-icon.png")));
		menuBar.add(mnNhanVien);
		
		mntmQuanLyNhanVien = new JMenuItem("Quản lý nhân viên");
		mnNhanVien.add(mntmQuanLyNhanVien);
		
		mntmTimKiemNhanVien = new JMenuItem("TÌm kiếm nhân viên");
		mnNhanVien.add(mntmTimKiemNhanVien);
		
		mnLapHoaDon = new JMenu("Hóa đơn");
		mnLapHoaDon.setForeground(Color.BLACK);
		mnLapHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mnLapHoaDon.setIcon(new ImageIcon(FrmMainMenu.class.getResource("/img/Places-folder-blue-icon (1).png")));
		menuBar.add(mnLapHoaDon);
		
		mntmLapHoaDon = new JMenuItem("Lập hóa đơn");
		mnLapHoaDon.add(mntmLapHoaDon);
		
		mntmDsHoaDon = new JMenuItem("Tìm hóa đơn");
		mnLapHoaDon.add(mntmDsHoaDon);
		
		mnThongKe = new JMenu("Bảng thống kê");
		mnThongKe.setForeground(Color.BLACK);
		mnThongKe.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mnThongKe.setIcon(new ImageIcon(FrmMainMenu.class.getResource("/img/Places-folder-blue-icon.png")));
		menuBar.add(mnThongKe);
		
		mntmThongKe = new JMenuItem("Thống kê");
		mnThongKe.add(mntmThongKe);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblTitle = new JLabel("THỐNG KÊ");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblTitle.setBackground(Color.WHITE);
		lblTitle.setBounds(493, 0, 160, 60);
		contentPane.add(lblTitle);
		
		scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(369, 343, 1186, 220);
		getContentPane().add(scrollPaneTable);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã sách", "Tên sách", "Đơn giá", "Số lượng bán", "Số lượng tồn", "Thành tiền"
			}
		));
		scrollPaneTable.setViewportView(table);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(369, 71, 1186, 272);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Từ ngày");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(58, 39, 85, 40);
		panel.add(lblNewLabel);
		
		JLabel lblnNgy = new JLabel("Đến ngày");
		lblnNgy.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblnNgy.setBounds(508, 39, 85, 40);
		panel.add(lblnNgy);
		
		
		btnThongKe = new JButton("THỐNG KÊ");
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThongKe.setBackground(Color.LIGHT_GRAY);
		btnThongKe.setBounds(408, 166, 234, 29);
		panel.add(btnThongKe);
		
		dateStart = new JDateChooser();
		dateStart.setBackground(new Color(255, 255, 255));
		dateStart.setDateFormatString("dd-MM-yyyy");
		dateStart.setBounds(162, 44, 280, 35);
		panel.add(dateStart);
		
		dateEnd = new JDateChooser();
		dateEnd.setDateFormatString("dd-MM-yyyy");
		dateEnd.setBackground(Color.WHITE);
		dateEnd.setBounds(607, 44, 280, 35);
		panel.add(dateEnd);
		
		btnThngKQuy_1 = new JButton("THỐNG KÊ QUÝ I");
		btnThngKQuy_1.setForeground(Color.WHITE);
		btnThngKQuy_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThngKQuy_1.setBackground(Color.LIGHT_GRAY);
		btnThngKQuy_1.setBounds(686, 166, 234, 29);
		panel.add(btnThngKQuy_1);
		
		
		lblchonnam = new JLabel("Chọn Năm");
		lblchonnam.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblchonnam.setBounds(58, 97, 85, 40);
		panel.add(lblchonnam);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(162, 103, 85, 34);
		localDate = LocalDateTime.now();
		int year = localDate.getYear();
		for (int i =  year; i >= 2016; i--) {
			comboBox.addItem(String.valueOf(i));
		}
		
		panel.add(comboBox);
		
		btnThngKQuy_2 = new JButton("THỐNG KÊ QUÝ II");
		btnThngKQuy_2.setForeground(Color.WHITE);
		btnThngKQuy_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThngKQuy_2.setBackground(Color.LIGHT_GRAY);
		btnThngKQuy_2.setBounds(686, 206, 234, 29);
		panel.add(btnThngKQuy_2);
		
		btnThngKQuy_3 = new JButton("THỐNG KÊ QUÝ III");
		btnThngKQuy_3.setForeground(Color.WHITE);
		btnThngKQuy_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThngKQuy_3.setBackground(Color.LIGHT_GRAY);
		btnThngKQuy_3.setBounds(942, 167, 234, 29);
		panel.add(btnThngKQuy_3);
	
		
		btnThngKQuy_4 = new JButton("THỐNG KÊ QUÝ IV");
		btnThngKQuy_4.setForeground(Color.WHITE);
		btnThngKQuy_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThngKQuy_4.setBackground(Color.LIGHT_GRAY);
		btnThngKQuy_4.setBounds(942, 206, 234, 29);
		panel.add(btnThngKQuy_4);
		
		btnThongKeSPBanChay = new JButton("SẢN PHẨM BÁN CHẠY");
		btnThongKeSPBanChay.setForeground(Color.WHITE);
		btnThongKeSPBanChay.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThongKeSPBanChay.setBackground(Color.LIGHT_GRAY);
		btnThongKeSPBanChay.setBounds(408, 206, 234, 29);
		panel.add(btnThongKeSPBanChay);
		panel.add(Box.createVerticalStrut(10));
		
		JLabel lblNewLabel_1 = new JLabel("Kết quả thống kê");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 231, 186, 30);
		panel.add(lblNewLabel_1);
		
		
		pnlTitle = new JPanel();
		pnlTitle.setBounds(369, 0, 1186, 70);
		pnlTitle.setBackground(Color.WHITE);
		contentPane.add(pnlTitle);
		
		JLabel lblTongDoanhThu = new JLabel("Tổng Doanh Thu");
		lblTongDoanhThu.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblTongDoanhThu.setBounds(841, 588, 210, 45);
		contentPane.add(lblTongDoanhThu);
		
		tfTongDoanhThu = new JTextField();
		tfTongDoanhThu.setEditable(false);
		tfTongDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfTongDoanhThu.setBounds(1107, 588, 210, 45);
		contentPane.add(tfTongDoanhThu);
		tfTongDoanhThu.setColumns(10);
		
		lblTngSLng = new JLabel("Tổng Số Lượng Bán");
		lblTngSLng.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblTngSLng.setBounds(369, 588, 251, 45);
		contentPane.add(lblTngSLng);
		
		tfTongSL = new JTextField();
		tfTongSL.setEditable(false);
		tfTongSL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfTongSL.setColumns(10);
		tfTongSL.setBounds(661, 588, 170, 45);
		contentPane.add(tfTongSL);
		
		JButton btnXuat = new JButton("XUẤT BÁO CÁO");
		btnXuat.setBackground(Color.LIGHT_GRAY);
		btnXuat.setForeground(Color.WHITE);
		btnXuat.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnXuat.setBounds(1385, 590, 170, 45);
		contentPane.add(btnXuat);
		btnXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnXuat();
			}
		});

		
		mntmQuanLySach.addActionListener(this);
		mntmTimKiemSach.addActionListener(this);
		mntmDanhMucSach.addActionListener(this);
		mntmQuanLyKhachHang.addActionListener(this);
		mntmTimKiemKhachHang.addActionListener(this);
		mntmQuanLyNhanVien.addActionListener(this);
		mntmTimKiemNhanVien.addActionListener(this);
		mntmLapHoaDon.addActionListener(this);
		mntmThongKe.addActionListener(this);
		mntmLapHoaDon.addActionListener(this);
		mntmDsHoaDon.addActionListener(this);
		mntmTacGia.addActionListener(this);
		mntmNXB.addActionListener(this);

		String pattern = "#,###,###.##";
		DecimalFormat formatTienTe = new DecimalFormat(pattern);
		
		btnThongKeSPBanChay.addActionListener(new ActionListener() {
		    private ThongKe_dao tk_dao;
			private double soluong;

			public void actionPerformed(ActionEvent e) {
				try {
					tk_dao = new ThongKe_dao();
					ArrayList<ThongKe> data = tk_dao.getAllSPBanChay(Date.valueOf(DATE_FORMAT_SQL.format(dateStart.getDate())), 
							Date.valueOf(DATE_FORMAT_SQL.format(dateEnd.getDate())));
					soluong = tk_dao.SoLuongSPBanChay(Date.valueOf(DATE_FORMAT_SQL.format(dateStart.getDate())), 
							Date.valueOf(DATE_FORMAT_SQL.format(dateEnd.getDate())));
					DefaultTableModel model = new DefaultTableModel();
					Double tongDoanhThu = 0.0;
					model.addColumn("Mã sách");
					model.addColumn("Tên sách");
					model.addColumn("Đơn giá");
					model.addColumn("Số lượng bán");
					model.addColumn("Số lượng tồn");
					model.addColumn("Thành tiền");
					for (ThongKe cthd : data) {
						model.addRow(new Object[] {
								cthd.getMaSach(),
								cthd.getTenSach(),
								cthd.getDonGia(),
								cthd.getSoLuongBan(),
								cthd.getSoLuongTon(),
								cthd.getTongTien()
						});
						tongDoanhThu += cthd.getDonGia() * cthd.getSoLuongBan();
						
					}
					tfTongDoanhThu.setText(formatTienTe.format(tongDoanhThu).toString());
					tfTongSL.setText(formatTienTe.format(soluong).toString());
					table.setModel(model);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn khoảng thời gian cần thống kê");
				}
		    }
		});
		btnThongKe.addActionListener(new ActionListener() {
		    private ThongKe_dao tk_dao;
			private double soluong;

			public void actionPerformed(ActionEvent e) {
		    	tk_dao = new ThongKe_dao();
		        ArrayList<ThongKe> data = tk_dao.getAll(Date.valueOf(DATE_FORMAT_SQL.format(dateStart.getDate())), 
		        		Date.valueOf(DATE_FORMAT_SQL.format(dateEnd.getDate())));
		        soluong = tk_dao.SoLuongSPBanTheoNgay(Date.valueOf(DATE_FORMAT_SQL.format(dateStart.getDate())), 
		        		Date.valueOf(DATE_FORMAT_SQL.format(dateEnd.getDate())));
		        DefaultTableModel model = new DefaultTableModel();
		        Double tongDoanhThu = 0.0;
		        model.addColumn("Mã sách");
		        model.addColumn("Tên sách");
		        model.addColumn("Đơn giá");
		        model.addColumn("Số lượng bán");
		        model.addColumn("Số lượng tồn");
		        model.addColumn("Thành tiền");
		        for (ThongKe cthd : data) {
		            model.addRow(new Object[] {
		                cthd.getMaSach(),
		                cthd.getTenSach(),
		                cthd.getDonGia(),
		                cthd.getSoLuongBan(),
		                cthd.getSoLuongTon(),
		                cthd.getTongTien()
		            });
		            tongDoanhThu += cthd.getDonGia() * cthd.getSoLuongBan();
		            
		        }
		        tfTongDoanhThu.setText(formatTienTe.format(tongDoanhThu).toString());
		        tfTongSL.setText(formatTienTe.format(soluong).toString());
		        table.setModel(model);
		    }
		});
		btnThngKQuy_1.addActionListener(new ActionListener() {
		    private ThongKe_dao tk_dao;
			private double soluong;
			
			public void actionPerformed(ActionEvent e) {
				clearTextField();
		    	tk_dao = new ThongKe_dao();
		    	String nam = (String) comboBox.getSelectedItem();
		        ArrayList<ThongKe> data = tk_dao.getAllTheoQuy(nam,"01","02","03");
		        soluong = tk_dao.SoLuongSPTheoQuy(nam,"01","02","03");
		        DefaultTableModel model = new DefaultTableModel();
		        Double tongDoanhThu = 0.0;
		        model.addColumn("Mã sách");
		        model.addColumn("Tên sách");
		        model.addColumn("Đơn giá");
		        model.addColumn("Số lượng bán");
		        model.addColumn("Số lượng tồn");
		        model.addColumn("Thành tiền");
		        for (ThongKe cthd : data) {
		            model.addRow(new Object[] {
		                cthd.getMaSach(),
		                cthd.getTenSach(),
		                cthd.getDonGia(),
		                cthd.getSoLuongBan(),
		                cthd.getSoLuongTon(),
		                cthd.getTongTien()
		            });
		            tongDoanhThu += cthd.getDonGia() * cthd.getSoLuongBan();
		            
		        }
		        tfTongDoanhThu.setText(formatTienTe.format(tongDoanhThu).toString());
		        tfTongSL.setText(formatTienTe.format(soluong).toString());
		        table.setModel(model);
		    }
		});
		btnThngKQuy_2.addActionListener(new ActionListener() {
		    private ThongKe_dao tk_dao;
			private double soluong;

			public void actionPerformed(ActionEvent e) {
				clearTextField();
		    	tk_dao = new ThongKe_dao();
		    	String nam = (String) comboBox.getSelectedItem();
		        ArrayList<ThongKe> data = tk_dao.getAllTheoQuy(nam,"04","05","06");
		        soluong = tk_dao.SoLuongSPTheoQuy(nam,"04","05","06");
		        DefaultTableModel model = new DefaultTableModel();
		        Double tongDoanhThu = 0.0;
		        model.addColumn("Mã sách");
		        model.addColumn("Tên sách");
		        model.addColumn("Đơn giá");
		        model.addColumn("Số lượng bán");
		        model.addColumn("Số lượng tồn");
		        model.addColumn("Thành tiền");
		        for (ThongKe cthd : data) {
		            model.addRow(new Object[] {
		                cthd.getMaSach(),
		                cthd.getTenSach(),
		                cthd.getDonGia(),
		                cthd.getSoLuongBan(),
		                cthd.getSoLuongTon(),
		                cthd.getTongTien()
		            });
		            tongDoanhThu += cthd.getDonGia() * cthd.getSoLuongBan();
		            
		        }
		        tfTongDoanhThu.setText(formatTienTe.format(tongDoanhThu).toString());
		        tfTongSL.setText(formatTienTe.format(soluong).toString());
		        table.setModel(model);
		        
		    }
		});
		btnThngKQuy_3.addActionListener(new ActionListener() {
		    private ThongKe_dao tk_dao;
			private double soluong;

			public void actionPerformed(ActionEvent e) {
				clearTextField();
		    	tk_dao = new ThongKe_dao();
		    	String nam = (String) comboBox.getSelectedItem();
		        ArrayList<ThongKe> data = tk_dao.getAllTheoQuy(nam,"07","08","09");
		        soluong = tk_dao.SoLuongSPTheoQuy(nam,"07","08","09");
		        DefaultTableModel model = new DefaultTableModel();
		        Double tongDoanhThu = 0.0;
		        model.addColumn("Mã sách");
		        model.addColumn("Tên sách");
		        model.addColumn("Đơn giá");
		        model.addColumn("Số lượng bán");
		        model.addColumn("Số lượng tồn");
		        model.addColumn("Thành tiền");
		        for (ThongKe cthd : data) {
		            model.addRow(new Object[] {
		                cthd.getMaSach(),
		                cthd.getTenSach(),
		                cthd.getDonGia(),
		                cthd.getSoLuongBan(),
		                cthd.getSoLuongTon(),
		                cthd.getTongTien()
		            });
		            tongDoanhThu += cthd.getDonGia() * cthd.getSoLuongBan();
		            
		        }
		        tfTongDoanhThu.setText(formatTienTe.format(tongDoanhThu).toString());
		        tfTongSL.setText(formatTienTe.format(soluong).toString());
		        table.setModel(model);
		       
		    }
		});
		btnThngKQuy_4.addActionListener(new ActionListener() {
			
		    private ThongKe_dao tk_dao;
			private double soluong;
			public void actionPerformed(ActionEvent e) {
				clearTextField();
		    	tk_dao = new ThongKe_dao();
		    	String nam = (String) comboBox.getSelectedItem();
		        ArrayList<ThongKe> data = tk_dao.getAllTheoQuy(nam,"10","11","12");
		        soluong = tk_dao.SoLuongSPTheoQuy(nam,"10","11","12");
		        DefaultTableModel model = new DefaultTableModel();
		        Double tongDoanhThu = 0.0;
		        model.addColumn("Mã sách");
		        model.addColumn("Tên sách");
		        model.addColumn("Đơn giá");
		        model.addColumn("Số lượng bán");
		        model.addColumn("Số lượng tồn");
		        model.addColumn("Thành tiền");
		        for (ThongKe cthd : data) {
		            model.addRow(new Object[] {
		                cthd.getMaSach(),
		                cthd.getTenSach(),
		                cthd.getDonGia(),
		                cthd.getSoLuongBan(),
		                cthd.getSoLuongTon(),
		                cthd.getTongTien()
		            });
		            tongDoanhThu += cthd.getDonGia() * cthd.getSoLuongBan();
		            
		        }
		        tfTongDoanhThu.setText(formatTienTe.format(tongDoanhThu).toString());
		        tfTongSL.setText(formatTienTe.format(soluong).toString());
		        table.setModel(model);
		        
		    }
		});
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			FrmQuanLySach quanlySach = new FrmQuanLySach();
			FrmTheLoai danhMucSach = new FrmTheLoai();
			FrmQuanLyNhanVien quanlyNhanVien;
			quanlyNhanVien = new FrmQuanLyNhanVien();
			FrmQuanLyKhachHang quanlyKhachHang = new FrmQuanLyKhachHang();
			FrmThongKe thongKe = new FrmThongKe();
			FrmTimKiemSach timKiemSach = new FrmTimKiemSach();
			FrmTimKiemKhachHang timKiemKhachHang = new FrmTimKiemKhachHang();
			FrmTimKiemNhanVien timKiemNhanVien = new FrmTimKiemNhanVien();
			FrmLapHoaDon lapHoaDon = new FrmLapHoaDon();
			FrmDatHang datHang = new FrmDatHang();
			FrmTimKiemHoaDon dsHoaDon = new FrmTimKiemHoaDon();
			FrmTacGia tacgia = new FrmTacGia();
			FrmNXB nxb = new FrmNXB();
			Object o = e.getSource();
			if (o.equals(mntmQuanLySach)) {
				this.setVisible(false);
				quanlySach.setVisible(true);
			} else if (o.equals(mntmQuanLyNhanVien)) {
				this.setVisible(false);
				quanlyNhanVien.setVisible(true);
			} else if (o.equals(mntmQuanLyKhachHang)) {
				this.setVisible(false);
				quanlyKhachHang.setVisible(true);
			} else if (o.equals(mntmThongKe)) {
				this.setVisible(false);
				thongKe.setVisible(true);
			} else if (o.equals(mntmLapHoaDon)) {
				this.setVisible(false);
				lapHoaDon.setVisible(true);
			}else if (o.equals(mntmDsHoaDon)) {
				this.setVisible(false);
				dsHoaDon.setVisible(true);
			} else if (o.equals(mntmTimKiemSach)) {
				this.setVisible(false);
				timKiemSach.setVisible(true);
			} else if (o.equals(mntmTimKiemKhachHang)) {
				this.setVisible(false);
				timKiemKhachHang.setVisible(true);
			} else if (o.equals(mntmTimKiemNhanVien)) {
				this.setVisible(false);
				timKiemNhanVien.setVisible(true);
			} else if (o.equals(mntmDanhMucSach)) {
				this.setVisible(false);
				danhMucSach.setVisible(true);
			} else if (o.equals(mntmDatHang)) {
				this.setVisible(false);
				datHang.setVisible(true);
			}else if (o.equals(mntmTacGia)) {
				this.setVisible(false);
				tacgia.setVisible(true);
			}else if (o.equals(mntmNXB)) {
				this.setVisible(false);
				nxb.setVisible(true);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	private void btnXuat() {
		JFileChooser fc = new JFileChooser();
		int option = fc.showSaveDialog(this);
		if (option == JFileChooser.APPROVE_OPTION) {
			String filename = fc.getSelectedFile().getName();
			String path = fc.getSelectedFile().getParentFile().getPath();
			int len = filename.length();
			String ext = "";
			String file = "";

			if (len > 4) {
				ext = filename.substring(len - 4, len);
			}

			if (ext.equals(".xls")) {
				file = path + "\\" + filename;
			} else {
				file = path + "\\" + filename + ".xls";
			}
			try {
				toExcel(table, new File(file));
				if (JOptionPane.showConfirmDialog(this, "Mở file?", "Xuất file thành công!!!",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					File f = new File(file);
					try {
						Desktop.getDesktop().open(f);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, "Xuất file không thành công!");
			}
		}
	}

	public void toExcel(JTable table, File file) throws IOException {
		String sheetName = "Sheet1";// Tên sheet
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName);
		TableModel model = table.getModel();
		// Lấy tên cột
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < model.getColumnCount(); i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(model.getColumnName(i).toString());
		}
		for (int r = 1; r <= model.getRowCount(); r++) {
			row = sheet.createRow(r);
			for (int c = 0; c < model.getColumnCount(); c++) {
				HSSFCell cell = row.createCell(c);

				cell.setCellValue(model.getValueAt(r - 1, c).toString());
			}
		}

		FileOutputStream fileOut = new FileOutputStream(file);

		// ghi workbook từ Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		wb.close();
	}
	public void clearTextField() {
        dateStart.setDate(null);
        dateEnd.setDate(null);
	}
}
