package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.tools.Diagnostic;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import dao.KhachHang_dao;
import dao.NhanVien_dao;
import entity.KhachHang;
import entity.NhanVien;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JCheckBox;

@SuppressWarnings("unused")
public class FrmQuanLyKhachHang extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3739840895945097164L;
	private JPanel contentPane;
	private JTextField tfMaKH;
	private JTextField tfTenKH;
	private JTextField tfSDT;
	private JScrollPane scrollPaneTable;
	private JTable table_KH;
	private JPanel panel;
	private JPanel pnlTitle;
	private JPanel pnlFuncTable;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnXoaTrang;
	private JButton btnSua;
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
	private JTable table;
	private DefaultTableModel model;
	private KhachHang_dao khachhang_dao;
	private JMenuItem mntmDatHang;
	private JMenuItem mntmDsHoaDon;
	private JCheckBox chkGioiTinh;
	private JTextField txtDiaChi;
	private JMenuItem mntmTacGia;
	private JMenuItem mntmNXB;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmQuanLyKhachHang frame = new FrmQuanLyKhachHang();
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
	public FrmQuanLyKhachHang() throws Exception {
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1939, 1102);
		setResizable(false);
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
		
		JLabel lblNewLabel = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblNewLabel.setBounds(790, 0, 380, 70);
		contentPane.add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(707, 127, 877, 200);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTenNV = new JLabel("Tên khách hàng");
		lblTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenNV.setBounds(20, 65, 140, 35);
		panel.add(lblTenNV);
		
		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSDT.setBounds(457, 10, 140, 35);
		panel.add(lblSDT);
		
		JLabel lblGT = new JLabel("Giới tính");
		lblGT.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGT.setBounds(20, 123, 105, 35);
		panel.add(lblGT);
		
		tfMaKH = new JTextField();
		tfMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfMaKH.setBounds(150, 12, 250, 30);
		panel.add(tfMaKH);
		tfMaKH.setColumns(10);
		
		tfTenKH = new JTextField();
		tfTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfTenKH.setColumns(10);
		tfTenKH.setBounds(150, 67, 250, 30);
		panel.add(tfTenKH);
		
		tfSDT = new JTextField();
		tfSDT.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfSDT.setColumns(10);
		tfSDT.setBounds(582, 12, 250, 30);
		panel.add(tfSDT);
		
		JLabel lblMaKH = new JLabel("Mã khách hàng");
		lblMaKH.setBounds(20, 10, 140, 35);
		panel.add(lblMaKH);
		lblMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		chkGioiTinh = new JCheckBox("Nam");
		chkGioiTinh.setBounds(150, 131, 93, 21);
		panel.add(chkGioiTinh);
		
		JLabel lblDiaChi = new JLabel("Địa Chỉ");
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDiaChi.setBounds(457, 65, 140, 35);
		panel.add(lblDiaChi);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(582, 65, 250, 30);
		panel.add(txtDiaChi);
		
		pnlTitle = new JPanel();
		pnlTitle.setBackground(Color.WHITE);
		pnlTitle.setBounds(398, 0, 1186, 70);
		contentPane.add(pnlTitle);
		
		pnlFuncTable = new JPanel();
		pnlFuncTable.setBounds(408, 70, 307, 256);
		contentPane.add(pnlFuncTable);
		pnlFuncTable.setBackground(new Color(255, 255, 255));
		pnlFuncTable.setLayout(null);
		
		btnThem = new JButton("THÊM");
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setBackground(Color.LIGHT_GRAY);
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnThem.setBounds(65, 10, 180, 34);
		pnlFuncTable.add(btnThem);
		
		btnXoa = new JButton("XÓA");
		btnXoa.setForeground(new Color(255, 255, 255));
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXoa.setBackground(Color.LIGHT_GRAY);
		btnXoa.setBounds(65, 60, 180, 34);
		pnlFuncTable.add(btnXoa);
		
		btnXoaTrang = new JButton("XÓA TRẮNG");
		btnXoaTrang.setBounds(65, 110, 180, 34);
		pnlFuncTable.add(btnXoaTrang);
		btnXoaTrang.setForeground(new Color(255, 255, 255));
		btnXoaTrang.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnXoaTrang.setBackground(Color.LIGHT_GRAY);
		
		btnSua = new JButton("SỬA");
		btnSua.setBounds(65, 160, 180, 34);
		pnlFuncTable.add(btnSua);
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnSua.setBackground(Color.LIGHT_GRAY);
		JButton btnXuat_1 = new JButton("Xuất DS");
		btnXuat_1.setForeground(new Color(255, 255, 255));
		btnXuat_1.setBackground(Color.LIGHT_GRAY);
		btnXuat_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnXuat_1.setBounds(65, 210, 180, 34);
		pnlFuncTable.add(btnXuat_1);
		btnXuat_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnXuat();
			}
		});
		
		table = new JTable(model);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        model = new DefaultTableModel();
        
        Vector<String> header = new Vector<String>();
                header.add("Mã khách hàng");
                header.add("Tên khách hàng");
                header.add("Số điện thoại");
                header.add("Địa chỉ");
                header.add("Giới tính");
        model = new DefaultTableModel(header,0);
        getDsKhachHang();
        
        scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(398, 325, 1142, 456);
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

		table.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int i = table.getSelectedRow();
                tfMaKH.setText(table.getModel().getValueAt(i, 0).toString());
                tfTenKH.setText(table.getModel().getValueAt(i, 1).toString());
                tfSDT.setText(table.getModel().getValueAt(i,2).toString());
                txtDiaChi.setText(table.getModel().getValueAt(i, 3).toString());
                chkGioiTinh.setSelected(table.getModel().getValueAt(i, 3).toString() == "Nam" ? true : false );
                tfMaKH.setEditable(false);
            }
        });
		
		
		btnThem.addMouseListener((MouseListener) new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            
            {
            	
            	if (!tfMaKH.getText().trim().matches("^KH\\d{4}$")) {
            	  JOptionPane.showMessageDialog(null, "Mã KH không được để trống và phải có độ dài 6 ký tự bắt đầu bằng KH");
            	  return;
            	}
            	if (tfTenKH.getText().trim().matches("\\b([A-Z][a-z]*)\\b")) {
            		  JOptionPane.showMessageDialog(null, "Tên KH không được để trống");
            		  return;
            		}
            	String sdtRegex = "^\\d{10,11}$";
            	if (!tfSDT.getText().trim().matches(sdtRegex)) {
            	  JOptionPane.showMessageDialog(null, "SĐT phải là 10-11 số");
            	  return; 
            	}
            	if (txtDiaChi.getText().trim().isEmpty()) {
            		  JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống");
            		  return;
            		}
            	try {
            		String maKH = tfMaKH.getText();
            		String tenKH = tfTenKH.getText();
            		String soDienThoai = tfSDT.getText();
            		Boolean gioiTinh = chkGioiTinh.isSelected() ;
            		String diaChi = txtDiaChi.getText();
            		KhachHang khachHang = new KhachHang(maKH, tenKH, soDienThoai, diaChi, gioiTinh);
            		khachhang_dao = new KhachHang_dao();
            		khachhang_dao.themKH(khachHang);
            		getDsKhachHang();
            		JOptionPane.showMessageDialog(null, "Thành Công: Thêm khách hàng thành công!!");
            		
            		//clean TEXTFIELD
            		clearTextField();
            		
            	} catch (Exception ex) {
					// TODO: handle exception
            		JOptionPane.showMessageDialog(null, "Lỗi khi thêm khách hàng");
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
            		int i = JOptionPane.showConfirmDialog(null, "Xác Nhận Xóa Khách Hàng","",JOptionPane.YES_NO_OPTION);
            		if(i==JOptionPane.YES_OPTION)
            		{
            			String maKH = model.getValueAt(selectedRow, 0).toString();
            			model.removeRow(selectedRow);
            			if(maKH != "") {
            				khachhang_dao = new KhachHang_dao();
            				try {
            					khachhang_dao.xoaKhachHangTheoMa(maKH);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
            				try {
            					getDsKhachHang();
            				} catch (Exception e1) {
            					// TODO Auto-generated catch block
            					e1.printStackTrace();
            				}
            				JOptionPane.showMessageDialog(null, "Xóa Thành Công");
            				//clean TEXTFIELD
	                    clearTextField();
            			}
            		}
            	}
            }
        });
		
		btnSua.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
            	String maKH = tfMaKH.getText();
            	String tenKH = tfTenKH.getText();
            	String soDienThoai = tfSDT.getText();
            	String diaChi = txtDiaChi.getText();
            	boolean gioiTinh = chkGioiTinh.isSelected();
            	
            	KhachHang khachHang = new KhachHang(maKH, tenKH, soDienThoai, diaChi, gioiTinh);
                
                try {
					khachhang_dao = new KhachHang_dao();
					khachhang_dao.update(khachHang);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
//                outModel(model, danhSachBangDia.getAll());
                try {
					getDsKhachHang();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                JOptionPane.showMessageDialog(null, "Thành Công: Sửa Khách Hàng Thành Công");
                
                //clean TEXTFIELD
                clearTextField();
            }
        });
		btnXoaTrang.addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e)
            {
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
	
	@SuppressWarnings("unchecked")
	public void outModel(DefaultTableModel model,ArrayList<KhachHang> ListKhachHang)
    {
        @SuppressWarnings("rawtypes")
		Vector row;
        model.setRowCount(0);
        for(KhachHang khachHang:ListKhachHang)
        {
            row = new Vector<String>();
            row.add(khachHang.getMaKH());
            row.add(khachHang.getTenKH());
            row.add(khachHang.getSoDT());
            row.add(khachHang.getDiaChi());
            row.add(khachHang.isGioiTinh()?"Nam" : "Nữ");

            model.addRow(row);
            
        }
        table.setModel(model);
    }
	
	public void getDsKhachHang() throws Exception
	{
		khachhang_dao = new KhachHang_dao();
		ArrayList<KhachHang> nhanVien = khachhang_dao.layDsKhachHang();
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
		tfMaKH.setText("");
        tfTenKH.setText("");
        tfSDT.setText("");
        txtDiaChi.setText("");
        tfMaKH.setEditable(true);
	}
}
