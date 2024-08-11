package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dao.ChiTietHoaDon_dao;
import dao.HoaDon_Dao;
import dao.Sach_dao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Sach;
import javax.swing.JTextArea;
import java.awt.Font;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;



@SuppressWarnings("serial")
public class FrmHoaDonXuat extends JFrame{

	/**
	 * 
	 */

	private JPanel pnHoaDon;
	private static HoaDon_Dao hd_dao;
	private static ChiTietHoaDon_dao cthd_dao;
	private static Sach_dao sach_dao;
	public static JTextArea textArea;
		
	public FrmHoaDonXuat(String maHoaDonDuocChon) throws SQLException {
		
		hd_dao = new HoaDon_Dao();
		cthd_dao = new ChiTietHoaDon_dao();
		sach_dao = new Sach_dao();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(new Dimension(425,600));
		setLocationRelativeTo(null);
		setResizable(false);

		pnHoaDon = new JPanel();
		pnHoaDon.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnHoaDon.setLayout(null);
		textArea = new JTextArea();
		textArea.setEnabled(true);
		HoaDon hoaDon= hd_dao.timHoaDon(maHoaDonDuocChon);
		 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		textArea.setFont(new Font("Arial", Font.BOLD, 11));
		textArea.setBounds(5, 5, 400, 535);
		textArea.append("\n 		Hiệu Sách Tư Nhân \n");
		textArea.append("\n 12 Nguyễn Văn Bảo, phường 4, quận Gò Vấp, Thành Phố Hồ Chí Minh \n");
		textArea.append("---------------------------------------------------------------------------------------------------\n");
		textArea.append("	 	HÓA ĐƠN BÁN HÀNG \n");
		textArea.append("  Ngày lập hóa đơn: " + sdf.format(hoaDon.getNgayLapHoaDon()) + "\n");	
		textArea.append("  Khách hàng:         " + FrmTimKiemHoaDon.tfTenKH.getText() + "\n");
		textArea.append("  Điện thoại:           " + FrmTimKiemHoaDon.tfSDT.getText() + "\n");
		textArea.append("  Nhân viên:            " + FrmTimKiemHoaDon.tfTenNV.getText() + "\n");
		textArea.append("\n"+String.format("   |%-14s|%-16s|%-16s|\n", "    SL    ", "      ĐGiá    	  ", "	TTiền"));
		textArea.append(" --------------------------------------------------------------------------------------------------\n");

	
		double tongTien = 0;
		List<ChiTietHoaDon> listSach=cthd_dao.layCTHDSach(maHoaDonDuocChon);
		for (ChiTietHoaDon e : listSach) {
			Sach sach=sach_dao.timSachTheoMaSach(e.getMaSach());
			String tenSP=sach.getTenSach();
			int sL=e.getSoLuong();
			double ttien=e.thanhTien();
			double dg=sach.getDonGia();
			
			tongTien += ttien;
			tenSP = (tenSP.length() > 64) ? tenSP.substring(0,64) +"\n" +"  "+tenSP.substring(64,tenSP.length()) : tenSP ;
			textArea.append("  "+tenSP +"\n");
			textArea.append(String.format("   |%-14s|%-24s|%-16s|\n", "    "+sL+"    ", "      "+dg+"    ", "	"+ttien));
			textArea.append("  -------------------------------------------------------------------------------------------------\n");			
		}
		DecimalFormat df = new DecimalFormat("#,###,###,###  VNĐ");
		textArea.append(String.format("\n  Tổng cộng: %55s \n" ,df.format(tongTien).toString()));
		textArea.append("\n\n\n 	Cảm ơn quý khách và hẹn gặp lại! \n");
		pnHoaDon.add(textArea);
		textArea.getRows();
		add(pnHoaDon);
		setVisible(true);

	}
}



