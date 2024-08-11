package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import db.connectDB;
import entity.ChiTietHoaDon;


public class ChiTietHoaDon_dao {
	private Connection con;
	private static ChiTietHoaDon_dao _instance;

	public ChiTietHoaDon_dao() {
		con = connectDB.getInstance().getConnection();
	}

	public static ChiTietHoaDon_dao getInstance() throws Exception {
		if (_instance == null) {
			synchronized (ChiTietHoaDon_dao.class) {
				if (null == _instance) {
					_instance = new ChiTietHoaDon_dao();
				}
			}
		}
		return _instance;
	}
	
	// Thêm
	public boolean themCTHD(ChiTietHoaDon cthd) throws SQLException {
		PreparedStatement stmt = null;
		int n = 0;

		stmt = con.prepareStatement("insert into ChiTietHoaDon values(?,?,?,?)");
		stmt.setString(1, cthd.getMaHoaDon());
		stmt.setString(2, cthd.getMaSach());
		stmt.setInt(3, cthd.getSoLuong());
		stmt.setDouble(4, cthd.getDonGia());
		
		n = stmt.executeUpdate();

		return n > 0;
	}
	//Xóa theo mã hóa đơn
	public boolean xoaCTHD(String maHD) throws Exception {
		
		PreparedStatement stmt = con.prepareStatement("delete ChiTietHoaDon where MaHoaDon=?");
		stmt.setString(1, maHD);
		int n = stmt.executeUpdate();

		return n > 0;
	}
	//Xóa theo mã hóa đơn và mã sách
	public boolean xoaCTHDTheoMaHDMaSach(String maHD, String maSach) throws Exception {
		
		PreparedStatement stmt = con.prepareStatement("delete ChiTietHoaDon where MaHoaDon=? AND MaSach=?");
		stmt.setString(1, maHD);
		stmt.setString(2, maSach);
		int n = stmt.executeUpdate();

		return n > 0;
	}
	//Lấy danh sách
	public ArrayList<ChiTietHoaDon> layDsCTHoaDon() throws SQLException {
		ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<>();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("select * from ChiTietHoaDon");
		while (rs.next()) {
			String maHD = rs.getString(1);
			String maSach = rs.getString(2);
			int soLuong = rs.getInt(3);
			Double donGia = rs.getDouble(4);

			ChiTietHoaDon cthd = new ChiTietHoaDon(maHD, maSach, soLuong, donGia);
			dsCTHD.add(cthd);
		}

		return dsCTHD;

	}
	
	public boolean updateSoLuongTon(ChiTietHoaDon ct) throws SQLException {
		PreparedStatement stmt = null;
		int n = 0;

		stmt = con.prepareStatement("UPDATE Sach \r\n"
				+ "SET Sach.SoLuong = s.SoLuong - ct.SoLuong\r\n"
				+ "FROM Sach s \r\n"
				+ "JOIN ChiTietHoaDon ct ON s.MaSach = ct.MaSach \r\n"
				+ "JOIN HoaDon hd ON ct.MaHoaDon = hd.MaHoaDon\r\n"
				+ "WHERE s.MaSach = ? AND s.SoLuong >= ? \r\n"
				+ "	AND ? = hd.MaHoaDon");
		stmt.setString(1, ct.getMaSach());
		stmt.setInt(2, ct.getSoLuong());
		stmt.setString(3, ct.getMaHoaDon());
		n = stmt.executeUpdate();

		return n > 0;
	}
	
	public List<ChiTietHoaDon> layCTHDSach(String mahd) {
		List<ChiTietHoaDon> chiTietHoaDonSachs = new ArrayList<ChiTietHoaDon>();
		try {
			String sql = "select * from ChiTietHoaDon where MaHoaDon = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, mahd);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				String maSach = rs.getString(2);
				int soLuong = rs.getInt(3);
				Double donGia = rs.getDouble(4);

				ChiTietHoaDon cthd = new ChiTietHoaDon(maHD, maSach, soLuong, donGia);
				chiTietHoaDonSachs.add(cthd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return chiTietHoaDonSachs;
	}
}
