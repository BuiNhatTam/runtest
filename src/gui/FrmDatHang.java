package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietDatHang_dao;
import dao.DatHang_dao;
import dao.KhachHang_dao;
import dao.Sach_dao;
import entity.ChiTietDatHang;
import entity.DatHang;
import entity.KhachHang;
import entity.Sach;

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
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class FrmDatHang extends JFrame implements ActionListener {

	private JPanel contentPane;
	private static DefaultTableModel model;
	private JScrollPane scrollPaneTable;
	public static JTable table;
	private static JTextField tfMaSach;
	private static JTextField tfSoLuongTon;
	private DefaultComboBoxModel<String> modelCategory_TenSach;
	private JComboBox<String> combox_TenSach;
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
	private JMenu mnDatHang;
	private JMenuItem mntmDatHang;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnDatHang;
	private Sach_dao sach_dao;
	private static JTextField tfDonGia;
	private static JTextField tfMaDatHang;
	private JLabel lblMaDatHang;
	private KhachHang_dao khachhang_dao;
	private JLabel lblNgayDatHang;
	private static DefaultTableModel model_DH;
	private JTable table_DH;
	private JScrollPane scrollPaneTable_DH;
	private static JSpinner spinnerSoLuong;
	private DatHang_dao dathang_dao;
	private ChiTietDatHang_dao ctdh_dao;
	private JMenuItem mntmDsHoaDon;
	private JMenuItem mntmTacGia;
	private JMenuItem mntmNXB;
	private static final SimpleDateFormat DATE_FORMAT_SQL = new SimpleDateFormat("yyyy-MM-dd");
	private static String prefixMaDatHang = "DH00";
	static JTextField tfSDT;
	private static JTextField tfMaKH;
	static JTextField tfTenKh;
	static JDateChooser dateNDH;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDatHang frame = new FrmDatHang();
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
	public FrmDatHang() throws Exception {
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(357, 69, 713, 298);
		contentPane.add(panel);

		JLabel lblMaSach = new JLabel("Mã sách");
		lblMaSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaSach.setBounds(344, 58, 140, 35);
		panel.add(lblMaSach);

		JLabel lblTenSach = new JLabel("Tên sách");
		lblTenSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenSach.setBounds(40, 8, 140, 35);
		panel.add(lblTenSach);

		JLabel lblSoLuongTon = new JLabel("Số lượng tồn");
		lblSoLuongTon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoLuongTon.setBounds(40, 110, 140, 35);
		panel.add(lblSoLuongTon);

		tfMaSach = new JTextField();
		tfMaSach.setEditable(false);
		tfMaSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfMaSach.setColumns(10);
		tfMaSach.setBounds(467, 60, 203, 30);
		panel.add(tfMaSach);

		tfSoLuongTon = new JTextField();
		tfSoLuongTon.setEditable(false);
		tfSoLuongTon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfSoLuongTon.setColumns(10);
		tfSoLuongTon.setBounds(170, 110, 154, 30);
		panel.add(tfSoLuongTon);

		JLabel lblSLng = new JLabel("Số lượng");
		lblSLng.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSLng.setBounds(40, 210, 140, 35);
		panel.add(lblSLng);

		spinnerSoLuong = new JSpinner();
		spinnerSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		spinnerSoLuong.setBounds(170, 210, 154, 30);
		panel.add(spinnerSoLuong);

		JPanel pnlTitle = new JPanel();
		pnlTitle.setLayout(null);
		pnlTitle.setBackground(Color.WHITE);
		pnlTitle.setBounds(358, 0, 1186, 70);
		contentPane.add(pnlTitle);

		JLabel lbltHng = new JLabel("ĐẶT HÀNG");
		lbltHng.setForeground(Color.BLACK);
		lbltHng.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lbltHng.setBackground(new Color(0, 0, 0));
		lbltHng.setBounds(488, 0, 159, 70);
		pnlTitle.add(lbltHng);

		JPanel pnlFuncTable = new JPanel();
		pnlFuncTable.setLayout(null);
		pnlFuncTable.setBackground(Color.WHITE);
		pnlFuncTable.setBounds(1070, 69, 473, 298);
		contentPane.add(pnlFuncTable);

		btnThem = new JButton("THÊM VÀO GIỎ HÀNG");
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnThem.setBackground(Color.LIGHT_GRAY);
		btnThem.setBounds(74, 22, 317, 52);
		pnlFuncTable.add(btnThem);

		btnXoa = new JButton("XÓA KHỎI GIỎ HÀNG");
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnXoa.setBackground(Color.LIGHT_GRAY);
		btnXoa.setBounds(74, 115, 317, 52);
		pnlFuncTable.add(btnXoa);

		btnDatHang = new JButton("ĐẶT HÀNG");
		btnDatHang.setForeground(Color.WHITE);
		btnDatHang.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDatHang.setBackground(Color.LIGHT_GRAY);
		btnDatHang.setBounds(74, 211, 317, 52);
		pnlFuncTable.add(btnDatHang);

		modelCategory_TenSach = new DefaultComboBoxModel<>();
		combox_TenSach = new JComboBox<>(modelCategory_TenSach);
		combox_TenSach.setBounds(170, 12, 500, 36);
		getDsCategory_TenSach();
		panel.add(combox_TenSach);

		JLabel lblSoLuongTon_1 = new JLabel("Đơn giá");
		lblSoLuongTon_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoLuongTon_1.setBounds(344, 210, 72, 35);
		panel.add(lblSoLuongTon_1);

		tfDonGia = new JTextField();
		tfDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfDonGia.setColumns(10);
		tfDonGia.setBounds(467, 212, 203, 30);
		panel.add(tfDonGia);

		tfMaDatHang = new JTextField();
		tfMaDatHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfMaDatHang.setColumns(10);
		tfMaDatHang.setBounds(170, 60, 154, 30);
		panel.add(tfMaDatHang);

		lblMaDatHang = new JLabel("Mã đặt hàng");
		lblMaDatHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaDatHang.setBounds(40, 58, 140, 35);
		tfMaDatHang.setEditable(false);
		DatHang_dao dh = new DatHang_dao();
		int currentLength = dh.laySoPhieuDatHang();
		String maHoaDonUpDate = prefixMaDatHang + (++currentLength);
		tfMaDatHang.setText(maHoaDonUpDate);
		panel.add(lblMaDatHang);

		JLabel lblMaKhachHang = new JLabel("Mã khách hàng");
		lblMaKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaKhachHang.setBounds(40, 160, 140, 35);
		panel.add(lblMaKhachHang);

		lblNgayDatHang = new JLabel("Ngày đặt hàng");
		lblNgayDatHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgayDatHang.setBounds(40, 255, 140, 35);
		panel.add(lblNgayDatHang);

		JLabel lblSDT = new JLabel("Số Điện Thoại");
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSDT.setBounds(344, 110, 140, 35);
		panel.add(lblSDT);

		tfSDT = new JTextField();
		tfSDT.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfSDT.setBounds(467, 110, 203, 30);
		panel.add(tfSDT);
		tfSDT.setColumns(10);

		tfMaKH = new JTextField();
		tfMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfMaKH.setBounds(170, 160, 154, 30);
		tfMaKH.setEditable(false);
		panel.add(tfMaKH);
		tfMaKH.setColumns(10);

		JLabel lbl = new JLabel("Tên Khách Hàng");
		lbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl.setBounds(344, 160, 140, 35);
		panel.add(lbl);

		tfTenKh = new JTextField();
		tfTenKh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfTenKh.setBounds(467, 160, 203, 30);
		tfTenKh.setEditable(false);
		panel.add(tfTenKh);
		tfTenKh.setColumns(10);

		dateNDH = new JDateChooser();
		dateNDH.setDateFormatString("dd - MM - yyyy");
		dateNDH.setBounds(164, 255, 506, 30);
		dateNDH.setDate(Date.valueOf(LocalDate.now()));
		panel.add(dateNDH);

		combox_TenSach.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tenSach = (String) combox_TenSach.getSelectedItem();
				Sach s = sach_dao.timSachTheoTenSach(tenSach);
				tfMaSach.setText(s.getMaSach());
				tfSoLuongTon.setText(Integer.toString(s.getSoLuong()));
				tfDonGia.setText(Double.toString(s.getDonGia()));
			}
		});

		Vector<String> header = new Vector<String>();
		header.add("Mã đặt hàng");
		header.add("Mã sách");
		header.add("Tên sách");
		header.add("Số lượng");
		header.add("Đơn giá");
		model = new DefaultTableModel(null, header);

		table = new JTable(model);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		model = new DefaultTableModel();
		model = (DefaultTableModel) table.getModel();

		scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(366, 406, 651, 235);
		getContentPane().add(scrollPaneTable);
		scrollPaneTable.setViewportView(table);

		JLabel lblGioHang = new JLabel("Giỏ Hàng");
		lblGioHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGioHang.setBounds(635, 367, 118, 40);
		contentPane.add(lblGioHang);

		JLabel lblnHng = new JLabel("Đơn Hàng");
		lblnHng.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblnHng.setBounds(1145, 367, 118, 40);
		contentPane.add(lblnHng);

		Vector<String> header_DH = new Vector<String>();
		header_DH.add("Mã đặt hàng");
		header_DH.add("Mã khách hàng");
		header_DH.add("Ngày đặt hàng");
		model_DH = new DefaultTableModel(null, header_DH);

		table_DH = new JTable(model_DH);
		table_DH.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		model_DH = new DefaultTableModel();
		model_DH = (DefaultTableModel) table_DH.getModel();
		scrollPaneTable_DH = new JScrollPane();
		scrollPaneTable_DH.setBounds(1014, 406, 530, 235);
		contentPane.add(scrollPaneTable_DH);
		scrollPaneTable_DH.setViewportView(table_DH);

		tfSDT.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {

				if (tfSDT.getText().length() != 0) {
					khachhang_dao = new KhachHang_dao();
					String soDienThoai = "";
					Pattern pattern = Pattern.compile("^\\d{10}$");
					Matcher matcher = pattern.matcher(tfSDT.getText());
					boolean valid = matcher.find();
					if (!tfSDT.getText().equals("") && valid) {
						soDienThoai = tfSDT.getText();
						KhachHang khachHang = khachhang_dao.getKhachHangBySDT(soDienThoai);

						if (khachHang == null) {
							tfMaKH.setText("");
							tfTenKh.setText("");
							tfSDT.requestFocus();
							tfSDT.selectAll();
							JOptionPane.showMessageDialog(null, "Error: Khách hàng không tồn tại", "Error Message",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						tfMaKH.setText(khachHang.getMaKH());
						tfTenKh.setText(khachHang.getTenKH());
						return;
					}
					if (tfSDT.getText().length() != 0 && valid == false) {
						tfMaKH.setText("");
						tfTenKh.setText("");
						tfSDT.requestFocus();
						return;
					}
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {

				if (tfSDT.getText().length() != 0) {

					String soDienThoai = "";
					Pattern pattern = Pattern.compile("^\\d{10}$");
					Matcher matcher = pattern.matcher(tfSDT.getText());
					boolean valid = matcher.find();
					if (!tfSDT.getText().equals("") && valid) {
						soDienThoai = tfSDT.getText();
						KhachHang khachHang = khachhang_dao.getKhachHangBySDT(soDienThoai);

						if (khachHang == null) {
							tfMaKH.setText("");
							tfTenKh.setText("");
							tfSDT.requestFocus();
							tfSDT.selectAll();

							JOptionPane.showMessageDialog(null, "Error: Khách hàng không tồn tại", "Error Message",
									JOptionPane.ERROR_MESSAGE);

							return;
						}

						tfMaKH.setText(khachHang.getMaKH());
						tfTenKh.setText(khachHang.getTenKH());
						return;
					}
					if (tfSDT.getText().length() != 0 && valid == false) {
						tfMaKH.setText("");
						tfTenKh.setText("");
						tfSDT.requestFocus();
						return;
					}
				}

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}
		});

		btnThem.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String maDatHang = tfMaDatHang.getText();
					String maSach = tfMaSach.getText();
					String tenSach = (String) combox_TenSach.getSelectedItem();
					int soLuong = (int) spinnerSoLuong.getValue();
					int soLuongTon = Integer.parseInt(tfSoLuongTon.getText());
					if (soLuong > soLuongTon) {
						JOptionPane.showMessageDialog(null, "Số lượng tồn không đủ!!");
					} else if (soLuongTon == 0) {
						JOptionPane.showMessageDialog(null, "Hết hàng!!");
					} else {
						Double donGia = Double.parseDouble(tfDonGia.getText());
						Object[] rowData = { maDatHang, maSach, tenSach, soLuong, donGia };
						model.addRow(rowData);
						model.fireTableDataChanged();
						JOptionPane.showMessageDialog(null, "Thêm vào giỏ hàng thành công!!");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		btnXoa.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					model.removeRow(selectedRow);
				}
			}
		});

		btnDatHang.addMouseListener(new MouseAdapter() {

			DatHang newdh = new DatHang();

			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					String maDH = tfMaDatHang.getText();
					String maKH = tfMaKH.getText();
					DatHang dh = new DatHang(maDH, maKH, Date.valueOf(DATE_FORMAT_SQL.format(dateNDH.getDate())));
					dathang_dao = new DatHang_dao();
					dathang_dao.themPhieuDatHang(dh);
					int rowCount = table.getRowCount();
					ArrayList<ChiTietDatHang> ctdhList = new ArrayList<>();
					for (int i = 0; i < rowCount; i++) {
						String maDatHang = table.getValueAt(i, 0).toString();
						String maSach1 = table.getValueAt(i, 1).toString();
						int soLuong1 = (int) table.getValueAt(i, 3);
						Double donGia1 = (Double) table.getValueAt(i, 4);

						ChiTietDatHang ctdh1 = new ChiTietDatHang(maDatHang, maSach1, soLuong1, donGia1);
						ctdhList.add(ctdh1);
					}

					ctdh_dao = new ChiTietDatHang_dao();
					for (ChiTietDatHang ct : ctdhList) {
						ctdh_dao.themCTPhieuDatHang(ct);
					}

					Object[] rowData = { maDH, maKH, Date.valueOf(DATE_FORMAT_SQL.format(dateNDH.getDate())) };
					model_DH.addRow(rowData);
					model_DH.fireTableDataChanged();
					JOptionPane.showMessageDialog(null, "Thành Công: Đặt hàng thành công!!");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Lỗi khi đặt hàng");
					String mahoadonhientai = tfMaDatHang.getText();
					dathang_dao.xoaPhieuDatHang(mahoadonhientai);
					ex.printStackTrace();
				}
				xoaRong();
				newdh.setMaDatHang(maHoaDonUpDate);

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

	public void outmodelCategory_TenSach(DefaultComboBoxModel<String> modelCate, ArrayList<Sach> ListMaSach) {
		for (Sach ms : ListMaSach) {
			String cate = ms.getTenSach();
			modelCate.addElement(cate);
		}
		combox_TenSach.setModel(modelCate);
	}

	public void getDsCategory_TenSach() throws Exception {
		dathang_dao = new DatHang_dao();
		ArrayList<Sach> ms = dathang_dao.layDsTenSach();
		outmodelCategory_TenSach(modelCategory_TenSach, ms);
	}

	public static void xoaRong() {
		model.setRowCount(0);
		model_DH.setRowCount(0);
		tfMaSach.setText("");
		tfSoLuongTon.setText("");
		tfDonGia.setText("");
		tfSDT.setText("");
		tfMaKH.setText("");
		tfTenKh.setText("");
		dateNDH.setDate(Date.valueOf(LocalDate.now()));
		spinnerSoLuong.setValue(0);
		DatHang_dao dh = new DatHang_dao();
		int currentLength = dh.laySoPhieuDatHang();
		String maHoaDonUpDate = prefixMaDatHang + (++currentLength);
		tfMaDatHang.setText(maHoaDonUpDate);
	}
}
