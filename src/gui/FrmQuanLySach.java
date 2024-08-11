package gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import dao.NhaXuatBan_dao;
import dao.Sach_dao;
import dao.TacGia_dao;
import dao.TheLoai_dao;
import entity.NhaXuatBan;
import entity.Sach;
import entity.TacGia;
import entity.TheLoai;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import javax.swing.JTable;
import java.awt.SystemColor;

import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;


public class FrmQuanLySach extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4220925912667756305L;
	private JPanel contentPane;
	private JTextField tfTenSach;
	private JTextField tfSoLuong;
	private JTextField tfDonGia;
	private JScrollPane scrollPaneTable;
	private JTable table;
	private JPanel pnlFuncTable;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaTrang;
	private JPanel panel;
	private JTextField tfMaSach;
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
	private Sach_dao sach_dao;

	private DefaultComboBoxModel<String> modelCategory_TheLoai;
	private JComboBox<String> combox_TheLoai;
	private TheLoai_dao theloai_dao;
	private DefaultComboBoxModel modelCategory_TacGia;
	private JComboBox combox_TacGia;
	private TacGia_dao tacgia_dao;
	private NhaXuatBan_dao nxb_dao;
	private DefaultComboBoxModel modelCategory_NXB;
	private JComboBox combox_NXB;
	private JMenuItem mntmDatHang;
	private JMenuItem mntmDsHoaDon;
	private JMenuItem mntmTacGia;
	private JMenuItem mntmNXB;
	private JButton btnxuat;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmQuanLySach frame = new FrmQuanLySach();
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
	public FrmQuanLySach() throws Exception {
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
		
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBackground(Color.WHITE);
		pnlTitle.setBounds(351, 0, 1186, 70);
		contentPane.add(pnlTitle);
		pnlTitle.setLayout(null);
		JLabel lblTitle = new JLabel("CẬP NHẬT SÁCH");
		lblTitle.setBounds(487, 0, 265, 70);
		pnlTitle.add(lblTitle);
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setBackground(Color.BLACK);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 28));
		
		pnlFuncTable = new JPanel();
		pnlFuncTable.setBackground(new Color(255, 255, 255));
		pnlFuncTable.setBounds(351, 535, 1176, 117);
		contentPane.add(pnlFuncTable);
		pnlFuncTable.setLayout(null);
		
		btnThem = new JButton("THÊM");
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setBackground(Color.LIGHT_GRAY);
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnThem.setBounds(101, 33, 180, 45);
		pnlFuncTable.add(btnThem);
		
		btnXoa = new JButton("XÓA");
		btnXoa.setForeground(new Color(255, 255, 255));
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXoa.setBackground(Color.LIGHT_GRAY);
		btnXoa.setBounds(306, 32, 180, 45);
		pnlFuncTable.add(btnXoa);
		
		btnSua = new JButton("SỬA");
		btnSua.setBounds(717, 33, 180, 45);
		pnlFuncTable.add(btnSua);
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnSua.setBackground(Color.LIGHT_GRAY);
		
		btnXoaTrang = new JButton("XÓA TRẮNG");
		btnXoaTrang.setForeground(Color.WHITE);
		btnXoaTrang.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnXoaTrang.setBackground(Color.LIGHT_GRAY);
		btnXoaTrang.setBounds(513, 33, 180, 45);
		pnlFuncTable.add(btnXoaTrang);
		
		btnxuat = new JButton("Xuất Danh Sách");
		btnxuat.setBounds(917, 33, 180, 45);
		pnlFuncTable.add(btnxuat);
		btnxuat.setForeground(new Color(255, 255, 255));
		btnxuat.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnxuat.setBackground(Color.LIGHT_GRAY);
		btnxuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnXuat();
			}
		});
		
		panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(351, 69, 405, 469);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMaSach = new JLabel("Mã Sách");
		lblMaSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaSach.setBounds(10, 50, 123, 35);
		panel.add(lblMaSach);
		
		JLabel lblTenSach = new JLabel("Tên Sách");
		lblTenSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenSach.setBounds(10, 100, 140, 35);
		panel.add(lblTenSach);
		
		JLabel lblNXB = new JLabel("Tác Giả");
		lblNXB.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNXB.setBounds(10, 150, 140, 35);
		panel.add(lblNXB);
		
		JLabel lblTG = new JLabel("Nhà Xuất Bản");
		lblTG.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTG.setBounds(10, 200, 140, 35);
		panel.add(lblTG);
		
		JLabel lblSoLuong = new JLabel("Số Lượng");
		lblSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoLuong.setBounds(10, 250, 140, 35);
		panel.add(lblSoLuong);
		
		tfMaSach = new JTextField();
		tfMaSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfMaSach.setBounds(143, 52, 250, 30);
		panel.add(tfMaSach);
		tfMaSach.setColumns(10);
		
		tfTenSach = new JTextField();
		tfTenSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfTenSach.setColumns(10);
		tfTenSach.setBounds(143, 102, 250, 30);
		panel.add(tfTenSach);
		
		tfSoLuong = new JTextField();
		tfSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfSoLuong.setColumns(10);
		tfSoLuong.setBounds(143, 252, 250, 30);
		panel.add(tfSoLuong);
		
		tfDonGia = new JTextField();
		tfDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfDonGia.setColumns(10);
		tfDonGia.setBounds(143, 302, 250, 30);
		panel.add(tfDonGia);
		
		JLabel lblDonGia = new JLabel("Đơn Giá");
		lblDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDonGia.setBounds(10, 300, 140, 35);
		panel.add(lblDonGia);
		
		JLabel lblTheLoai = new JLabel("Thể Loại");
		lblTheLoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTheLoai.setBounds(10, 350, 140, 35);
		panel.add(lblTheLoai);
		
		JLabel lblNhaptt = new JLabel("Nhập thông tin sách");
		lblNhaptt.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNhaptt.setBounds(127, 10, 173, 30);
		panel.add(lblNhaptt);
		modelCategory_TacGia = new DefaultComboBoxModel<>();
		combox_TacGia = new JComboBox<>(modelCategory_TacGia);
		combox_TacGia.setBounds(143, 152, 250, 30);
		getDsCategory_TG();
		panel.add(combox_TacGia);
		
		// category NXB
		modelCategory_NXB = new DefaultComboBoxModel<>();
		combox_NXB = new JComboBox<>(modelCategory_NXB);
		combox_NXB.setBounds(143, 202, 250, 30);
		getDsCategory_NXB();
		panel.add(combox_NXB);
		
		// category The Loai
		modelCategory_TheLoai = new DefaultComboBoxModel<>();
		combox_TheLoai = new JComboBox<>(modelCategory_TheLoai);
		combox_TheLoai.setBounds(143, 352, 250, 30);
		getDsCategory_TL();
		panel.add(combox_TheLoai);
		
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        model = new DefaultTableModel();
        
        Vector<String> header = new Vector<String>();
                header.add("Mã sách");
                header.add("Tên sách");
                header.add("Nhà xuất bản");
                header.add("Tác giả");
                header.add("Số lượng");
                header.add("Đơn giá");
                header.add("Thể loại");
        model = new DefaultTableModel(header,0);
        getDsSach();
        
		scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(766, 96, 770, 442);
		getContentPane().add(scrollPaneTable);
		scrollPaneTable.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin chi tiết");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(766, 69, 169, 25);
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
 
		

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
                tfMaSach.setText(table.getModel().getValueAt(i, 0).toString());
                tfTenSach.setText(table.getModel().getValueAt(i, 1).toString());
                tfSoLuong.setText(table.getModel().getValueAt(i,4).toString());
                tfDonGia.setText(table.getModel().getValueAt(i,5).toString());
                tfMaSach.setEditable(false);
			}
		});
		
		btnThem.addMouseListener((MouseListener) new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
            	
            	if (!tfMaSach.getText().trim().matches("^SP\\d{4}$")) {
            		  JOptionPane.showMessageDialog(null, "Mã sách phải bắt đầu bằng SP và 6 số!");
            		  return;
            		}
            	if (!tfTenSach.getText().trim().matches("\\b([A-Z][a-z]*)\\b")) {
            	  JOptionPane.showMessageDialog(null, "Tên sách phải viết hoa chữ cái đầu!");
            	  return;
            	}
            	if (!tfSoLuong.getText().trim().matches("\\d+")) {
            		  JOptionPane.showMessageDialog(null, "Số lượng phải là số!");
            		  return;
            		}
            	if (!tfDonGia.getText().trim().matches("\\d+\\.\\d+")) {
            		  JOptionPane.showMessageDialog(null, "Đơn giá phải là số!");
            		  return;
            		}
            	String tacGia = (String) combox_TacGia.getSelectedItem();
        		String[] ListtacGia = tacGia.split("-");
        		
        		String nxb =(String) combox_NXB.getSelectedItem();
        		String[] ListNXB = nxb.split("-");
        		
        		String theLoai = (String) combox_TheLoai.getSelectedItem();
        		String[] ListTL = theLoai.split("-");

        		String maSach = tfMaSach.getText();
        		String tenSach = tfTenSach.getText();
        		String maNXB = ListNXB[0];
        		String maTacGia = ListtacGia[0];
        		String soLuong = tfSoLuong.getText();
        		String donGia = tfDonGia.getText();
        		String maTheLoai = ListTL[0];
            	try {
            		Sach sach = new Sach(maSach, tenSach, Integer.parseInt(soLuong), Double.parseDouble(donGia), maTacGia, maNXB, maTheLoai);
            		sach_dao = new Sach_dao();
            		sach_dao.themSach(sach);
            		getDsSach();
            		JOptionPane.showMessageDialog(null, "Thành Công: Thêm sách thành công!!");
            		
            		//clean TEXTFIELD
            		clearTextField();
            		
            	} catch (Exception ex) {
					// TODO: handle exception
            		JOptionPane.showMessageDialog(null, "Lỗi khi thêm sách");
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
            		int i = JOptionPane.showConfirmDialog(null, "Xác Nhận Xóa Sách","",JOptionPane.YES_NO_OPTION);
            		if(i==JOptionPane.YES_OPTION)
            		{
            			String maSach = model.getValueAt(selectedRow, 0).toString();
            			model.removeRow(selectedRow);
            			if(maSach != "") {
            				sach_dao = new Sach_dao();
            				try {
            					sach_dao.xoaSachTheoMa(maSach);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
            				try {
            					getDsSach();
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
            	String tacGia = (String) combox_TacGia.getSelectedItem();
        		String[] ListtacGia = tacGia.split("-");
        		
        		String nxb =(String) combox_NXB.getSelectedItem();
        		String[] ListNXB = nxb.split("-");
        		
        		String theLoai = (String) combox_TheLoai.getSelectedItem();
        		String[] ListTL = theLoai.split("-");
        		
        		
        		String maSach = tfMaSach.getText();
        		String tenSach = tfTenSach.getText();
        		String maNXB = ListNXB[0];
        		String maTacGia = ListtacGia[0];
        		String soLuong = tfSoLuong.getText();
        		String donGia = tfDonGia.getText();
        		String maTheLoai = ListTL[0];
            	
        		Sach sach = new Sach(maSach, tenSach, Integer.parseInt(soLuong), Double.parseDouble(donGia), maTacGia, maNXB, maTheLoai);
                
                try {
					sach_dao = new Sach_dao();
					sach_dao.updateSach(sach);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
                try {
					getDsSach();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                JOptionPane.showMessageDialog(null, "Thành Công: Sửa thông tin sách Thành Công");
                
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
	
	// categoty TG
	
	
	public void getDsCategory_TG() throws Exception {
		tacgia_dao = new TacGia_dao();
		ArrayList<TacGia> tacgia = tacgia_dao.layDsTacGia();
		outmodelCategory_TacGia(modelCategory_TacGia, tacgia);
	}
	
	
	public void outmodelCategory_TacGia(DefaultComboBoxModel<String> modelCate, ArrayList<TacGia> ListTacGia) {
		for (TacGia tg: ListTacGia) {
			String cate = tg.getMaTacGia() + "-" + tg.getTenTacgia();
			modelCate.addElement(cate);
		}
		combox_TacGia.setModel(modelCate);
	}
	
	//categoty NXB
	@SuppressWarnings("unchecked")
	public void getDsCategory_NXB() throws Exception {
		nxb_dao = new NhaXuatBan_dao();
		ArrayList<NhaXuatBan> nxb = nxb_dao.layDsNXB();
		outmodelCategory_NXB(modelCategory_NXB, nxb);
	}
	
	
	public void outmodelCategory_NXB(DefaultComboBoxModel<String> modelCate, ArrayList<NhaXuatBan> ListNXB) {
		for (NhaXuatBan nxb: ListNXB) {
			String cate = nxb.getMaNhaXuatBan() + "-" + nxb.getTenNhaXuatBan();
			modelCate.addElement(cate);
		}
		combox_NXB.setModel(modelCate);
	}
	

	
	//categoty TL
	public void outmodelCategory_TheLoai(DefaultComboBoxModel<String> modelCate, ArrayList<TheLoai> ListTheLoai) {
		for (TheLoai tl: ListTheLoai) {
			String cate = tl.getMaTheLoai() + "-" + tl.getTenTheLoai();
			modelCate.addElement(cate);
		}
		combox_TheLoai.setModel(modelCate);
	}
	
	public void getDsCategory_TL() throws Exception {
		theloai_dao = new TheLoai_dao();
		ArrayList<TheLoai> theLoai = theloai_dao.layDsTheLoai();
		outmodelCategory_TheLoai(modelCategory_TheLoai, theLoai);
	}
	
	
	public void outModel(DefaultTableModel model,ArrayList<Sach> ListSach)
    {
        Vector row;
        model.setRowCount(0);
        for(Sach sach:ListSach)
        {
            row = new Vector();
            row.add(sach.getMaSach());
            row.add(sach.getTenSach());
            row.add(sach.getMaNXB());
            row.add(sach.getMaTacGia());
            row.add(sach.getSoLuong());
            row.add(sach.getDonGia());
            row.add(sach.getMaTheLoai());
            model.addRow(row);
            
        }
        table.setModel(model);
    }
	
	public void getDsSach() throws Exception
	{
		sach_dao = new Sach_dao();
		ArrayList<Sach> sach = sach_dao.layDsSach();
		model.setRowCount(0);
        outModel(model, sach);
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
		tfMaSach.setText("");
		tfTenSach.setText("");
		tfSoLuong.setText("");
		tfDonGia.setText("");
		tfMaSach.setEditable(true);
	}
}
