package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.NhanVien_dao;
import entity.NhanVien;

import javax.swing.JTextArea;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JScrollPane;
import javax.swing.JTable;


@SuppressWarnings("unused")
public class FrmHoaDonSanPham extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnHoaDon;
	public static JTextArea textArea;
	private NhanVien_dao nv_dao;
		

	public FrmHoaDonSanPham() throws SQLException {
		JScrollPane sc = new JScrollPane();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(new Dimension(425,600));
		String maNv = (String) FrmLapHoaDon.combox_MaNV.getSelectedItem();
		nv_dao = new NhanVien_dao();
		NhanVien nv = nv_dao.timNVTheoMa(maNv);
		String tennv = nv.getTenNV();
		Date dateChooser = FrmLapHoaDon.dateChooserNgayLHD.getDate();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = sdf.format(dateChooser);
		setLocationRelativeTo(null);
		setResizable(false);
		pnHoaDon = new JPanel();
		pnHoaDon.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnHoaDon.setLayout(null);
		textArea = new JTextArea();
		textArea.setEnabled(true);

		textArea.setFont(new Font("Arial", Font.BOLD, 11));
		textArea.setBounds(5, 5, 400, 535);
		textArea.append("\n 		Hiệu sách tư nhân \n");
		textArea.append("\n 12 Nguyễn Văn Bảo, phường 4, quận Gò Vấp, Thành Phố Hồ Chí Minh \n");
		textArea.append("---------------------------------------------------------------------------------------------------\n");
		textArea.append("	 	HÓA ĐƠN BÁN HÀNG \n");
		textArea.append("  Ngày lập hóa đơn: " + formattedDate + "\n");	
		textArea.append("  Khách hàng:         " + FrmLapHoaDon.tfTenKH.getText() + "\n");
		textArea.append("  Điện thoại:           " + FrmLapHoaDon.tfSDT.getText() + "\n");
		textArea.append("  Nhân viên:            " + tennv + "\n");
		textArea.append("\n"+String.format("   |%-14s|%-16s|%-16s|\n", "    SL    ", "      ĐGiá    	  ", "	TTiền"));
		textArea.append(" --------------------------------------------------------------------------------------------------\n");

		int tableRowCount  = FrmLapHoaDon.table.getRowCount();
		String tenSanPham = null, soLuong, donGia;
		double tongtien =0;
		for(int i = 0; i<tableRowCount; i++) {
			tenSanPham = FrmLapHoaDon.table.getValueAt(i, 3).toString();
			soLuong = FrmLapHoaDon.table.getValueAt(i, 4).toString();
			donGia = FrmLapHoaDon.table.getValueAt(i, 5).toString();
			double thanhTien = Double.parseDouble(FrmLapHoaDon.table.getValueAt(i, 6).toString());
			tongtien += thanhTien;
			tenSanPham = (tenSanPham.length() > 64) ? tenSanPham.substring(0,64) +"\n" +"  "+tenSanPham.substring(64,tenSanPham.length()) : tenSanPham ;
			textArea.append("  "+tenSanPham +"\n");
			textArea.append(String.format("   |%-14s|%-24s|%-16s|\n", "    "+soLuong+"    ", "      "+donGia+"    ", "	"+thanhTien));
			textArea.append("  -------------------------------------------------------------------------------------------------\n");
		}
		DecimalFormat df = new DecimalFormat("#,###,###,###  VNĐ");
		textArea.append(String.format("\n  Tổng cộng:      %55s" ,df.format(tongtien).toString()));
		textArea.append("\n\n\n 	Cảm ơn quý khách và hẹn gặp lại! \n");
		pnHoaDon.add(textArea);
		textArea.getRows();
		add(pnHoaDon);
		setVisible(true);

	}
}
