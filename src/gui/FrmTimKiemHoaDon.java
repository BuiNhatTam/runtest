package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import PDF.InPDF;
import dao.ChiTietHoaDon_dao;
import dao.HoaDon_Dao;
import dao.KhachHang_dao;
import dao.Sach_dao;
import entity.ChiTietHoaDon;
import entity.KhachHang;
import entity.Sach;

@SuppressWarnings("serial")
public class FrmTimKiemHoaDon extends JFrame implements ActionListener {

	private JPanel contentPane;
	private static DefaultTableModel model_HD;
	private JScrollPane scrollPaneTable_HD;
	public static JTable table_HD;
	private JMenuBar menuBar;
	private JMenu mnNhanVien;
	private JMenu mnThongKe;
	private JMenuItem mntmTimKiemSach;
	private JMenu mnTrangChu;
	private JMenu mnSach;
	private JMenuItem mntmQuanLySach;
	private JMenu mnKhachHang;
	private JMenuItem mntmQuanLyKhachHang;
	private JMenuItem mntmTimKiemKhachHang;
	private JMenuItem mntmQuanLyNhanVien;
	private JMenuItem mntmTimKiemNhanVien;
	private JMenu mnLapHoaDon;
	private JMenuItem mntmThongKe;
	private JMenuItem mntmLapHoaDon;
	private JMenuItem mntmTrangChu;
	private JMenuItem mntmDanhMucSach;
	private JMenuItem mnDatHang;
	private JMenuItem mntmDatHang;
	private JButton btnTim;
	private JButton btnXoa;
	private JButton btnXuatHD;
	private JLabel lblNgayLapHD;
	private static DefaultTableModel model_CTHD;
	public static JTable table_CTHD;
	private JScrollPane scrollPaneTable_CTHD;
	private static KhachHang_dao kh_dao;
	private static ChiTietHoaDon_dao cthd_dao;
	private static Sach_dao sach_dao;
	private int hoaDonChonHienTai = -1;
	private String maHoaDonDuocChon;
	static JTextField tfSDT;
	static JDateChooser dateLHD;
	static JTextField tfMaHD;
	private JMenuItem mntmDsHoaDon;
	private JMenuItem mntmTacGia;
	private JMenuItem mntmNXB;
	static JTextField tfTenKH;
	static JTextField tfTenNV;
	static JTextField tfMaNV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmTimKiemHoaDon frame = new FrmTimKiemHoaDon();
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
	public FrmTimKiemHoaDon() throws Exception {

		kh_dao = new KhachHang_dao();
		cthd_dao = new ChiTietHoaDon_dao();
		sach_dao = new Sach_dao();
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
		

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(395, 70, 710, 152);
		contentPane.add(panel);

		JPanel pnlTitle = new JPanel();
		pnlTitle.setLayout(null);
		pnlTitle.setBackground(Color.WHITE);
		pnlTitle.setBounds(371, 0, 1186, 70);
		contentPane.add(pnlTitle);

		JLabel lbltHng = new JLabel("TÌM HÓA ĐƠN");
		lbltHng.setForeground(Color.BLACK);
		lbltHng.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lbltHng.setBackground(Color.WHITE);
		lbltHng.setBounds(488, 0, 275, 70);
		pnlTitle.add(lbltHng);

		JPanel pnlFuncTable = new JPanel();
		pnlFuncTable.setLayout(null);
		pnlFuncTable.setBackground(Color.WHITE);
		pnlFuncTable.setBounds(1104, 70, 453, 152);
		contentPane.add(pnlFuncTable);

		btnTim = new JButton("TÌM KIẾM");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTim.setBackground(Color.LIGHT_GRAY);
		btnTim.setBounds(23, 10, 189, 52);
		pnlFuncTable.add(btnTim);

		btnXoa = new JButton("LÀM MỚI");
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnXoa.setBackground(Color.LIGHT_GRAY);
		btnXoa.setBounds(243, 10, 189, 52);
		pnlFuncTable.add(btnXoa);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaRong();
			}
		});

		btnXuatHD = new JButton("IN HÓA ĐƠN");
		btnXuatHD.setForeground(Color.WHITE);
		btnXuatHD.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnXuatHD.setBackground(Color.LIGHT_GRAY);
		btnXuatHD.setBounds(76, 80, 317, 52);
		pnlFuncTable.add(btnXuatHD);

		lblNgayLapHD = new JLabel("Ngày Lập HĐ");
		lblNgayLapHD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgayLapHD.setBounds(10, 66, 140, 35);
		panel.add(lblNgayLapHD);

		JLabel lblSDT = new JLabel("Số Điện Thoại");
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSDT.setBounds(373, 10, 140, 35);
		panel.add(lblSDT);

		tfSDT = new JTextField();
		tfSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfSDT.setBounds(500, 12, 200, 30);
		panel.add(tfSDT);
		tfSDT.setColumns(10);

		dateLHD = new JDateChooser();
		dateLHD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dateLHD.setDateFormatString("dd - MM - yyyy");
		dateLHD.setBounds(127, 66, 200, 30);
//		dateNDH.setDate(Date.valueOf(LocalDate.now()));
		panel.add(dateLHD);

		JLabel lblMaHD = new JLabel("Mã Hóa Đơn");
		lblMaHD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaHD.setBounds(10, 10, 140, 35);
		panel.add(lblMaHD);

		tfMaHD = new JTextField();
		tfMaHD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfMaHD.setBounds(127, 12, 200, 30);
		panel.add(tfMaHD);
		tfMaHD.setColumns(10);
		
		tfTenKH = new JTextField();
		tfTenKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfTenKH.setEditable(false);
		tfTenKH.setBounds(500, 66, 200, 29);
		panel.add(tfTenKH);
		tfTenKH.setColumns(10);
		
		JLabel lblTenKH = new JLabel("Tên Khách Hàng");
		lblTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenKH.setBounds(373, 70, 118, 26);
		panel.add(lblTenKH);
		
		JLabel lblTenNV = new JLabel("Tên Nhân Viên");
		lblTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenNV.setBounds(374, 115, 120, 30);
		panel.add(lblTenNV);
		
		tfTenNV = new JTextField();
		tfTenNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfTenNV.setEditable(false);
		tfTenNV.setBounds(500, 115, 200, 30);
		panel.add(tfTenNV);
		tfTenNV.setColumns(10);
		
		tfMaNV = new JTextField();
		tfMaNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfMaNV.setEditable(false);
		tfMaNV.setBounds(127, 115, 200, 30);
		panel.add(tfMaNV);
		tfMaNV.setColumns(10);
		
		JLabel lblMaNV = new JLabel("Mã Nhân Viên");
		lblMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaNV.setBounds(10, 115, 130, 30);
		panel.add(lblMaNV);

		Vector<String> header = new Vector<String>();
		header.add("Mã Hóa Đơn");
		header.add("Mã Khách Hàng");
		header.add("Tên Khách Hàng");
		header.add("Số Điện Thoại");
		header.add("Mã Nhân Viên");
		header.add("Tên Nhân Viên");
		header.add("Ngày Lập Hóa Đơn");
		header.add("Tổng Tiền");
		model_HD = new DefaultTableModel(null, header);
		table_HD = new JTable(model_HD);
		table_HD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		model_HD = new DefaultTableModel();
		model_HD = (DefaultTableModel) table_HD.getModel();
		table_HD.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				try {

					int selectedRow = table_HD.getSelectedRow();
					int totalRow = table_HD.getRowCount();

					if (selectedRow <= totalRow && selectedRow != hoaDonChonHienTai) {
						// Remove table
						model_CTHD.setRowCount(0);
						hoaDonChonHienTai = selectedRow;
						String maHoaDon = table_HD.getValueAt(selectedRow, 0).toString();
						maHoaDonDuocChon = maHoaDon;
						DecimalFormat formatter = new DecimalFormat("###,###,###");

						List<ChiTietHoaDon> chiTietHoaDonSachs = cthd_dao.layCTHDSach(maHoaDon);

						chiTietHoaDonSachs.forEach((e1) -> {
							Sach sach = sach_dao.timSachTheoMaSach(e1.getMaSach());

							Object[] o2 = { e1.getMaHoaDon(), e1.getMaSach(),
									sach.getTenSach(),
									e1.getSoLuong(), formatter.format(sach.getDonGia()),
									formatter.format(e1.thanhTien()) };
							model_CTHD.addRow(o2);
						});
					}

				} catch (Exception e) {
				}

			}
		});

		scrollPaneTable_HD = new JScrollPane();
		scrollPaneTable_HD.setBounds(391, 218, 1166, 186);
		getContentPane().add(scrollPaneTable_HD);
		scrollPaneTable_HD.setViewportView(table_HD);

		Vector<String> header_CTHD = new Vector<String>();
		header_CTHD.add("Mã Hóa Đơn");
		header_CTHD.add("Mã Sách");
		header_CTHD.add("Tên Sách");
		header_CTHD.add("Số Lượng");
		header_CTHD.add("Đơn Giá");
		header_CTHD.add("Thành Tiền");
		model_CTHD = new DefaultTableModel(null, header_CTHD);

		table_CTHD = new JTable(model_CTHD);
		table_CTHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		model_CTHD = new DefaultTableModel();
		model_CTHD = (DefaultTableModel) table_CTHD.getModel();
		scrollPaneTable_CTHD = new JScrollPane();
		scrollPaneTable_CTHD.setBounds(391, 405, 1166, 214);
		contentPane.add(scrollPaneTable_CTHD);
		scrollPaneTable_CTHD.setViewportView(table_CTHD);
		
		table_HD.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int i = table_HD.getSelectedRow();
                tfMaHD.setText(table_HD.getModel().getValueAt(i, 0).toString());
                tfMaNV.setText(table_HD.getModel().getValueAt(i, 4).toString());
                tfTenKH.setText(table_HD.getModel().getValueAt(i, 2).toString());
                tfSDT.setText(table_HD.getModel().getValueAt(i,3).toString());
                tfTenNV.setText(table_HD.getModel().getValueAt(i,5).toString());


            }
        });

		btnTim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HoaDon_Dao hd_dao = new HoaDon_Dao();
				String maHD = tfMaHD.getText();
//				TH tìm theo mã hóa đơn
				try {
					
					if (maHD.length() != 0) {
						model_HD.getDataVector().removeAllElements();
						try {
							Object[] hoadDon = hd_dao.timHoaDonById(maHD);
							
							if (hoadDon == null) {
								xoaRongTableDanhSachHD();
								return;
							}
							model_HD.addRow(hoadDon);
							return;
							
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
					}
					String sdt = tfSDT.getText();
					
					if (sdt.length() != 0) {
						KhachHang khachHang = kh_dao.getKhachHangBySDT(sdt);
						if (khachHang == null) {
							xoaRongTableDanhSachHD();
							thongBaoKhongTonTai("Khách hàng không tồn tại");
							return;
						}
						List<Object> hoaDonKhachHang = new ArrayList<Object>();
						
						hoaDonKhachHang = hd_dao.getAllOrderBySDT(sdt, null);
						
						model_HD.getDataVector().removeAllElements();
						if (hoaDonKhachHang == null || hoaDonKhachHang.size() == 0) {
							xoaRongTableDanhSachHD();
							return;
						}
						
						hoaDonKhachHang.forEach((o) -> {
							model_HD.addRow((Object[]) o);
						});
						return;
					}
					
					if (dateLHD != null && tfMaHD.getText().equals("")) {
						xoaRongTableDanhSachHD();
						List<Object> hoaDonKhachHang = new ArrayList<Object>();
						java.sql.Date ngayLapSql = new java.sql.Date(dateLHD.getDate().getTime());
						try {
							hoaDonKhachHang = hd_dao.getAllOrderByDate(ngayLapSql);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						if (hoaDonKhachHang == null) {
							xoaRongTableDanhSachHD();
							return;
						}
						model_HD.getDataVector().removeAllElements();
						hoaDonKhachHang.forEach((o) -> {
							model_HD.addRow((Object[]) o);
						});
						return;
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày (thông tin) cần tìm kiếm!!");
				}

			}

		});
		
		btnXuatHD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (maHoaDonDuocChon == null) {
					thongBaoKhongTonTai("Vui lòng chọn hóa đơn cần xuất");
				}else {
					int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn in hóa đơn không ?", "In hóa đơn : ",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						try {
							new FrmHoaDonXuat(maHoaDonDuocChon);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//InPDF.printComponent(FrmHoaDonXuat.textArea);
						InPDF.printComponent(FrmHoaDonXuat.textArea);
					} else if (result == JOptionPane.NO_OPTION) {
						xoaRong();
						return;
					}
					xoaRong();

				}

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

	private void xoaRongTableDanhSachHD() {
		model_HD.setRowCount(0);
		model_HD.getDataVector().removeAllElements();
		model_HD.fireTableDataChanged();
	}

	public void thongBaoKhongTonTai(String thongbao) {
		JOptionPane.showMessageDialog(this, thongbao);
	}

	public static void xoaRong() {
		model_CTHD.setRowCount(0);
		model_HD.setRowCount(0);
		tfSDT.setText("");
		tfMaHD.setText("");
		dateLHD.setDate(null);
		tfTenKH.setText("");
		tfTenNV.setText("");
		tfMaNV.setText("");
		

	}
}
