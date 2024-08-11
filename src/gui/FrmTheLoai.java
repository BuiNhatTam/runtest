package gui;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import dao.TheLoai_dao;
import entity.TheLoai;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import com.toedter.components.JSpinField;
import com.toedter.components.JLocaleChooser;

public class FrmTheLoai extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7361826456546321798L;
	private JPanel contentPane;
	private JTextField tfMaTheLoai;
	private JTextField tfTenTheLoai;
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
	private TheLoai_dao theloai_dao;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private JMenuItem mntmDatHang;
	private JMenuItem mntmDsHoaDon;
	private JMenuItem mntmTacGia;
	private JMenuItem mntmNXB;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmTheLoai frame = new FrmTheLoai();
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
	public FrmTheLoai() throws Exception {
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
		panel.setBounds(375, 213, 695, 118);
		contentPane.add(panel);
		
		JLabel lblMaTheLoai = new JLabel("Mã Thể Loại");
		lblMaTheLoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaTheLoai.setBounds(40, 10, 140, 35);
		panel.add(lblMaTheLoai);
		
		JLabel lblTenTheLoai = new JLabel("Tên Thể Loại");
		lblTenTheLoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenTheLoai.setBounds(40, 55, 140, 35);
		panel.add(lblTenTheLoai);
		
		tfMaTheLoai = new JTextField();
		tfMaTheLoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfMaTheLoai.setColumns(10);
		tfMaTheLoai.setBounds(170, 17, 500, 30);
		panel.add(tfMaTheLoai);
		
		tfTenTheLoai = new JTextField();
		tfTenTheLoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfTenTheLoai.setColumns(10);
		tfTenTheLoai.setBounds(170, 60, 500, 30);
		panel.add(tfTenTheLoai);
		
		JPanel pnlTitle = new JPanel();
		pnlTitle.setLayout(null);
		pnlTitle.setBackground(Color.WHITE);
		pnlTitle.setBounds(372, 38, 1186, 70);
		contentPane.add(pnlTitle);
		
		JLabel lblDanhMcSch = new JLabel("THỂ LOẠI SÁCH");
		lblDanhMcSch.setForeground(Color.BLACK);
		lblDanhMcSch.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblDanhMcSch.setBackground(Color.WHITE);
		lblDanhMcSch.setBounds(413, 0, 310, 70);
		pnlTitle.add(lblDanhMcSch);
		
		JPanel pnlFuncTable_1 = new JPanel();
		pnlFuncTable_1.setLayout(null);
		pnlFuncTable_1.setBackground(Color.WHITE);
		pnlFuncTable_1.setBounds(375, 331, 695, 473);
		contentPane.add(pnlFuncTable_1);
		
		JButton btnThem = new JButton("THÊM");
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnThem.setBackground(Color.LIGHT_GRAY);
		btnThem.setBounds(141, 40, 400, 45);
		pnlFuncTable_1.add(btnThem);
		
		JButton btnXoa = new JButton("XÓA");
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXoa.setBackground(Color.LIGHT_GRAY);
		btnXoa.setBounds(141, 126, 400, 45);
		pnlFuncTable_1.add(btnXoa);
		
		JButton btnSua = new JButton("SỬA");
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnSua.setBackground(Color.LIGHT_GRAY);
		btnSua.setBounds(141, 211, 400, 45);
		pnlFuncTable_1.add(btnSua);
		
		JButton btnXoaTrang = new JButton("XÓA TRẮNG");
		btnXoaTrang.setForeground(Color.WHITE);
		btnXoaTrang.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnXoaTrang.setBackground(Color.LIGHT_GRAY);
		btnXoaTrang.setBounds(141, 293, 400, 45);
		pnlFuncTable_1.add(btnXoaTrang);
		
		table = new JTable(model);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        model = new DefaultTableModel();
        
        Vector<String> header = new Vector<String>();
                header.add("Mã danh mục");
                header.add("Tên danh mục");
        model = new DefaultTableModel(header,0);
        getDsTheLoai();
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(1080, 257, 490, 546);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(1068, 213, 490, 47);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Danh Mục");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(203, 10, 102, 27);
		panel_1.add(lblNewLabel);
		
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

		
		table.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int i = table.getSelectedRow();
                tfMaTheLoai.setText(table.getModel().getValueAt(i, 0).toString());
                tfTenTheLoai.setText(table.getModel().getValueAt(i, 1).toString());
            }
        });
		
		btnThem.addMouseListener((MouseListener) new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
            	try {
            		String maTL = tfMaTheLoai.getText();
            		String tenTL = tfTenTheLoai.getText();
            		
            		TheLoai theLoai = new TheLoai(maTL ,tenTL);
            		theloai_dao = new TheLoai_dao();
            		theloai_dao.themTheLoai(theLoai);
            		getDsTheLoai();
            		JOptionPane.showMessageDialog(null, "Thành Công: Thêm thể loại thành công!!");
            		//clean TEXTFIELD
            		clearTextField();
            		
            	} catch (Exception ex) {
					// TODO: handle exception
            		JOptionPane.showMessageDialog(null, "Lỗi khi thêm thể loại");
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
            		int i = JOptionPane.showConfirmDialog(null, "Xác Nhận Xóa Thể loại","",JOptionPane.YES_NO_OPTION);
            		if(i==JOptionPane.YES_OPTION)
            		{
            			String maTL = model.getValueAt(selectedRow, 0).toString();
            			model.removeRow(selectedRow);
            			if(maTL != "") {
            				theloai_dao = new TheLoai_dao();
            				try {
            					theloai_dao.xoaTheLoai(maTL);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
            				try {
            					getDsTheLoai();
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
            	String maTL = tfMaTheLoai.getText();
            	String tenTL = tfTenTheLoai.getText();
            	
            	TheLoai theloai = new TheLoai(maTL, tenTL);
                
                try {
					theloai_dao = new TheLoai_dao();
					theloai_dao.updateTheLoai(theloai);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
                //outModel(model, danhSachBangDia.getAll());
                try {
					getDsTheLoai();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                JOptionPane.showMessageDialog(null, "Thành Công: Sửa Thể Loại Thành Công");
                
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void outModel(DefaultTableModel model,ArrayList<TheLoai> listTheLoais)
    {
        Vector row;
        model.setRowCount(0);
        for(TheLoai theloai:listTheLoais)
        {
            row = new Vector();
            row.add(theloai.getMaTheLoai());
            row.add(theloai.getTenTheLoai());

            model.addRow(row);
            
        }
        table.setModel(model);
    }
	
	public void getDsTheLoai() throws Exception
	{
		theloai_dao = new TheLoai_dao();
		ArrayList<TheLoai> theloai = theloai_dao.layDsTheLoai();
		model.setRowCount(0);
        outModel(model, theloai);
	}
	
	public void clearTextField() {
		tfMaTheLoai.setText("");
		tfTenTheLoai.setText("");
	}
}
