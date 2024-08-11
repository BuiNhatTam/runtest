package gui;

import java.awt.EventQueue;
import dao.NhanVien_dao;
import entity.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JCheckBox;

public class FrmQuanLyNhanVien extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3739840895945097164L;
	private JPanel contentPane;
	private JTextField tfMaNV;
	private JTextField tfTenNV;
	private JTextField tfSDT;
	private JTextField tfDC;
	private JTextField tfHSL;
	private JScrollPane scrollPaneTable;
	private JTable table;
	private JPanel panel;
	private JPanel pnlFuncTable;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaTrang;
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
	private DefaultTableModel model;
	private NhanVien_dao nhanvien_dao;
	private JDateChooser dateNgaySinh;
	private JDateChooser dateNgayVaoLam;
	private JMenu mnDatHang;
	private JMenuItem mntmDatHang;
	private JMenuItem mntmDsHoaDon;
	private JCheckBox chkGioiTinh;
	private JMenuItem mntmTacGia;
	private JMenuItem mntmNXB;
	private static final SimpleDateFormat DATE_FORMAT_SQL = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmQuanLyNhanVien frame = new FrmQuanLyNhanVien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public FrmQuanLyNhanVien() throws Exception {
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1936, 1096);
	//	setResizable(True);

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
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(675, 95, 881, 230);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblMaNV = new JLabel("Mã nhân viên");
		lblMaNV.setForeground(Color.BLACK);
		lblMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaNV.setBounds(40, 10, 140, 35);
		panel.add(lblMaNV);

		JLabel lblTenNV = new JLabel("Tên nhân viên");
		lblTenNV.setForeground(Color.BLACK);
		lblTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenNV.setBounds(40, 55, 140, 35);
		panel.add(lblTenNV);

		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setForeground(Color.BLACK);
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSDT.setBounds(40, 100, 140, 35);
		panel.add(lblSDT);

		JLabel lblGT = new JLabel("Giới tính");
		lblGT.setForeground(Color.BLACK);
		lblGT.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGT.setBounds(40, 145, 140, 35);
		panel.add(lblGT);

		JLabel lblNVL = new JLabel("Ngày vào làm");
		lblNVL.setForeground(Color.BLACK);
		lblNVL.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNVL.setBounds(450, 145, 140, 35);
		panel.add(lblNVL);

		JLabel lblHSL = new JLabel("Hệ số lương");
		lblHSL.setForeground(Color.BLACK);
		lblHSL.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblHSL.setBounds(450, 100, 140, 35);
		panel.add(lblHSL);

		JLabel lblNgaySinh = new JLabel("Ngày sinh");
		lblNgaySinh.setForeground(Color.BLACK);
		lblNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgaySinh.setBounds(450, 55, 140, 35);
		panel.add(lblNgaySinh);

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setForeground(Color.BLACK);
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDiaChi.setBounds(450, 10, 140, 35);
		panel.add(lblDiaChi);

		tfMaNV = new JTextField();
		tfMaNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfMaNV.setBounds(170, 17, 250, 30);
		panel.add(tfMaNV);
		tfMaNV.setColumns(10);

		tfTenNV = new JTextField();
		tfTenNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfTenNV.setColumns(10);
		tfTenNV.setBounds(170, 60, 250, 30);
		panel.add(tfTenNV);

		tfSDT = new JTextField();
		tfSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfSDT.setColumns(10);
		tfSDT.setBounds(170, 105, 250, 30);
		panel.add(tfSDT);

		tfDC = new JTextField();
		tfDC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfDC.setColumns(10);
		tfDC.setBounds(575, 15, 250, 30);
		panel.add(tfDC);

		tfHSL = new JTextField();
		tfHSL.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfHSL.setColumns(10);
		tfHSL.setBounds(575, 105, 250, 30);
		panel.add(tfHSL);

		dateNgaySinh = new JDateChooser();
		dateNgaySinh.setDateFormatString("dd/MM/yyyy");
		dateNgaySinh.setBounds(575, 60, 250, 30);
		panel.add(dateNgaySinh);

		dateNgayVaoLam = new JDateChooser();
		dateNgayVaoLam.setDateFormatString("dd/MM/yyyy");
		dateNgayVaoLam.setBounds(575, 150, 250, 30);
		panel.add(dateNgayVaoLam);
		
	    chkGioiTinh = new JCheckBox("Nam");
	    chkGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		chkGioiTinh.setBounds(170, 153, 93, 21);
		panel.add(chkGioiTinh);

		pnlFuncTable = new JPanel();
		pnlFuncTable.setBounds(370, 65, 307, 256);
		contentPane.add(pnlFuncTable);
		pnlFuncTable.setBackground(new Color(255, 255, 255));
		pnlFuncTable.setLayout(null);

		btnThem = new JButton("THÊM");
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setBackground(Color.LIGHT_GRAY);
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnThem.setBounds(64, 11, 180, 29);
		pnlFuncTable.add(btnThem);

		btnXoa = new JButton("XÓA");
		btnXoa.setForeground(new Color(255, 255, 255));
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXoa.setBackground(Color.LIGHT_GRAY);
		btnXoa.setBounds(64, 60, 180, 29);
		pnlFuncTable.add(btnXoa);

		btnSua = new JButton("SỬA");
		btnSua.setBounds(65, 160, 180, 29);
		pnlFuncTable.add(btnSua);
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnSua.setBackground(Color.LIGHT_GRAY);

		btnXoaTrang = new JButton("XÓA TRẮNG");
		btnXoaTrang.setBounds(65, 110, 180, 29);
		pnlFuncTable.add(btnXoaTrang);
		btnXoaTrang.setForeground(new Color(255, 255, 255));
		btnXoaTrang.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnXoaTrang.setBackground(Color.LIGHT_GRAY);

		JButton btnXuat = new JButton("Xuất DS");
		btnXuat.setForeground(new Color(255, 255, 255));
		btnXuat.setBackground(Color.LIGHT_GRAY);
		btnXuat.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnXuat.setBounds(65, 210, 180, 29);
		pnlFuncTable.add(btnXuat);
		btnXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnXuat();
			}
		});
		pnlTitle = new JPanel();
		pnlTitle.setBackground(Color.WHITE);
		pnlTitle.setBounds(370, 0, 1186, 70);
		contentPane.add(pnlTitle);
		pnlTitle.setLayout(null);

		JLabel lblNewLabel = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblNewLabel.setBounds(447, 0, 395, 70);
		pnlTitle.add(lblNewLabel);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));

		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		model = new DefaultTableModel();

		Vector<String> header = new Vector<String>();
		header.add("Mã nhân viên");
		header.add("Tên nhân viên");
		header.add("Số điện thoại");
		header.add("Giới tính");
		header.add("Địa chỉ");
		header.add("Ngày Sinh");
		header.add("Hệ số lương");
		header.add("Ngày vào làm");
		model = new DefaultTableModel(header, 0);
		getDsNhanVien();

		scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(370, 324, 1186, 315);
		getContentPane().add(scrollPaneTable);
		scrollPaneTable.setViewportView(table);

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
		mntmTrangChu.addActionListener(this);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				tfMaNV.setText(table.getModel().getValueAt(i, 0).toString());
				tfTenNV.setText(table.getModel().getValueAt(i, 1).toString());
				tfSDT.setText(table.getModel().getValueAt(i, 2).toString());
				chkGioiTinh.setSelected(table.getModel().getValueAt(i, 3).toString() == "Nam" ? true : false );
				tfDC.setText(table.getModel().getValueAt(i, 4).toString());
				tfMaNV.setEditable(false);
				try {
					dateNgaySinh.setDate(DATE_FORMAT_SQL.parse(table.getModel().getValueAt(i, 5).toString()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tfHSL.setText(table.getModel().getValueAt(i, 6).toString());
				try {
					dateNgayVaoLam.setDate(DATE_FORMAT_SQL.parse(table.getModel().getValueAt(i, 7).toString()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnThem.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!tfMaNV.getText().trim().matches("^NV\\d{4}$")) {
				  JOptionPane.showMessageDialog(null, "Mã nhân viên không được để trống và phải có độ dài 5 ký tự!");
				  return;
				}
				if (tfTenNV.getText().trim().matches("\\b([A-Z][a-z]*)\\b")) {
					  JOptionPane.showMessageDialog(null, "Tên nhân viên không được để trống bắt đầu bằng chữ viết hoa!");
					  return;
					}
				String sdtRegex = "^\\d{10,11}$";
				if (!tfSDT.getText().trim().matches(sdtRegex)) {
				  JOptionPane.showMessageDialog(null, "SĐT phải là 10-11 số!");
				  return;
				}
				String heSoLuongRegex = "^\\d+\\.?\\d*$";
				if (!tfHSL.getText().trim().matches(heSoLuongRegex)) {
				  JOptionPane.showMessageDialog(null, "Hệ số lương phải là số dương!");
				  return; 
				}
				try {
					String maNV = tfMaNV.getText();
					String tenNV = tfTenNV.getText();
					String soDienThoai = tfSDT.getText();
					Boolean gioiTinh = chkGioiTinh.isSelected() ;
					String diaChi = tfDC.getText();
					String heSoLuong = tfHSL.getText();
					
					NhanVien nhanVien = new NhanVien(maNV, tenNV, soDienThoai, diaChi, Float.parseFloat(heSoLuong),
							gioiTinh, Date.valueOf(DATE_FORMAT_SQL.format(dateNgaySinh.getDate())),
							Date.valueOf(DATE_FORMAT_SQL.format(dateNgayVaoLam.getDate())));
					nhanvien_dao = new NhanVien_dao();
					nhanvien_dao.themNV(nhanVien);
					getDsNhanVien();
					JOptionPane.showMessageDialog(null, "Thành Công: Thêm nhân viên thành công!!");

					// clean TEXTFIELD
					clearTextField();
				} catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Lỗi khi thêm nhân viên");
				}
			}
		});
		btnXoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					int i = JOptionPane.showConfirmDialog(null, "Xác Nhận Xóa Nhân Viên", "",
							JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {
						String maNV = model.getValueAt(selectedRow, 0).toString();
						model.removeRow(selectedRow);
						if (maNV != "") {
							nhanvien_dao = new NhanVien_dao();
							try {
								nhanvien_dao.xoaNhanVienTheoMa(maNV);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								getDsNhanVien();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "Xóa Thành Công");
							// clean TEXTFIELD
							clearTextField();
						}
					}
				}
			}
		});
		btnSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String maNV = tfMaNV.getText();
				String tenNV = tfTenNV.getText();
				String soDienThoai = tfSDT.getText();
				Boolean gioiTinh = chkGioiTinh.isSelected() ;
				String diaChi = tfDC.getText();
				String heSoLuong = tfHSL.getText();

				NhanVien nhanVien = new NhanVien(maNV, tenNV, soDienThoai, diaChi, Float.parseFloat(heSoLuong),
						gioiTinh, Date.valueOf(DATE_FORMAT_SQL.format(dateNgaySinh.getDate())),
						Date.valueOf(DATE_FORMAT_SQL.format(dateNgayVaoLam.getDate())));
				
				try {
					nhanvien_dao = new NhanVien_dao();
					nhanvien_dao.update(nhanVien);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
					
					getDsNhanVien();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Thành Công: Sửa Nhân Viên Thành Công");

				// clean TEXTFIELD
				clearTextField();
			}
		});
		btnXoaTrang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearTextField();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			FrmQuanLySach quanlySach = new FrmQuanLySach();
			FrmTheLoai danhMucSach = new FrmTheLoai();
			FrmQuanLyNhanVien quanlyNhanVien = new FrmQuanLyNhanVien();
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
			FrmMainMenu mn= new FrmMainMenu();
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
			}else if (o.equals(mntmTrangChu)){
				this.setVisible(false);
				mn.setVisible(true);
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void outModel(DefaultTableModel model, ArrayList<NhanVien> ListNhanVien) {
		Vector row;
		model.setRowCount(0);
		for (NhanVien nhanVien : ListNhanVien) {
			row = new Vector();
			row.add(nhanVien.getMaNV());
			row.add(nhanVien.getTenNV());
			row.add(nhanVien.getSoDT());
			row.add(nhanVien.isGioiTinh()?"Nam":"Nữ");
			row.add(nhanVien.getDiaChi());
			row.add(nhanVien.getNgaySinh());
			row.add(nhanVien.getHeSoLuong());
			row.add(nhanVien.getNgayVaoLam());

			model.addRow(row);
			
		}
		table.setModel(model);
	}

	public void getDsNhanVien() throws Exception {
		nhanvien_dao = new NhanVien_dao();
		ArrayList<NhanVien> nhanVien = nhanvien_dao.layDsNhanVien();
		model.setRowCount(0);
		outModel(model, nhanVien);
		
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
		tfMaNV.setText("");
		tfTenNV.setText("");
		tfSDT.setText("");
		tfDC.setText("");
		tfHSL.setText("");
		dateNgaySinh.setDate(null);
		dateNgayVaoLam.setDate(null);
		tfMaNV.setEditable(true);
	}
}
