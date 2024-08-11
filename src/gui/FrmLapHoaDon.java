package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;

import PDF.InPDF;
import dao.ChiTietDatHang_dao;
import dao.ChiTietHoaDon_dao;
import dao.DatHang_dao;
import dao.HoaDon_Dao;
import dao.KhachHang_dao;
import dao.NhanVien_dao;
import dao.Sach_dao;
import entity.ChiTietDatHang;
import entity.ChiTietHoaDon;
import entity.DatHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Sach;

@SuppressWarnings("unused")
public class FrmLapHoaDon extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8196603149655350000L;
	private JPanel contentPane;
	private static JTextField tfMaHD;
	private JTextField tfMakH;
	private JTextField tfMaNV;
	private JTextField tfDatHang;
	static JTextField tfThanhTien;
	private JTextField tfTenHD;
	static JTable table;
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
	private JMenuItem mntmDatHang;
	private JMenu mnDatHang;
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel modelCategory_MaNV;
	@SuppressWarnings("rawtypes")
	static JComboBox combox_MaNV;
	private NhanVien_dao nhanvien_dao;
	private DefaultTableModel model;
	private DefaultTableModel model_DH;
	private static JTable table_DH;
	private JLabel lblTable_DH;
	private JLabel lblTable_HD;
	private DatHang_dao dathang_dao;
	private JScrollPane scrollPane_DH;
	private JScrollPane scrollPaneTable;
	private JScrollPane scrollPaneTable_DH;
	private JButton btnThem;
	private JButton btnXoa;
	private KhachHang_dao kh_dao;
	private ChiTietDatHang_dao chitietDH_dao;
	private Sach_dao sach_dao;
	public static JDateChooser dateChooserNgayLHD;
	private static final SimpleDateFormat DATE_FORMAT_SQL = new SimpleDateFormat("yyyy-MM-dd");
	private ChiTietHoaDon_dao cthd_dao;
	private HoaDon_Dao hoadon_dao;
	private JMenuItem mntmDsHoaDon;
	private JMenuItem mntmTacGia;
	private JMenuItem mntmNXB;
	private static String prefixMaHoaDon = "HD00";
	static JTextField tfTenKH;
	static JTextField tfSDT;
	private static JTextField tfMaKH;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLapHoaDon frame = new FrmLapHoaDon();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public FrmLapHoaDon() throws Exception {
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
		panel.setBounds(373, 72, 1186, 301);
		contentPane.add(panel);
		
		JLabel lblMaHD = new JLabel("Mã Hóa đơn");
		lblMaHD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaHD.setBounds(22, 7, 140, 35);
		panel.add(lblMaHD);
		
		JLabel lblMaNhanVien = new JLabel("Mã nhân viên");
		lblMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaNhanVien.setBounds(295, 7, 140, 35);
		panel.add(lblMaNhanVien);
		
		JLabel lblThanhTien = new JLabel("Tổng Tiền");
		lblThanhTien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblThanhTien.setBounds(22, 142, 140, 35);
		panel.add(lblThanhTien);
		
		tfMaHD = new JTextField();
		tfMaHD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfMaHD.setColumns(10);
		tfMaHD.setBounds(132, 9, 140, 30);
		tfMaHD.setEditable(false);
		HoaDon_Dao hd = new HoaDon_Dao();
		int currentLength = hd.laySoHoaDon();
		String maHoaDonUpDate = prefixMaHoaDon+ (++currentLength);
		tfMaHD.setText(maHoaDonUpDate);
		
		panel.add(tfMaHD);
		
		tfThanhTien = new JTextField();
		tfThanhTien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfThanhTien.setColumns(10);
		tfThanhTien.setBounds(132, 144, 367, 30);
		panel.add(tfThanhTien);
		
		JPanel pnlFuncTable = new JPanel();
		pnlFuncTable.setLayout(null);
		pnlFuncTable.setBackground(Color.WHITE);
		pnlFuncTable.setBounds(0, 236, 537, 55);
		panel.add(pnlFuncTable);
		
		btnThem = new JButton("THÊM");
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnThem.setBackground(Color.LIGHT_GRAY);
		btnThem.setBounds(10, 10, 150, 35);
		pnlFuncTable.add(btnThem);
		
		btnXoa = new JButton("XÓA");
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXoa.setBackground(Color.LIGHT_GRAY);
		btnXoa.setBounds(170, 9, 150, 35);
		pnlFuncTable.add(btnXoa);
		
		JButton btnXuatHD = new JButton("XUẤT HÓA ĐƠN");
		btnXuatHD.setForeground(Color.WHITE);
		btnXuatHD.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXuatHD.setBackground(Color.LIGHT_GRAY);
		btnXuatHD.setBounds(330, 9, 200, 35);
		pnlFuncTable.add(btnXuatHD);
		
		JLabel lblNgayLap = new JLabel("Ngày lập");
		lblNgayLap.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgayLap.setBounds(22, 182, 140, 35);
		panel.add(lblNgayLap);
		
		JLabel lblTenHoaDon = new JLabel("Tên hóa đơn");
		lblTenHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenHoaDon.setBounds(295, 97, 140, 35);
		panel.add(lblTenHoaDon);
		
		tfTenHD = new JTextField();
		tfTenHD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfTenHD.setText("Hóa đơn bán lẻ");
		tfTenHD.setColumns(10);
		tfTenHD.setBounds(407, 99, 140, 30);
		panel.add(tfTenHD);
		
		dateChooserNgayLHD = new JDateChooser();
		dateChooserNgayLHD.setDateFormatString("dd - MM - yyyy");
		dateChooserNgayLHD.setBounds(132, 196, 367, 30);
		dateChooserNgayLHD.setDate(Date.valueOf(LocalDate.now()));
		panel.add(dateChooserNgayLHD);
		
		JPanel pnlTitle = new JPanel();
		pnlTitle.setLayout(null);
		pnlTitle.setBackground(Color.WHITE);
		pnlTitle.setBounds(373, 0, 1186, 70);
		contentPane.add(pnlTitle);
		
		JLabel lblLapHD = new JLabel("LẬP HÓA ĐƠN");
		lblLapHD.setForeground(Color.BLACK);
		lblLapHD.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblLapHD.setBackground(Color.WHITE);
		lblLapHD.setBounds(472, 0, 208, 70);
		pnlTitle.add(lblLapHD);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(558, 102, 628, 265);
		pnlTitle.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(700, 57, 245, 48);
		pnlTitle.add(lblNewLabel);
		
		modelCategory_MaNV = new DefaultComboBoxModel<>();
		combox_MaNV = new JComboBox<>(modelCategory_MaNV);
		combox_MaNV.setBounds(407, 11, 140, 30);
		getDsCategory_MaNV();
		panel.add(combox_MaNV);
		
		
		Vector<String> header = new Vector<String>();
	       header.add("Mã hóa đơn");
	       header.add("Mã nhân viên");
	       header.add("Tên Khách Hàng");
	       header.add("Tên sản phẩm");
	       header.add("Số lượng");
	       header.add("Đơn giá");
	       header.add("Thành tiền");
	    model = new DefaultTableModel(null, header);
	    table = new JTable(model);
	    table.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    model = new DefaultTableModel();
	    model = (DefaultTableModel) table.getModel();
		scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(373, 409, 1186, 235);
		contentPane.add(scrollPaneTable);
		scrollPaneTable.setViewportView(table);
		
		table_DH = new JTable(model_DH);
		table_DH.setFont(new Font("Tahoma", Font.PLAIN, 14));
        model_DH = new DefaultTableModel();
        
        Vector<String> header_DH = new Vector<String>();
        		header_DH.add("Mã đặt hàng");
        		header_DH.add("Mã khách hàng");
        		header_DH.add("Ngày đặt hàng");
        model_DH = new DefaultTableModel(header_DH,0);
        getDsDatHang();
        
        scrollPaneTable_DH = new JScrollPane();
		scrollPaneTable_DH.setBounds(565, 35, 621, 266);
		panel.add(scrollPaneTable_DH);
		scrollPaneTable_DH.setViewportView(table_DH);
		
		lblTable_DH = new JLabel("DANH SÁCH ĐẶT HÀNG");
		lblTable_DH.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTable_DH.setBounds(771, 0, 225, 30);
		panel.add(lblTable_DH);
		
		JLabel lblTenKH = new JLabel("Tên KH");
		lblTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenKH.setBounds(295, 57, 130, 35);
		panel.add(lblTenKH);
		
		tfTenKH = new JTextField();
		tfTenKH.setEditable(false);
		tfTenKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfTenKH.setBounds(407, 57, 140, 30);
		panel.add(tfTenKH);
		tfTenKH.setColumns(10);
		
		JLabel lblSDT = new JLabel("Số Điện Thoại");
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSDT.setBounds(22, 97, 130, 35);
		panel.add(lblSDT);
		
		tfSDT = new JTextField();
		tfSDT.setEditable(false);
		tfSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfSDT.setBounds(132, 102, 140, 30);
		panel.add(tfSDT);
		tfSDT.setColumns(10);
		
		tfMaKH = new JTextField();
		tfMaKH.setEditable(false);
		tfMaKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfMaKH.setBounds(132, 57, 140, 30);
		panel.add(tfMaKH);
		tfMaKH.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mã KH");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(22, 57, 130, 30);
		panel.add(lblNewLabel_1);
		
		lblTable_HD = new JLabel("CHI TIẾT ");
		lblTable_HD.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTable_HD.setBounds(373, 386, 225, 30);
		contentPane.add(lblTable_HD);
		
		table_DH.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
            	model.setRowCount(0);
                int row = table_DH.getSelectedRow();
                tfMaKH.setText(table_DH.getModel().getValueAt(row, 1).toString());
                
                String maKH = tfMaKH.getText();
                kh_dao = new KhachHang_dao();
    			KhachHang kh = kh_dao.searchMaKhachHang(maKH);
    			tfTenKH.setText(kh.getTenKH());
    			tfSDT.setText(kh.getSoDT());	
            }
        });
		
		
		btnThem.addMouseListener((MouseListener) new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
            	model.setRowCount(0);
            	int row = table_DH.getSelectedRow();
				String maHD = tfMaHD.getText();
				String maDH = table_DH.getModel().getValueAt(row, 0).toString();
				String maKH = table_DH.getModel().getValueAt(row, 1).toString();
				String tenHD = tfTenHD.getText();
				String maNV = (String) combox_MaNV.getSelectedItem();
				Double tongTien = 0.0;
				Double thanhTien = 0.0;
				kh_dao = new KhachHang_dao();
				KhachHang kh = kh_dao.searchMaKhachHang(maKH);
				String tenKH = kh.getTenKH();
				chitietDH_dao = new ChiTietDatHang_dao();
				ArrayList<ChiTietDatHang> dsCTDH;
				ArrayList<ChiTietHoaDon> dsChiTietHD = new ArrayList<>();
				HoaDon hd = new HoaDon(maHD, maNV, maKH, tenHD, Date.valueOf(DATE_FORMAT_SQL.format(dateChooserNgayLHD.getDate())));
				hoadon_dao = new HoaDon_Dao();
				try {
					hoadon_dao.themHD(hd);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			try {
				dsCTDH = chitietDH_dao.timTheoMaCTDH(maDH);
				
				for (ChiTietDatHang ctdh: dsCTDH) {
					int soLuong = ctdh.getSoLuong();
					Double donGia = ctdh.getDonGia();
					String maSach = ctdh.getMaSach();
					sach_dao = new Sach_dao();
					Sach sp =sach_dao.timSachTheoMaSach(maSach);
					String tenSach = sp.getTenSach();
					ChiTietHoaDon cthd = new ChiTietHoaDon(maHD, maSach, soLuong, donGia);
					dsChiTietHD.add(cthd);
					tongTien += (soLuong * donGia);
					thanhTien = soLuong * donGia;
					Object [] rowData =  {maHD, maNV, tenKH, tenSach, soLuong, donGia,thanhTien};
					model.addRow(rowData);
					model.fireTableDataChanged();
				}
				
				for (ChiTietHoaDon cthd: dsChiTietHD) {
					cthd_dao = new ChiTietHoaDon_dao();
					cthd_dao.themCTHD(cthd);
					cthd_dao.updateSoLuongTon(cthd);
				}
				tfThanhTien.setText(tongTien.toString());				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            }
        });
		
		btnXoa.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
            	int selectedRow = table.getSelectedRow();
            	if (selectedRow >= 0) {
            		int i = JOptionPane.showConfirmDialog(null, "Xác Nhận Xóa","",JOptionPane.YES_NO_OPTION);
            		if(i==JOptionPane.YES_OPTION)
            		{
            			String maHD = model.getValueAt(selectedRow, 0).toString();
            			String maSach = model.getValueAt(selectedRow, 3).toString();
            			model.removeRow(selectedRow);
            			if(maHD != "") {
            				cthd_dao = new ChiTietHoaDon_dao();
            				try {
								cthd_dao.xoaCTHDTheoMaHDMaSach(maHD, maSach);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
            				JOptionPane.showMessageDialog(null, "Xóa Thành Công");
            				xoaRong();
            			}
            		}
            	}
            }
        });
		
		btnXuatHD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn in hóa đơn không ?", "In hóa đơn : ",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					try {
						new FrmHoaDonSanPham();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					InPDF.printComponent(FrmHoaDonSanPham.textArea);
				} else if (result == JOptionPane.NO_OPTION) {
					xoaRong();
					return;
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
		mntmTrangChu.addActionListener(this);
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
	
	@SuppressWarnings("unchecked")
	public void outmodelCategory_NV(DefaultComboBoxModel<String> modelCate, ArrayList<NhanVien> ListMaNV) {
		for (NhanVien mnv: ListMaNV) {
			String cate = mnv.getMaNV();
			modelCate.addElement(cate);
		}
		combox_MaNV.setModel(modelCate);
	}
	@SuppressWarnings("unchecked")
	public void getDsCategory_MaNV() throws Exception {
		nhanvien_dao = new NhanVien_dao();
		ArrayList<NhanVien> mnv=  nhanvien_dao.layDsNhanVien();
		outmodelCategory_NV(modelCategory_MaNV, mnv);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void outModel(DefaultTableModel model,ArrayList<DatHang> ListDH)
    {
        Vector row;
        model.setRowCount(0);
        for(DatHang dh:ListDH)
        {
            row = new Vector();
            row.add(dh.getMaDatHang());
            row.add(dh.getMaKhachHang());
            row.add(dh.getNgayDatHang());


            model.addRow(row);
            
        }
        table_DH.setModel(model);
    }
	
	public void getDsDatHang() throws Exception
	{
		dathang_dao = new DatHang_dao();
		ArrayList<DatHang> dsdh = dathang_dao.layDsDatHang();
		model_DH.setRowCount(0);
        outModel(model_DH, dsdh);
	}
	
	public static void xoaRong() {
		tfThanhTien.setText("");
		tfMaKH.setText("");
		tfSDT.setText("");
		tfTenKH.setText("");
		HoaDon_Dao hd = new HoaDon_Dao();
		int currentLength = hd.laySoHoaDon();
		String maHoaDonUpDate = prefixMaHoaDon+ (++currentLength);
		tfMaHD.setText(maHoaDonUpDate);
		
	}
}
