package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import dao.Sach_dao;
import entity.Sach;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;

public class FrmTimKiemSach extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6997150976088310266L;
	private JPanel contentPane;
	private static JTextField tfTenSach;
	private static JTextField tfTenNXB;
	private static JTextField tfTenTG;
	private static JTextField tfMaSach;
	private JScrollPane scrollPaneTable;
	private JTable table;
	private JLabel lblNewLabel;
	private JPanel pnlTitle;
	private JPanel panel;
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
	private JMenuItem mntmNXB;
	private JMenuItem mntmTacGia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmTimKiemSach frame = new FrmTimKiemSach();
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
	public FrmTimKiemSach() {
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
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pnlTitle = new JPanel();
		pnlTitle.setBackground(Color.WHITE);
		pnlTitle.setBounds(347, 0, 1186, 70);
		contentPane.add(pnlTitle);
		
		lblNewLabel = new JLabel("TÌM KIẾM SÁCH");
		pnlTitle.add(lblNewLabel);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		
		panel = new JPanel();
		panel.setBounds(347, 112, 1166, 188);
		contentPane.add(panel);
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setLayout(null);
		
		JLabel lblMaSach = new JLabel("Mã Sách");
		lblMaSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaSach.setBounds(10, 50, 123, 35);
		panel.add(lblMaSach);
		
		JLabel lblTenSach = new JLabel("Tên Sách");
		lblTenSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenSach.setBounds(10, 100, 140, 35);
		panel.add(lblTenSach);
		
		JLabel lblMaNXB = new JLabel("Nhà Xuất Bản");
		lblMaNXB.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaNXB.setBounds(415, 50, 140, 35);
		panel.add(lblMaNXB);
		
		JLabel lblMaTG = new JLabel("Tác Giả");
		lblMaTG.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaTG.setBounds(415, 100, 140, 35);
		panel.add(lblMaTG);
		
		tfMaSach = new JTextField();
		tfMaSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfMaSach.setBounds(110, 52, 250, 30);
		panel.add(tfMaSach);
		tfMaSach.setColumns(10);
		
		tfTenSach = new JTextField();
		tfTenSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfTenSach.setColumns(10);
		tfTenSach.setBounds(110, 102, 250, 30);
		panel.add(tfTenSach);
		
		tfTenNXB = new JTextField();
		tfTenNXB.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfTenNXB.setColumns(10);
		tfTenNXB.setBounds(565, 52, 250, 30);
		panel.add(tfTenNXB);
		
		tfTenTG = new JTextField();
		tfTenTG.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfTenTG.setColumns(10);
		tfTenTG.setBounds(565, 102, 250, 30);
		panel.add(tfTenTG);
		
		JLabel lblNhaptt = new JLabel("Thông tin tìm kiếm");
		lblNhaptt.setBackground(SystemColor.activeCaption);
		lblNhaptt.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNhaptt.setBounds(518, 10, 173, 30);
		panel.add(lblNhaptt);
		
		btnTimKiem = new JButton("TÌM KIẾM");
		btnTimKiem.setBounds(907, 67, 180, 45);
		panel.add(btnTimKiem);
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnTimKiem.setBackground(Color.LIGHT_GRAY);
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					searchSach();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				xoaRong();
			}
			
		});
		
		
		scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(347, 369, 1186, 300);
		getContentPane().add(scrollPaneTable);
		
		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {
				"M\u00E3 S\u00E1ch", "T\u00EAn s\u00E1ch","S\u1ED1 l\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1","Tên TG", "Tên NXB", "Th\u1EC3 lo\u1EA1i"
			}
		));
		scrollPaneTable.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Kết quả tìm kiếm");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(979, 317, 179, 30);
		contentPane.add(lblNewLabel_1);
		
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
	
	private void searchSach() throws SQLException {
		String ma = tfMaSach.getText();
		if (ma.equals("")) {
			ma = null;
		}

		String ten = tfTenSach.getText();
		if (ten.equals("")) {
			ten = null;
		}
		String tacgia = tfTenTG.getText();
		if (tacgia.equals("")) {
			tacgia = null;
		}

		String nhaxuatban = tfTenNXB.getText();
		if (nhaxuatban.equals("")) {
			nhaxuatban = null;
		}
		Sach_dao s = new Sach_dao();
		ArrayList<Sach> ds = s.searchSachTheoProperties(ma, ten, tacgia, nhaxuatban);
		model = new DefaultTableModel();
		DefaultTableModel old = (DefaultTableModel) table.getModel();
		int n = old.getColumnCount();
		for (int i = 0; i < n; i++) {
			model.addColumn(old.getColumnName(i));
		}
		n = ds.size();
		for (int i = 0; i < n; i++) {
			Sach s1 = ds.get(i);
			model.addRow(new Object[] {s1.getMaSach(),s1.getTenSach(),s1.getDonGia(),s1.getSoLuong(),s1.getMaTacGia(),s1.getMaNXB(),s1.getMaTheLoai()});
		}
		table.setModel(model);
	}
	public static void xoaRong() {
		tfMaSach.setText("");
		tfTenNXB.setText("");
		tfTenSach.setText("");
		tfTenTG.setText("");
	}

}
