package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.NhanVien_dao;
import entity.NhanVien;

import java.awt.SystemColor;

public class FrmTimKiemNhanVien extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3814705726572191288L;
	private JPanel contentPane;
	private static JTextField tfMaNV;
	private static JTextField tfSDT;
	private static JTextField tfTenNV;
	private JScrollPane scrollPaneTable;
	private JTable table;
	private JPanel panel;
	private JComponent pnlTitle;
	private JPanel pnlFuncTable;
	private JButton btnTimKiem;
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
	private DefaultTableModel model;
	private JMenuItem mntmDsHoaDon;
	private JLabel lblTnNhnVin;
	private JMenuItem mntmNXB;
	private JMenuItem mntmTacGia;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmTimKiemNhanVien frame = new FrmTimKiemNhanVien();
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
	public FrmTimKiemNhanVien() {
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(346, 116, 713, 240);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMaNV = new JLabel("Mã nhân viên");
		lblMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaNV.setBounds(40, 35, 140, 35);
		panel.add(lblMaNV);
		
		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSDT.setBounds(40, 100, 140, 35);
		panel.add(lblSDT);
		
	
		
		tfMaNV = new JTextField();
		tfMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfMaNV.setBounds(170, 37, 500, 30);
		panel.add(tfMaNV);
		tfMaNV.setColumns(10);
		
		tfSDT = new JTextField();
		tfSDT.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfSDT.setColumns(10);
		tfSDT.setBounds(170, 105, 500, 30);
		panel.add(tfSDT);
		
		tfTenNV = new JTextField();
		tfTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfTenNV.setColumns(10);
		tfTenNV.setBounds(170, 170, 500, 30);
		panel.add(tfTenNV);
		
		lblTnNhnVin = new JLabel("Tên Nhân Viên ");
		lblTnNhnVin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTnNhnVin.setBounds(40, 170, 140, 35);
		panel.add(lblTnNhnVin);
		
		pnlTitle = new JPanel();
		pnlTitle.setBackground(Color.WHITE);
		pnlTitle.setBounds(346, 0, 1186, 70);
		contentPane.add(pnlTitle);
		pnlTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("TÌM KIẾM NHÂN VIÊN");
		lblTitle.setBounds(414, 0, 310, 70);
		pnlTitle.add(lblTitle);
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setBackground(Color.BLACK);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 28));
		
		scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(346, 367, 1186, 298);
		getContentPane().add(scrollPaneTable);
		
		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Địa Chỉ", "Hệ Số Lương", "Giới Tính", "Ngày Sinh", "Ngày vào làm"
			}
		));
		scrollPaneTable.setViewportView(table);
		
		pnlFuncTable = new JPanel();
		pnlFuncTable.setLayout(null);
		pnlFuncTable.setBackground(new Color(255, 255, 255));
		pnlFuncTable.setBounds(1059, 116, 473, 240);
		contentPane.add(pnlFuncTable);
		
		btnTimKiem = new JButton("TÌM KIẾM");
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnTimKiem.setBackground(Color.LIGHT_GRAY);
		btnTimKiem.setBounds(40, 90, 180, 45);
		pnlFuncTable.add(btnTimKiem);
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					searchNhanVien();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				xoaRong();
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
	private void searchNhanVien() throws SQLException {
		String ma = tfMaNV.getText();
		if (ma.equals("")) {
			ma = null;
		}

		String sdt = tfSDT.getText();
		if (sdt.equals("")) {
			sdt = null;
		}
		String tenString = tfTenNV.getText();
		if (tenString.length() == 0) {
			tenString = null;
		}

		NhanVien_dao nv = new NhanVien_dao();
		ArrayList<NhanVien> ds = nv.searchNhanVienTheoProperties(ma,sdt,tenString);
		model = new DefaultTableModel();
		DefaultTableModel old = (DefaultTableModel) table.getModel();
		int n = old.getColumnCount();
		for (int i = 0; i < n; i++) {
			model.addColumn(old.getColumnName(i));
		}
		n = ds.size();
		for (int i = 0; i < n; i++) {
			NhanVien nv1 = ds.get(i);
			model.addRow(new Object[] { nv1.getMaNV(),nv1.getTenNV(),nv1.getSoDT(),nv1.getDiaChi(),nv1.getHeSoLuong(),nv1.isGioiTinh()?"Nam" : "Nữ",nv1.getNgaySinh(),nv1.getNgayVaoLam()});
		}
		table.setModel(model);
	}
	
	public static void xoaRong() {
		tfMaNV.setText("");
		tfSDT.setText("");
		tfTenNV.setText("");

	}

}
