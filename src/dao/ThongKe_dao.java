package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import db.connectDB;
import entity.ThongKe;

public class ThongKe_dao {
	private Connection con;
	private static ThongKe_dao _instance;

	public ThongKe_dao() {
		con = connectDB.getInstance().getConnection();
	}

	public static ThongKe_dao getInstance() throws Exception {
		if (_instance == null) {
			synchronized (ThongKe_dao.class) {
				if (null == _instance) {
					_instance = new ThongKe_dao();
				}
			}
		}
		return _instance;
	}

	public ArrayList<ThongKe> getAll(java.sql.Date w1, java.sql.Date w2) {
		String GET_ALL;
		ArrayList<ThongKe> arr = new ArrayList<>();
		GET_ALL = "SELECT ct.MaSach, s.TenSach, ct.DonGia, ct.SoLuong, s.SoLuong AS SoLuongTon, ct.SoLuong * ct.DonGia AS ThanhTien "
				+ "FROM ChiTietHoaDon ct " + "JOIN HoaDon hd ON ct.MaHoaDon = hd.MaHoaDon "
				+ "JOIN Sach s ON ct.MaSach = s.MaSach "
				+ "WHERE hd.NgayXuatHD BETWEEN CONVERT(DATETIME, ?, 105) AND CONVERT(DATETIME, ?, 105)";
		try {
			PreparedStatement ps = con.prepareStatement(GET_ALL);
			ps.setDate(1, w1);
			ps.setDate(2, w2);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String maSach = rs.getString(1);
				String tenSach = rs.getString(2);
				Double donGia = rs.getDouble(3);
				int soLuongBan = rs.getInt(4);
				int soLuongTon = rs.getInt(5);
				Double tongTien = rs.getDouble(6);
				ThongKe chiTiet = new ThongKe(maSach, tenSach, donGia, soLuongBan, soLuongTon, tongTien);
				arr.add(chiTiet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arr;
	}

	public double SoLuongSPBanTheoNgay(java.sql.Date tuNgay, java.sql.Date denNgay) {
		double TongSL = 0;
		try {
			String sql = "SELECT SUM(ct.SoLuong) " + "FROM ChiTietHoaDon ct "
					+ "JOIN HoaDon hd ON ct.MaHoaDon = hd.MaHoaDon "
					+ "WHERE hd.NgayXuatHD BETWEEN CONVERT(DATETIME, ?, 105) AND CONVERT(DATETIME, ?, 105)";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, tuNgay);
			ps.setDate(2, denNgay);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				TongSL = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return TongSL;
	}

	public double SoLuongSPTheoQuy(String nam, String thang1, String thang2, String thang3) {
		double TongSL = 0;
		try {
			String sql = "\r\n" + "select Sum(ct.SoLuong) from ChiTietHoaDon ct \r\n" + "join  HoaDon hd \r\n"
					+ "on ct.MaHoaDon= hd.MaHoaDon \r\n"
					+ "where YEAR(hd.NgayXuatHD) = ? and (MONTH(hd.NgayXuatHD) = ?  or MONTH(hd.NgayXuatHD) = ? or MONTH(hd.NgayXuatHD) = ?)";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nam);
			ps.setString(2, thang1);
			ps.setString(3, thang2);
			ps.setString(4, thang3);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				TongSL = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return TongSL;
	}

	public ArrayList<ThongKe> getAllTheoQuy(String nam, String thang1, String thang2, String thang3) {
		String GET_ALL;
		ArrayList<ThongKe> arr = new ArrayList<>();
		GET_ALL = "SELECT ct.MaSach, s.TenSach, ct.DonGia, ct.SoLuong, s.SoLuong AS SoLuongTon, ct.SoLuong * ct.DonGia AS ThanhTien "
				+ "FROM ChiTietHoaDon ct " + "JOIN HoaDon hd ON ct.MaHoaDon = hd.MaHoaDon "
				+ "JOIN Sach s ON ct.MaSach = s.MaSach "
				+ "where YEAR(hd.NgayXuatHD) = ? and (MONTH(hd.NgayXuatHD) = ?  or MONTH(hd.NgayXuatHD) = ? or MONTH(hd.NgayXuatHD) = ?)";
		try {
			PreparedStatement ps = con.prepareStatement(GET_ALL);
			ps.setString(1, nam);
			ps.setString(2, thang1);
			ps.setString(3, thang2);
			ps.setString(4, thang3);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String maSach = rs.getString(1);
				String tenSach = rs.getString(2);
				Double donGia = rs.getDouble(3);
				int soLuongBan = rs.getInt(4);
				int soLuongTon = rs.getInt(5);
				Double tongTien = rs.getDouble(6);
				ThongKe chiTiet = new ThongKe(maSach, tenSach, donGia, soLuongBan, soLuongTon, tongTien);
				arr.add(chiTiet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arr;
	}

	public ArrayList<ThongKe> getAllSPBanChay(java.sql.Date w1, java.sql.Date w2) {
		String GET_ALL;
		ArrayList<ThongKe> arr = new ArrayList<>();
		GET_ALL = "SELECT TOP(5) ct.MaSach, s.TenSach, ct.DonGia, SUM(ct.SoLuong) AS SoLuongBan, s.SoLuong AS SoLuongTon, SUM(ct.SoLuong * ct.DonGia) AS thanhtien\r\n"
				+ "FROM ChiTietHoaDon ct\r\n" + "JOIN HoaDon hd ON ct.MaHoaDon = hd.MaHoaDon\r\n"
				+ "JOIN Sach s ON ct.MaSach = s.MaSach\r\n"
				+ "WHERE hd.NgayXuatHD BETWEEN CONVERT(DATETIME, ?, 105) AND CONVERT(DATETIME, ?, 105)\r\n"
				+ "GROUP BY ct.MaSach, s.TenSach, ct.DonGia, s.SoLuong\r\n" + "ORDER BY SUM(ct.SoLuong) DESC";
		try {
			PreparedStatement ps = con.prepareStatement(GET_ALL);
			ps.setDate(1, w1);
			ps.setDate(2, w2);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String maSach = rs.getString(1);
				String tenSach = rs.getString(2);
				Double donGia = rs.getDouble(3);
				int soLuongBan = rs.getInt(4);
				int soLuongTon = rs.getInt(5);
				Double tongTien = rs.getDouble(6);
				ThongKe chiTiet = new ThongKe(maSach, tenSach, donGia, soLuongBan, soLuongTon, tongTien);
				arr.add(chiTiet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arr;
	};

	public double SoLuongSPBanChay(java.sql.Date tuNgay, java.sql.Date denNgay) {
		double TongSL = 0;
		try {
			String sql = "SELECT SUM(subquery.total_sales) \r\n" + "FROM (\r\n"
					+ "  SELECT TOP(5) SUM(ct.SoLuong) AS total_sales\r\n" + "  FROM ChiTietHoaDon ct\r\n"
					+ "  JOIN HoaDon hd ON ct.MaHoaDon = hd.MaHoaDon\r\n" + "  JOIN Sach s ON ct.MaSach = s.MaSach\r\n"
					+ "  WHERE hd.NgayXuatHD BETWEEN CONVERT(DATETIME, ?, 105) AND CONVERT(DATETIME, ?, 105)\r\n"
					+ "  GROUP BY ct.MaSach, s.TenSach, ct.DonGia\r\n" + "  ORDER BY sum(ct.SoLuong) DESC\r\n"
					+ ") AS subquery";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, tuNgay);
			ps.setDate(2, denNgay);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				TongSL = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return TongSL;
	}

}
